package luoyu.lightshield.ShieldPayload;

import luoyu.lightshield.PlayerShield;
import luoyu.lightshield.ShieldHUD.ShieldHudOverlay;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handlers.ServerPayloadHandler;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import org.apache.logging.log4j.core.jmx.Server;


import static com.mojang.text2speech.Narrator.LOGGER;

public class ClientPayloadHandler {
    private static final ClientPayloadHandler Client = new ClientPayloadHandler();

    public static ClientPayloadHandler getClient() {
        return Client;
    }
    public void handleData(final SyncShieldSystem.ShieldData shielddata, final PlayPayloadContext context) {
        Player player = context.player().get();
        float shieldAmount = shielddata.shieldAmount();

        context.workHandler().submitAsync(() -> {
                    PlayerShield playerShield = PlayerShield.getPlayerShield(player);
                    playerShield.setShieldAmount(shieldAmount);
                    System.out.println(shieldAmount);
                    LOGGER.info(String.valueOf(shieldAmount));

                    ShieldHudOverlay.setShieldAmount(shieldAmount);
                })
                .exceptionally(e -> {
                    context.packetHandler().disconnect(Component.translatable("LightShield.networking.failed", e.getMessage()));
                    LOGGER.info(String.valueOf(shieldAmount));
                    return null;
                });
    }
}