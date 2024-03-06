package luoyu.lightshield.Effects;

import luoyu.lightshield.Enchantment.ShieldRegenEnchant;
import luoyu.lightshield.LightShield;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class EffectInit {
    public static final DeferredRegister<MobEffect> EFFECT = DeferredRegister.create(Registries.MOB_EFFECT, LightShield.MOD_ID);
    private static <T extends MobEffect> DeferredHolder<MobEffect, T> register(String _id, Supplier<T> _sup) {
        return EFFECT.register(_id, _sup);
    }
    public static final DeferredHolder<MobEffect, ShieldMaxEffect> EFFECT_SHIELD_MAX = register("shield_max", ShieldMaxEffect::new);
    public static final DeferredHolder<MobEffect, ShieldRegenEffect> EFFECT_SHIELD_REGEN = register("shield_regen", ShieldRegenEffect::new);
}
