package com.github.turtlelabsmc.reverie;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class Reverie implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        System.out.println("Hello world, Reverie loading");
    }

    public static Identifier modId(String path)
    {
        return new Identifier("reverie", path);
    }
}
