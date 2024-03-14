//package luoyu.lightshield.NetWork;
//
//import luoyu.lightshield.LightShield;
//import net.minecraft.network.FriendlyByteBuf;
//import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
//import net.minecraft.resources.ResourceLocation;
//import net.neoforged.bus.api.SubscribeEvent;
//import net.neoforged.fml.common.Mod;
//import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
//import net.neoforged.neoforge.network.registration.IPayloadRegistrar;
//
//public class SyncShieldMax {
//    @Mod.EventBusSubscriber(modid = "lightshield", bus = Mod.EventBusSubscriber.Bus.MOD)
//    public record shieldMaxData(float maxShieldAmount) implements CustomPacketPayload {
//
//        public static final ResourceLocation ID = new ResourceLocation(LightShield.MOD_ID, "maxshieldamount");
//
//        public shieldMaxData(final FriendlyByteBuf buffer) {
//            this(buffer.readFloat());
//        }
//        @Override
//        public void write(final FriendlyByteBuf buffer) {
//            buffer.writeFloat(this.maxShieldAmount);
//        }
//        @Override
//        public ResourceLocation id() {
//            return ID;
//        }
//        @SubscribeEvent
//        public static void registerServerPayload(final RegisterPayloadHandlerEvent event) {
//            final IPayloadRegistrar registrar = event.registrar(LightShield.MOD_ID);
//            registrar.play(shieldMaxData.ID, shieldMaxData::new, handler -> handler
//                    .client(ClientPayloadHandler.getClient()::handleShieldAmountData));
//        }
//    }
//}
