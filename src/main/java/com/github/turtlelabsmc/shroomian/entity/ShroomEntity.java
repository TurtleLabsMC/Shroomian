package com.github.turtlelabsmc.shroomian.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
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
public abstract class ShroomEntity extends AnimalEntity
{
    private static final TrackedData<ShroomType> SHROOMIAN_TYPE = DataTracker.registerData(ShroomEntity.class, ShroomType.SHROOM_VARIANT);

    protected ShroomEntity(EntityType<? extends AnimalEntity> entityType, World world)
    {
        super(entityType, world);
    }

    public void pushAwayFrom(Entity entity)
    {
        if (!entity.noClip && !this.noClip) {
            LivingEntity livingEntity = (LivingEntity)entity;
            if (!(livingEntity instanceof ShroomEntity)) {
                livingEntity.setAttacker(this);
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 1));
            }
        }
    }

    public boolean onHit()
    {
        LivingEntity livingEntity = Objects.requireNonNull(this.getAttacker());
        if (!(livingEntity instanceof ShroomEntity)) {
            livingEntity.setAttacker(this);
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 60, 1));
        }
        return true;
    }

    public boolean damage(DamageSource source, float amount)
    {
        if (super.damage(source, amount) && this.getAttacker() != null) {
            return this.onHit();
        }
        return false;
    }

    @Override
    public boolean canHaveStatusEffect(StatusEffectInstance effect)
    {
        return effect.getEffectType() != StatusEffects.POISON;
    }

    @Override
    protected void initDataTracker()
    {
        super.initDataTracker();
        this.dataTracker.startTracking(SHROOMIAN_TYPE, ShroomType.WARPED);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt)
    {
        if (entityData instanceof ShroomData) {
            this.setVariant(((ShroomData)entityData).type);
        } else {
            ShroomType type = world.getBiomeKey(this.getBlockPos()).map(ShroomType::fromBiome).orElse(ShroomType.WARPED);
            entityData = new ShroomData(type);
            this.setVariant(type);
        }
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound compound)
    {
        super.writeCustomDataToNbt(compound);
        compound.putString("ShroomType", this.getVariant().getName());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound compound)
    {
        super.readCustomDataFromNbt(compound);
        this.setVariant(ShroomType.fromName(compound.getString("ShroomType")));
    }

    @Override
    public boolean canWalkOnFluid(Fluid fluid)
    {
        return fluid.isIn(FluidTags.LAVA);
    }

    public void setVariant(ShroomType type)
    {
        this.dataTracker.set(SHROOMIAN_TYPE, type);
    }

    public ShroomType getVariant()
    {
        return this.dataTracker.get(SHROOMIAN_TYPE);
    }

    public enum ShroomType
    {
        WARPED("warped", BiomeKeys.WARPED_FOREST),
        CRIMSON("crimson", BiomeKeys.CRIMSON_FOREST);

        private static final Random random = new Random();
        private static final Map<String, ShroomType> TYPES_BY_NAME = Arrays.stream(values()).collect(Collectors.toMap(ShroomType::getName, (type) -> type));
        private final String name;
        private final List<RegistryKey<Biome>> spawnBiomes;

        @SafeVarargs
        ShroomType(String nameIn, RegistryKey<Biome>... spawnBiomesIn)
        {
            this.name = nameIn;
            this.spawnBiomes = Arrays.asList(spawnBiomesIn);
        }

        public static ShroomType fromName(String name)
        {
            return TYPES_BY_NAME.getOrDefault(name, WARPED);
        }

        public static ShroomType fromBiome(RegistryKey<Biome> biomeKey)
        {
            List<ShroomType> possibleTypes = Arrays.stream(ShroomType.values()).filter(type -> type.getSpawnBiomes().contains(biomeKey)).collect(Collectors.toList());
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

        public static final TrackedDataHandler<ShroomType> SHROOM_VARIANT = new TrackedDataHandler<>()
        {
            @Override
            public void write(PacketByteBuf buf, ShroomType value)
            {
                buf.writeEnumConstant(value);
            }

            @Override
            public ShroomType read(PacketByteBuf buf)
            {
                return buf.readEnumConstant(ShroomType.class);
            }

            @Override
            public ShroomType copy(ShroomType value)
            {
                return value;
            }
        };

        static {
            TrackedDataHandlerRegistry.register(SHROOM_VARIANT);
        }
    }

    public static class ShroomData extends PassiveEntity.PassiveData
    {
        public final ShroomType type;

        public ShroomData(ShroomType typeIn)
        {
            super(false);
            this.type = typeIn;
        }
    }
}
