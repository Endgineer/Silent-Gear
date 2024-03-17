/*
 * Silent Gear -- CraftingItems
 * Copyright (C) 2018 SilentChaos512
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation version 3
 * of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.silentchaos512.gear.item;

import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.silentchaos512.gear.SilentGear;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;

public enum CraftingItems implements ItemLike {
    BLUEPRINT_PAPER,
    TEMPLATE_BOARD,
    UPGRADE_BASE,
    SINEW,
    DRIED_SINEW,
    SINEW_FIBER,
    FINE_SILK,
    FINE_SILK_CLOTH,
    // Rods
    ROUGH_ROD,
    STONE_ROD,
    IRON_ROD;

    @SuppressWarnings("NonFinalFieldInEnum")
    private RegistryObject<ItemInternal> item = null;

    @Override
    public Item asItem() {
        if (this.item == null) {
            throw new NullPointerException("CraftingItems accessed too early!");
        }
        return this.item.get();
    }

    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }

    public static void register(DeferredRegister<Item> items) {
        for (CraftingItems item : values()) {
            item.item = items.register(item.getName(), ItemInternal::new);
        }
    }

    private static final class ItemInternal extends Item {
        ItemInternal() {
            super(new Properties().tab(SilentGear.ITEM_GROUP));
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
            String descKey = this.getDescriptionId() + ".desc";
            if (I18n.exists(descKey)) {
                tooltip.add(new TranslatableComponent(descKey).withStyle(ChatFormatting.ITALIC));
            }
        }
    }
}
