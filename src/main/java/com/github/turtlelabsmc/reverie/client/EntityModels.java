package com.github.turtlelabsmc.reverie.client;


import com.github.turtlelabsmc.reverie.entity.render.ShroomianEntityRenderer;
import com.github.turtlelabsmc.reverie.registry.EntityRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class EntityModels
{
    public static void register()
    {
        EntityRendererRegistry.INSTANCE.register(EntityRegistry.SHROOMIAN, ShroomianEntityRenderer::new);
    }
}
