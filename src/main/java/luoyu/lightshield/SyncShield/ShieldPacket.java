package luoyu.lightshield.SyncShield;

import luoyu.lightshield.LightShield;
import luoyu.lightshield.PlayerShield;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.social.PlayerEntry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.network.ServerPlayerConnection;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.handlers.ServerPayloadHandler;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;

import javax.swing.text.html.parser.Entity;

public record ShieldPacket(float shield_amount) implements CustomPacketPayload {

    public static final ResourceLocation ID = new ResourceLocation(LightShield.MOD_ID, "shield_amount");

    public ShieldPacket(final FriendlyByteBuf buffer) {
        this(buffer.readFloat());
    }
    @Override
    public void write(final FriendlyByteBuf buffer) {
        buffer.writeFloat(shield_amount);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }
    @SubscribeEvent
    public static void registerPacketPayload(final RegisterPayloadHandlerEvent event) {
        final IPayloadRegistrar registrar = event.registrar(LightShield.MOD_ID);
        registrar.play(ShieldPacket.ID, ShieldPacket::new, handler -> handler
                .client(ClientPayloadHandler.getInstance()::handleData));
    }
}