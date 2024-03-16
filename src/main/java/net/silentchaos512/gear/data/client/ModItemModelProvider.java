package net.silentchaos512.gear.data.client;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.silentchaos512.gear.SilentGear;
import net.silentchaos512.gear.init.ModBlocks;
import net.silentchaos512.gear.init.ModItems;
import net.silentchaos512.gear.init.Registration;
import net.silentchaos512.gear.item.CraftingItems;
import net.silentchaos512.gear.item.blueprint.GearBlueprintItem;
import net.silentchaos512.gear.item.blueprint.PartBlueprintItem;
import net.silentchaos512.lib.util.NameUtils;

import javax.annotation.Nonnull;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, SilentGear.MOD_ID, existingFileHelper);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Silent Gear - Item Models";
    }

    @Override
    protected void registerModels() {
        // Blocks
        Registration.BLOCKS.getEntries().stream()
                .map(RegistryObject::get)
                .forEach(this::blockItemModel);

        ModelFile itemGenerated = getExistingFile(new ResourceLocation("item/generated"));

        for (CraftingItems item : CraftingItems.values()) {
                builder(item, itemGenerated, "item/" + item.getName());
        }

        // Crafted materials
        builder(ModItems.SHEET_METAL)
                .parent(itemGenerated)
                .texture("layer0", "item/sheet_metal")
                .texture("layer1", "item/sheet_metal_highlight");

        // Compound materials
        builder(ModItems.ALLOY_INGOT)
                .parent(itemGenerated)
                .texture("layer0", "item/alloy_ingot")
                .texture("layer1", "item/alloy_ingot_highlight");
        builder(ModItems.HYBRID_GEM)
                .parent(itemGenerated)
                .texture("layer0", "item/hybrid_gem")
                .texture("layer1", "item/hybrid_gem_highlight");
        builder(ModItems.MIXED_FABRIC, itemGenerated, "item/mixed_fabric");

        // Custom materials
        builder(ModItems.CUSTOM_INGOT)
                .parent(itemGenerated)
                .texture("layer0", "item/alloy_ingot")
                .texture("layer1", "item/alloy_ingot_highlight");
        builder(ModItems.CUSTOM_GEM)
                .parent(itemGenerated)
                .texture("layer0", "item/hybrid_gem")
                .texture("layer1", "item/hybrid_gem_highlight");

        builder(ModItems.BLUEPRINT_BOOK)
                .parent(itemGenerated)
                .texture("layer0", "item/blueprint_book_cover")
                .texture("layer1", "item/blueprint_book_pages")
                .texture("layer2", "item/blueprint_book_deco");

        builder(ModItems.JEWELER_TOOLS, itemGenerated, "item/jeweler_tools");

        // Blueprints and templates
        Registration.getItems(PartBlueprintItem.class).forEach(item -> {
            if (item.hasStandardModel()) {
                builder(item)
                        .parent(itemGenerated)
                        .texture("layer0", "item/" + (item.isSingleUse() ? "template" : "blueprint"))
                        .texture("layer1", "item/blueprint_" + item.getPartType().getName().getPath());
            }
        });
        Registration.getItems(GearBlueprintItem.class).forEach(item -> builder(item)
                .parent(itemGenerated)
                .texture("layer0", "item/" + (item.isSingleUse() ? "template" : "blueprint"))
                .texture("layer1", "item/blueprint_" + item.getGearType().getName()));

        // Misc
        builder(ModItems.BLUEPRINT_PACKAGE, itemGenerated);
        builder(ModItems.PEBBLE, itemGenerated);
    }

    private void blockItemModel(Block block) {
        if (block.asItem() != Items.AIR) {
            String name = NameUtils.from(block).getPath();
            withExistingParent(name, modLoc("block/" + name));
        }
    }

    private ItemModelBuilder builder(ItemLike item) {
        return getBuilder(NameUtils.fromItem(item).getPath());
    }

    private ItemModelBuilder builder(ItemLike item, ModelFile parent) {
        String name = NameUtils.fromItem(item).getPath();
        return builder(item, parent, "item/" + name);
    }

    private ItemModelBuilder builder(ItemLike item, ModelFile parent, String texture) {
        return getBuilder(NameUtils.fromItem(item).getPath()).parent(parent).texture("layer0", texture);
    }
}
