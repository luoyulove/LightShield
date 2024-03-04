package luoyu.lightshield.Listener;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.sound.PlaySoundEvent;
import net.neoforged.neoforge.client.event.sound.SoundEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import luoyu.lightshield.ShieldSystem.Shield;
@Mod.EventBusSubscriber(modid = "lightshield")
public class ShieldEvent {
    // 受伤计算
    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent e) {
        LivingEntity livingEntity = e.getEntity();

        if (livingEntity instanceof Player) {
            Player player = (Player) livingEntity;
            Shield shield = Shield.getPlayerShield(player);
            float originalDamage = e.getAmount();

            if (shield.getShieldAmount() > 0) {
                float shieldDamage = Math.min(shield.getShieldAmount(), originalDamage);

                shield.setShieldAmount(shield.getShieldAmount() - shieldDamage);

                e.setAmount(originalDamage - shieldDamage);
            }
        }
    }
}
