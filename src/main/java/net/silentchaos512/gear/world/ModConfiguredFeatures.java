package net.silentchaos512.gear.world;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.silentchaos512.gear.config.Config;
import net.silentchaos512.gear.init.ModBlocks;

public class ModConfiguredFeatures {
    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> SALVENETTLE_VEGITATION = FeatureUtils.register("salvenettle_vegitation", Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.SALVENETTLE.get())));
    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> WILDERCRESS_VEGITATION = FeatureUtils.register("wildercress_vegitation", Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.WILDERCRESS.get())));
    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> BLIGHTLEAF_VEGITATION = FeatureUtils.register("blightleaf_vegitation", Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.BLIGHTLEAF.get())));
    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> ROSEBLOOD_VEGITATION = FeatureUtils.register("roseblood_vegitation", Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.ROSEBLOOD.get())));
    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> BRYLL_VEGITATION = FeatureUtils.register("bryll_vegitation", Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.BRYLL.get())));
    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> DUSKWEED_VEGITATION = FeatureUtils.register("duskweed_vegitation", Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.DUSKWEED.get())));
    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> SOULBELL_VEGITATION = FeatureUtils.register("soulbell_vegitation", Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.SOULBELL.get())));
    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> ECTOGRASS_VEGITATION = FeatureUtils.register("ectograss_vegitation", Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.ECTOGRASS.get())));
    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> RUNELEAF_VEGITATION = FeatureUtils.register("runeleaf_vegitation", Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.RUNELEAF.get())));
    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> SPIRITBLOOM_VEGITATION = FeatureUtils.register("spiritbloom_vegitation", Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.SPIRITBLOOM.get())));
    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> CELESTIALBLOSSOM_VEGITATION = FeatureUtils.register("celestialblossom_vegitation", Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.CELESTIALBLOSSOM.get())));

    public static final Holder<ConfiguredFeature<VegetationPatchConfiguration, ?>> SALVENETTLE_PATCH_CONFIG = FeatureUtils.register("salvenettle_patch_config",
        Feature.VEGETATION_PATCH,
        new VegetationPatchConfiguration(
            BlockTags.DEEPSLATE_ORE_REPLACEABLES,                   // Blocks that can be replaced by ground block
            BlockStateProvider.simple(Blocks.DEEPSLATE),            // Ground block upon which veg will spawn
            PlacementUtils.inlinePlaced(SALVENETTLE_VEGITATION),    // Feature that will be placed by patch
            CaveSurface.FLOOR,                                      // Where
            ConstantInt.of(1),                            // Depth of the base covered by ground blocks
            0.0F,                                         // Probability of putting the ground blocks one block deeper
            5,                                            // Vertical range used to determine a suitable position for patch
            Config.Common.daemonPlantChance.get().floatValue(),     // Probability of spawning each veg on the patch
            UniformInt.of(2, 3),                // Horizontal area that patch will cover
            Config.Common.daemonPlantChance.get().floatValue()      // Probability of spawning veg on edge of patch
        ));
    
    public static final Holder<ConfiguredFeature<VegetationPatchConfiguration, ?>> WILDERCRESS_PATCH_CONFIG = FeatureUtils.register("wildercress_patch_config",
        Feature.VEGETATION_PATCH,
        new VegetationPatchConfiguration(
            BlockTags.DEEPSLATE_ORE_REPLACEABLES,                   // Blocks that can be replaced by ground block
            BlockStateProvider.simple(Blocks.DEEPSLATE),            // Ground block upon which veg will spawn
            PlacementUtils.inlinePlaced(WILDERCRESS_VEGITATION),    // Feature that will be placed by patch
            CaveSurface.FLOOR,                                      // Where
            ConstantInt.of(1),                            // Depth of the base covered by ground blocks
            0.0F,                                         // Probability of putting the ground blocks one block deeper
            5,                                            // Vertical range used to determine a suitable position for patch
            Config.Common.daemonPlantChance.get().floatValue(),     // Probability of spawning each veg on the patch
            UniformInt.of(2, 3),                // Horizontal area that patch will cover
            Config.Common.daemonPlantChance.get().floatValue()      // Probability of spawning veg on edge of patch
        ));
    
    public static final Holder<ConfiguredFeature<VegetationPatchConfiguration, ?>> BLIGHTLEAF_PATCH_CONFIG = FeatureUtils.register("blightleaf_patch_config",
        Feature.VEGETATION_PATCH,
        new VegetationPatchConfiguration(
            BlockTags.DEEPSLATE_ORE_REPLACEABLES,                   // Blocks that can be replaced by ground block
            BlockStateProvider.simple(Blocks.DEEPSLATE),            // Ground block upon which veg will spawn
            PlacementUtils.inlinePlaced(BLIGHTLEAF_VEGITATION),     // Feature that will be placed by patch
            CaveSurface.FLOOR,                                      // Where
            ConstantInt.of(1),                            // Depth of the base covered by ground blocks
            0.0F,                                         // Probability of putting the ground blocks one block deeper
            5,                                            // Vertical range used to determine a suitable position for patch
            Config.Common.daemonPlantChance.get().floatValue(),     // Probability of spawning each veg on the patch
            UniformInt.of(2, 3),                // Horizontal area that patch will cover
            Config.Common.daemonPlantChance.get().floatValue()      // Probability of spawning veg on edge of patch
        ));
    
    public static final Holder<ConfiguredFeature<VegetationPatchConfiguration, ?>> ROSEBLOOD_PATCH_CONFIG = FeatureUtils.register("roseblood_patch_config",
        Feature.VEGETATION_PATCH,
        new VegetationPatchConfiguration(
            BlockTags.DEEPSLATE_ORE_REPLACEABLES,                   // Blocks that can be replaced by ground block
            BlockStateProvider.simple(Blocks.DEEPSLATE),            // Ground block upon which veg will spawn
            PlacementUtils.inlinePlaced(ROSEBLOOD_VEGITATION),      // Feature that will be placed by patch
            CaveSurface.FLOOR,                                      // Where
            ConstantInt.of(1),                            // Depth of the base covered by ground blocks
            0.0F,                                         // Probability of putting the ground blocks one block deeper
            5,                                            // Vertical range used to determine a suitable position for patch
            Config.Common.daemonPlantChance.get().floatValue(),     // Probability of spawning each veg on the patch
            UniformInt.of(2, 3),                // Horizontal area that patch will cover
            Config.Common.daemonPlantChance.get().floatValue()      // Probability of spawning veg on edge of patch
        ));
    
    public static final Holder<ConfiguredFeature<VegetationPatchConfiguration, ?>> BRYLL_PATCH_CONFIG = FeatureUtils.register("bryll_patch_config",
        Feature.VEGETATION_PATCH,
        new VegetationPatchConfiguration(
            BlockTags.DEEPSLATE_ORE_REPLACEABLES,                   // Blocks that can be replaced by ground block
            BlockStateProvider.simple(Blocks.DEEPSLATE),            // Ground block upon which veg will spawn
            PlacementUtils.inlinePlaced(BRYLL_VEGITATION),          // Feature that will be placed by patch
            CaveSurface.FLOOR,                                      // Where
            ConstantInt.of(1),                            // Depth of the base covered by ground blocks
            0.0F,                                         // Probability of putting the ground blocks one block deeper
            5,                                            // Vertical range used to determine a suitable position for patch
            Config.Common.daemonPlantChance.get().floatValue(),     // Probability of spawning each veg on the patch
            UniformInt.of(2, 3),                // Horizontal area that patch will cover
            Config.Common.daemonPlantChance.get().floatValue()      // Probability of spawning veg on edge of patch
        ));
    
    public static final Holder<ConfiguredFeature<VegetationPatchConfiguration, ?>> DUSKWEED_PATCH_CONFIG = FeatureUtils.register("duskweed_patch_config",
        Feature.VEGETATION_PATCH,
        new VegetationPatchConfiguration(
            BlockTags.DEEPSLATE_ORE_REPLACEABLES,                   // Blocks that can be replaced by ground block
            BlockStateProvider.simple(Blocks.DEEPSLATE),            // Ground block upon which veg will spawn
            PlacementUtils.inlinePlaced(DUSKWEED_VEGITATION),       // Feature that will be placed by patch
            CaveSurface.FLOOR,                                      // Where
            ConstantInt.of(1),                            // Depth of the base covered by ground blocks
            0.0F,                                         // Probability of putting the ground blocks one block deeper
            5,                                            // Vertical range used to determine a suitable position for patch
            Config.Common.daemonPlantChance.get().floatValue(),     // Probability of spawning each veg on the patch
            UniformInt.of(2, 3),                // Horizontal area that patch will cover
            Config.Common.daemonPlantChance.get().floatValue()      // Probability of spawning veg on edge of patch
        ));
    
    public static final Holder<ConfiguredFeature<VegetationPatchConfiguration, ?>> SOULBELL_PATCH_CONFIG = FeatureUtils.register("soulbell_patch_config",
        Feature.VEGETATION_PATCH,
        new VegetationPatchConfiguration(
            BlockTags.DEEPSLATE_ORE_REPLACEABLES,                   // Blocks that can be replaced by ground block
            BlockStateProvider.simple(Blocks.DEEPSLATE),            // Ground block upon which veg will spawn
            PlacementUtils.inlinePlaced(SOULBELL_VEGITATION),       // Feature that will be placed by patch
            CaveSurface.FLOOR,                                      // Where
            ConstantInt.of(1),                            // Depth of the base covered by ground blocks
            0.0F,                                         // Probability of putting the ground blocks one block deeper
            5,                                            // Vertical range used to determine a suitable position for patch
            Config.Common.daemonPlantChance.get().floatValue(),     // Probability of spawning each veg on the patch
            UniformInt.of(2, 3),                // Horizontal area that patch will cover
            Config.Common.daemonPlantChance.get().floatValue()      // Probability of spawning veg on edge of patch
        ));
    
    public static final Holder<ConfiguredFeature<VegetationPatchConfiguration, ?>> ECTOGRASS_PATCH_CONFIG = FeatureUtils.register("ectograss_patch_config",
        Feature.VEGETATION_PATCH,
        new VegetationPatchConfiguration(
            BlockTags.DEEPSLATE_ORE_REPLACEABLES,                   // Blocks that can be replaced by ground block
            BlockStateProvider.simple(Blocks.DEEPSLATE),            // Ground block upon which veg will spawn
            PlacementUtils.inlinePlaced(ECTOGRASS_VEGITATION),      // Feature that will be placed by patch
            CaveSurface.FLOOR,                                      // Where
            ConstantInt.of(1),                            // Depth of the base covered by ground blocks
            0.0F,                                         // Probability of putting the ground blocks one block deeper
            5,                                            // Vertical range used to determine a suitable position for patch
            Config.Common.daemonPlantChance.get().floatValue(),     // Probability of spawning each veg on the patch
            UniformInt.of(2, 3),                // Horizontal area that patch will cover
            Config.Common.daemonPlantChance.get().floatValue()      // Probability of spawning veg on edge of patch
        ));
    
    public static final Holder<ConfiguredFeature<VegetationPatchConfiguration, ?>> RUNELEAF_PATCH_CONFIG = FeatureUtils.register("runeleaf_patch_config",
        Feature.VEGETATION_PATCH,
        new VegetationPatchConfiguration(
            BlockTags.DEEPSLATE_ORE_REPLACEABLES,                   // Blocks that can be replaced by ground block
            BlockStateProvider.simple(Blocks.DEEPSLATE),            // Ground block upon which veg will spawn
            PlacementUtils.inlinePlaced(RUNELEAF_VEGITATION),       // Feature that will be placed by patch
            CaveSurface.FLOOR,                                      // Where
            ConstantInt.of(1),                            // Depth of the base covered by ground blocks
            0.0F,                                         // Probability of putting the ground blocks one block deeper
            5,                                            // Vertical range used to determine a suitable position for patch
            Config.Common.daemonPlantChance.get().floatValue(),     // Probability of spawning each veg on the patch
            UniformInt.of(2, 3),                // Horizontal area that patch will cover
            Config.Common.daemonPlantChance.get().floatValue()      // Probability of spawning veg on edge of patch
        ));
    
    public static final Holder<ConfiguredFeature<VegetationPatchConfiguration, ?>> SPIRITBLOOM_PATCH_CONFIG = FeatureUtils.register("spiritbloom_patch_config",
        Feature.VEGETATION_PATCH,
        new VegetationPatchConfiguration(
            BlockTags.DEEPSLATE_ORE_REPLACEABLES,                   // Blocks that can be replaced by ground block
            BlockStateProvider.simple(Blocks.DEEPSLATE),            // Ground block upon which veg will spawn
            PlacementUtils.inlinePlaced(SPIRITBLOOM_VEGITATION),    // Feature that will be placed by patch
            CaveSurface.FLOOR,                                      // Where
            ConstantInt.of(1),                            // Depth of the base covered by ground blocks
            0.0F,                                         // Probability of putting the ground blocks one block deeper
            5,                                            // Vertical range used to determine a suitable position for patch
            Config.Common.daemonPlantChance.get().floatValue(),     // Probability of spawning each veg on the patch
            UniformInt.of(2, 3),                // Horizontal area that patch will cover
            Config.Common.daemonPlantChance.get().floatValue()      // Probability of spawning veg on edge of patch
        ));
    
    public static final Holder<ConfiguredFeature<VegetationPatchConfiguration, ?>> CELESTIALBLOSSOM_PATCH_CONFIG = FeatureUtils.register("celestialblossom_patch_config",
        Feature.VEGETATION_PATCH,
        new VegetationPatchConfiguration(
            BlockTags.DEEPSLATE_ORE_REPLACEABLES,                       // Blocks that can be replaced by ground block
            BlockStateProvider.simple(Blocks.DEEPSLATE),                // Ground block upon which veg will spawn
            PlacementUtils.inlinePlaced(CELESTIALBLOSSOM_VEGITATION),   // Feature that will be placed by patch
            CaveSurface.FLOOR,                                          // Where
            ConstantInt.of(1),                                // Depth of the base covered by ground blocks
            0.0F,                                             // Probability of putting the ground blocks one block deeper
            5,                                                // Vertical range used to determine a suitable position for patch
            Config.Common.daemonPlantChance.get().floatValue(),         // Probability of spawning each veg on the patch
            UniformInt.of(2, 3),                    // Horizontal area that patch will cover
            Config.Common.daemonPlantChance.get().floatValue()          // Probability of spawning veg on edge of patch
        ));
}
