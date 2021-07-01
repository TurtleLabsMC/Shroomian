package com.github.turtlelabsmc.reverie.mixin;

import com.github.turtlelabsmc.reverie.registry.StatusEffectRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class SporeEffectMixin
{
    @Shadow public abstract boolean addStatusEffect(StatusEffectInstance effect);

    @Inject(at = @At("TAIL"), method = "applyDamage", cancellable = true)
    public void SporeEffectAttack(DamageSource source, float amount, CallbackInfo callbackInfo)
    {
        LivingEntity attacker = (LivingEntity) source.getAttacker();

        if (attacker.hasStatusEffect(StatusEffectRegistry.SPORE_EFFECT)) {
            int amplifier = attacker.getStatusEffect(StatusEffectRegistry.SPORE_EFFECT).getAmplifier();

            this.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 40 * amplifier, 0, true, false));
        }
        // new StatusEffectInstance(StatusEffects.POISON, 40 * amplifier, 0, true, false));
    }
}

