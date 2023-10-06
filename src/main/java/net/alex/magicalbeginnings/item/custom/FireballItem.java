package net.alex.magicalbeginnings.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FireballItem extends Item {
    public FireballItem(Settings settings) {
        super(settings);
    }

    public SpellPersistentProjectileEntity createSpell(World world, LivingEntity shooter) {
        return new SpellEntity(world, shooter);
    }
}
