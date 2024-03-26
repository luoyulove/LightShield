package luoyu.lightshield.ShieldSystem;

import luoyu.lightshield.Effects.ShieldMaxEffect;
import luoyu.lightshield.Enchantment.EnchantInit;
import luoyu.lightshield.NetWork.NetWorkPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkDirection;

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
                shieldRegen(event.player);
            }
        }
    }
//    @SubscribeEvent
//    public static void onPlayerSpawn(PlayerEvent.PlayerRespawnEvent event){
//        if (event.getEntity() instanceof ServerPlayer player){
////            player.addEffect(new MobEffectInstance(EffectInit.EFFECT_SHIELD_REGEN.get(), 600, 5, true, false));
//            Shield.getPlayerShield(player).refreshPlayerMaxShield();
//        }
//    }
    public void setPlayerMaxShield() {
        int enchantmentLevel = 0;
        int EffectLevel = 0;
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() == EquipmentSlot.Type.ARMOR) {
                enchantmentLevel += EnchantmentHelper.getEnchantmentLevel(EnchantInit.SHIELD_MAX.get(), player);
            }
        }
        for (MobEffectInstance effect : player.getActiveEffects()){
            if (effect.getEffect() instanceof ShieldMaxEffect){
                EffectLevel = effect.getAmplifier();
            }
        }
        float newMaxShieldAmount = (4 + (enchantmentLevel * 1) + (EffectLevel * 4));
        setMaxShieldAmount(newMaxShieldAmount);
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
