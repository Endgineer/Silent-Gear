package net.silentchaos512.gear.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.crafting.RecipeType;
import net.silentchaos512.gear.SilentGear;
import net.silentchaos512.gear.api.item.GearType;
import net.silentchaos512.gear.api.part.PartType;
import net.silentchaos512.gear.config.Config;
import net.silentchaos512.gear.item.*;
import net.silentchaos512.gear.item.blueprint.GearBlueprintItem;
import net.silentchaos512.gear.item.blueprint.PartBlueprintItem;
import net.silentchaos512.gear.item.blueprint.book.BlueprintBookItem;
import net.silentchaos512.gear.item.gear.*;
import net.silentchaos512.lib.registry.ItemRegistryObject;
import net.silentchaos512.lib.util.TimeUtils;

import javax.annotation.Nullable;
import java.util.function.Supplier;

@SuppressWarnings({"unused", "OverlyCoupledClass"})
public final class ModItems {
    public static final ItemRegistryObject<TitaniteShardItem> TITANITE_SHARD = register("titanite_shard", () -> new TitaniteShardItem());

    public static final ItemRegistryObject<HeatedMetalItem> HEATED_METAL_ITEM = register("heated_metal_item", () -> new HeatedMetalItem());

    public static final ItemRegistryObject<Item> NOVITE_INGOT = register("novite_ingot", () -> new Item(new Item.Properties().tab(SilentGear.ITEM_GROUP)));
    public static final ItemRegistryObject<Item> BATHUS_INGOT = register("bathus_ingot", () -> new Item(new Item.Properties().tab(SilentGear.ITEM_GROUP)));
    public static final ItemRegistryObject<Item> MARMAROS_INGOT = register("marmaros_ingot", () -> new Item(new Item.Properties().tab(SilentGear.ITEM_GROUP)));
    public static final ItemRegistryObject<Item> KRATONITE_INGOT = register("kratonite_ingot", () -> new Item(new Item.Properties().tab(SilentGear.ITEM_GROUP)));
    public static final ItemRegistryObject<Item> FRACTITE_INGOT = register("fractite_ingot", () -> new Item(new Item.Properties().tab(SilentGear.ITEM_GROUP)));
    public static final ItemRegistryObject<Item> ZEPHYRIUM_INGOT = register("zephyrium_ingot", () -> new Item(new Item.Properties().tab(SilentGear.ITEM_GROUP)));
    public static final ItemRegistryObject<Item> ARGONITE_INGOT = register("argonite_ingot", () -> new Item(new Item.Properties().tab(SilentGear.ITEM_GROUP)));
    public static final ItemRegistryObject<Item> KATAGON_INGOT = register("katagon_ingot", () -> new Item(new Item.Properties().tab(SilentGear.ITEM_GROUP)));
    public static final ItemRegistryObject<Item> GORGONITE_INGOT = register("gorgonite_ingot", () -> new Item(new Item.Properties().tab(SilentGear.ITEM_GROUP)));
    public static final ItemRegistryObject<Item> PROMETHIUM_INGOT = register("promethium_ingot", () -> new Item(new Item.Properties().tab(SilentGear.ITEM_GROUP)));
    public static final ItemRegistryObject<Item> PRIMAL_INGOT = register("primal_ingot", () -> new Item(new Item.Properties().tab(SilentGear.ITEM_GROUP)));

