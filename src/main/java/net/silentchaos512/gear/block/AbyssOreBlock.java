package net.silentchaos512.gear.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.ForgeHooks;
import net.silentchaos512.gear.api.GearApi;
import net.silentchaos512.gear.api.stats.ItemStats;
import net.silentchaos512.gear.item.gear.GearPickaxeItem;
import net.silentchaos512.gear.util.GearData;

public class AbyssOreBlock extends OreBlock {
    float requiredHarvestLevel;

    public AbyssOreBlock(float requiredHarvestLevel) {
        super(Properties.of(Material.STONE).requiresCorrectToolForDrops().color(MaterialColor.DEEPSLATE).strength(4.5F, 1200.0F).sound(SoundType.DEEPSLATE));
        this.requiredHarvestLevel = requiredHarvestLevel;
    }

    @Override
    public boolean canHarvestBlock(BlockState state, BlockGetter level, BlockPos pos, Player player) {
        ItemStack stack = player.getMainHandItem();

        if(!(stack.getItem() instanceof GearPickaxeItem)) { return false; }

        boolean isMaxReinforce = !GearData.getPrimaryMainMaterial(stack).getGrade().isNotMax();
        float harvestLevel = GearApi.getStat(stack, ItemStats.HARVEST_LEVEL);

        boolean harvestabilitySatisfied = isMaxReinforce && harvestLevel == this.requiredHarvestLevel || harvestLevel > this.requiredHarvestLevel;

        return ForgeHooks.isCorrectToolForDrops(state, player) && harvestabilitySatisfied;
    }
}
