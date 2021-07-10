package com.github.turtlelabsmc.shroomian.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ShroomianClient implements ClientModInitializer
{

    @Override
    public void onInitializeClient()
    {
        System.out.println("Reverie client loading");
        EntityModels.register();
    }
}
