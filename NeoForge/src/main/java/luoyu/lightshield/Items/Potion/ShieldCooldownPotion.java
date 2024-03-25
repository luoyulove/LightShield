package luoyu.lightshield.Items.Potion;

import luoyu.lightshield.Effects.EffectInit;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;

import java.util.Collections;
import java.util.List;

public class ShieldCooldownPotion extends Potion {
    @Override
    public List<MobEffectInstance> getEffects() {
        MobEffectInstance effectInstance = new MobEffectInstance(EffectInit.EFFECT_SHIELD_COOLDOWN.get(), 100 , 0);
        return Collections.singletonList(effectInstance);
    }
}