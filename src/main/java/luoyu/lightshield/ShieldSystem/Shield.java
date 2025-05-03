package luoyu.lightshield.ShieldSystem;

import luoyu.lightshield.Effects.ShieldMaxEffect;
import luoyu.lightshield.Enchantment.EnchantInit;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;


public class Shield {
    public static Double getShieldAmount(Player player){
        Attribute attribute = ShieldAttribute.SHIELD_AMOUNT.get();
        AttributeInstance shieldAmount = player.getAttribute(attribute);

        return shieldAmount.getBaseValue();
    }

    public static Double getShieldMax(Player player){
        Attribute attribute = ShieldAttribute.SHIELD_MAX.get();
        AttributeInstance shieldMax = player.getAttribute(attribute);

        return shieldMax.getBaseValue();
    }

    public static Double getShieldRegen(Player player){
        Attribute attribute = ShieldAttribute.SHIELD_REGEN.get();
        AttributeInstance shieldRegen = player.getAttribute(attribute);

        return shieldRegen.getBaseValue();
    }

    public static void setShieldAmount(Player player, Double value){
        Attribute attribute = ShieldAttribute.SHIELD_AMOUNT.get();
        AttributeInstance shieldAmount = player.getAttribute(attribute);

        shieldAmount.setBaseValue(value);
    }

    public static void setShieldMax(Player player, Double value){
        Attribute attribute = ShieldAttribute.SHIELD_MAX.get();
        AttributeInstance shieldMax = player.getAttribute(attribute);

        shieldMax.setBaseValue(value);
    }

    public static void setShieldRegen(Player player, Double value){
        Attribute attribute = ShieldAttribute.SHIELD_REGEN.get();
        AttributeInstance shieldRegen = player.getAttribute(attribute);

        shieldRegen.setBaseValue(value);
    }

    public static void addShieldAmount(Player player, Double value){
        Attribute attribute = ShieldAttribute.SHIELD_AMOUNT.get();
        AttributeInstance shieldAmount = player.getAttribute(attribute);

        Attribute shieldMaxAb = ShieldAttribute.SHIELD_MAX.get();
        AttributeInstance shieldMax = player.getAttribute(shieldMaxAb);

        if (shieldAmount.getBaseValue() > shieldMax.getBaseValue()) {
            shieldAmount.setBaseValue(shieldMax.getBaseValue());
            return;
        }

        setPlayerMaxShield(player);
        shieldAmount.setBaseValue(shieldAmount.getBaseValue() + value);
    }

    public static void setPlayerMaxShield(Player player) {
        int enchantmentLevel = 0;
        int EffectLevel = 0;

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() == EquipmentSlot.Type.ARMOR) {
                ItemStack armorStack = player.getItemBySlot(slot);
                if (!armorStack.isEmpty()) {
                    enchantmentLevel += EnchantmentHelper.getTagEnchantmentLevel(EnchantInit.SHIELD_MAX.get(), armorStack);
                }
            }
        }

        for (MobEffectInstance effect : player.getActiveEffects()){
            if (effect.getEffect() instanceof ShieldMaxEffect){
                EffectLevel = effect.getAmplifier();
            }
        }

        Double Amplifier = 1 + (enchantmentLevel * 0.05) + (EffectLevel * 0.1);
        Double MaxShieldAmount = (Shield.getShieldMax(player) * Amplifier);

        setShieldMax(player, MaxShieldAmount);
    }
}