package net.silentchaos512.gear.helper.slimeknights.blockentity;

/*
 * SOURCE: https://github.com/SlimeKnights/TinkersConstruct/blob/1.18.2/src/main/java/slimeknights/tconstruct/shared/block/entity/TableBlockEntity.java
 * 
 * MIT License
 * 
 * Copyright (c) 2022 SlimeKnights
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import net.silentchaos512.gear.helper.slimeknights.network.InventorySlotSyncPacket;
import net.silentchaos512.gear.network.Network;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.PacketDistributor;

public class HolderBlockEntity extends InventoryBlockEntity {
    public HolderBlockEntity(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state, int inventorySize, int maxStackSize) {
        super(tileEntityTypeIn, pos, state, false, inventorySize, maxStackSize);
    }

    @Override
    public void setItem(int slot, ItemStack itemstack) {
        if (level != null && level instanceof ServerLevel && !level.isClientSide) {
            Network.channel.send(PacketDistributor.ALL.noArg(), new InventorySlotSyncPacket(itemstack, slot, worldPosition));
        }
        super.setItem(slot, itemstack);
    }

    @Override
    protected boolean shouldSyncOnUpdate() {
        return true;
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag nbt = super.getUpdateTag();
        writeInventoryToNBT(nbt);
        return nbt;
    }
}
