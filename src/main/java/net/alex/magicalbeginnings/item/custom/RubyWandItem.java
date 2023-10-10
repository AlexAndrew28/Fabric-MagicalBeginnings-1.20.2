package net.alex.magicalbeginnings.item.custom;

import net.alex.magicalbeginnings.entity.custom.FireballEntity;
import net.alex.magicalbeginnings.item.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class RubyWandItem extends Item {


    public RubyWandItem(Settings settings) {
        super(settings);
    }


    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack wand = new ItemStack(ModItems.RUBY_WAND);


        user.getItemCooldownManager().set(this, 2);
        if (!world.isClient) {

            FireballEntity persistentProjectileEntity = new FireballEntity(world, user);
            persistentProjectileEntity.setVelocity(user, user.getPitch(), user.getYaw(), 1.5f, 1.5f, 0f);

            world.spawnEntity(persistentProjectileEntity);
        }

        return TypedActionResult.success(wand);
    }

}
