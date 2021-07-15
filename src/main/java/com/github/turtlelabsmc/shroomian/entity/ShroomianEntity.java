package com.github.turtlelabsmc.shroomian.entity;

import com.github.turtlelabsmc.shroomian.registry.EntityRegistry;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer.Builder;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("EntityConstructor")
public class ShroomianEntity extends ShroomEntity
{
    public ShroomianEntity(EntityType<? extends ShroomianEntity> type, World worldIn)
    {
        super(type, worldIn);
    }

    public static Builder createShroomianAttributes()
    {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 30.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2D);
    }

    @Override
    protected void initGoals()
    {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 0.3D));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, PlayerEntity.class, 8.0f, 1.5, 2.0));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(3, new LookAtEntityGoal(this, HoglinEntity.class, 6.0F));
        this.goalSelector.add(4, new LookAroundGoal(this));
    }

    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions size)
    {
        return this.isAlive() ? size.height * 0.85F : size.height * 0.92F;
    }

    @Nullable
    @Override
    public ShroomianEntity createChild(ServerWorld serverWorld, PassiveEntity mate)
    {
        ShroomianEntity child = EntityRegistry.SHROOMIAN.create(this.world);
        if(child != null) {
            child.setVariant(this.random.nextFloat() < 0.5f ? ((ShroomianEntity) (mate)).getVariant() : this.getVariant());
        }
        return child;
    }
}
