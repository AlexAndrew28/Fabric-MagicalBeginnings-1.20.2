package net.alex.magicalbeginnings.item.custom;

import net.alex.magicalbeginnings.util.IEntityDataSaver;
import net.alex.magicalbeginnings.util.MagicExpData;
import net.alex.magicalbeginnings.util.ManaData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.entity.player.PlayerEntity;

public class MagicalStewItem extends Item {

    public MagicalStewItem(Settings settings) {
        super(settings);
    }

    /**
     * When the item has been eaten this will be called
     * Effect: initialises both mana and magic exp to lv1
     *
     * @param stack the item
     * @param world the world
     * @param user the player who used the item
     * @return this item
     */
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack itemStack = super.finishUsing(stack, world, user);

        if (!world.isClient) {
            IEntityDataSaver dataPlayer = ((IEntityDataSaver) user);

            // setting the amount to 0 is a key for initialise for the first time
            // if you were to use this item multiple times it would do nothing after the first use
            ManaData.addMaxMana(dataPlayer, 0);
            ManaData.addManaRegen(dataPlayer, 0);
            MagicExpData.increaseMagicExp(dataPlayer, 0);
        }

        return itemStack;
    }

}
