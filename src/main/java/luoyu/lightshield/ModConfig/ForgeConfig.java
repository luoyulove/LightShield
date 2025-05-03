package luoyu.lightshield.ModConfig;

import net.minecraftforge.common.ForgeConfigSpec;

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