    public static final ItemRegistryObject<Item> RAW_NOVITE = register("raw_novite", () -> new Item(new Item.Properties().tab(SilentGear.ITEM_GROUP)));
    public static final ItemRegistryObject<Item> RAW_BATHUS = register("raw_bathus", () -> new Item(new Item.Properties().tab(SilentGear.ITEM_GROUP)));
    public static final ItemRegistryObject<Item> RAW_MARMAROS = register("raw_marmaros", () -> new Item(new Item.Properties().tab(SilentGear.ITEM_GROUP)));
    public static final ItemRegistryObject<Item> RAW_KRATONITE = register("raw_kratonite", () -> new Item(new Item.Properties().tab(SilentGear.ITEM_GROUP)));
    public static final ItemRegistryObject<Item> RAW_FRACTITE = register("raw_fractite", () -> new Item(new Item.Properties().tab(SilentGear.ITEM_GROUP)));
    public static final ItemRegistryObject<Item> RAW_ZEPHYRIUM = register("raw_zephyrium", () -> new Item(new Item.Properties().tab(SilentGear.ITEM_GROUP)));
    public static final ItemRegistryObject<Item> RAW_ARGONITE = register("raw_argonite", () -> new Item(new Item.Properties().tab(SilentGear.ITEM_GROUP)));
    public static final ItemRegistryObject<Item> RAW_KATAGON = register("raw_katagon", () -> new Item(new Item.Properties().tab(SilentGear.ITEM_GROUP)));
    public static final ItemRegistryObject<Item> RAW_GORGONITE = register("raw_gorgonite", () -> new Item(new Item.Properties().tab(SilentGear.ITEM_GROUP)));
    public static final ItemRegistryObject<Item> RAW_PROMETHIUM = register("raw_promethium", () -> new Item(new Item.Properties().tab(SilentGear.ITEM_GROUP)));
    public static final ItemRegistryObject<Item> RAW_PRIMAL = register("raw_primal", () -> new Item(new Item.Properties().tab(SilentGear.ITEM_GROUP)));

    public static final ItemRegistryObject<Item> CRUSHED_RAW_NOVITE = register("crushed_raw_novite", () -> new Item(new Item.Properties().tab(SilentGear.ITEM_GROUP)));
    public static final ItemRegistryObject<Item> CRUSHED_RAW_BATHUS = register("crushed_raw_bathus", () -> new Item(new Item.Properties().tab(SilentGear.ITEM_GROUP)));
    public static final ItemRegistryObject<Item> CRUSHED_RAW_MARMAROS = register("crushed_raw_marmaros", () -> new Item(new Item.Properties().tab(SilentGear.ITEM_GROUP)));
    public static final ItemRegistryObject<Item> CRUSHED_RAW_KRATONITE = register("crushed_raw_kratonite", () -> new Item(new Item.Properties().tab(SilentGear.ITEM_GROUP)));
    public static final ItemRegistryObject<Item> CRUSHED_RAW_FRACTITE = register("crushed_raw_fractite", () -> new Item(new Item.Properties().tab(SilentGear.ITEM_GROUP)));
    public static final ItemRegistryObject<Item> CRUSHED_RAW_ZEPHYRIUM = register("crushed_raw_zephyrium", () -> new Item(new Item.Properties().tab(SilentGear.ITEM_GROUP)));
    public static final ItemRegistryObject<Item> CRUSHED_RAW_ARGONITE = register("crushed_raw_argonite", () -> new Item(new Item.Properties().tab(SilentGear.ITEM_GROUP)));
    public static final ItemRegistryObject<Item> CRUSHED_RAW_KATAGON = register("crushed_raw_katagon", () -> new Item(new Item.Properties().tab(SilentGear.ITEM_GROUP)));
    public static final ItemRegistryObject<Item> CRUSHED_RAW_GORGONITE = register("crushed_raw_gorgonite", () -> new Item(new Item.Properties().tab(SilentGear.ITEM_GROUP)));
    public static final ItemRegistryObject<Item> CRUSHED_RAW_PROMETHIUM = register("crushed_raw_promethium", () -> new Item(new Item.Properties().tab(SilentGear.ITEM_GROUP)));
    public static final ItemRegistryObject<Item> CRUSHED_RAW_PRIMAL = register("crushed_raw_primal", () -> new Item(new Item.Properties().tab(SilentGear.ITEM_GROUP)));

    public static final ItemRegistryObject<BlueprintPackageItem> BLUEPRINT_PACKAGE = register("blueprint_package", () ->
            new BlueprintPackageItem(SilentGear.getId("starter_blueprints")));

