package luoyu.lightshield.NetWork;

import luoyu.lightshield.Api;
import luoyu.lightshield.ShieldSystem.Shield;
import luoyu.lightshield.ShieldSystem.ShieldHudOverlay;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;


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
            Shield shield = Shield.getPlayerShield(player);
            shield.setShieldAmount(shieldAmount);

                      // for DEBUG
//                    System.out.println(shieldAmount);
//                    LOGGER.info(String.valueOf(shieldAmount));
            Api.getShieldAmount(shieldAmount);
        })
                .exceptionally(e -> {
                    context.packetHandler().disconnect(Component.translatable("LightShield.networking.failed", e.getMessage()));
                    LOGGER.info(String.valueOf(shieldAmount));
                    return null;
                });
    }
}