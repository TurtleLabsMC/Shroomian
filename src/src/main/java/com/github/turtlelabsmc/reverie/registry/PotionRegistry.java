package com.github.turtlelabsmc.reverie.registry;

import com.github.turtlelabsmc.reverie.Reverie;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;

public class PotionRegistry
{
    private static final Map<Identifier, Potion> POTIONS = new HashMap<>();

    public static final Potion SPORE_POTION = addToRegistry("spore_potion", new Potion(new StatusEffectInstance(StatusEffectRegistry.SPORE_EFFECT, 20 * 90)));

    public static void register()
    {
        for (Map.Entry<Identifier, Potion> entry : POTIONS.entrySet()) {
            Registry.register(Registry.POTION, entry.getKey(), entry.getValue());
        }
    }

    private static Potion addToRegistry(String registryName, Potion potion)
    {
        POTIONS.put(Reverie.modId(registryName), potion);
        return potion;
    }
}
