package net.silentchaos512.gear.world;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraftforge.fml.ModList;
import net.silentchaos512.gear.config.Config;

public class ModPlacedFeatures {
    public static final int DAEMONPLANTS_STRATUM_FACTOR = ModList.get().isLoaded("curseoftheabyss") ? -net.endgineer.curseoftheabyss.config.spec.ModCommonConfig.ABYSS.ABYSS.SPAN.get()/11 : 64/11;

    public static final Holder<PlacedFeature> SALVENETTLE_PLACED = PlacementUtils.register("salvenettle_placed", ModConfiguredFeatures.SALVENETTLE_CONFIGURED,
        RarityFilter.onAverageOnceEvery(Config.Common.daemonPlantCount.get()),
        InSquarePlacement.spread(),
        HeightRangePlacement.triangle(VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*1),
        VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*0)),
        BiomeFilter.biome());

    public static final Holder<PlacedFeature> WILDERCRESS_PLACED = PlacementUtils.register("wildercress_placed", ModConfiguredFeatures.WILDERCRESS_CONFIGURED,
        RarityFilter.onAverageOnceEvery(Config.Common.daemonPlantCount.get()),
        InSquarePlacement.spread(),
        HeightRangePlacement.triangle(VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*2),
        VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*1)),
        BiomeFilter.biome());

    public static final Holder<PlacedFeature> BLIGHTLEAF_PLACED = PlacementUtils.register("blightleaf_placed", ModConfiguredFeatures.BLIGHTLEAF_CONFIGURED,
        RarityFilter.onAverageOnceEvery(Config.Common.daemonPlantCount.get()),
        InSquarePlacement.spread(),
        HeightRangePlacement.triangle(VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*3),
        VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*2)),
        BiomeFilter.biome());

    public static final Holder<PlacedFeature> ROSEBLOOD_PLACED = PlacementUtils.register("roseblood_placed", ModConfiguredFeatures.ROSEBLOOD_CONFIGURED,
        RarityFilter.onAverageOnceEvery(Config.Common.daemonPlantCount.get()),
        InSquarePlacement.spread(),
        HeightRangePlacement.triangle(VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*4),
        VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*3)),
        BiomeFilter.biome());

    public static final Holder<PlacedFeature> BRYLL_PLACED = PlacementUtils.register("bryll_placed", ModConfiguredFeatures.BRYLL_CONFIGURED,
        RarityFilter.onAverageOnceEvery(Config.Common.daemonPlantCount.get()),
        InSquarePlacement.spread(),
        HeightRangePlacement.triangle(VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*5),
        VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*4)),
        BiomeFilter.biome());

    public static final Holder<PlacedFeature> DUSKWEED_PLACED = PlacementUtils.register("duskweed_placed", ModConfiguredFeatures.DUSKWEED_CONFIGURED,
        RarityFilter.onAverageOnceEvery(Config.Common.daemonPlantCount.get()),
        InSquarePlacement.spread(),
        HeightRangePlacement.triangle(VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*6),
        VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*5)),
        BiomeFilter.biome());

    public static final Holder<PlacedFeature> SOULBELL_PLACED = PlacementUtils.register("soulbell_placed", ModConfiguredFeatures.SOULBELL_CONFIGURED,
        RarityFilter.onAverageOnceEvery(Config.Common.daemonPlantCount.get()),
        InSquarePlacement.spread(),
        HeightRangePlacement.triangle(VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*7),
        VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*6)),
        BiomeFilter.biome());

    public static final Holder<PlacedFeature> ECTOGRASS_PLACED = PlacementUtils.register("ectograss_placed", ModConfiguredFeatures.ECTOGRASS_CONFIGURED,
        RarityFilter.onAverageOnceEvery(Config.Common.daemonPlantCount.get()),
        InSquarePlacement.spread(),
        HeightRangePlacement.triangle(VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*8),
        VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*7)),
        BiomeFilter.biome());

    public static final Holder<PlacedFeature> RUNELEAF_PLACED = PlacementUtils.register("runeleaf_placed", ModConfiguredFeatures.RUNELEAF_CONFIGURED,
        RarityFilter.onAverageOnceEvery(Config.Common.daemonPlantCount.get()),
        InSquarePlacement.spread(),
        HeightRangePlacement.triangle(VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*9),
        VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*8)),
        BiomeFilter.biome());

    public static final Holder<PlacedFeature> SPIRITBLOOM_PLACED = PlacementUtils.register("spiritbloom_placed", ModConfiguredFeatures.SPIRITBLOOM_CONFIGURED,
        RarityFilter.onAverageOnceEvery(Config.Common.daemonPlantCount.get()),
        InSquarePlacement.spread(),
        HeightRangePlacement.triangle(VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*10),
        VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*9)),
        BiomeFilter.biome());

    public static final Holder<PlacedFeature> CELESTIALBLOSSOM_PLACED = PlacementUtils.register("celestialblossom_placed", ModConfiguredFeatures.CELESTIALBLOSSOM_CONFIGURED,
        RarityFilter.onAverageOnceEvery(Config.Common.daemonPlantCount.get()),
        InSquarePlacement.spread(),
        HeightRangePlacement.triangle(VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*11),
        VerticalAnchor.absolute(DAEMONPLANTS_STRATUM_FACTOR*10)),
        BiomeFilter.biome());
}
