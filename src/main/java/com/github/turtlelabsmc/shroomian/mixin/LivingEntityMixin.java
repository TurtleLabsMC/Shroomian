package com.github.turtlelabsmc.shroomian.mixin;

import com.github.turtlelabsmc.shroomian.event.EntityAttackedByEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin
{
    @Inject(method = "onAttacking", at = @At("TAIL"))
    public void shroomianSporeEffectAttack(Entity target, CallbackInfo ci)
    {
        if (!(target instanceof LivingEntity)) return;

        EntityAttackedByEntityCallback.EVENT.invoker().onEntityAttack((LivingEntity)(Object)this, (LivingEntity)target);
    }
}

