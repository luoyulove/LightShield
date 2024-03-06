package luoyu.lightshield.Items.Usability;

import luoyu.lightshield.ShieldSystem.Shield;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import static luoyu.lightshield.ShieldSystem.Shield.getPlayerShield;

public class SmallShieldScales extends Item {
    public SmallShieldScales(Properties properties){
        super(properties);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {

            Shield shield = getPlayerShield(player);
            float newShieldAmount = Math.min(shield.getShieldAmount() + 2, shield.getMaxShieldAmount());
            shield.setShieldAmount(newShieldAmount);
            player.getCooldowns().addCooldown(this, 100);
        }
        return super.use(level, player, hand);
    }
}
