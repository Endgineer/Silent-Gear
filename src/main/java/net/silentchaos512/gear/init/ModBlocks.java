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

    public static final BlockRegistryObject<ModCropBlock> FLAX_PLANT = registerNoItem("flax_plant", () ->
            new ModCropBlock(ModItems.FLAX_SEEDS::get, BlockBehaviour.Properties.of(Material.PLANT)
                    .strength(0)
                    .noCollission()
                    .randomTicks()
                    .sound(SoundType.CROP)));
    public static final BlockRegistryObject<BushBlock> WILD_FLAX_PLANT = registerNoItem("wild_flax_plant", () ->
            new BushBlock(BlockBehaviour.Properties.of(Material.PLANT)
                    .strength(0)
                    .noCollission()
                    .sound(SoundType.CROP)));

    public static final BlockRegistryObject<Block> NETHERWOOD_CHARCOAL_BLOCK = register("netherwood_charcoal_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(5, 6)),
            bro -> () -> new BlockItem(bro.get(), new Item.Properties().tab(SilentGear.ITEM_GROUP)) {
                @Override
                public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                    return 10 * Config.Common.netherwoodCharcoalBurnTime.get();
                }
            });

    public static final BlockRegistryObject<WoodBlock> NETHERWOOD_LOG = register("netherwood_log", () ->
            new WoodBlock(STRIPPED_WOOD::get, netherWoodProps(2f, 2f)));
    public static final BlockRegistryObject<RotatedPillarBlock> STRIPPED_NETHERWOOD_LOG = register("stripped_netherwood_log", () ->
            new RotatedPillarBlock(netherWoodProps(2f, 2f)));
    public static final BlockRegistryObject<WoodBlock> NETHERWOOD_WOOD = register("netherwood_wood", () ->
            new WoodBlock(STRIPPED_WOOD::get, netherWoodProps(2f, 2f)));
    public static final BlockRegistryObject<RotatedPillarBlock> STRIPPED_NETHERWOOD_WOOD = register("stripped_netherwood_wood", () ->
            new RotatedPillarBlock(netherWoodProps(2f, 2f)));

    public static final BlockRegistryObject<Block> NETHERWOOD_PLANKS = register("netherwood_planks", () ->
            new Block(netherWoodProps(2f, 3f)));
    public static final BlockRegistryObject<SlabBlock> NETHERWOOD_SLAB = register("netherwood_slab", () ->
            new SlabBlock(netherWoodProps(2f, 3f)));
    public static final BlockRegistryObject<StairBlock> NETHERWOOD_STAIRS = register("netherwood_stairs", () ->
            new StairBlock(NETHERWOOD_PLANKS::asBlockState, netherWoodProps(2f, 3f)));
    public static final BlockRegistryObject<FenceBlock> NETHERWOOD_FENCE = register("netherwood_fence", () ->
            new FenceBlock(netherWoodProps(2f, 3f)));
    public static final BlockRegistryObject<FenceGateBlock> NETHERWOOD_FENCE_GATE = register("netherwood_fence_gate", () ->
            new FenceGateBlock(netherWoodProps(2f, 3f)));
    public static final BlockRegistryObject<DoorBlock> NETHERWOOD_DOOR = register("netherwood_door", () ->
            new DoorBlock(netherWoodProps(3f, 3f).noOcclusion()));
    public static final BlockRegistryObject<TrapDoorBlock> NETHERWOOD_TRAPDOOR = register("netherwood_trapdoor", () ->
            new TrapDoorBlock(netherWoodProps(3f, 3f).noOcclusion()));
    public static final BlockRegistryObject<LeavesBlock> NETHERWOOD_LEAVES = register("netherwood_leaves", () ->
            new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES)
                    .strength(0.2f)
                    .randomTicks()
                    .noOcclusion()
                    .sound(SoundType.GRASS)));
    public static final BlockRegistryObject<NetherwoodSapling> NETHERWOOD_SAPLING = register("netherwood_sapling", () ->
            new NetherwoodSapling(BlockBehaviour.Properties.of(Material.PLANT)
                    .strength(0)
                    .noCollission()
                    .randomTicks()
                    .sound(SoundType.GRASS)));

    public static final BlockRegistryObject<FlowerPotBlock> POTTED_NETHERWOOD_SAPLING = registerNoItem("potted_netherwood_sapling", () ->
            makePottedPlant(NETHERWOOD_SAPLING));

    private ModBlocks() {}

    static void register() {}

    @OnlyIn(Dist.CLIENT)
    public static void registerRenderTypes(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(FLAX_PLANT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(NETHERWOOD_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(NETHERWOOD_SAPLING.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(NETHERWOOD_TRAPDOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(POTTED_NETHERWOOD_SAPLING.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WILD_FLAX_PLANT.get(), RenderType.cutout());
    }

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        STRIPPED_WOOD.put(NETHERWOOD_LOG.get(), STRIPPED_NETHERWOOD_LOG.get());
        STRIPPED_WOOD.put(NETHERWOOD_WOOD.get(), STRIPPED_NETHERWOOD_WOOD.get());
    }

    private static OreBlock getOre(SoundType soundType) {
        return new ModOreBlock(BlockBehaviour.Properties.of(Material.STONE)
                .strength(4, 10)
                .requiresCorrectToolForDrops()
                .sound(soundType));
    }

    private static Block getRawOreBlock(SoundType soundType) {
        return new ModOreBlock(BlockBehaviour.Properties.of(Material.STONE)
                .strength(4, 20)
                .requiresCorrectToolForDrops()
                .sound(soundType));
    }

    private static Block getStorageBlock() {
        return new Block(BlockBehaviour.Properties.of(Material.METAL)
                .strength(3, 6)
                .sound(SoundType.METAL));
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

    @SuppressWarnings("SameParameterValue")
    private static FlowerPotBlock makePottedPlant(Supplier<? extends Block> flower) {
        FlowerPotBlock potted = new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT.delegate.get(), flower, Block.Properties.of(Material.DECORATION).strength(0));
        ResourceLocation flowerId = Objects.requireNonNull(flower.get().getRegistryName());
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(flowerId, () -> potted);
        return potted;
    }

    private static BlockBehaviour.Properties netherWoodProps(float hardnessIn, float resistanceIn) {
        return BlockBehaviour.Properties.of(Material.NETHER_WOOD)
                .strength(hardnessIn, resistanceIn)
                .sound(SoundType.WOOD);
    }
}
