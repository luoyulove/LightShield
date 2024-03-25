package luoyu.lightshield.Effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class ShieldMaxEffect extends MobEffect {
    public ShieldMaxEffect(){
        super(MobEffectCategory.BENEFICIAL, 0xFFFF00);
    }
}
