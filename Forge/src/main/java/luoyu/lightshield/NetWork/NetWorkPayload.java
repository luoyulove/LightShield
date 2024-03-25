package luoyu.lightshield.NetWork;

import luoyu.lightshield.Api;
import luoyu.lightshield.ShieldSystem.Shield;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import static luoyu.ModStatic.MOD_ID;

public class NetWorkPayload {
    private static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(MOD_ID, "network"))
            .networkProtocolVersion(() -> String.valueOf(1))
            .optional()
            .acceptedVersions((status, version) -> true)
            .simpleChannel();

    public static void registerPackets() {
        int id = 0;
        CHANNEL.registerMessage(id++, ShieldAmountUpdatePacket.class,
                ShieldAmountUpdatePacket::encode,
                ShieldAmountUpdatePacket::decode,
                ShieldAmountUpdatePacket::handle);
    }
    public static void handle(ShieldAmountUpdatePacket packet, NetworkEvent.Context context) {
        context.enqueueWork(() -> {
            Player player = context.getSender();
            onShieldAmountReceived(player, packet.getShieldAmount());
        });
        context.setPacketHandled(true);
    }

    private static void onShieldAmountReceived(Player player, float shieldAmount) {
        Shield.getPlayerShield(player).setShieldAmount(shieldAmount);
    }
}