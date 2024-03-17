package net.silentchaos512.gear.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.subtypes.IIngredientSubtypeInterpreter;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.silentchaos512.gear.SilentGear;
import net.silentchaos512.gear.api.item.ICoreTool;
import net.silentchaos512.gear.api.material.IMaterialInstance;
import net.silentchaos512.gear.init.ModItems;
import net.silentchaos512.gear.init.ModRecipes;
import net.silentchaos512.gear.init.Registration;
import net.silentchaos512.gear.item.CraftingItems;
import net.silentchaos512.gear.item.CustomMaterialItem;
import net.silentchaos512.gear.item.FragmentItem;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@JeiPlugin
public class SGearJeiPlugin implements IModPlugin {
    private static final ResourceLocation PLUGIN_UID = SilentGear.getId("plugin/main");
    static final ResourceLocation GEAR_CRAFTING = SilentGear.getId("category/gear_crafting");

    @Override
    public ResourceLocation getPluginUid() {
        return PLUGIN_UID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration reg) {
        IGuiHelper guiHelper = reg.getJeiHelpers().getGuiHelper();
        reg.addRecipeCategories(new GearCraftingRecipeCategoryJei(guiHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration reg) {
        assert Minecraft.getInstance().level != null;
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        reg.addRecipes(recipeManager.getRecipes().stream()
                .filter(SGearJeiPlugin::isGearCraftingRecipe)
                .collect(Collectors.toList()), GEAR_CRAFTING);

        addInfoPage(reg, CraftingItems.SPOON_UPGRADE);
        for (Item item : Registration.getItems(item -> item instanceof ICoreTool)) {
            addInfoPage(reg, item);
        }
    }

    private static boolean isGearCraftingRecipe(Recipe<?> recipe) {
        RecipeSerializer<?> serializer = recipe.getSerializer();
        return serializer == ModRecipes.SHAPED_GEAR.get() || serializer == ModRecipes.SHAPELESS_GEAR.get() || serializer == ModRecipes.COMPOUND_PART.get();
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration reg) {
        reg.addRecipeCatalyst(new ItemStack(Blocks.CRAFTING_TABLE), GEAR_CRAFTING);
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration reg) {
        reg.registerSubtypeInterpreter(ModItems.FRAGMENT.get(), (stack, context) -> {
            IMaterialInstance material = FragmentItem.getMaterial(stack);
            return material != null ? material.getId().toString() : "";
        });

        IIngredientSubtypeInterpreter<ItemStack> customMaterials = (stack, context) -> {
            IMaterialInstance material = CustomMaterialItem.getMaterial(stack);
            return material != null ? material.getId().toString() : "";
        };
        reg.registerSubtypeInterpreter(ModItems.CUSTOM_GEM.get(), customMaterials);
        reg.registerSubtypeInterpreter(ModItems.CUSTOM_INGOT.get(), customMaterials);
    }

    private static void addInfoPage(IRecipeRegistration reg, ItemLike item) {
        String key = getDescKey(Objects.requireNonNull(item.asItem().getRegistryName()));
        ItemStack stack = new ItemStack(item);
        reg.addIngredientInfo(stack, VanillaTypes.ITEM, new TranslatableComponent(key));
    }

    private static void addInfoPage(IRecipeRegistration reg, String name, Collection<ItemLike> items) {
        String key = getDescKey(SilentGear.getId(name));
        List<ItemStack> stacks = items.stream().map(ItemStack::new).collect(Collectors.toList());
        reg.addIngredientInfo(stacks, VanillaTypes.ITEM, new TranslatableComponent(key));
    }

    private static void addInfoPage(IRecipeRegistration reg, ItemLike item, Stream<ItemStack> variants) {
        String key = getDescKey(Objects.requireNonNull(item.asItem().getRegistryName()));
        reg.addIngredientInfo(variants.collect(Collectors.toList()), VanillaTypes.ITEM, new TranslatableComponent(key));
    }

    private static String getDescKey(ResourceLocation name) {
        return "jei." + name.getNamespace() + "." + name.getPath() + ".desc";
    }
}
