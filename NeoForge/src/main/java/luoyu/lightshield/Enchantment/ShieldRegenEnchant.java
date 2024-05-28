package luoyu.lightshield.Enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.jetbrains.annotations.Contract;


public class ShieldRegenEnchant extends Enchantment{
    public ShieldRegenEnchant() {
        super(new EnchantmentDefinition());
    }
    @Override
    public boolean canEnchant(ItemStack itemStack){
        return itemStack.getItem() instanceof ArmorItem || super.canEnchant(itemStack);
    }
    @Contract
    public final int getMinCost(int level){
        return level * 12;
    }
    @Override
    public int getMaxCost(int level){
        return this.getMinCost(level) + 16;
    }
    @Override
    public int getMinLevel(){
        return 1;
    }
    @Override
    public int getMaxLevel(){
        return 3;
    }
}