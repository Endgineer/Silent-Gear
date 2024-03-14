package net.silentchaos512.gear.data;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.silentchaos512.gear.SilentGear;
import net.silentchaos512.gear.init.ModBlocks;
import net.silentchaos512.gear.init.ModItems;
import net.silentchaos512.gear.init.ModTags;
import net.silentchaos512.gear.init.Registration;
import net.silentchaos512.gear.item.CraftingItems;
import net.silentchaos512.gear.item.blueprint.AbstractBlueprintItem;
import net.silentchaos512.gear.item.gear.GearCurioItem;
import net.silentchaos512.gear.util.Const;

import java.util.Arrays;
import java.util.Comparator;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(DataGenerator generatorIn, BlockTagsProvider blocks, ExistingFileHelper existingFileHelper) {
        super(generatorIn, blocks, SilentGear.MOD_ID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Silent Gear - Item Tags";
    }

    @SuppressWarnings("OverlyLongMethod")
    @Override
    public void addTags() {
        // Forge
        copy(ModTags.Blocks.ORES_TITANITE, ModTags.Items.ORES_TITANITE);
        copy(ModTags.Blocks.ORES_AZURE_SILVER, ModTags.Items.ORES_AZURE_SILVER);
        copy(Tags.Blocks.ORES, Tags.Items.ORES);

        copy(ModTags.Blocks.STORAGE_BLOCKS_BLAZE_GOLD, ModTags.Items.STORAGE_BLOCKS_BLAZE_GOLD);
        copy(ModTags.Blocks.STORAGE_BLOCKS_AZURE_SILVER, ModTags.Items.STORAGE_BLOCKS_AZURE_SILVER);
        copy(ModTags.Blocks.STORAGE_BLOCKS_AZURE_ELECTRUM, ModTags.Items.STORAGE_BLOCKS_AZURE_ELECTRUM);
        copy(ModTags.Blocks.STORAGE_BLOCKS_TYRIAN_STEEL, ModTags.Items.STORAGE_BLOCKS_TYRIAN_STEEL);
        copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);

        builder(ModTags.Items.STORAGE_BLOCKS_RAW_AZURE_SILVER, ModBlocks.RAW_AZURE_SILVER_BLOCK);

        builder(ModTags.Items.RAW_MATERIALS_AZURE_SILVER, CraftingItems.RAW_AZURE_SILVER);

        builder(ModTags.Items.CHUNKS_AZURE_SILVER, CraftingItems.RAW_AZURE_SILVER);
        builder(ModTags.Items.COAL_GENERATOR_FUELS, ModItems.NETHERWOOD_CHARCOAL, ModBlocks.NETHERWOOD_CHARCOAL_BLOCK);

        builder(ModTags.Items.DUSTS_BLAZE_GOLD, CraftingItems.BLAZE_GOLD_DUST);
        builder(ModTags.Items.DUSTS_AZURE_SILVER, CraftingItems.AZURE_SILVER_DUST);
        builder(ModTags.Items.DUSTS_AZURE_ELECTRUM, CraftingItems.AZURE_ELECTRUM_DUST);
        builder(ModTags.Items.DUSTS_TYRIAN_STEEL, CraftingItems.TYRIAN_STEEL_DUST);
        builder(ModTags.Items.DUSTS_STARMETAL, CraftingItems.STARMETAL_DUST);
        getBuilder(Tags.Items.DUSTS)
                .addTag(ModTags.Items.DUSTS_BLAZE_GOLD)
                .addTag(ModTags.Items.DUSTS_AZURE_SILVER)
                .addTag(ModTags.Items.DUSTS_AZURE_ELECTRUM)
                .addTag(ModTags.Items.DUSTS_TYRIAN_STEEL)
                .addTag(ModTags.Items.DUSTS_STARMETAL);

        builder(ModTags.Items.GEMS_TITANITE, ModItems.TITANITE_SHARD, ModItems.TITANITE_SHARD_CHARGED, ModItems.TITANITE_SHARD_FROSTY, ModItems.TITANITE_SHARD_SMOLDERING);
        getBuilder(Tags.Items.GEMS)
                .addTag(ModTags.Items.GEMS_TITANITE);

        builder(ModTags.Items.INGOTS_BLAZE_GOLD, CraftingItems.BLAZE_GOLD_INGOT);
        builder(ModTags.Items.INGOTS_AZURE_SILVER, CraftingItems.AZURE_SILVER_INGOT);
        builder(ModTags.Items.INGOTS_AZURE_ELECTRUM, CraftingItems.AZURE_ELECTRUM_INGOT);
        builder(ModTags.Items.INGOTS_TYRIAN_STEEL, CraftingItems.TYRIAN_STEEL_INGOT);
        getBuilder(Tags.Items.INGOTS)
                .addTag(ModTags.Items.INGOTS_BLAZE_GOLD)
                .addTag(ModTags.Items.INGOTS_AZURE_SILVER)
                .addTag(ModTags.Items.INGOTS_AZURE_ELECTRUM)
                .addTag(ModTags.Items.INGOTS_TYRIAN_STEEL);

        builder(ModTags.Items.NUGGETS_BLAZE_GOLD, CraftingItems.BLAZE_GOLD_NUGGET);
        builder(ModTags.Items.NUGGETS_AZURE_SILVER, CraftingItems.AZURE_SILVER_NUGGET);
        builder(ModTags.Items.NUGGETS_AZURE_ELECTRUM, CraftingItems.AZURE_ELECTRUM_NUGGET);
        builder(ModTags.Items.NUGGETS_TYRIAN_STEEL, CraftingItems.TYRIAN_STEEL_NUGGET);
        builder(ModTags.Items.NUGGETS_DIAMOND, CraftingItems.DIAMOND_SHARD);
        builder(ModTags.Items.NUGGETS_EMERALD, CraftingItems.EMERALD_SHARD);
        getBuilder(Tags.Items.NUGGETS)
                .addTag(ModTags.Items.NUGGETS_BLAZE_GOLD)
                .addTag(ModTags.Items.NUGGETS_AZURE_SILVER)
                .addTag(ModTags.Items.NUGGETS_AZURE_ELECTRUM)
                .addTag(ModTags.Items.NUGGETS_TYRIAN_STEEL)
                .addTag(ModTags.Items.NUGGETS_DIAMOND)
                .addTag(ModTags.Items.NUGGETS_EMERALD);

        builder(ModTags.Items.RODS_IRON, CraftingItems.IRON_ROD);
        builder(ModTags.Items.RODS_NETHERWOOD, CraftingItems.NETHERWOOD_STICK);
        builder(ModTags.Items.RODS_ROUGH, CraftingItems.ROUGH_ROD);
        builder(ModTags.Items.RODS_STONE, CraftingItems.STONE_ROD);
        builder(Tags.Items.RODS_WOODEN, CraftingItems.NETHERWOOD_STICK);
        getBuilder(Tags.Items.RODS)
                .addTag(ModTags.Items.RODS_IRON)
                .addTag(ModTags.Items.RODS_NETHERWOOD)
                .addTag(ModTags.Items.RODS_ROUGH)
                .addTag(ModTags.Items.RODS_STONE);

        builder(ModTags.Items.PAPER, Items.PAPER);
        builder(ModTags.Items.BLUEPRINT_PAPER, CraftingItems.BLUEPRINT_PAPER);
        builder(ModTags.Items.TEMPLATE_BOARDS, CraftingItems.TEMPLATE_BOARD);

        builder(ModTags.Items.FRUITS, ModItems.NETHER_BANANA);
        builder(Tags.Items.STRING, CraftingItems.FLAX_STRING, CraftingItems.SINEW_FIBER);

        builder(ModTags.Items.AXES, ModItems.AXE, ModItems.SAW, ModItems.MACHETE, ModItems.MATTOCK, ModItems.PAXEL);
        builder(ModTags.Items.BOOTS, ModItems.BOOTS);
        builder(ModTags.Items.BOWS, ModItems.BOW);
        builder(ModTags.Items.CHESTPLATES, ModItems.CHESTPLATE);
        builder(ModTags.Items.CROSSBOWS, ModItems.CROSSBOW);
        builder(ModTags.Items.ELYTRA, ModItems.ELYTRA);
        builder(ModTags.Items.HAMMERS, ModItems.HAMMER, ModItems.PROSPECTOR_HAMMER);
        builder(ModTags.Items.HELMETS, ModItems.HELMET);
        builder(ModTags.Items.HOES, ModItems.MATTOCK);
        builder(ModTags.Items.KNIVES, ModItems.KNIFE, ModItems.DAGGER);
        getBuilder(makeWrapper("forge", "tools/knives")).addTag(ModTags.Items.KNIVES); // alt tag that some mods are apparently using :(
        builder(ModTags.Items.LEGGINGS, ModItems.LEGGINGS);
        builder(ModTags.Items.PICKAXES, ModItems.HAMMER, ModItems.PAXEL, ModItems.PICKAXE);
        builder(ModTags.Items.SAWS, ModItems.SAW);
        builder(Tags.Items.SHEARS, ModItems.SHEARS);
        builder(ModTags.Items.SHIELDS, ModItems.SHIELD);
        builder(ModTags.Items.SHOVELS, ModItems.EXCAVATOR, ModItems.MATTOCK, ModItems.PAXEL, ModItems.SHOVEL);
        builder(ModTags.Items.SICKLES, ModItems.SICKLE);
        builder(ModTags.Items.SWORDS, ModItems.DAGGER, ModItems.KATANA, ModItems.MACHETE, ModItems.SWORD);

        // Minecraft
        copy(BlockTags.LEAVES, ItemTags.LEAVES);
        copy(BlockTags.LOGS, ItemTags.LOGS);
        copy(BlockTags.PLANKS, ItemTags.PLANKS);
        copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
        copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
        copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
        copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
        copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
        copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);

        getBuilder(ItemTags.ARROWS).add(ModItems.ARROW.get());
        builder(ItemTags.PIGLIN_LOVED,
                ModBlocks.BLAZE_GOLD_BLOCK,
                ModItems.GOLDEN_NETHER_BANANA,
                CraftingItems.BLAZE_GOLD_DUST,
                CraftingItems.BLAZE_GOLD_INGOT);

        builder(ItemTags.CLUSTER_MAX_HARVESTABLES, ModItems.PICKAXE, ModItems.PAXEL, ModItems.PROSPECTOR_HAMMER);

        // Silent Gear

        copy(ModTags.Blocks.NETHERWOOD_LOGS, ModTags.Items.NETHERWOOD_LOGS);

        getBuilder(ModTags.Items.STARLIGHT_CHARGER_CATALYSTS_TIER_1).addTag(ModTags.Items.DUSTS_BLAZE_GOLD);
        getBuilder(ModTags.Items.STARLIGHT_CHARGER_CATALYSTS_TIER_2).addTag(ModTags.Items.DUSTS_AZURE_SILVER);
        getBuilder(ModTags.Items.STARLIGHT_CHARGER_CATALYSTS_TIER_3).addTag(ModTags.Items.DUSTS_STARMETAL);
        getBuilder(ModTags.Items.STARLIGHT_CHARGER_CATALYSTS)
                .addTag(ModTags.Items.STARLIGHT_CHARGER_CATALYSTS_TIER_1)
                .addTag(ModTags.Items.STARLIGHT_CHARGER_CATALYSTS_TIER_2)
                .addTag(ModTags.Items.STARLIGHT_CHARGER_CATALYSTS_TIER_3);

        // Blueprints
        Multimap<ResourceLocation, AbstractBlueprintItem> blueprints = MultimapBuilder.linkedHashKeys().arrayListValues().build();
        ForgeRegistries.ITEMS.getValues().stream()
                .filter(item -> item instanceof AbstractBlueprintItem)
                .map(item -> (AbstractBlueprintItem) item)
                .sorted(Comparator.comparing(blueprint -> blueprint.getItemTag().location()))
                .forEach(item -> blueprints.put(item.getItemTag().location(), item));
        TagsProvider.TagAppender<Item> blueprintsBuilder = getBuilder(ModTags.Items.BLUEPRINTS);
        blueprints.keySet().forEach(tagId -> {
            TagKey<Item> tag = ItemTags.create(tagId);
            getBuilder(tag).add(blueprints.get(tagId).toArray(new Item[0]));
            blueprintsBuilder.addTag(tag);
        });

        // Curios
        Registration.getItems(GearCurioItem.class).forEach(item ->
                builder(makeWrapper(Const.CURIOS, item.getSlot()), item));

        builder(makeWrapper(Const.CURIOS, "back"), ModItems.ELYTRA);
    }

    private TagKey<Item> makeWrapper(String namespace, String path) {
        return ItemTags.create(new ResourceLocation(namespace, path));
    }

    private void builder(TagKey<Item> tag, ItemLike... items) {
        getBuilder(tag).add(Arrays.stream(items).map(ItemLike::asItem).toArray(Item[]::new));
    }

    protected TagsProvider.TagAppender<Item> getBuilder(TagKey<Item> tag) {
        return tag(tag);
    }
}
