package com.github.turtlelabsmc.shroomian.block;

import com.github.turtlelabsmc.shroomian.registry.ShroomianObjects;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class ShroomBlock extends PlantBlock
{
    public static final IntProperty AGE = Properties.AGE_3;;
    //public static final BooleanProperty WARPED = BooleanProperty.of("warped");

    public ShroomBlock(Settings settings)
    {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0));
    }

    public boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos)
    {
        return floor.isIn(BlockTags.NYLIUM);
    }

    public boolean hasRandomTicks(BlockState state)
    {
        return state.get(AGE) < 3;
    }

    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state)
    {
        return new ItemStack(ShroomianObjects.SHROOM);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager)
    {
        stateManager.add(AGE);
    }
}
