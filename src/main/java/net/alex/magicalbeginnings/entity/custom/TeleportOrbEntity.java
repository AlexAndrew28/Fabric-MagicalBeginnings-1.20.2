package net.alex.magicalbeginnings.entity.custom;

import net.alex.magicalbeginnings.entity.ModEntities;
import net.alex.magicalbeginnings.util.IEntityDataSaver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TeleportOrbEntity extends PersistentProjectileEntity {

    public TeleportOrbEntity(World world, LivingEntity owner) {
        super(ModEntities.TELEPORT_ORB, owner, world);

        // base damage of the spell
        double damage = 0;
        super.setDamage(damage);

        // spells don't have gravity
        setNoGravity(true);
    }

    public TeleportOrbEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
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
        // spell can go through water
        return 1.0f;
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
            this.getWorld().addParticle(ParticleTypes.GLOW, this.getParticleX(0.5), this.getRandomBodyY(), this.getParticleZ(0.5), d, e, f);
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
        double xCoordinate = ((IEntityDataSaver) MinecraftClient.getInstance().player).getPersistentData().getDouble("teleportationCoordinateX");
        double yCoordinate = ((IEntityDataSaver) MinecraftClient.getInstance().player).getPersistentData().getDouble("teleportationCoordinateY");
        double zCoordinate = ((IEntityDataSaver) MinecraftClient.getInstance().player).getPersistentData().getDouble("teleportationCoordinateZ");

        target.teleport(xCoordinate, yCoordinate, zCoordinate);
    }
}

