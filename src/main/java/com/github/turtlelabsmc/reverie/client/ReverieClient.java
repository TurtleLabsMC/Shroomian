package com.github.turtlelabsmc.reverie.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ReverieClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        System.out.println("Reverie client loading");
        EntityModels.register();
    }
}
