package com.github.turtlelabsmc.reverie.client;

import net.fabricmc.api.ClientModInitializer;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class ReverieClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        System.out.println("Reverie client loading");
    }
}
