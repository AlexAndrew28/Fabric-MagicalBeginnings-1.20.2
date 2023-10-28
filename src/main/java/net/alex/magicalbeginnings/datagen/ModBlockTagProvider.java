package net.alex.magicalbeginnings.datagen;

import net.alex.magicalbeginnings.block.ModBlocks;
import net.alex.magicalbeginnings.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    /**
     * create and build tags for blocks
     *
     * @param arg ?
     */
    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ModTags.Blocks.RYFT_ORES)
                .add(ModBlocks.RYFT_ORE)
                .add(ModBlocks.DEEPSLATE_RYFT_ORE)
                .add(ModBlocks.NETHER_RYFT_ORE)
                .add(ModBlocks.END_RYFT_ORE);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.RYFT_BLOCK)
                .add(ModBlocks.RYFT_ORE)
                .add(ModBlocks.DEEPSLATE_RYFT_ORE)
                .add(ModBlocks.NETHER_RYFT_ORE)
                .add(ModBlocks.END_RYFT_ORE);

        /*
        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.BLOCKNAME);
         */

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.RYFT_BLOCK)
                .add(ModBlocks.RYFT_ORE)
                .add(ModBlocks.DEEPSLATE_RYFT_ORE)
                .add(ModBlocks.NETHER_RYFT_ORE);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.END_RYFT_ORE);

        /*
        getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("fabric", "needs_tool_level_4")))
                .add(ModBlocks.BLOCKNAME);
         */
    }

}
