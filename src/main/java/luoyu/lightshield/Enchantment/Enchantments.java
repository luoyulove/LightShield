package luoyu.lightshield.Enchantment;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;

import static luoyu.lightshield.LightShield.MODID;

public class Enchantments {
    public static final ResourceKey<Enchantment> SHIELD_MAX = key("shield_max");
    public static final ResourceKey<Enchantment> SHIELD_REGEN = key("shield_regen");
    public static final ResourceKey<Enchantment> SHIELD_DEFENSE = key("shield_defense");

    public static <DamageType> void bootstrap(BootstrapContext<Enchantment> context){
        HolderGetter<Enchantment> holderGetter_Enchant = context.lookup(Registries.ENCHANTMENT);
        HolderGetter<Item> holderGetter_Item = context.lookup(Registries.ITEM);

        register(
                context,
                SHIELD_MAX,
                Enchantment.enchantment(
                        Enchantment.definition(
                                holderGetter_Item.getOrThrow(ItemTags.EQUIPPABLE_ENCHANTABLE),
                                2,
                                2,
                                Enchantment.constantCost(24),
                                Enchantment.constantCost(12),
                                12,
                                EquipmentSlotGroup.ARMOR
                        )
                )
        );

        register(
                context,
                SHIELD_REGEN,
                Enchantment.enchantment(
                        Enchantment.definition(
                                holderGetter_Item.getOrThrow(ItemTags.EQUIPPABLE_ENCHANTABLE),
                                2,
                                4,
                                Enchantment.constantCost(16),
                                Enchantment.constantCost(8),
                                8,
                                EquipmentSlotGroup.ARMOR
                        )
                )
        );

        register(
                context,
                SHIELD_DEFENSE,
                Enchantment.enchantment(
                        Enchantment.definition(
                                holderGetter_Item.getOrThrow(ItemTags.EQUIPPABLE_ENCHANTABLE),
                                2,
                                4,
                                Enchantment.constantCost(16),
                                Enchantment.constantCost(8),
                                8,
                                EquipmentSlotGroup.ARMOR
                        )
                )
        );
    }

    // 注册附魔的方法
    private static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        context.register(key, builder.build(key.location()));
    }

    // 创建附魔资源键的方法
    private static ResourceKey<Enchantment> key(String name) {
        return ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(MODID, name));
    }
}
