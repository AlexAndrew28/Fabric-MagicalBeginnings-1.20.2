package net.alex.magicalbeginnings.util;

import net.alex.magicalbeginnings.networking.ModMessages;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;

public class TeleportationData {


    /**
     * Set the players teleportation coordinates
     *
     * @param player the player to effect
     */
    public static void updateCoordinateX(IEntityDataSaver player, double x){
        NbtCompound nbt = player.getPersistentData();

        nbt.putDouble("teleportationCoordinateX", x);

        syncCoordinateX(x, (ServerPlayerEntity) player);
    }

    public static void updateCoordinateY(IEntityDataSaver player, double y){
        NbtCompound nbt = player.getPersistentData();

        nbt.putDouble("teleportationCoordinateY", y);

        syncCoordinateY(y, (ServerPlayerEntity) player);
    }
    public static void updateCoordinateZ(IEntityDataSaver player, double z){
        NbtCompound nbt = player.getPersistentData();

        nbt.putDouble("teleportationCoordinateZ", z);

        syncCoordinateZ(z, (ServerPlayerEntity) player);
    }

    public static void syncCoordinateX(double x, ServerPlayerEntity player){
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeDouble(x);
        ServerPlayNetworking.send(player, ModMessages.TELEPORTATION_COORDINATE_X_SYNC, buffer);
    }

    public static void syncCoordinateY(double y, ServerPlayerEntity player){
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeDouble(y);
        ServerPlayNetworking.send(player, ModMessages.TELEPORTATION_COORDINATE_Y_SYNC, buffer);
    }

    public static void syncCoordinateZ(double z, ServerPlayerEntity player){
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeDouble(z);
        ServerPlayNetworking.send(player, ModMessages.TELEPORTATION_COORDINATE_Z_SYNC, buffer);
    }



}
