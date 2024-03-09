package net.silentchaos512.gear.item;

import java.util.List;
import javax.annotation.Nullable;
import net.silentchaos512.gear.metallurgy.MetallurgyEntry;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
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
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.silentchaos512.gear.SilentGear;
import net.minecraft.sounds.SoundEvents;

public class HeatedMetalItem extends Item {
    public static final String METAL = SilentGear.MOD_ID+".metal";
    public static final String ITEM = SilentGear.MOD_ID+".item";
    public static final String REINFORCE = SilentGear.MOD_ID+".reinforce";
    public static final String HEAT = SilentGear.MOD_ID+".heat";
    public static final String PROGRESS = SilentGear.MOD_ID+".progress";
    public static final String EXPERIENCE = SilentGear.MOD_ID+".experience";

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
        String item = tag.getString(HeatedMetalItem.ITEM);
        int reinforce = tag.getInt(HeatedMetalItem.REINFORCE);
        double heat = tag.getDouble(HeatedMetalItem.HEAT);
        int progress = tag.getInt(HeatedMetalItem.PROGRESS);
        double experience = tag.getDouble(HeatedMetalItem.EXPERIENCE);

        tooltip.add(new TranslatableComponent("tooltip.silentgear.metal_stats.item").append(" ").append(new TranslatableComponent("item.silentgear."+metal+"_"+item+"_main_"+reinforce)).withStyle(ChatFormatting.GRAY));
        tooltip.add(new TranslatableComponent("tooltip.silentgear.metal_stats.progress").append(" "+String.valueOf(progress)+"/"+MetallurgyEntry.get(metal).getReinforce(reinforce).getXPTotal()).withStyle(ChatFormatting.GRAY));
        tooltip.add(new TranslatableComponent("tooltip.silentgear.metal_stats.heat").append(" "+String.valueOf(Math.round(heat / MetallurgyEntry.get(metal).getReinforce(reinforce).getHeatCapacity() * 10000.0) / 100.0)+"%").withStyle(ChatFormatting.GOLD));
        tooltip.add(new TranslatableComponent("tooltip.silentgear.metal_stats.experience").append(" "+String.valueOf(Math.round(experience * 100.0) / 100.0)+"p").withStyle(ChatFormatting.GREEN));
        tooltip.add(new TranslatableComponent("tooltip.silentgear.metal_stats.malleability").append(" "+String.valueOf(Math.round(MetallurgyEntry.get(metal).getReinforce(reinforce).getMalleability(heat) * 10000.0) / 100.0)+"%").withStyle(ChatFormatting.LIGHT_PURPLE));

        if(progress == MetallurgyEntry.get(metal).getReinforce(reinforce).getXPTotal()) {
            tooltip.add(new TranslatableComponent("tooltip.silentgear.metal_stats.hints.quenching").withStyle(ChatFormatting.DARK_GRAY));
        } else if(experience == 0) {
            if(heat > MetallurgyEntry.get(metal).getReinforce(reinforce).getFoldingPoint()) {
                tooltip.add(new TranslatableComponent("tooltip.silentgear.metal_stats.hints.annealing").withStyle(ChatFormatting.DARK_GRAY));
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
        String item = tag.getString(HeatedMetalItem.ITEM);
        int reinforce = tag.getInt(HeatedMetalItem.REINFORCE);
        int progress = tag.getInt(HeatedMetalItem.PROGRESS);
        
        final HitResult hitresult = player.pick(2.0D, 0.0F, true);
        BlockPos pos = ((BlockHitResult) hitresult).getBlockPos();

        if(hitresult.getType() == HitResult.Type.BLOCK && level.getBlockState(pos).getBlock() instanceof LayeredCauldronBlock && progress == MetallurgyEntry.get(metal).getReinforce(reinforce).getXPTotal()) {
            LayeredCauldronBlock.lowerFillLevel(level.getBlockState(pos), level, pos);
            player.setItemInHand(hand, new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(SilentGear.MOD_ID+":"+metal+"_"+item+"_main_"+reinforce))));
            player.playSound(SoundEvents.LAVA_EXTINGUISH, 1.0F, 1.0F);
            player.playSound(SoundEvents.BUBBLE_COLUMN_UPWARDS_AMBIENT, 1.0F, 1.0F);
        }

        return super.use(level, player, hand);
    }
}
