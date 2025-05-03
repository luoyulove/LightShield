package luoyu.lightshield.ShieldSystem;

import luoyu.lightshield.Effects.ShieldCooldownEffect;
import luoyu.lightshield.Effects.ShieldRegenEffect;
import luoyu.lightshield.Enchantment.EnchantInit;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class ShieldRegenEvent {
    public static float shieldRegenAmount(Player player) {
        int enchantmentLevel = 0;
        int EffectLevel = 0;

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() == EquipmentSlot.Type.ARMOR) {
                ItemStack armorStack = player.getItemBySlot(slot);
                if (!armorStack.isEmpty()) {
                    enchantmentLevel += EnchantmentHelper.getEnchantmentLevel(EnchantInit.SHIELD_REGEN.get(), player);
                    enchantmentLevel += EnchantmentHelper.getTagEnchantmentLevel(EnchantInit.SHIELD_DEFENSE.get(), armorStack);
                }
            }
        }

        for (MobEffectInstance effect : player.getActiveEffects()){
            if (effect.getEffect() instanceof ShieldRegenEffect){
                EffectLevel = effect.getAmplifier();
            }
        }
        for (MobEffectInstance effect : player.getActiveEffects()){
            if (effect.getEffect() instanceof ShieldCooldownEffect){
                return 0;
            }
        }
        return (float) (Shield.getShieldRegen(player) + ((enchantmentLevel * 0.05F) + (EffectLevel * 0.1F)));
    }
    public static void shieldRegen(Player player){
        Double shieldAmount = Shield.getShieldAmount(player);
        Double shieldMax = Shield.getShieldMax(player);
        float newShieldAmount = (float) Math.min(shieldAmount + shieldRegenAmount(player), shieldMax);
        Shield.setShieldAmount(player, (double) newShieldAmount);

        if (shieldAmount > shieldMax) {
            Shield.setShieldAmount(player, shieldMax);
        }
    }
}
