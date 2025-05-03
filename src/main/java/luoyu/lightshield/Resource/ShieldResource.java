package luoyu.lightshield.Resource;

import luoyu.lightshield.ShieldSystem.ShieldHudOverlay;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static luoyu.lightshield.LightShield.MODID;
public class ShieldResource {
    @Mod.EventBusSubscriber(modid = "lightshield",  value = {Dist.CLIENT}, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        public static final ResourceLocation SHIELD_I = ResourceLocation.fromNamespaceAndPath(MODID, "textures/gui/icon_shield1.png");
        public static final ResourceLocation SHIELD_II = ResourceLocation.fromNamespaceAndPath(MODID,
                "textures/gui/icon_shield2.png");
        public static final ResourceLocation SHIELD_III = ResourceLocation.fromNamespaceAndPath(MODID,
                "textures/gui/icon_shield3.png");

        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll("textures/gui/icon_shield.png", ShieldHudOverlay.HUD_SHIELD);
            event.registerAboveAll("textures/gui/icon_shield2.png", ShieldHudOverlay.HUD_SHIELD);
            event.registerAboveAll("textures/gui/icon_shield3.png", ShieldHudOverlay.HUD_SHIELD);
        }
    }
}