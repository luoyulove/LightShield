package luoyu.lightshield.Listener;

import luoyu.lightshield.Enchantment.EnchantInit;
import luoyu.lightshield.ShieldSystem.SyncShieldSystem;
import luoyu.lightshield.ShieldSystem.Shield;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import static com.mojang.text2speech.Narrator.LOGGER;

@Mod.EventBusSubscriber(modid = "lightshield")
public class ShieldEvent {
    //    private static float ClientShieldAmount;
//    public static void setShieldAmount(float shieldAmount){
//        ClientShieldAmount = shieldAmount;
//    }
    // 受伤计算
    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent e) {
        LivingEntity livingEntity = e.getEntity();

        if (livingEntity instanceof Player) {
            Player player = (Player) livingEntity;
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
                float ReduceDamage = originalDamage * EnchantReduce;

                float shieldAbsorbedDamage = Math.min(ReduceDamage, shield.getShieldAmount());

                float newShieldAmount = shield.setShieldAmount(shield.getShieldAmount() - shieldAbsorbedDamage);
                float finalDamage = (Math.max(0, ReduceDamage - newShieldAmount));
                e.setAmount(finalDamage);

                var pkt = new SyncShieldSystem.ShieldData(newShieldAmount);
                PacketDistributor.PLAYER.with((ServerPlayer) player).send(pkt);

//                LOGGER.info("调试：" + "护盾当前：" + shield.getShieldAmount());
//                LOGGER.info("调试：" + "护盾上限：" + shield.getMaxShieldAmount());
//                LOGGER.info("调试：" + "原伤害："  + originalDamage + " 对护盾伤害：" + ReduceDamage);
//                LOGGER.info("调试：" + "最终伤害：" + finalDamage);
            }
        }
    }
}