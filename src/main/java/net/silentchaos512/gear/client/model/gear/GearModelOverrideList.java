package net.silentchaos512.gear.client.model.gear;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.model.IModelConfiguration;
import net.silentchaos512.gear.SilentGear;
import net.silentchaos512.gear.api.item.GearType;
import net.silentchaos512.gear.api.item.ICoreItem;
import net.silentchaos512.gear.api.material.IMaterialDisplay;
import net.silentchaos512.gear.api.material.MaterialLayer;
import net.silentchaos512.gear.api.part.IPartDisplay;
import net.silentchaos512.gear.api.part.PartDataList;
import net.silentchaos512.gear.api.part.PartType;
import net.silentchaos512.gear.client.material.GearDisplayManager;
import net.silentchaos512.gear.client.model.ModelErrorLogging;
import net.silentchaos512.gear.client.model.PartTextures;
import net.silentchaos512.gear.config.Config;
import net.silentchaos512.gear.gear.material.MaterialInstance;
import net.silentchaos512.gear.gear.part.CompoundPart;
import net.silentchaos512.gear.gear.part.PartData;
import net.silentchaos512.gear.item.gear.GearCrossbowItem;
import net.silentchaos512.gear.util.GearData;
import net.silentchaos512.gear.util.GearHelper;
import net.silentchaos512.utils.Color;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class GearModelOverrideList extends ItemOverrides {
    private final Cache<CacheKey, BakedModel> bakedModelCache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .build();

    private final GearModel model;
    private final IModelConfiguration owner;
    private final ModelBakery bakery;
    private final Function<Material, TextureAtlasSprite> spriteGetter;
    private final ModelState modelTransform;
    private final ResourceLocation modelLocation;

    @SuppressWarnings("ConstructorWithTooManyParameters")
    public GearModelOverrideList(GearModel model,
                                 IModelConfiguration owner,
                                 ModelBakery bakery,
                                 Function<Material, TextureAtlasSprite> spriteGetter,
                                 ModelState modelTransform,
                                 ResourceLocation modelLocation) {
        this.model = model;
        this.owner = owner;
        this.bakery = bakery;
        this.spriteGetter = spriteGetter;
        this.modelTransform = modelTransform;
        this.modelLocation = modelLocation;
    }

    static boolean isDebugLoggingEnabled() {
        return Config.Common.modelAndTextureLogging.get();
    }

    @Nullable
    @Override
    public BakedModel resolve(BakedModel model, ItemStack stack, @Nullable ClientLevel worldIn, @Nullable LivingEntity entityIn, int p_173469_) {
        int animationFrame = getAnimationFrame(stack, worldIn, entityIn);
        CacheKey key = getKey(model, stack, worldIn, entityIn, animationFrame);
        try {
            return bakedModelCache.get(key, () -> getOverrideModel(key, stack, worldIn, entityIn, animationFrame));
        } catch (Exception e) {
            ModelErrorLogging.notifyOfException(e, "gear item");
        }
        return model;
    }

    private static int getAnimationFrame(ItemStack stack, @Nullable ClientLevel world, @Nullable LivingEntity entity) {
        return ((ICoreItem) stack.getItem()).getAnimationFrame(stack, world, entity);
    }

    private BakedModel getOverrideModel(CacheKey key, ItemStack stack, @Nullable ClientLevel worldIn, @Nullable LivingEntity entityIn, int animationFrame) {
        boolean broken = GearHelper.isBroken(stack);
        if (isDebugLoggingEnabled()) {
            SilentGear.LOGGER.info("getOverrideModel for {} ({})", stack.getHoverName().getString(), broken ? "broken" : "normal");
            SilentGear.LOGGER.info("- model key {}", key.data);
        }
        List<MaterialLayer> layers = new ArrayList<>();

        for (PartData part : getPartsInRenderOrder(stack)) {
            if (((ICoreItem) stack.getItem()).hasTexturesFor(part.getType())) {
                addSimplePartLayers(layers, part, stack);

                if (part.get() instanceof CompoundPart) {
                    MaterialInstance mat = CompoundPart.getPrimaryMaterial(part);
                    if (mat != null) {
                        addWithBlendedColor(layers, part, mat, stack);
                    }
                }
            }
        }

        // TODO: Make this not a special case...
        if (stack.getItem() instanceof GearCrossbowItem) {
            getCrossbowCharge(stack, worldIn, entityIn).ifPresent(layers::add);
        }

        return model.bake(stack, layers, animationFrame, "test", owner, bakery, spriteGetter, modelTransform, this, modelLocation);
    }

    private static PartDataList getPartsInRenderOrder(ItemStack stack) {
        PartDataList unsorted = GearData.getConstructionParts(stack);
        PartDataList ret = PartDataList.of();
        ICoreItem item = (ICoreItem) stack.getItem();

        for (PartType partType : item.getRenderParts()) {
            ret.addAll(unsorted.getPartsOfType(partType));
        }

        for (PartData part : unsorted) {
            if (!ret.contains(part)) {
                ret.add(part);
            }
        }

        return ret;
    }

    private static void addWithBlendedColor(List<MaterialLayer> list, PartData part, MaterialInstance material, ItemStack stack) {
        IMaterialDisplay model = material.getDisplayProperties();
        GearType gearType = GearHelper.getType(stack);
        List<MaterialLayer> layers = model.getLayerList(gearType, part, material).getLayers();
        addColorBlendedLayers(list, part, stack, layers);
    }

    private static void addSimplePartLayers(List<MaterialLayer> list, PartData part, ItemStack stack) {
        IPartDisplay model = GearDisplayManager.get(part.get());
        if (model != null) {
            GearType gearType = GearHelper.getType(stack);
            List<MaterialLayer> layers = model.getLayers(gearType, part).getLayers();
            addColorBlendedLayers(list, part, stack, layers);
        }
    }

    private static void addColorBlendedLayers(List<MaterialLayer> list, PartData part, ItemStack stack, List<MaterialLayer> layers) {
        for (int i = 0; i < layers.size(); i++) {
            MaterialLayer layer = layers.get(i);
            if ((layer.getColor() & 0xFFFFFF) < 0xFFFFFF) {
                int blendedColor = part.getColor(stack, i, 0);
                MaterialLayer coloredLayer = layer.withColor(blendedColor);
                list.add(coloredLayer);
                if (isDebugLoggingEnabled()) {
                    debugLogLayer(coloredLayer, Color.format(blendedColor));
                }
            } else {
                list.add(layer);
                if (isDebugLoggingEnabled()) {
                    debugLogLayer(layer, "colorless");
                }
            }
        }
    }

    private static void debugLogLayer(MaterialLayer layer, String colorStr) {
        //noinspection ConstantConditions -- unknown NPE for some data pack users
        String partTypeStr = layer.getPartType() != null
                ? SilentGear.shortenId(layer.getPartType().getName())
                : "null type?";
        SilentGear.LOGGER.info("  - add layer {} ({}, {})",
                layer.getTextureId(),
                partTypeStr,
                colorStr);
    }

    private static Optional<MaterialLayer> getCrossbowCharge(ItemStack stack, @Nullable ClientLevel world, @Nullable LivingEntity entity) {
        // TODO: Maybe should add an ICoreItem method to get additional layers?
        ItemPropertyFunction chargedProperty = ItemProperties.getProperty(stack.getItem(), new ResourceLocation("charged"));
        ItemPropertyFunction fireworkProperty = ItemProperties.getProperty(stack.getItem(), new ResourceLocation("firework"));

        if (chargedProperty != null && fireworkProperty != null) {
            boolean charged = chargedProperty.call(stack, world, entity, 0) > 0;
            boolean firework = fireworkProperty.call(stack, world, entity, 0) > 0;
            if (charged) {
                if (firework) {
                    return Optional.of(new MaterialLayer(PartTextures.CHARGED_FIREWORK, Color.VALUE_WHITE));
                }
                return Optional.of(new MaterialLayer(PartTextures.CHARGED_ARROW, Color.VALUE_WHITE));
            }
        }

        return Optional.empty();
    }

    private static CacheKey getKey(BakedModel model, ItemStack stack, @Nullable ClientLevel world, @Nullable LivingEntity entity, int animationFrame) {
        String brokenSuffix = GearHelper.isBroken(stack) ? "broken" : "";
        String chargeSuffix = getCrossbowCharge(stack, world, entity)
                .map(l -> ";" + l.getTextureId().getPath())
                .orElse("");
        return new CacheKey(model, GearData.getModelKey(stack, animationFrame) + brokenSuffix + chargeSuffix);
    }

    public void clearCache() {
        SilentGear.LOGGER.debug("Clearing model cache for {}", this.model.gearType);
        bakedModelCache.invalidateAll();
    }

    static final class CacheKey {
        final BakedModel parent;
        final String data;

        CacheKey(BakedModel parent, String hash) {
            this.parent = parent;
            this.data = hash;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CacheKey cacheKey = (CacheKey) o;
            return parent == cacheKey.parent && Objects.equals(data, cacheKey.data);
        }

        @Override
        public int hashCode() {
            return 31 * parent.hashCode() + data.hashCode();
        }
    }
}
