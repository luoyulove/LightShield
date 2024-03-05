package luoyu.lightshield.Listener;

import luoyu.lightshield.Enchantment.EnchantInit;
import luoyu.lightshield.ShieldPayload.SyncShieldSystem;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.sound.PlaySoundEvent;
import net.neoforged.neoforge.client.event.sound.SoundEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import luoyu.lightshield.ShieldSystem.Shield;
import net.neoforged.neoforge.network.PacketDistributor;

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
                float shieldDamage = Math.min(shield.getShieldAmount(), originalDamage);

                int enchantmentLevel = 0;
                for (EquipmentSlot slot : EquipmentSlot.values()) {
                    if (slot.getType() == EquipmentSlot.Type.ARMOR) {
                        enchantmentLevel += EnchantmentHelper.getEnchantmentLevel(EnchantInit.SHIELD_DEFENSE.get(), player);
                    }
                    if (enchantmentLevel > 20){
                        enchantmentLevel = 20;
                    }
                }
                shieldDamage = shieldDamage * (1 - (0.025F * enchantmentLevel));
                shield.setShieldAmount(shield.getShieldAmount() - ((shieldDamage)* (1 - (0.025F * enchantmentLevel))));

                e.setAmount((originalDamage - shieldDamage));
            }
        }
//            var pkt = new SyncShieldSystem.ShieldData(ClientShieldAmount);
//            PacketDistributor.PLAYER.with((ServerPlayer) player).send(pkt);
    }
}
