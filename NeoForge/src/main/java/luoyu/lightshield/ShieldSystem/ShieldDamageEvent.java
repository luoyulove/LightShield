package luoyu.lightshield.ShieldSystem;

import luoyu.lightshield.Effects.EffectInit;
import luoyu.lightshield.Enchantment.EnchantInit;
import luoyu.lightshield.NetWork.SyncShieldAmount;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import static com.mojang.text2speech.Narrator.LOGGER;
@Mod("lightshield")
public class ShieldDamageEvent {
    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent e) {
        LivingEntity livingEntity = e.getEntity();

        if (livingEntity instanceof Player player) {
            Shield shield = Shield.getPlayerShield(player);
            float originalDamage = e.getAmount();

            if (shield.getShieldAmount() > 0) {
                int enchantmentLevel = 0;
                for (EquipmentSlot slot : EquipmentSlot.values()) {
                    if (slot.getType() == EquipmentSlot.Type.ARMOR) {
                        enchantmentLevel += EnchantmentHelper.getEnchantmentLevel(EnchantInit.SHIELD_DEFENSE.get(), player);
                    }
                    enchantmentLevel = Math.min(enchantmentLevel, 20);
                }
                float EnchantReduce = 1 - (0.025F * enchantmentLevel);
                originalDamage = originalDamage * EnchantReduce;

                if (e.getEntity().getAbsorptionAmount() == 0) {
                    if (originalDamage > (shield.getMaxShieldAmount() * 0.2)) {
                        player.addEffect(new MobEffectInstance((Holder<MobEffect>) EffectInit.EFFECT_SHIELD_COOLDOWN.get(), 40, 0, false, true));
                    }
                }

                shield.setShieldAmount(shield.getShieldAmount() - originalDamage);

                float finalDamage = 0;
                if (shield.getShieldAmount() - originalDamage <= 0){
                    Double ShieldCooldown = (double) (shield.getMaxShieldAmount() / player.getMaxHealth());

                    finalDamage = -(shield.getShieldAmount());
                    shield.setShieldAmount(0);
                    player.addEffect(new MobEffectInstance((Holder<MobEffect>) EffectInit.EFFECT_SHIELD_COOLDOWN.get(), Math.min((int) (((ShieldCooldown * 8) + 3) * 20), 240), 0, false, true));
                }
                e.setAmount(finalDamage);

                var pkt = new SyncShieldAmount.shieldAmountData(shield.getShieldAmount());
                PacketDistributor.sendToPlayer((ServerPlayer) player,pkt);
//                LOGGER.info("调试：" + "当前护盾：" + shield.getShieldAmount());
//                LOGGER.info("调试：" + "护盾上限：" + shield.getMaxShieldAmount());
//                LOGGER.info("调试：" + "护盾吸收："  + shieldAbsorbedDamage + "(减免"+(originalDamage - ReduceDamage)+")");
//                LOGGER.info("调试：" + "最终伤害：" + finalDamage);
            }
        }
    }
}