package luoyu.lightshield.ModEvent;

import luoyu.lightshield.Effects.EffectInit;
import luoyu.lightshield.Enchantment.EnchantInit;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static luoyu.lightshield.ShieldSystem.Shield.*;

@Mod.EventBusSubscriber
public class DefendAttackEvent {
    @SubscribeEvent
    public static void onDefendAttack(LivingDamageEvent event) {
        LivingEntity livingEntity = event.getEntity();

        if (livingEntity instanceof Player player) {
            double shieldAmount = getShieldAmount(player);
            double shieldMax = getShieldMax(player);
            float originalDamage = event.getAmount();

            if (shieldAmount > 0) {
                int enchantmentLevel = 0;

                for (EquipmentSlot slot : EquipmentSlot.values()) {
                    if (slot.getType() == EquipmentSlot.Type.ARMOR) {
                        ItemStack armorStack = player.getItemBySlot(slot);
                        if (!armorStack.isEmpty()) {
                            enchantmentLevel += EnchantmentHelper.getEnchantmentLevel(EnchantInit.SHIELD_DEFENSE.get(), player);
                        }
                    }
                }

                float damageReducePercent = 1 - (0.025F * enchantmentLevel);
                float reducedDamage = originalDamage * Math.min(damageReducePercent, 0.4F);

                if (player.getAbsorptionAmount() < 1) {
                    player.addEffect(new MobEffectInstance(EffectInit.SHIELD_COOLDOWN.get(), 40, 0, false, true));
                }
                double damageToShield = Math.min(shieldAmount, reducedDamage);

                setShieldAmount(player, shieldAmount - damageToShield);

                float finalDamage = (float) Math.max(reducedDamage - shieldAmount, 0);

                if (damageToShield >= shieldAmount) {
                    double shieldCooldownTime = (shieldMax / player.getMaxHealth()) * 8 + 4;  // 计算冷却时间
                    player.addEffect(new MobEffectInstance(EffectInit.SHIELD_COOLDOWN.get(), (int) Math.min(shieldCooldownTime * 20, 240), 0, false, true));
                }

                event.setAmount(finalDamage);
            }
        }
    }
}
