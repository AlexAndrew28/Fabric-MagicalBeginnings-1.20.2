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

    public FireballEntity(World world, LivingEntity owner) {
        super(ModEntities.FIREBALL, owner, world);

        // base damage of the fireball
        double damage = 3;
        super.setDamage(damage);

        // spells don't have gravity
        setNoGravity(true);
    }

    public FireballEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected ItemStack asItemStack() {
        return null;
    }

    /**
     * Called when the projectile hits a block
     *
     * @param blockHitResult data about the block hit
     */
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        this.discard();
    }

    /**
     * get the drag coefficient for projectile as it travels through water
     *
     * @return drag coefficient
     */
    protected float getDragInWater() {
        // fire cannot go through water
        return 0.0f;
    }

    /**
     * called every tick to update position of projectile
     */
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

    /**
     * spawns particles around the projectile as it flies for effect
     *
     * @param amount amount of particles to spawn
     */
    private void spawnParticles(int amount) {
        double d = 230;
        double e = 116;
        double f = 30;
        for (int j = 0; j < amount; ++j) {
            this.getWorld().addParticle(ParticleTypes.FLAME, this.getParticleX(0.5), this.getRandomBodyY(), this.getParticleZ(0.5), d, e, f);
        }
    }

    /**
     * Behaviour when the projectile hits an entity (sets target on fire)
     *
     * @param target the entity that got hit
     */
    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);
        target.setOnFireFor(1);
        //world.createExplosion(target, target.getX(), target.getY(), target.getZ(), 0.5f, World.ExplosionSourceType.NONE);
    }
}

