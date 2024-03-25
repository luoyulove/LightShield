package luoyu.lightshield.NetWork;

import luoyu.lightshield.Api;
import luoyu.lightshield.ShieldSystem.Shield;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.Optional;

import static luoyu.ModStatic.MOD_ID;

public class NetWorkPayload {
    private static final String CHANNEL_NAME = "lightshield_shieldamount";
    private static final String PROTOCOL_VERSION = "1";
    private static int packetID = 1;
    private static SimpleChannel channel;

    public static void onCommonSetup(FMLCommonSetupEvent event) {
        channel = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(MOD_ID, CHANNEL_NAME),
                () -> PROTOCOL_VERSION,
                PROTOCOL_VERSION::equals,
                PROTOCOL_VERSION::equals
        );
        channel.registerMessage(packetID++, ShieldAmountPacket.class,
                ShieldAmountPacket::encode,
                ShieldAmountPacket::decode,
                ShieldAmountPacket::handle,
                Optional.of(NetworkDirection.PLAY_TO_CLIENT));
    }

    public static void handle(ShieldAmountPacket packet, NetworkEvent.Context context) {
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