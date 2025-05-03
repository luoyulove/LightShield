package luoyu.lightshield.PotionEffect;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static net.neoforged.neoforge.internal.versions.neoforge.NeoForgeVersion.MOD_ID;

public class EffectInit {
    public static final DeferredRegister<MobEffect> EFFECT = DeferredRegister.create(Registries.MOB_EFFECT, MOD_ID);
    private static <T extends MobEffect> DeferredHolder<MobEffect, T> register(String _id, Supplier<T> _sup) {
        return EFFECT.register(_id, _sup);
    }
    public static final DeferredHolder<MobEffect, ShieldMaxEffect> EFFECT_SHIELD_MAX = register("shield_max", ShieldMaxEffect::new);
    public static final DeferredHolder<MobEffect, ShieldRegenEffect> EFFECT_SHIELD_REGEN = register("shield_regen", ShieldRegenEffect::new);
    public static final DeferredHolder<MobEffect, ShieldCooldownEffect> EFFECT_SHIELD_COOLDOWN = register("shield_cooldown", ShieldCooldownEffect::new);
}