package luoyu.lightshield;

import luoyu.lightshield.ShieldSystem.Shield;
import net.minecraft.world.entity.player.Player;

public class Api {
    public static void setShieldAmount(Player player, float amount) {

    }

    public static float getShieldAmount(Player player) {
        return Shield.getShieldAmount(player).floatValue();
    }

    public static float getMaxShieldAmount(Player player) {
        return Shield.getShieldMax(player).floatValue();
    }
}
