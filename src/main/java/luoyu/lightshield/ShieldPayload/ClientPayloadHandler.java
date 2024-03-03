package luoyu.lightshield.ShieldPayload;

import luoyu.lightshield.PlayerShield;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;


import static com.mojang.text2speech.Narrator.LOGGER;

public class ClientPayloadHandler {
    private static final ClientPayloadHandler INSTANCE = new ClientPayloadHandler();

    public static ClientPayloadHandler getInstance() {
        return INSTANCE;
    }

    public void handleData(final PlayerShield.shieldData data, final PlayPayloadContext context) {
        float shieldAmount = data.shieldAmount();
        Player player = context.player().get();

        context.workHandler().submitAsync(() -> {
                    PlayerShield playerShield = PlayerShield.getPlayerShield(player);
                    playerShield.setShieldAmount(shieldAmount);
                    System.out.println(shieldAmount);
                    LOGGER.info(String.valueOf(shieldAmount));
                })
                .exceptionally(e -> {
                    context.packetHandler().disconnect(Component.translatable("LightShield.networking.failed", e.getMessage()));
                    LOGGER.info(String.valueOf(shieldAmount));
                    return null;
                });
    }
}