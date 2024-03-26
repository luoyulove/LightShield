package luoyu.lightshield.NetWork;

import luoyu.lightshield.Api;
import luoyu.lightshield.ShieldSystem.Shield;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import static luoyu.lightshield.NetWork.NetWorkHandler.CHANNEL;

public class NetWorkPacket {
    public static class ShieldPacket {
        public float shieldAmount;

        public ShieldPacket(float shieldAmount) {
            this.shieldAmount = shieldAmount;
        }

        public static void encode(ShieldPacket packet, FriendlyByteBuf buffer) {
            buffer.writeFloat(packet.shieldAmount);
        }
        public static ShieldPacket decode(FriendlyByteBuf buffer) {
            return new ShieldPacket(buffer.readFloat());
        }
        private static Player getPlayer(NetworkEvent.Context context) {
            return context.getDirection() == NetworkDirection.PLAY_TO_SERVER ? context.getSender() : Minecraft.getInstance().player;
        }
        public static void handle(ShieldPacket packet, Supplier<NetworkEvent.Context> context) {
            context.get().enqueueWork(() -> {
                Player player = getPlayer(context.get());
                    updateShieldAmount(player,packet.shieldAmount);
                    Shield.getPlayerShield(player).setShieldAmount(packet.shieldAmount);
            });
            context.get().setPacketHandled(true);
        }
        public static void updateShieldAmount(Player player, float shieldAmount) {
            Api.setShieldAmount(player, shieldAmount);
            Shield.getPlayerShield(player).setShieldAmount(shieldAmount);
        }
    }
}
