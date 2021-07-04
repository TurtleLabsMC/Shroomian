package com.github.turtlelabsmc.reverie;

import com.github.turtlelabsmc.reverie.registry.ReverieBlocks;
import com.github.turtlelabsmc.reverie.registry.ReverieItems;
import net.fabricmc.api.ModInitializer;

public class Reverie implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        System.out.println("Hello world, Reverie loading");

        ReverieBlocks.init();
        ReverieItems.init();
    }
}
