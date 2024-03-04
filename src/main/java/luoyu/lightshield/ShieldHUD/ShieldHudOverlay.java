package luoyu.lightshield.ShieldHUD;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.client.gui.overlay.IGuiOverlay;

import static luoyu.lightshield.Resource.ShieldResource.ClientModBusEvents.FILLED_SHIELD;
public class ShieldHudOverlay {
    private static float ClientShieldAmount;
    public static Player getPlayer(){
        return Minecraft.getInstance().player;
    }
    public static void setShieldAmount(float shieldAmount){
        ClientShieldAmount = shieldAmount;
    }
    public static final IGuiOverlay HUD_SHIELD = (gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        int x = screenWidth / 2;
        int y = screenHeight;

        Player player = getPlayer();
        if (player != null && player.isCreative() && player.isSpectator()) {
            return;
        }

        int shieldCount = (int) (ClientShieldAmount / 2);
//        int shieldCount = (int) (PlayerShield.getPlayerShie / 2);
        if (shieldCount > 10) {
            shieldCount = 10;
        }

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, FILLED_SHIELD);

        for (int i = 0; i < shieldCount; i++) {
                guiGraphics.blit(FILLED_SHIELD, x - 91 + (i * 8), y - 36, 90, 0, 0, 10, 5,
                        9, 9);
            }
    };
}