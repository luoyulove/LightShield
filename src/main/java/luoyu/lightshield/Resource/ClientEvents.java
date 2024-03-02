package luoyu.lightshield.Resource;

import luoyu.lightshield.ShieldHUD.ShieldHudOverlay;
import luoyu.lightshield.LightShield;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterGuiOverlaysEvent;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = LightShield.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        public static final ResourceLocation FILLED_SHIELD = new ResourceLocation(LightShield.MOD_ID,
                "textures/gui/icon_shield.png");

        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll(FILLED_SHIELD, ShieldHudOverlay.HUD_SHIELD);
        }
    }
}