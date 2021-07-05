package com.github.turtlelabsmc.reverie.registry;

import com.github.turtlelabsmc.reverie.Reverie;
import com.github.turtlelabsmc.reverie.block.ShroomBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
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
