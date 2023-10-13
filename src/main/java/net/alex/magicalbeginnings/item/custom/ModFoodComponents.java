package net.alex.magicalbeginnings.item.custom;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.FoodComponents;

public class ModFoodComponents {
    public static final FoodComponent BERRY = new FoodComponent.Builder().hunger(2).saturationModifier(0.25f).build();

    public static final FoodComponent MAGICAL_STEW = new FoodComponent.Builder().hunger(5).saturationModifier(1.0f).build();
    public static final FoodComponent CURSED_STEW = new FoodComponent.Builder().hunger(0).saturationModifier(0f).build();
}
