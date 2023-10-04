package net.alex.magicalbeginnings.item;

import net.alex.magicalbeginnings.MagicalBeginnings;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static Item RYFT_CRYSTAL = registerItem("ryft_crystal", new Item(new FabricItemSettings()));
    public static Item RYFT_INGOT = registerItem("ryft_ingot", new Item(new FabricItemSettings()));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(MagicalBeginnings.MOD_ID, name), item);
    }

    public static void registerModItems(){
        MagicalBeginnings.LOGGER.info("Registering Mod Items for " + MagicalBeginnings.MOD_ID);
    }
}
