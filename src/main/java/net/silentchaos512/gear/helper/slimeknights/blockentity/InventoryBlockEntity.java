package net.silentchaos512.gear.helper.slimeknights.blockentity;

/*
 * SOURCE: https://github.com/SlimeKnights/Mantle/blob/1.18.2/src/main/java/slimeknights/mantle/block/entity/InventoryBlockEntity.java
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

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.silentchaos512.gear.helper.slimeknights.util.ItemStackList;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class InventoryBlockEntity extends BaseBlockEntity implements Container {
    private NonNullList<ItemStack> inventory;

    private final boolean saveSizeToNBT;
    protected int stackSizeLimit;

    protected IItemHandlerModifiable itemHandler;
    protected LazyOptional<IItemHandlerModifiable> itemHandlerCap;

    public IItemHandlerModifiable getItemHandler() { return itemHandler; }

    public InventoryBlockEntity(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state, boolean saveSizeToNBT, int inventorySize, int maxStackSize) {
        super(tileEntityTypeIn, pos, state);
        this.saveSizeToNBT = saveSizeToNBT;
        this.inventory = NonNullList.withSize(inventorySize, ItemStack.EMPTY);
        this.stackSizeLimit = maxStackSize;
        this.itemHandler = new InvWrapper(this);
        this.itemHandlerCap = LazyOptional.of(() -> this.itemHandler);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return this.itemHandlerCap.cast();
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        this.itemHandlerCap.invalidate();
    }

    @Override
    public ItemStack getItem(int slot) {
        if (slot < 0 || slot >= this.inventory.size()) {
        return ItemStack.EMPTY;
        }

        return this.inventory.get(slot);
    }

    public boolean isStackInSlot(int slot) {
        return !this.getItem(slot).isEmpty();
    }

    private void resizeInternal(int size) {
        if (size == this.inventory.size()) {
            return;
        }
        ItemStackList newInventory = ItemStackList.withSize(size);

        for (int i = 0; i < size && i < this.inventory.size(); i++) {
            newInventory.set(i, this.inventory.get(i));
        }
        this.inventory = newInventory;
    }

    public void resize(int size) {
        this.resizeInternal(size);
        this.setChangedFast();
    }

    @Override
    public int getContainerSize() {
        return this.inventory.size();
    }

    @Override
    public int getMaxStackSize() {
        return this.stackSizeLimit;
    }

    @Override
    public void setItem(int slot, ItemStack itemstack) {
        if (slot < 0 || slot >= this.inventory.size()) {
            return;
        }

        ItemStack current = this.inventory.get(slot);
        this.inventory.set(slot, itemstack);

        if (!itemstack.isEmpty() && itemstack.getCount() > this.getMaxStackSize()) {
            itemstack.setCount(this.getMaxStackSize());
        }
        if (!ItemStack.matches(current, itemstack)) {
            this.setChangedFast();
        }
    }

    @Override
    public ItemStack removeItem(int slot, int quantity) {
        if (quantity <= 0) {
            return ItemStack.EMPTY;
        }
        ItemStack itemStack = this.getItem(slot);

        if (itemStack.isEmpty()) {
            return ItemStack.EMPTY;
        }

        if (itemStack.getCount() <= quantity) {
            this.setItem(slot, ItemStack.EMPTY);
            this.setChangedFast();
            return itemStack;
        }

        itemStack = itemStack.split(quantity);
        if (this.getItem(slot).getCount() == 0) {
            this.setItem(slot, ItemStack.EMPTY);
        }

        this.setChangedFast();
        return itemStack;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        ItemStack itemStack = this.getItem(slot);
        this.setItem(slot, ItemStack.EMPTY);
        return itemStack;
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack itemstack) {
        if (slot < this.getContainerSize()) {
            return this.inventory.get(slot).isEmpty() || itemstack.getCount() + this.inventory.get(slot).getCount() <= this.getMaxStackSize();
        }
        return false;
    }

    @Override
    public void clearContent() {
        for (int i = 0; i < this.inventory.size(); i++) {
            this.inventory.set(i, ItemStack.EMPTY);
        }
    }

    @Override
    public boolean stillValid(Player entityplayer) {
        if (level == null || this.level.getBlockEntity(this.worldPosition) != this || this.getBlockState().getBlock() == Blocks.AIR) {
            return false;
        }

        return entityplayer.distanceToSqr(this.worldPosition.getX() + 0.5D, this.worldPosition.getY() + 0.5D, this.worldPosition.getZ() + 0.5D) <= 64D;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.inventory) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void startOpen(Player player) {}

    @Override
    public void stopOpen(Player player) {}

    private static final String TAG_INVENTORY_SIZE = "InventorySize";
    private static final String TAG_ITEMS = "Items";
    private static final String TAG_SLOT = "Slot";

    @Override
    public void load(CompoundTag tags) {
        super.load(tags);
        if (saveSizeToNBT) {
            this.resizeInternal(tags.getInt(TAG_INVENTORY_SIZE));
        }
        this.readInventoryFromNBT(tags);
    }

    @Override
    public void saveSynced(CompoundTag tags) {
        super.saveSynced(tags);
        if (saveSizeToNBT) {
            tags.putInt(TAG_INVENTORY_SIZE, this.inventory.size());
        }
    }

    @Override
    public void saveAdditional(CompoundTag tags) {
        super.saveAdditional(tags);
        this.writeInventoryToNBT(tags);
    }

    public void writeInventoryToNBT(CompoundTag tag) {
        Container inventory = this;
        ListTag nbttaglist = new ListTag();

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            if (!inventory.getItem(i).isEmpty()) {
                CompoundTag itemTag = new CompoundTag();
                itemTag.putByte(TAG_SLOT, (byte) i);
                inventory.getItem(i).save(itemTag);
                nbttaglist.add(itemTag);
            }
        }

        tag.put(TAG_ITEMS, nbttaglist);
    }

    public void readInventoryFromNBT(CompoundTag tag) {
        ListTag list = tag.getList(TAG_ITEMS, Tag.TAG_COMPOUND);

        int limit = this.getMaxStackSize();
        ItemStack stack;
        for (int i = 0; i < list.size(); ++i) {
            CompoundTag itemTag = list.getCompound(i);
            int slot = itemTag.getByte(TAG_SLOT) & 255;
            if (slot < this.inventory.size()) {
                stack = ItemStack.of(itemTag);
                if (!stack.isEmpty() && stack.getCount() > limit) {
                    stack.setCount(limit);
                }
                this.inventory.set(slot, stack);
            }
        }
    }
}
