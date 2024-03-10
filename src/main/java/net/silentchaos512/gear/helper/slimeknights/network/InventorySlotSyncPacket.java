package net.silentchaos512.gear.helper.slimeknights.network;

/*
 * SOURCE: https://github.com/SlimeKnights/TinkersConstruct/blob/1.18.2/src/main/java/slimeknights/tconstruct/common/network/InventorySlotSyncPacket.java
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

import java.util.function.Supplier;
import net.minecraftforge.network.NetworkEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.network.NetworkEvent.Context;

public class InventorySlotSyncPacket {
    public final ItemStack itemstack;
    public final int slot;
    public final BlockPos pos;
    
    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> handleThreadsafe(context));
        context.setPacketHandled(true);
    }

    public InventorySlotSyncPacket(ItemStack itemstack, int slot, BlockPos pos) {
        this.itemstack = itemstack;
        this.slot = slot;
        this.pos = pos;
    }

    public InventorySlotSyncPacket(FriendlyByteBuf buffer) {
        this.itemstack = buffer.readItem();
        this.slot = buffer.readShort();
        this.pos = buffer.readBlockPos();
    }

    public void encode(FriendlyByteBuf packetBuffer) {
        packetBuffer.writeItem(this.itemstack);
        packetBuffer.writeShort(this.slot);
        packetBuffer.writeBlockPos(this.pos);
    }

    public void handleThreadsafe(Context context) {
        HandleClient.handle(this);
    }

    private static class HandleClient {
        private static void handle(InventorySlotSyncPacket packet) {
            Minecraft mc = Minecraft.getInstance();
            Level world = mc.level;
            if (world != null) {
                BlockEntity entity = world.getBlockEntity(packet.pos);
                if (entity != null) {
                    entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
                        .filter(cap -> cap instanceof IItemHandlerModifiable)
                        .ifPresent(cap -> {
                            ((IItemHandlerModifiable)cap).setStackInSlot(packet.slot, packet.itemstack);
                            mc.levelRenderer.blockChanged(null, packet.pos, null, null, 0);
                        });
                }
            }
        }
    }
}
