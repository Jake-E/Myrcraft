package io.alwa.myrcraft.blocks;

import io.alwa.myrcraft.tiles.TileEntityRubberWood;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockRubberWood extends BlockLog {

    public static final PropertyBool SAP = PropertyBool.create("sap");
    public static final BlockLog.EnumAxis[] AXES = BlockLog.EnumAxis.values();

    public BlockRubberWood() {
        setSoundType(SoundType.WOOD);
        Blocks.FIRE.setFireInfo(this, 5, 5);
        this.setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y).withProperty(SAP, false));
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return state.getValue(SAP) ? new TileEntityRubberWood() : null;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return state.getValue(SAP);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, LOG_AXIS, SAP);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(LOG_AXIS, AXES[meta % 4]).withProperty(SAP, meta >= 4);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(LOG_AXIS).ordinal() + (state.getValue(SAP) ? 4 : 0);
    }

    @Override
    public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
        super.onNeighborChange(world, pos, neighbor);
    }
}
