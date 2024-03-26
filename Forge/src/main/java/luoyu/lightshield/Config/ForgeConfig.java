package luoyu.lightshield.Config;

import luoyu.ModConfig;
import luoyu.ModConfigDefault;
import net.minecraftforge.common.ForgeConfigSpec;

import static luoyu.lightshield.Config.ForgeConfig.Config.BUILDER;

public class ForgeConfig implements ModConfig {

    public static class Config {
        public static ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
        //overlay config
        public static final ForgeConfigSpec.BooleanValue ENABLE_OVERLAY;

        static {
            BUILDER.push("ShieldHudOverlay");
            ENABLE_OVERLAY = BUILDER
                    .comment("Enable Shield Overlay\n启动护盾渲染")
                    .define("EnableShieldOverlay", ModConfigDefault.modConfigDefault.EnableOverlay());
            BUILDER.pop();
        }
        public static ForgeConfigSpec CONFIG = BUILDER.build();
    }
    @Override
    public boolean EnableOverlay(){
        return Config.ENABLE_OVERLAY.get();
    }

    @Override
    public void EnableOverlay(boolean enable) {

    }
}
