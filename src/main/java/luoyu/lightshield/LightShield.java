package luoyu.lightshield;

import luoyu.lightshield.ModConfig.ForgeConfig;
import luoyu.lightshield.ModConfig.ModConfigDefault;
import luoyu.lightshield.ShieldSystem.Shield;
import luoyu.lightshield.ShieldSystem.ShieldAttribute;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.mojang.text2speech.Narrator.LOGGER;
import static luoyu.lightshield.LightShield.MODID;

import static luoyu.lightshield.Effects.EffectInit.EFFECT;
import static luoyu.lightshield.Enchantment.EnchantInit.ENCHANTMENTS;

@Mod(MODID)
public class LightShield {
    public static final String MODID = "lightshield";
    public static luoyu.lightshield.ModConfig.ModConfig config = new ModConfigDefault();

    public LightShield(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        EFFECT.register(modEventBus);
        ENCHANTMENTS.register(modEventBus);

        ShieldAttribute.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        config = new ForgeConfig();
//        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Enabling LightShield");
    }
}