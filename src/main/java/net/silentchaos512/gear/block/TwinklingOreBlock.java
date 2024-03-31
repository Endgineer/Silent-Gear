package net.silentchaos512.gear.block;

import java.util.Random;
import com.mojang.math.Vector3f;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class TwinklingOreBlock extends Block {
    public static final Vector3f TWINKLE_PARTICLE_COLOR = new Vector3f(Vec3.fromRGB24(16777215));
    public static final DustParticleOptions TWINKLE = new DustParticleOptions(TWINKLE_PARTICLE_COLOR, 1.0F);

    private final UniformInt xpRange;

    public TwinklingOreBlock() {
        super(BlockBehaviour.Properties.of(Material.STONE)
            .requiresCorrectToolForDrops()
            .color(MaterialColor.DEEPSLATE)
            .strength(4.5F, 1200.0F)
            .sound(SoundType.DEEPSLATE));
        this.xpRange = UniformInt.of(3, 7);
    }

    @Override
    public int getExpDrop(BlockState state, net.minecraft.world.level.LevelReader reader, BlockPos pos, int fortune, int silktouch) {
        return this.xpRange.sample(RANDOM);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, Random rng) {
        if(rng.nextFloat() <= 0.5) {
            twinkle(level, pos);
        }
    }

    private static void twinkle(Level level, BlockPos pos) {
        Random random = level.random;

        for(Direction direction : Direction.values()) {
            BlockPos blockpos = pos.relative(direction);
            if(!level.getBlockState(blockpos).isSolidRender(level, blockpos)) {
                Direction.Axis direction$axis = direction.getAxis();
                double d1 = direction$axis == Direction.Axis.X ? 0.5D + 0.5625D * (double) direction.getStepX() : (double) random.nextFloat();
                double d2 = direction$axis == Direction.Axis.Y ? 0.5D + 0.5625D * (double) direction.getStepY() : (double) random.nextFloat();
                double d3 = direction$axis == Direction.Axis.Z ? 0.5D + 0.5625D * (double) direction.getStepZ() : (double) random.nextFloat();
                level.addParticle(TwinklingOreBlock.TWINKLE, (double) pos.getX() + d1, (double) pos.getY() + d2, (double) pos.getZ() + d3, 0.0D, 0.0D, 0.0D);
                level.playLocalSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 1.0F, 5.0F, true);
            }
        }
    }
}
