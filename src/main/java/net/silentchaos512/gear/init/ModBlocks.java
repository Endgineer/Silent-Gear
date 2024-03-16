package net.silentchaos512.gear.init;

import net.silentchaos512.gear.block.TitaniteAnvilBlock;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.silentchaos512.gear.SilentGear;
import net.silentchaos512.gear.block.*;
import net.silentchaos512.gear.config.Config;
import net.silentchaos512.lib.registry.BlockRegistryObject;

import javax.annotation.Nullable;

import com.simibubi.create.AllBlocks;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = SilentGear.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModBlocks {
    public static final BlockRegistryObject<Block> DEEPSLATE_TITANITE_ORE = register("deepslate_titanite_ore", () -> new TwinklingOreBlock(
        BlockBehaviour.Properties.of(Material.STONE)
            .requiresCorrectToolForDrops()
            .color(MaterialColor.DEEPSLATE)
            .strength(4.5F, 1200.0F)
            .sound(SoundType.DEEPSLATE)
            .lightLevel((state) -> state.getValue(TwinklingOreBlock.CHARGE)), UniformInt.of(3, 7)
    ));
	
    public static final BlockRegistryObject<TitaniteAnvilBlock> TITANITE_ANVIL = register("titanite_anvil", () -> new TitaniteAnvilBlock(
        BlockBehaviour.Properties.copy(AllBlocks.RAILWAY_CASING.get()).noOcclusion()
    ));

    private static final Map<Block, Block> STRIPPED_WOOD = new HashMap<>();

    private ModBlocks() {}

    static void register() {}

    @OnlyIn(Dist.CLIENT)
    public static void registerRenderTypes(FMLClientSetupEvent event) {
    }

    private static <T extends Block> BlockRegistryObject<T> registerNoItem(String name, Supplier<T> block) {
        return new BlockRegistryObject<>(Registration.BLOCKS.register(name, block));
    }

    private static <T extends Block> BlockRegistryObject<T> register(String name, Supplier<T> block) {
        return register(name, block, ModBlocks::defaultItem);
    }

    private static <T extends Block> BlockRegistryObject<T> register(String name, Supplier<T> block, Function<BlockRegistryObject<T>, Supplier<? extends BlockItem>> item) {
        BlockRegistryObject<T> ret = registerNoItem(name, block);
        Registration.ITEMS.register(name, item.apply(ret));
        return ret;
    }

    private static <T extends Block> Supplier<BlockItem> defaultItem(BlockRegistryObject<T> block) {
        return () -> new BlockItem(block.get(), new Item.Properties().tab(SilentGear.ITEM_GROUP));
    }
}
