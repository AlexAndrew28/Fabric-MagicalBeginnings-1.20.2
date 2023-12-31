package net.alex.magicalbeginnings.entity;

import net.alex.magicalbeginnings.MagicalBeginnings;
import net.alex.magicalbeginnings.entity.custom.*;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {

    // register fireball entity
    public static final EntityType<FireballEntity> FIREBALL = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(MagicalBeginnings.MOD_ID,"fireball"),
            FabricEntityTypeBuilder.<FireballEntity>create(SpawnGroup.MISC, FireballEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5F, 0.5F))
                    .build());

    // register waterball entity
    public static final EntityType<WaterballEntity> WATERBALL = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(MagicalBeginnings.MOD_ID,"waterball"),
            FabricEntityTypeBuilder.<WaterballEntity>create(SpawnGroup.MISC, WaterballEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5F, 0.5F))
                    .build());

    // register proj dig entity
    public static final EntityType<DigProjectileEntity> DIG_PROJECTILE = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(MagicalBeginnings.MOD_ID,"dig_projectile"),
            FabricEntityTypeBuilder.<DigProjectileEntity>create(SpawnGroup.MISC, DigProjectileEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5F, 0.5F))
                    .build());


    public static final EntityType<TeleportOrbEntity> TELEPORT_ORB = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(MagicalBeginnings.MOD_ID,"teleport_orb"),
            FabricEntityTypeBuilder.<TeleportOrbEntity>create(SpawnGroup.MISC, TeleportOrbEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5F, 0.5F))
                    .build());

    public static final EntityType<LocationMarkOrbEntity> LOCATION_MARK_ORB = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(MagicalBeginnings.MOD_ID,"location_mark_orb"),
            FabricEntityTypeBuilder.<LocationMarkOrbEntity>create(SpawnGroup.MISC, LocationMarkOrbEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5F, 0.5F))
                    .build());
}
