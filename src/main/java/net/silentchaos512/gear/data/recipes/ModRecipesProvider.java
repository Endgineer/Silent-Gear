package net.silentchaos512.gear.data.recipes;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.silentchaos512.gear.SilentGear;
import net.silentchaos512.gear.api.item.GearType;
import net.silentchaos512.gear.api.item.ICoreItem;
import net.silentchaos512.gear.api.part.MaterialGrade;
import net.silentchaos512.gear.api.part.PartType;
import net.silentchaos512.gear.crafting.ingredient.BlueprintIngredient;
import net.silentchaos512.gear.crafting.ingredient.GearPartIngredient;
import net.silentchaos512.gear.crafting.ingredient.PartMaterialIngredient;
import net.silentchaos512.gear.gear.material.LazyMaterialInstance;
import net.silentchaos512.gear.gear.material.MaterialCategories;
import net.silentchaos512.gear.init.*;
import net.silentchaos512.gear.item.CraftingItems;
import net.silentchaos512.gear.item.blueprint.GearBlueprintItem;
import net.silentchaos512.gear.item.gear.GearArmorItem;
import net.silentchaos512.gear.util.DataResource;
import net.silentchaos512.lib.data.recipe.ExtendedShapedRecipeBuilder;
import net.silentchaos512.lib.data.recipe.ExtendedShapelessRecipeBuilder;
import net.silentchaos512.lib.data.recipe.LibRecipeProvider;
import net.silentchaos512.lib.util.NameUtils;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class ModRecipesProvider extends LibRecipeProvider {
    private static final boolean ADD_TEST_RECIPES = false;

    public ModRecipesProvider(DataGenerator generatorIn) {
        super(generatorIn, SilentGear.MOD_ID);
    }

    @Override
    public String getName() {
        return "Silent Gear - Recipes";
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        registerSpecialRecipes(consumer);
        registerCraftingItems(consumer);
        registerBlueprints(consumer);
        registerCompoundParts(consumer);
        registerGear(consumer);
        registerSmithing(consumer);

        if (ADD_TEST_RECIPES) {
            registerTestRecipes(consumer);
        }
    }

    private void registerTestRecipes(Consumer<FinishedRecipe> consumer) {
        shapedBuilder(Items.BUCKET)
                .patternLine("# #")
                .patternLine(" # ")
                .key('#', PartMaterialIngredient.builder(PartType.MAIN)
                        .withMaterial(DataResource.material("copper"))
                        .withGrade(MaterialGrade.A, null).build()
                )
                .build(consumer, modId("graded_mat_test"));
    }

    private void registerSpecialRecipes(Consumer<FinishedRecipe> consumer) {
        special(consumer, (SimpleRecipeSerializer<?>) ModRecipes.SWAP_GEAR_PART.get());
        special(consumer, (SimpleRecipeSerializer<?>) ModRecipes.COMBINE_FRAGMENTS.get());
    }

    private void registerBlueprints(Consumer<FinishedRecipe> consumer) {
        toolBlueprint(consumer, "sword", ModItems.SWORD_BLUEPRINT, ModItems.SWORD_TEMPLATE, "#", "#", "/");
        toolBlueprint(consumer, "katana", ModItems.KATANA_BLUEPRINT, ModItems.KATANA_TEMPLATE, "##", "# ", "/ ");
        toolBlueprint(consumer, "machete", ModItems.MACHETE_BLUEPRINT, ModItems.MACHETE_TEMPLATE, "  #", " ##", "/  ");
        toolBlueprint(consumer, "spear", ModItems.SPEAR_BLUEPRINT, ModItems.SPEAR_TEMPLATE, "#  ", " / ", "  /");
        toolBlueprint(consumer, "knife", ModItems.KNIFE_BLUEPRINT, ModItems.KNIFE_TEMPLATE, " #", "/ ");
        toolBlueprint(consumer, "dagger", ModItems.DAGGER_BLUEPRINT, ModItems.DAGGER_TEMPLATE, "#", "/");
        toolBlueprint(consumer, "pickaxe", ModItems.PICKAXE_BLUEPRINT, ModItems.PICKAXE_TEMPLATE, "###", " / ", " / ");
        toolBlueprint(consumer, "shovel", ModItems.SHOVEL_BLUEPRINT, ModItems.SHOVEL_TEMPLATE, "#", "/", "/");
        toolBlueprint(consumer, "axe", ModItems.AXE_BLUEPRINT, ModItems.AXE_TEMPLATE, "##", "#/", " /");
        toolBlueprint(consumer, "paxel", ModItems.PAXEL_BLUEPRINT, ModItems.PAXEL_TEMPLATE, "###", "#/#", " /#");
        toolBlueprint(consumer, "hammer", ModItems.HAMMER_BLUEPRINT, ModItems.HAMMER_TEMPLATE, "###", "###", " / ");
        toolBlueprint(consumer, "excavator", ModItems.EXCAVATOR_BLUEPRINT, ModItems.EXCAVATOR_TEMPLATE, "# #", "###", " / ");
        toolBlueprint(consumer, "saw", ModItems.SAW_BLUEPRINT, ModItems.SAW_TEMPLATE, "###", "##/", "  /");
        toolBlueprint(consumer, "mattock", ModItems.MATTOCK_BLUEPRINT, ModItems.MATTOCK_TEMPLATE, "## ", "#/#", " / ");
        toolBlueprint(consumer, "prospector_hammer", ModItems.PROSPECTOR_HAMMER_BLUEPRINT, ModItems.PROSPECTOR_HAMMER_TEMPLATE,
                Ingredient.of(Tags.Items.INGOTS_IRON), "##", " /", " @");
        toolBlueprint(consumer, "sickle", ModItems.SICKLE_BLUEPRINT, ModItems.SICKLE_TEMPLATE, " #", "##", "/ ");
        toolBlueprint(consumer, "shears", ModItems.SHEARS_BLUEPRINT, ModItems.SHEARS_TEMPLATE, " #", "#/");
        toolBlueprint(consumer, "fishing_rod", ModItems.FISHING_ROD_BLUEPRINT, ModItems.FISHING_ROD_TEMPLATE, "  /", " /#", "/ #");
        toolBlueprint(consumer, "bow", ModItems.BOW_BLUEPRINT, ModItems.BOW_TEMPLATE, " #/", "# /", " #/");
        toolBlueprint(consumer, "crossbow", ModItems.CROSSBOW_BLUEPRINT, ModItems.CROSSBOW_TEMPLATE, "/#/", "###", " / ");
        toolBlueprint(consumer, "slingshot", ModItems.SLINGSHOT_BLUEPRINT, ModItems.SLINGSHOT_TEMPLATE, "# #", " / ", " / ");
        toolBlueprint(consumer, "shield", ModItems.SHIELD_BLUEPRINT, ModItems.SHIELD_TEMPLATE, "# #", "///", " # ");
        toolBlueprint(consumer, "arrow", ModItems.ARROW_BLUEPRINT, ModItems.ARROW_TEMPLATE, Ingredient.of(Tags.Items.FEATHERS), "#", "/", "@");
        armorBlueprint(consumer, "helmet", ModItems.HELMET_BLUEPRINT, ModItems.HELMET_TEMPLATE, "###", "# #");
        armorBlueprint(consumer, "chestplate", ModItems.CHESTPLATE_BLUEPRINT, ModItems.CHESTPLATE_TEMPLATE, "# #", "###", "###");
        armorBlueprint(consumer, "leggings", ModItems.LEGGINGS_BLUEPRINT, ModItems.LEGGINGS_TEMPLATE, "###", "# #", "# #");
        armorBlueprint(consumer, "boots", ModItems.BOOTS_BLUEPRINT, ModItems.BOOTS_TEMPLATE, "# #", "# #");

        shapedBuilder(ModItems.TRIDENT_BLUEPRINT)
                .key('#', ModTags.Items.BLUEPRINT_PAPER)
                .key('H', Items.HEART_OF_THE_SEA)
                .key('T', Items.TRIDENT)
                .patternLine("#H#")
                .patternLine("#T#")
                .patternLine(" # ")
                .build(consumer);

        ShapedRecipeBuilder.shaped(ModItems.ELYTRA_BLUEPRINT)
                .define('#', ModTags.Items.BLUEPRINT_PAPER)
                .define('/', Tags.Items.INGOTS_NETHERITE)
                .define('e', Items.ELYTRA)
                .define('p', Items.PHANTOM_MEMBRANE)
                .pattern("/e/")
                .pattern("p#p")
                .pattern("p p")
                .unlockedBy("has_item", has(ModTags.Items.BLUEPRINT_PAPER))
                .save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.ELYTRA_TEMPLATE)
                .define('#', ModTags.Items.TEMPLATE_BOARDS)
                .define('/', Tags.Items.INGOTS_NETHERITE)
                .define('e', Items.ELYTRA)
                .define('p', Items.PHANTOM_MEMBRANE)
                .pattern("/e/")
                .pattern("p#p")
                .pattern("p p")
                .unlockedBy("has_item", has(ModTags.Items.TEMPLATE_BOARDS))
                .save(consumer);

        // Curio blueprints
        ShapedRecipeBuilder.shaped(ModItems.RING_BLUEPRINT)
                .group("silentgear:blueprints/ring")
                .define('#', ModTags.Items.BLUEPRINT_PAPER)
                .define('/', PartMaterialIngredient.of(PartType.MAIN, GearType.CURIO, MaterialCategories.METAL))
                .pattern(" #/")
                .pattern("# #")
                .pattern("/# ")
                .unlockedBy("has_item", has(ModTags.Items.BLUEPRINT_PAPER))
                .save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.RING_BLUEPRINT)
                .group("silentgear:blueprints/ring")
                .define('#', ModTags.Items.BLUEPRINT_PAPER)
                .define('/', Tags.Items.INGOTS_GOLD)
                .pattern(" #/")
                .pattern("# #")
                .pattern("/# ")
                .unlockedBy("has_item", has(ModTags.Items.BLUEPRINT_PAPER))
                .save(consumer, SilentGear.getId("ring_blueprint_alt"));
        ShapedRecipeBuilder.shaped(ModItems.RING_TEMPLATE)
                .group("silentgear:blueprints/ring")
                .define('#', ModTags.Items.TEMPLATE_BOARDS)
                .define('/', PartMaterialIngredient.of(PartType.MAIN, GearType.CURIO, MaterialCategories.METAL))
                .pattern(" #/")
                .pattern("# #")
                .pattern("/# ")
                .unlockedBy("has_item", has(ModTags.Items.TEMPLATE_BOARDS))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.BRACELET_BLUEPRINT)
                .group("silentgear:blueprints/bracelet")
                .define('#', ModTags.Items.BLUEPRINT_PAPER)
                .define('/', PartMaterialIngredient.of(PartType.MAIN, GearType.CURIO, MaterialCategories.METAL))
                .pattern("###")
                .pattern("# #")
                .pattern("/#/")
                .unlockedBy("has_item", has(ModTags.Items.BLUEPRINT_PAPER))
                .save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.BRACELET_BLUEPRINT)
                .group("silentgear:blueprints/bracelet")
                .define('#', ModTags.Items.BLUEPRINT_PAPER)
                .define('/', Tags.Items.INGOTS_GOLD)
                .pattern("###")
                .pattern("# #")
                .pattern("/#/")
                .unlockedBy("has_item", has(ModTags.Items.BLUEPRINT_PAPER))
                .save(consumer, SilentGear.getId("bracelet_blueprint_alt"));
        ShapedRecipeBuilder.shaped(ModItems.BRACELET_TEMPLATE)
                .group("silentgear:blueprints/bracelet")
                .define('#', ModTags.Items.TEMPLATE_BOARDS)
                .define('/', PartMaterialIngredient.of(PartType.MAIN, GearType.CURIO, MaterialCategories.METAL))
                .pattern("###")
                .pattern("# #")
                .pattern("/#/")
                .unlockedBy("has_item", has(ModTags.Items.TEMPLATE_BOARDS))
                .save(consumer);

        // Part blueprints
        ShapedRecipeBuilder.shaped(ModItems.JEWELER_TOOLS)
                .pattern("  p")
                .pattern("d#s")
                .pattern("ips")
                .define('p', ItemTags.PLANKS)
                .define('d', Tags.Items.GEMS_DIAMOND)
                .define('#', ModTags.Items.BLUEPRINT_PAPER)
                .define('s', Tags.Items.RODS_WOODEN)
                .define('i', Tags.Items.INGOTS_IRON)
                .unlockedBy("has_item", has(ModTags.Items.BLUEPRINT_PAPER))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.BINDING_BLUEPRINT)
                .group("silentgear:blueprints/binding")
                .requires(Ingredient.of(ModTags.Items.BLUEPRINT_PAPER), 1)
                .requires(PartMaterialIngredient.of(PartType.BINDING, GearType.TOOL), 2)
                .unlockedBy("has_item", has(ModTags.Items.BLUEPRINT_PAPER))
                .save(consumer);
        ExtendedShapelessRecipeBuilder.vanillaBuilder(ModItems.BINDING_BLUEPRINT)
                .setGroup("silentgear:blueprints/binding")
                .addIngredient(ModTags.Items.BLUEPRINT_PAPER)
                .addIngredient(Tags.Items.STRING)
                .addCriterion("has_item", has(ModTags.Items.BLUEPRINT_PAPER))
                .build(consumer, SilentGear.getId("binding_blueprint_alt"));
        ShapelessRecipeBuilder.shapeless(ModItems.BINDING_TEMPLATE)
                .group("silentgear:blueprints/binding")
                .requires(Ingredient.of(ModTags.Items.TEMPLATE_BOARDS), 1)
                .requires(PartMaterialIngredient.of(PartType.BINDING, GearType.TOOL), 2)
                .unlockedBy("has_item", has(ModTags.Items.TEMPLATE_BOARDS))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.CORD_BLUEPRINT)
                .group("silentgear:blueprints/cord")
                .define('#', ModTags.Items.BLUEPRINT_PAPER)
                .define('/', PartMaterialIngredient.of(PartType.CORD, GearType.TOOL))
                .pattern("#/")
                .pattern("#/")
                .pattern("#/")
                .unlockedBy("has_item", has(ModTags.Items.BLUEPRINT_PAPER))
                .save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.CORD_BLUEPRINT)
                .group("silentgear:blueprints/cord")
                .define('#', ModTags.Items.BLUEPRINT_PAPER)
                .define('/', Tags.Items.STRING)
                .pattern("#/")
                .pattern("#/")
                .pattern("#/")
                .unlockedBy("has_item", has(ModTags.Items.BLUEPRINT_PAPER))
                .save(consumer, SilentGear.getId("cord_blueprint_alt"));
        ShapedRecipeBuilder.shaped(ModItems.CORD_TEMPLATE)
                .group("silentgear:blueprints/cord")
                .define('#', ModTags.Items.TEMPLATE_BOARDS)
                .define('/', PartMaterialIngredient.of(PartType.CORD, GearType.TOOL))
                .pattern("#/")
                .pattern("#/")
                .pattern("#/")
                .unlockedBy("has_item", has(ModTags.Items.TEMPLATE_BOARDS))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.FLETCHING_BLUEPRINT)
                .group("silentgear:blueprints/fletching")
                .requires(Ingredient.of(ModTags.Items.BLUEPRINT_PAPER), 2)
                .requires(Tags.Items.FEATHERS)
                .unlockedBy("has_item", has(ModTags.Items.BLUEPRINT_PAPER))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(ModItems.FLETCHING_TEMPLATE)
                .group("silentgear:blueprints/fletching")
                .requires(Ingredient.of(ModTags.Items.TEMPLATE_BOARDS), 2)
                .requires(Tags.Items.FEATHERS)
                .unlockedBy("has_item", has(ModTags.Items.TEMPLATE_BOARDS))
                .save(consumer);

        ExtendedShapelessRecipeBuilder.vanillaBuilder(ModItems.GRIP_BLUEPRINT)
                .setGroup("silentgear:blueprints/grip")
                .addIngredient(ModTags.Items.BLUEPRINT_PAPER, 2)
                .addIngredient(PartMaterialIngredient.of(PartType.GRIP, GearType.TOOL))
                .addCriterion("has_item", has(ModTags.Items.BLUEPRINT_PAPER))
                .build(consumer);
        ExtendedShapelessRecipeBuilder.vanillaBuilder(ModItems.GRIP_BLUEPRINT)
                .setGroup("silentgear:blueprints/grip")
                .addIngredient(ModTags.Items.BLUEPRINT_PAPER, 2)
                .addIngredient(ItemTags.WOOL)
                .addCriterion("has_item", has(ModTags.Items.BLUEPRINT_PAPER))
                .build(consumer, SilentGear.getId("grip_blueprint_alt"));
        ExtendedShapelessRecipeBuilder.vanillaBuilder(ModItems.GRIP_TEMPLATE)
                .setGroup("silentgear:blueprints/grip")
                .addIngredient(ModTags.Items.TEMPLATE_BOARDS, 2)
                .addIngredient(PartMaterialIngredient.of(PartType.GRIP, GearType.TOOL))
                .addCriterion("has_item", has(ModTags.Items.TEMPLATE_BOARDS))
                .build(consumer);

        ExtendedShapelessRecipeBuilder.vanillaBuilder(ModItems.LINING_BLUEPRINT)
                .setGroup("silentgear:blueprints/lining")
                .addIngredient(ModTags.Items.BLUEPRINT_PAPER, 3)
                .addIngredient(ItemTags.WOOL, 2)
                .addIngredient(Tags.Items.STRING, 2)
                .addCriterion("has_item", has(ModTags.Items.BLUEPRINT_PAPER))
                .build(consumer);
        ExtendedShapelessRecipeBuilder.vanillaBuilder(ModItems.LINING_TEMPLATE)
                .setGroup("silentgear:blueprints/lining")
                .addIngredient(ModTags.Items.TEMPLATE_BOARDS, 3)
                .addIngredient(ItemTags.WOOL, 2)
                .addIngredient(Tags.Items.STRING, 2)
                .addCriterion("has_item", has(ModTags.Items.TEMPLATE_BOARDS))
                .build(consumer);

        ShapedRecipeBuilder.shaped(ModItems.ROD_BLUEPRINT)
                .group("silentgear:blueprints/rod")
                .define('#', ModTags.Items.BLUEPRINT_PAPER)
                .define('/', Tags.Items.RODS_WOODEN)
                .pattern("#/")
                .pattern("#/")
                .unlockedBy("has_item", has(ModTags.Items.BLUEPRINT_PAPER))
                .save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.ROD_TEMPLATE)
                .group("silentgear:blueprints/rod")
                .define('#', ModTags.Items.TEMPLATE_BOARDS)
                .define('/', Tags.Items.RODS_WOODEN)
                .pattern("#/")
                .pattern("#/")
                .unlockedBy("has_item", has(ModTags.Items.TEMPLATE_BOARDS))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.TIP_BLUEPRINT)
                .group("silentgear:blueprints/tip")
                .requires(Ingredient.of(ModTags.Items.BLUEPRINT_PAPER), 2)
                .requires(ModTags.Items.PAPER)
                .requires(Tags.Items.STONE)
                .unlockedBy("has_item", has(ModTags.Items.BLUEPRINT_PAPER))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(ModItems.TIP_TEMPLATE)
                .group("silentgear:blueprints/tip")
                .requires(Ingredient.of(ModTags.Items.TEMPLATE_BOARDS), 2)
                .requires(ModTags.Items.PAPER)
                .requires(Tags.Items.STONE)
                .unlockedBy("has_item", has(ModTags.Items.TEMPLATE_BOARDS))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.COATING_BLUEPRINT)
                .group("silentgear:blueprints/coating")
                .requires(Ingredient.of(ModTags.Items.BLUEPRINT_PAPER), 4)
                .requires(Tags.Items.GEMS_DIAMOND)
                .requires(Tags.Items.GEMS_EMERALD)
                .unlockedBy("has_item", has(ModTags.Items.BLUEPRINT_PAPER))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(ModItems.COATING_TEMPLATE)
                .group("silentgear:blueprints/coating")
                .requires(Ingredient.of(ModTags.Items.TEMPLATE_BOARDS), 4)
                .requires(Tags.Items.GEMS_DIAMOND)
                .requires(Tags.Items.GEMS_EMERALD)
                .unlockedBy("has_item", has(ModTags.Items.TEMPLATE_BOARDS))
                .save(consumer);

        ExtendedShapelessRecipeBuilder.vanillaBuilder(ModItems.BLUEPRINT_BOOK)
                .addIngredient(ModTags.Items.BLUEPRINT_PAPER, 3)
                .addIngredient(Items.LEATHER)
                .build(consumer);
    }

    private void registerCompoundParts(Consumer<FinishedRecipe> consumer) {
        ExtendedShapelessRecipeBuilder.builder(ModRecipes.COMPOUND_PART.get(), ModItems.ADORNMENT)
                .addIngredient(BlueprintIngredient.of(ModItems.JEWELER_TOOLS.get()))
                .addIngredient(PartMaterialIngredient.of(PartType.ADORNMENT))
                .build(consumer, SilentGear.getId("part/adornment"));

        ExtendedShapelessRecipeBuilder.builder(ModRecipes.COMPOUND_PART.get(), ModItems.ROD, 4)
                .addIngredient(BlueprintIngredient.of(ModItems.ROD_BLUEPRINT.get()))
                .addIngredient(PartMaterialIngredient.of(PartType.ROD), 2)
                .build(consumer, SilentGear.getId("part/rod"));

        ExtendedShapelessRecipeBuilder.builder(ModRecipes.COMPOUND_PART.get(), ModItems.LONG_ROD, 2)
                .addIngredient(BlueprintIngredient.of(ModItems.ROD_BLUEPRINT.get()))
                .addIngredient(PartMaterialIngredient.of(PartType.ROD), 3)
                .build(consumer, SilentGear.getId("part/long_rod"));

        ExtendedShapelessRecipeBuilder.builder(ModRecipes.COMPOUND_PART.get(), ModItems.BINDING, 1)
                .addIngredient(BlueprintIngredient.of(ModItems.BINDING_BLUEPRINT.get()))
                .addIngredient(PartMaterialIngredient.of(PartType.BINDING))
                .build(consumer, SilentGear.getId("part/binding"));

        ExtendedShapelessRecipeBuilder.builder(ModRecipes.COMPOUND_PART.get(), ModItems.BINDING, 2)
                .addIngredient(BlueprintIngredient.of(ModItems.BINDING_BLUEPRINT.get()))
                .addIngredient(PartMaterialIngredient.of(PartType.BINDING), 2)
                .build(consumer, SilentGear.getId("part/binding2"));

        ExtendedShapelessRecipeBuilder.builder(ModRecipes.COMPOUND_PART.get(), ModItems.CORD, 1)
                .addIngredient(BlueprintIngredient.of(ModItems.CORD_BLUEPRINT.get()))
                .addIngredient(PartMaterialIngredient.of(PartType.CORD), 3)
                .build(consumer, SilentGear.getId("part/cord"));

        ExtendedShapelessRecipeBuilder.builder(ModRecipes.COMPOUND_PART.get(), ModItems.FLETCHING, 1)
                .addIngredient(BlueprintIngredient.of(ModItems.FLETCHING_BLUEPRINT.get()))
                .addIngredient(PartMaterialIngredient.of(PartType.FLETCHING), 1)
                .build(consumer, SilentGear.getId("part/fletching"));

        ExtendedShapelessRecipeBuilder.builder(ModRecipes.COMPOUND_PART.get(), ModItems.GRIP, 1)
                .addIngredient(BlueprintIngredient.of(ModItems.GRIP_BLUEPRINT.get()))
                .addIngredient(PartMaterialIngredient.of(PartType.GRIP))
                .build(consumer, SilentGear.getId("part/grip"));

        ExtendedShapelessRecipeBuilder.builder(ModRecipes.COMPOUND_PART.get(), ModItems.GRIP, 2)
                .addIngredient(BlueprintIngredient.of(ModItems.GRIP_BLUEPRINT.get()))
                .addIngredient(PartMaterialIngredient.of(PartType.GRIP), 2)
                .build(consumer, SilentGear.getId("part/grip2"));

        ExtendedShapelessRecipeBuilder.builder(ModRecipes.COMPOUND_PART.get(), ModItems.LINING, 1)
                .addIngredient(BlueprintIngredient.of(ModItems.LINING_BLUEPRINT.get()))
                .addIngredient(PartMaterialIngredient.of(PartType.LINING))
                .build(consumer, SilentGear.getId("part/lining"));

        ExtendedShapelessRecipeBuilder.builder(ModRecipes.COMPOUND_PART.get(), ModItems.TIP, 1)
                .addIngredient(BlueprintIngredient.of(ModItems.TIP_BLUEPRINT.get()))
                .addIngredient(PartMaterialIngredient.of(PartType.TIP))
                .build(consumer, SilentGear.getId("part/tip"));

        ExtendedShapelessRecipeBuilder.builder(ModRecipes.COMPOUND_PART.get(), ModItems.TIP, 1)
                .addIngredient(BlueprintIngredient.of(ModItems.TIP_BLUEPRINT.get()))
                .addIngredient(CraftingItems.UPGRADE_BASE)
                .addIngredient(PartMaterialIngredient.of(PartType.TIP))
                .build(consumer, SilentGear.getId("part/tip_alt"));

        ExtendedShapelessRecipeBuilder.builder(ModRecipes.COMPOUND_PART.get(), ModItems.TIP, 2)
                .addIngredient(BlueprintIngredient.of(ModItems.TIP_BLUEPRINT.get()))
                .addIngredient(PartMaterialIngredient.of(PartType.TIP), 2)
                .build(consumer, SilentGear.getId("part/tip2"));

        ExtendedShapelessRecipeBuilder.builder(ModRecipes.COMPOUND_PART.get(), ModItems.TIP, 2)
                .addIngredient(BlueprintIngredient.of(ModItems.TIP_BLUEPRINT.get()))
                .addIngredient(CraftingItems.UPGRADE_BASE)
                .addIngredient(PartMaterialIngredient.of(PartType.TIP), 2)
                .build(consumer, SilentGear.getId("part/tip2_alt"));

        ExtendedShapelessRecipeBuilder.builder(ModRecipes.COMPOUND_PART.get(), ModItems.COATING, 1)
                .addIngredient(BlueprintIngredient.of(ModItems.COATING_BLUEPRINT.get()))
                .addIngredient(PartMaterialIngredient.of(PartType.COATING))
                .build(consumer, SilentGear.getId("part/coating"));

        ExtendedShapelessRecipeBuilder.builder(ModRecipes.COMPOUND_PART.get(), ModItems.COATING, 1)
                .addIngredient(BlueprintIngredient.of(ModItems.COATING_BLUEPRINT.get()))
                .addIngredient(Items.GLASS_BOTTLE)
                .addIngredient(PartMaterialIngredient.of(PartType.COATING))
                .build(consumer, SilentGear.getId("part/coating_alt"));

        ExtendedShapelessRecipeBuilder.builder(ModRecipes.COMPOUND_PART.get(), ModItems.COATING, 2)
                .addIngredient(BlueprintIngredient.of(ModItems.COATING_BLUEPRINT.get()))
                .addIngredient(PartMaterialIngredient.of(PartType.COATING), 2)
                .build(consumer, SilentGear.getId("part/coating2"));

        ExtendedShapelessRecipeBuilder.builder(ModRecipes.COMPOUND_PART.get(), ModItems.COATING, 2)
                .addIngredient(BlueprintIngredient.of(ModItems.COATING_BLUEPRINT.get()))
                .addIngredient(Items.GLASS_BOTTLE)
                .addIngredient(PartMaterialIngredient.of(PartType.COATING), 2)
                .build(consumer, SilentGear.getId("part/coating2_alt"));
    }

    private void registerGear(Consumer<FinishedRecipe> consumer) {
        toolRecipes(consumer, "sword", 2, ModItems.SWORD, ModItems.SWORD_BLADE, ModItems.SWORD_BLUEPRINT.get());
        toolRecipes(consumer, "katana", 3, ModItems.KATANA, ModItems.KATANA_BLADE, ModItems.KATANA_BLUEPRINT.get());
        toolRecipes(consumer, "machete", 3, ModItems.MACHETE, ModItems.MACHETE_BLADE, ModItems.MACHETE_BLUEPRINT.get());
        toolRecipes(consumer, "spear", 1, ModItems.SPEAR, ModItems.SPEAR_TIP, ModItems.SPEAR_BLUEPRINT.get());
        toolRecipes(consumer, "trident", 3, ModItems.TRIDENT, ModItems.TRIDENT_PRONGS, ModItems.TRIDENT_BLUEPRINT.get());
        toolRecipes(consumer, "knife", 1, ModItems.KNIFE, ModItems.KNIFE_BLADE, ModItems.KNIFE_BLUEPRINT.get());
        toolRecipes(consumer, "dagger", 1, ModItems.DAGGER, ModItems.DAGGER_BLADE, ModItems.DAGGER_BLUEPRINT.get());
        toolRecipes(consumer, "pickaxe", 3, ModItems.PICKAXE, ModItems.PICKAXE_HEAD, ModItems.PICKAXE_BLUEPRINT.get());
        toolRecipes(consumer, "shovel", 1, ModItems.SHOVEL, ModItems.SHOVEL_HEAD, ModItems.SHOVEL_BLUEPRINT.get());
        toolRecipes(consumer, "axe", 3, ModItems.AXE, ModItems.AXE_HEAD, ModItems.AXE_BLUEPRINT.get());
        toolRecipes(consumer, "paxel", 5, ModItems.PAXEL, ModItems.PAXEL_HEAD, ModItems.PAXEL_BLUEPRINT.get());
        toolRecipes(consumer, "hammer", 6, ModItems.HAMMER, ModItems.HAMMER_HEAD, ModItems.HAMMER_BLUEPRINT.get());
        toolRecipes(consumer, "excavator", 5, ModItems.EXCAVATOR, ModItems.EXCAVATOR_HEAD, ModItems.EXCAVATOR_BLUEPRINT.get());
        toolRecipes(consumer, "mattock", 4, ModItems.MATTOCK, ModItems.MATTOCK_HEAD, ModItems.MATTOCK_BLUEPRINT.get());
        toolRecipes(consumer, "prospector_hammer", 2, ModItems.PROSPECTOR_HAMMER, ModItems.PROSPECTOR_HAMMER_HEAD, ModItems.PROSPECTOR_HAMMER_BLUEPRINT.get());
        toolRecipes(consumer, "saw", 5, ModItems.SAW, ModItems.SAW_BLADE, ModItems.SAW_BLUEPRINT.get());
        toolRecipes(consumer, "sickle", 3, ModItems.SICKLE, ModItems.SICKLE_BLADE, ModItems.SICKLE_BLUEPRINT.get());
        toolRecipes(consumer, "shears", 2, ModItems.SHEARS, ModItems.SHEARS_BLADES, ModItems.SHEARS_BLUEPRINT.get());
        bowRecipes(consumer, "fishing_rod", 2, ModItems.FISHING_ROD, ModItems.FISHING_REEL_AND_HOOK, ModItems.FISHING_ROD_BLUEPRINT.get());
        bowRecipes(consumer, "bow", 3, ModItems.BOW, ModItems.BOW_LIMBS, ModItems.BOW_BLUEPRINT.get());
        bowRecipes(consumer, "crossbow", 3, ModItems.CROSSBOW, ModItems.CROSSBOW_LIMBS, ModItems.CROSSBOW_BLUEPRINT.get());
        bowRecipes(consumer, "slingshot", 2, ModItems.SLINGSHOT, ModItems.SLINGSHOT_LIMBS, ModItems.SLINGSHOT_BLUEPRINT.get());
        arrowRecipes(consumer, "arrow", ModItems.ARROW, ModItems.ARROW_HEADS, ModItems.ARROW_BLUEPRINT.get());

        curioRecipes(consumer, "ring", 2, ModItems.RING, ModItems.RING_SHANK, ModItems.RING_BLUEPRINT.get());
        curioRecipes(consumer, "bracelet", 3, ModItems.BRACELET, ModItems.BRACELET_BAND, ModItems.BRACELET_BLUEPRINT.get());

        ExtendedShapelessRecipeBuilder.builder(ModRecipes.SHAPELESS_GEAR.get(), ModItems.SHIELD)
                .addIngredient(BlueprintIngredient.of(ModItems.SHIELD_BLUEPRINT.get()))
                .addIngredient(PartMaterialIngredient.of(PartType.MAIN, GearType.ARMOR), 2)
                .addIngredient(GearPartIngredient.of(PartType.ROD))
                .build(consumer, SilentGear.getId("gear/shield"));

        armorRecipes(consumer, 5, ModItems.HELMET.get(), ModItems.HELMET_PLATES, ModItems.HELMET_BLUEPRINT.get());
        armorRecipes(consumer, 8, ModItems.CHESTPLATE.get(), ModItems.CHESTPLATE_PLATES, ModItems.CHESTPLATE_BLUEPRINT.get());
        armorRecipes(consumer, 7, ModItems.LEGGINGS.get(), ModItems.LEGGING_PLATES, ModItems.LEGGINGS_BLUEPRINT.get());
        armorRecipes(consumer, 4, ModItems.BOOTS.get(), ModItems.BOOT_PLATES, ModItems.BOOTS_BLUEPRINT.get());

        ExtendedShapelessRecipeBuilder.builder(ModRecipes.COMPOUND_PART.get(), ModItems.ELYTRA_WINGS)
                .addIngredient(BlueprintIngredient.of(ModItems.ELYTRA_BLUEPRINT.get()))
                .addIngredient(PartMaterialIngredient.of(PartType.MAIN,
                        GearType.ELYTRA,
                        MaterialCategories.CLOTH,
                        MaterialCategories.SHEET), 6)
                .build(consumer, SilentGear.getId("gear/elytra_wings"));

        ExtendedShapelessRecipeBuilder.builder(ModRecipes.SHAPELESS_GEAR.get(), ModItems.ELYTRA.get())
                .addIngredient(ModItems.ELYTRA_WINGS)
                .addIngredient(GearPartIngredient.of(PartType.BINDING))
                .build(consumer, SilentGear.getId("gear/elytra"));

        // Rough recipes
        ExtendedShapedRecipeBuilder.builder(ModRecipes.SHAPED_GEAR.get(), ModItems.SWORD)
                .patternLine("#")
                .patternLine("#")
                .patternLine("/")
                .key('#', PartMaterialIngredient.of(PartType.MAIN, GearType.TOOL))
                .key('/', ModTags.Items.RODS_ROUGH)
                .build(consumer, SilentGear.getId("gear/rough/sword"));
        ExtendedShapedRecipeBuilder.builder(ModRecipes.SHAPED_GEAR.get(), ModItems.DAGGER)
                .patternLine("#")
                .patternLine("/")
                .key('#', PartMaterialIngredient.of(PartType.MAIN, GearType.TOOL))
                .key('/', ModTags.Items.RODS_ROUGH)
                .build(consumer, SilentGear.getId("gear/rough/dagger"));
        ExtendedShapedRecipeBuilder.builder(ModRecipes.SHAPED_GEAR.get(), ModItems.KNIFE)
                .patternLine(" #")
                .patternLine("/ ")
                .key('#', PartMaterialIngredient.of(PartType.MAIN, GearType.TOOL))
                .key('/', ModTags.Items.RODS_ROUGH)
                .build(consumer, SilentGear.getId("gear/rough/knife"));
        ExtendedShapedRecipeBuilder.builder(ModRecipes.SHAPED_GEAR.get(), ModItems.PICKAXE)
                .patternLine("###")
                .patternLine(" / ")
                .patternLine(" / ")
                .key('#', PartMaterialIngredient.of(PartType.MAIN, GearType.TOOL))
                .key('/', ModTags.Items.RODS_ROUGH)
                .build(consumer, SilentGear.getId("gear/rough/pickaxe"));
        ExtendedShapedRecipeBuilder.builder(ModRecipes.SHAPED_GEAR.get(), ModItems.SHOVEL)
                .patternLine("#")
                .patternLine("/")
                .patternLine("/")
                .key('#', PartMaterialIngredient.of(PartType.MAIN, GearType.TOOL))
                .key('/', ModTags.Items.RODS_ROUGH)
                .build(consumer, SilentGear.getId("gear/rough/shovel"));
        ExtendedShapedRecipeBuilder.builder(ModRecipes.SHAPED_GEAR.get(), ModItems.AXE)
                .patternLine("##")
                .patternLine("#/")
                .patternLine(" /")
                .key('#', PartMaterialIngredient.of(PartType.MAIN, GearType.TOOL))
                .key('/', ModTags.Items.RODS_ROUGH)
                .build(consumer, SilentGear.getId("gear/rough/axe"));

        // Coonversion recipes
        toolConversion(consumer, ModItems.SWORD, Items.DIAMOND_SWORD, Items.GOLDEN_SWORD, Items.IRON_SWORD, Items.STONE_SWORD, Items.WOODEN_SWORD);
        toolConversion(consumer, ModItems.PICKAXE, Items.DIAMOND_PICKAXE, Items.GOLDEN_PICKAXE, Items.IRON_PICKAXE, Items.STONE_PICKAXE, Items.WOODEN_PICKAXE);
        toolConversion(consumer, ModItems.SHOVEL, Items.DIAMOND_SHOVEL, Items.GOLDEN_SHOVEL, Items.IRON_SHOVEL, Items.STONE_SHOVEL, Items.WOODEN_SHOVEL);
        toolConversion(consumer, ModItems.AXE, Items.DIAMOND_AXE, Items.GOLDEN_AXE, Items.IRON_AXE, Items.STONE_AXE, Items.WOODEN_AXE);
        armorConversion(consumer, ModItems.HELMET, Items.DIAMOND_HELMET, Items.GOLDEN_HELMET, Items.IRON_HELMET, Items.LEATHER_HELMET);
        armorConversion(consumer, ModItems.CHESTPLATE, Items.DIAMOND_CHESTPLATE, Items.GOLDEN_CHESTPLATE, Items.IRON_CHESTPLATE, Items.LEATHER_CHESTPLATE);
        armorConversion(consumer, ModItems.LEGGINGS, Items.DIAMOND_LEGGINGS, Items.GOLDEN_LEGGINGS, Items.IRON_LEGGINGS, Items.LEATHER_LEGGINGS);
        armorConversion(consumer, ModItems.BOOTS, Items.DIAMOND_BOOTS, Items.GOLDEN_BOOTS, Items.IRON_BOOTS, Items.LEATHER_BOOTS);
    }

    private void registerCraftingItems(Consumer<FinishedRecipe> consumer) {
        damageGear(ModItems.PEBBLE, 9, 1)
                .addIngredient(ModTags.Items.HAMMERS)
                .addIngredient(Tags.Items.COBBLESTONE)
                .build(consumer);

        damageGear(CraftingItems.TEMPLATE_BOARD, 6, 1)
                .addIngredient(ModTags.Items.KNIVES)
                .addIngredient(ItemTags.LOGS)
                .build(consumer);

        ShapedRecipeBuilder.shaped(CraftingItems.FINE_SILK_CLOTH)
                .pattern("##")
                .pattern("##")
                .define('#', CraftingItems.FINE_SILK)
                .unlockedBy("has_item", has(CraftingItems.FINE_SILK))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(CraftingItems.FINE_SILK, 4)
                .requires(CraftingItems.FINE_SILK_CLOTH)
                .unlockedBy("has_item", has(CraftingItems.FINE_SILK))
                .save(consumer);

        // TODO: Maybe should organize these better...
        // B
        ShapelessRecipeBuilder.shapeless(CraftingItems.BLUEPRINT_PAPER, 4)
                .requires(Ingredient.of(ModTags.Items.PAPER), 4)
                .requires(Tags.Items.DYES_BLUE)
                .unlockedBy("has_paper", has(ModTags.Items.PAPER))
                .save(consumer);
        // C
        ShapelessRecipeBuilder.shapeless(Blocks.COBBLESTONE)
                .requires(ModItems.PEBBLE, 9)
                .unlockedBy("has_pebble", has(ModItems.PEBBLE))
                .save(consumer, SilentGear.getId("cobblestone_from_pebbles"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(CraftingItems.SINEW), CraftingItems.DRIED_SINEW, 0.35f, 200)
                .unlockedBy("has_item", has(CraftingItems.SINEW))
                .save(consumer);
        // I
        ShapedRecipeBuilder.shaped(CraftingItems.IRON_ROD, 4)
                .define('/', Tags.Items.INGOTS_IRON)
                .pattern("/")
                .pattern("/")
                .unlockedBy("has_item", has(Items.IRON_INGOT))
                .save(consumer);
        // R
        ShapedRecipeBuilder.shaped(CraftingItems.ROUGH_ROD, 2)
                .define('/', Tags.Items.RODS_WOODEN)
                .pattern(" /")
                .pattern("/ ")
                .unlockedBy("has_item", has(Items.STICK))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(CraftingItems.ROUGH_ROD, 2)
                .requires(ModItems.ROD_BLUEPRINT.get().getItemTag())
                .requires(Ingredient.of(Tags.Items.RODS_WOODEN), 2)
                .unlockedBy("has_item", has(ModItems.ROD_BLUEPRINT.get().getItemTag()))
                .save(consumer, SilentGear.getId("rough_rod2"));
        // S
        ShapelessRecipeBuilder.shapeless(CraftingItems.SINEW_FIBER, 3)
                .requires(CraftingItems.DRIED_SINEW)
                .unlockedBy("has_item", has(CraftingItems.SINEW))
                .save(consumer);
        ShapedRecipeBuilder.shaped(CraftingItems.STONE_ROD, 4)
                .define('#', Tags.Items.COBBLESTONE)
                .pattern("#")
                .pattern("#")
                .unlockedBy("has_item", has(Tags.Items.COBBLESTONE))
                .save(consumer);
        // U
        ShapelessRecipeBuilder.shapeless(CraftingItems.UPGRADE_BASE, 4)
                .requires(Ingredient.of(ModTags.Items.PAPER), 2)
                .requires(ItemTags.PLANKS)
                .requires(Tags.Items.STONE)
                .unlockedBy("has_item", has(ItemTags.PLANKS))
                .save(consumer);
    }

    private void registerSmithing(Consumer<FinishedRecipe> consumer) {
        Registration.getItems(item -> item instanceof ICoreItem).forEach(item -> {
            if (((ICoreItem) item).getGearType() != GearType.ELYTRA) {
                GearSmithingRecipeBuilder.coating(item).build(consumer);
            }
            GearSmithingRecipeBuilder.upgrade(item, PartType.MISC_UPGRADE).build(consumer);
        });
    }

    private void special(Consumer<FinishedRecipe> consumer, SimpleRecipeSerializer<?> serializer) {
        SpecialRecipeBuilder.special(serializer).save(consumer, NameUtils.from(serializer).toString());
    }

    private ExtendedShapelessRecipeBuilder damageGear(ItemLike result, int count, int damage) {
        return ExtendedShapelessRecipeBuilder.builder(ModRecipes.DAMAGE_ITEM.get(), result, count)
                .addExtraData(json -> json.addProperty("damage", damage));
    }

    @SuppressWarnings("MethodWithTooManyParameters")
    private static void toolRecipes(Consumer<FinishedRecipe> consumer, String name, int mainCount, ItemLike tool, ItemLike toolHead, GearBlueprintItem blueprintItem) {
        // Tool head
        ExtendedShapelessRecipeBuilder.builder(ModRecipes.COMPOUND_PART.get(), toolHead)
                .addIngredient(BlueprintIngredient.of(blueprintItem))
                .addIngredient(PartMaterialIngredient.of(PartType.MAIN, GearType.TOOL), mainCount)
                .build(consumer, SilentGear.getId("gear/" + name + "_head"));
        // Tool from head and rod
        ExtendedShapelessRecipeBuilder.builder(ModRecipes.SHAPELESS_GEAR.get(), tool)
                .addIngredient(toolHead)
                .addIngredient(GearPartIngredient.of(PartType.ROD))
                .build(consumer, SilentGear.getId("gear/" + name));
        // Quick tool (mains and rods, skipping head)
        ExtendedShapelessRecipeBuilder.builder(ModRecipes.SHAPELESS_GEAR.get(), tool)
                .addIngredient(BlueprintIngredient.of(blueprintItem))
                .addIngredient(PartMaterialIngredient.of(PartType.MAIN, GearType.TOOL), mainCount)
                .addIngredient(GearPartIngredient.of(PartType.ROD))
                .build(consumer, SilentGear.getId("gear/" + name + "_quick"));
    }

    @SuppressWarnings("MethodWithTooManyParameters")
    private static void bowRecipes(Consumer<FinishedRecipe> consumer, String name, int mainCount, ItemLike tool, ItemLike toolHead, GearBlueprintItem blueprintItem) {
        // Main part
        ExtendedShapelessRecipeBuilder.builder(ModRecipes.COMPOUND_PART.get(), toolHead)
                .addIngredient(BlueprintIngredient.of(blueprintItem))
                .addIngredient(PartMaterialIngredient.of(PartType.MAIN, GearType.TOOL), mainCount)
                .build(consumer, SilentGear.getId("gear/" + name + "_main"));
        // Tool from main, rod, and cord
        ExtendedShapelessRecipeBuilder.builder(ModRecipes.SHAPELESS_GEAR.get(), tool)
                .addIngredient(toolHead)
                .addIngredient(GearPartIngredient.of(PartType.ROD))
                .addIngredient(GearPartIngredient.of(PartType.CORD))
                .build(consumer, SilentGear.getId("gear/" + name));
        // Quick tool (main materials, rod, and cord, skipping main part)
        ExtendedShapelessRecipeBuilder.builder(ModRecipes.SHAPELESS_GEAR.get(), tool)
                .addIngredient(BlueprintIngredient.of(blueprintItem))
                .addIngredient(PartMaterialIngredient.of(PartType.MAIN, GearType.TOOL), mainCount)
                .addIngredient(GearPartIngredient.of(PartType.ROD))
                .addIngredient(GearPartIngredient.of(PartType.CORD))
                .build(consumer, SilentGear.getId("gear/" + name + "_quick"));
    }

    private static void arrowRecipes(Consumer<FinishedRecipe> consumer, String name, ItemLike arrow, ItemLike arrowHead, GearBlueprintItem blueprintItem) {
        BlueprintIngredient blueprint = BlueprintIngredient.of(blueprintItem);
        // Arrow head
        ExtendedShapelessRecipeBuilder.builder(ModRecipes.COMPOUND_PART.get(), arrowHead)
                .addIngredient(blueprint)
                .addIngredient(PartMaterialIngredient.of(PartType.MAIN, GearType.PROJECTILE))
                .build(consumer, SilentGear.getId("gear/" + name + "_head"));
        // Arrows from head
        ExtendedShapelessRecipeBuilder.builder(ModRecipes.SHAPELESS_GEAR.get(), arrow)
                .addIngredient(arrowHead)
                .addIngredient(GearPartIngredient.of(PartType.ROD))
                .addIngredient(GearPartIngredient.of(PartType.FLETCHING))
                .build(consumer, SilentGear.getId("gear/" + name));
        // Quick arrows
        ExtendedShapelessRecipeBuilder.builder(ModRecipes.SHAPELESS_GEAR.get(), arrow)
                .addIngredient(BlueprintIngredient.of(blueprintItem))
                .addIngredient(PartMaterialIngredient.of(PartType.MAIN, GearType.TOOL))
                .addIngredient(GearPartIngredient.of(PartType.ROD))
                .addIngredient(GearPartIngredient.of(PartType.FLETCHING))
                .build(consumer, SilentGear.getId("gear/" + name + "_quick"));
    }

    private void armorRecipes(Consumer<FinishedRecipe> consumer, int mainCount, GearArmorItem armor, ItemLike plates, GearBlueprintItem blueprintItem) {
        ExtendedShapelessRecipeBuilder.builder(ModRecipes.COMPOUND_PART.get(), plates)
                .addIngredient(BlueprintIngredient.of(blueprintItem))
                .addIngredient(PartMaterialIngredient.of(PartType.MAIN, armor.getGearType()), mainCount)
                .build(consumer, SilentGear.getId("gear/" + NameUtils.fromItem(plates).getPath()));

        ExtendedShapelessRecipeBuilder.builder(ModRecipes.SHAPELESS_GEAR.get(), armor)
                .addIngredient(plates)
                .build(consumer, SilentGear.getId("gear/" + NameUtils.fromItem(armor).getPath()));

        ExtendedShapelessRecipeBuilder.builder(ModRecipes.SHAPELESS_GEAR.get(), armor)
                .addIngredient(plates)
                .addIngredient(GearPartIngredient.of(PartType.LINING))
                .build(consumer, SilentGear.getId("gear/" + NameUtils.fromItem(armor).getPath() + "_with_lining"));
    }

    private static void curioRecipes(Consumer<FinishedRecipe> consumer, String name, int mainCount, ItemLike curioItem, ItemLike curioMain, GearBlueprintItem blueprint) {
        ExtendedShapelessRecipeBuilder.builder(ModRecipes.COMPOUND_PART.get(), curioMain)
                .addIngredient(BlueprintIngredient.of(blueprint))
                .addIngredient(PartMaterialIngredient.of(PartType.MAIN, GearType.CURIO, MaterialCategories.METAL), mainCount)
                .build(consumer, SilentGear.getId("gear/" + name + "_main_only"));

        ExtendedShapelessRecipeBuilder.builder(ModRecipes.SHAPELESS_GEAR.get(), curioItem)
                .addIngredient(BlueprintIngredient.of(ModItems.JEWELER_TOOLS.get()))
                .addIngredient(curioMain)
                .build(consumer, SilentGear.getId("gear/" + name));

        ExtendedShapelessRecipeBuilder.builder(ModRecipes.SHAPELESS_GEAR.get(), curioItem)
                .addIngredient(BlueprintIngredient.of(ModItems.JEWELER_TOOLS.get()))
                .addIngredient(curioMain)
                .addIngredient(GearPartIngredient.of(PartType.ADORNMENT))
                .build(consumer, SilentGear.getId("gear/" + name + "_with_gem"));

        ExtendedShapelessRecipeBuilder.builder(ModRecipes.SHAPELESS_GEAR.get(), curioItem)
                .addIngredient(BlueprintIngredient.of(blueprint))
                .addIngredient(PartMaterialIngredient.of(PartType.MAIN, GearType.CURIO, MaterialCategories.METAL), mainCount)
                .addIngredient(GearPartIngredient.of(PartType.ADORNMENT))
                .build(consumer, SilentGear.getId("gear/" + name + "quick"));
    }

    private void toolBlueprint(Consumer<FinishedRecipe> consumer, String group, ItemLike blueprint, ItemLike template, String... pattern) {
        toolBlueprint(consumer, group, blueprint, template, Ingredient.EMPTY, pattern);
    }

    private void toolBlueprint(Consumer<FinishedRecipe> consumer, String group, ItemLike blueprint, ItemLike template, Ingredient extra, String... pattern) {
        ShapedRecipeBuilder builderBlueprint = ShapedRecipeBuilder.shaped(blueprint)
                .group("silentgear:blueprints/" + group)
                .define('#', ModTags.Items.BLUEPRINT_PAPER)
                .define('/', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_item", has(ModTags.Items.BLUEPRINT_PAPER));

        ShapedRecipeBuilder builderTemplate = ShapedRecipeBuilder.shaped(template)
                .group("silentgear:blueprints/" + group)
                .define('#', ModTags.Items.TEMPLATE_BOARDS)
                .define('/', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_item", has(ModTags.Items.TEMPLATE_BOARDS));

        if (extra != Ingredient.EMPTY) {
            builderBlueprint.define('@', extra);
            builderTemplate.define('@', extra);
        }

        for (String line : pattern) {
            builderBlueprint.pattern(line);
            builderTemplate.pattern(line);
        }

        builderBlueprint.save(consumer);
        builderTemplate.save(consumer);
    }

    private void armorBlueprint(Consumer<FinishedRecipe> consumer, String group, ItemLike blueprint, ItemLike template, String... pattern) {
        ShapedRecipeBuilder builderBlueprint = ShapedRecipeBuilder.shaped(blueprint)
                .group("silentgear:blueprints/" + group)
                .define('#', ModTags.Items.BLUEPRINT_PAPER)
                .unlockedBy("has_item", has(ModTags.Items.BLUEPRINT_PAPER));
        for (String line : pattern) {
            builderBlueprint.pattern(line);
        }
        builderBlueprint.save(consumer);

        ShapedRecipeBuilder builderTemplate = ShapedRecipeBuilder.shaped(template)
                .group("silentgear:blueprints/" + group)
                .define('#', ModTags.Items.TEMPLATE_BOARDS)
                .unlockedBy("has_item", has(ModTags.Items.TEMPLATE_BOARDS));
        for (String line : pattern) {
            builderTemplate.pattern(line);
        }
        builderTemplate.save(consumer);
    }

    private static final Map<Tier, ResourceLocation> TOOL_MATERIALS = ImmutableMap.<Tier, ResourceLocation>builder()
            .put(Tiers.DIAMOND, SilentGear.getId("diamond"))
            .put(Tiers.GOLD, SilentGear.getId("gold"))
            .put(Tiers.IRON, SilentGear.getId("iron"))
            .put(Tiers.STONE, SilentGear.getId("stone"))
            .put(Tiers.WOOD, SilentGear.getId("wood"))
            .build();
    private static final Map<ArmorMaterial, ResourceLocation> ARMOR_MATERIALS = ImmutableMap.<ArmorMaterial, ResourceLocation>builder()
            .put(ArmorMaterials.DIAMOND, SilentGear.getId("diamond"))
            .put(ArmorMaterials.GOLD, SilentGear.getId("gold"))
            .put(ArmorMaterials.IRON, SilentGear.getId("iron"))
            .put(ArmorMaterials.LEATHER, SilentGear.getId("leather"))
            .build();

    private static void toolConversion(Consumer<FinishedRecipe> consumer, ItemLike result, Item... toolItems) {
        for (Item input : toolItems) {
            assert input instanceof TieredItem;
            ExtendedShapelessRecipeBuilder.builder(ModRecipes.CONVERSION.get(), result)
                    .addIngredient(input)
                    .addExtraData(json -> {
                        ResourceLocation material = TOOL_MATERIALS.getOrDefault(((TieredItem) input).getTier(), SilentGear.getId("emerald"));
                        json.getAsJsonObject("result").add("materials", buildMaterials(material, SilentGear.getId("wood")));
                    })
                    .build(consumer, SilentGear.getId("gear/convert/" + NameUtils.from(input).getPath()));
        }
    }

    private static void armorConversion(Consumer<FinishedRecipe> consumer, ItemLike result, Item... armorItems) {
        for (Item input : armorItems) {
            assert input instanceof ArmorItem;
            ExtendedShapelessRecipeBuilder.builder(ModRecipes.CONVERSION.get(), result)
                    .addIngredient(input)
                    .addExtraData(json -> {
                        ResourceLocation material = ARMOR_MATERIALS.getOrDefault(((ArmorItem) input).getMaterial(), SilentGear.getId("emerald"));
                        json.getAsJsonObject("result").add("materials", buildMaterials(material, null));
                    })
                    .build(consumer, SilentGear.getId("gear/convert/" + NameUtils.from(input).getPath()));
        }
    }

    private static JsonObject buildMaterials(ResourceLocation main, @Nullable ResourceLocation rod) {
        JsonObject json = new JsonObject();
        json.add("main", LazyMaterialInstance.of(main).serialize());
        if (rod != null) {
            json.add("rod", LazyMaterialInstance.of(rod).serialize());
        }
        return json;
    }

    private void metals(Consumer<FinishedRecipe> consumer, float smeltingXp, Metals metal) {
        if (metal.ore != null) {
            SimpleCookingRecipeBuilder.blasting(Ingredient.of(metal.oreTag), metal.ingot, smeltingXp, 100)
                    .unlockedBy("has_item", has(metal.oreTag))
                    .save(consumer, SilentGear.getId(metal.name + "_ore_blasting"));
            SimpleCookingRecipeBuilder.smelting(Ingredient.of(metal.oreTag), metal.ingot, smeltingXp, 200)
                    .unlockedBy("has_item", has(metal.oreTag))
                    .save(consumer, SilentGear.getId(metal.name + "_ore_smelting"));
        }

        if (metal.rawOre != null) {
            SimpleCookingRecipeBuilder.blasting(Ingredient.of(metal.rawOre), metal.ingot, smeltingXp, 100)
                    .unlockedBy("has_item", has(metal.rawOre))
                    .save(consumer, SilentGear.getId(metal.name + "_raw_ore_blasting"));
            SimpleCookingRecipeBuilder.smelting(Ingredient.of(metal.rawOre), metal.ingot, smeltingXp, 200)
                    .unlockedBy("has_item", has(metal.rawOre))
                    .save(consumer, SilentGear.getId(metal.name + "_raw_ore_smelting"));

            compressionRecipes(consumer, metal.rawOreBlock, metal.rawOre, null);
        }

        InventoryChangeTrigger.TriggerInstance hasIngot = has(metal.ingotTag);

        if (metal.block != null) {
            compressionRecipes(consumer, metal.block, metal.ingot, metal.nugget);
        }

        if (metal.dustTag != null) {
            Ingredient dustOrChunks = metal.chunksTag != null
                    ? Ingredient.fromValues(Stream.of(new Ingredient.TagValue(metal.chunksTag), new Ingredient.TagValue(metal.dustTag)))
                    : Ingredient.of(metal.dustTag);
            SimpleCookingRecipeBuilder.blasting(dustOrChunks, metal.ingot, smeltingXp, 100)
                    .unlockedBy("has_item", hasIngot)
                    .save(consumer, SilentGear.getId(metal.name + "_dust_blasting"));
            SimpleCookingRecipeBuilder.smelting(dustOrChunks, metal.ingot, smeltingXp, 200)
                    .unlockedBy("has_item", hasIngot)
                    .save(consumer, SilentGear.getId(metal.name + "_dust_smelting"));
        }

        if (metal.dust != null) {
            damageGear(metal.dust, 1, 1)
                    .addIngredient(ModTags.Items.HAMMERS)
                    .addIngredient(metal.ingotTag)
                    .addCriterion("has_item", hasIngot)
                    .build(consumer);
        }
    }

    @SuppressWarnings("WeakerAccess")
    private static class Metals {
        private final String name;
        private ItemLike ore;
        private TagKey<Item> oreTag;
        private ItemLike rawOre;
        private ItemLike rawOreBlock;
        private ItemLike block;
        private final ItemLike ingot;
        private final TagKey<Item> ingotTag;
        private ItemLike nugget;
        private ItemLike dust;
        private TagKey<Item> dustTag;
        private TagKey<Item> chunksTag;

        public Metals(String name, ItemLike ingot, TagKey<Item> ingotTag) {
            this.name = name;
            this.ingot = ingot;
            this.ingotTag = ingotTag;
        }

        public Metals ore(ItemLike oreBlockItem, TagKey<Item> oreTag, ItemLike rawOre, ItemLike rawOreBlock) {
            this.ore = oreBlockItem;
            this.oreTag = oreTag;
            this.rawOre = rawOre;
            this.rawOreBlock = rawOreBlock;
            return this;
        }

        public Metals block(ItemLike item, TagKey<Item> tag) {
            this.block = item;
            return this;
        }

        public Metals nugget(ItemLike item, TagKey<Item> tag) {
            this.nugget = item;
            return this;
        }

        public Metals dust(ItemLike item, TagKey<Item> tag) {
            this.dust = item;
            this.dustTag = tag;
            return this;
        }

        public Metals chunks(TagKey<Item> tag) {
            this.chunksTag = tag;
            return this;
        }
    }
}
