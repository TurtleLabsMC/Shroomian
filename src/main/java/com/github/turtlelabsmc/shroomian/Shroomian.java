package com.github.turtlelabsmc.shroomian;

import com.github.turtlelabsmc.shroomian.event.EventManager;
import com.github.turtlelabsmc.shroomian.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Shroomian implements ModInitializer
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "shroomian";
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier(MODID, MODID), () -> new ItemStack(ShroomianObjects.SHROOM));

    @Override
    public void onInitialize()
    {
        LOGGER.info("Hello world, Shroomian loading");
        EventManager.registerEvents();

        PotionRegistry.register();
        StatusEffectRegistry.register();
        ShroomianObjects.register();
        FeatureRegistry.register();
        EntityRegistry.init();
    }

    public static Identifier modId(String path)
    {
        return new Identifier(MODID, path);
    }
}
