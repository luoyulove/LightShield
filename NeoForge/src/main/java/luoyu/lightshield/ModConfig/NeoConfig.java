package luoyu.lightshield.ModConfig;

import net.neoforged.neoforge.common.ModConfigSpec;

public class NeoConfig implements Config{
    public class Config{
        public static ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
        //overlay config
        public static final ModConfigSpec.BooleanValue ENABLE_OVERLAY;
//        public static final ModConfigSpec.DoubleValue SHIELD_AMOUNT_CHANGE;
//
//        public static final ModConfigSpec.DoubleValue SHIELD_AMOUNT_MAX_CHANGE;

        static {
            BUILDER.push("overlay");
            ENABLE_OVERLAY = BUILDER.comment("Enable Shield Overlay\n启动护盾渲染").define("EnableShieldOverlay", true);
            BUILDER.pop();

//            BUILDER.push("Other");
//            SHIELD_AMOUNT_CHANGE = BUILDER.comment("change Shield Amount\n调整护盾量").defineInRange("ShieldAmountChange", 1.0, 0,25565);
//            SHIELD_AMOUNT_MAX_CHANGE = BUILDER.comment("change Shield Max Amount\n调整最大护盾量").defineInRange("ShieldMaxAmountChange", 1.0, 0,25565);
//            BUILDER.pop();
        }
        public static ModConfigSpec CONFIG = BUILDER.build();
    }
    @Override
    public boolean getConfigOverlay(){
        return Config.ENABLE_OVERLAY.get();
    }
    @Override
    public boolean enableShieldHud() {
        return true;
    }

//    @Override
//    public Double setShieldAmountMultiple() {
//        return 1.0;
//    }
//    @Override
//    public Double setShieldMaxAmountMultiple() {
//        return 1.0;
//    }

//    @Override
//    public Double getShieldAmountMultiple(){
//        return Config.SHIELD_AMOUNT_CHANGE.get();
//    }
//
//    @Override
//    public Double getShieldMaxAmountMultiple() {
//        return Config.SHIELD_AMOUNT_MAX_CHANGE.get();
//    }
}
