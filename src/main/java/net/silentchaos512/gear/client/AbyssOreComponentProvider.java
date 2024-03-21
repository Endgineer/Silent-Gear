package net.silentchaos512.gear.client;

import mcp.mobius.waila.api.BlockAccessor;
import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.ITooltip;
import mcp.mobius.waila.api.config.IPluginConfig;
import net.silentchaos512.gear.block.AbyssOreBlock;
import net.silentchaos512.gear.util.TextUtil;

public enum AbyssOreComponentProvider implements IComponentProvider {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        int requiredHarvestLevel = ((AbyssOreBlock) accessor.getBlock()).getRequiredHarvestLevel();
        tooltip.append(TextUtil.misc("harvestLevel", TextUtil.misc("harvestLevel." + requiredHarvestLevel)).append(requiredHarvestLevel < 10 ? " " : " §6+5§7 "));
    }
}
