package net.alex.magicalbeginnings.item.custom;

import net.alex.magicalbeginnings.entity.custom.FireballEntity;
import net.alex.magicalbeginnings.entity.custom.LocationMarkOrbEntity;
import net.alex.magicalbeginnings.item.ModItems;
import net.alex.magicalbeginnings.networking.ModMessages;
import net.alex.magicalbeginnings.util.IEntityDataSaver;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class MarkingWandItem extends Item {

    //TODO: get rid of hardcoded values
    private static final int manaCost = 50;
    private static final int expGain = 10;


    public MarkingWandItem(Settings settings) {
        super(settings);
    }


    /**
     * When this item is used (right click when in the players hand) this method will call
     * Effect: shoots a projectile that marks the coordinates of any block or entity it hits
     *
     * @param world the world the item was used in
     * @param user the player who used the item
     * @param hand the hand used
     * @return the success of the action
     */
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack wand = new ItemStack(ModItems.MARKING_WAND);

        // cooldown for using the item
        user.getItemCooldownManager().set(this, 2);
        if (!world.isClient) {

            int currentMana = ((IEntityDataSaver) MinecraftClient.getInstance().player).getPersistentData().getInt("currentMana");

            // if the player has enough mana
            if (currentMana > manaCost){
                // update player stored values
                ClientPlayNetworking.send(ModMessages.USE_MANA, PacketByteBufs.create().writeInt(manaCost));
                ClientPlayNetworking.send(ModMessages.INCREASE_MAGIC_EXP, PacketByteBufs.create().writeInt(expGain));

                // create a new proj
                LocationMarkOrbEntity persistentProjectileEntity = new LocationMarkOrbEntity(world, user);

                // move the proj along its path so it spawns in front of the player instead of inside
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
