package luoyu.lightshield.ShieldSystem;

import luoyu.lightshield.Effects.ShieldMaxEffect;
import luoyu.lightshield.Enchantment.EnchantInit;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static luoyu.lightshield.NetWork.NetWorkHandler.CHANNEL;
import static luoyu.lightshield.ShieldSystem.ShieldRegenEvent.shieldRegen;

@Mod.EventBusSubscriber
public class Shield {
    private static final Map<UUID, Shield> PlayerShieldMap = new HashMap<>();
    private final Player player;
    private float maxShieldAmount;
    private float shieldAmount;

    public Shield(Player player) {
        this.player = player;
        this.shieldAmount = 0;
        this.setPlayerMaxShield();
    }

    public static Shield getPlayerShield(Player player) {
        UUID playerUUID = player.getUUID();
        if (!PlayerShieldMap.containsKey(playerUUID)) {
            PlayerShieldMap.put(playerUUID, new Shield(player));
        } else {
            PlayerShieldMap.get(playerUUID).setPlayerMaxShield();
        }
        return PlayerShieldMap.get(playerUUID);
    }
    @SubscribeEvent
    public static void onShieldRegen(TickEvent.PlayerTickEvent event) {
        if (!event.side.isClient()) {
            if (event.phase == TickEvent.Phase.END && event.player.tickCount % 20 == 0) {
                Shield.getPlayerShield(event.player).setPlayerMaxShield();
                shieldRegen(event.player);
            }
        }
    }
    public void setPlayerMaxShield() {
        int enchantmentLevel = 0;
        int EffectLevel = 0;

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() == EquipmentSlot.Type.ARMOR) {
                ItemStack armorStack = player.getItemBySlot(slot);
                if (!armorStack.isEmpty()) {
//                    enchantmentLevel += EnchantmentHelper.getEnchantmentLevel(EnchantInit.SHIELD_MAX.get(), player);
                    enchantmentLevel += EnchantmentHelper.getTagEnchantmentLevel(EnchantInit.SHIELD_DEFENSE.get(), armorStack);
                }
            }
        }

        for (MobEffectInstance effect : player.getActiveEffects()){
            if (effect.getEffect() instanceof ShieldMaxEffect){
                EffectLevel = effect.getAmplifier();
            }
        }
        float MaxShieldAmount = 4 + (enchantmentLevel * 1) + (EffectLevel * 4);
        setMaxShieldAmount(MaxShieldAmount);
//
//        float ShieldAmount = getShieldAmount();
//
//        String ShieldAmountValue = Float.toString(ShieldAmount);
//
//        Component message = Component.nullToEmpty(ShieldAmountValue);
//        player.sendSystemMessage(message);
    }

    public float getShieldAmount() {
        return this.shieldAmount;
    }

    public float getMaxShieldAmount() {
        return this.maxShieldAmount;
    }

    public  float setShieldAmount(float shieldAmount) {
        return this.shieldAmount = shieldAmount;
    }
    public float setMaxShieldAmount(float maxShieldAmount) {
        return this.maxShieldAmount = maxShieldAmount;
    }
}