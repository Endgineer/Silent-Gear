package net.silentchaos512.gear.data.loot;

import net.minecraft.data.loot.ChestLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.silentchaos512.gear.init.LootInjector;
import net.silentchaos512.gear.init.ModBlocks;
import net.silentchaos512.gear.init.ModItems;
import net.silentchaos512.gear.item.CraftingItems;

import java.util.function.BiConsumer;

public class ModChestLootTables extends ChestLoot {
    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
        consumer.accept(LootInjector.Tables.CHESTS_NETHER_BRIDGE, addNetherMetalsAndFlora());
        consumer.accept(LootInjector.Tables.CHESTS_BASTION_OTHER, addNetherFlora(LootTable.lootTable()));
        consumer.accept(LootInjector.Tables.CHESTS_BASTION_BRIDGE, addNetherMetalsAndFlora());
        consumer.accept(LootInjector.Tables.CHESTS_RUINED_PORTAL, addNetherMetalsAndFlora());
    }

    private static LootTable.Builder addNetherMetalsAndFlora() {
        LootTable.Builder builder = LootTable.lootTable();
        addNetherFlora(builder);
        return builder;
    }

    private static LootTable.Builder addNetherFlora(LootTable.Builder builder) {
        builder.withPool(LootPool.lootPool()
                .setRolls(UniformGenerator.between(1, 2))
                .setBonusRolls(UniformGenerator.between(0, 1))
                .add(EmptyLootItem.emptyItem()
                        .setWeight(10)
                )
        );
        return builder;
    }
}
