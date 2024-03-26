package luoyu;

import org.lwjgl.system.SharedLibrary;

public class ModConfigDefault implements ModConfig{
    public static final ModConfigDefault modConfigDefault = new ModConfigDefault();
    @Override
    public boolean EnableOverlay(){
        return true;
    }
    @Override
    public void EnableOverlay(boolean enable) {}
}
