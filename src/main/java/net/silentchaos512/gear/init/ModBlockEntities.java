package net.silentchaos512.gear.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import net.silentchaos512.gear.SilentGear;
import net.silentchaos512.gear.block.anvil.TitaniteAnvilBlockEntity;
import net.silentchaos512.gear.client.renderer.TitaniteAnvilBlockEntityRenderer;

public final class ModBlockEntities {
    public static final RegistryObject<BlockEntityType<TitaniteAnvilBlockEntity>> TITANITE_ANVIL_BLOCK_ENTITY = Registration.BLOCK_ENTITIES.register(
        "titanite_anvil_block_entity",
        () -> BlockEntityType.Builder.of(
            TitaniteAnvilBlockEntity::new,
            ModBlocks.TITANITE_ANVIL.get()
        ).build(null)
    );

    private ModBlockEntities() {}

    static void register() {}
    
    @Mod.EventBusSubscriber(modid = SilentGear.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Events {
        @OnlyIn(Dist.CLIENT)
        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModBlockEntities.TITANITE_ANVIL_BLOCK_ENTITY.get(), TitaniteAnvilBlockEntityRenderer::new);
        }
    }
}
