package net.silentchaos512.gear.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.silentchaos512.gear.SilentGear;

public abstract class TitaniteShardAwakenedItem extends TitaniteShardItem {
    public TitaniteShardAwakenedItem() {
        super(new Item.Properties().rarity(Rarity.EPIC).tab(SilentGear.ITEM_GROUP));
    }

    @Override
    public boolean isFoil(ItemStack itemstack) {
        return itemstack.getTag() == null;
    }

    @Override
    public void appendHoverText(ItemStack itemstack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(itemstack == null || itemstack.isEmpty() || !itemstack.hasTag()) {
            components.add(new TranslatableComponent("tooltip.silentgear.titanite_shard_awakened1").withStyle(ChatFormatting.DARK_GRAY));
            components.add(new TranslatableComponent("tooltip.silentgear.titanite_shard_awakened2").withStyle(ChatFormatting.DARK_GRAY));
            components.add(new TranslatableComponent("tooltip.silentgear.titanite_shard_awakened3").withStyle(ChatFormatting.GRAY));
        }
    }
}
