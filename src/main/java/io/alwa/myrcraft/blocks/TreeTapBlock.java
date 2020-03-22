package io.alwa.myrcraft.blocks;

import io.alwa.myrcraft.tiles.TileEntityTreeTap;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TreeTapBlock extends DirectionalBlock
{
    public static final BooleanProperty FLOWING = BooleanProperty.create("flowing");
    
    //TODO these are wrong and need fixing
    public static VoxelShape AXIS_EAST =  Block.makeCuboidShape(0.769999988079071D, 0.00000000298023224D, 0.3999999940395355D, 1.0D, 0.150000011920929D, 0.5999999761581421D);
    public static VoxelShape AXIS_WEST =  Block.makeCuboidShape(0.0D, 0.00000000298023224D, 0.3999999940395355D, 0.23000001192092896D, 0.150000011920929D, 0.5999999761581421D);
    public static VoxelShape AXIS_SOUTH =  Block.makeCuboidShape(0.3999999940395355D, 0.00000000298023224D, 1.0D, 0.5999999761581421D, 0.150000011920929D, 0.77000001192092896D);
    public static VoxelShape AXIS_NORTH =  Block.makeCuboidShape(0.3999999940395355D, 0.00000000298023224D, 0.0D, 0.5999999761581421D, 0.150000011920929D, 0.23000001192092896D);

    public TreeTapBlock()
    {
        super(Properties.create(Material.IRON));
        setDefaultState(getStateContainer().getBaseState().with(FACING, Direction.NORTH).with(FLOWING, false));
    }
    
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing());
    }
    
    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> props)
    {
        props.add(FACING, FLOWING);
    }
    
    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return new TileEntityTreeTap();
    }
    
    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }
    
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_)
    {
        switch(state.get(FACING))
        {
            case EAST:
                return AXIS_EAST;
            case WEST:
                return AXIS_WEST;
            case SOUTH:
                return AXIS_SOUTH;
            default:
                return AXIS_NORTH;
        }
    }
}
