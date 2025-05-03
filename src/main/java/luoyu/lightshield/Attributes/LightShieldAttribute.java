package luoyu.lightshield.Attributes;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.EntityType;
import net.minecraft.core.registries.BuiltInRegistries;

import static luoyu.lightshield.LightShield.MODID;


@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class LightShieldAttribute {
	public static final DeferredRegister<Attribute> REGISTRY = DeferredRegister.create(BuiltInRegistries.ATTRIBUTE, MODID);

	public static final DeferredHolder<Attribute, Attribute> shield_Amount = REGISTRY.register(
			"lightshield.shield_amount", () -> new RangedAttribute("attribute.lightshield.shield_amount", 0, 0, 1000000)
					.setSyncable(true));
	public static final DeferredHolder<Attribute, Attribute> shield_max = REGISTRY.register(
			"lightshield.shield_max", () -> new RangedAttribute("attribute.lightshield.shield_max", 4, 4, 1000000)
					.setSyncable(true));
	public static final DeferredHolder<Attribute, Attribute> shield_regen = REGISTRY.register(
			"lightshield.shield_regen", () -> new RangedAttribute("attribute.lightshield.shield_regenshield_regen", 0.25, 0.25, 1000000)
					.setSyncable(true));

	@SubscribeEvent
	public static void addShieldAmount(EntityAttributeModificationEvent event) {
		event.add(EntityType.PLAYER, shield_Amount);
	}
	@SubscribeEvent
	public static void addShieldMax(EntityAttributeModificationEvent event) {
		event.add(EntityType.PLAYER, shield_max);
	}
}