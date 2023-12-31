package net.alex.magicalbeginnings.block.entity;

import it.unimi.dsi.fastutil.longs.AbstractLongList;
import net.alex.magicalbeginnings.item.ModItems;
import net.alex.magicalbeginnings.screen.RyftImbuingScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class RyftImbuingStationBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(10, ItemStack.EMPTY);

    // one centre input slot
    private static final int INPUT_SLOT_base = 0;

    // 9 surrounding input slots
    private static final int INPUT_SLOT_1 = 1;
    private static final int INPUT_SLOT_2 = 2;
    private static final int INPUT_SLOT_3 = 3;
    private static final int INPUT_SLOT_4 = 4;
    private static final int INPUT_SLOT_5 = 5;
    private static final int INPUT_SLOT_6 = 6;
    private static final int INPUT_SLOT_7 = 7;
    private static final int INPUT_SLOT_8 = 8;

    private static final int[] ALL_INPUT_SLOTS = {
            INPUT_SLOT_1,
            INPUT_SLOT_2,
            INPUT_SLOT_3,
            INPUT_SLOT_4,
            INPUT_SLOT_5,
            INPUT_SLOT_6,
            INPUT_SLOT_7,
            INPUT_SLOT_8,
    };

    // a single output slot
    private static final int OUTPUT_SLOT = 9;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;

    // max progress before crafting is complete
    private int maxProgress = 150;

    // 1-100 chance that each slot destroys a single item
    private final int destroyInputChance = 25;

    private Random random = new Random();

    /**
     * Constructor
     *
     * @param pos the position of the block in the world
     * @param state the state of the block
     */
    public RyftImbuingStationBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.RYFT_IMBUING_STATION_BLOCK_ENTITY_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {

            // two cases: either crafting (including 0% progress) or finished crafting
            @Override
            public int get(int index) {
                return switch (index){
                    case 0 -> RyftImbuingStationBlockEntity.this.progress;
                    case 1 -> RyftImbuingStationBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index){
                    case 0 -> RyftImbuingStationBlockEntity.this.progress = value;
                    case 1 -> RyftImbuingStationBlockEntity.this.maxProgress = value;
                }
            }

            // 10 slots so returns size of 10
            @Override
            public int size() {
                return 10;
            }
        };

    }

    /**
     * Gets the inventory
     *
     * @return inventory
     */
    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    /**
     * reads the saved crafting progress
     *
     * @param nbt data to read
     */
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("ryft_imbuing_station.progress");
    }

    /**
     * writes current crafting progress
     *
     * @param nbt the nbt to write data to
     */
    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("ryft_imbuing_station.progress", progress);
    }

    /**
     * ??
     *
     * @param player the player that is opening the screen
     * @param buf    the packet buffer
     */
    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    /**
     * Gets the name of the block
     *
     * @return the name: ryft imbuing station
     */
    @Override
    public Text getDisplayName() {
        return Text.literal("Ryft Imbuing Station");
    }

    /**
     * handler for the block screen
     *
     * @param syncId ?
     * @param playerInventory the players inventory
     * @param player the player
     *
     * @return a new screen handler for the ryft imbuing station
     */
    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new RyftImbuingScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    /**
     * called every tick to update the block over time
     *
     * @param world the world
     * @param pos the position of the block in the world
     * @param state the state of the block
     */
    public void tick(World world, BlockPos pos, BlockState state) {
        if(world.isClient()){
            return;
        }
        // if block can craft
        if(isOutputSlotEmptyOrReceivable()){

            // if there is a valid recipe
            if(this.hasRecipe()){
                this.increaseCraftProgress();
                markDirty(world, pos, state);

                // if the crafting progress reaches max
                if(hasCraftingFinsihed()){
                    this.craftItem();
                    this.resetProgress();
                }
            } else{
                this.resetProgress();
            }
        } else {
            this.resetProgress();
            markDirty(world, pos, state);
        }
    }

    /**
     * resets the progress value to 0
     */
    private void resetProgress() {
        this.progress = 0;
    }

    /**
     * Crafts an item, placing it in the output slot and destroying an amount of the input items
     *
     */
    private void craftItem() {

        // random independent chance for each slot to be used
        for (int allInputSlot : ALL_INPUT_SLOTS) {
            int randomInt = random.nextInt(100);
            // destroy 1 from this slot
            if (randomInt < destroyInputChance) {
                this.removeStack(allInputSlot, 1);
            }
        }
        // gaunted removal from centre slot
        this.removeStack(INPUT_SLOT_base, 1);

        // create the output
        ItemStack result = new ItemStack(ModItems.RYFT_INGOT);
        this.setStack(OUTPUT_SLOT, new ItemStack(result.getItem(), getStack(OUTPUT_SLOT).getCount() + result.getCount()));


    }

    /**
     * Checks if crafting has finished
     *
     * @return true if finished else false
     */
    private boolean hasCraftingFinsihed() {
        return progress >= maxProgress;
    }

    /**
     * Increments crafting progress by 1
     */
    private void increaseCraftProgress() {
        progress ++;
    }

    /**
     * Checks if there is a current valid recipe in the block
     *
     * @return true if valid else false
     */
    private boolean hasRecipe() {
        ItemStack result = new ItemStack(ModItems.RYFT_INGOT);

        boolean hasInput = getStack(INPUT_SLOT_base).getItem() == Items.IRON_INGOT &&
                getStack(INPUT_SLOT_1).getItem() == ModItems.RYFT_CRYSTAL &&
                getStack(INPUT_SLOT_2).getItem() == ModItems.RYFT_CRYSTAL &&
                getStack(INPUT_SLOT_3).getItem() == ModItems.RYFT_CRYSTAL &&
                getStack(INPUT_SLOT_4).getItem() == ModItems.RYFT_CRYSTAL &&
                getStack(INPUT_SLOT_5).getItem() == ModItems.RYFT_CRYSTAL &&
                getStack(INPUT_SLOT_6).getItem() == ModItems.RYFT_CRYSTAL &&
                getStack(INPUT_SLOT_7).getItem() == ModItems.RYFT_CRYSTAL &&
                getStack(INPUT_SLOT_8).getItem() == ModItems.RYFT_CRYSTAL;

        return hasInput && canInsertAmountIntoOutputSlot(result) && canInsertItemIntoOutputSlot(result.getItem());

    }

    /**
     * Checks if the output slot is either empty or the same item type
     *
     * @param item the item in the output slot
     * @return boolean based on contents of output slot
     */
    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.getStack(OUTPUT_SLOT).getItem() == item || this.getStack(OUTPUT_SLOT).isEmpty();
    }

    /**
     * Checks if the output slot can hold another of the same item
     *
     * @param result the output from the crafting
     * @return boolean based on the contents of the output
     */
    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
        return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= getStack(OUTPUT_SLOT).getMaxCount();
    }

    /**
     * Checks if the output slot is either empty or there are fewer items than the max amount
     *
     * @return boolean based on contents of the output
     */
    private boolean isOutputSlotEmptyOrReceivable() {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
    }


}
