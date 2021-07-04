package com.github.turtlelabsmc.reverie.client;

import com.github.turtlelabsmc.reverie.registry.ReverieBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class ReverieClient implements ClientModInitializer
{

    @Override
    public void onInitializeClient()
    {
        System.out.println("Reverie client loading");

        BlockRenderLayerMap.INSTANCE.putBlock(ReverieBlocks.LARCIA_BLOCK, RenderLayer.getCutout());
    }
}
