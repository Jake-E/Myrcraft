package io.alwa.myrcraft.blocks;


import io.alwa.myrcraft.Myrcraft;
import io.alwa.myrcraft.tiles.TileEntityWoodBucket;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

public class BlockWoodBucket extends Block {
    public BlockWoodBucket() {
        super(Material.WOOD);
        setSoundType(SoundType.WOOD);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityWoodBucket();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        IItemHandler inv = player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

        if (inv == null)
        {
            return false;
        }

        TileEntity tileEntity = world.getTileEntity(pos);
        TileEntityWoodBucket tile = tileEntity instanceof TileEntityWoodBucket ? (TileEntityWoodBucket) tileEntity : null;

        if (tile == null)
        {
            return false;
        }
        ItemStack stack = player.getHeldItem(hand);
        ItemStack stack1 = stack.copy();
        FluidActionResult result = FluidUtil.tryFillContainerAndStow(stack1, tile.tank, inv, Integer.MAX_VALUE, player, !world.isRemote);

        if (!result.isSuccess())
        {
            result = FluidUtil.tryEmptyContainerAndStow(stack1, tile.tank, inv, Integer.MAX_VALUE, player, !world.isRemote);
        }

        if (result.isSuccess())
        {
            player.setHeldItem(hand, result.getResult());
            tile.markDirty();
        }

        return true;
    }
    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(0.075D, 0.7D, 0.075D, 0.925D, 0D, 0.925D);
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
        return new AxisAlignedBB(0D,0D,0D,0D,0D,0D);
    }

    @Override
    public boolean isBlockNormalCube(IBlockState state) {
        return false;
    }
}
