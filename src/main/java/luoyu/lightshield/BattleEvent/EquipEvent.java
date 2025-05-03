package luoyu.lightshield.BattleEvent;

import luoyu.lightshield.Enchantment.EnchantInit;
import luoyu.lightshield.ShieldSystem.ShieldAttribute;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Mod.EventBusSubscriber
public class EquipEvent {
    private static final UUID SHIELD_MAX_ENCHANTMENT_UUID = UUID.fromString("e1a1b2c3-e123-4d56-7890-a1b2c3d4e5f6");

    @SubscribeEvent
    public static void onEquipEvent(LivingEquipmentChangeEvent event) {
        if (event.getEntity() instanceof Player player) {
            int enchantmentLevel = 0;
            
            for (EquipmentSlot slot : EquipmentSlot.values()) {
                if (slot.getType() == EquipmentSlot.Type.ARMOR) {
                    ItemStack armorStack = player.getItemBySlot(slot);
                    if (!armorStack.isEmpty()) {
                        enchantmentLevel += EnchantmentHelper.getTagEnchantmentLevel(EnchantInit.SHIELD_MAX.get(), armorStack);
                    }
                }
            }
            var shieldMaxAttribute = player.getAttribute(ShieldAttribute.SHIELD_MAX.get());
            if (shieldMaxAttribute != null) {

                shieldMaxAttribute.removeModifier(SHIELD_MAX_ENCHANTMENT_UUID);

                if (enchantmentLevel > 0) {
                    AttributeModifier modifier = getAttributeModifier(enchantmentLevel, shieldMaxAttribute);

                    shieldMaxAttribute.addTransientModifier(modifier);
                }
            }
        }
    }

    private static @NotNull AttributeModifier getAttributeModifier(int enchantmentLevel, AttributeInstance shieldMaxAttribute) {
        double percentBonus = 0.10 * enchantmentLevel;
        double baseValue = shieldMaxAttribute.getBaseValue();
        double amount = baseValue * percentBonus;

        AttributeModifier modifier = new AttributeModifier(
                SHIELD_MAX_ENCHANTMENT_UUID,
                "Shield Max Enchantment Bonus",
                amount,
                AttributeModifier.Operation.MULTIPLY_BASE
        );
        return modifier;
    }
}