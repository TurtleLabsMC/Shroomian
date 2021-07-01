package com.github.turtlelabsmc.reverie.registry;

import com.github.turtlelabsmc.reverie.Reverie;
import com.github.turtlelabsmc.reverie.effects.SporeEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.registry.Registry;

public class StatusEffectRegistry
{
    public static final StatusEffect SPORE_EFFECT = register("spore_effect", new SporeEffect());

    public static StatusEffect register(String registryName, StatusEffect statusEffect)
    {
        return Registry.register(Registry.STATUS_EFFECT, Reverie.modId(registryName), statusEffect);
    }
}
