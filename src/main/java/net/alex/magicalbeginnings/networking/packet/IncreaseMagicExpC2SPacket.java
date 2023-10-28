package net.alex.magicalbeginnings.networking.packet;

import net.alex.magicalbeginnings.util.IEntityDataSaver;
import net.alex.magicalbeginnings.util.MagicExpData;
import net.alex.magicalbeginnings.util.ManaData;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
/**
 * A packet that increases the magic exp
 * Goes from client to server
 */
public class IncreaseMagicExpC2SPacket {

    /**
     * Update the magic exp and sync: current magic exp, current magic exp level, magic exp needed for next leve
     *
     * @param server the server
     * @param player the player
     * @param handler a network handler
     * @param buf the packet buf that stores data
     * @param responseSender the packet sender
     */
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender){
        // happens only on the server
        int amount = buf.readInt();

        // increase the magic exp by an amount
        MagicExpData.increaseMagicExp(((IEntityDataSaver) player), amount);


        // sync all of the values
        MagicExpData.syncMagicExpCurrent(((IEntityDataSaver) player).
                getPersistentData().getInt("magicLevelCurrent"), player);

        MagicExpData.syncMagicExpLevel(((IEntityDataSaver) player).
                getPersistentData().getInt("magicLevel"), player);

        MagicExpData.syncMagicExpNeeded(((IEntityDataSaver) player).
                getPersistentData().getInt("magicLevelNeeded"), player);
    }
}
