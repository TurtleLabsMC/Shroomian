package com.github.turtlelabsmc.reverie.registry;

import com.github.turtlelabsmc.reverie.Reverie;
import com.github.turtlelabsmc.reverie.entity.ShroomianEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;

public class EntityRegistry
{

    public static final EntityType<ShroomianEntity> SHROOMIAN = Registry.register(Registry.ENTITY_TYPE, new Identifier("reverie", "shroomian"), FabricEntityTypeBuilder.createLiving().entityFactory(ShroomianEntity::new).trackRangeChunks(4).dimensions(EntityDimensions.fixed(3f, 3f)).build());

    public static void init() {

        FabricDefaultAttributeRegistry.register(SHROOMIAN, ShroomianEntity.createShroomianAttributes());
    }
}
