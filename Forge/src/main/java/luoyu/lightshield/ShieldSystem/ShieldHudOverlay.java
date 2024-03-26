package luoyu.lightshield.ShieldSystem;

import luoyu.lightshield.Api;
import luoyu.lightshield.Config.ForgeConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

import static luoyu.lightshield.Resource.ShieldResource.ClientModBusEvents.SHIELD_II;
import static luoyu.lightshield.Resource.ShieldResource.ClientModBusEvents.SHIELD_III;

public class ShieldHudOverlay {
    public static final IGuiOverlay HUD_SHIELD = (gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        int x = screenWidth / 2;
        int y = screenHeight;
        Player player = Minecraft.getInstance().player;

        if (!new ForgeConfig().EnableOverlay()) {
            return;
        }
        if (player != null && (player.isCreative() || player.isSpectator())) {
            return;
        }

        float shieldAmount = Api.getShieldAmount(player);

        int shieldCount_I = (int) (shieldAmount / 2);
        int shieldCount_II = (int) ((shieldAmount / 2) - 10);

        if (shieldCount_I > 10) {
            shieldCount_I = 10;}
        if (shieldCount_II > 10) {
            shieldCount_II = 10;}

        if (shieldAmount < 21) {
            for (int i = 0; i < shieldCount_I; i++) {
                guiGraphics.blit(SHIELD_II, x - 91 + (i * 8), y - 36, 90, 0, 0, 10, 5,
                        9, 9);
            }
        }
        if (shieldAmount > 21) {
            for (int i = 0; i < shieldCount_II; i++) {
                guiGraphics.blit(SHIELD_III, x - 91 + (i * 8), y - 36, 90, 0, 0, 10, 5,
                        9, 9);
            }
        }
    };
}