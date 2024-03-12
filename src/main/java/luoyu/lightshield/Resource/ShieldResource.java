package luoyu.lightshield.Resource;

import luoyu.lightshield.ShieldSystem.ShieldHud;
import luoyu.lightshield.LightShield;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterGuiOverlaysEvent;

public class ShieldResource {
    @Mod.EventBusSubscriber(modid = LightShield.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        public static final ResourceLocation SHIELD_I = new ResourceLocation(LightShield.MOD_ID,
                "textures/gui/icon_shield.png");
        public static final ResourceLocation SHIELD_II = new ResourceLocation(LightShield.MOD_ID,
                "textures/gui/icon_shield2.png");
        public static final ResourceLocation SHIELD_III = new ResourceLocation(LightShield.MOD_ID,
                "textures/gui/icon_shield3.png");
        public static final ResourceLocation EFFECT_SHIELD_REGEN = new ResourceLocation(LightShield.MOD_ID,
                "textures/gui/icon_shield.png");
        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll(SHIELD_I, ShieldHud.HUD_SHIELD);
            event.registerAboveAll(SHIELD_II, ShieldHud.HUD_SHIELD);
            event.registerAboveAll(SHIELD_III, ShieldHud.HUD_SHIELD);
        }
    }
}