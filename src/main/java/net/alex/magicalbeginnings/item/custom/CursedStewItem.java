package net.alex.magicalbeginnings.item.custom;

import net.alex.magicalbeginnings.util.IEntityDataSaver;
import net.alex.magicalbeginnings.util.MagicExpData;
import net.alex.magicalbeginnings.util.ManaData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CursedStewItem extends Item {

    public CursedStewItem(Settings settings) {
        super(settings);
    }

    /**
     * Called when the item has been eaten
     * Effect: reset all mana stats
     *
     * @param stack the item
     * @param world the world
     * @param user the player who ate
     *
     * @return the item
     */
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack itemStack = super.finishUsing(stack, world, user);

        if (!world.isClient) {
            // happens on the server
            IEntityDataSaver dataPlayer = ((IEntityDataSaver) user);

            // reset both mana and magic exp
            ManaData.resetMana(dataPlayer);
            MagicExpData.resetMagicExp(dataPlayer);
        }

        return itemStack;
    }

}
