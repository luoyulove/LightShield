package luoyu.lightshield.Effects;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static luoyu.ModStatic.MOD_ID;

public class EffectInit {
    public static final DeferredRegister<MobEffect> EFFECT = DeferredRegister.create(Registries.MOB_EFFECT, MOD_ID);

    public static final RegistryObject<MobEffect> SHIELD_COOLDOWN = EFFECT.register("shield_cooldown", ShieldCooldownEffect::new);
    public static final RegistryObject<MobEffect> SHIELD_MAX = EFFECT.register("shield_max", ShieldMaxEffect::new);
    public static final RegistryObject<MobEffect> SHIELD_REGEN = EFFECT.register("shield_regen", ShieldRegenEffect::new);
}
