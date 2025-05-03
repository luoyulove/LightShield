package luoyu.lightshield.ShieldEvent;

import luoyu.lightshield.ShieldSystem.ShieldMain;
import net.minecraft.server.TickTask;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class ShieldRegenEvent {
    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent.Post event){
        Player player = event.getEntity();
        if (player.tickCount == 10){
            ShieldMain.shieldRegenAmount(player);
        }
    }
}