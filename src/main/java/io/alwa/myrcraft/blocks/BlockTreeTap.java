package io.alwa.myrcraft.blocks;

import io.alwa.myrcraft.tiles.TileEntityRubberWood;
import io.alwa.myrcraft.tiles.TileEntityTreeTap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockTreeTap extends BlockHorizontal {

    public BlockTreeTap() {
        super(Material.IRON);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityTreeTap();
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, facing.getOpposite());
    }

    @Override
    public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {

        return side.getAxis() != EnumFacing.Axis.Y && worldIn.getTileEntity(pos.offset(side.getOpposite())) instanceof TileEntityRubberWood;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        switch (state.getValue(FACING))
        {
            case EAST:
                return new AxisAlignedBB(0.769999988079071D, 0.00000000298023224D, 0.3999999940395355D, 1.0D, 0.150000011920929D, 0.5999999761581421D);
            case WEST:
                return new AxisAlignedBB(0.0D, 0.00000000298023224D, 0.3999999940395355D, 0.23000001192092896D, 0.150000011920929D, 0.5999999761581421D);
            case SOUTH:
                return new AxisAlignedBB(0.3999999940395355D, 0.00000000298023224D, 1.0D, 0.5999999761581421D, 0.150000011920929D, 0.77000001192092896D);
            case NORTH:
                return new AxisAlignedBB(0.3999999940395355D, 0.00000000298023224D, 0.0D, 0.5999999761581421D, 0.150000011920929D, 0.23000001192092896D);
            default:
                return new AxisAlignedBB(0.1499999940395355D, 0.00000000298023224D, 0.0D, 0.5999999761581421D, 0.150000011920929D, 0.30000001192092896D);
        }
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return NULL_AABB;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getHorizontalIndex();
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
}
