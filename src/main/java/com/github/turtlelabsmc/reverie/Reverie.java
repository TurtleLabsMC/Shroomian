package com.github.turtlelabsmc.reverie;

import com.github.turtlelabsmc.reverie.event.EventManager;
import com.github.turtlelabsmc.reverie.registry.EntityRegistry;
import com.github.turtlelabsmc.reverie.registry.PotionRegistry;
import com.github.turtlelabsmc.reverie.registry.StatusEffectRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Reverie implements ModInitializer
{
    public static final Logger LOGGER = LogManager.getLogger();
    @Override
    public void onInitialize()
    {
        LOGGER.info("Hello world, Reverie loading");
        EventManager.registerEvents();

        PotionRegistry.register();
        StatusEffectRegistry.register();
        EntityRegistry.init();
    }

    public static Identifier modId(String path)
    {
        return new Identifier("reverie", path);
    }
}
