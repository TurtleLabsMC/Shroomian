package com.github.turtlelabsmc.shroomian.client;


import com.github.turtlelabsmc.shroomian.entity.render.ShroomianEntityRenderer;
import com.github.turtlelabsmc.shroomian.entity.render.SporelingEntityRenderer;
import com.github.turtlelabsmc.shroomian.registry.EntityRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class EntityModels
{
    public static void register()
    {
        EntityRendererRegistry.INSTANCE.register(EntityRegistry.SHROOMIAN, ShroomianEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityRegistry.SPORELING, SporelingEntityRenderer::new);
    }
}
