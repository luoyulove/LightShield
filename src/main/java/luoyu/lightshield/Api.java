package luoyu.lightshield;

import luoyu.lightshield.NetWork.SyncShieldSystem;
import luoyu.lightshield.ShieldSystem.Shield;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import static com.mojang.text2speech.Narrator.LOGGER;

public class Api {
    public static float ClientShieldAmount;
    public static void getShieldAmount(float shieldAmount){
        ClientShieldAmount = shieldAmount;
    }
}
