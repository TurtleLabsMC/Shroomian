package com.github.turtlelabsmc.shroomian.registry;

import com.github.turtlelabsmc.shroomian.Shroomian;
import com.github.turtlelabsmc.shroomian.entity.ShroomianEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.util.registry.Registry;

public class EntityRegistry
{
    public static final EntityType<ShroomianEntity> SHROOMIAN = Registry.register(
            Registry.ENTITY_TYPE,
            Shroomian.modId("shroomian"),
            FabricEntityTypeBuilder.createLiving().entityFactory(ShroomianEntity::new).trackRangeChunks(4).dimensions(EntityDimensions.fixed(0.45f, 0.5f)).build()
    );

    public static void init()
    {
        FabricDefaultAttributeRegistry.register(SHROOMIAN, ShroomianEntity.createShroomianAttributes());
    }
}
