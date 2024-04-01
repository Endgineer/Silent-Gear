package net.silentchaos512.gear.world;

import com.google.common.collect.ImmutableList;

import net.endgineer.curseoftheabyss.config.variables.ModVariables;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.silentchaos512.gear.SilentGear;
import net.silentchaos512.gear.config.Config;
import net.silentchaos512.gear.init.ModBlocks;
import net.silentchaos512.gear.util.ModResourceLocation;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Mod.EventBusSubscriber(modid = SilentGear.MOD_ID)
public final class ModWorldFeatures {
    public static final RuleTest END_STONE_RULE_TEST = new TagMatchTest(Tags.Blocks.END_STONES);

    private static boolean configuredFeaturesRegistered = false;

    @SuppressWarnings("WeakerAccess")
    public static final class Configured {
        static final Map<String, Lazy<ConfiguredFeature<?, ?>>> TO_REGISTER = new LinkedHashMap<>();

        private static final ReplaceBlockConfiguration TITANITE_ORE_VEINS_CONFIG = new ReplaceBlockConfiguration(ImmutableList.of(
                OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_TITANITE_ORE.get().defaultBlockState())));

        public static final Holder<ConfiguredFeature<ReplaceBlockConfiguration, ?>> TITANITE_ORE_VEINS = create("titanite_ore_veins", Feature.REPLACE_SINGLE_BLOCK, TITANITE_ORE_VEINS_CONFIG);

        public static <FC extends FeatureConfiguration> Holder<ConfiguredFeature<FC, ?>> create(String name, Feature<FC> feature, FC featureConfig) {
            return FeatureUtils.register("silentgear:" + name, feature, featureConfig);
        }

        private Configured() {}
    }

    public static final class Placed {
        public static final Holder<PlacedFeature> ORE_TITANITE = create("ore_titanite", Configured.TITANITE_ORE_VEINS,
                commonOrePlacement(Config.Common.titaniteCount.get(),
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-ModVariables.ABYSS.SPAN), VerticalAnchor.absolute(0))));

        private static <FC extends FeatureConfiguration> Holder<PlacedFeature> create(String name, Holder<ConfiguredFeature<FC, ?>> configuredFeature, List<PlacementModifier> modifiers) {
            return PlacementUtils.register("silentgear:" + name, configuredFeature, modifiers);
        }

        private static <FC extends FeatureConfiguration> Holder<PlacedFeature> create(String name, Holder<ConfiguredFeature<FC, ?>> configuredFeature, PlacementModifier... modifiers) {
            return PlacementUtils.register("silentgear:" + name, configuredFeature, modifiers);
        }

        private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
            return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
        }

        private static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
            return orePlacement(CountPlacement.of(p_195344_), p_195345_);
        }

        private static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
            return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
        }

        private Placed() {}
    }

    private ModWorldFeatures() {}

    public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {}

    private static void registerConfiguredFeatures() {
        if (configuredFeaturesRegistered) return;

        configuredFeaturesRegistered = true;

        Configured.TO_REGISTER.forEach((name, cf) -> registerConfiguredFeature(name, cf.get()));
    }
    
    public static void generatePlants(final BiomeLoadingEvent event) {
        ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
        Set<net.minecraftforge.common.BiomeDictionary.Type> types = net.minecraftforge.common.BiomeDictionary.getTypes(key);

        if(/*types.contains(net.minecraftforge.common.BiomeDictionary.Type.OVERWORLD)*/true) {
            List<Holder<PlacedFeature>> base = event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION);

            base.add(ModPlacedFeatures.SALVENETTLE_PATCH_PLACED);
            base.add(ModPlacedFeatures.WILDERCRESS_PATCH_PLACED);
            base.add(ModPlacedFeatures.BLIGHTLEAF_PATCH_PLACED);
            base.add(ModPlacedFeatures.ROSEBLOOD_PATCH_PLACED);
            base.add(ModPlacedFeatures.BRYLL_PATCH_PLACED);
            base.add(ModPlacedFeatures.DUSKWEED_PATCH_PLACED);
            base.add(ModPlacedFeatures.SOULBELL_PATCH_PLACED);
            base.add(ModPlacedFeatures.ECTOGRASS_PATCH_PLACED);
            base.add(ModPlacedFeatures.RUNELEAF_PATCH_PLACED);
            base.add(ModPlacedFeatures.SPIRITBLOOM_PATCH_PLACED);
            base.add(ModPlacedFeatures.CELESTIALBLOSSOM_PATCH_PLACED);
        }
    }

    private static void registerConfiguredFeature(String name, ConfiguredFeature<?, ?> configuredFeature) {
        ModResourceLocation id = SilentGear.getId(name);
        debugLog("Register configured feature " + id);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, id, configuredFeature);
    }

    @SubscribeEvent
    public static void addFeaturesToBiomes(BiomeLoadingEvent biome) {
        // Need to load these as late as possible, or configs won't be loaded
        registerConfiguredFeatures();
        generatePlants(biome);
    }

    private static void debugLog(String msg) {
        if (Config.Common.worldGenLogging.get()) {
            SilentGear.LOGGER.debug(msg);
        }
    }
}
