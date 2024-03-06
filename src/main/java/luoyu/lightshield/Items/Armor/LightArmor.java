package luoyu.lightshield.Items.Armor;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;


public class LightArmor{
    public static final ArmorMaterial LIGHTSHIELD_ARMOR = new ArmorMaterial() {
        @Override
        public String getName() {
            return "lightshield:light_armor";
        }
        @Override
        public int getDurabilityForType(ArmorItem.Type type) {
            return switch (type){
                case HELMET -> 25 * 15;
                case CHESTPLATE -> 40 * 15;
                case LEGGINGS -> 35 * 15;
                case BOOTS -> 20 * 15;
            };
        }

        @Override
        public int getDefenseForType(ArmorItem.Type type) {
            return switch (type){
                case HELMET -> 4;
                case CHESTPLATE -> 7;
                case LEGGINGS -> 6;
                case BOOTS -> 3;
            };
        }

        @Override
        public int getEnchantmentValue() {
            return 22;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ARMOR_EQUIP_DIAMOND;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(Items.DIAMOND);
        }
        @Override
        public float getToughness() {
            return 3.75F;
        }

        @Override
        public float getKnockbackResistance() {
            return 0;
        }
    };
}