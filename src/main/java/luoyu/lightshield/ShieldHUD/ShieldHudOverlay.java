package luoyu.lightshield.ShieldHUD;

import com.mojang.blaze3d.systems.RenderSystem;
import luoyu.lightshield.PlayerShield;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.client.gui.overlay.IGuiOverlay;
import net.neoforged.neoforge.network.PacketDistributor;

import static luoyu.lightshield.Resource.ShieldIcon.ClientModBusEvents.FILLED_SHIELD;
public class ShieldHudOverlay {
    public static Player getPlayer(){
        return ShieldHudOverlay.getOnlinePlayer(Minecraft.getInstance().player);
    }
    public static Player getOnlinePlayer(Player player){
        PlayerShield.getPlayerShield(player);
        return player;
    }
    public static final IGuiOverlay HUD_SHIELD = (gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        int x = screenWidth / 2;
        int y = screenHeight;

        Player player = getPlayer();
        if (player != null && player.isCreative()) {
            return;
        }

        int shieldCount = (int) (PlayerShield.getPlayerShield(getPlayer()).getShieldAmount() / 2);
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