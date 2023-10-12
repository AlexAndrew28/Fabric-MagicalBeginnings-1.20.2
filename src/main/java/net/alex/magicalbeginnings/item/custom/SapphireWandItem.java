package net.alex.magicalbeginnings.item.custom;

import net.alex.magicalbeginnings.entity.custom.FireballEntity;
import net.alex.magicalbeginnings.entity.custom.WaterballEntity;
import net.alex.magicalbeginnings.item.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SapphireWandItem extends Item {


    public SapphireWandItem(Settings settings) {
        super(settings);
    }


    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack wand = new ItemStack(ModItems.SAPPHIRE_WAND);


        user.getItemCooldownManager().set(this, 2);
        if (!world.isClient) {

            WaterballEntity persistentProjectileEntity = new WaterballEntity(world, user);
            persistentProjectileEntity.setVelocity(user, user.getPitch(), user.getYaw(), 1.5f, 1.5f, 0f);

            world.spawnEntity(persistentProjectileEntity);
        }

        return TypedActionResult.success(wand);
    }

}
