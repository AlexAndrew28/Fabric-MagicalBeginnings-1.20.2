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

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack itemStack = super.finishUsing(stack, world, user);

        if (!world.isClient) {
            IEntityDataSaver dataPlayer = ((IEntityDataSaver) user);
            ManaData.addMaxMana(dataPlayer, 0);
            ManaData.addManaRegen(dataPlayer, 0);
            MagicExpData.increaseMagicExp(dataPlayer, 0);
        }

        return itemStack;
    }

}
