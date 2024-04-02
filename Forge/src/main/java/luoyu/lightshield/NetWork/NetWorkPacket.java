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

        // 服务器端中传输玩家的护盾值
        public static void handle(ShieldPacket packet, Supplier<NetworkEvent.Context> context) {
            context.get().enqueueWork(() -> {
                ServerPlayer serverPlayer = context.get().getSender();
                updateShieldAmount(packet.shieldAmount);
                Shield.getPlayerShield(serverPlayer).setShieldAmount(packet.shieldAmount);
            });
            context.get().setPacketHandled(true);
        }
    }

    // 使用LocalPlayer获取本地玩家，并使用服务器端护盾值来进行渲染显示
    public static void updateShieldAmount(float shieldAmount) {
        Player player = Minecraft.getInstance().player;
        Api.setShieldAmount(player, shieldAmount);
        Shield.getPlayerShield(player).setShieldAmount(shieldAmount);
    }
}
