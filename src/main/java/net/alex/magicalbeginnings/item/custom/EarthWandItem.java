package net.alex.magicalbeginnings.item.custom;

import net.alex.magicalbeginnings.entity.custom.DigProjectileEntity;
import net.alex.magicalbeginnings.entity.custom.FireballEntity;
import net.alex.magicalbeginnings.item.ModItems;
import net.alex.magicalbeginnings.networking.ModMessages;
import net.alex.magicalbeginnings.util.IEntityDataSaver;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EarthWandItem extends Item {

    private static final int manaCost = 50;
    private static final int expGain = 10;


    public EarthWandItem(Settings settings) {
        super(settings);
    }


    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack wand = new ItemStack(ModItems.EARTH_WAND);


        user.getItemCooldownManager().set(this, 2);
        if (!world.isClient) {

            int currentMana = ((IEntityDataSaver) MinecraftClient.getInstance().player).getPersistentData().getInt("currentMana");

            if (currentMana > manaCost){
                ClientPlayNetworking.send(ModMessages.USE_MANA, PacketByteBufs.create().writeInt(manaCost));

                ClientPlayNetworking.send(ModMessages.INCREASE_MAGIC_EXP, PacketByteBufs.create().writeInt(expGain));
                DigProjectileEntity persistentProjectileEntity = new DigProjectileEntity(world, user);

                persistentProjectileEntity.setVelocity(user, user.getPitch(), user.getYaw(), 1.5f, 1.5f, 0f);

                Vec3d vel = persistentProjectileEntity.getVelocity();

                double x = persistentProjectileEntity.getX();
                double y = persistentProjectileEntity.getY();
                double z = persistentProjectileEntity.getZ();

                double newX = x + vel.x;
                double newY = y + vel.y;
                double newZ = z + vel.z;

                persistentProjectileEntity.setPos(newX, newY, newZ);

                world.spawnEntity(persistentProjectileEntity);

            }else{
                user.sendMessage(Text.literal("Not Enough Mana"));
            }

        }

        return TypedActionResult.success(wand);
    }

}
