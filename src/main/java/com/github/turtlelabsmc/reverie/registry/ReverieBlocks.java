package com.github.turtlelabsmc.reverie.registry;

import com.github.turtlelabsmc.reverie.block.LarciaBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.lwjgl.system.CallbackI;

public class ReverieBlocks
{
    public static final Block LARCIA_BLOCK = new LarciaBlock(StatusEffects.REGENERATION, AbstractBlock.Settings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));


    public static void init()
    {
        Registry.register(Registry.BLOCK, new Identifier("reverie", "larcia"), LARCIA_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("reverie", "larcia"), new BlockItem(LARCIA_BLOCK, new FabricItemSettings().group(ItemGroup.DECORATIONS)));
    }
}
