package luoyu.lightshield.SyncShield;

import luoyu.lightshield.PlayerShield;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import static com.mojang.text2speech.Narrator.LOGGER;

public class ClientPayloadHandler {

    private static final ClientPayloadHandler INSTANCE = new ClientPayloadHandler();

    public static ClientPayloadHandler getInstance() {
        return INSTANCE;
    }

    public void handleData(final ShieldPacket data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread
        new PlayerShield(Minecraft.getInstance().player).setShieldAmount(data.shield_amount());
        System.out.println(data.shield_amount());
        LOGGER.info(String.valueOf(data.shield_amount()));

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
                    System.out.println(data.shield_amount());
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(Component.translatable("LightShield.networking.failed", e.getMessage()));
                    return null;
                });
    }
}