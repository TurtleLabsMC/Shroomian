package com.github.turtlelabsmc.reverie;

import com.github.turtlelabsmc.reverie.event.EventManager;
import com.github.turtlelabsmc.reverie.registry.FeatureRegistry;
import com.github.turtlelabsmc.reverie.registry.ReverieObjects;
import com.github.turtlelabsmc.reverie.registry.PotionRegistry;
import com.github.turtlelabsmc.reverie.registry.StatusEffectRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Reverie implements ModInitializer
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "reverie";
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier(MODID, MODID), () -> new ItemStack(ReverieObjects.SHROOM));

    @Override
    public void onInitialize()
    {
        LOGGER.info("Hello world, Reverie loading");
        EventManager.registerEvents();

        PotionRegistry.register();
        StatusEffectRegistry.register();
        ReverieObjects.register();
        FeatureRegistry.register();
    }

    public static Identifier modId(String path)
    {
        return new Identifier(MODID, path);
    }
}
