package luoyu.lightshield.SyncShield;

import io.netty.channel.pool.SimpleChannelPool;
import luoyu.lightshield.LightShield;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.PacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.handling.IPacketHandler;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;

import java.util.UUID;

public class ShieldPacket implements CustomPacketPayload {
    private float shieldAmount;
    private UUID player_uuid;

    public ShieldPacket(float shieldAmount, UUID player_uuid) {
        this.shieldAmount = shieldAmount;
        this.player_uuid = player_uuid;
    }

    public static ShieldPacket fromBytes(FriendlyByteBuf buf) {
        return new ShieldPacket(buf.readFloat(), buf.readUUID());
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeFloat(this.shieldAmount);
        buf.writeUUID(this.player_uuid);
    }

    @Override
    public ResourceLocation id() {
        return new ResourceLocation(LightShield.MOD_ID, "shield_amount");
    }

    public float getShieldAmount() {
        return shieldAmount;
    }

    public UUID getPlayerUUID() {
        return player_uuid;
    }
}