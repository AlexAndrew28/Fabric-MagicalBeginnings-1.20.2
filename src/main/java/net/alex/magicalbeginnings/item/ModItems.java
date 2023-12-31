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
    public static Item EARTH_AMULET = registerItem("earth_amulet", new EarthAmuletItem(new FabricItemSettings().maxCount(1)));
    public static Item SKY_AMULET = registerItem("sky_amulet", new SkyAmuletItem(new FabricItemSettings().maxCount(1)));
    public static Item EARTH_WAND = registerItem("earth_wand", new EarthWandItem(new FabricItemSettings().maxCount(1)));
    public static Item MARKING_WAND = registerItem("marking_wand", new MarkingWandItem(new FabricItemSettings().maxCount(1)));
    public static Item TELEPORTING_AMULET = registerItem("teleporting_amulet", new TeleportingAmuletItem(new FabricItemSettings().maxCount(1)));
    public static Item TELEPORTING_WAND = registerItem("teleporting_wand", new TeleportingWandItem(new FabricItemSettings().maxCount(1)));


    public static Item EMPTY_FOCUS = registerItem("empty_focus", new Item(new FabricItemSettings()));
    public static Item AIR_FOCUS = registerItem("air_focus", new Item(new FabricItemSettings()));
    public static Item MAGIC_FOCUS = registerItem("magic_focus", new Item(new FabricItemSettings()));
    public static Item NATURE_FOCUS = registerItem("nature_focus", new Item(new FabricItemSettings()));
    public static Item NECROTIC_FOCUS = registerItem("necrotic_focus", new Item(new FabricItemSettings()));
    public static Item WATER_FOCUS = registerItem("water_focus", new Item(new FabricItemSettings()));
    public static Item CORRUPTION_FOCUS = registerItem("corruption_focus", new Item(new FabricItemSettings()));
    public static Item FIRE_FOCUS = registerItem("fire_focus", new Item(new FabricItemSettings()));
    public static Item FOCUS_SOCKET = registerItem("focus_socket", new Item(new FabricItemSettings()));
    public static Item AIR_ESSENCE = registerItem("air_essence", new Item(new FabricItemSettings()));
    public static Item MAGIC_ESSENCE = registerItem("magic_essence", new Item(new FabricItemSettings()));
    public static Item NATURE_ESSENCE = registerItem("nature_essence", new Item(new FabricItemSettings()));
    public static Item NECROTIC_ESSENCE = registerItem("necrotic_essence", new Item(new FabricItemSettings()));
    public static Item WATER_ESSENCE = registerItem("water_essence", new Item(new FabricItemSettings()));
    public static Item CORRUPTION_ESSENCE = registerItem("corruption_essence", new Item(new FabricItemSettings()));
    public static Item FIRE_ESSENCE = registerItem("fire_essence", new Item(new FabricItemSettings()));

    /**
     * Registers a new item
     *
     * @param name the name of the item
     * @param item the item
     * @return a registered item
     */
    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(MagicalBeginnings.MOD_ID, name), item);
    }

    /**
     * Logs the fact that items are being registered
     *
     */
    public static void registerModItems(){
        MagicalBeginnings.LOGGER.info("Registering Mod Items for " + MagicalBeginnings.MOD_ID);
    }
}
