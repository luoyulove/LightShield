package luoyu.lightshield.NetWork;

import luoyu.lightshield.Api;
import luoyu.lightshield.ShieldSystem.Shield;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;


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

        public static void handle(ShieldPacket packet, Supplier<NetworkEvent.Context> context) {

            if (context.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
                context.get().enqueueWork(() -> {
                    ServerPlayer serverPlayer = context.get().getSender();
                    updateShieldAmount(serverPlayer, packet.shieldAmount);
                    Shield.getPlayerShield(serverPlayer).setShieldAmount(packet.shieldAmount);
                });
                context.get().setPacketHandled(true);
            }
        }
        public static void updateShieldAmount(Player player, float shieldAmount) {
            Api.setShieldAmount(player, shieldAmount);
            Shield.getPlayerShield(player).setShieldAmount(shieldAmount);
        }
    }
}
