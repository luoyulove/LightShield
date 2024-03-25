package luoyu.lightshield.Effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ShieldCooldownEffect extends MobEffect {
    public ShieldCooldownEffect(){
        super(MobEffectCategory.BENEFICIAL, 0xFFFF00);
    }
}