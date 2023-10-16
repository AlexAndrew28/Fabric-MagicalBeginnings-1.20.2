package net.alex.magicalbeginnings.networking.packet;

import net.alex.magicalbeginnings.util.IEntityDataSaver;
import net.alex.magicalbeginnings.util.MagicExpData;
import net.alex.magicalbeginnings.util.ManaData;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class IncreaseMagicExpC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender){
        // happens only on the server
        int amount = buf.readInt();

        MagicExpData.increaseMagicExp(((IEntityDataSaver) player), amount);

        MagicExpData.syncMagicExpCurrent(((IEntityDataSaver) player).
                getPersistentData().getInt("magicLevelCurrent"), player);

        MagicExpData.syncMagicExpLevel(((IEntityDataSaver) player).
                getPersistentData().getInt("magicLevel"), player);

        MagicExpData.syncMagicExpNeeded(((IEntityDataSaver) player).
                getPersistentData().getInt("magicLevelNeeded"), player);
    }
}
