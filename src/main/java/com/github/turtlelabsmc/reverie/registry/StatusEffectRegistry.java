package com.github.turtlelabsmc.reverie.registry;

import com.github.turtlelabsmc.reverie.Reverie;
import com.github.turtlelabsmc.reverie.effects.SporeStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;

public class StatusEffectRegistry
{
    private static final Map<Identifier, StatusEffect> STATUS_EFFECTS = new HashMap<>();

    public static final StatusEffect SPORE_EFFECT = addToRegistry("spore_effect", new SporeStatusEffect());

    public static void register()
    {
        for (Map.Entry<Identifier, StatusEffect> entry : STATUS_EFFECTS.entrySet()) {
            Registry.register(Registry.STATUS_EFFECT, entry.getKey(), entry.getValue());
        }
    }

    private static StatusEffect addToRegistry(String registryName, StatusEffect statusEffect)
    {
        STATUS_EFFECTS.put(Reverie.modId(registryName), statusEffect);
        return statusEffect;
    }
}
