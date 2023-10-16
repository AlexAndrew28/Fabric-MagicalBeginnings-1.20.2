package net.alex.magicalbeginnings.block;

import net.alex.magicalbeginnings.MagicalBeginnings;
import net.alex.magicalbeginnings.block.custom.RyftImbuingStationBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {

    public static final Block RYFT_BLOCK = registerBlock("ryft_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    public static final Block RYFT_ORE = registerBlock("ryft_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.STONE).strength(3f), UniformIntProvider.create(2, 5)));

    public static final Block DEEPSLATE_RYFT_ORE = registerBlock("deepslate_ryft_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE).strength(4f), UniformIntProvider.create(3, 6)));

    public static final Block NETHER_RYFT_ORE = registerBlock("nether_ryft_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.NETHERRACK).strength(2.5f), UniformIntProvider.create(3, 7)));

    public static final Block END_RYFT_ORE = registerBlock("end_ryft_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.END_STONE).strength(5f), UniformIntProvider.create(5, 7)));

    public static final Block RYFT_IMBUING_STATION = registerBlock("ryft_imbuing_station",
            new RyftImbuingStationBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque()));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(MagicalBeginnings.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, new Identifier(MagicalBeginnings.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks(){
        MagicalBeginnings.LOGGER.info("Registering ModBlocks for " + MagicalBeginnings.MOD_ID);
    }
}
