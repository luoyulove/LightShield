package luoyu.lightshield.Items.Potion;

import luoyu.lightshield.Effects.EffectInit;
import luoyu.lightshield.Effects.ShieldRegenEffect;
import luoyu.lightshield.LightShield;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class ShieldRegenPotion extends Potion {
    @Override
    public List<MobEffectInstance> getEffects() {
        MobEffectInstance effectInstance = new MobEffectInstance(EffectInit.EFFECT_SHIELD_REGEN.get(), 200 , 0);
        return Collections.singletonList(effectInstance);
    }
}

