package net.silentchaos512.gear.init;

import com.google.common.collect.ImmutableList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.silentchaos512.gear.SilentGear;

import java.util.List;

public final class ModTags {
    public static final class Blocks {
        public static final TagKey<Block> PROSPECTOR_HAMMER_TARGETS = mod("prospector_hammer_targets");

        public static final TagKey<Block> ORES_TITANITE = forge("ores/titanite");

        private Blocks() {}

        private static TagKey<Block> forge(String path) {
            return BlockTags.create(new ResourceLocation("forge", path));
        }

        private static TagKey<Block> mod(String path) {
            return BlockTags.create(SilentGear.getId(path));
        }
    }

    public static final class Items {
        public static final TagKey<Item> ORES_TITANITE = forge("ores/titanite");

        public static final TagKey<Item> COAL_GENERATOR_FUELS = silentsMechanisms("coal_generator_fuels");

        public static final TagKey<Item> GEMS_TITANITE = forge("gems/titanite");

        public static final TagKey<Item> NUGGETS_DIAMOND = forge("nuggets/diamond");
        public static final TagKey<Item> NUGGETS_EMERALD = forge("nuggets/emerald");

        public static final TagKey<Item> PAPER = forge("paper");
        public static final TagKey<Item> BLUEPRINT_PAPER = mod("blueprint_paper");

        public static final TagKey<Item> RODS_IRON = forge("rods/iron");
        public static final TagKey<Item> RODS_STONE = forge("rods/stone");
        public static final TagKey<Item> RODS_ROUGH = mod("rods/rough");

        public static final TagKey<Item> FRUITS = forge("fruits");

        public static final TagKey<Item> AXES = forge("axes");
        public static final TagKey<Item> BOOTS = forge("boots");
        public static final TagKey<Item> BOWS = forge("bows");
        public static final TagKey<Item> CHESTPLATES = forge("chestplates");
        public static final TagKey<Item> CROSSBOWS = forge("crossbows");
        public static final TagKey<Item> ELYTRA = forge("elytra");
        public static final TagKey<Item> HAMMERS = forge("hammers");
        public static final TagKey<Item> HELMETS = forge("helmets");
        public static final TagKey<Item> HOES = forge("hoes");
        public static final TagKey<Item> KNIVES = forge("knives");
        public static final TagKey<Item> LEGGINGS = forge("leggings");
        public static final TagKey<Item> PICKAXES = forge("pickaxes");
        public static final TagKey<Item> SAWS = forge("saws");
        public static final TagKey<Item> SHIELDS = forge("shields");
        public static final TagKey<Item> SHOVELS = forge("shovels");
        public static final TagKey<Item> SICKLES = forge("sickles");
        public static final TagKey<Item> SWORDS = forge("swords");

        public static final TagKey<Item> BLUEPRINTS = mod("blueprints");

        private Items() {}

        private static TagKey<Item> forge(String path) {
            return ItemTags.create(new ResourceLocation("forge", path));
        }

        private static TagKey<Item> mod(String path) {
            return ItemTags.create(SilentGear.getId(path));
        }

        private static TagKey<Item> silentsMechanisms(String path) {
            return ItemTags.create(new ResourceLocation("silents_mechanisms", path));
        }
    }

    private ModTags() {}
}
