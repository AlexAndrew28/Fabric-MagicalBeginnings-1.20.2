package net.alex.magicalbeginnings.datagen;

import net.alex.magicalbeginnings.block.ModBlocks;
import net.alex.magicalbeginnings.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
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

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.FOCUS_SOCKET, 1)
                .pattern("ODO")
                .pattern("DOD")
                .pattern("ODO")
                .input('O', Items.OBSIDIAN)
                .input('D', Items.DIAMOND)
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .criterion(hasItem(Items.OBSIDIAN), conditionsFromItem(Items.OBSIDIAN))
                .offerTo(exporter, new Identifier("focus_socket_craft"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.EMPTY_FOCUS, 1)
                .pattern("GRG")
                .pattern("RFR")
                .pattern("GRG")
                .input('G', Items.GOLD_INGOT)
                .input('R', ModItems.RYFT_INGOT)
                .input('F', ModItems.FOCUS_SOCKET)
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                .criterion(hasItem(ModItems.RYFT_INGOT), conditionsFromItem(ModItems.RYFT_INGOT))
                .criterion(hasItem(ModItems.FOCUS_SOCKET), conditionsFromItem(ModItems.FOCUS_SOCKET))
                .offerTo(exporter, new Identifier("empty_focus_craft"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RUBY_WAND, 1)
                .pattern("   ")
                .pattern(" f ")
                .pattern(" s ")
                .input('s', Items.STICK)
                .input('f', ModItems.FIRE_FOCUS)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.FIRE_FOCUS), conditionsFromItem(ModItems.FIRE_FOCUS))
                .offerTo(exporter, new Identifier("ruby_wand_craft"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SAPPHIRE_WAND, 1)
                .pattern("   ")
                .pattern(" w ")
                .pattern(" s ")
                .input('s', Items.STICK)
                .input('w', ModItems.WATER_FOCUS)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.WATER_FOCUS), conditionsFromItem(ModItems.WATER_FOCUS))
                .offerTo(exporter, new Identifier("sapphire_wand_craft"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.FIRE_FOCUS, 1)
                .input(ModItems.FIRE_ESSENCE)
                .input(ModItems.EMPTY_FOCUS)
                .criterion(hasItem(ModItems.FIRE_ESSENCE), conditionsFromItem(ModItems.FIRE_ESSENCE))
                .criterion(hasItem(ModItems.EMPTY_FOCUS), conditionsFromItem(ModItems.EMPTY_FOCUS))
                .offerTo(exporter, new Identifier("fire_focus_craft"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.AIR_FOCUS, 1)
                .input(ModItems.AIR_ESSENCE)
                .input(ModItems.EMPTY_FOCUS)
                .criterion(hasItem(ModItems.AIR_ESSENCE), conditionsFromItem(ModItems.AIR_ESSENCE))
                .criterion(hasItem(ModItems.EMPTY_FOCUS), conditionsFromItem(ModItems.EMPTY_FOCUS))
                .offerTo(exporter, new Identifier("air_focus_craft"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CORRUPTION_FOCUS, 1)
                .input(ModItems.CORRUPTION_ESSENCE)
                .input(ModItems.EMPTY_FOCUS)
                .criterion(hasItem(ModItems.CORRUPTION_ESSENCE), conditionsFromItem(ModItems.CORRUPTION_ESSENCE))
                .criterion(hasItem(ModItems.EMPTY_FOCUS), conditionsFromItem(ModItems.EMPTY_FOCUS))
                .offerTo(exporter, new Identifier("corruption_focus_craft"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MAGIC_FOCUS, 1)
                .input(ModItems.MAGIC_ESSENCE)
                .input(ModItems.EMPTY_FOCUS)
                .criterion(hasItem(ModItems.MAGIC_ESSENCE), conditionsFromItem(ModItems.MAGIC_ESSENCE))
                .criterion(hasItem(ModItems.EMPTY_FOCUS), conditionsFromItem(ModItems.EMPTY_FOCUS))
                .offerTo(exporter, new Identifier("magic_focus_craft"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.NATURE_FOCUS, 1)
                .input(ModItems.NATURE_ESSENCE)
                .input(ModItems.EMPTY_FOCUS)
                .criterion(hasItem(ModItems.NATURE_ESSENCE), conditionsFromItem(ModItems.NATURE_ESSENCE))
                .criterion(hasItem(ModItems.EMPTY_FOCUS), conditionsFromItem(ModItems.EMPTY_FOCUS))
                .offerTo(exporter, new Identifier("nature_focus_craft"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.NECROTIC_FOCUS, 1)
                .input(ModItems.NECROTIC_ESSENCE)
                .input(ModItems.EMPTY_FOCUS)
                .criterion(hasItem(ModItems.NECROTIC_ESSENCE), conditionsFromItem(ModItems.NECROTIC_ESSENCE))
                .criterion(hasItem(ModItems.EMPTY_FOCUS), conditionsFromItem(ModItems.EMPTY_FOCUS))
                .offerTo(exporter, new Identifier("necrotic_focus_craft"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.WATER_FOCUS, 1)
                .input(ModItems.WATER_ESSENCE)
                .input(ModItems.EMPTY_FOCUS)
                .criterion(hasItem(ModItems.WATER_ESSENCE), conditionsFromItem(ModItems.WATER_ESSENCE))
                .criterion(hasItem(ModItems.EMPTY_FOCUS), conditionsFromItem(ModItems.EMPTY_FOCUS))
                .offerTo(exporter, new Identifier("water_focus_craft"));

        /*
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RYFT_INGOT, 1)
                .input(ModItems.RYFT_CRYSTAL)
                .input(Items.IRON_INGOT)
                .criterion(hasItem(ModItems.RYFT_CRYSTAL), conditionsFromItem(ModItems.RYFT_CRYSTAL))
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter, new Identifier("ryft_ingot_craft"));

         */

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
