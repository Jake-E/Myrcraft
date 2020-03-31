package io.alwa.myrcraft.blocks;

import io.alwa.myrcraft.tiles.TileEntityRubberWood;
import io.alwa.myrcraft.tiles.TileEntityTreeTap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.storage.loot.LootContext;

import javax.annotation.Nullable;
import java.util.List;

public class TreeTapBlock extends DirectionalBlock
{
    public static final BooleanProperty FLOWING = BooleanProperty.create("flowing");
    
    public TreeTapBlock()
    {
        super(Properties.create(Material.IRON));
        setDefaultState(getStateContainer().getBaseState().with(FACING, Direction.NORTH).with(FLOWING, false));
    }

    @Override
    public List<ItemStack> getDrops(BlockState p_220076_1_, LootContext.Builder p_220076_2_) {
        return super.getDrops(p_220076_1_, p_220076_2_);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        if(context.getFace().getAxis() == Direction.Axis.Y) return null;
        if(context.getWorld().getTileEntity(context.getPos().offset(context.getFace().getOpposite())) instanceof TileEntityRubberWood) {
            return this.getDefaultState().with(FACING, context.getFace().getOpposite());
        }
        return null;
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
               return Block.makeCuboidShape(13.0D, -1.0D, 6.0D, 16.0D, 4.0D, 10.0D);
            case WEST:
                return Block.makeCuboidShape(0.0D, -1.0D, 6.0D, 3.0D, 4.0D, 10.0D);
            case SOUTH:
                return Block.makeCuboidShape(6.0D, -1.0D, 13.0D, 10.0D, 4.0D, 16.0D);
            default:
                return Block.makeCuboidShape(6.0D, -1.0D, 0.0D, 10.0D, 4.0D, 3.0D);
        }
    }
}
