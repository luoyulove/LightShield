package luoyu.lightshield.ShieldSystem;

import luoyu.lightshield.NetWork.SyncShieldSystem;
import luoyu.lightshield.Effects.ShieldMaxEffect;
import luoyu.lightshield.Enchantment.EnchantInit;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static luoyu.lightshield.ShieldSystem.ShieldRegenEvent.shieldRegen;

@Mod.EventBusSubscriber(modid = "lightshield", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Shield {
    private static final Map<UUID, Shield> PlayerShieldMap = new HashMap<>();
    private final Player player;
    private float maxShieldAmount;
    private float shieldAmount;

    public Shield(Player player) {
        this.player = player;
        this.shieldAmount = 0;
        this.maxShieldAmount = refreshPlayerMaxShield();
    }

    public static Shield getPlayerShield(Player player) {
        UUID playerUUID = player.getUUID();
        if (!PlayerShieldMap.containsKey(playerUUID)) {
            PlayerShieldMap.put(playerUUID, new Shield(player));
        } else {
            PlayerShieldMap.get(playerUUID).refreshPlayerMaxShield();
        }
        return PlayerShieldMap.get(playerUUID);
    }
    @SubscribeEvent
    public static void onShieldRegen(TickEvent.PlayerTickEvent event) {
        if (!event.side.isClient()) {
            if (event.phase == TickEvent.Phase.END && event.player.tickCount % 100 == 0) {
                shieldRegen(event.player);
            }
        }
    }
    @SubscribeEvent
    public static void onShieldRefresh(TickEvent.PlayerTickEvent event){
        if (!event.side.isClient() && event.phase == TickEvent.Phase.END && event.player.tickCount % 10 == 0) {
            Shield shield = getPlayerShield(event.player);
            Shield.getPlayerShield(event.player).refreshPlayerMaxShield();
            float newShieldAmount = shield.getShieldAmount();

            if (newShieldAmount > 0) {
                var pkt = new SyncShieldSystem.ShieldData(newShieldAmount);
                PacketDistributor.PLAYER.with((ServerPlayer) event.player).send(pkt);
            }
            if (newShieldAmount < 0) {
                var pkt = new SyncShieldSystem.ShieldData(0.5F);
                PacketDistributor.PLAYER.with((ServerPlayer) event.player).send(pkt);
            }
        }
    }
    private float refreshPlayerMaxShield() {
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
        return maxShieldAmount = 4 + (enchantmentLevel * 2) + (EffectLevel * 8F);
    }

    public float getShieldAmount() {
        return this.shieldAmount;
    }

    public float getMaxShieldAmount() {
        return this.maxShieldAmount;
    }

    public float setShieldAmount(float shieldAmount) {
        return this.shieldAmount = shieldAmount;
    }
}
