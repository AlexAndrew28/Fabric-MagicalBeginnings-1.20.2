package net.alex.magicalbeginnings.util;

import net.alex.magicalbeginnings.MagicalBeginnings;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> RYFT_ORES = createTag("ryft_ores");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(MagicalBeginnings.MOD_ID, name));
        }
    }


    public static class Items {
        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(MagicalBeginnings.MOD_ID, name));
        }
    }
}
