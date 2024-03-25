package com.example;

import com.example.config.ModConfigFabric;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModFabric implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger(ModStatic.MOD_NAME);

    @Override
    public void onInitialize() {
        ModStatic.modConfig = new ModConfigFabric();
        LOGGER.info("Enabling " + ModStatic.MOD_NAME);
    }
}
