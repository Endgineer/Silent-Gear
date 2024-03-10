package net.silentchaos512.gear.init;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.RegistryObject;
import net.silentchaos512.gear.block.charger.ChargerContainer;
import net.silentchaos512.gear.block.charger.ChargerScreen;
import net.silentchaos512.gear.block.press.MetalPressContainer;
import net.silentchaos512.gear.block.press.MetalPressScreen;
import net.silentchaos512.gear.block.salvager.SalvagerContainer;
import net.silentchaos512.gear.block.salvager.SalvagerScreen;
import net.silentchaos512.gear.item.blueprint.book.BlueprintBookContainer;
import net.silentchaos512.gear.item.blueprint.book.BlueprintBookContainerScreen;

public final class ModContainers {
    public static final RegistryObject<MenuType<MetalPressContainer>> METAL_PRESS = register("metal_press",
            MetalPressContainer::new);

    public static final RegistryObject<MenuType<SalvagerContainer>> SALVAGER = register("salvager",
            SalvagerContainer::new);

    public static final RegistryObject<MenuType<ChargerContainer>> STARLIGHT_CHARGER = register("starlight_charger",
            ChargerContainer::createStarlightCharger);

    public static final RegistryObject<MenuType<BlueprintBookContainer>> BLUEPRINT_BOOK = register("blueprint_book",
            BlueprintBookContainer::new);

    private ModContainers() {}

    static void register() {}

    @OnlyIn(Dist.CLIENT)
    public static void registerScreens(FMLClientSetupEvent event) {
        MenuScreens.register(METAL_PRESS.get(), MetalPressScreen::new);
        MenuScreens.register(SALVAGER.get(), SalvagerScreen::new);
        MenuScreens.register(STARLIGHT_CHARGER.get(), ChargerScreen::new);

        MenuScreens.register(BLUEPRINT_BOOK.get(), BlueprintBookContainerScreen::new);
    }

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> register(String name, IContainerFactory<T> factory) {
        return Registration.CONTAINERS.register(name, () -> IForgeMenuType.create(factory));
    }
}
