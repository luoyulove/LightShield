package luoyu.lightshield.Items.Potion;

import luoyu.lightshield.Effects.ShieldRegenEffect;
import luoyu.lightshield.LightShield;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class PotionInit {
    public static final DeferredRegister<Potion> POTION = DeferredRegister.create(Registries.POTION, LightShield.MOD_ID);

    private static <T extends Potion> DeferredHolder<Potion, T> register(String _id, Supplier<T> _sup) {
        return POTION.register(_id, _sup);
    }
    public static final DeferredHolder<Potion, ShieldMaxPotion> POTION_SHIELD_REGEN = register("shield_regen", ShieldMaxPotion::new);
    public static final DeferredHolder<Potion, ShieldRegenPotion> POTION_SHIELD_MAX = register("shield_max", ShieldRegenPotion::new);
}

