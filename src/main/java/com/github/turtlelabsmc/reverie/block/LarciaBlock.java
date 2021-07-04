package com.github.turtlelabsmc.reverie.block;

import com.github.turtlelabsmc.reverie.registry.ReverieItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LarciaBlock extends FlowerBlock {

    public static final BooleanProperty WITHERED = BooleanProperty.of("withered");

    public LarciaBlock(StatusEffect effect, Settings settings) {
        super(effect, 5, settings);
        setDefaultState(getStateManager().getDefaultState().with(WITHERED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(WITHERED);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            boolean isWithered = state.get(WITHERED);
            if (player.getInventory().getMainHandStack().isOf(Items.GLASS_BOTTLE) && !isWithered) {
                world.setBlockState(pos, state.with(WITHERED, true));
                player.getMainHandStack().decrement(1);
                if (player.getMainHandStack().isEmpty()) {
                    player.setStackInHand(hand, new ItemStack(ReverieItems.ELIXIR));
                } else if (!player.getInventory().insertStack(new ItemStack(ReverieItems.ELIXIR))) {
                    player.dropItem(new ItemStack(ReverieItems.ELIXIR), false);
                }
                return ActionResult.SUCCESS;
            }

        }
        return ActionResult.FAIL;
    }
}
