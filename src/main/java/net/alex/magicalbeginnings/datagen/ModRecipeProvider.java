package net.alex.magicalbeginnings.datagen;

import net.alex.magicalbeginnings.block.ModBlocks;
import net.alex.magicalbeginnings.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModRecipeProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> RYFT_SMELTABLES = List.of(
            ModBlocks.RYFT_ORE,
            ModBlocks.DEEPSLATE_RYFT_ORE,
            ModBlocks.NETHER_RYFT_ORE,
            ModBlocks.END_RYFT_ORE
    );

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerSmelting(exporter, RYFT_SMELTABLES, RecipeCategory.MISC, ModItems.RYFT_CRYSTAL, 0.7f, 200, "ryft");
        offerBlasting(exporter, RYFT_SMELTABLES, RecipeCategory.MISC, ModItems.RYFT_CRYSTAL, 0.7f, 100, "ryft");

        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.RYFT_INGOT, RecipeCategory.MISC, ModBlocks.RYFT_BLOCK);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RYFT_INGOT, 1)
                .input(ModItems.RYFT_CRYSTAL)
                .input(Items.IRON_INGOT)
                .criterion(hasItem(ModItems.RYFT_CRYSTAL), conditionsFromItem(ModItems.RYFT_CRYSTAL))
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter, new Identifier("ryft_ingot_craft"));

        /*
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ITEMNAME, 1)
                .pattern("ABC")
                .pattern("ABC")
                .pattern("ABC")
                .input("A", ModItems.ITEMNAME)
                .input("B", Items.ITEMNAME)
                .input("c", Items.ITEMNAME)
                .criterion(hasItem(ModItems.ITEMNAME), conditionsFromItem(ModItems.ITEMNAME))
                .criterion(hasItem(Items.ITEMNAME), conditionsFromItem(Items.ITEMNAME))
                .criterion(hasItem(Items.ITEMNAME), conditionsFromItem(Items.ITEMNAME))
                .offerTo(exporter, new Identifier("identifier_name"));
         */
    }
}