    //region Blueprints
    public static final ItemRegistryObject<BlueprintBookItem> BLUEPRINT_BOOK = register("blueprint_book", () ->
            new BlueprintBookItem(unstackableProps().rarity(Rarity.UNCOMMON)));
    // Blueprints
    public static final ItemRegistryObject<PartBlueprintItem> JEWELER_TOOLS = register("jeweler_tools", () ->
            new JewelerKitItem(PartType.ADORNMENT, false, unstackableProps()));
    public static final ItemRegistryObject<PartBlueprintItem> ROD_BLUEPRINT = registerPartBlueprint(PartType.ROD, false);
    public static final ItemRegistryObject<PartBlueprintItem> TIP_BLUEPRINT = registerPartBlueprint(PartType.TIP, false);
    public static final ItemRegistryObject<PartBlueprintItem> COATING_BLUEPRINT = registerPartBlueprint(PartType.COATING, false);
    public static final ItemRegistryObject<PartBlueprintItem> GRIP_BLUEPRINT = registerPartBlueprint(PartType.GRIP, false);
    public static final ItemRegistryObject<PartBlueprintItem> BINDING_BLUEPRINT = registerPartBlueprint(PartType.BINDING, false);
    public static final ItemRegistryObject<PartBlueprintItem> LINING_BLUEPRINT = registerPartBlueprint(PartType.LINING, false);
    public static final ItemRegistryObject<PartBlueprintItem> CORD_BLUEPRINT = registerPartBlueprint(PartType.CORD, false);
    public static final ItemRegistryObject<PartBlueprintItem> FLETCHING_BLUEPRINT = registerPartBlueprint(PartType.FLETCHING, false);
    public static final ItemRegistryObject<GearBlueprintItem> SWORD_BLUEPRINT = registerGearBlueprint(GearType.SWORD, false);
    public static final ItemRegistryObject<GearBlueprintItem> KATANA_BLUEPRINT = registerGearBlueprint(GearType.KATANA, false);
    public static final ItemRegistryObject<GearBlueprintItem> MACHETE_BLUEPRINT = registerGearBlueprint(GearType.MACHETE, false);
    public static final ItemRegistryObject<GearBlueprintItem> SPEAR_BLUEPRINT = registerGearBlueprint(GearType.SPEAR, false);
    public static final ItemRegistryObject<GearBlueprintItem> TRIDENT_BLUEPRINT = registerGearBlueprint(GearType.TRIDENT, false);
    public static final ItemRegistryObject<GearBlueprintItem> KNIFE_BLUEPRINT = registerGearBlueprint(GearType.KNIFE, false);
    public static final ItemRegistryObject<GearBlueprintItem> DAGGER_BLUEPRINT = registerGearBlueprint(GearType.DAGGER, false);
    public static final ItemRegistryObject<GearBlueprintItem> PICKAXE_BLUEPRINT = registerGearBlueprint(GearType.PICKAXE, false);
    public static final ItemRegistryObject<GearBlueprintItem> SHOVEL_BLUEPRINT = registerGearBlueprint(GearType.SHOVEL, false);
    public static final ItemRegistryObject<GearBlueprintItem> AXE_BLUEPRINT = registerGearBlueprint(GearType.AXE, false);
    public static final ItemRegistryObject<GearBlueprintItem> PAXEL_BLUEPRINT = registerGearBlueprint(GearType.PAXEL, false);
    public static final ItemRegistryObject<GearBlueprintItem> HAMMER_BLUEPRINT = registerGearBlueprint(GearType.HAMMER, false);
    public static final ItemRegistryObject<GearBlueprintItem> EXCAVATOR_BLUEPRINT = registerGearBlueprint(GearType.EXCAVATOR, false);
    public static final ItemRegistryObject<GearBlueprintItem> SAW_BLUEPRINT = registerGearBlueprint(GearType.SAW, false);
    public static final ItemRegistryObject<GearBlueprintItem> MATTOCK_BLUEPRINT = registerGearBlueprint(GearType.MATTOCK, false);
    public static final ItemRegistryObject<GearBlueprintItem> PROSPECTOR_HAMMER_BLUEPRINT = registerGearBlueprint(GearType.PROSPECTOR_HAMMER, false);
    public static final ItemRegistryObject<GearBlueprintItem> SICKLE_BLUEPRINT = registerGearBlueprint(GearType.SICKLE, false);
    public static final ItemRegistryObject<GearBlueprintItem> SHEARS_BLUEPRINT = registerGearBlueprint(GearType.SHEARS, false);
    public static final ItemRegistryObject<GearBlueprintItem> FISHING_ROD_BLUEPRINT = registerGearBlueprint(GearType.FISHING_ROD, false);
    public static final ItemRegistryObject<GearBlueprintItem> BOW_BLUEPRINT = registerGearBlueprint(GearType.BOW, false);
    public static final ItemRegistryObject<GearBlueprintItem> CROSSBOW_BLUEPRINT = registerGearBlueprint(GearType.CROSSBOW, false);
    public static final ItemRegistryObject<GearBlueprintItem> SLINGSHOT_BLUEPRINT = registerGearBlueprint(GearType.SLINGSHOT, false);
    public static final ItemRegistryObject<GearBlueprintItem> SHIELD_BLUEPRINT = registerGearBlueprint(GearType.SHIELD, false);
    public static final ItemRegistryObject<GearBlueprintItem> HELMET_BLUEPRINT = registerGearBlueprint(GearType.HELMET, false);
    public static final ItemRegistryObject<GearBlueprintItem> CHESTPLATE_BLUEPRINT = registerGearBlueprint(GearType.CHESTPLATE, false);
    public static final ItemRegistryObject<GearBlueprintItem> LEGGINGS_BLUEPRINT = registerGearBlueprint(GearType.LEGGINGS, false);
    public static final ItemRegistryObject<GearBlueprintItem> BOOTS_BLUEPRINT = registerGearBlueprint(GearType.BOOTS, false);
    public static final ItemRegistryObject<GearBlueprintItem> ELYTRA_BLUEPRINT = registerGearBlueprint(GearType.ELYTRA, false);
    public static final ItemRegistryObject<GearBlueprintItem> ARROW_BLUEPRINT = registerGearBlueprint(GearType.ARROW, false);
    public static final ItemRegistryObject<GearBlueprintItem> RING_BLUEPRINT = registerGearBlueprint(GearType.RING, false);
    public static final ItemRegistryObject<GearBlueprintItem> BRACELET_BLUEPRINT = registerGearBlueprint(GearType.BRACELET, false);
    //endregion

