package net.alex.magicalbeginnings.networking.packet;

import net.alex.magicalbeginnings.util.IEntityDataSaver;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
/**
 * A packet that syncs the magic exp required for the next level
 * Goes from server to client
 */
public class MagicExpLevelNeededSyncDataS2CPacket {
    /**
     * Update the saved value for the magic exp required
     *
     * @param client the client
     * @param handler a network handler
     * @param buf the packet byte buf that stores data
     * @param responseSender a packet sender
     */
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender){
        if(client.player != null) {
            ((IEntityDataSaver) client.player).getPersistentData().putInt("magicLevelNeeded", buf.readInt());
        }
    }
}
