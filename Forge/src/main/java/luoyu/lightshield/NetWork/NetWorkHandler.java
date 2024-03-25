package luoyu.lightshield.NetWork;

import luoyu.lightshield.ShieldSystem.Shield;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.Supplier;

import static luoyu.ModStatic.MOD_ID;
import static net.minecraft.commands.arguments.EntityArgument.getPlayer;

public class NetWorkHandler {
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MOD_ID, "network"), () -> ".", s -> true, s -> true
    );
    public static void init() {
        CHANNEL.registerMessage(0, NetWorkPacket.NetWorkPacketd.class, NetWorkPacket.NetWorkPacketd::encode, NetWorkPacket.NetWorkPacketd::decode, NetWorkPacket.NetWorkPacketd::handle);
    }
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event){
        if (event.player instanceof ServerPlayer player){
            float shieldAmount = Shield.getPlayerShield(player).getShieldAmount();
            CHANNEL.sendTo(new NetWorkPacket.NetWorkPacketd(shieldAmount), player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
        }
    }
}
