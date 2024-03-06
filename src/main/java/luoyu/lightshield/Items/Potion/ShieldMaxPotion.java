package luoyu.lightshield.Items.Potion;

import luoyu.lightshield.Effects.EffectInit;
import luoyu.lightshield.Effects.ShieldMaxEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.client.event.RenderTooltipEvent;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class ShieldMaxPotion extends Potion {
    @Override
    public List<MobEffectInstance> getEffects() {
        MobEffectInstance effectInstance = new MobEffectInstance(EffectInit.EFFECT_SHIELD_MAX.get(), 1200 , 0);
        return Collections.singletonList(effectInstance);
    }
}
