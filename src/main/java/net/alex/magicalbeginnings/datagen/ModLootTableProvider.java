package net.alex.magicalbeginnings.datagen;


import net.alex.magicalbeginnings.block.ModBlocks;
import net.alex.magicalbeginnings.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.RYFT_BLOCK);

        addDrop(ModBlocks.RYFT_ORE, oreDrops(ModBlocks.RYFT_ORE, ModItems.RYFT_CRYSTAL, 1.0f, 2.0f));
        addDrop(ModBlocks.DEEPSLATE_RYFT_ORE, oreDrops(ModBlocks.DEEPSLATE_RYFT_ORE, ModItems.RYFT_CRYSTAL, 1.0f, 3.0f));
        addDrop(ModBlocks.NETHER_RYFT_ORE, oreDrops(ModBlocks.NETHER_RYFT_ORE, ModItems.RYFT_CRYSTAL, 0.0f, 6.0f));
        addDrop(ModBlocks.END_RYFT_ORE, oreDrops(ModBlocks.END_RYFT_ORE, ModItems.RYFT_CRYSTAL, 4.0f, 5.0f));



    }

    private LootTable.Builder oreDrops(Block ore, Item drop, Float min, Float max){
        return BlockLootTableGenerator.dropsWithSilkTouch(ore, (LootPoolEntry.Builder)this.applyExplosionDecay(drop,((LeafEntry.Builder)
                ItemEntry.builder(drop).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(min, max))))
                .apply(ApplyBonusLootFunction.oreDrops((Enchantments.FORTUNE)))));
    }
}
