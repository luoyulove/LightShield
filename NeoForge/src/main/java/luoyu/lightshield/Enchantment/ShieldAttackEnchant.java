package luoyu.lightshield.Enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ShieldAttackEnchant extends Enchantment{
    public ShieldAttackEnchant() {
        super(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.ARMOR, new EquipmentSlot[]{
                EquipmentSlot.MAINHAND
        });
    }
//    @SubscribeEvent
//    public void AttackToShield(LivingDamageEvent event) {
//        if (event.getEntity() instanceof Player player && Shield.getPlayerShield(player).getShieldAmount() > 0) {
//            ItemStack heldItem = player.getMainHandItem();
//            int attackAddition = 2 * EnchantmentHelper.getEnchantmentLevel(EnchantInit.SHIELD_ATTACK.get(), player);
//            event.setAmount(event.getAmount() + attackAddition);
//        }
//    }
    @Override
    public boolean canEnchant(ItemStack itemStack){
        return itemStack.getItem() instanceof SwordItem || itemStack.getItem() instanceof AxeItem ||super.canEnchant(itemStack);
    }
    @Override
    public int getMinCost(int level){
        return level * 10;
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
        return 4;
    }
}
