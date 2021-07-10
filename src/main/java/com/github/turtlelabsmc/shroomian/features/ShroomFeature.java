package com.github.turtlelabsmc.shroomian.features;

import com.github.turtlelabsmc.shroomian.block.ShroomBlock;
import com.github.turtlelabsmc.shroomian.registry.ShroomianObjects;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;

public class ShroomFeature extends Feature<DefaultFeatureConfig>
{
    public ShroomFeature(Codec<DefaultFeatureConfig> config)
    {
        super(config);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> ctx)
    {
        StructureWorldAccess world = ctx.getWorld();
        System.out.println("HEY   ->   " + ctx.getOrigin().toShortString());
        Random random = ctx.getRandom();

        // Go up from the origin, roll dice, generate if dice rolled

        BlockPos.Mutable mutable = ctx.getOrigin().mutableCopy();
        for (int i=0; i<256; i++) {
            mutable.move(Direction.UP);
            if (canGenerate(world, mutable) && !isNotSuitable(world, mutable)) {
                if (random.nextInt(6) == 0) {
                    generateShroom(world, random, mutable, 0, 1); // 20% roll
                }
            }
        }

        BlockPos topPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE, ctx.getOrigin());
        Direction offset = Direction.NORTH;

        for (int y = 1; y <= 15; y++) {
            offset = offset.rotateYClockwise();
            world.setBlockState(topPos.up(y).offset(offset), Blocks.STONE.getDefaultState(), 3);
        }
        return true;
    }

    public static void generateShroom(WorldAccess world, Random random, BlockPos.Mutable pos, int minAge, int maxAge)
    {
        // Assumes `pos` is air block
        if (world.isAir(pos)) {
            world.setBlockState(pos, ShroomianObjects.SHROOM_BLOCK.getDefaultState().with(ShroomBlock.AGE, MathHelper.nextInt(random, minAge, maxAge)), Block.NOTIFY_LISTENERS);
        }
    }

    private static boolean canGenerate(WorldAccess world, BlockPos.Mutable pos)
    {
        // Don't generate at y=0
        do {
            pos.move(0, -1, 0);
            if (world.isOutOfHeightLimit(pos)) {
                return false;
            }
        } while(world.getBlockState(pos).isAir());

        pos.move(0, 1, 0);
        return true;
    }

    private static boolean isNotSuitable(WorldAccess world, BlockPos pos)
    {
        // Not air, and one of the Nylium variants
        if (!world.isAir(pos)) {
            return true;
        } else {
            BlockState blockState = world.getBlockState(pos.down());
            return !blockState.isIn(BlockTags.NYLIUM);
        }
    }

//    @Override
//    public boolean generate(StructureWorldAccess world, ChunkGenerator generator, Random random, BlockPos pos,
//                            DefaultFeatureConfig config) {
//        BlockPos topPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE, pos);
//        Direction offset = Direction.NORTH;
//
//        for (int y = 1; y <= 15; y++) {
//            offset = offset.rotateYClockwise();
//            world.setBlockState(topPos.up(y).offset(offset), Blocks.STONE.getDefaultState(), 3);
//        }
//
//        return true;
//    }
}