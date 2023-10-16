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

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);


        addProperties(arrayPropertyDelegate);

    }

    public boolean isCrafting(){
        return true;
        /*
        return propertyDelegate.get(0) > 0 &&
                propertyDelegate.get(1) > 0 &&
                propertyDelegate.get(2) > 0 &&
                propertyDelegate.get(3) > 0 &&
                propertyDelegate.get(4) > 0 &&
                propertyDelegate.get(5) > 0 &&
                propertyDelegate.get(6) > 0 &&
                propertyDelegate.get(7) > 0 &&
                propertyDelegate.get(8) > 0;

         */
    }

    public int getScaledProgress() {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);
        int progressArrowSize = 26;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

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

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }


    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
