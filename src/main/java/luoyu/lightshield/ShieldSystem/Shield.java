package luoyu.lightshield.ShieldSystem;

import luoyu.lightshield.Enchantment.EnchantInit;
import luoyu.lightshield.ShieldPayload.SyncShieldSystem;
import net.minecraft.server.level.ServerPlayer;
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

@Mod.EventBusSubscriber(modid = "lightshield", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Shield {
    private static final Map<UUID, Shield> PlayerShieldMap = new HashMap<>();
    private final Player player;
    private float maxShieldAmount;
    private float shieldAmount;

    public Shield(Player player) {
        this.player = player;
        this.shieldAmount = 0;
        this.maxShieldAmount = 10;
        this.refreshPlayerMaxShield();
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
        if (!event.side.isClient() && getPlayerShield(event.player).shieldAmount < getPlayerShield(event.player).maxShieldAmount) {
            if (event.phase == TickEvent.Phase.END && event.player.tickCount % 100 == 0) {
                Shield shield = getPlayerShield(event.player);
                float shieldRegen = shieldRegenAmount(event.player);
                float newShieldAmount = Math.min(shield.shieldAmount + shieldRegen, shield.getMaxShieldAmount());
                shield.setShieldAmount(newShieldAmount);

                // sync with the client
                var pkt = new SyncShieldSystem.ShieldData(newShieldAmount);
                PacketDistributor.PLAYER.with((ServerPlayer) event.player).send(pkt);
            }
        }
    }
    @SubscribeEvent
    public static void onShieldRefresh(TickEvent.PlayerTickEvent event){
        if (!event.side.isClient() && event.phase == TickEvent.Phase.END && event.player.tickCount % 5 == 0) {
            Shield shield = getPlayerShield(event.player);
            float newShieldAmount = shield.shieldAmount;

            if (newShieldAmount > 0) {
                var pkt = new SyncShieldSystem.ShieldData(newShieldAmount);
                PacketDistributor.PLAYER.with((ServerPlayer) event.player).send(pkt);
            }
        }
    }

    public static float shieldRegenAmount(Player player) {
        int enchantmentLevel = 0;
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() == EquipmentSlot.Type.ARMOR) {
                enchantmentLevel += EnchantmentHelper.getEnchantmentLevel(EnchantInit.SHIELD_REGEN.get(), player);
            }
        }
        return 1 + (enchantmentLevel * 0.25F);
    }
    private void refreshPlayerMaxShield() {
        int enchantmentLevel = 0;
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() == EquipmentSlot.Type.ARMOR) {
                enchantmentLevel += EnchantmentHelper.getEnchantmentLevel(EnchantInit.SHIELD_MAX.get(), player);
            }
        }
        this.maxShieldAmount = 4 + enchantmentLevel;
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
