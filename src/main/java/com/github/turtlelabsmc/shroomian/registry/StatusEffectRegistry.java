package com.github.turtlelabsmc.shroomian.registry;

import com.github.turtlelabsmc.shroomian.Shroomian;
import com.github.turtlelabsmc.shroomian.effects.SporeStatusEffect;
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
        STATUS_EFFECTS.put(Shroomian.modId(registryName), statusEffect);
        return statusEffect;
    }
}
