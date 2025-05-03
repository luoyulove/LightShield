package luoyu.lightshield.ShieldEvent;

import luoyu.lightshield.PotionEffect.EffectInit;
import luoyu.lightshield.ShieldSystem.ShieldMain;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import static luoyu.lightshield.LightShield.MODID;

@Mod(MODID)
public class ShieldDefendEvent {
    @SubscribeEvent
    public void onLivingDamage(LivingDamageEvent.Pre event) {
        LivingEntity livingEntity = event.getEntity();

        if (livingEntity instanceof Player player) {
            Double shield = ShieldMain.INSTANCE.getShieldAmount(player);
            float originalDamage = event.getOriginalDamage();

            shield = shield - originalDamage;
            float damage = 0F;
            if (shield < 0) damage = (float) -shield;

            if (shield <= 0) {
                event.setNewDamage(damage);
                return;
            }

            if (damage <= 0) {
                event.setNewDamage(0);
                ShieldMain.INSTANCE.setShieldAmount(player, Math.max(0, shield));

                if (damage > (ShieldMain.INSTANCE.getShieldMax(player) * 0.2)) {
                    player.addEffect(new MobEffectInstance(
                            (Holder<MobEffect>) EffectInit.EFFECT_SHIELD_COOLDOWN.get(), 40, 0, false, true
                    ));
                }
            }

            if (damage > 0) {
                Double cooldown = (ShieldMain.INSTANCE.getShieldMax(player) / player.getMaxHealth());
                event.setNewDamage(-damage);
                ShieldMain.INSTANCE.setShieldAmount(player, 0);
                player.addEffect(new MobEffectInstance(
                        (Holder<MobEffect>) EffectInit.EFFECT_SHIELD_COOLDOWN.get(), Math.min((int) (((cooldown * 8) + 3) * 20), 240), 0, false, true
                ));
            }
        }
    }
}