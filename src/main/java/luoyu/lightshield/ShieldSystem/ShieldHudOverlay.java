package luoyu.lightshield.ShieldSystem;

import net.minecraft.client.gui.GuiGraphics;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.Minecraft;

@EventBusSubscriber({Dist.CLIENT})
public class ShieldHudOverlay {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void eventHandler(RenderGuiEvent.Pre event) {
        GuiGraphics guiGraphics = event.getGuiGraphics();

        int x = guiGraphics.guiWidth() / 2;
        int y = guiGraphics.guiHeight();

        Player player = Minecraft.getInstance().player;
        if (player.isCreative() || player.isSpectator()) return;

        Double shieldAmount = ShieldMain.INSTANCE.getShieldAmount(player);

        int shieldIconCount1 = (int) (shieldAmount / 2);
        int shieldIconCount2 = (int) (shieldAmount / 2) - 10;

        if (shieldAmount < 21){
            for (int i = 0; i < shieldIconCount1; i++){
                guiGraphics.blit(ResourceLocation.parse("lightshield:textures/gui/icon_shield2.png"), x - 91 + (i * 8), y - 36, 90, 0, 0, 10, 5,
                        9, 9);
            }
        }
        if (shieldAmount > 21) {
            for (int i = 0; i < shieldIconCount2; i++) {
                guiGraphics.blit(ResourceLocation.parse("lightshield:textures/gui/icon_shield3.png"), x - 91 + (i * 8), y - 36, 90, 0, 0, 10, 5,
                        9, 9);
            }
        }
    }
}
