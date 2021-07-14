package com.github.turtlelabsmc.shroomian.registry;

import com.github.turtlelabsmc.shroomian.Shroomian;
import com.github.turtlelabsmc.shroomian.features.ShroomFeature;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.FeatureConfig;

public class FeatureRegistry
{
    public static final ShroomFeature SHROOM = new ShroomFeature(DefaultFeatureConfig.CODEC);
    public static final ConfiguredFeature<?, ?> SHROOM_CONFIGURED = SHROOM.configure(FeatureConfig.DEFAULT).decorate(Decorator.CHANCE.configure(new ChanceDecoratorConfig(100)));

    public static void register()
    {
      Registry.register(Registry.FEATURE, Shroomian.modId("shroom"), SHROOM);
      RegistryKey<ConfiguredFeature<?,?>> shroom = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, Shroomian.modId("shroom"));
      Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, shroom.getValue(), SHROOM_CONFIGURED);

      BiomeModifications.addFeature(
              BiomeSelectors.includeByKey(BiomeKeys.WARPED_FOREST, BiomeKeys.CRIMSON_FOREST),
              GenerationStep.Feature.UNDERGROUND_DECORATION, shroom
      );
    }
}
