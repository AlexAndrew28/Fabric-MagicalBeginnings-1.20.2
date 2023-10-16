package net.alex.magicalbeginnings.block.entity;

import net.alex.magicalbeginnings.MagicalBeginnings;
import net.alex.magicalbeginnings.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<RyftImbuingStationBlockEntity> RYFT_IMBUING_STATION_BLOCK_ENTITY_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(MagicalBeginnings.MOD_ID, "ryft_imbuing_be"),
                    FabricBlockEntityTypeBuilder.create(RyftImbuingStationBlockEntity::new,
                            ModBlocks.RYFT_IMBUING_STATION).build());

    public static void registerBlockEntities(){
        MagicalBeginnings.LOGGER.info("Registering Block Entities for " + MagicalBeginnings.MOD_ID);
    }
}
