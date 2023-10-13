package net.alex.magicalbeginnings.item.custom;

import net.alex.magicalbeginnings.entity.custom.FireballEntity;
import net.alex.magicalbeginnings.item.ModItems;
import net.alex.magicalbeginnings.networking.ModMessages;
import net.alex.magicalbeginnings.util.IEntityDataSaver;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class RubyWandItem extends Item {


    public RubyWandItem(Settings settings) {
        super(settings);
    }


    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack wand = new ItemStack(ModItems.RUBY_WAND);


        user.getItemCooldownManager().set(this, 2);
        if (!world.isClient) {

            int currentMana = ((IEntityDataSaver) MinecraftClient.getInstance().player).getPersistentData().getInt("currentMana");

            if (currentMana > 50){
                ClientPlayNetworking.send(ModMessages.USE_MANA, PacketByteBufs.create());

                FireballEntity persistentProjectileEntity = new FireballEntity(world, user);
                persistentProjectileEntity.setVelocity(user, user.getPitch(), user.getYaw(), 1.5f, 1.5f, 0f);

                world.spawnEntity(persistentProjectileEntity);
            }else{
                user.sendMessage(Text.literal("Not Enough Mana"));
            }

        }

        return TypedActionResult.success(wand);
    }

}
