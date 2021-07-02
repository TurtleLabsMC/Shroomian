package com.github.turtlelabsmc.reverie.entity;

import com.github.turtlelabsmc.reverie.registry.EntityRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.entity.attribute.DefaultAttributeContainer.Builder;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("EntityConstructor")
public class ShroomianEntity extends AnimalEntity {

    private static final TrackedData<Integer> SHROOMIAN_TYPE = DataTracker.registerData(ShroomianEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public ShroomianEntity(EntityType<? extends ShroomianEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static Builder createShroomianAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 30.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2D);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(SHROOMIAN_TYPE, 0);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        Optional<RegistryKey<Biome>> biome = world.getBiomeKey(this.getBlockPos());
        ShroomianEntity.Type type = ShroomianEntity.Type.fromBiome(biome);
        if(entityData instanceof ShroomianEntity.ShroomianData) {
            type = ((ShroomianEntity.ShroomianData) entityData).type;
        }
        else {
            entityData = new ShroomianEntity.ShroomianData(type);
        }
        this.setVariant(type);
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 0.3D));
        this.goalSelector.add(2, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(3, new LookAtEntityGoal(this, HoglinEntity.class, 6.0F));
        this.goalSelector.add(4, new LookAroundGoal(this));
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound compound) {
        super.writeCustomDataToNbt(compound);
        compound.putString("Type", this.getVariant().getName());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound compound) {
        super.readCustomDataFromNbt(compound);
        this.setVariant(ShroomianEntity.Type.byName(compound.getString("Type")));
    }

    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions size) {
        return this.isAlive() ? size.height * 0.85F : size.height * 0.92F;
    }

    @Override
    public boolean canWalkOnFluid(Fluid fluid) {
        return fluid == Fluids.LAVA;
    }


    private void setVariant(ShroomianEntity.Type type) {
        this.dataTracker.set(SHROOMIAN_TYPE, type.getIndex());
    }

    public ShroomianEntity.Type getVariant() {
        return ShroomianEntity.Type.fromId(this.dataTracker.get(SHROOMIAN_TYPE));
    }

    @Nullable
    @Override
    public ShroomianEntity createChild(ServerWorld serverWorld, PassiveEntity mate) {
        ShroomianEntity child = EntityRegistry.SHROOMIAN.create(this.world);
        if(child != null) {
            child.setVariant(this.random.nextFloat() < 0.5f ? ((ShroomianEntity) (mate)).getVariant() : this.getVariant());
        }
        return child;
    }

    public enum Type {
        WARPED(0, "warped", BiomeKeys.WARPED_FOREST),
        CRIMSON(1, "crimson", BiomeKeys.CRIMSON_FOREST);

        private static final ShroomianEntity.Type[] typeList = Arrays.stream(values()).sorted(Comparator.comparingInt(ShroomianEntity.Type::getIndex)).toArray(Type[]::new);
        private static final Map<String, Type> TYPES_BY_NAME = Arrays.stream(values()).collect(Collectors.toMap(ShroomianEntity.Type::getName, (type) -> type));
        private final int index;
        private final String name;
        private final List<RegistryKey<Biome>> spawnBiomes;

        Type(int indexIn, String nameIn, RegistryKey<Biome>... spawnBiomesIn) {
            this.index = indexIn;
            this.name = nameIn;
            this.spawnBiomes = Arrays.asList(spawnBiomesIn);
        }

        public static ShroomianEntity.Type byName(String name) {
            return TYPES_BY_NAME.getOrDefault(name, WARPED);
        }

        public static ShroomianEntity.Type fromId(int index) {
            if(index < 0 || index > typeList.length) {
                index = 0;
            }
            return typeList[index];
        }

        public static ShroomianEntity.Type fromBiome(Optional<RegistryKey<Biome>> optional) {
            List<Type> shuffledList = Arrays.asList(typeList.clone());
            Collections.shuffle(shuffledList);
            if(optional.isPresent()) {
                for(ShroomianEntity.Type type : shuffledList) {
                    if(type.getSpawnBiomes().contains(optional.get())) {
                        return type;
                    }
                }
            }
            return WARPED;
        }

        public String getName() {
            return this.name;
        }

        public List<RegistryKey<Biome>> getSpawnBiomes() {
            return this.spawnBiomes;
        }

        public int getIndex() {
            return this.index;
        }
    }

    public static class ShroomianData extends PassiveEntity.PassiveData {
        public final ShroomianEntity.Type type;

        public ShroomianData(ShroomianEntity.Type typeIn) {
            super(false);
            this.type = typeIn;
        }
    }
}
