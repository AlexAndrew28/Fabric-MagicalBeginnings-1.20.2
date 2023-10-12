package net.alex.magicalbeginnings.entity.custom;

import net.alex.magicalbeginnings.entity.ModEntities;
import net.fabricmc.loader.impl.lib.sat4j.core.Vec;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class WaterballEntity extends PersistentProjectileEntity {

    public WaterballEntity(World world, LivingEntity owner) {
        super(ModEntities.WATERBALL, owner, world);

        double damage = 3;
        super.setDamage(damage);

        setNoGravity(true);
    }

    public WaterballEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected ItemStack asItemStack() {
        return null;
    }

    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        this.discard();
    }

    protected float getDragInWater() {
        //this.kill();
        return 1.0f;
    }

    public void tick() {
        super.tick();
        if (this.getWorld().isClient) {
            this.spawnParticles(2);
        }

        Vec3d vec3d = this.getVelocity();
        if (Math.abs(vec3d.x) < 0.2){
            if (Math.abs(vec3d.y) < 0.2){
                if (Math.abs(vec3d.z) < 0.2){
                    this.kill();
                }
            }
        }


    }

    private void spawnParticles(int amount) {
        double d = 230;
        double e = 116;
        double f = 30;
        for (int j = 0; j < amount; ++j) {
            this.getWorld().addParticle(ParticleTypes.BUBBLE, this.getParticleX(0.5), this.getRandomBodyY(), this.getParticleZ(0.5), d, e, f);
        }
    }

    @Override
    protected void onHit(LivingEntity target) {
        Vec3d vel = this.getVelocity();
        double x = -vel.x;
        double z = -vel.z;
        super.onHit(target);
        target.takeKnockback(0.5, x, z);
        //target.setOnFireFor(1);
        //world.createExplosion(target, target.getX(), target.getY(), target.getZ(), 0.5f, World.ExplosionSourceType.NONE);
    }
}

