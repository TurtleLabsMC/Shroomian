package com.github.turtlelabsmc.reverie;

import com.github.turtlelabsmc.reverie.entity.render.ShroomianEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)

public class ReverieClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Entities
        EntityRendererRegistry.INSTANCE.register(Reverie.SHROOMIAN,
                (entityRenderDispatcher, context) -> new ShroomianEntityRenderer(entityRenderDispatcher));
    }
}