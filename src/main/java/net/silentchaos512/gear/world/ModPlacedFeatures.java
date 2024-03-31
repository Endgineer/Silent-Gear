package net.silentchaos512.gear.world;

import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.EnvironmentScanPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraftforge.fml.ModList;
import net.silentchaos512.gear.config.Config;

public class ModPlacedFeatures {
    public static final int DAEMONPLANTS_STRATUM_FACTOR = ModList.get().isLoaded("curseoftheabyss") ? -net.endgineer.curseoftheabyss.config.spec.ModCommonConfig.ABYSS.ABYSS.SPAN.get()/11 : 64/11;

    public static final Holder<PlacedFeature> SALVENETTLE_PATCH_PLACED = PlacementUtils.register("salvenettle_patch_placed",
        ModConfiguredFeatures.SALVENETTLE_PATCH_CONFIG,
        RarityFilter.onAverageOnceEvery(Config.Common.daemonPlantPatchCount.get()),
        CountPlacement.of(1),
        InSquarePlacement.spread(),
        HeightRangePlacement.triangle(VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*1), VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*0)),
        EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
        RandomOffsetPlacement.vertical(ConstantInt.of(1)),
        BiomeFilter.biome());
    
    public static final Holder<PlacedFeature> WILDERCRESS_PATCH_PLACED = PlacementUtils.register("wildercress_patch_placed",
        ModConfiguredFeatures.WILDERCRESS_PATCH_CONFIG,
        RarityFilter.onAverageOnceEvery(Config.Common.daemonPlantPatchCount.get()),
        CountPlacement.of(1),
        InSquarePlacement.spread(),
        HeightRangePlacement.triangle(VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*2), VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*1)),
        EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
        RandomOffsetPlacement.vertical(ConstantInt.of(1)),
        BiomeFilter.biome());
    
    public static final Holder<PlacedFeature> BLIGHTLEAF_PATCH_PLACED = PlacementUtils.register("blightleaf_patch_placed",
        ModConfiguredFeatures.BLIGHTLEAF_PATCH_CONFIG,
        RarityFilter.onAverageOnceEvery(Config.Common.daemonPlantPatchCount.get()),
        CountPlacement.of(1),
        InSquarePlacement.spread(),
        HeightRangePlacement.triangle(VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*3), VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*2)),
        EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
        RandomOffsetPlacement.vertical(ConstantInt.of(1)),
        BiomeFilter.biome());
    
    public static final Holder<PlacedFeature> ROSEBLOOD_PATCH_PLACED = PlacementUtils.register("roseblood_patch_placed",
        ModConfiguredFeatures.ROSEBLOOD_PATCH_CONFIG,
        RarityFilter.onAverageOnceEvery(Config.Common.daemonPlantPatchCount.get()),
        CountPlacement.of(1),
        InSquarePlacement.spread(),
        HeightRangePlacement.triangle(VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*4), VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*3)),
        EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
        RandomOffsetPlacement.vertical(ConstantInt.of(1)),
        BiomeFilter.biome());
    
    public static final Holder<PlacedFeature> BRYLL_PATCH_PLACED = PlacementUtils.register("bryll_patch_placed",
        ModConfiguredFeatures.BRYLL_PATCH_CONFIG,
        RarityFilter.onAverageOnceEvery(Config.Common.daemonPlantPatchCount.get()),
        CountPlacement.of(1),
        InSquarePlacement.spread(),
        HeightRangePlacement.triangle(VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*5), VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*4)),
        EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
        RandomOffsetPlacement.vertical(ConstantInt.of(1)),
        BiomeFilter.biome());
    
    public static final Holder<PlacedFeature> DUSKWEED_PATCH_PLACED = PlacementUtils.register("duskweed_patch_placed",
        ModConfiguredFeatures.DUSKWEED_PATCH_CONFIG,
        RarityFilter.onAverageOnceEvery(Config.Common.daemonPlantPatchCount.get()),
        CountPlacement.of(1),
        InSquarePlacement.spread(),
        HeightRangePlacement.triangle(VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*6), VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*5)),
        EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
        RandomOffsetPlacement.vertical(ConstantInt.of(1)),
        BiomeFilter.biome());
    
    public static final Holder<PlacedFeature> SOULBELL_PATCH_PLACED = PlacementUtils.register("soulbell_patch_placed",
        ModConfiguredFeatures.SOULBELL_PATCH_CONFIG,
        RarityFilter.onAverageOnceEvery(Config.Common.daemonPlantPatchCount.get()),
        CountPlacement.of(1),
        InSquarePlacement.spread(),
        HeightRangePlacement.triangle(VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*7), VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*6)),
        EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
        RandomOffsetPlacement.vertical(ConstantInt.of(1)),
        BiomeFilter.biome());
    
    public static final Holder<PlacedFeature> ECTOGRASS_PATCH_PLACED = PlacementUtils.register("ectograss_patch_placed",
        ModConfiguredFeatures.ECTOGRASS_PATCH_CONFIG,
        RarityFilter.onAverageOnceEvery(Config.Common.daemonPlantPatchCount.get()),
        CountPlacement.of(1),
        InSquarePlacement.spread(),
        HeightRangePlacement.triangle(VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*8), VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*7)),
        EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
        RandomOffsetPlacement.vertical(ConstantInt.of(1)),
        BiomeFilter.biome());
    
    public static final Holder<PlacedFeature> RUNELEAF_PATCH_PLACED = PlacementUtils.register("runeleaf_patch_placed",
        ModConfiguredFeatures.RUNELEAF_PATCH_CONFIG,
        RarityFilter.onAverageOnceEvery(Config.Common.daemonPlantPatchCount.get()),
        CountPlacement.of(1),
        InSquarePlacement.spread(),
        HeightRangePlacement.triangle(VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*9), VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*8)),
        EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
        RandomOffsetPlacement.vertical(ConstantInt.of(1)),
        BiomeFilter.biome());
    
    public static final Holder<PlacedFeature> SPIRITBLOOM_PATCH_PLACED = PlacementUtils.register("spiritbloom_patch_placed",
        ModConfiguredFeatures.SPIRITBLOOM_PATCH_CONFIG,
        RarityFilter.onAverageOnceEvery(Config.Common.daemonPlantPatchCount.get()),
        CountPlacement.of(1),
        InSquarePlacement.spread(),
        HeightRangePlacement.triangle(VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*10), VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*9)),
        EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
        RandomOffsetPlacement.vertical(ConstantInt.of(1)),
        BiomeFilter.biome());
    
    public static final Holder<PlacedFeature> CELESTIALBLOSSOM_PATCH_PLACED = PlacementUtils.register("celestialblossom_patch_placed",
        ModConfiguredFeatures.CELESTIALBLOSSOM_PATCH_CONFIG,
        RarityFilter.onAverageOnceEvery(Config.Common.daemonPlantPatchCount.get()),
        CountPlacement.of(1),
        InSquarePlacement.spread(),
        HeightRangePlacement.triangle(VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*11), VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*10)),
        EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
        RandomOffsetPlacement.vertical(ConstantInt.of(1)),
        BiomeFilter.biome());
}
