package net.alex.magicalbeginnings.entity;

import net.alex.magicalbeginnings.MagicalBeginnings;
import net.alex.magicalbeginnings.entity.custom.FireballEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    /*

    public static final EntityType<FireballEntity> FIREBALL = Registry.register(Registries.ENTITY_TYPE, new Identifier(MagicalBeginnings.MOD_ID, "fireball"), FabricEntityTypeBuilder.create(SpawnGroup.MISC, FireballEntity::new));

    public static final EntityType<FireballEntity> FIREBALL = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(MagicalBeginnings.MOD_ID, "fireball"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, FireballEntity::new)
                    .dimensions(EntityDimensions.fixed(1f,1f)).build());

     */
    public static final EntityType<FireballEntity> FIREBALL = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(MagicalBeginnings.MOD_ID,"fireball"),
            FabricEntityTypeBuilder.<FireballEntity>create(SpawnGroup.MISC, FireballEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F)) // dimensions in Minecraft units of the projectile
                    //.trackRangeBlocks(4).trackedUpdateRate(10) // necessary for all thrown projectiles (as it prevents it from breaking, lol)
                    .build()); // VERY IMPORTANT DONT DELETE FOR THE LOVE OF GOD PSLSSSSSS
}
