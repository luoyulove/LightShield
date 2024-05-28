package luoyu.lightshield.NetWork;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.common.Mod;

public class SyncShieldAmount {
    @Mod("lightshield")
    public record shieldAmountData(float shieldAmount) implements CustomPacketPayload {

        public static final CustomPacketPayload.Type<shieldAmountData> TYPE = new CustomPacketPayload.Type<>(new ResourceLocation("lightshield", "shieldamountdata"));
        public static final StreamCodec<ByteBuf, shieldAmountData> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.FLOAT,
                shieldAmountData::shieldAmount,
                shieldAmountData::new
        );

        @Override
        public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }
}
