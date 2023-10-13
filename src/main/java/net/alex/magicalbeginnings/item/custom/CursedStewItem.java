package net.alex.magicalbeginnings.item.custom;

import net.alex.magicalbeginnings.util.IEntityDataSaver;
import net.alex.magicalbeginnings.util.ManaData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CursedStewItem extends Item {

    public CursedStewItem(Settings settings) {
        super(settings);
    }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack itemStack = super.finishUsing(stack, world, user);

        if (!world.isClient) {
            IEntityDataSaver dataPlayer = ((IEntityDataSaver) user);
            ManaData.resetMana(dataPlayer);
        }

        return itemStack;
    }

}
