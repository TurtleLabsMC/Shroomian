package com.github.turtlelabsmc.reverie;

import com.github.turtlelabsmc.reverie.entity.ShroomianEntity;
import com.github.turtlelabsmc.reverie.event.EventManager;
import com.github.turtlelabsmc.reverie.registry.EntityRegistry;
import com.github.turtlelabsmc.reverie.registry.PotionRegistry;
import com.github.turtlelabsmc.reverie.registry.StatusEffectRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Reverie implements ModInitializer
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final EntityType<ShroomianEntity> SHROOMIAN = Registry.register(Registry.ENTITY_TYPE, new Identifier("reverie", "shroomian"), FabricEntityTypeBuilder.createLiving().entityFactory(ShroomianEntity::new).trackRangeChunks(4).dimensions(EntityDimensions.fixed(3f, 3f)).build());

    @Override
    public void onInitialize()
    {
        LOGGER.info("Hello world, Reverie loading");
        EventManager.registerEvents();

        PotionRegistry.register();
        StatusEffectRegistry.register();
        EntityRegistry.init();
        FabricDefaultAttributeRegistry.register(SHROOMIAN, ShroomianEntity.createShroomianAttributes());
    }

    public static Identifier modId(String path)
    {
        return new Identifier("reverie", path);
    }
}
