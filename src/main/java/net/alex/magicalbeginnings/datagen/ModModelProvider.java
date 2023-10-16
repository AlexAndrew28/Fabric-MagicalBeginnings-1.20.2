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

        blockStateModelGenerator.registerSimpleState(ModBlocks.RYFT_IMBUING_STATION);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.RYFT_CRYSTAL, Models.GENERATED);
        itemModelGenerator.register(ModItems.RYFT_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLACK_BERRY, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLUE_BERRY, Models.GENERATED);
        itemModelGenerator.register(ModItems.MAGICAL_STEW, Models.GENERATED);
        itemModelGenerator.register(ModItems.CURSED_STEW, Models.GENERATED);

        itemModelGenerator.register(ModItems.FOCUS_SOCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.EMPTY_FOCUS, Models.GENERATED);

        itemModelGenerator.register(ModItems.AIR_FOCUS, Models.GENERATED);
        itemModelGenerator.register(ModItems.AIR_ESSENCE, Models.GENERATED);

        itemModelGenerator.register(ModItems.CORRUPTION_FOCUS, Models.GENERATED);
        itemModelGenerator.register(ModItems.CORRUPTION_ESSENCE, Models.GENERATED);

        itemModelGenerator.register(ModItems.FIRE_FOCUS, Models.GENERATED);
        itemModelGenerator.register(ModItems.FIRE_ESSENCE, Models.GENERATED);

        itemModelGenerator.register(ModItems.MAGIC_FOCUS, Models.GENERATED);
        itemModelGenerator.register(ModItems.MAGIC_ESSENCE, Models.GENERATED);

        itemModelGenerator.register(ModItems.NATURE_FOCUS, Models.GENERATED);
        itemModelGenerator.register(ModItems.NATURE_ESSENCE, Models.GENERATED);

        itemModelGenerator.register(ModItems.NECROTIC_FOCUS, Models.GENERATED);
        itemModelGenerator.register(ModItems.NECROTIC_ESSENCE, Models.GENERATED);

        itemModelGenerator.register(ModItems.WATER_FOCUS, Models.GENERATED);
        itemModelGenerator.register(ModItems.WATER_ESSENCE, Models.GENERATED);

    }
}
