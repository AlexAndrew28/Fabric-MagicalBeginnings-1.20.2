package net.alex.magicalbeginnings.networking.packet;

import net.alex.magicalbeginnings.util.IEntityDataSaver;
import net.alex.magicalbeginnings.util.TeleportationData;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

/**
 * A packet that updates the teleport coordinates
 * Goes from client to server
 */
public class UpdateTeleportCoordinateZC2SPacket {
    /**
     * Update the coordinates for the player teleport
     *
     * @param server the server
     * @param player the player
     * @param handler network handler
     * @param buf the buf that stores data
     * @param responseSender the packet sender
     */
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender){
        // happens only on the server
        Double coordinateZ = buf.readDouble();

        TeleportationData.updateCoordinateZ((IEntityDataSaver) player, coordinateZ);

        TeleportationData.syncCoordinateZ(((IEntityDataSaver) player).getPersistentData().getDouble("teleportationCoordinateZ"), player);
    }
}
