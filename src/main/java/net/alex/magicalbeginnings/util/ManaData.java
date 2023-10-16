package net.alex.magicalbeginnings.util;

import net.alex.magicalbeginnings.networking.ModMessages;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class ManaData {

    public static void resetMana(IEntityDataSaver player){
        NbtCompound nbt = player.getPersistentData();
        nbt.putInt("maxMana", 0);
        nbt.putInt("currentMana", 0);
        nbt.putInt("manaRegen", 0);

        syncCurrentMana(0, (ServerPlayerEntity) player);
        syncManaRegen(0, (ServerPlayerEntity) player);
        syncMaxMana(0, (ServerPlayerEntity) player);
    }

    public static void addMaxMana(IEntityDataSaver player, int amount){
        NbtCompound nbt = player.getPersistentData();
        int maxMana = nbt.getInt("maxMana");

        if (maxMana == 0 && amount == 0){
            maxMana = 100;
        }else{
            maxMana += amount;
        }

        nbt.putInt("maxMana", maxMana);

        syncMaxMana(maxMana, (ServerPlayerEntity) player);
    }

    public static void addManaRegen(IEntityDataSaver player, int amount){
        NbtCompound nbt = player.getPersistentData();
        int manaRegen = nbt.getInt("manaRegen");

        if (manaRegen == 0 && amount == 0){
            manaRegen = 1;
        }
        else{
            manaRegen += amount;
        }
        nbt.putInt("manaRegen", manaRegen);

        syncManaRegen(manaRegen, (ServerPlayerEntity) player);
    }

    public static void addMana(IEntityDataSaver player, int amount){
        NbtCompound nbt = player.getPersistentData();

        int currentMana = nbt.getInt("currentMana");
        int maxMana = nbt.getInt("maxMana");

        currentMana += amount;

        if (currentMana > maxMana){
            currentMana = maxMana;
        }

        nbt.putInt("currentMana", currentMana);

        syncCurrentMana(currentMana, (ServerPlayerEntity) player);
    }

    public static void removeMana(IEntityDataSaver player, int amount){
        NbtCompound nbt = player.getPersistentData();

        int currentMana = nbt.getInt("currentMana");

        currentMana -= amount;

        if (currentMana < 0){
            currentMana = 0;
        }

        nbt.putInt("currentMana", currentMana);

        syncCurrentMana(currentMana, (ServerPlayerEntity) player);
    }


    public static void regenMana(IEntityDataSaver player){
        NbtCompound nbt = player.getPersistentData();

        int currentMana = nbt.getInt("currentMana");
        int maxMana = nbt.getInt("maxMana");
        //int maxMana = 1000;
        int manaRegen = nbt.getInt("manaRegen");
        //int manaRegen = 1;

        currentMana += manaRegen;

        if (currentMana > maxMana){
            currentMana = maxMana;
        }

        nbt.putInt("currentMana", currentMana);
        syncCurrentMana(currentMana, (ServerPlayerEntity) player);
    }

    public static void syncCurrentMana(int currentMana, ServerPlayerEntity player){
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(currentMana);
        ServerPlayNetworking.send(player, ModMessages.CURRENT_MANA_SYNC, buffer);
    }

    public static void syncMaxMana(int maxMana, ServerPlayerEntity player){
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(maxMana);
        ServerPlayNetworking.send(player, ModMessages.MAX_MANA_SYNC, buffer);
    }

    public static void syncManaRegen(int manaRegen, ServerPlayerEntity player){
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(manaRegen);
        ServerPlayNetworking.send(player, ModMessages.MANA_REGEN_SYNC, buffer);
    }


}
