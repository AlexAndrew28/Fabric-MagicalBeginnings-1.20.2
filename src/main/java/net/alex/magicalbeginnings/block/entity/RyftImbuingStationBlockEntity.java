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

    private static final int INPUT_SLOT_base = 0;
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
    private static final int OUTPUT_SLOT = 9;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 150;

    private final int destroyInputChance = 25;

    private Random random = new Random();

    public RyftImbuingStationBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.RYFT_IMBUING_STATION_BLOCK_ENTITY_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
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

            @Override
            public int size() {
                return 10;
            }
        };

    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("ryft_imbuing_station.progress");
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("ryft_imbuing_station.progress", progress);
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Ryft Imbuing Station");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new RyftImbuingScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if(world.isClient()){
            return;
        }

        if(isOutputSlotEmptyOrReceivable()){
            if(this.hasRecipe()){
                this.increaseCraftProgress();
                markDirty(world, pos, state);

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

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem() {

        for(int i = 0; i<ALL_INPUT_SLOTS.length; i++){
            int randomInt = random.nextInt(100);
            if (randomInt < destroyInputChance){
                this.removeStack(ALL_INPUT_SLOTS[i], 1);
            }
        }

        this.removeStack(INPUT_SLOT_base, 1);

        ItemStack result = new ItemStack(ModItems.RYFT_INGOT);

        this.setStack(OUTPUT_SLOT, new ItemStack(result.getItem(), getStack(OUTPUT_SLOT).getCount() + result.getCount()));


    }

    private boolean hasCraftingFinsihed() {
        return progress >= maxProgress;
    }

    private void increaseCraftProgress() {
        progress ++;
    }

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

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.getStack(OUTPUT_SLOT).getItem() == item || this.getStack(OUTPUT_SLOT).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
        return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= getStack(OUTPUT_SLOT).getMaxCount();
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
    }


}
