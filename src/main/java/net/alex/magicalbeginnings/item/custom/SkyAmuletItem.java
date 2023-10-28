package net.alex.magicalbeginnings.item.custom;

import com.ibm.icu.message2.Mf2DataModel;
import net.alex.magicalbeginnings.item.ModItems;
import net.alex.magicalbeginnings.networking.ModMessages;
import net.alex.magicalbeginnings.util.IEntityDataSaver;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SkyAmuletItem extends Item {

    private static final int manaCost = 50;
    private static final int expGain = 10;


    public SkyAmuletItem(Settings settings) {
        super(settings);
    }


    /**
     * When the player uses the item (right click while it is in the hand) this is called
     * Effect: causes the player to leap up into the sky
     *
     * @param world the world the item was used in
     * @param user the player who used the item
     * @param hand the hand used
     * @return the success of the action
     */
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack wand = new ItemStack(ModItems.SKY_AMULET);

        // cooldown of the item
        user.getItemCooldownManager().set(this, 2);
        if (!world.isClient) {

            int currentMana = ((IEntityDataSaver) MinecraftClient.getInstance().player).getPersistentData().getInt("currentMana");

            // if the player has enough mana
            if (currentMana > manaCost){
                ClientPlayNetworking.send(ModMessages.USE_MANA, PacketByteBufs.create().writeInt(manaCost));

                ClientPlayNetworking.send(ModMessages.INCREASE_MAGIC_EXP, PacketByteBufs.create().writeInt(expGain));

                user.sendMessage(Text.literal("find out how to add player vel properly"));

                //TODO not working -- fix this


            }else{
                user.sendMessage(Text.literal("Not Enough Mana"));
            }

        }

        return TypedActionResult.success(wand);
    }
}
