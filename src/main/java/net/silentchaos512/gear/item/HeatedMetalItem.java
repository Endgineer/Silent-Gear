package net.silentchaos512.gear.item;

import java.util.List;
import javax.annotation.Nullable;
import net.silentchaos512.gear.metallurgy.MetallurgyEntry;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.silentchaos512.gear.SilentGear;
import net.silentchaos512.gear.api.material.IMaterialInstance;
import net.silentchaos512.gear.api.material.MaterialList;
import net.silentchaos512.gear.gear.material.MaterialInstance;
import net.minecraft.sounds.SoundEvents;

public class HeatedMetalItem extends Item {
    public static final String METAL = SilentGear.MOD_ID+".metal";
    public static final String COUNT = SilentGear.MOD_ID+".count";
    public static final String REINFORCE = SilentGear.MOD_ID+".reinforce";
    public static final String HEAT = SilentGear.MOD_ID+".heat";
    public static final String PROGRESS = SilentGear.MOD_ID+".progress";
    public static final String EXPERIENCE = SilentGear.MOD_ID+".experience";
    public static final String PART = SilentGear.MOD_ID+".part";

    public static final String WITCH_FACTORS = SilentGear.MOD_ID+".witch_factors";

    public HeatedMetalItem() {
        super(new Item.Properties().stacksTo(1).tab(null));
    }

    @Override
    public void appendHoverText(ItemStack itemstack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        HeatedMetalItem.addMetalStats(components, itemstack);
        super.appendHoverText(itemstack, level, components, flag);
    }

    public static boolean addMetalStats(List<Component> tooltip, ItemStack itemstack) {
        if(itemstack == null || itemstack.isEmpty() || !itemstack.hasTag()) { return false; }

        CompoundTag tag = itemstack.getTag();
        String metal = tag.getString(HeatedMetalItem.METAL);
        int count = tag.getInt(HeatedMetalItem.COUNT);
        int reinforce = tag.getInt(HeatedMetalItem.REINFORCE);
        double heat = tag.getDouble(HeatedMetalItem.HEAT);
        int progress = tag.getInt(HeatedMetalItem.PROGRESS);
        double experience = tag.getDouble(HeatedMetalItem.EXPERIENCE);

        String witch_factors = tag.getString(HeatedMetalItem.WITCH_FACTORS);

        tooltip.add(new TranslatableComponent("tooltip.silentgear.metal_stats.progress").append(" ")
            .append(
                String.valueOf(progress)+"/"+MetallurgyEntry.get(metal).getReinforce(reinforce).getXPTotal(count)
            ).withStyle(ChatFormatting.GRAY));
        tooltip.add(new TranslatableComponent("tooltip.silentgear.metal_stats.heat").append(" ")
            .append(
                String.valueOf(Math.round(heat / MetallurgyEntry.get(metal).getReinforce(reinforce).getHeatCapacity() * 10000.0) / 100.0)+"%"
            ).withStyle(ChatFormatting.GOLD));
        tooltip.add(new TranslatableComponent("tooltip.silentgear.metal_stats.experience").append(" ")
            .append(
                String.valueOf(Math.round(experience * 100.0) / 100.0)+"p"
            ).withStyle(ChatFormatting.GREEN));
        tooltip.add(new TranslatableComponent("tooltip.silentgear.metal_stats.malleability").append(" ")
            .append(
                String.valueOf(Math.round(MetallurgyEntry.get(metal).getReinforce(reinforce).getMalleability(heat) * 10000.0) / 100.0)+"%"
            ).withStyle(ChatFormatting.LIGHT_PURPLE));

        tooltip.add(new TranslatableComponent("tooltip.silentgear.metal_stats.witch_factors").append(" ")
            .append(witch_factors.replaceAll("r", "8")).withStyle(ChatFormatting.GRAY));

        if(progress == MetallurgyEntry.get(metal).getReinforce(reinforce).getXPTotal(count)) {
            tooltip.add(new TranslatableComponent("tooltip.silentgear.metal_stats.hints.quenching").withStyle(ChatFormatting.DARK_GRAY));
        } else if(experience == 0) {
            if(MetallurgyEntry.get(metal).getReinforce(reinforce).compareFoldingRange(heat) > 0) {
                tooltip.add(new TranslatableComponent("tooltip.silentgear.metal_stats.hints.annealing").withStyle(ChatFormatting.DARK_GRAY));
            } else if(MetallurgyEntry.get(metal).getReinforce(reinforce).compareFoldingRange(heat) < 0) {
                tooltip.add(new TranslatableComponent("tooltip.silentgear.metal_stats.hints.heating").withStyle(ChatFormatting.DARK_GRAY));
            } else {
                tooltip.add(new TranslatableComponent("tooltip.silentgear.metal_stats.hints.folding").withStyle(ChatFormatting.DARK_GRAY));
            }
        } else if(heat == 0) {
                tooltip.add(new TranslatableComponent("tooltip.silentgear.metal_stats.hints.heating").withStyle(ChatFormatting.DARK_GRAY));
        } else {
            tooltip.add(new TranslatableComponent("tooltip.silentgear.metal_stats.hints.hammering").withStyle(ChatFormatting.DARK_GRAY));
        }

        return true;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getMainHandItem();

        CompoundTag tag = itemstack.getTag();
        String metal = tag.getString(HeatedMetalItem.METAL);
        int count = tag.getInt(HeatedMetalItem.COUNT);
        int reinforce = tag.getInt(HeatedMetalItem.REINFORCE);
        int progress = tag.getInt(HeatedMetalItem.PROGRESS);

        final HitResult hitresult = player.pick(2.0D, 0.0F, true);
        BlockPos pos = ((BlockHitResult) hitresult).getBlockPos();

        if(hitresult.getType() == HitResult.Type.BLOCK && level.getBlockState(pos).getBlock() instanceof LayeredCauldronBlock && progress == MetallurgyEntry.get(metal).getReinforce(reinforce).getXPTotal(count)) {
            LayeredCauldronBlock.lowerFillLevel(level.getBlockState(pos), level, pos);

            ItemStack partstack = ItemStack.of((CompoundTag) tag.get(HeatedMetalItem.PART));
            MainPartItem part = (MainPartItem) partstack.getItem();

            MaterialList materials = MainPartItem.getMaterials(partstack);
            for(IMaterialInstance material : materials) {
                material.getGrade().next().setGradeOnStack(material.getItem());
            }

            ItemStack result = part.createOne(materials.stream().map(material -> MaterialInstance.from(material.getItem())).toList());
            player.setItemInHand(InteractionHand.MAIN_HAND, result.copy());

            player.playSound(SoundEvents.LAVA_EXTINGUISH, 1.0F, 1.0F);
            player.playSound(SoundEvents.BUBBLE_COLUMN_UPWARDS_AMBIENT, 1.0F, 1.0F);
        }

        return super.use(level, player, hand);
    }

    @Override
    public Component getName(ItemStack itemstack) {
        CompoundTag tag = itemstack.getTag();

        if(tag == null) {
            return super.getName(itemstack);
        }

        int reinforce = tag.getInt(HeatedMetalItem.REINFORCE);

        ItemStack partstack = ItemStack.of((CompoundTag) tag.get(HeatedMetalItem.PART));

        return new TextComponent(partstack.getDisplayName().getString().replaceAll("\\[|\\]|\s\\+[0-9]+", ""))
            .append(" +"+reinforce).append(" (")
            .append(new TranslatableComponent("tooltip.silentgear.metal_stats.unfinished")).append(")");
    }
}
