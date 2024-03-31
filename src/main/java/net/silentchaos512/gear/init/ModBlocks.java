package net.silentchaos512.gear.init;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
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
    public static final BlockRegistryObject<Block> DEEPSLATE_TITANITE_ORE = register("deepslate_titanite_ore", () -> new TwinklingOreBlock());
	
    public static final BlockRegistryObject<TitaniteAnvilBlock> TITANITE_ANVIL = register("titanite_anvil", () -> new TitaniteAnvilBlock(
        BlockBehaviour.Properties.copy(AllBlocks.RAILWAY_CASING.get()).noOcclusion()
    ));

    public static final BlockRegistryObject<Block> DEEPSLATE_LUMINITE_ORE = register("deepslate_luminite_ore", () -> new AbyssOreBlock(14));
    public static final BlockRegistryObject<Block> DEEPSLATE_DRAKOLITE_ORE = register("deepslate_drakolite_ore", () -> new AbyssOreBlock(18));
    public static final BlockRegistryObject<Block> DEEPSLATE_PHASMATITE_ORE = register("deepslate_phasmatite_ore", () -> new AbyssOreBlock(20));
    public static final BlockRegistryObject<Block> DEEPSLATE_AEDRITE_ORE = register("deepslate_aedrite_ore", () -> new AbyssOreBlock(24));
    public static final BlockRegistryObject<Block> DEEPSLATE_DAEDRITE_ORE = register("deepslate_daedrite_ore", () -> new AbyssOreBlock(24));

    public static final BlockRegistryObject<Block> DEEPSLATE_BLACK_ORE = register("deepslate_black_ore", () -> new AbyssOreBlock(10));
    public static final BlockRegistryObject<Block> DEEPSLATE_WHITE_ORE = register("deepslate_white_ore", () -> new AbyssOreBlock(10));
    public static final BlockRegistryObject<Block> DEEPSLATE_MITHRIL_ORE = register("deepslate_mithril_ore", () -> new AbyssOreBlock(12));
    public static final BlockRegistryObject<Block> DEEPSLATE_ADAMANTITE_ORE = register("deepslate_adamantite_ore", () -> new AbyssOreBlock(14));
    public static final BlockRegistryObject<Block> DEEPSLATE_RUNITE_ORE = register("deepslate_runite_ore", () -> new AbyssOreBlock(16));
    public static final BlockRegistryObject<Block> DEEPSLATE_ORICHALCITE_ORE = register("deepslate_orichalcite_ore", () -> new AbyssOreBlock(18));
    public static final BlockRegistryObject<Block> DEEPSLATE_NECRITE_ORE = register("deepslate_necrite_ore", () -> new AbyssOreBlock(20));
    public static final BlockRegistryObject<Block> DEEPSLATE_BANITE_ORE = register("deepslate_banite_ore", () -> new AbyssOreBlock(22));
    public static final BlockRegistryObject<Block> DEEPSLATE_MORTITE_ORE = register("deepslate_mortite_ore", () -> new AbyssOreBlock(26));
    public static final BlockRegistryObject<Block> DEEPSLATE_DELDRIMITE_ORE = register("deepslate_deldrimite_ore", () -> new AbyssOreBlock(28));
    public static final BlockRegistryObject<Block> DEEPSLATE_ASTRALARIUM_ORE = register("deepslate_astralarium_ore", () -> new AbyssOreBlock(30));

    public static final BlockRegistryObject<Block> DEEPSLATE_NOVITE_ORE = register("deepslate_novite_ore", () -> new AbyssOreBlock(5));
    public static final BlockRegistryObject<Block> DEEPSLATE_BATHUS_ORE = register("deepslate_bathus_ore", () -> new AbyssOreBlock(11));
    public static final BlockRegistryObject<Block> DEEPSLATE_MARMAROS_ORE = register("deepslate_marmaros_ore", () -> new AbyssOreBlock(13));
    public static final BlockRegistryObject<Block> DEEPSLATE_KRATONITE_ORE = register("deepslate_kratonite_ore", () -> new AbyssOreBlock(15));
    public static final BlockRegistryObject<Block> DEEPSLATE_FRACTITE_ORE = register("deepslate_fractite_ore", () -> new AbyssOreBlock(17));
    public static final BlockRegistryObject<Block> DEEPSLATE_ZEPHYRIUM_ORE = register("deepslate_zephyrium_ore", () -> new AbyssOreBlock(19));
    public static final BlockRegistryObject<Block> DEEPSLATE_ARGONITE_ORE = register("deepslate_argonite_ore", () -> new AbyssOreBlock(21));
    public static final BlockRegistryObject<Block> DEEPSLATE_KATAGON_ORE = register("deepslate_katagon_ore", () -> new AbyssOreBlock(23));
    public static final BlockRegistryObject<Block> DEEPSLATE_GORGONITE_ORE = register("deepslate_gorgonite_ore", () -> new AbyssOreBlock(25));
    public static final BlockRegistryObject<Block> DEEPSLATE_PROMETHIUM_ORE = register("deepslate_promethium_ore", () -> new AbyssOreBlock(27));
    public static final BlockRegistryObject<Block> DEEPSLATE_PRIMAL_ORE = register("deepslate_primal_ore", () -> new AbyssOreBlock(29));

    public static final BlockRegistryObject<Block> SALVENETTLE = register("salvenettle", () -> new AbyssFlowerBlock());
    public static final BlockRegistryObject<Block> WILDERCRESS = register("wildercress", () -> new AbyssFlowerBlock());
    public static final BlockRegistryObject<Block> BLIGHTLEAF = register("blightleaf", () -> new AbyssFlowerBlock());
    public static final BlockRegistryObject<Block> ROSEBLOOD = register("roseblood", () -> new AbyssFlowerBlock());
    public static final BlockRegistryObject<Block> BRYLL = register("bryll", () -> new AbyssFlowerBlock());
    public static final BlockRegistryObject<Block> DUSKWEED = register("duskweed", () -> new AbyssFlowerBlock());
    public static final BlockRegistryObject<Block> SOULBELL = register("soulbell", () -> new AbyssFlowerBlock());
    public static final BlockRegistryObject<Block> ECTOGRASS = register("ectograss", () -> new AbyssFlowerBlock());
    public static final BlockRegistryObject<Block> RUNELEAF = register("runeleaf", () -> new AbyssFlowerBlock());
    public static final BlockRegistryObject<Block> SPIRITBLOOM = register("spiritbloom", () -> new AbyssFlowerBlock());
    public static final BlockRegistryObject<Block> CELESTIALBLOSSOM = register("celestialblossom", () -> new AbyssFlowerBlock());

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
