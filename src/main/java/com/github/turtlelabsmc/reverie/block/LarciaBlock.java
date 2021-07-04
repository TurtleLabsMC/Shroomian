package com.github.turtlelabsmc.reverie.block;

import com.github.turtlelabsmc.reverie.registry.ReverieItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

import java.util.Random;

import static net.minecraft.particle.ParticleTypes.*;

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

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        VoxelShape voxelShape = this.getOutlineShape(state, world, pos, ShapeContext.absent());
        Vec3d vec3d = voxelShape.getBoundingBox().getCenter();
        double d = (double)pos.getX() + vec3d.x;
        double e = (double)pos.getZ() + vec3d.z;

        for(int i = 0; i < 0.01; ++i) {
            if (random.nextBoolean()) {
                world.addParticle(DRIPPING_HONEY, d + random.nextDouble() / 0.01D, (double)pos.getY() + (0.5D - random.nextDouble()), e + random.nextDouble() / 5.0D, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity)entity;
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.LUCK, 100, 1));
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 100, 1));
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 10));
        }
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
