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

        setNoGravity(true);
    }

    public DigProjectileEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected ItemStack asItemStack() {
        return null;
    }

    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);

        if (this.getWorld().isClient) {
            return;
        }

        this.discard();
        BlockPos blockPos = blockHitResult.getBlockPos();

        float blockHardness = this.getWorld().getBlockState(blockPos).getHardness(this.getWorld(), blockPos);

        // cannot mine bedrock (hardness -1) and obsidian+ (hardness 50)
        if(0 < blockHardness && blockHardness < 49){
            this.getWorld().breakBlock(blockPos, true);
        }
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
            this.getWorld().addParticle(ParticleTypes.SNEEZE, this.getParticleX(0.5), this.getRandomBodyY(), this.getParticleZ(0.5), d, e, f);
        }
    }

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);
    }

    @Override
    protected boolean canHit(Entity entity) {
        return false;
    }
}

