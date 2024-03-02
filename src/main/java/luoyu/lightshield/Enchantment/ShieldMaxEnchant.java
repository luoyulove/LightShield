package luoyu.lightshield.Enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ShieldMaxEnchant extends Enchantment {
    public ShieldMaxEnchant() {
        super(Rarity.VERY_RARE, EnchantmentCategory.ARMOR, new EquipmentSlot[]{
                EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET
        });
    }
    @Override
    public boolean canEnchant(ItemStack itemStack){
        return itemStack.getItem() instanceof ArmorItem || super.canEnchant(itemStack);
    }
    @Override
    public int getMinCost(int level){
        return level * 16;
    }
    @Override
    public int getMaxCost(int level){
        return level * 20;
    }
    @Override
    public int getMinLevel(){
        return 1;
    }
    @Override
    public int getMaxLevel(){
        return 2;
    }
}
