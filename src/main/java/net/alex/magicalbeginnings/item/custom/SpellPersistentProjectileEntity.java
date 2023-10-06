package net.alex.magicalbeginnings.item.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SpellPersistentProjectileEntity extends PersistentProjectileEntity {
    protected SpellPersistentProjectileEntity(EntityType<? extends PersistentProjectileEntity> type, LivingEntity owner, World world) {
        super(type, owner, world);
    }

    @Override
    protected ItemStack asItemStack() {
        return null;
    }
}
