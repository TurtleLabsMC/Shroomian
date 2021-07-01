package com.github.turtlelabsmc.reverie.mixin;

import com.github.turtlelabsmc.reverie.event.EntityAttackedByEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin
{
    @Inject(method = "applyDamageEffects", at = @At("TAIL"))
    public void SporeEffectAttack(LivingEntity attacker, Entity target, CallbackInfo ci)
    {
        if (!(target instanceof LivingEntity)) return;

        EntityAttackedByEntityCallback.EVENT.invoker().onEntityAttack(attacker, (LivingEntity)target);
    }
}

