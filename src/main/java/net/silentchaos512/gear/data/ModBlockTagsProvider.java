package net.silentchaos512.gear.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.silentchaos512.gear.SilentGear;
import net.silentchaos512.gear.init.ModBlocks;
import net.silentchaos512.gear.init.ModTags;
import net.silentchaos512.lib.block.IBlockProvider;

import java.util.Arrays;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, SilentGear.MOD_ID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Silent Gear - Block Tags";
    }

    @Override
    public void addTags() {
        // Harvesting
        tag(BlockTags.NEEDS_IRON_TOOL)
                .addTag(ModTags.Blocks.ORES_TITANITE)
                .add(ModBlocks.DEEPSLATE_TITANITE_ORE.get())
                .add(ModBlocks.TITANITE_ANVIL.get());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .addTag(ModTags.Blocks.NETHERWOOD_LOGS)
                .add(ModBlocks.NETHERWOOD_PLANKS.get())
                .add(ModBlocks.NETHERWOOD_SLAB.get())
                .add(ModBlocks.NETHERWOOD_STAIRS.get())
                .add(ModBlocks.NETHERWOOD_FENCE.get())
                .add(ModBlocks.NETHERWOOD_FENCE_GATE.get())
                .add(ModBlocks.NETHERWOOD_DOOR.get())
                .add(ModBlocks.NETHERWOOD_TRAPDOOR.get());
        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.NETHERWOOD_LEAVES.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addTag(ModTags.Blocks.ORES_TITANITE)
                .add(ModBlocks.NETHERWOOD_CHARCOAL_BLOCK.get())
                .add(ModBlocks.DEEPSLATE_TITANITE_ORE.get())
                .add(ModBlocks.TITANITE_ANVIL.get());

        // Silent Gear
        getBuilder(ModTags.Blocks.NETHERWOOD_LOGS)
                .add(ModBlocks.NETHERWOOD_LOG.get())
                .add(ModBlocks.STRIPPED_NETHERWOOD_LOG.get())
                .add(ModBlocks.NETHERWOOD_WOOD.get())
                .add(ModBlocks.STRIPPED_NETHERWOOD_WOOD.get());
        getBuilder(ModTags.Blocks.NETHERWOOD_SOIL)
                .addTag(Tags.Blocks.NETHERRACK)
                .addTag(BlockTags.DIRT)
                .add(Blocks.FARMLAND);
        getBuilder(ModTags.Blocks.PROSPECTOR_HAMMER_TARGETS)
                .add(Blocks.ANCIENT_DEBRIS)
                .addTag(Tags.Blocks.ORES);

        // Forge
        builder(ModTags.Blocks.ORES_TITANITE, ModBlocks.DEEPSLATE_TITANITE_ORE);
        getBuilder(Tags.Blocks.ORES)
                .addTag(ModTags.Blocks.ORES_TITANITE);

        // Minecraft
        builder(BlockTags.LEAVES, ModBlocks.NETHERWOOD_LEAVES);
        getBuilder(BlockTags.LOGS).addTag(ModTags.Blocks.NETHERWOOD_LOGS);
        builder(BlockTags.PLANKS, ModBlocks.NETHERWOOD_PLANKS);
        builder(BlockTags.SAPLINGS, ModBlocks.NETHERWOOD_SAPLING);
        builder(BlockTags.WOODEN_DOORS, ModBlocks.NETHERWOOD_DOOR);
        builder(BlockTags.WOODEN_FENCES, ModBlocks.NETHERWOOD_FENCE);
        builder(BlockTags.WOODEN_SLABS, ModBlocks.NETHERWOOD_SLAB);
        builder(BlockTags.WOODEN_STAIRS, ModBlocks.NETHERWOOD_STAIRS);
        builder(BlockTags.WOODEN_TRAPDOORS, ModBlocks.NETHERWOOD_TRAPDOOR);
    }

    private void builder(TagKey<Block> tag, IBlockProvider... items) {
        getBuilder(tag).add(Arrays.stream(items).map(IBlockProvider::asBlock).toArray(Block[]::new));
    }

    protected TagsProvider.TagAppender<Block> getBuilder(TagKey<Block> tag) {
        return tag(tag);
    }
}
