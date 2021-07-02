package com.github.turtlelabsmc.reverie.registry;

import com.github.turtlelabsmc.reverie.Reverie;
import com.github.turtlelabsmc.reverie.block.ShroomBlock;
import com.github.turtlelabsmc.reverie.item.ShroomItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public class ReverieObjects {

    public static Map<Item, Identifier> ITEMS = new LinkedHashMap<>();
    public static Map<Block, Identifier> BLOCKS = new LinkedHashMap<>();

    private static Item.Settings withReverieGroup() {
        return new Item.Settings().group(Reverie.ITEM_GROUP);
    }

    public static final Block SHROOM_BLOCK = create("shroom", new ShroomBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC)), false);
    public static final Item SHROOM = create("shroom", new AliasedBlockItem(SHROOM_BLOCK, withReverieGroup()));

    private static <T extends Item> T create(String name, T item) {
        ITEMS.put(item, Reverie.modId(name));
        return item;
    }
    private static <T extends Block> T create(String name, T block, boolean withItem) {
        BLOCKS.put(block, Reverie.modId(name));
        if (withItem) ITEMS.put(new BlockItem(block, withReverieGroup()), BLOCKS.get(block));
        return block;
    }

    public static void register() {
        ITEMS.keySet().forEach(item -> Registry.register(Registry.ITEM, ITEMS.get(item), item));
        BLOCKS.keySet().forEach(block -> Registry.register(Registry.BLOCK, BLOCKS.get(block), block));
    }
}
