package net.silentchaos512.gear.world;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.silentchaos512.gear.config.Config;
import net.silentchaos512.gear.init.ModBlocks;

public class ModConfiguredFeatures {
    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> SALVENETTLE_CONFIGURED = FeatureUtils.register("salvenettle_configured", Feature.FLOWER,
        new RandomPatchConfiguration(Config.Common.daemonPlantTries.get(), 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.SALVENETTLE.get())))));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> WILDERCRESS_CONFIGURED = FeatureUtils.register("wildercress_configured", Feature.FLOWER,
        new RandomPatchConfiguration(Config.Common.daemonPlantTries.get(), 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.WILDERCRESS.get())))));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> BLIGHTLEAF_CONFIGURED = FeatureUtils.register("blightleaf_configured", Feature.FLOWER,
        new RandomPatchConfiguration(Config.Common.daemonPlantTries.get(), 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.BLIGHTLEAF.get())))));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> ROSEBLOOD_CONFIGURED = FeatureUtils.register("roseblood_configured", Feature.FLOWER,
        new RandomPatchConfiguration(Config.Common.daemonPlantTries.get(), 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.ROSEBLOOD.get())))));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> BRYLL_CONFIGURED = FeatureUtils.register("bryll_configured", Feature.FLOWER,
        new RandomPatchConfiguration(Config.Common.daemonPlantTries.get(), 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.BRYLL.get())))));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> DUSKWEED_CONFIGURED = FeatureUtils.register("duskweed_configured", Feature.FLOWER,
        new RandomPatchConfiguration(Config.Common.daemonPlantTries.get(), 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.DUSKWEED.get())))));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> SOULBELL_CONFIGURED = FeatureUtils.register("soulbell_configured", Feature.FLOWER,
        new RandomPatchConfiguration(Config.Common.daemonPlantTries.get(), 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.SOULBELL.get())))));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> ECTOGRASS_CONFIGURED = FeatureUtils.register("ectograss_configured", Feature.FLOWER,
        new RandomPatchConfiguration(Config.Common.daemonPlantTries.get(), 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.ECTOGRASS.get())))));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> RUNELEAF_CONFIGURED = FeatureUtils.register("runeleaf_configured", Feature.FLOWER,
        new RandomPatchConfiguration(Config.Common.daemonPlantTries.get(), 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.RUNELEAF.get())))));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> SPIRITBLOOM_CONFIGURED = FeatureUtils.register("spiritbloom_configured", Feature.FLOWER,
        new RandomPatchConfiguration(Config.Common.daemonPlantTries.get(), 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.SPIRITBLOOM.get())))));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> CELESTIALBLOSSOM_CONFIGURED = FeatureUtils.register("celestialblossom_configured", Feature.FLOWER,
        new RandomPatchConfiguration(Config.Common.daemonPlantTries.get(), 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.CELESTIALBLOSSOM.get())))));
}
