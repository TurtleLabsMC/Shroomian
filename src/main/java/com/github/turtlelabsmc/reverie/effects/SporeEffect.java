package com.github.turtlelabsmc.reverie.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.stat.Stat;

public class SporeEffect extends StatusEffect
{
    public SporeEffect()
    {
        super(StatusEffectType.BENEFICIAL, 0x7535A8); // color can change
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier)
    {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier)
    {

    }
}
