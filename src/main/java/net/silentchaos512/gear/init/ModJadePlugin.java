package net.silentchaos512.gear.init;

import mcp.mobius.waila.api.IWailaClientRegistration;
import mcp.mobius.waila.api.IWailaCommonRegistration;
import mcp.mobius.waila.api.IWailaPlugin;
import mcp.mobius.waila.api.TooltipPosition;
import mcp.mobius.waila.api.WailaPlugin;
import net.silentchaos512.gear.block.AbyssOreBlock;
import net.silentchaos512.gear.client.AbyssOreComponentProvider;

@WailaPlugin
public class ModJadePlugin implements IWailaPlugin {
    @Override
    public void register(IWailaCommonRegistration registration) {}

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerComponentProvider(AbyssOreComponentProvider.INSTANCE, TooltipPosition.BODY, AbyssOreBlock.class);
    }
}
