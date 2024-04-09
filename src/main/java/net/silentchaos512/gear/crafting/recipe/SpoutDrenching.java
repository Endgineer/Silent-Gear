package net.silentchaos512.gear.crafting.recipe;

/*
 * SOURCE: https://github.com/Creators-of-Create/Create/blob/mc1.18/dev/src/main/java/com/simibubi/create/compat/tconstruct/SpoutCasting.java
 * 
 * MIT License
 * 
 * Copyright (c) 2019 simibubi
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

import com.simibubi.create.api.behaviour.BlockSpoutingBehaviour;
import com.simibubi.create.content.fluids.spout.SpoutBlockEntity;
import net.silentchaos512.gear.block.anvil.TitaniteAnvilBlockEntity;
import net.silentchaos512.gear.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import net.silentchaos512.gear.SilentGear;

public class SpoutDrenching extends BlockSpoutingBehaviour {
    @Override
    public int fillBlock(Level level, BlockPos pos, SpoutBlockEntity spout, FluidStack fluid, boolean simulate) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
		if (blockEntity == null) { return 0; }

        if (!blockEntity.getType().equals(ModBlockEntities.TITANITE_ANVIL_BLOCK_ENTITY.get())) { return 0; }

        return ((TitaniteAnvilBlockEntity) level.getBlockEntity(pos)).drenchItem(fluid, simulate ? FluidAction.SIMULATE : FluidAction.EXECUTE);
    }

    public static void registerRecipes() {
        BlockSpoutingBehaviour.addCustomSpoutInteraction(new ResourceLocation(SilentGear.MOD_ID, "drenching"), new SpoutDrenching());
    }
}
