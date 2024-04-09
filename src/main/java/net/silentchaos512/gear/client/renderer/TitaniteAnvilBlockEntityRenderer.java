package net.silentchaos512.gear.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.silentchaos512.gear.block.TitaniteAnvilBlock;
import net.silentchaos512.gear.block.anvil.TitaniteAnvilBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

@SuppressWarnings("incomplete-switch")
public class TitaniteAnvilBlockEntityRenderer implements BlockEntityRenderer<TitaniteAnvilBlockEntity> {
    public TitaniteAnvilBlockEntityRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public void render(TitaniteAnvilBlockEntity entity, float partial, PoseStack posestack, MultiBufferSource source, int light, int overlay) {
        ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();
        float angleDegrees = 0;



        ItemStack slotstack = entity.getItem(TitaniteAnvilBlockEntity.SLOT);
        
        posestack.pushPose();
        posestack.translate(0.5F, 1.015625, 0.5F);
        posestack.scale(0.5F, 0.5F, 0.5F);
        posestack.mulPose(Vector3f.XP.rotationDegrees(90));

        switch(entity.getBlockState().getValue(TitaniteAnvilBlock.FACING)) {
            case NORTH -> posestack.mulPose(Vector3f.ZP.rotationDegrees(0+angleDegrees));
            case EAST -> posestack.mulPose(Vector3f.ZP.rotationDegrees(90+angleDegrees));
            case SOUTH -> posestack.mulPose(Vector3f.ZP.rotationDegrees(180+angleDegrees));
            case WEST -> posestack.mulPose(Vector3f.ZP.rotationDegrees(270+angleDegrees));
        }

        renderer.renderStatic(slotstack, ItemTransforms.TransformType.GUI, getLightLevel(entity.getLevel(), entity.getBlockPos()), OverlayTexture.NO_OVERLAY, posestack, source, 1);
        posestack.popPose();
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int blocklight = level.getBrightness(LightLayer.BLOCK, pos);
        int skylight = level.getBrightness(LightLayer.SKY, pos);

        return LightTexture.pack(blocklight, skylight);
    }
}
