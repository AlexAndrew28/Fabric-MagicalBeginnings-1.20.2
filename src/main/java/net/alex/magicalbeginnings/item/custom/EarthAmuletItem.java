package net.alex.magicalbeginnings.item.custom;

import net.alex.magicalbeginnings.entity.custom.FireballEntity;
import net.alex.magicalbeginnings.item.ModItems;
import net.alex.magicalbeginnings.networking.ModMessages;
import net.alex.magicalbeginnings.util.IEntityDataSaver;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffect;
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

public class EarthAmuletItem extends Item {

    private static final int manaCost = 50;
    private static final int expGain = 10;


    public EarthAmuletItem(Settings settings) {
        super(settings);
    }

    /**
     * Called when the player right clicks with the item in their hand
     * Effect: player gains protection buff
     *
     * @param world the world the item was used in
     * @param user the player who used the item
     * @param hand the hand used
     *
     * @return the success of the action
     */
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack wand = new ItemStack(ModItems.EARTH_AMULET);

        // cooldown for using the item
        user.getItemCooldownManager().set(this, 2);
        if (!world.isClient) {

            int currentMana = ((IEntityDataSaver) MinecraftClient.getInstance().player).getPersistentData().getInt("currentMana");

            // check that the player has enough mana
            if (currentMana > manaCost){
                // reduce mana by mana cost
                ClientPlayNetworking.send(ModMessages.USE_MANA, PacketByteBufs.create().writeInt(manaCost));
                // increase magic exp
                ClientPlayNetworking.send(ModMessages.INCREASE_MAGIC_EXP, PacketByteBufs.create().writeInt(expGain));

                // add the protection buff
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 200, 1));

            }else{
                user.sendMessage(Text.literal("Not Enough Mana"));
            }

        }

        return TypedActionResult.success(wand);
    }
}
