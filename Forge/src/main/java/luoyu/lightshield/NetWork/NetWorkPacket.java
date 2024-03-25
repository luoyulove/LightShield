package luoyu.lightshield.NetWork;

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
    public static class NetWorkPacketd {
        public static float shieldAmount;

        public NetWorkPacketd(float shieldAmount) {
            this.shieldAmount = shieldAmount;
        }

        public static void encode(NetWorkPacketd packet, FriendlyByteBuf buffer) {
            buffer.writeFloat(packet.shieldAmount);
        }
        public static NetWorkPacketd decode(FriendlyByteBuf buffer) {
            return new NetWorkPacketd(buffer.readFloat());
        }
        private static Player getPlayer(NetworkEvent.Context context) {
            return context.getSender();
//            return Minecraft.getInstance().player;
        }
        public static void handle(luoyu.lightshield.NetWork.NetWorkPacket.NetWorkPacketd packet, Supplier<NetworkEvent.Context> context) {
            context.get().enqueueWork(() -> {
                Player player = getPlayer(context.get());
                if (player != null) {
                    updateShieldAmount(player,NetWorkPacketd.shieldAmount);
                }
            });
            context.get().setPacketHandled(true);
        }
        public static void updateShieldAmount(Player player, float shieldAmount) {
            Shield.getPlayerShield(player).setShieldAmount(shieldAmount);
        }
    }
}
