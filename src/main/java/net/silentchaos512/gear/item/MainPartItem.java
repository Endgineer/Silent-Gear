package net.silentchaos512.gear.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.registries.ForgeRegistries;
import net.silentchaos512.gear.SilentGear;
import net.silentchaos512.gear.api.item.GearType;
import net.silentchaos512.gear.api.material.MaterialList;
import net.silentchaos512.gear.api.part.PartType;
import net.silentchaos512.gear.api.util.StatGearKey;
import net.silentchaos512.gear.gear.part.PartData;
import net.silentchaos512.gear.metallurgy.MetallurgyEntry;

import javax.annotation.Nullable;
import java.util.List;

public class MainPartItem extends CompoundPartItem {
    private final GearType gearType;

    public MainPartItem(GearType gearType, Properties properties) {
        super(PartType.MAIN, properties.defaultDurability(100));
        this.gearType = gearType;
    }

    @Override
    public GearType getGearType() {
        return gearType;
    }

    @Override
    public int getCraftedCount(ItemStack stack) {
        return 1;
    }

    @Override
    public int getColorWeight(int index, int totalCount) {
        int diff = super.getColorWeight(index, totalCount);
        return diff * diff;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        // TODO: Cache durability stat?
        PartData part = PartData.from(stack);
        if (part != null) {
            StatGearKey statKey = StatGearKey.of(gearType.getDurabilityStat(), gearType);
            return Math.round(part.getStat(PartType.MAIN, statKey));
        }
        return super.getMaxDamage(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        if (this.gearType == GearType.ARMOR) {
            // Old armor body item
            tooltip.add(new TextComponent("DEPRECATED").withStyle(ChatFormatting.RED));
        }

        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getMainHandItem();
        MaterialList materials = MainPartItem.getMaterials(itemstack);

        final HitResult hitresult = player.pick(2.0D, 0.0F, true);
        BlockPos pos = ((BlockHitResult) hitresult).getBlockPos();

        String metal = materials.get(0).getId().getPath();

        if(hitresult.getType() == HitResult.Type.BLOCK && player.level.getFluidState(pos).getType() == Fluids.LAVA && MetallurgyEntry.get(metal) != null && materials.get(0).getGrade().isNotMax()) {
            CompoundTag tag = new CompoundTag();
            tag.putString(HeatedMetalItem.METAL, metal);
            tag.putInt(HeatedMetalItem.COUNT, materials.size());
            tag.putInt(HeatedMetalItem.REINFORCE, materials.get(0).getGrade().next().ordinal());
            tag.putDouble(HeatedMetalItem.HEAT, 0);
            tag.putInt(HeatedMetalItem.PROGRESS, 0);
            tag.putDouble(HeatedMetalItem.EXPERIENCE, 0);
            tag.putInt("CustomModelData", 0);
            tag.put(HeatedMetalItem.PART, itemstack.save(new CompoundTag()));
            
            ItemStack result = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(SilentGear.MOD_ID+":heated_metal_item")));
            result.setTag(tag);
            player.setItemInHand(hand, result);
            
            player.playSound(SoundEvents.BUCKET_EMPTY_LAVA, 1.0F, 1.0F);
        }

        return super.use(level, player, hand);
    }
}
