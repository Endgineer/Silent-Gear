package net.silentchaos512.gear.data;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.silentchaos512.gear.SilentGear;
import net.silentchaos512.gear.event.GearEvents;
import net.silentchaos512.gear.gear.material.LazyMaterialInstance;
import net.silentchaos512.gear.gear.part.LazyPartData;
import net.silentchaos512.gear.gear.trait.DurabilityTrait;
import net.silentchaos512.gear.init.ModBlocks;
import net.silentchaos512.gear.init.ModItems;
import net.silentchaos512.gear.item.CraftingItems;
import net.silentchaos512.gear.util.Const;
import net.silentchaos512.gear.util.GearData;
import net.silentchaos512.gear.util.GearHelper;
import net.silentchaos512.lib.advancements.GenericIntTrigger;
import net.silentchaos512.lib.util.NameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Set;
import java.util.function.Consumer;

public class ModAdvancementProvider implements DataProvider {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
    private final DataGenerator generator;

    public ModAdvancementProvider(DataGenerator generator) {
        this.generator = generator;
    }

    @Override
    public void run(HashCache cache) {
        Path path = this.generator.getOutputFolder();
        Set<ResourceLocation> set = Sets.newHashSet();
        //noinspection OverlyLongLambda
        Consumer<Advancement> consumer = (p_204017_3_) -> {
            if (!set.add(p_204017_3_.getId())) {
                throw new IllegalStateException("Duplicate advancement " + p_204017_3_.getId());
            } else {
                Path path1 = getPath(path, p_204017_3_);

                try {
                    DataProvider.save(GSON, cache, p_204017_3_.deconstruct().serializeToJson(), path1);
                } catch (IOException ioexception) {
                    LOGGER.error("Couldn't save advancement {}", path1, ioexception);
                }

            }
        };

        new Advancements().accept(consumer);
    }

    @Override
    public String getName() {
        return "Silent Gear - Advancements";
    }

    private static Path getPath(Path pathIn, Advancement advancementIn) {
        return pathIn.resolve("data/" + advancementIn.getId().getNamespace() + "/advancements/" + advancementIn.getId().getPath() + ".json");
    }

