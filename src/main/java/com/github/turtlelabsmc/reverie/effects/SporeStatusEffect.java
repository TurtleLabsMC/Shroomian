package com.github.turtlelabsmc.reverie.effects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;

public class SporeStatusEffect extends StatusEffect
{
    public SporeStatusEffect()
    {
        super(StatusEffectType.BENEFICIAL, 0x7535A8); // color can change
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier)
    {
        return false;
    }
}
