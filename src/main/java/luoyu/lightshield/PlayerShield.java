package luoyu.lightshield;

import luoyu.lightshield.Enchantment.EnchantInit;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = "lightshield", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerShield {
    private static final Map<UUID, PlayerShield> PlayerShieldMap = new HashMap<>();
    private final Player player;
    private float maxShieldAmount;
    private float shieldAmount;

    public PlayerShield(Player player) {
        this.player = player;
        this.shieldAmount = 0;
        this.maxShieldAmount = 10;
        this.refreshPlayerMaxShield();
    }

    public static PlayerShield getPlayerShield(Player player) {
        UUID playerUUID = player.getUUID();
        if (!PlayerShieldMap.containsKey(playerUUID)) {
            PlayerShieldMap.put(playerUUID, new PlayerShield(player));
        } else {
            PlayerShieldMap.get(playerUUID).refreshPlayerMaxShield();
        }
        return PlayerShieldMap.get(playerUUID);
    }

    @SubscribeEvent
    public static void onShieldRegen(TickEvent.PlayerTickEvent event) {
        if (!event.side.isClient() && getPlayerShield(event.player).shieldAmount < getPlayerShield(event.player).maxShieldAmount) {
            if (event.phase == TickEvent.Phase.END && event.player.tickCount % 100 == 0) {
                PlayerShield playerShield = getPlayerShield(event.player);
                float shieldRegen = shieldRegenAmount(event.player);
                float newShieldAmount = Math.min(playerShield.shieldAmount + shieldRegen, playerShield.getMaxShieldAmount());
                playerShield.setShieldAmount(newShieldAmount);
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

    public  float getShieldAmount() {
        return this.shieldAmount;
    }

    public float getMaxShieldAmount() {
        return this.maxShieldAmount;
    }

    public float setShieldAmount(float shieldAmount) {
        return this.shieldAmount = shieldAmount;
    }
    public ShieldData toShieldData() {
        return new ShieldData(this.getShieldAmount());
    }

    @Mod.EventBusSubscriber(modid = "lightshield", bus = Mod.EventBusSubscriber.Bus.MOD)
    public record ShieldData(float shieldAmount) implements CustomPacketPayload {
        public static final ResourceLocation ID = new ResourceLocation(LightShield.MOD_ID, "shieldamount");

        public ShieldData(final FriendlyByteBuf buffer) {
            this(buffer.readFloat());
        }
        @Override
        public void write(final FriendlyByteBuf buffer) {
            buffer.writeFloat(this.shieldAmount);
        }

        @Override
        public ResourceLocation id() {
            return ID;
        }

        @SubscribeEvent
        public static void registerServerPayload(final RegisterPayloadHandlerEvent event) {
            final IPayloadRegistrar registrar = event.registrar(LightShield.MOD_ID);
            registrar.play(ShieldData.ID, PlayerShield.ShieldData::new, handler -> handler
                    .client(luoyu.lightshield.ShieldPayload.ClientPayloadHandler.getClient()::handleData)
                    .server(luoyu.lightshield.ShieldPayload.ServerPayloadHandler.getServer()::handleData));
        }
    }
}
