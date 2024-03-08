package net.silentchaos512.gear.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.silentchaos512.gear.SilentGear;

public class TitaniteShardItem extends Item {
    public TitaniteShardItem() {
        super(new Item.Properties().rarity(Rarity.RARE).tab(SilentGear.ITEM_GROUP));
    }
}
