package io.alwa.myrcraft.tiles;

import io.alwa.myrcraft.Myrcraft;
import io.alwa.myrcraft.MyrcraftConfig;
import io.alwa.myrcraft.blocks.BlockTreeTap;
import io.alwa.myrcraft.blocks.MyrcraftBlocks;
import io.alwa.myrcraft.items.MyrcraftItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.fluids.FluidStack;

public class TileEntityTreeTap extends TileEntity implements ITickable {
    @Override
    public void update() {
        if (!world.isRemote) {
            IBlockState tap = world.getBlockState(pos);
            EnumFacing tapFace = tap.getValue(BlockTreeTap.FACING);

            TileEntity blockLog = world.getTileEntity(pos.offset(tapFace, 1));
            TileEntity blockBucket = world.getTileEntity(pos.down());

            if (blockLog instanceof TileEntityRubberWood) {
                if (blockBucket instanceof TileEntityWoodBucket && world.getTotalWorldTime() % MyrcraftConfig.rubbertree.transfer_ticks == 0) {
                    TileEntityWoodBucket bucket = (TileEntityWoodBucket) blockBucket;

                    FluidStack stack = ((TileEntityRubberWood) blockLog).tank.drain(new FluidStack(Myrcraft.LATEX, MyrcraftConfig.rubbertree.transfer_rate), false);

                    if (stack != null) {
                        int filled = bucket.tank.fill(stack, true);
                        if (filled > 0) {
                            ((TileEntityRubberWood) blockLog).tank.drain(new FluidStack(Myrcraft.LATEX, filled), true);
                        }
                    }
                }
            } else {
                world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(MyrcraftItems.TREETAP)));
                world.setBlockToAir(pos);
            }

        }
    }
}
