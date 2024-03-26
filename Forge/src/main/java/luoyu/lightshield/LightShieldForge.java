package luoyu.lightshield;

import luoyu.lightshield.NetWork.NetWorkHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.mojang.text2speech.Narrator.LOGGER;
import static luoyu.lightshield.Effects.EffectInit.EFFECT;
import static luoyu.lightshield.Enchantment.EnchantInit.ENCHANTMENTS;

import static luoyu.ModStatic.MOD_ID;

@Mod(MOD_ID)
public class LightShieldForge {
    public static final String MOD_ID = "lightshield";

    public LightShieldForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        EFFECT.register(modEventBus);
        ENCHANTMENTS.register(modEventBus);
        NetWorkHandler.NetWorkInit();

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Enabling LightShield");
    }
}