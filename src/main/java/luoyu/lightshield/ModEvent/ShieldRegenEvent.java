package luoyu.lightshield.ModEvent;

import luoyu.lightshield.Effects.ShieldCooldownEffect;
import luoyu.lightshield.Effects.ShieldRegenEffect;
import luoyu.lightshield.Enchantment.EnchantInit;
import luoyu.lightshield.ShieldSystem.Shield;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static luoyu.lightshield.ShieldSystem.Shield.*;
@Mod.EventBusSubscriber
public class ShieldRegenEvent {
    @SubscribeEvent
    public static void onShieldRegen(TickEvent.PlayerTickEvent event) {
        if (!event.side.isClient()) {
            if (event.phase == TickEvent.Phase.END && event.player.tickCount % 20 == 0) {
                Player player = event.player;

                int enchantmentLevel = 0;
                int EffectLevel = 0;

                for (EquipmentSlot slot : EquipmentSlot.values()) {
                    if (slot.getType() == EquipmentSlot.Type.ARMOR) {
                        ItemStack armorStack = player.getItemBySlot(slot);
                        if (!armorStack.isEmpty()) {
                            enchantmentLevel += EnchantmentHelper.getEnchantmentLevel(EnchantInit.SHIELD_REGEN.get(), player);
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
                        return;
                    }
                }
                Double Amplifier = 1 + (enchantmentLevel * 0.05) + (EffectLevel * 0.1);
                Double regenAmount = (Shield.getShieldRegen(player) * Amplifier);

                addShieldAmount(event.player, getShieldRegen(event.player) + regenAmount);
            }
        }
    }
}
