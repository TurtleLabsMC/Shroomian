package com.github.turtlelabsmc.shroomian.entity;

import com.github.turtlelabsmc.shroomian.registry.EntityRegistry;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("EntityConstructor")
public class SporelingEntity extends ShroomEntity
{
    public SporelingEntity(EntityType<? extends AnimalEntity> entityType, World world)
    {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createSporelingAttributes()
    {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 15.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.15D);
    }

    @Override
    protected void initGoals()
    {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 0.3D));
        this.goalSelector.add(2, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(3, new LookAtEntityGoal(this, HoglinEntity.class, 6.0F));
        this.goalSelector.add(4, new LookAroundGoal(this));
    }

    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions size)
    {
        return this.isAlive() ? size.height * 0.6F : size.height * 0.6F;
    }

    @Nullable
    @Override
    public SporelingEntity createChild(ServerWorld serverWorld, PassiveEntity mate)
    {
        SporelingEntity child = EntityRegistry.SPORELING.create(this.world);
        if(child != null) {
            child.setVariant(this.random.nextFloat() < 0.5f ? ((SporelingEntity) (mate)).getVariant() : this.getVariant());
        }
        return child;
    }
}
