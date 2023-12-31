package net.alex.magicalbeginnings.item;

import net.alex.magicalbeginnings.MagicalBeginnings;
import net.alex.magicalbeginnings.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.text.Text;
import net.minecraft.item.ItemGroup;

public class ModItemGroups {

    public static final ItemGroup MAGICAL_BEGINNINGS_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(MagicalBeginnings.MOD_ID, "ryft_crystal"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.main_group"))
                    .icon(() -> new ItemStack(ModItems.RYFT_CRYSTAL)).entries((displayContext, entries) -> {

                        entries.add(ModItems.RYFT_CRYSTAL);
                        entries.add(ModItems.RYFT_INGOT);

                        entries.add(ModItems.RUBY_WAND);
                        entries.add(ModItems.SAPPHIRE_WAND);
                        entries.add(ModItems.EARTH_AMULET);
                        entries.add(ModItems.SKY_AMULET);
                        entries.add(ModItems.EARTH_WAND);

                        entries.add(ModItems.MARKING_WAND);
                        entries.add(ModItems.TELEPORTING_AMULET);
                        entries.add(ModItems.TELEPORTING_WAND);

                        entries.add(ModItems.BLUE_BERRY);
                        entries.add(ModItems.BLACK_BERRY);

                        entries.add(ModItems.MAGICAL_STEW);
                        entries.add(ModItems.CURSED_STEW);

                        entries.add(ModBlocks.RYFT_BLOCK);

                        entries.add(ModBlocks.RYFT_ORE);
                        entries.add(ModBlocks.DEEPSLATE_RYFT_ORE);
                        entries.add(ModBlocks.NETHER_RYFT_ORE);
                        entries.add(ModBlocks.END_RYFT_ORE);

                        entries.add(ModBlocks.RYFT_IMBUING_STATION);

                        entries.add(ModItems.EMPTY_FOCUS);
                        entries.add(ModItems.FOCUS_SOCKET);

                        entries.add(ModItems.CORRUPTION_FOCUS);
                        entries.add(ModItems.FIRE_FOCUS);
                        entries.add(ModItems.WATER_FOCUS);
                        entries.add(ModItems.AIR_FOCUS);
                        entries.add(ModItems.NATURE_FOCUS);
                        entries.add(ModItems.NECROTIC_FOCUS);
                        entries.add(ModItems.MAGIC_FOCUS);

                        entries.add(ModItems.CORRUPTION_ESSENCE);
                        entries.add(ModItems.FIRE_ESSENCE);
                        entries.add(ModItems.WATER_ESSENCE);
                        entries.add(ModItems.AIR_ESSENCE);
                        entries.add(ModItems.NATURE_ESSENCE);
                        entries.add(ModItems.NECROTIC_ESSENCE);
                        entries.add(ModItems.MAGIC_ESSENCE);

                    }).build());

    /**
     * Logs that the item groups are being registered
     */
    public static void registerItemGroups(){
        MagicalBeginnings.LOGGER.info("Registering Item Groups for " + MagicalBeginnings.MOD_ID);
    }
}
