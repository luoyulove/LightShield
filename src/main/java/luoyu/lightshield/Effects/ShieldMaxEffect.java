package luoyu.lightshield.Effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class ShieldMaxEffect extends MobEffect {
    public ShieldMaxEffect(){
        super(MobEffectCategory.BENEFICIAL, 0x0);
    }
    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier){
//        if (entity instanceof ServerPlayer) {
//            if (entity.tickCount % 60 == 0) {
//                // 获取玩家护盾
//                float shieldAmount = getPlayerShield((ServerPlayer) entity).getShieldAmount();
//                float maxShieldAmount = getPlayerShield((ServerPlayer) entity).getMaxShieldAmount();
//
//                if (shieldAmount < maxShieldAmount){
//                    float newShieldAmount = shieldAmount + amplifier;
//                    getPlayerShield((ServerPlayer) entity).setShieldAmount(newShieldAmount);
//                }
//            }
//        }
        super.applyEffectTick(entity, amplifier);
    }
}
