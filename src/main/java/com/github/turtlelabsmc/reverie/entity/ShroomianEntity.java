package com.github.turtlelabsmc.reverie.entity;

import com.github.turtlelabsmc.reverie.registry.EntityRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer.Builder;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("EntityConstructor")
public class ShroomianEntity extends AnimalEntity
{
    private static final TrackedData<ShroomianType> SHROOMIAN_TYPE = DataTracker.registerData(ShroomianEntity.class, ShroomianType.SHROOMIAN_VARIANT);

    public ShroomianEntity(EntityType<? extends ShroomianEntity> type, World worldIn)
    {
        super(type, worldIn);
    }

    public static Builder createShroomianAttributes()
    {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 30.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2D);
    }

    @Override
    protected void initDataTracker()
    {
        super.initDataTracker();
        this.dataTracker.startTracking(SHROOMIAN_TYPE, ShroomianType.WARPED);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt)
    {
        if (entityData instanceof ShroomianData) {
            this.setVariant(((ShroomianData)entityData).type);
        } else {
            ShroomianType type = ShroomianType.fromBiome(world.getBiomeKey(this.getBlockPos()));
            entityData = new ShroomianData(type);
            this.setVariant(type);
        }
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
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
    public void writeCustomDataToNbt(NbtCompound compound)
    {
        super.writeCustomDataToNbt(compound);
        compound.putString("Type", this.getVariant().getName());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound compound)
    {
        super.readCustomDataFromNbt(compound);
        this.setVariant(ShroomianType.fromName(compound.getString("Type")));
    }

    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions size)
    {
        return this.isAlive() ? size.height * 0.85F : size.height * 0.92F;
    }

    @Override
    public boolean canWalkOnFluid(Fluid fluid)
    {
        return fluid.isIn(FluidTags.LAVA);
    }


    private void setVariant(ShroomianType type)
    {
        this.dataTracker.set(SHROOMIAN_TYPE, type);
    }

    public ShroomianType getVariant()
    {
        return this.dataTracker.get(SHROOMIAN_TYPE);
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

    public enum ShroomianType
    {
        WARPED("warped", BiomeKeys.WARPED_FOREST),
        CRIMSON("crimson", BiomeKeys.CRIMSON_FOREST);

        private static final Random random = new Random();
        private static final Map<String, ShroomianType> TYPES_BY_NAME = Arrays.stream(values()).collect(Collectors.toMap(ShroomianType::getName, (type) -> type));
        private final String name;
        private final List<RegistryKey<Biome>> spawnBiomes;

        ShroomianType(String nameIn, RegistryKey<Biome>... spawnBiomesIn)
        {
            this.name = nameIn;
            this.spawnBiomes = Arrays.asList(spawnBiomesIn);
        }

        public static ShroomianType fromName(String name)
        {
            return TYPES_BY_NAME.getOrDefault(name, WARPED);
        }

        public static ShroomianType fromBiome(Optional<RegistryKey<Biome>> optional)
        {
            if (optional.isEmpty()) return WARPED;
            List<ShroomianType> possibleTypes = Arrays.stream(ShroomianType.values()).filter(type -> type.getSpawnBiomes().contains(optional.get())).collect(Collectors.toList());
            if (possibleTypes.isEmpty()) return WARPED;
            return possibleTypes.get(random.nextInt(possibleTypes.size()));
        }

        public String getName()
        {
            return this.name;
        }

        public List<RegistryKey<Biome>> getSpawnBiomes()
        {
            return this.spawnBiomes;
        }

        public static final TrackedDataHandler<ShroomianType> SHROOMIAN_VARIANT = new TrackedDataHandler<>()
        {
            @Override
            public void write(PacketByteBuf buf, ShroomianType value)
            {
                buf.writeEnumConstant(value);
            }

            @Override
            public ShroomianType read(PacketByteBuf buf)
            {
                return buf.readEnumConstant(ShroomianType.class);
            }

            @Override
            public ShroomianType copy(ShroomianType value)
            {
                return value;
            }
        };

        static {
            TrackedDataHandlerRegistry.register(SHROOMIAN_VARIANT);
        }
    }

    public static class ShroomianData extends PassiveEntity.PassiveData
    {
        public final ShroomianType type;

        public ShroomianData(ShroomianType typeIn)
        {
            super(false);
            this.type = typeIn;
        }
    }
}
