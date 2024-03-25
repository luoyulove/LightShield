package luoyu.lightshield.NetWork;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ShieldAmountUpdatePacket {
    private float shieldAmount;

    public ShieldAmountUpdatePacket(float shieldAmount) {
        this.shieldAmount = shieldAmount;
    }

    public float getShieldAmount() {
        return shieldAmount;
    }

    public static void encode(ShieldAmountUpdatePacket packet, FriendlyByteBuf buffer) {
        buffer.writeFloat(packet.getShieldAmount());
    }

    public static ShieldAmountUpdatePacket decode(FriendlyByteBuf buffer) {
        return new ShieldAmountUpdatePacket(buffer.readFloat());
    }

    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
    }
}
