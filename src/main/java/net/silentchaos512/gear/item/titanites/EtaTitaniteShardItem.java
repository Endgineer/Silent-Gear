package net.silentchaos512.gear.item.titanites;

import javax.annotation.Nullable;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.silentchaos512.gear.SilentGear;
import net.silentchaos512.gear.item.TitaniteShardItem;

public class EtaTitaniteShardItem extends TitaniteShardItem {
    public EtaTitaniteShardItem() {
        super(new Item.Properties().rarity(Rarity.EPIC).tab(SilentGear.ITEM_GROUP));
    }

    @Override
    public String getWitchFactor() {
        return "a";
    }

    @Override
    public void appendHoverText(ItemStack itemstack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(itemstack == null || itemstack.isEmpty() || !itemstack.hasTag()) {
            components.add(new TranslatableComponent("tooltip.silentgear.eta_titanite_shard1").withStyle(ChatFormatting.DARK_GRAY));
        }
    }
}
