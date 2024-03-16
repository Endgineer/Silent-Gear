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
    }

    @Override
    public void fenceBlock(FenceBlock block, ResourceLocation texture) {
        super.fenceBlock(block, texture);
        models().withExistingParent(NameUtils.from(block).getPath() + "_inventory", mcLoc("block/fence_inventory"))
                .texture("texture", texture);
    }
}
