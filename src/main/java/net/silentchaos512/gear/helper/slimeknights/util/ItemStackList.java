package net.silentchaos512.gear.helper.slimeknights.util;

/*
 * SOURCE: https://github.com/SlimeKnights/Mantle/blob/1.18.2/src/main/java/slimeknights/mantle/util/ItemStackList.java
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

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@SuppressWarnings({"unchecked"})
public class ItemStackList extends NonNullList<ItemStack> {
    protected ItemStackList() {
        this(new ArrayList<>());
    }

    protected ItemStackList(List<ItemStack> delegate) {
        super(delegate, ItemStack.EMPTY);
    }

    public static ItemStackList create() {
        return new ItemStackList();
    }

    public static ItemStackList withSize(int size) {
        ItemStack[] aobject = new ItemStack[size];
        Arrays.fill(aobject, ItemStack.EMPTY);
        return new ItemStackList(Arrays.asList(aobject));
    }

    public static ItemStackList of(ItemStack... element) {
        ItemStackList itemStackList = create();
        itemStackList.addAll(Arrays.asList(element));
        return itemStackList;
    }

    public static ItemStackList of(Collection<ItemStack> boringList) {
        ItemStackList itemStackList = create();
        itemStackList.addAll(boringList);
        return itemStackList;
    }

    public static ItemStackList of(Container inventory) {
        ItemStackList itemStackList = withSize(inventory.getContainerSize());
        for (int i = 0; i < inventory.getContainerSize(); i++) {
          itemStackList.add(inventory.getItem(i));
        }
        return itemStackList;
    }

    public boolean hasItem(int index) {
        return index >= 0 && index < size() && !get(index).isEmpty();
    }

    public void setEmpty(int index) {
        if (index >= 0 && index < size()) {
          set(index, ItemStack.EMPTY);
        }
    }

    public ItemStackList copy(boolean fixed) {
        ItemStackList copy = fixed ? withSize(this.size()) : create();
        for (int i = 0; i < size(); i++) {
          copy.set(i, get(i));
        }
        return copy;
    }

    public ItemStackList deepCopy(boolean fixed) {
        ItemStackList copy = fixed ? withSize(this.size()) : create();
        for (int i = 0; i < size(); i++) {
          copy.set(i, get(i).copy());
        }
        return copy;
    }
}
