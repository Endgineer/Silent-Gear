package net.silentchaos512.gear.config;

import com.google.common.collect.ImmutableList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.silentchaos512.gear.SilentGear;
import net.silentchaos512.gear.api.stats.ItemStat;
import net.silentchaos512.gear.init.NerfedGear;
import net.silentchaos512.gear.item.blueprint.BlueprintType;
import net.silentchaos512.gear.util.GearHelper;
import net.silentchaos512.gear.util.IAoeTool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(modid = SilentGear.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class Config {
    public static final class Common {
        static final ForgeConfigSpec spec;
        // Blueprints
        public static final ForgeConfigSpec.EnumValue<BlueprintType> blueprintTypes;
        public static final ForgeConfigSpec.BooleanValue spawnWithStarterBlueprints;
        // Nerfed gear
        public static final ForgeConfigSpec.BooleanValue nerfedItemsEnabled;
        public static final ForgeConfigSpec.DoubleValue nerfedItemDurabilityMulti;
        public static final ForgeConfigSpec.DoubleValue nerfedItemHarvestSpeedMulti;
        static final ForgeConfigSpec.ConfigValue<List<? extends String>> nerfedItems;
        // Sinew
        public static final ForgeConfigSpec.DoubleValue sinewDropRate;
        static final ForgeConfigSpec.ConfigValue<List<? extends String>> sinewAnimals;
        // Gear
        public static final ForgeConfigSpec.BooleanValue allowLegacyMaterialMixing;
        public static final ForgeConfigSpec.BooleanValue allowConversionRecipes;
        public static final ForgeConfigSpec.BooleanValue forceRemoveEnchantments;
        public static final ForgeConfigSpec.BooleanValue sendGearBrokenMessage;
        public static final ForgeConfigSpec.EnumValue<IAoeTool.MatchMode> matchModeStandard;
        public static final ForgeConfigSpec.EnumValue<IAoeTool.MatchMode> matchModeOres;
        public static final ForgeConfigSpec.IntValue damageFactorLevels;
        public static final ForgeConfigSpec.BooleanValue gearBreaksPermanently;
        public static final ForgeConfigSpec.ConfigValue<Tiers> dummyToolTier;
        public static final ForgeConfigSpec.ConfigValue<ArmorMaterials> dummyArmorMaterial;
        public static final ForgeConfigSpec.IntValue prospectorHammerRange;
        public static final ForgeConfigSpec.IntValue sawRecursionDepth;
        public static final ForgeConfigSpec.BooleanValue upgradesInAnvilOnly;
        public static final ForgeConfigSpec.BooleanValue destroySwappedParts;
        private static final Map<ItemStat, ForgeConfigSpec.DoubleValue> statMultipliers = new HashMap<>();
        // Other items
        public static final ForgeConfigSpec.IntValue netherwoodCharcoalBurnTime;
        // World
        public static final ForgeConfigSpec.IntValue azureSilverCount;
        public static final ForgeConfigSpec.IntValue titaniteCount;
        public static final ForgeConfigSpec.IntValue crimsonIronCount;
        // Debug
        public static final ForgeConfigSpec.BooleanValue extraPartAndTraitLogging;
        public static final ForgeConfigSpec.BooleanValue statsDebugLogging;
        public static final ForgeConfigSpec.BooleanValue modelAndTextureLogging;
        public static final ForgeConfigSpec.BooleanValue worldGenLogging;
        // Other
        public static final ForgeConfigSpec.BooleanValue showWipText;

        static {
            ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

            {
                builder.push("item");

                {
                    builder.comment("Blueprint and template settings");
                    builder.push("blueprint");
                    blueprintTypes = builder
                            .comment("Allowed blueprint types. Valid values are: BOTH, BLUEPRINT, and TEMPLATE")
                            .defineEnum("typesAllowed", BlueprintType.BOTH);
                    spawnWithStarterBlueprints = builder
                            .comment("When joining a new world, should players be given a blueprint package?",
                                    "The blueprint package gives some blueprints when used (right-click).",
                                    "To change what is given, override the starter_blueprints loot table.")
                            .define("spawnWithStarterBlueprints", true);
                    builder.pop();
                }

                netherwoodCharcoalBurnTime = builder
                        .comment("Burn time of netherwood charcoal, in ticks. Vanilla charcoal is 1600.")
                        .defineInRange("netherwood_charcoal.burn_time", 2400, 0, Integer.MAX_VALUE);

                builder.pop();
            }
            {
                builder.comment("Settings for nerfed items.",
                        "You can give items reduced durability to encourage use of Silent Gear tools.",
                        "Changes require a restart!");
                builder.push("nerfedItems");
                nerfedItemsEnabled = builder
                        .comment("Enable this feature. If false, the other settings in this category are ignored.")
                        .define("enabled", false);
                nerfedItemDurabilityMulti = builder
                        .comment("Multiplies max durability by this value. If the result would be zero, a value of 1 is assigned.")
                        .defineInRange("durabilityMultiplier", 0.05, 0, 1);
                nerfedItemHarvestSpeedMulti = builder
                        .comment("Multiplies harvest speed by this value.")
                        .defineInRange("harvestSpeedMultiplier", 0.5, 0, 1);
                nerfedItems = builder
                        .comment("These items will have reduced durability")
                        .defineList("items", NerfedGear.DEFAULT_ITEMS, Config::isResourceLocation);
                builder.pop();
            }
            {
                builder.comment("Settings for sinew drops");
                builder.push("sinew");

                sinewDropRate = builder
                        .comment("Drop rate of sinew (chance out of 1)")
                        .defineInRange("dropRate", 0.2, 0, 1);
                sinewAnimals = builder
                        .comment("These entities can drop sinew when killed.")
                        .defineList("dropsFrom",
                                ImmutableList.of(
                                        "minecraft:cow",
                                        "minecraft:pig",
                                        "minecraft:sheep"
                                ),
                                Config::isResourceLocation);
                builder.pop();
            }
            {
                builder.comment("Settings for gear (tools, weapons, and armor)");
                builder.push("gear");

                allowLegacyMaterialMixing = builder
                        .comment("Allow parts to be crafted with mixed materials in a crafting grid, like earlier versions.",
                                "In 1.17, mixing is normally only allowed in compound-crafting blocks.")
                        .define("allowLegacyMaterialMixing", false);

                allowConversionRecipes = builder
                        .comment("If set to false all conversion recipes (type 'silentgear:conversion') will be disabled",
                                "An example of a conversion recipe is placing a vanilla stone pickaxe into a crafting grid to make a Silent Gear stone pickaxe",
                                "Note: This also affects conversion recipes added by data packs and other mods")
                        .define("allowConversionRecipes", true);

                sendGearBrokenMessage = builder
                        .comment("Displays a message in chat, notifying the player that an item broke and hinting that its parts can be replaced")
                        .define("sendBrokenMessage", true);

                damageFactorLevels = builder
                        .comment("How frequently gear will recalcute stats as damaged",
                                "Higher numbers will cause more recalculations, allowing traits to update stat values more often")
                        .defineInRange("damageFactorLevels", 10, 1, Integer.MAX_VALUE);

                gearBreaksPermanently = builder
                        .comment("If true, gear breaks permanently, like vanilla tools and armor")
                        .define("breaksPermanently", false);

                dummyToolTier = builder
                        .comment("The item tier assigned to gear tool items.",
                                "Leave this alone unless you are trying to work around mod compatibility issues!",
                                "Normally, this value is not used for anything. But some mods mistakenly check it.")
                        .define("dummyToolTier", GearHelper.DEFAULT_DUMMY_TIER);

                dummyArmorMaterial = builder
                        .comment("The armor material assigned to the gear armor items.",
                                "Leave this alone unless you are trying to work around mod compatibility issues!",
                                "Normally, this value is not used for anything. But some mods mistakenly check it.")
                        .define("dummyArmorMaterial", GearHelper.DEFAULT_DUMMY_ARMOR_MATERIAL);

                {
                    builder.push("enchanting");
                    forceRemoveEnchantments = builder
                            .comment("Forcibly remove all enchantments from gear items. Enchantments added by traits will not be removed.",
                                    "Enchantments will be removed during stat recalculations, so items not in a player's inventory will not be affected.")
                            .define("forceRemoveEnchantments", false);
                    builder.pop();
                }
                {
                    builder.push("prospector_hammer");
                    prospectorHammerRange = builder
                            .comment("The range in blocks the prospector hammer will search for blocks of interest")
                            .defineInRange("range", 16, 0, 64);
                    builder.pop();
                }
                {
                    builder.push("saw");
                    sawRecursionDepth = builder
                            .comment("Caps how far the saw can look for blocks when chopping down trees. Try decreasing this if you get stack overflow exceptions.",
                                    "Increasing this value is allowed, but not recommended unless you know what you are doing.")
                            .defineInRange("recursionDepth", 200, 0, Integer.MAX_VALUE);
                    builder.pop();
                }
                {
                    builder.comment("Settings for AOE tools (hammer, excavator)",
                            "Match modes determine what blocks are considered similar enough to be mined together.",
                            "LOOSE: Break anything (you probably do not want this)",
                            "MODERATE: Break anything with the same harvest level",
                            "STRICT: Break only the exact same block");
                    builder.push("aoeTool");
                    matchModeStandard = builder
                            .comment("Match mode for most blocks")
                            .defineEnum("matchMode.standard", IAoeTool.MatchMode.MODERATE);
                    matchModeOres = builder
                            .comment("Match mode for ore blocks (anything in the forge:ores block tag)")
                            .defineEnum("matchMode.ores", IAoeTool.MatchMode.STRICT);
                    builder.pop();
                }
                {
                    builder.push("upgrades");
                    upgradesInAnvilOnly = builder
                            .comment("If true, upgrade parts may only be applied in an anvil.")
                            .define("applyInAnvilOnly", false);
                    destroySwappedParts = builder
                            .comment("If true, parts that are replaced (swapped out) of a gear item are not returned to the player and are instead destroyed.")
                            .comment("This applies to the recipe where placing a gear item and a part into a crafting grid will swap out the part.")
                            .define("destroySwappedParts", false);
                    builder.pop();
                }
                {
                    builder.comment("Multipliers for stats on all gear. This allows the stats on all items to be increased or decreased",
                            "without overriding every single file.");
                    builder.push("statMultipliers");

                    // FIXME: Does not work, called too early
                    /*ItemStats.getRegistry().getValues().forEach(stat -> {
                        ResourceLocation name = Objects.requireNonNull(stat.getRegistryName());
                        String key = name.getNamespace() + "." + name.getPath();
                        ForgeConfigSpec.DoubleValue config = builder
                                .defineInRange(key, 1, 0, Double.MAX_VALUE);
                        statMultipliers.put(stat, config);
                    });*/
                    builder.pop();
                }
                builder.pop();
            }

            {
                builder.comment("World generation options (REQUIRES GAME RESTART)");
                builder.push("world");
                azureSilverCount = builder
                        .comment("Veins of azure silver ore per chunk")
                        .defineInRange("azureSilver.count", 8, 0, 1000);
                titaniteCount = builder
                        .comment("Attempts to place individual titanite ore blocks per chunk.")
                        .defineInRange("titanite.count", 6, 0, 1000);
                crimsonIronCount = builder
                        .comment("Veins of crimson iron ore per chunk")
                        .defineInRange("crimsonIron.count", 14, 0, 1000);
                builder.pop();
            }

            extraPartAndTraitLogging = builder
                    .comment("Log additional information related to loading and synchronizing gear parts and traits.",
                            "This might help track down more obscure issues.")
                    .define("debug.logging.extraPartAndTraitInfo", false);

            statsDebugLogging = builder
                    .comment("Log stat calculations in the debug.log every time gear stats are recalculated")
                    .define("debug.logging.stats", true);

            modelAndTextureLogging = builder
                    .comment("Log information on construction of gear and part models, as well as textures they attempt to load.",
                            "This is intended to help find and fix rare issues that some users are experiencing.")
                    .define("debug.logging.modelAndTexture", false);

            worldGenLogging = builder
                    .comment("Log details about certain features being adding to biomes and other world generator details")
                    .define("debug.logging.worldGen", true);

            // Other random stuff
            showWipText = builder
                    .comment("Shows a \"WIP\" (work in progress) label in the tooltip of certain unfinished, but usable blocks and items")
                    .comment("Set to false to remove the text from tooltips")
                    .define("other.showWipText", true);

            spec = builder.build();
        }

        private Common() {}

        public static boolean isLoaded() {
            return spec.isLoaded();
        }

        public static float getStatWithMultiplier(ItemStat stat, float value) {
            if (statMultipliers.containsKey(stat))
                return statMultipliers.get(stat).get().floatValue() * value;
            return value;
        }

        @SuppressWarnings("TypeMayBeWeakened")
        public static boolean isNerfedItem(Item item) {
            return nerfedItemsEnabled.get() && isThingInList(item, nerfedItems);
        }

        public static boolean isSinewAnimal(LivingEntity entity) {
            return isThingInList(entity.getType(), sinewAnimals);
        }

        private static boolean isThingInList(IForgeRegistryEntry<?> thing, ForgeConfigSpec.ConfigValue<List<? extends String>> list) {
            ResourceLocation name = thing.getRegistryName();
            for (String str : list.get()) {
                ResourceLocation fromList = ResourceLocation.tryParse(str);
                if (fromList != null && fromList.equals(name)) {
                    return true;
                }
            }
            return false;
        }
    }

    private static boolean isResourceLocation(Object o) {
        return o instanceof String && ResourceLocation.tryParse((String) o) != null;
    }

    public static final class Client {
        static final ForgeConfigSpec spec;

        public static final ForgeConfigSpec.BooleanValue allowEnchantedEffect;
        public static final ForgeConfigSpec.BooleanValue playKachinkSound;
        //public static final ForgeConfigSpec.BooleanValue useLiteModels;
        //Tooltip
        public static final ForgeConfigSpec.BooleanValue showMaterialTooltips;
        public static final ForgeConfigSpec.BooleanValue showPartTooltips;
        public static final ForgeConfigSpec.BooleanValue vanillaStyleTooltips;
        public static final ForgeConfigSpec.BooleanValue showJeiHints;

        static {
            ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

            allowEnchantedEffect = builder
                    .comment("Allow gear items to have the 'enchanted glow' effect. Set to 'false' to disable the effect.",
                            "The way vanilla handles the effect is bugged, and it is recommended to disable this until it can be fixed",
                            "The bug is not harmful and some like the way the overpowered effect looks")
                    .define("gear.allowEnchantedEffect", false);
            playKachinkSound = builder
                    .comment("Plays a sped-up 'item breaking' sound when an item's stats are recalculated due to durability loss")
                    .define("gear.playKachinkSound", true);
            /*useLiteModels = builder
                    .comment("Use 'lite' gear models. These should be easier on some systems, but do not allow unique textures for different materials.",
                            "Currently, this option has no effect, as the normal model system is not working yet (lite models are used)")
                    .define("gear.useLiteModels", false);*/

            showMaterialTooltips = builder
                    .comment("Show SGear Material tooltips on items that can be used as materials.")
                    .define("tooltip.showMaterialTooltips", true);

            showPartTooltips = builder
                    .comment("Show tooltips on parts and items that can be used as parts.")
                    .define("tooltip.showPartTooltips", true);

            vanillaStyleTooltips = builder
                    .comment("Tooltips are replaced with a simpler variant similar to vanilla and contains about as much information.")
                    .define("tooltip.vanillaStyleTooltips", false);

            showJeiHints = builder
                    .comment("Show tooltips on certain items (like blueprints) reminding the player of JEI functionality,",
                            "or encouraging the player to install JEI (Just Enough Items) if the mod is missing.")
                    .define("tooltip.jeiHints", true);

            spec = builder.build();
        }

        private Client() {}
    }

    private Config() {}

    public static void init() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Common.spec);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Client.spec);
    }
}
