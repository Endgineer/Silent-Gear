package net.silentchaos512.gear.gear.material;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.silentchaos512.gear.api.event.GetMaterialStatsEvent;
import net.silentchaos512.gear.api.material.IMaterial;
import net.silentchaos512.gear.api.material.IMaterialInstance;
import net.silentchaos512.gear.api.parts.MaterialGrade;
import net.silentchaos512.gear.api.parts.PartType;
import net.silentchaos512.gear.api.stats.ItemStat;
import net.silentchaos512.gear.api.stats.ItemStats;
import net.silentchaos512.gear.api.stats.StatInstance;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class MaterialInstance implements IMaterialInstance {
    private static final Map<ResourceLocation, MaterialInstance> QUICK_CACHE = new HashMap<>();

    private final IMaterial material;
    private final MaterialGrade grade;
    private final ItemStack item;

    private MaterialInstance(IMaterial material) {
        this(material, MaterialGrade.NONE, material.getDisplayItem(PartType.MAIN, 0));
    }

    private MaterialInstance(IMaterial material, MaterialGrade grade) {
        this(material, grade, material.getDisplayItem(PartType.MAIN, 0));
    }

    private MaterialInstance(IMaterial material, ItemStack craftingItem) {
        this(material, MaterialGrade.NONE, craftingItem);
    }

    private MaterialInstance(IMaterial material, MaterialGrade grade, ItemStack craftingItem) {
        this.material = material;
        this.grade = grade;
        this.item = craftingItem.copy();
        this.item.setCount(1);
    }

    public static MaterialInstance of(IMaterial material) {
        return QUICK_CACHE.computeIfAbsent(material.getId(), id -> new MaterialInstance(material));
    }

    public static MaterialInstance of(IMaterial material, MaterialGrade grade) {
        return new MaterialInstance(material, grade);
    }

    public static MaterialInstance of(IMaterial material, ItemStack craftingItem) {
        return new MaterialInstance(material, MaterialGrade.fromStack(craftingItem), craftingItem);
    }

    public static MaterialInstance of(IMaterial material, MaterialGrade grade, ItemStack craftingItem) {
        return new MaterialInstance(material, grade, craftingItem);
    }

    @Nullable
    public static MaterialInstance from(ItemStack stack) {
        IMaterial material = MaterialManager.from(stack);
        if (material != null) {
            return of(material, stack);
        }
        return null;
    }

    @Override
    public ResourceLocation getMaterialId() {
        return material.getId();
    }

    @Nonnull
    @Override
    public IMaterial getMaterial() {
        return material;
    }

    @Override
    public MaterialGrade getGrade() {
        return grade;
    }

    @Override
    public ItemStack getItem() {
        return item;
    }

    @Override
    public int getTier(PartType partType) {
        return material.getTier(partType);
    }

    public Collection<PartType> getPartTypes() {
        return material.getPartTypes();
    }

    public Collection<StatInstance> getStatModifiers(ItemStat stat, PartType partType) {
        return getStatModifiers(stat, partType, ItemStack.EMPTY);
    }

    public Collection<StatInstance> getStatModifiers(ItemStat stat, PartType partType, ItemStack gear) {
        Collection<StatInstance> mods = material.getStatModifiers(stat, partType, gear);
        if (stat.isAffectedByGrades() && grade != MaterialGrade.NONE) {
            // Apply grade bonus to all modifiers. Makes it easier to see the effect on rods and such.
            float bonus = 1f + grade.bonusPercent / 100f;
            return mods.stream().map(m -> new StatInstance(m.getValue() * bonus, m.getOp())).collect(Collectors.toList());
        }
        GetMaterialStatsEvent event = new GetMaterialStatsEvent(this, stat, partType, mods);
        MinecraftForge.EVENT_BUS.post(event);
        return event.getModifiers();
    }

    @Override
    public float getStat(ItemStat stat, PartType partType, ItemStack gear) {
        return stat.compute(stat.getDefaultValue(), getStatModifiers(stat, partType, gear));
    }

    public int getRepairValue() {
        if (material.allowedInPart(PartType.MAIN)) {
            float durability = getStat(ItemStats.DURABILITY, PartType.MAIN);
            float repairEfficiency = getStat(ItemStats.REPAIR_EFFICIENCY, PartType.MAIN);
            return Math.round(durability * repairEfficiency) + 1;
        }
        return 0;
    }

    @Nullable
    public static MaterialInstance read(CompoundNBT nbt) {
        ResourceLocation id = ResourceLocation.tryCreate(nbt.getString("ID"));
        IMaterial material = MaterialManager.get(id);
        if (material == null) return null;

        ItemStack stack = ItemStack.read(nbt.getCompound("Item"));
        return of(material, stack);
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        nbt.putString("ID", material.getId().toString());
        nbt.put("Item", item.write(new CompoundNBT()));
        return nbt;
    }

    @Override
    public int getColor(PartType partType, ItemStack gear) {
        return material.getPrimaryColor(gear, partType);
    }

    @Override
    public ITextComponent getDisplayName(PartType partType, ItemStack gear) {
        return material.getDisplayName(partType, gear);
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeResourceLocation(this.material.getId());
        buffer.writeEnumValue(this.grade);
    }
}
