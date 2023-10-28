package net.alex.magicalbeginnings.networking;

import net.alex.magicalbeginnings.MagicalBeginnings;
import net.alex.magicalbeginnings.networking.packet.*;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static Identifier USE_MANA = new Identifier(MagicalBeginnings.MOD_ID, "use_mana");
    public static Identifier CURRENT_MANA_SYNC = new Identifier(MagicalBeginnings.MOD_ID, "current_mana_sync");
    public static Identifier MAX_MANA_SYNC = new Identifier(MagicalBeginnings.MOD_ID, "max_mana_sync");
    public static Identifier MANA_REGEN_SYNC = new Identifier(MagicalBeginnings.MOD_ID, "mana_regen_sync");

    public static Identifier MAGIC_EXP_LEVEL_SYNC = new Identifier(MagicalBeginnings.MOD_ID, "magic_exp_level_sync");
    public static Identifier MAGIC_EXP_LEVEL_CURRENT_SYNC = new Identifier(MagicalBeginnings.MOD_ID, "magic_exp_level_current_sync");
    public static Identifier MAGIC_EXP_LEVEL_NEEDED_SYNC = new Identifier(MagicalBeginnings.MOD_ID, "magic_exp_level_needed_sync");
    public static Identifier INCREASE_MAGIC_EXP = new Identifier(MagicalBeginnings.MOD_ID, "increase_magic_exp");

    /**
     * Registers all the packets that go from client to server
     */
    public static void registerC2SPackets(){
        ServerPlayNetworking.registerGlobalReceiver(USE_MANA, UseManaC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(INCREASE_MAGIC_EXP, IncreaseMagicExpC2SPacket::receive);
    }

    /**
     * Registers all the packets that go from server to client
     */
    public static void registerS2CPackets(){
        ClientPlayNetworking.registerGlobalReceiver(CURRENT_MANA_SYNC, CurrentManaSyncDataS2CPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(MAX_MANA_SYNC, MaxManaSyncDataS2CPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(MANA_REGEN_SYNC, ManaRegenSyncDataS2CPacket::receive);

        ClientPlayNetworking.registerGlobalReceiver(MAGIC_EXP_LEVEL_SYNC, MagicExpLevelSyncDataS2CPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(MAGIC_EXP_LEVEL_CURRENT_SYNC, MagicExpLevelCurrentSyncDataS2CPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(MAGIC_EXP_LEVEL_NEEDED_SYNC, MagicExpLevelNeededSyncDataS2CPacket::receive);
    }

}
