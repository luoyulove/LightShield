package luoyu.lightshield.Enchantment;

import luoyu.lightshield.PlayerShield;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;



public class LightEnchantment extends Enchantment implements PlayerShield.ShieldRegenAmount{
    public static final String enchant_name = "shield_regen";
    public LightEnchantment() {
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
        return level * 5;
    }
    @Override
    public int getMaxCost(int level){
        return level * 10 + 5;
    }
    @Override
    public int getMinLevel(){
        return 1;
    }
    @Override
    public int getMaxLevel(){
        return 4;
    }
    @Override
    public String getDescriptionId(){
        return enchant_name;
    }
}