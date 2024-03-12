package luoyu.lightshield.ShieldSystem;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.client.gui.overlay.IGuiOverlay;

import static luoyu.lightshield.Resource.ShieldResource.ClientModBusEvents.*;

public class ShieldHud {
    private static float ClientShieldAmount;
    public static Player getPlayer(){
        return Minecraft.getInstance().player;
    }
    public static void getShieldAmount(float shieldAmount){
        ClientShieldAmount = shieldAmount;
    }
    public static final IGuiOverlay HUD_SHIELD = (gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        int x = screenWidth / 2;
        int y = screenHeight;

        Player player = getPlayer();
        if (player != null && player.isCreative() && player.isSpectator()) {
            return;
        }

        int shieldCount_I = (int) (ClientShieldAmount / 2);
        int shieldCount_II = (int) ((ClientShieldAmount / 2) - 10);
        int shieldCount_III = (int) ((ClientShieldAmount / 2) - 20);
        if (shieldCount_I > 10) {
            shieldCount_I = 10;
        }
        if (shieldCount_II > 10) {
            shieldCount_II = 10;
        }
        if (shieldCount_III > 10) {
            shieldCount_III = 10;
        }

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, SHIELD_I);

        if (ClientShieldAmount < 20) {
            for (int i = 0; i < shieldCount_I; i++) {
                guiGraphics.blit(SHIELD_II, x - 91 + (i * 8), y - 36, 90, 0, 0, 10, 5,
                        9, 9);
            }
        }
        if (ClientShieldAmount > 20) {
            for (int i = 0; i < shieldCount_II; i++) {
                guiGraphics.blit(SHIELD_III, x - 91 + (i * 8), y - 36, 90, 0, 0, 10, 5,
                        9, 9);
            }
        }
//        if (ClientShieldAmount > 40) {
//            for (int i = 0; i < shieldCount_III; i++) {
//                guiGraphics.blit(SHIELD_III, x - 91 + (i * 8), y - 36, 90, 0, 0, 10, 5,
//                        9, 9);
//            }
//        }
    };
}