    //region Compound Parts and Tool Heads
    // Tool Heads
    public static final ItemRegistryObject<MainPartItem> SWORD_BLADE = registerCompoundPart("sword_blade", () ->
            new MainPartItem(GearType.SWORD, unstackableProps()));
    public static final ItemRegistryObject<MainPartItem> KATANA_BLADE = registerCompoundPart("katana_blade", () ->
            new MainPartItem(GearType.KATANA, unstackableProps()));
    public static final ItemRegistryObject<MainPartItem> MACHETE_BLADE = registerCompoundPart("machete_blade", () ->
            new MainPartItem(GearType.MACHETE, unstackableProps()));
    public static final ItemRegistryObject<MainPartItem> SPEAR_TIP = registerCompoundPart("spear_tip", () ->
            new MainPartItem(GearType.SPEAR, unstackableProps()));
    public static final ItemRegistryObject<MainPartItem> TRIDENT_PRONGS = registerCompoundPart("trident_prongs", () ->
            new MainPartItem(GearType.TRIDENT, unstackableProps()));
    public static final ItemRegistryObject<MainPartItem> KNIFE_BLADE = registerCompoundPart("knife_blade", () ->
            new MainPartItem(GearType.KNIFE, unstackableProps()));
    public static final ItemRegistryObject<MainPartItem> DAGGER_BLADE = registerCompoundPart("dagger_blade", () ->
            new MainPartItem(GearType.DAGGER, unstackableProps()));
    public static final ItemRegistryObject<MainPartItem> PICKAXE_HEAD = registerCompoundPart("pickaxe_head", () ->
            new MainPartItem(GearType.PICKAXE, unstackableProps()));
    public static final ItemRegistryObject<MainPartItem> SHOVEL_HEAD = registerCompoundPart("shovel_head", () ->
            new MainPartItem(GearType.SHOVEL, unstackableProps()));
    public static final ItemRegistryObject<MainPartItem> AXE_HEAD = registerCompoundPart("axe_head", () ->
            new MainPartItem(GearType.AXE, unstackableProps()));
    public static final ItemRegistryObject<MainPartItem> PAXEL_HEAD = registerCompoundPart("paxel_head", () ->
            new MainPartItem(GearType.PAXEL, unstackableProps()));
    public static final ItemRegistryObject<MainPartItem> HAMMER_HEAD = registerCompoundPart("hammer_head", () ->
            new MainPartItem(GearType.HAMMER, unstackableProps()));
    public static final ItemRegistryObject<MainPartItem> EXCAVATOR_HEAD = registerCompoundPart("excavator_head", () ->
            new MainPartItem(GearType.EXCAVATOR, unstackableProps()));
    public static final ItemRegistryObject<MainPartItem> SAW_BLADE = registerCompoundPart("saw_blade", () ->
            new MainPartItem(GearType.SAW, unstackableProps()));
    public static final ItemRegistryObject<MainPartItem> MATTOCK_HEAD = registerCompoundPart("mattock_head", () ->
            new MainPartItem(GearType.MATTOCK, unstackableProps()));
    public static final ItemRegistryObject<MainPartItem> PROSPECTOR_HAMMER_HEAD = registerCompoundPart("prospector_hammer_head", () ->
            new MainPartItem(GearType.PROSPECTOR_HAMMER, unstackableProps()));
    public static final ItemRegistryObject<MainPartItem> SICKLE_BLADE = registerCompoundPart("sickle_blade", () ->
            new MainPartItem(GearType.SICKLE, unstackableProps()));
    public static final ItemRegistryObject<MainPartItem> SHEARS_BLADES = registerCompoundPart("shears_blades", () ->
            new MainPartItem(GearType.SHEARS, unstackableProps()));
    public static final ItemRegistryObject<MainPartItem> FISHING_REEL_AND_HOOK = registerCompoundPart("fishing_reel_and_hook", () ->
            new MainPartItem(GearType.FISHING_ROD, unstackableProps()));
    public static final ItemRegistryObject<MainPartItem> BOW_LIMBS = registerCompoundPart("bow_limbs", () ->
            new MainPartItem(GearType.BOW, unstackableProps()));
    public static final ItemRegistryObject<MainPartItem> CROSSBOW_LIMBS = registerCompoundPart("crossbow_limbs", () ->
            new MainPartItem(GearType.CROSSBOW, unstackableProps()));
    public static final ItemRegistryObject<MainPartItem> SLINGSHOT_LIMBS = registerCompoundPart("slingshot_limbs", () ->
            new MainPartItem(GearType.SLINGSHOT, unstackableProps()));
    public static final ItemRegistryObject<MainPartItem> SHIELD_PLATE = registerCompoundPart("shield_plate", () ->
            new MainPartItem(GearType.SHIELD, new Item.Properties().stacksTo(1)));
    public static final ItemRegistryObject<MainPartItem> HELMET_PLATES = registerCompoundPart("helmet_plates", () ->
            new MainPartItem(GearType.HELMET, unstackableProps()));
    public static final ItemRegistryObject<MainPartItem> CHESTPLATE_PLATES = registerCompoundPart("chestplate_plates", () ->
            new MainPartItem(GearType.CHESTPLATE, unstackableProps()));
    public static final ItemRegistryObject<MainPartItem> LEGGING_PLATES = registerCompoundPart("legging_plates", () ->
            new MainPartItem(GearType.LEGGINGS, unstackableProps()));
    public static final ItemRegistryObject<MainPartItem> BOOT_PLATES = registerCompoundPart("boot_plates", () ->
            new MainPartItem(GearType.BOOTS, unstackableProps()));
    public static final ItemRegistryObject<MainPartItem> ELYTRA_WINGS = registerCompoundPart("elytra_wings", () ->
            new MainPartItem(GearType.ELYTRA, unstackableProps()));
    public static final ItemRegistryObject<MainPartItem> ARROW_HEADS = registerCompoundPart("arrow_heads", () ->
            new MainPartItem(GearType.ARROW, unstackableProps()));
    public static final ItemRegistryObject<MainPartItem> RING_SHANK = registerCompoundPart("ring_shank", () ->
            new MainPartItem(GearType.RING, unstackableProps()));
    public static final ItemRegistryObject<MainPartItem> BRACELET_BAND = registerCompoundPart("bracelet_band", () ->
            new MainPartItem(GearType.BRACELET, unstackableProps()));
    // Compound Parts
    public static final ItemRegistryObject<CompoundPartItem> ROD = registerCompoundPart("rod", () ->
            new CompoundPartItem(PartType.ROD, baseProps()));
    public static final ItemRegistryObject<CompoundPartItem> LONG_ROD = registerCompoundPart("long_rod", () ->
            new CompoundPartItem(PartType.ROD, baseProps()));
    public static final ItemRegistryObject<CompoundPartItem> TIP = registerCompoundPart("tip", () ->
            new CompoundPartItem(PartType.TIP, baseProps()));
    public static final ItemRegistryObject<CompoundPartItem> COATING = registerCompoundPart("coating", () ->
            new CompoundPartItem(PartType.COATING, baseProps()));
    public static final ItemRegistryObject<CompoundPartItem> GRIP = registerCompoundPart("grip", () ->
            new CompoundPartItem(PartType.GRIP, baseProps()));
    public static final ItemRegistryObject<CompoundPartItem> BINDING = registerCompoundPart("binding", () ->
            new CompoundPartItem(PartType.BINDING, baseProps()));
    public static final ItemRegistryObject<CompoundPartItem> LINING = registerCompoundPart("lining", () ->
            new CompoundPartItem(PartType.LINING, baseProps()));
    public static final ItemRegistryObject<CompoundPartItem> CORD = registerCompoundPart("cord", () ->
            new CompoundPartItem(PartType.CORD, baseProps()));
    public static final ItemRegistryObject<CompoundPartItem> FLETCHING = registerCompoundPart("fletching", () ->
            new CompoundPartItem(PartType.FLETCHING, baseProps()));
    public static final ItemRegistryObject<CompoundPartItem> ADORNMENT = registerCompoundPart("adornment", () ->
            new CompoundPartItem(PartType.ADORNMENT, baseProps()));
    //endregion

