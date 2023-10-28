package net.alex.magicalbeginnings.util;

import net.alex.magicalbeginnings.networking.ModMessages;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class MagicExpData {

    private static final int bonusMaxManaPerLevel = 100;
    private static final int bonusManaRegenPerLevel = 1;

    /**
     * Resets the players magic exp
     *
     * @param player the player to reset the exp of
     */
    public static void resetMagicExp(IEntityDataSaver player){
        NbtCompound nbt = player.getPersistentData();
        nbt.putInt("magicLevel", 0);
        nbt.putInt("magicLevelCurrent", 0);
        nbt.putInt("magicLevelNeeded", 0);

        // since the values change they need to be synced
        syncMagicExpLevel(0, (ServerPlayerEntity) player);
        syncMagicExpCurrent(0, (ServerPlayerEntity) player);
        syncMagicExpNeeded(0, (ServerPlayerEntity) player);
    }

    /**
     * Increase the magic exp by some amount
     *
     * @param player the player to make this change to
     * @param amount the amount of magic exp to gain
     */
    public static void increaseMagicExp(IEntityDataSaver player, int amount){
        NbtCompound nbt = player.getPersistentData();
        int magicLevel = nbt.getInt("magicLevel");
        int magicLevelCurrent = nbt.getInt("magicLevelCurrent");
        int magicLevelNeeded = nbt.getInt("magicLevelNeeded");

        // check if this is an initialise call
        if(magicLevel == 0 && amount == 0){
            nbt.putInt("magicLevel", 1);
            nbt.putInt("magicLevelCurrent", 0);
            nbt.putInt("magicLevelNeeded", 100);
            syncMagicExpLevel(1, (ServerPlayerEntity) player);
            syncMagicExpNeeded(100, (ServerPlayerEntity) player);
            syncMagicExpCurrent(0, (ServerPlayerEntity) player);
        }
        else{
            // increase magic exp
            magicLevelCurrent += amount;

            boolean changed = false;

            // check if the level increases
            while (magicLevelCurrent >= magicLevelNeeded){
                magicLevelCurrent -= magicLevelNeeded;
                magicLevelNeeded = (int) ((magicLevelNeeded + 500)*1.001);
                magicLevel += 1;
                changed = true;

                // for each level gained increase the players max mana and mana regen
                ManaData.addMaxMana(player, bonusMaxManaPerLevel);
                ManaData.addManaRegen(player, bonusManaRegenPerLevel);
            }

            nbt.putInt("magicLevel", magicLevel);
            nbt.putInt("magicLevelCurrent", magicLevelCurrent);
            nbt.putInt("magicLevelNeeded", magicLevelNeeded);

            // sync the values that changed
            if (changed){
                syncMagicExpLevel(magicLevel, (ServerPlayerEntity) player);
                syncMagicExpNeeded(magicLevelNeeded, (ServerPlayerEntity) player);
            }
            syncMagicExpCurrent(magicLevelCurrent,
                    (ServerPlayerEntity) player);
        }


    }

    /**
     * Sync the magic exp level
     *
     * @param magicLevel the new magic level
     * @param player the player to update the values of
     */
    public static void syncMagicExpLevel(int magicLevel, ServerPlayerEntity player) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(magicLevel);
        ServerPlayNetworking.send(player, ModMessages.MAGIC_EXP_LEVEL_SYNC, buffer);
    }
    /**
     * Sync the current magic exp
     *
     * @param magicLevelCurrent the new current magic exp
     * @param player the player to update the values of
     */
    public static void syncMagicExpCurrent(int magicLevelCurrent, ServerPlayerEntity player) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(magicLevelCurrent);
        ServerPlayNetworking.send(player, ModMessages.MAGIC_EXP_LEVEL_CURRENT_SYNC, buffer);
    }
    /**
     * Sync the magic exp needed
     *
     * @param magicLevelNeeded the new magic exp needed
     * @param player the player to update the values of
     */
    public static void syncMagicExpNeeded(int magicLevelNeeded, ServerPlayerEntity player) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(magicLevelNeeded);
        ServerPlayNetworking.send(player, ModMessages.MAGIC_EXP_LEVEL_NEEDED_SYNC, buffer);
    }
}
