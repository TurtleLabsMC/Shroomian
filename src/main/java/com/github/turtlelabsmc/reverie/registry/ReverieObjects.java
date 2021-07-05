package com.github.turtlelabsmc.reverie.registry;

import com.github.turtlelabsmc.reverie.Reverie;
import com.github.turtlelabsmc.reverie.block.LarciaBlock;
import com.github.turtlelabsmc.reverie.block.ShroomBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;

public class ReverieObjects
{
    private static final Map<Item, Identifier> ITEMS = new HashMap<>();
    private static final Map<Block, Identifier> BLOCKS = new HashMap<>();

    public static final Block SHROOM_BLOCK = addBlockToRegistry("shroom", new ShroomBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC)), false);
    public static final Item SHROOM = addItemToRegistry("shroom", new AliasedBlockItem(SHROOM_BLOCK, withReverieGroup()));

    public static final Item ELIXIR = addItemToRegistry("elixir", new Item(new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(1).statusEffect(new StatusEffectInstance(StatusEffects.LUCK, 2400), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 2), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2400), 1.0F).alwaysEdible().build())));
    public static final Block LARCIA_BLOCK = addBlockToRegistry("larcia", new LarciaBlock(StatusEffects.REGENERATION, AbstractBlock.Settings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)), true);

    public static void register()
    {
        ITEMS.keySet().forEach(item -> Registry.register(Registry.ITEM, ITEMS.get(item), item));
        BLOCKS.keySet().forEach(block -> Registry.register(Registry.BLOCK, BLOCKS.get(block), block));
    }

    private static Item addItemToRegistry(String name, Item item)
    {
        ITEMS.put(item, Reverie.modId(name));
        return item;
    }

    private static Block addBlockToRegistry(String name, Block block, boolean withItem)
    {
        BLOCKS.put(block, Reverie.modId(name));
        if (withItem) ITEMS.put(new BlockItem(block, withReverieGroup()), BLOCKS.get(block));
        return block;
    }

    private static Item.Settings withReverieGroup()
    {
        return new Item.Settings().group(Reverie.ITEM_GROUP);
    }
}
