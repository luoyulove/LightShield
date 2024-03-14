package luoyu.lightshield;

import luoyu.lightshield.NetWork.SyncShieldSystem;
import luoyu.lightshield.ShieldSystem.Shield;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import static com.mojang.text2speech.Narrator.LOGGER;

public class Api {
    private static float ClientShieldAmount;
    public void getShieldAmount(float shieldAmount){
        ClientShieldAmount = shieldAmount;
    }
    @SubscribeEvent
    public static void DebugForOtherModGetShield(TickEvent.PlayerTickEvent event){
        if (!event.side.isClient() && event.phase == TickEvent.Phase.END && event.player.tickCount % 10 == 0) {
            Player player = event.player;

            LOGGER.info(String.valueOf(Api.ClientShieldAmount));
            player.sendSystemMessage(Component.literal(String.valueOf(Api.ClientShieldAmount)));
        }
    }
}
