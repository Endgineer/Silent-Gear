package net.silentchaos512.gear.block.anvil;

import net.silentchaos512.gear.init.ModBlockEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.state.BlockState;
import net.silentchaos512.gear.block.TitaniteAnvilBlock;
import net.silentchaos512.gear.init.ModItems;
import net.silentchaos512.gear.helper.openmods.EnchantmentUtils;
import net.silentchaos512.gear.SilentGear;
import net.silentchaos512.gear.api.GearApi;
import net.silentchaos512.gear.api.part.PartType;
import net.silentchaos512.gear.helper.slimeknights.blockentity.HolderBlockEntity;
import net.silentchaos512.gear.item.HeatedMetalItem;
import net.silentchaos512.gear.item.TitaniteShardItem;
import net.silentchaos512.gear.metallurgy.MetallurgyEntry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import net.minecraftforge.items.wrapper.InvWrapper;
import java.util.List;
import com.simibubi.create.AllItems;
import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.content.equipment.goggles.IHaveHoveringInformation;
import com.simibubi.create.foundation.utility.Lang;

public class TitaniteAnvilBlockEntity extends HolderBlockEntity implements IHaveGoggleInformation, IHaveHoveringInformation {
    public static final int SLOT = 0;
    
    public TitaniteAnvilBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TITANITE_ANVIL_BLOCK_ENTITY.get(), pos, state, 4, 1);
        this.itemHandler = new InvWrapper(this);
    }

    public void interact(Player player, InteractionHand hand) {
        if (level == null || level.isClientSide) {
            return;
        }

        ItemStack itemstack = player.getItemInHand(hand);
        if(itemstack.is(AllItems.WRENCH.get()) && !player.getCooldowns().isOnCooldown(AllItems.WRENCH.get())) {
            this.hammerItem(player);
        } else if(player.isShiftKeyDown()) {
            this.takeItem(player);
        } else if(this.getItem(SLOT).is(ModItems.HEATED_METAL_ITEM.get()) && itemstack.getItem() instanceof TitaniteShardItem) {
            this.foldItem(player, hand);
        } else if(!this.isStackInSlot(SLOT) && itemstack.is(ModItems.HEATED_METAL_ITEM.get())) {
            this.setItem(SLOT, itemstack);
            player.setItemInHand(hand, ItemStack.EMPTY);
        }
    }

    private void hammerItem(Player player) {
        ItemStack itemstack = this.getItem(SLOT);
        
        if(itemstack.is(ModItems.HEATED_METAL_ITEM.get())) {
            CompoundTag tag = itemstack.getTag();
            if(!itemstack.hasTag()) { return; }

            String metal = tag.getString(HeatedMetalItem.METAL);
            int reinforce = tag.getInt(HeatedMetalItem.REINFORCE);
            double heat = tag.getDouble(HeatedMetalItem.HEAT);
            int progress = tag.getInt(HeatedMetalItem.PROGRESS);
            double experience = tag.getDouble(HeatedMetalItem.EXPERIENCE);

            double cost = Math.min(MetallurgyEntry.get(metal).getReinforce(reinforce).getXPGain(heat), Math.min(experience, EnchantmentUtils.getPlayerXP(player)));

            if(experience > 0 && heat > 0 && cost > 0) {
                double temp = Math.max(0, experience - cost);
                int difference = (int) (Math.ceil(experience) - Math.ceil(temp));

                if(difference > 0) {
                    player.giveExperiencePoints(-difference);
                    progress += difference;

                    tag.putInt(HeatedMetalItem.PROGRESS, progress);
                }

                heat -= Math.min(MetallurgyEntry.get(metal).getReinforce(reinforce).getHeatLoss(heat), heat);
                experience = temp;

                tag.putDouble(HeatedMetalItem.HEAT, heat);
                tag.putDouble(HeatedMetalItem.EXPERIENCE, experience);
                tag.putInt("CustomModelData", (int) Math.ceil(16.0 * heat / MetallurgyEntry.get(metal).getReinforce(reinforce).getHeatCapacity()));

                itemstack.setTag(tag);
                this.setItem(SLOT, itemstack);

                level.playSound(null, this.getBlockPos(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1.0F, 1.0F);
            }

            ((ServerLevel) level).sendParticles(ParticleTypes.CRIT, this.getBlockPos().getX() + 0.5D, this.getBlockPos().getY() + 1.0D, this.getBlockPos().getZ() + 0.5D, 10, 0.05D, 0.05D, 0.05D, 1);
        }
        
        level.playSound(null, worldPosition, SoundEvents.ANVIL_PLACE, SoundSource.PLAYERS, 1.0F, 5.0F);
        player.getCooldowns().addCooldown(AllItems.WRENCH.get(), 20);
    }

    private void foldItem(Player player, InteractionHand hand) {
        ItemStack itemstack = this.getItem(SLOT);

        if(itemstack.is(ModItems.HEATED_METAL_ITEM.get())) {
            CompoundTag tag = itemstack.getTag();
            if(!itemstack.hasTag()) { return; }

            String metal = tag.getString(HeatedMetalItem.METAL);
            int count = tag.getInt(HeatedMetalItem.COUNT);
            int reinforce = tag.getInt(HeatedMetalItem.REINFORCE);
            double heat = tag.getDouble(HeatedMetalItem.HEAT);
            int progress = tag.getInt(HeatedMetalItem.PROGRESS);
            double experience = tag.getDouble(HeatedMetalItem.EXPERIENCE);

            String witch_factors = tag.getString(HeatedMetalItem.WITCH_FACTORS);

            if(progress != MetallurgyEntry.get(metal).getReinforce(reinforce).getXPTotal(count) && experience == 0 && MetallurgyEntry.get(metal).getReinforce(reinforce).compareFoldingRange(heat) == 0) {
                Item input = GearApi.getMaterial(new ResourceLocation(SilentGear.MOD_ID+":"+metal)).getDisplayItem(PartType.MAIN, 0).getItem();
                int remainder = count;

                if(player.getInventory().countItem(input) >= remainder) {
                    tag.putString(HeatedMetalItem.WITCH_FACTORS, witch_factors.replaceFirst("8", ((TitaniteShardItem) player.getItemInHand(hand).getItem()).getWitchFactor()));
                    player.getItemInHand(hand).setCount(player.getItemInHand(hand).getCount()-1);

                    for(int i = 0; remainder > 0; i++) {
                        ItemStack stack = player.getInventory().getItem(i);
                        if (stack.getItem().equals(input)) {
                            int amount = Math.min(stack.getCount(), remainder);
                            player.getInventory().getItem(i).setCount(stack.getCount()-amount);
                            remainder -= amount;
                        }
                    }

                    tag.putDouble(HeatedMetalItem.EXPERIENCE, MetallurgyEntry.get(metal).getReinforce(reinforce).getXPTrip(count));
                    itemstack.setTag(tag);
                    this.setItem(SLOT, itemstack);

                    level.playSound(null, this.getBlockPos(), SoundEvents.ANVIL_USE, SoundSource.PLAYERS, 1.0F, 1.0F);
                }
            }
        }
    }

    public void takeItem(Player player) {
        int slot = -1;

        if(this.isStackInSlot(SLOT)) {
            slot = SLOT;
        }

        if(slot != -1 && player.getInventory().canPlaceItem(1, this.getItem(slot))) {
            ItemStack item = this.getItem(slot);
            this.setItem(slot, ItemStack.EMPTY);
            player.getInventory().add(item);
        }
    }

    public void dropItems() {
        SimpleContainer inventory = new SimpleContainer(this.getContainerSize());
        for(int i = 0; i < inventory.getContainerSize(); i++) {
            inventory.setItem(i, this.getItem(i));
            this.setItem(i, ItemStack.EMPTY);
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public int drenchItem(FluidStack fluidstack, FluidAction action) {
        ItemStack itemstack = this.getItem(SLOT);
        int heat_difference = 0;

        if(itemstack.is(ModItems.HEATED_METAL_ITEM.get())) {
            CompoundTag tag = itemstack.getTag();
            if(!itemstack.hasTag()) { return 0; }

            String metal = tag.getString(HeatedMetalItem.METAL);
            int reinforce = tag.getInt(HeatedMetalItem.REINFORCE);
            double heat = tag.getDouble(HeatedMetalItem.HEAT);

            Fluid fluid = fluidstack.getFluid();

            if(fluid.isSame(Fluids.LAVA) && fluidstack.getAmount() > 0) {
                heat_difference = Math.min(Math.min(fluidstack.getAmount(), MetallurgyEntry.get(metal).getReinforce(reinforce).getHeatCapacity() - (int) heat), 10);
            } else if(fluid.isSame(Fluids.WATER) && fluidstack.getAmount() > 0) {
                heat_difference = -Math.min(Math.min(fluidstack.getAmount(), (int) Math.ceil(heat)), 10);
            }

            if(action == FluidAction.EXECUTE) {
                heat = Math.max(0, Math.min(heat+heat_difference, MetallurgyEntry.get(metal).getReinforce(reinforce).getHeatCapacity()));

                if(heat_difference > 0) {
                    level.playSound(null, this.getBlockPos(), SoundEvents.BUCKET_EMPTY_LAVA, SoundSource.BLOCKS, 1.0F, 2.0F);
                } else if(heat_difference < 0) {
                    ((ServerLevel) level).sendParticles(ParticleTypes.CLOUD, this.getBlockPos().getX() + 0.5D, this.getBlockPos().getY() + 1.0D, this.getBlockPos().getZ() + 0.5D, 10, 0.05D, 0.05D, 0.05D, 0.05);
                    level.playSound(null, this.getBlockPos(), SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);
                }

                tag.putDouble(HeatedMetalItem.HEAT, heat);
                tag.putInt("CustomModelData", (int) Math.ceil(16.0 * heat / MetallurgyEntry.get(metal).getReinforce(reinforce).getHeatCapacity()));
                itemstack.setTag(tag);
                this.setItem(SLOT, itemstack);
            }
        }

        return Math.abs(heat_difference);
    }

    @Override
	public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        ItemStack item = this.getItem(SLOT);
        
        if(item.is(ModItems.HEATED_METAL_ITEM.get())) {
            Lang.itemName(item).forGoggles(tooltip);
            return HeatedMetalItem.addMetalStats(tooltip, item);
        }

        return false;
	}

    @SuppressWarnings("incomplete-switch")
    private double pointOfInterest() {
        Minecraft mc = Minecraft.getInstance();

        final HitResult hitresult = mc.player.pick(4.5D, 0.0F, true);
        Vec3 truepos = ((BlockHitResult) hitresult).getLocation();
        BlockPos blockpos = ((BlockHitResult) hitresult).getBlockPos();
        double model_x = truepos.x() - blockpos.getX();
        double model_z = truepos.z() - blockpos.getZ();
        Direction FACING = this.getBlockState().getValue(TitaniteAnvilBlock.FACING);

        double w = 0;
        switch(FACING) {
            case NORTH -> w = Math.toRadians(0);
            case EAST -> w = Math.toRadians(270);
            case SOUTH -> w = Math.toRadians(0);
            case WEST -> w = Math.toRadians(270);
        }

        Vec2 vector = new Vec2((float) (Math.cos(w)*model_x - Math.sin(w)*model_z), (float) (Math.sin(w)*model_x + Math.cos(w)*model_z));
        switch(FACING) {
            case NORTH: return 1 - vector.x;
            case EAST: return 1 - vector.x;
            case SOUTH: return vector.x;
            case WEST: return vector.x;
        }

        return -1;
    }
}
