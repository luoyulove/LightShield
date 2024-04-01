package luoyu.lightshield.NetWork;

import luoyu.lightshield.Api;
import luoyu.lightshield.ShieldSystem.Shield;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;


//import static com.mojang.text2speech.Narrator.LOGGER;

public class ClientPayloadHandler {
    private static final ClientPayloadHandler Client = new ClientPayloadHandler();

    public static ClientPayloadHandler getClient() {
        return Client;
    }
    public void handleShieldAmountData(final SyncShieldAmount.shieldAmountData shieldAmountData, final PlayPayloadContext context) {
        Player player = context.player().get();
        float shieldAmount = shieldAmountData.shieldAmount();

        context.workHandler().submitAsync(() -> {
            Shield shield = Shield.getPlayerShield(player);
            shield.setShieldAmount(shieldAmount);
        })
                .exceptionally(e -> {
                    context.packetHandler().disconnect(Component.translatable("LightShield.networking.failed", e.getMessage()));
//                    LOGGER.info(String.valueOf(shieldAmount));
                    return null;
                });
    }
//    public void handleShieldMaxAmountData(final SyncShieldAmount.shieldMaxAmountData shieldMaxAmountData, final PlayPayloadContext context) {
//        Player player = context.player().get();
//        float maxShieldAmount = shieldMaxAmountData.shieldAmount();
//
//        context.workHandler().submitAsync(() -> {
//                    Shield shield = Shield.getPlayerShield(player);
//                    shield.setShieldAmount(maxShieldAmount);
//                })
//                .exceptionally(e -> {
//                    context.packetHandler().disconnect(Component.translatable("LightShield.networking.failed", e.getMessage()));
//                    LOGGER.info(String.valueOf(maxShieldAmount));
//                    return null;
//                });
//    }
}