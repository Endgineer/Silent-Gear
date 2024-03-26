package net.silentchaos512.gear.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class AbyssFlowerBlock extends FlowerBlock {
    @SuppressWarnings("deprecation")
    public AbyssFlowerBlock() {
        super(MobEffects.GLOWING, 0, BlockBehaviour.Properties.copy(Blocks.WITHER_ROSE).noOcclusion().sound(SoundType.AMETHYST).lightLevel((state) -> Integer.valueOf(7)));
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.is(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
        return this.mayPlaceOn(reader.getBlockState(pos.below()), reader, pos.below());
    }
}
