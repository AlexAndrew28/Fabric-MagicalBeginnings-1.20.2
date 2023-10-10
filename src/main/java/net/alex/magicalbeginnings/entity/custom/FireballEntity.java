package net.alex.magicalbeginnings.entity.custom;

import net.alex.magicalbeginnings.MagicalBeginnings;
import net.alex.magicalbeginnings.entity.ModEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.potion.Potions;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class FireballEntity extends PersistentProjectileEntity {

    private World world;
    public FireballEntity(World world, LivingEntity owner) {
        super(ModEntities.FIREBALL, owner, world);

        double damage = 3;
        this.world = world;
        super.setDamage(damage);

        setNoGravity(true);
    }

    public FireballEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
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
            this.spawnParticles(1);
        }

        Vec3d vec3d = this.getVelocity();
        if (Math.abs(vec3d.x + vec3d.y + vec3d.z) < 0.3){
            this.kill();
        }



    }

    private void spawnParticles(int amount) {
        double d = 230;
        double e = 116;
        double f = 30;
        for (int j = 0; j < amount; ++j) {
            this.getWorld().addParticle(ParticleTypes.FLAME, this.getParticleX(0.5), this.getRandomBodyY(), this.getParticleZ(0.5), d, e, f);
        }
    }

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);
        //world.createExplosion(target, target.getX(), target.getY(), target.getZ(), 0.5f, World.ExplosionSourceType.NONE);
    }
}

