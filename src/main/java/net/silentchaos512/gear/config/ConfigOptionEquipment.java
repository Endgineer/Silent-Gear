package net.silentchaos512.gear.config;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gnu.trove.map.hash.THashMap;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.config.Configuration;
import net.silentchaos512.gear.SilentGear;
import net.silentchaos512.gear.api.item.ICoreItem;
import net.silentchaos512.gear.api.stats.ItemStat;
import net.silentchaos512.gear.api.stats.StatInstance;
import net.silentchaos512.gear.api.stats.StatInstance.Operation;

import javax.annotation.Nonnull;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ConfigOptionEquipment {
    private static final String[] RECIPE_INGREDIENTS = {"head_count", "rod_count", "bowstring_count"};

    public final ICoreItem item;
    public final String name;

    private boolean canCraft = true;
    private boolean isVisible = true;

    private Map<ItemStat, StatInstance> modifiers = new THashMap<>();
    private Map<ItemStat, Float> baseModifiers = new THashMap<>();
    private Map<String, Integer> recipe = new HashMap<>();
    // TODO: Configurable harvest speeds by class? Or block material?
//    private float efficiencyAsPickaxe = 0f;
//    private float efficiencyAsShovel = 0f;
//    private float efficiencyAsAxe = 0f;

    public <T extends ICoreItem> ConfigOptionEquipment(T item) {
        this.item = item;
        this.name = item.getGearClass();
    }

    public ConfigOptionEquipment loadValue(Configuration config) {
        return loadValue(config, Config.CAT_TOOLS + Config.SEP + name);
    }

    public ConfigOptionEquipment loadValue(Configuration config, String category) {
        loadJsonResources();
        return this;
    }

    @Nonnull
    public StatInstance getBaseModifier(ItemStat stat) {
        float value = this.baseModifiers.containsKey(stat) ? this.baseModifiers.get(stat) : 0;
        return new StatInstance(this.item.getGearClass() + "_basemod", value, Operation.ADD);
    }

    @Nonnull
    public StatInstance getStatModifier(ItemStat stat) {
        if (this.modifiers.containsKey(stat))
            return this.modifiers.get(stat);
        return StatInstance.ZERO;
    }

    public int getHeadCount() {
        return this.recipe.getOrDefault("head_count", 0);
    }

    public int getRodCount() {
        return this.recipe.getOrDefault("rod_count", 0);
    }

    public int getBowstringCount() {
        return this.recipe.getOrDefault("bowstring_count", 0);
    }

//    public Set<String> getToolClasses(ItemStack stack) {
//        if (GearHelper.isBroken(stack)) return ImmutableSet.of();
//
//        ImmutableSet.Builder<String> builder = new ImmutableSet.Builder<>();
//        if (efficiencyAsPickaxe > 0f) builder.add(ToolClass.PICKAXE.getName());
//        if (efficiencyAsShovel > 0f) builder.add(ToolClass.SHOVEL.getName());
//        if (efficiencyAsAxe > 0f) builder.add(ToolClass.AXE.getName());
//        return builder.build();
//    }

//    public float getEfficiencyAsTool(ToolClass toolClass) {
//        if (toolClass == ToolClass.PICKAXE) return efficiencyAsPickaxe;
//        else if (toolClass == ToolClass.SHOVEL) return efficiencyAsShovel;
//        else if (toolClass == ToolClass.AXE) return efficiencyAsAxe;
//        else return 0f;
//    }

    public boolean canCraft() {
        return this.canCraft;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    private void loadJsonResources() {
        // Main resource file in JAR
        String path = "assets/" + SilentGear.MOD_ID + "/equipment/" + item.getGearClass() + ".json";
        SilentGear.log.info("Loading equipment asset file: " + path);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(path), "UTF-8"))) {
            readResourceFile(reader);
        } catch (Exception e) {
            SilentGear.log.severe("Error loading resource file! Either Silent screwed up or the JAR has been modified.");
            SilentGear.log.severe("    item: " + item);
            SilentGear.log.severe("    item type: " + item.getGearClass());
            e.printStackTrace();
        }

        // Override in config folder
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(Config.INSTANCE.directory.getPath(), "equipment/" + item.getGearClass() + ".json")))) {
            readResourceFile(reader);
        } catch (FileNotFoundException ex) {
            // Ignore
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readResourceFile(BufferedReader reader) {
        JsonElement je = (new GsonBuilder().create()).fromJson(reader, JsonElement.class);
        JsonObject json = je.getAsJsonObject();

        JsonElement elementMods = json.get("modifiers");
        if (elementMods.isJsonArray()) {
            JsonArray array = elementMods.getAsJsonArray();
            for (JsonElement element : array) {
                JsonObject obj = element.getAsJsonObject();
                String name = obj.has("name") ? JsonUtils.getString(obj, "name") : "";
                ItemStat stat = ItemStat.ALL_STATS.get(name);
                if (stat != null) {
                    float value = obj.has("value") ? JsonUtils.getFloat(obj, "value") : 0f;
                    Operation op = obj.has("op") ? Operation.byName(JsonUtils.getString(obj, "op")) : Operation.MUL1;
                    String id = this.item.getGearClass() + "_mod_" + stat.getUnlocalizedName();
                    this.modifiers.put(stat, new StatInstance(id, value, op));
                }
            }
        }

        JsonElement elementBaseMods = json.get("base_modifiers");
        if (elementBaseMods.isJsonObject()) {
            JsonObject obj = elementBaseMods.getAsJsonObject();
            for (ItemStat stat : ItemStat.ALL_STATS.values()) {
                if (obj.has(stat.getUnlocalizedName())) {
                    float value = obj.get(stat.getUnlocalizedName()).getAsFloat();
                    this.baseModifiers.put(stat, value);
                }
            }
        }

        if (JsonUtils.hasField(json, "crafting")) {
            JsonObject obj = JsonUtils.getJsonObject(json, "crafting");
            for (String type : RECIPE_INGREDIENTS)
                if (obj.has(type))
                    this.recipe.put(type, obj.get(type).getAsInt());
            this.canCraft = JsonUtils.getBoolean(obj, "can_craft", this.canCraft);
            this.isVisible = JsonUtils.getBoolean(obj, "visible", this.isVisible);
        }

//        if (JsonUtils.hasField(json, "tool_classes")) {
//            JsonObject obj = JsonUtils.getJsonObject(json, "tool_classes");
//            this.efficiencyAsPickaxe = JsonUtils.getFloat(obj, "pickaxe", this.efficiencyAsPickaxe);
//            this.efficiencyAsShovel = JsonUtils.getFloat(obj, "shovel", this.efficiencyAsShovel);
//            this.efficiencyAsAxe = JsonUtils.getFloat(obj, "axe", this.efficiencyAsAxe);
//        }
    }
}
