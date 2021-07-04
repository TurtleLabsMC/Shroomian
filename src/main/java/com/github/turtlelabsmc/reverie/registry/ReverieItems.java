package com.github.turtlelabsmc.reverie.registry;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ReverieItems
{
    public static final Item ELIXIR = new Item(new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(1).statusEffect(new StatusEffectInstance(StatusEffects.LUCK, 2400), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 2), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2400), 1.0F).alwaysEdible().build()));

    public static void init() {

        Registry.register(Registry.ITEM, new Identifier("reverie", "elixir"), ELIXIR);
    }
}
