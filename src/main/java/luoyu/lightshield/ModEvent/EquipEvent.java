package luoyu.lightshield.ModEvent;

import luoyu.lightshield.Enchantment.EnchantInit;
import luoyu.lightshield.ShieldSystem.ShieldAttribute;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
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

    // SHIELD_MAX 相关的 UUID 和事件处理
    private static final UUID SHIELD_MAX_ENCHANTMENT_UUID = UUID.fromString("e1a1b2c3-e123-4d56-7890-a1b2c3d4e5f6");

    // SHIELD_REGEN 相关的 UUID
    private static final UUID SHIELD_REGEN_ENCHANTMENT_UUID = UUID.fromString("e2a1b2c3-e123-4d56-7890-a1b2c3d4e5f6");

    @SubscribeEvent
    public static void onEquipEvent(LivingEquipmentChangeEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        int enchantmentLevel_Max = 0;
        int enchantmentLevel_Regen = 0;

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() == EquipmentSlot.Type.ARMOR) {
                ItemStack armorStack = player.getItemBySlot(slot);
                if (!armorStack.isEmpty()) {
                    enchantmentLevel_Max += EnchantmentHelper.getTagEnchantmentLevel(EnchantInit.SHIELD_MAX.get(), armorStack);
                    enchantmentLevel_Regen += EnchantmentHelper.getTagEnchantmentLevel(EnchantInit.SHIELD_REGEN.get(), armorStack);
                }
            }
        }

        handleShieldMax(player, enchantmentLevel_Max);
        handleShieldRegen(player, enchantmentLevel_Regen);
    }

    private static void handleShieldMax(Player player, int enchantmentLevel) {
        AttributeInstance shieldMaxAttribute = player.getAttribute(ShieldAttribute.SHIELD_MAX.get());
        if (shieldMaxAttribute == null) return;

        shieldMaxAttribute.removeModifier(SHIELD_MAX_ENCHANTMENT_UUID);

        if (enchantmentLevel > 0) {
            AttributeModifier modifier = equipShieldMaxModifier(enchantmentLevel, shieldMaxAttribute, SHIELD_MAX_ENCHANTMENT_UUID, "Shield Max Enchantment Bonus");
            shieldMaxAttribute.addTransientModifier(modifier);
        }
    }

    private static void handleShieldRegen(Player player, int enchantmentLevel) {
        AttributeInstance shieldRegenAttribute = player.getAttribute(ShieldAttribute.SHIELD_REGEN.get());
        if (shieldRegenAttribute == null) return;

        shieldRegenAttribute.removeModifier(SHIELD_REGEN_ENCHANTMENT_UUID);

        if (enchantmentLevel > 0) {
            AttributeModifier modifier = equipShieldRegenModifier(enchantmentLevel, shieldRegenAttribute, SHIELD_REGEN_ENCHANTMENT_UUID, "Shield Regen Enchantment Bonus");
            shieldRegenAttribute.addTransientModifier(modifier);
        }
    }

    private static @NotNull AttributeModifier equipShieldMaxModifier(int enchantmentLevel, AttributeInstance attribute, UUID uuid, String name) {
        double percentBonus = 0.5 * enchantmentLevel; // 每级+50%，装备总和附魔16，最多+400%

        return new AttributeModifier(
                uuid,
                name,
                percentBonus,
                AttributeModifier.Operation.MULTIPLY_TOTAL
        );
    }

    private static @NotNull AttributeModifier equipShieldRegenModifier(int enchantmentLevel, AttributeInstance attribute, UUID uuid, String name) {
        double percentBonus = 0.125 * enchantmentLevel; // 每级+12.5%，装备总和附魔16，最多+200%

        return new AttributeModifier(
                uuid,
                name,
                percentBonus,
                AttributeModifier.Operation.MULTIPLY_TOTAL
        );
    }
}