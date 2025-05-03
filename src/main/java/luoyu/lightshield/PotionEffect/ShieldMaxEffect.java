package luoyu.lightshield.PotionEffect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ShieldMaxEffect extends MobEffect {
    public ShieldMaxEffect(){
        super(MobEffectCategory.BENEFICIAL, 0xFFFF00);
    }
}