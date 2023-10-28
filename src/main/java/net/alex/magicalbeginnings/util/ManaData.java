package net.alex.magicalbeginnings.util;

import net.alex.magicalbeginnings.networking.ModMessages;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class ManaData {

    /**
     * Reset all mana to 0
     *
     * @param player the player to effect
     */
    public static void resetMana(IEntityDataSaver player){
        NbtCompound nbt = player.getPersistentData();
        nbt.putInt("maxMana", 0);
        nbt.putInt("currentMana", 0);
        nbt.putInt("manaRegen", 0);

        // sync the new changes
        syncCurrentMana(0, (ServerPlayerEntity) player);
        syncManaRegen(0, (ServerPlayerEntity) player);
        syncMaxMana(0, (ServerPlayerEntity) player);
    }

    /**
     * Increase the max mana
     *
     * @param player the player to effect
     * @param amount the amount to increase the max mana by
     */
    public static void addMaxMana(IEntityDataSaver player, int amount){
        NbtCompound nbt = player.getPersistentData();
        int maxMana = nbt.getInt("maxMana");

        // check if initialise call
        if (maxMana == 0 && amount == 0){
            maxMana = 100;
        }else{
            maxMana += amount;
        }

        nbt.putInt("maxMana", maxMana);

        syncMaxMana(maxMana, (ServerPlayerEntity) player);
    }

    /**
     * Increase the mana regen
     *
     * @param player the player to effect
     * @param amount the amount to increase the regen by
     */
    public static void addManaRegen(IEntityDataSaver player, int amount){
        NbtCompound nbt = player.getPersistentData();
        int manaRegen = nbt.getInt("manaRegen");

        // check if initialise call
        if (manaRegen == 0 && amount == 0){
            manaRegen = 1;
        }
        else{
            manaRegen += amount;
        }
        nbt.putInt("manaRegen", manaRegen);

        syncManaRegen(manaRegen, (ServerPlayerEntity) player);
    }


    /**
     * Increase the current mana
     *
     * @param player the player to effect
     * @param amount the amount to increase the current mana by
     */
    public static void addMana(IEntityDataSaver player, int amount){
        NbtCompound nbt = player.getPersistentData();

        int currentMana = nbt.getInt("currentMana");
        int maxMana = nbt.getInt("maxMana");

        currentMana += amount;

        // don't increase mana past max
        if (currentMana > maxMana){
            currentMana = maxMana;
        }

        nbt.putInt("currentMana", currentMana);

        syncCurrentMana(currentMana, (ServerPlayerEntity) player);
    }

    /**
     * Remove mana from the player
     *
     * @param player the player to remove mana from
     * @param amount the amount of mana to remove
     */
    public static void removeMana(IEntityDataSaver player, int amount){
        NbtCompound nbt = player.getPersistentData();

        int currentMana = nbt.getInt("currentMana");

        currentMana -= amount;

        // mana cannot go below 0
        if (currentMana < 0){
            currentMana = 0;
        }

        nbt.putInt("currentMana", currentMana);

        syncCurrentMana(currentMana, (ServerPlayerEntity) player);
    }


    /**
     * Regen the mana of the player
     *
     * @param player the player to regen the mana of
     */
    public static void regenMana(IEntityDataSaver player){
        NbtCompound nbt = player.getPersistentData();

        int currentMana = nbt.getInt("currentMana");
        int maxMana = nbt.getInt("maxMana");
        //int maxMana = 1000;
        int manaRegen = nbt.getInt("manaRegen");
        //int manaRegen = 1;

        currentMana += manaRegen;

        // mana cannot go above max
        if (currentMana > maxMana){
            currentMana = maxMana;
        }

        nbt.putInt("currentMana", currentMana);
        syncCurrentMana(currentMana, (ServerPlayerEntity) player);
    }

    /**
     * Sync the current mana
     *
     * @param currentMana the current mana of the player
     * @param player the player to sync the mana of
     */
    public static void syncCurrentMana(int currentMana, ServerPlayerEntity player){
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(currentMana);
        ServerPlayNetworking.send(player, ModMessages.CURRENT_MANA_SYNC, buffer);
    }
    /**
     * Sync the max mana
     *
     * @param maxMana the max mana of the player
     * @param player the player to sync the max mana of
     */
    public static void syncMaxMana(int maxMana, ServerPlayerEntity player){
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(maxMana);
        ServerPlayNetworking.send(player, ModMessages.MAX_MANA_SYNC, buffer);
    }
    /**
     * Sync the mana regen
     *
     * @param manaRegen the mana regen of the player
     * @param player the player to sync the mana regen of
     */
    public static void syncManaRegen(int manaRegen, ServerPlayerEntity player){
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(manaRegen);
        ServerPlayNetworking.send(player, ModMessages.MANA_REGEN_SYNC, buffer);
    }


}
