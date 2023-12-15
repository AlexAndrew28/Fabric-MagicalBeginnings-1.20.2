package net.alex.magicalbeginnings.item.custom;

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
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.ZipCompressor;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TeleportingAmuletItem extends Item {

    //TODO: get rid of hardcoded values
    private static final int manaCost = 50;
    private static final int expGain = 10;


    public TeleportingAmuletItem(Settings settings) {
        super(settings);
    }


    /**
     * When this item is used (right click when in the players hand) this method will call
     * Effect: teleports the player to the last coordinate that they marked
     *
     * @param world the world the item was used in
     * @param user the player who used the item
     * @param hand the hand used
     * @return the success of the action
     */
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack wand = new ItemStack(ModItems.TELEPORTING_AMULET);
        NbtCompound nbt = ((IEntityDataSaver) user).getPersistentData();

        // cooldown for using the item
        user.getItemCooldownManager().set(this, 2);
        if (!world.isClient) {

            if(user.isSneaking()){
                double xPos = user.getX();
                double yPos = user.getY();
                double zPos = user.getZ();

                nbt.putDouble("selfTeleportX", xPos);
                nbt.putDouble("selfTeleportY", yPos);
                nbt.putDouble("selfTeleportZ", zPos);
                nbt.putBoolean("selfTeleportInit", true);

                user.sendMessage(Text.literal("Coordinates set to: (" + (int) xPos + ", " + (int) yPos + ", " + (int) zPos + ")"));
            }else{
                if(nbt.getBoolean("selfTeleportInit")){
                    int currentMana = ((IEntityDataSaver) MinecraftClient.getInstance().player).getPersistentData().getInt("currentMana");
                    // if the player has enough mana
                    if (currentMana > manaCost){
                        // update player stored values
                        ClientPlayNetworking.send(ModMessages.USE_MANA, PacketByteBufs.create().writeInt(manaCost));
                        ClientPlayNetworking.send(ModMessages.INCREASE_MAGIC_EXP, PacketByteBufs.create().writeInt(expGain));


                        user.teleport(nbt.getDouble("selfTeleportX"), nbt.getDouble("selfTeleportY"), nbt.getDouble("selfTeleportZ"));

                    } else{
                        user.sendMessage(Text.literal("Not Enough Mana"));
                    }
                } else{
                    user.sendMessage(Text.literal("Coordinates not set (shift click to set)"));
                }
            }



        }

        return TypedActionResult.success(wand);
    }

}
