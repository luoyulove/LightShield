package luoyu.lightshield.Enchantment;

import luoyu.lightshield.LightShield;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class EnchantInit {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(Registries.ENCHANTMENT, LightShield.MOD_ID);
    private static <T extends Enchantment> DeferredHolder<Enchantment, T> register(String _id, Supplier<T> _sup) {
        return ENCHANTMENTS.register(_id, _sup);
    }
    public static final DeferredHolder<Enchantment, ShieldRegenEnchant> SHIELD_REGEN = register("shield_regen", ShieldRegenEnchant::new);
    public static final DeferredHolder<Enchantment, ShieldMaxEnchant> SHIELD_MAX = register("max_shield", ShieldMaxEnchant::new);
    public static final DeferredHolder<Enchantment, ShieldDefenseEnchant> SHIELD_DEFENSE = register("shield_defense", ShieldDefenseEnchant::new);
//    public static final DeferredHolder<Enchantment, ShieldAttackEnchant> SHIELD_ATTACK = register("shield_attack", ShieldAttackEnchant::new);
}
