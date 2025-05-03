package luoyu.lightshield.ShieldSystem;

import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

import static luoyu.lightshield.LightShield.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ShieldAttribute {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, MODID);
    public static final RegistryObject<Attribute> SHIELD_AMOUNT = registerAttribute("shield_amount", 0.0D, 0.0D, 1000000.0D);
    public static final RegistryObject<Attribute> SHIELD_MAX = registerAttribute("shield_max", 4.0D, 0.0D, 1000000.0D);
    public static final RegistryObject<Attribute> SHIELD_REGEN = registerAttribute("shield_regen", 0.25D, 0.0D, 1000000.0D);
    private static RegistryObject<Attribute> registerAttribute(String name, double defaultValue, double min, double max) {
        return ATTRIBUTES.register(
                "lightshield." + name,
                () -> new RangedAttribute("attribute.lightshield." + name, defaultValue, min, max).setSyncable(true)
        );
    }

    private static boolean shouldRegisterAttributes() {
        return !DefaultAttributes.hasSupplier(EntityType.PLAYER);
    }

    @SubscribeEvent
    public static void onEntityAttributeModification(EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER, SHIELD_AMOUNT.get());
        event.add(EntityType.PLAYER, SHIELD_MAX.get());
        event.add(EntityType.PLAYER, SHIELD_REGEN.get());
    }
    // 主类调用此方法进行注册
    public static void register(IEventBus bus) {
        ATTRIBUTES.register(bus);
    }
}