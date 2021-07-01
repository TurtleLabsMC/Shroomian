package com.github.turtlelabsmc.reverie;

import com.github.turtlelabsmc.reverie.effects.SporeEffect;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Reverie implements ModInitializer
{

    public static final StatusEffect SPORE_EFFECT = new SporeEffect();

    @Override
    public void onInitialize()
    {
        System.out.println("Hello world, Reverie loading");

        Registry.register(Registry.STATUS_EFFECT, new Identifier("reverie", "spore_effect"), SPORE_EFFECT);
    }
}