    // Compound materials
    public static final ItemRegistryObject<CompoundMaterialItem> ALLOY_INGOT = register("alloy_ingot", () ->
            new CompoundMaterialItem(baseProps()));
    public static final ItemRegistryObject<CompoundMaterialItem> HYBRID_GEM = register("hybrid_gem", () ->
            new CompoundMaterialItem(baseProps()));
    public static final ItemRegistryObject<CompoundMaterialItem> MIXED_FABRIC = register("mixed_fabric", () ->
            new CompoundMaterialItem(baseProps()));

    // Custom materials
    public static final ItemRegistryObject<CustomMaterialItem> CUSTOM_INGOT = register("custom_ingot", () ->
            new CustomMaterialItem(baseProps()));
    public static final ItemRegistryObject<CustomMaterialItem> CUSTOM_GEM = register("custom_gem", () ->
            new CustomMaterialItem(baseProps()));

    public static final ItemRegistryObject<CraftedMaterialItem> SHEET_METAL = register("sheet_metal", () ->
            new CraftedMaterialItem(baseProps()));

    static {
        CraftingItems.register(Registration.ITEMS);
    }

    public static final ItemRegistryObject<FragmentItem> FRAGMENT = register("fragment", () -> new FragmentItem(baseProps()));

    public static final ItemRegistryObject<Item> PEBBLE = register("pebble", () -> new SlingshotAmmoItem(baseProps()));