    private static class Advancements implements Consumer<Consumer<Advancement>> {
        @SuppressWarnings({"unused", "OverlyLongMethod"})
        @Override
        public void accept(Consumer<Advancement> consumer) {
            ItemStack rootIcon = new ItemStack(ModItems.PICKAXE);
            
            Advancement root = Advancement.Builder.advancement()
                    .display(rootIcon, title("root"), description("root"), new ResourceLocation("minecraft:textures/gui/advancements/backgrounds/adventure.png"), FrameType.TASK, false, false, false)
                    .addCriterion("get_item", getItem(Items.CRAFTING_TABLE))
                    .save(consumer, id("root"));

            Advancement overworldPlants = Advancement.Builder.advancement()
                    .parent(root)
                    .display(CraftingItems.FLAX_FIBER, title("overworld_plants"), description("overworld_plants"), null, FrameType.TASK, true, true, false)
                    .addCriterion("flax_seeds", getItem(ModItems.FLAX_SEEDS))
                    .addCriterion("flax_fibers", getItem(CraftingItems.FLAX_FIBER))
                    .requirements(RequirementsStrategy.AND)
                    .save(consumer, id("overworld_plants"));
            Advancement kachink1 = Advancement.Builder.advancement()
                    .parent(root)
                    .display(Items.IRON_NUGGET, title("kachink1"), description("kachink1"), null, FrameType.TASK, true, true, false)
                    .addCriterion("kachink", genericInt(GearHelper.DAMAGE_FACTOR_CHANGE, 1))
                    .save(consumer, id("kachink1"));
            Advancement kachink2 = Advancement.Builder.advancement()
                    .parent(kachink1)
                    .display(CraftingItems.DIAMOND_SHARD, title("kachink2"), description("kachink2"), null, FrameType.TASK, true, true, false)
                    .addCriterion("kachink", genericInt(DurabilityTrait.TRIGGER_BRITTLE, 1))
                    .save(consumer, id("kachink2"));

            Advancement crudeTool = Advancement.Builder.advancement()
                    .parent(root)
                    .display(CraftingItems.ROUGH_ROD, title("crude_tool"), description("crude_tool"), null, FrameType.TASK, true, true, false)
                    .addCriterion("tool_has_rough_rod", genericInt(GearEvents.CRAFTED_WITH_ROUGH_ROD, 1))
                    .save(consumer, id("crude_tool"));
            Advancement survivalTool = Advancement.Builder.advancement()
                    .parent(crudeTool)
                    .display(ModItems.KNIFE, title("survival_tool"), description("survival_tool"), null, FrameType.TASK, true, true, false)
                    .addCriterion("knife", getItem(ModItems.KNIFE))
                    .addCriterion("dagger", getItem(ModItems.DAGGER))
                    .requirements(RequirementsStrategy.OR)
                    .save(consumer, id("survival_tool"));
            Advancement templateBoard = simpleGetItem(consumer, CraftingItems.TEMPLATE_BOARD, survivalTool);

            Advancement blueprintPaper = simpleGetItem(consumer, CraftingItems.BLUEPRINT_PAPER, templateBoard);
            Advancement upgradeBase = simpleGetItem(consumer, CraftingItems.UPGRADE_BASE, templateBoard);

            Advancement blueprintBook = simpleGetItem(consumer, ModItems.BLUEPRINT_BOOK, blueprintPaper);

            Advancement tipUpgrade = simpleGetItem(consumer, ModItems.TIP, ModItems.TIP.get().create(LazyMaterialInstance.of(Const.Materials.EXAMPLE)), upgradeBase, "tip_upgrade");

            //region Gear

            Advancement mixedMaterials = Advancement.Builder.advancement()
                    .parent(blueprintPaper)
                    .display(Items.EMERALD, title("mixed_materials"), description("mixed_materials"), null, FrameType.TASK, true, true, false)
                    .addCriterion("mixed_materials", genericInt(GearEvents.UNIQUE_MAIN_PARTS, 2))
                    .save(consumer, id("mixed_materials"));

            Advancement armor = Advancement.Builder.advancement()
                    .parent(blueprintPaper)
                    .display(ModItems.HELMET, title("armor"), description("armor"), null, FrameType.TASK, true, true, false)
                    .addCriterion("helmet", getItem(ModItems.HELMET))
                    .addCriterion("chestplate", getItem(ModItems.CHESTPLATE))
                    .addCriterion("leggings", getItem(ModItems.LEGGINGS))
                    .addCriterion("boots", getItem(ModItems.BOOTS))
                    .requirements(RequirementsStrategy.OR)
                    .save(consumer, id("armor"));

            Advancement bow = Advancement.Builder.advancement()
                    .parent(blueprintPaper)
                    .display(ModItems.BOW, title("bow"), description("bow"), null, FrameType.TASK, true, true, false)
                    .addCriterion("get_item", getItem(ModItems.BOW))
                    .save(consumer, id("bow"));
            Advancement standardTools = Advancement.Builder.advancement()
                    .parent(blueprintPaper)
                    .display(ModItems.PICKAXE, title("standard_tools"), description("standard_tools"), null, FrameType.TASK, true, true, false)
                    .addCriterion("pickaxe", getItem(ModItems.PICKAXE))
                    .addCriterion("shovel", getItem(ModItems.SHOVEL))
                    .addCriterion("axe", getItem(ModItems.AXE))
                    .requirements(RequirementsStrategy.AND)
                    .save(consumer, id("standard_tools"));
            Advancement swords = Advancement.Builder.advancement()
                    .parent(blueprintPaper)
                    .display(ModItems.SWORD, title("swords"), description("swords"), null, FrameType.TASK, true, true, false)
                    .addCriterion("sword", getItem(ModItems.SWORD))
                    .addCriterion("katana", getItem(ModItems.KATANA))
                    .addCriterion("machete", getItem(ModItems.MACHETE))
                    .requirements(RequirementsStrategy.AND)
                    .save(consumer, id("swords"));

            Advancement bigJobTools = Advancement.Builder.advancement()
                    .parent(standardTools)
                    .display(ModItems.HAMMER, title("big_job_tools"), description("big_job_tools"), null, FrameType.TASK, true, true, false)
                    .addCriterion("hammer", getItem(ModItems.HAMMER))
                    .addCriterion("excavator", getItem(ModItems.EXCAVATOR))
                    .addCriterion("lumber_axe", getItem(ModItems.SAW))
                    .requirements(RequirementsStrategy.AND)
                    .save(consumer, id("big_job_tools"));

            Advancement crossbow = simpleGetItem(consumer, ModItems.CROSSBOW, bow);

            Advancement mattock = simpleGetItem(consumer, ModItems.MATTOCK, standardTools);

            Advancement sickle = simpleGetItem(consumer, ModItems.SICKLE, mattock);

            //endregion

            //region Nether

            Advancement nether = Advancement.Builder.advancement()
                    .parent(root)
                    .display(Items.OBSIDIAN, title("nether"), description("nether"), null, FrameType.TASK, false, false, false)
                    .addCriterion("entered_nether", ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(Level.NETHER))
                    .save(consumer, id("nether"));

            Advancement netherPlants = Advancement.Builder.advancement()
                    .parent(nether)
                    .display(ModItems.NETHER_BANANA, title("nether_plants"), description("nether_plants"), null, FrameType.TASK, true, true, false)
                    .addCriterion("banana", getItem(ModItems.NETHER_BANANA))
                    .addCriterion("sapling", getItem(ModBlocks.NETHERWOOD_SAPLING))
                    .requirements(RequirementsStrategy.AND)
                    .save(consumer, id("nether_plants"));

            //endregion

            //region The End

            Advancement theEnd = Advancement.Builder.advancement()
                    .parent(nether)
                    .display(Items.END_STONE, title("the_end"), description("the_end"), null, FrameType.TASK, false, false, false)
                    .addCriterion("entered_the_end", ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(Level.END))
                    .save(consumer, id("the_end"));

            //endregion
        }

