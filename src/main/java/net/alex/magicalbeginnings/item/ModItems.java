package net.alex.magicalbeginnings.item;

import net.alex.magicalbeginnings.MagicalBeginnings;
import net.alex.magicalbeginnings.item.custom.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static Item RYFT_CRYSTAL = registerItem("ryft_crystal", new Item(new FabricItemSettings()));
    public static Item RYFT_INGOT = registerItem("ryft_ingot", new Item(new FabricItemSettings()));

    public static Item BLUE_BERRY = registerItem("blue_berry", new Item(new FabricItemSettings().food(ModFoodComponents.BERRY)));
    public static Item BLACK_BERRY = registerItem("black_berry", new Item(new FabricItemSettings().food(ModFoodComponents.BERRY)));

    public static Item MAGICAL_STEW = registerItem("magical_stew", new MagicalStewItem(new FabricItemSettings().food(ModFoodComponents.MAGICAL_STEW)));
    public static Item CURSED_STEW = registerItem("cursed_stew", new CursedStewItem(new FabricItemSettings().food(ModFoodComponents.CURSED_STEW)));

    public static Item RUBY_WAND = registerItem("ruby_wand", new RubyWandItem(new FabricItemSettings().maxCount(1)));
    public static Item SAPPHIRE_WAND = registerItem("sapphire_wand", new SapphireWandItem(new FabricItemSettings().maxCount(1)));


    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(MagicalBeginnings.MOD_ID, name), item);
    }

    public static void registerModItems(){
        MagicalBeginnings.LOGGER.info("Registering Mod Items for " + MagicalBeginnings.MOD_ID);
    }
}
