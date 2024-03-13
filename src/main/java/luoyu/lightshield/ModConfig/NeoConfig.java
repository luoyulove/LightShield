package luoyu.lightshield.ModConfig;


import net.neoforged.neoforge.common.ModConfigSpec;

public class NeoConfig implements Config{
    public class Config{
        public static ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
        //overlay config
        public static final ModConfigSpec.BooleanValue ENABLE_OVERLAY;

        static {
            BUILDER.push("overlay");
            ENABLE_OVERLAY = BUILDER.comment("Enable Shield Overlay\n是否启动护盾渲染").define("EnableShieldOverlay", true);
            BUILDER.pop();
        }
        public static ModConfigSpec CONFIG = BUILDER.build();
    }
    public boolean getConfigOverlay(){
        return Config.ENABLE_OVERLAY.get();
    }
    @Override
    public boolean enableShieldHud() {
        return true;
    }
}
