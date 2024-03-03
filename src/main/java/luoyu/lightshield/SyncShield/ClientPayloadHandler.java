package luoyu.lightshield.SyncShield;

import luoyu.lightshield.PlayerShield;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import java.util.logging.Logger;

import static com.mojang.text2speech.Narrator.LOGGER;

public class ClientPayloadHandler {

    private static final ClientPayloadHandler INSTANCE = new ClientPayloadHandler();

    public static ClientPayloadHandler getInstance() {
        return INSTANCE;
    }

    public void handleData(final ShieldPacket data, final PlayPayloadContext context) {
        float ShieldAmount = data.shieldAmount();
        LOGGER.info(String.valueOf(data.shieldAmount()));

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
                    System.out.println(data.shieldAmount());
                    LOGGER.info(String.valueOf(data.shieldAmount()));
                    Player player = Minecraft.getInstance().player;
                    if (player != null) {
                        PlayerShield playerShield = PlayerShield.getPlayerShield(player);
                        playerShield.setShieldAmount(ShieldAmount);
                    }
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(Component.translatable("LightShield.networking.failed", e.getMessage()));
                    return null;
                });
    }
}