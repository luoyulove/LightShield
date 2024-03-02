package luoyu.lightshield.Client;

import luoyu.lightshield.SyncShield.ShieldPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.util.profiling.jfr.event.PacketEvent;
import net.neoforged.bus.api.SubscribeEvent;

public class ClientShield {
    public static Object onPacket(FriendlyByteBuf buf){
        return new ShieldPacket(buf.readFloat(), buf.readUUID());
    }
}
