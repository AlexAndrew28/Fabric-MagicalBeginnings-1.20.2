package net.alex.magicalbeginnings.item.custom;

import net.alex.magicalbeginnings.item.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class WandItem extends Item {


    public WandItem(Settings settings) {
        super(settings);
    }


    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack wand = new ItemStack(ModItems.RUBY_WAND);

        ItemStack itemStack = new ItemStack(ModItems.FIREBALL);

        user.getItemCooldownManager().set(this, 20);
        if (!world.isClient) {

            FireballItem spellItem =  (FireballItem) ModItems.FIREBALL;
            SpellPersistentProjectileEntity persistentProjectileEntity = spellItem.createSpell(world, user);
            persistentProjectileEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, 3.0f, 1.0f);

            world.spawnEntity(persistentProjectileEntity);
        }

        return TypedActionResult.success(wand);
    }

}