    public static final ItemRegistryObject<GearSwordItem> SWORD = register("sword", () -> new GearSwordItem(GearType.SWORD));
    public static final ItemRegistryObject<GearSwordItem> KATANA = register("katana", () -> new GearSwordItem(GearType.KATANA));
    public static final ItemRegistryObject<GearMacheteItem> MACHETE = register("machete", () -> new GearMacheteItem(GearType.MACHETE));
    public static final ItemRegistryObject<GearSwordItem> SPEAR = register("spear", () -> new GearSwordItem(GearType.SPEAR));
    public static final ItemRegistryObject<GearTridentItem> TRIDENT = register("trident", () -> new GearTridentItem());
    public static final ItemRegistryObject<GearDaggerItem> KNIFE = register("knife", () -> new GearDaggerItem(GearType.KNIFE));
    public static final ItemRegistryObject<GearDaggerItem> DAGGER = register("dagger", () -> new GearDaggerItem(GearType.DAGGER));
    public static final ItemRegistryObject<GearPickaxeItem> PICKAXE = register("pickaxe", () -> new GearPickaxeItem(GearType.PICKAXE));
    public static final ItemRegistryObject<GearShovelItem> SHOVEL = register("shovel", () -> new GearShovelItem(GearType.SHOVEL));
    public static final ItemRegistryObject<GearAxeItem> AXE = register("axe", () -> new GearAxeItem(GearType.AXE));
    public static final ItemRegistryObject<GearPaxelItem> PAXEL = register("paxel", () -> new GearPaxelItem(GearType.PAXEL));
    public static final ItemRegistryObject<GearHammerItem> HAMMER = register("hammer", () -> new GearHammerItem(GearType.HAMMER));
    public static final ItemRegistryObject<GearExcavatorItem> EXCAVATOR = register("excavator", () -> new GearExcavatorItem(GearType.EXCAVATOR));
    public static final ItemRegistryObject<GearSawItem> SAW = register("saw", () -> new GearSawItem(GearType.SAW));
    public static final ItemRegistryObject<GearProspectorHammerItem> PROSPECTOR_HAMMER = register("prospector_hammer", () -> new GearProspectorHammerItem(GearType.PROSPECTOR_HAMMER));
    public static final ItemRegistryObject<GearMattockItem> MATTOCK = register("mattock", () -> new GearMattockItem());
    public static final ItemRegistryObject<GearSickleItem> SICKLE = register("sickle", () -> new GearSickleItem(GearType.SICKLE));
    public static final ItemRegistryObject<GearShearsItem> SHEARS = register("shears", () -> new GearShearsItem());
    public static final ItemRegistryObject<GearFishingRodItem> FISHING_ROD = register("fishing_rod", GearFishingRodItem::new);
    public static final ItemRegistryObject<GearBowItem> BOW = register("bow", () -> new GearBowItem());
    public static final ItemRegistryObject<GearCrossbowItem> CROSSBOW = register("crossbow", () -> new GearCrossbowItem());
    public static final ItemRegistryObject<GearSlingshotItem> SLINGSHOT = register("slingshot", () -> new GearSlingshotItem());
    public static final ItemRegistryObject<GearShieldItem> SHIELD = register("shield", () -> new GearShieldItem());
    public static final ItemRegistryObject<GearArrowItem> ARROW = register("arrow", () -> new GearArrowItem(unstackableProps()));

