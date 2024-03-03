package luoyu.lightshield.ShieldPayload;

import luoyu.lightshield.PlayerShield;
import luoyu.lightshield.ShieldPayload.ShieldPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import java.util.UUID;
import java.util.logging.Logger;

import static com.mojang.text2speech.Narrator.LOGGER;

public class ClientPayloadHandler {

    private static final ClientPayloadHandler INSTANCE = new ClientPayloadHandler();

    public static ClientPayloadHandler getInstance() {
        return INSTANCE;
    }

    public void handleData(final ShieldPacket data, final PlayPayloadContext context) {
        float shieldAmount = data.shieldAmount();
        Player player = context.player().get();
        PlayerShield playerShield = PlayerShield.getPlayerShield(player);
        playerShield.setShieldAmount(shieldAmount);
        player.sendSystemMessage(Component.literal(String.valueOf(shieldAmount)));

        System.out.println(shieldAmount);
        LOGGER.info(String.valueOf(shieldAmount));
        context.workHandler().submitAsync(() -> {
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