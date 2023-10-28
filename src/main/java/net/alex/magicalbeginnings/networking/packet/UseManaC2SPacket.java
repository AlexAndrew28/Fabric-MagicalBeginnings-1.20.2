package net.alex.magicalbeginnings.networking.packet;

import net.alex.magicalbeginnings.util.IEntityDataSaver;
import net.alex.magicalbeginnings.util.ManaData;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
/**
 * A packet that uses mana
 * Goes from client to server
 */
public class UseManaC2SPacket {
    /**
     * Removes an amount of mana from the player as well as syncing max mana and mana regen
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
        int amount = buf.readInt();

        // removes mana
        ManaData.removeMana(((IEntityDataSaver) player), amount);

        // syncs max mana and mana regen
        ManaData.syncMaxMana(((IEntityDataSaver) player).getPersistentData().getInt("maxMana"), player);
        ManaData.syncManaRegen(((IEntityDataSaver) player).getPersistentData().getInt("manaRegen"), player);

    }
}