    public static final ItemRegistryObject<GearArmorItem> HELMET = register("helmet", () -> new GearArmorItem(EquipmentSlot.HEAD));
    public static final ItemRegistryObject<GearArmorItem> CHESTPLATE = register("chestplate", () -> new GearArmorItem(EquipmentSlot.CHEST));
    public static final ItemRegistryObject<GearArmorItem> LEGGINGS = register("leggings", () -> new GearArmorItem(EquipmentSlot.LEGS));
    public static final ItemRegistryObject<GearArmorItem> BOOTS = register("boots", () -> new GearArmorItem(EquipmentSlot.FEET));

    public static final ItemRegistryObject<GearElytraItem> ELYTRA = register("elytra", () -> new GearElytraItem(unstackableProps()));

    public static final ItemRegistryObject<GearCurioItem> RING = register("ring", () ->
            new GearCurioItem(GearType.RING, "ring", unstackableProps()));
    public static final ItemRegistryObject<GearCurioItem> BRACELET = register("bracelet", () ->
            new GearCurioItem(GearType.BRACELET, "bracelet", unstackableProps()));

    private ModItems() {}

    static void register() {}

    private static Item.Properties baseProps() {
        return new Item.Properties().tab(SilentGear.ITEM_GROUP);
    }

    private static Item.Properties unstackableProps() {
        return baseProps().stacksTo(1);
    }

    private static <T extends Item> ItemRegistryObject<T> register(String name, Supplier<T> item) {
        return new ItemRegistryObject<>(Registration.ITEMS.register(name, item));
    }

    private static <T extends CompoundPartItem> ItemRegistryObject<T> registerCompoundPart(String name, Supplier<T> item) {
        return register(name, item);
    }

    private static ItemRegistryObject<GearBlueprintItem> registerGearBlueprint(GearType gearType, boolean singleUse) {
        String name = gearType.getName() + "_blueprint";
        return register(name, () -> new GearBlueprintItem(gearType, singleUse, baseProps()));
    }

    private static ItemRegistryObject<PartBlueprintItem> registerPartBlueprint(PartType partType, boolean singleUse) {
        String name = partType.getName().getPath() + "_blueprint";
        return register(name, () -> new PartBlueprintItem(partType, singleUse, baseProps()));
    }
}
