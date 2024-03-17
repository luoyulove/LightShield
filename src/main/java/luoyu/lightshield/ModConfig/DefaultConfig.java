package luoyu.lightshield.ModConfig;

public class DefaultConfig implements Config{
    @Override
    public boolean getConfigOverlay() {
        return true;
    }
    @Override
    public boolean enableShieldHud() {
        return true;
    }
}


//    @Override
//    public Double setShieldMaxAmountMultiple() {
//        return 1.0;
//    }
//
//
//    @Override
//    public Double setShieldAmountMultiple() {
//        return 1.0;
//    }


//    @Override
//    public Double getShieldAmountMultiple() {
//        return NeoConfig.Config.SHIELD_AMOUNT_CHANGE.get();
//    }
//    @Override
//    public Double getShieldMaxAmountMultiple() {
//        return NeoConfig.Config.SHIELD_AMOUNT_MAX_CHANGE.get();
//    }
