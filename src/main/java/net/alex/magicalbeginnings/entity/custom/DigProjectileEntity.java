package net.alex.magicalbeginnings.entity.custom;

import net.alex.magicalbeginnings.entity.ModEntities;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class DigProjectileEntity extends PersistentProjectileEntity {

    public DigProjectileEntity(World world, LivingEntity owner) {
        super(ModEntities.DIG_PROJECTILE, owner, world);

        // base damage of the spell
        double damage = 0;
        super.setDamage(damage);

        // this projectile not affected by gravity
        setNoGravity(true);
    }

    public DigProjectileEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
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

        if (this.getWorld().isClient) {
            // don't run on client
            return;
        }
        this.discard();

        // check the hardness of the block hit
        BlockPos blockPos = blockHitResult.getBlockPos();
        float blockHardness = this.getWorld().getBlockState(blockPos).getHardness(this.getWorld(), blockPos);

        // cannot mine bedrock (hardness -1) and obsidian+ (hardness 50)
        if(0 < blockHardness && blockHardness < 49){
            this.getWorld().breakBlock(blockPos, true);
        }
    }

    /**
     * get the drag coefficient for projectile as it travels through water
     *
     * @return drag coefficient
     */
    protected float getDragInWater() {
        // spells can go through water same as air
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
            this.getWorld().addParticle(ParticleTypes.SNEEZE, this.getParticleX(0.5), this.getRandomBodyY(), this.getParticleZ(0.5), d, e, f);
        }
    }

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);
    }

    /**
     * Overrides to projectile cannot hit entities
     *
     * @param entity entity to check
     * @return false
     */
    @Override
    protected boolean canHit(Entity entity) {
        return false;
    }
}

