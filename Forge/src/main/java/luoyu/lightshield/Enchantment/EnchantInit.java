package luoyu.lightshield.Enchantment;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static luoyu.ModStatic.MOD_ID;

public class EnchantInit {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(Registries.ENCHANTMENT, MOD_ID);

    public static final RegistryObject<Enchantment> SHIELD_REGEN = ENCHANTMENTS.register("shield_regen", ShieldRegenEnchant::new);
    public static final RegistryObject<Enchantment> SHIELD_MAX = ENCHANTMENTS.register("shield_max", ShieldMaxEnchant::new);
    public static final RegistryObject<Enchantment> SHIELD_DEFENSE = ENCHANTMENTS.register("shield_defense", ShieldDefenseEnchant::new);
}
