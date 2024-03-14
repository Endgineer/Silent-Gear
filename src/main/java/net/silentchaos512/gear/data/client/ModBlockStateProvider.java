package net.silentchaos512.gear.data.client;

import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.silentchaos512.gear.SilentGear;
import net.silentchaos512.gear.block.ModCropBlock;
import net.silentchaos512.gear.init.ModBlocks;
import net.silentchaos512.lib.util.NameUtils;

import javax.annotation.Nonnull;

import static net.minecraftforge.client.model.generators.ModelProvider.BLOCK_FOLDER;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, SilentGear.MOD_ID, exFileHelper);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Silent Gear - Block States/Models";
    }

    @Override
    protected void registerStatesAndModels() {
        // Netherwood
        simpleBlock(ModBlocks.NETHERWOOD_CHARCOAL_BLOCK.get());
        simpleBlock(ModBlocks.NETHERWOOD_PLANKS.get());
        simpleBlock(ModBlocks.NETHERWOOD_LEAVES.get());
        axisBlock(ModBlocks.NETHERWOOD_LOG.get(), modLoc("block/netherwood_log"), modLoc("block/netherwood_log_top"));
        axisBlock(ModBlocks.STRIPPED_NETHERWOOD_LOG.get(), modLoc("block/stripped_netherwood_log"), modLoc("block/stripped_netherwood_log_top"));
        simpleBlock(ModBlocks.NETHERWOOD_WOOD.get(), models().cubeAll("netherwood_wood", modLoc("block/netherwood_log")));
        simpleBlock(ModBlocks.STRIPPED_NETHERWOOD_WOOD.get(), models().cubeAll("stripped_netherwood_wood", modLoc("block/stripped_netherwood_log")));
        simpleBlock(ModBlocks.NETHERWOOD_SAPLING.get(), models().cross("netherwood_sapling", modLoc("block/netherwood_sapling")));
        ResourceLocation planks = modLoc("block/netherwood_planks");
        slabBlock(ModBlocks.NETHERWOOD_SLAB.get(), planks, planks);
        stairsBlock(ModBlocks.NETHERWOOD_STAIRS.get(), planks);
        fenceBlock(ModBlocks.NETHERWOOD_FENCE.get(), planks);
        fenceGateBlock(ModBlocks.NETHERWOOD_FENCE_GATE.get(), planks);
        doorBlock(ModBlocks.NETHERWOOD_DOOR.get(), modLoc("block/netherwood_door_bottom"), modLoc("block/netherwood_door_top"));
        trapdoorBlock(ModBlocks.NETHERWOOD_TRAPDOOR.get(), modLoc("block/netherwood_trapdoor"), true);

        // Oddballs
        simpleBlock(ModBlocks.POTTED_NETHERWOOD_SAPLING.get(), models()
                .withExistingParent("potted_netherwood_sapling", "block/flower_pot_cross")
                .texture("plant", "block/netherwood_sapling"));

        // Plants
        getVariantBuilder(ModBlocks.FLAX_PLANT.get()).forAllStates(state -> {
            int i = cropAgeToIndex(state.getValue(ModCropBlock.AGE));
            return ConfiguredModel.builder()
                    .modelFile(models().crop("flax_plant" + i, modLoc("block/flax_plant" + i)))
                    .build();
        });
        simpleBlock(ModBlocks.WILD_FLAX_PLANT.get(), models().crop("wild_flax_plant", modLoc("block/flax_plant3")));
    }

    private static int cropAgeToIndex(int age) {
        if (age > 6)
            return 3;
        if (age > 3)
            return 2;
        if (age > 1)
            return 1;
        return 0;
    }

    @Override
    public void fenceBlock(FenceBlock block, ResourceLocation texture) {
        super.fenceBlock(block, texture);
        models().withExistingParent(NameUtils.from(block).getPath() + "_inventory", mcLoc("block/fence_inventory"))
                .texture("texture", texture);
    }
}
