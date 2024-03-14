package luoyu.lightshield.ShieldSystem;

import luoyu.lightshield.ModConfig.NeoConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.client.gui.overlay.IGuiOverlay;

import static luoyu.lightshield.Api.ClientShieldAmount;
import static luoyu.lightshield.Resource.ShieldResource.ClientModBusEvents.SHIELD_II;
import static luoyu.lightshield.Resource.ShieldResource.ClientModBusEvents.SHIELD_III;

public class ShieldHudOverlay {
    public static Player getPlayer(){
        return Minecraft.getInstance().player;
    }
    public static final IGuiOverlay HUD_SHIELD = (gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        int x = screenWidth / 2;
        int y = screenHeight;
        Player player = getPlayer();

        if (!new NeoConfig().getConfigOverlay()) {
            return;
        }
        if (player != null && player.isCreative() || player.isSpectator()) {
            return;
        }

        int shieldCount_I = (int) (ClientShieldAmount / 2);
        int shieldCount_II = (int) ((ClientShieldAmount / 2) - 10);

        if (shieldCount_I > 10) {
            shieldCount_I = 10;}
        if (shieldCount_II > 10) {
            shieldCount_II = 10;}

        if (ClientShieldAmount < 21) {
            for (int i = 0; i < shieldCount_I; i++) {
                guiGraphics.blit(SHIELD_II, x - 91 + (i * 8), y - 36, 90, 0, 0, 10, 5,
                        9, 9);
            }
        }
        if (ClientShieldAmount > 21) {
            for (int i = 0; i < shieldCount_II; i++) {
                guiGraphics.blit(SHIELD_III, x - 91 + (i * 8), y - 36, 90, 0, 0, 10, 5,
                        9, 9);
            }
        }
    };
}