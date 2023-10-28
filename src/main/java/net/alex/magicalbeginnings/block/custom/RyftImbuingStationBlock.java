package net.alex.magicalbeginnings.block.custom;

import net.alex.magicalbeginnings.block.entity.RyftImbuingStationBlockEntity;
import net.alex.magicalbeginnings.block.entity.ModBlockEntities;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import javax.swing.text.html.BlockView;

public class RyftImbuingStationBlock extends BlockWithEntity implements BlockEntityProvider {
    private static final VoxelShape SHAPE = Block.createCuboidShape(0, 0, 0, 16, 12, 16);

    public RyftImbuingStationBlock(Settings settings) {
        super(settings);
    }

    /**
     * Gets the visual dimensions of the block
     *
     * @param state state of the block
     * @param world the world
     * @param pos the position of the block in the world
     * @param context ?
     *
     * @return The dimensions of the block
     */
    @Override
    public VoxelShape getOutlineShape(BlockState state, net.minecraft.world.BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    /**
     * Gets the render type model
     *
     * @param state the current state of the block
     *
     * @return The model of the block render type
     */
    public BlockRenderType getRenderType(BlockState state){
        return BlockRenderType.MODEL;
    }

    /**
     * Creates a new entity of the block
     *
     * @param pos the position of the block in the world
     * @param state the current state of the block
     *
     * @return a new ryft imbuing station block entity
     */
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new RyftImbuingStationBlockEntity(pos, state);
    }

    /**
     * Called if the block state has changed. For example if the block is removed
     *
     * @param state the old state of the block
     * @param world the world
     * @param pos the position of the block in the world
     * @param newState the new state of the block
     * @param moved ?
     */
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof RyftImbuingStationBlockEntity) {
                ItemScatterer.spawn(world, pos, (RyftImbuingStationBlockEntity)blockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    /**
     * Called when the block is clicked on by the player
     *
     * @param state the current state of the block
     * @param world the world
     * @param pos the position of the block in the world
     * @param player the player that clicked the block
     * @param hand clicked with the main hand or offhand
     * @param hit how the block was hit
     *
     * @return if the action was a success
     */
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            // does not happen on the client
            NamedScreenHandlerFactory screenHandlerFactory = ((RyftImbuingStationBlockEntity) world.getBlockEntity(pos));

            // opens the block gui
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }

        return ActionResult.SUCCESS;
    }

    /**
     * A ticker ?
     *
     * @param world the world
     * @param state the state of the block
     * @param type the type of the block entity
     *
     * @return a validated ticker ??
     */
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.RYFT_IMBUING_STATION_BLOCK_ENTITY_BLOCK_ENTITY,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }
}