        private static Advancement simpleGetItem(Consumer<Advancement> consumer, ItemLike item, Advancement parent) {
            return simpleGetItem(consumer, item, parent, NameUtils.fromItem(item).getPath());
        }

        private static Advancement simpleGetItem(Consumer<Advancement> consumer, ItemLike item, Advancement parent, String key) {
            return simpleGetItem(consumer, item, new ItemStack(item), parent, key);
        }

        private static Advancement simpleGetItem(Consumer<Advancement> consumer, ItemLike item, ItemStack icon, Advancement parent, String key) {
            return Advancement.Builder.advancement()
                    .parent(parent)
                    .display(icon, title(key), description(key), null, FrameType.TASK, true, true, false)
                    .addCriterion("get_item", getItem(item))
                    .save(consumer, id(key));
        }

        private static String id(String path) {
            return SilentGear.getId(path).toString();
        }

        private static CriterionTriggerInstance getItem(ItemLike... items) {
            return InventoryChangeTrigger.TriggerInstance.hasItems(items);
        }

        private static CriterionTriggerInstance genericInt(ResourceLocation id, int value) {
            return GenericIntTrigger.Instance.instance(id, value);
        }

        private static Component title(String key) {
            return new TranslatableComponent("advancements.silentgear." + key + ".title");
        }

        private static Component description(String key) {
            return new TranslatableComponent("advancements.silentgear." + key + ".description");
        }
    }
}
