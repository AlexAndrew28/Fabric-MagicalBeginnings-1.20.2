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

                        entries.add(ModItems.BLUE_BERRY);
                        entries.add(ModItems.BLACK_BERRY);

                        entries.add(ModBlocks.RYFT_BLOCK);

                        entries.add(ModBlocks.RYFT_ORE);
                        entries.add(ModBlocks.DEEPSLATE_RYFT_ORE);
                        entries.add(ModBlocks.NETHER_RYFT_ORE);
                        entries.add(ModBlocks.END_RYFT_ORE);

                    }).build());

    public static void registerItemGroups(){
        MagicalBeginnings.LOGGER.info("Registering Item Groups for " + MagicalBeginnings.MOD_ID);
    }
}
