package net.alex.magicalbeginnings.screen;

import net.alex.magicalbeginnings.block.entity.RyftImbuingStationBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import org.jetbrains.annotations.Nullable;

public class RyftImbuingScreenHandler extends ScreenHandler {

    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    public final RyftImbuingStationBlockEntity blockEntity;

    public RyftImbuingScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf buf){
        this(syncId, inventory, inventory.player.getWorld().getBlockEntity(buf.readBlockPos()),
                new ArrayPropertyDelegate(10));
    }


    public RyftImbuingScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, PropertyDelegate arrayPropertyDelegate) {
        super(ModScreenHandlers.RYFT_IMBUING_SCREEN_HANDLER, syncId);
        checkSize(((Inventory) blockEntity), 10);
        this.inventory = ((Inventory) blockEntity);
        inventory.onOpen(playerInventory.player);
        this.propertyDelegate = arrayPropertyDelegate;
        this.blockEntity = ((RyftImbuingStationBlockEntity) blockEntity);

        // adds all the slots onto the slots in the gui texture
        this.addSlot(new Slot(inventory, 0, 66, 32));
        this.addSlot(new Slot(inventory, 1, 7, 17));
        this.addSlot(new Slot(inventory, 2, 7, 43));
        this.addSlot(new Slot(inventory, 3, 37, 6));
        this.addSlot(new Slot(inventory, 4, 37, 59));
        this.addSlot(new Slot(inventory, 5, 94, 6));
        this.addSlot(new Slot(inventory, 6, 94, 59));
        this.addSlot(new Slot(inventory, 7, 119, 17));
        this.addSlot(new Slot(inventory, 8, 119, 43));
        this.addSlot(new Slot(inventory, 9, 149, 48));

        // adds player inv and hotbar
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);


        addProperties(arrayPropertyDelegate);

    }

    /**
     * Checks if the station is currently crafting
     *
     * @return true if crafting
     */
    public boolean isCrafting(){

        //TODO: do this properly instead of just always rendering a 0 length arrow
        return true;

    }

    /**
     * Gets a scale for the crafting arrows in the gui
     *
     * @return the size of the arrow to display
     */
    public int getScaledProgress() {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);
        int progressArrowSize = 26;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    /**
     * Allows for quick mouse movements
     *
     * @param player the player
     * @param invSlot the index of the slot to quick-move from
     * @return an item stack
     */
    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    /**
     * Returns if the player can use this station
     *
     * @param player the player in question
     *
     * @return true if the player can use, false otherwise
     */
    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    /**
     * Adds the player inv to the station gui
     *
     * @param playerInventory the players inventory
     */
    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    /**
     * Adds the players hotbar to the gui
     *
     * @param playerInventory the players inventory
     */
    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
