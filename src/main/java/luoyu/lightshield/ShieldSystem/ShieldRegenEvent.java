package luoyu.lightshield.ShieldSystem;

import luoyu.lightshield.Effects.ShieldRegenEffect;
import luoyu.lightshield.Enchantment.EnchantInit;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.TickEvent;

import static com.mojang.text2speech.Narrator.LOGGER;
import static luoyu.lightshield.ShieldSystem.Shield.getPlayerShield;

public class ShieldRegenEvent {
    public static float shieldRegenAmount(Player player) {
        int enchantmentLevel = 0;
        int EffectLevel = 0;
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() == EquipmentSlot.Type.ARMOR) {
                enchantmentLevel += EnchantmentHelper.getEnchantmentLevel(EnchantInit.SHIELD_REGEN.get(), player);
            }
        }
        for (MobEffectInstance effect : player.getActiveEffects()){
            if (effect.getEffect() instanceof ShieldRegenEffect){
                EffectLevel = effect.getAmplifier();
            }
        }
        return 1 + (enchantmentLevel * 0.25F) + (EffectLevel * 1F);
    }
    @SubscribeEvent
    public static void onShieldRegen(TickEvent.PlayerTickEvent event) {
        if (!event.side.isClient()) {
            if (event.phase == TickEvent.Phase.END && event.player.tickCount % 100 == 0) {
                shieldRegen(event.player);
            }
        }
    }
    public static void shieldRegen(Player player){
        Shield shield = getPlayerShield(player);
        float shieldRegen = shieldRegenAmount(player);
        float newShieldAmount = Math.min(shield.getShieldAmount() + shieldRegen, shield.getMaxShieldAmount());
        shield.setShieldAmount(newShieldAmount);

        if (shield.getShieldAmount() > shield.getMaxShieldAmount()) {
            shield.setShieldAmount(shield.getMaxShieldAmount());
        }
    }
}
