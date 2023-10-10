package net.alex.magicalbeginnings.datagen;

import net.alex.magicalbeginnings.block.ModBlocks;
import net.alex.magicalbeginnings.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RYFT_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RYFT_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_RYFT_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.NETHER_RYFT_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.END_RYFT_ORE);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.RYFT_CRYSTAL, Models.GENERATED);
        itemModelGenerator.register(ModItems.RYFT_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLACK_BERRY, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLUE_BERRY, Models.GENERATED);

    }
}
