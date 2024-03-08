package net.silentchaos512.gear.event;

import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.silentchaos512.gear.SilentGear;
import net.silentchaos512.gear.api.item.ICoreItem;
import net.silentchaos512.gear.gear.part.PartData;
import net.silentchaos512.gear.util.GearData;

@Mod.EventBusSubscriber(modid = SilentGear.MOD_ID)
public final class RepairHandler {
    private RepairHandler() {}

    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event) {
        if (event.getLeft().getItem() instanceof ICoreItem) {
            event.setCanceled(true);
        }
    }

    private static void handleUpgradeApplication(AnvilUpdateEvent event, PartData part) {
        ItemStack result = event.getLeft().copy();
        applyName(event, result);

        GearData.addUpgradePart(result, part);
        GearData.recalculateStats(result, null);

        event.setOutput(result);
        event.setCost(3);
    }

    private static void applyName(AnvilUpdateEvent event, ItemStack stack) {
        if (!event.getName().isEmpty()) {
            stack.setHoverName(new TextComponent(event.getName()));
        }
    }
}
