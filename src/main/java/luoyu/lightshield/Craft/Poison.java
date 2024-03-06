//package luoyu.lightshield.Craft;
//
//import net.minecraft.world.item.Item;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.Items;
//import net.minecraft.world.item.alchemy.Potion;
//import net.minecraft.world.item.alchemy.PotionUtils;
//import net.minecraft.world.item.crafting.Ingredient;
//import net.neoforged.neoforge.common.brewing.BrewingRecipeRegistry;
//import net.neoforged.neoforge.common.brewing.IBrewingRecipe;
//
//public class Poison implements IBrewingRecipe {
//    private final Potion input;
//    private final Item ingredient;
//    private final Potion output;
//    public Poison(Potion input, Item ingredient, Potion output) {
//        this.input = input;
//        this.ingredient = ingredient;
//        this.output = output;
//    }
//
//    @Override
//    public boolean isInput(ItemStack itemStack) {
//        return PotionUtils.getPotion(itemStack) == this.input;
//    }
//
//    @Override
//    public boolean isIngredient(ItemStack itemStack) {
//        return itemStack.getItem() == this.ingredient;
//    }
//}
