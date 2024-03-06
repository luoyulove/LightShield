package luoyu.lightshield.Effects;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

import static luoyu.lightshield.ShieldSystem.Shield.getPlayerShield;

public class ShieldRegenEffect extends MobEffect {
    public ShieldRegenEffect(){
        super(MobEffectCategory.BENEFICIAL, 0x0000FF);
    }
}
