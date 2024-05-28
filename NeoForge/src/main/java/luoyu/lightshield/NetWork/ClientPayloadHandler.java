package luoyu.lightshield.NetWork;

import luoyu.lightshield.ShieldSystem.Shield;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ClientPayloadHandler {

    public static void handleData(final SyncShieldAmount.shieldAmountData shieldAmountData, final IPayloadContext context) {
        Player player = context.player();
        float shieldAmount = shieldAmountData.shieldAmount();

        context.enqueueWork(() -> {
            Shield shield = Shield.getPlayerShield(player);
            shield.setShieldAmount(shieldAmount);
        }).exceptionally(e -> {
            context.disconnect(Component.translatable("lightshield.networking.failed", e.getMessage()));
            return null;
        });
    }
}