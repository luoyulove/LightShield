package luoyu.lightshield.NetWork;

import luoyu.lightshield.ShieldSystem.Shield;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import org.apache.logging.log4j.core.jmx.Server;

import java.util.function.Supplier;

public class ShieldAmountPacket {
    private float shieldAmount;

    public ShieldAmountPacket(float shieldAmount) {
        this.shieldAmount = shieldAmount;
    }

    public float getShieldAmount() {
        return shieldAmount;
    }

    public static void encode(ShieldAmountPacket packet, FriendlyByteBuf buffer) {
        buffer.writeFloat(packet.getShieldAmount());
    }

    public static ShieldAmountPacket decode(FriendlyByteBuf buffer) {
        return new ShieldAmountPacket(buffer.readFloat());
    }

    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
        ServerPlayer player = contextSupplier.get().getSender();
        if (player != null){
            Shield.getPlayerShield(player).setShieldAmount(shieldAmount);
        }
    }
}
