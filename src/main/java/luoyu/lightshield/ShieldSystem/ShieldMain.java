package luoyu.lightshield.ShieldSystem;

import luoyu.lightshield.Attributes.LightShieldAttribute;
import luoyu.lightshield.Enchantment.EnchantmentUtils;
import luoyu.lightshield.Enchantment.Enchantments;
import luoyu.lightshield.PotionEffect.ShieldCooldownEffect;
import luoyu.lightshield.PotionEffect.ShieldRegenEffect;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import static luoyu.lightshield.Enchantment.Enchantments.SHIELD_REGEN;

public class ShieldMain {
    public static final ShieldMain INSTANCE = new ShieldMain();
    private ShieldMain() {}
    public double getShieldAmount(Player player) {
        AttributeInstance instance = player.getAttribute(LightShieldAttribute.shield_Amount);
        return instance != null ? instance.getValue() : 0.0;
    }
    public double getShieldMax(Player player) {
        AttributeInstance instance = player.getAttribute(LightShieldAttribute.shield_max);
        return instance != null ? instance.getValue() : 0.0;
    }
    public double getShieldRegen(Player player) {
        AttributeInstance instance = player.getAttribute(LightShieldAttribute.shield_regen);
        return instance != null ? instance.getValue() : 0.0;
    }
    public void setShieldAmount(Player player, double amount) {
        AttributeInstance instance = player.getAttribute(LightShieldAttribute.shield_Amount);
        if (instance != null) {
            instance.setBaseValue(amount);
        }
    }
    public void addShieldAmount(Player player, double amount) {
        double shieldAmount = getShieldAmount(player);
        double shieldMax = getShieldMax(player);
        double value = Math.min(shieldAmount + amount, shieldMax);
        setShieldAmount(player, value);
    }

    public static float shieldRegenAmount(Player player) {
        int enchantmentLevel = 0;
        int EffectLevel = 0;

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR) {
                ItemStack stack = player.getItemBySlot(slot);
                if (!stack.isEmpty()) {
                    enchantmentLevel += EnchantmentUtils.getEnchantmentLevel(SHIELD_REGEN, stack);
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
        return (0.4F + ((enchantmentLevel * 0.11F) + (EffectLevel * 0.2F)));
    }
}