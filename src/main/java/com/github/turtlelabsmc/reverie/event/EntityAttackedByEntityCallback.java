package com.github.turtlelabsmc.reverie.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public interface EntityAttackedByEntityCallback
{
    Event<EntityAttackedByEntityCallback> EVENT = EventFactory.createArrayBacked(EntityAttackedByEntityCallback.class, (callbacks) -> (attacker, attacked) -> {
        for (EntityAttackedByEntityCallback callback : callbacks) {
            callback.onEntityAttack(attacker, attacked);
        }
    });

    void onEntityAttack(LivingEntity attacker, LivingEntity attacked);
}
