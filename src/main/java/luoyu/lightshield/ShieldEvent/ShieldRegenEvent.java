package luoyu.lightshield.ShieldEvent;

import luoyu.lightshield.ShieldSystem.ShieldMain;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import static luoyu.lightshield.LightShield.MODID;

@Mod(MODID)
public class ShieldRegenEvent {
    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent.Post event){
        Player player = event.getEntity();
        if (player.tickCount == 10){
            ShieldMain.shieldRegenAmount(player);
        }
    }
}