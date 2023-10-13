package net.alex.magicalbeginnings.networking.packet;

import net.alex.magicalbeginnings.util.IEntityDataSaver;
import net.alex.magicalbeginnings.util.ManaData;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class UseManaC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender){
        // happens only on the server
        int amount = buf.readInt();

        ManaData.removeMana(((IEntityDataSaver) player), amount);

        ManaData.syncMaxMana(((IEntityDataSaver) player).getPersistentData().getInt("maxMana"), player);
        ManaData.syncManaRegen(((IEntityDataSaver) player).getPersistentData().getInt("manaRegen"), player);

    }
}
