package luoyu.lightshield.Effects;

import luoyu.lightshield.ShieldSystem.ShieldAttribute;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class ShieldMaxEffect extends MobEffect {
    public ShieldMaxEffect(){
        super(MobEffectCategory.BENEFICIAL, 0xFFFF00);
        this.addAttributeModifier(ShieldAttribute.SHIELD_MAX.get(), "e2a1b2c3-e123-4d56-7890-a1b2c3d4e5f6",
                0.1D, AttributeModifier.Operation.MULTIPLY_BASE);
    }
}
