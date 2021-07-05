package com.github.turtlelabsmc.reverie.client;

import com.github.turtlelabsmc.reverie.registry.ReverieObjects;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class ReverieClient implements ClientModInitializer
{

    @Override
    public void onInitializeClient()
    {
        System.out.println("Reverie client loading");
        BlockRenderLayerMap.INSTANCE.putBlock(ReverieObjects.LARCIA_BLOCK, RenderLayer.getCutout());
        EntityModels.register();
    }
}
