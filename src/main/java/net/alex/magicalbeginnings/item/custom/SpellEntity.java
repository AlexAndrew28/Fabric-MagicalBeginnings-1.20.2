package net.alex.magicalbeginnings.item.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.world.World;

public class SpellEntity extends SpellPersistentProjectileEntity{

    public SpellEntity(World world, LivingEntity owner) {
        super(EntityType.ARROW, owner, world);
    }


}
