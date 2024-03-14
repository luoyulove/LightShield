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
