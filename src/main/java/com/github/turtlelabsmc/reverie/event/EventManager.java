package com.github.turtlelabsmc.reverie.event;

import com.github.turtlelabsmc.reverie.registry.StatusEffectRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class EventManager
{
    public static void registerEvents()
    {
        EntityAttackedByEntityCallback.EVENT.register((attacker, attacked) -> {
            if (attacked.hasStatusEffect(StatusEffectRegistry.SPORE_EFFECT)) {
                if (attacked.getStatusEffect(StatusEffectRegistry.SPORE_EFFECT).getAmplifier() > 0) {
                    attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 40, 1));
                } else {
                    attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 52));
                }
            }
        });
    }
}
