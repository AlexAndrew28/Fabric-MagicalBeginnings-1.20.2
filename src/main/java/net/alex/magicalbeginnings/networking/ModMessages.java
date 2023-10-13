package net.alex.magicalbeginnings.networking;

import net.alex.magicalbeginnings.MagicalBeginnings;
import net.alex.magicalbeginnings.networking.packet.CurrentManaSyncDataS2CPacket;
import net.alex.magicalbeginnings.networking.packet.ManaRegenSyncDataS2CPacket;
import net.alex.magicalbeginnings.networking.packet.MaxManaSyncDataS2CPacket;
import net.alex.magicalbeginnings.networking.packet.UseManaC2SPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static Identifier USE_MANA = new Identifier(MagicalBeginnings.MOD_ID, "use_mana");
    public static Identifier CURRENT_MANA_SYNC = new Identifier(MagicalBeginnings.MOD_ID, "current_mana_sync");
    public static Identifier MAX_MANA_SYNC = new Identifier(MagicalBeginnings.MOD_ID, "max_mana_sync");
    public static Identifier MANA_REGEN_SYNC = new Identifier(MagicalBeginnings.MOD_ID, "mana_regen_sync");


    public static void registerC2SPackets(){
        ServerPlayNetworking.registerGlobalReceiver(USE_MANA, UseManaC2SPacket::receive);
    }

    public static void registerS2CPackets(){
        ClientPlayNetworking.registerGlobalReceiver(CURRENT_MANA_SYNC, CurrentManaSyncDataS2CPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(MAX_MANA_SYNC, MaxManaSyncDataS2CPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(MANA_REGEN_SYNC, ManaRegenSyncDataS2CPacket::receive);

    }

}
