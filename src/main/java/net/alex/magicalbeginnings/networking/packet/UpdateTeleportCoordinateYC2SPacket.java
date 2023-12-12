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
public class UpdateTeleportCoordinateYC2SPacket {
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
        Double coordinateY = buf.readDouble();

        TeleportationData.updateCoordinateY((IEntityDataSaver) player, coordinateY);

        TeleportationData.syncCoordinateY(((IEntityDataSaver) player).getPersistentData().getDouble("teleportationCoordinateY"), player);
    }
}
