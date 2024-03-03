package luoyu.lightshield.ShieldPayload;

import luoyu.lightshield.LightShield;
import luoyu.lightshield.PlayerShield;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;

public record ShieldPacket(float shieldAmount) implements CustomPacketPayload {

    public static final ResourceLocation ID = new ResourceLocation(LightShield.MOD_ID, "shieldamount");

    public ShieldPacket(final FriendlyByteBuf buffer) {
        this(buffer.readFloat());
    }
    @Override
    public void write(final FriendlyByteBuf buffer) {
        buffer.writeFloat(shieldAmount);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }
    @SubscribeEvent
    public static void registerServerPayload(final RegisterPayloadHandlerEvent event) {
        final IPayloadRegistrar registrar = event.registrar(LightShield.MOD_ID);
        registrar.play(ShieldPacket.ID, ShieldPacket::new, handler -> handler
                .client(luoyu.lightshield.ShieldPayload.ClientPayloadHandler.getInstance()::handleData));
    }
}