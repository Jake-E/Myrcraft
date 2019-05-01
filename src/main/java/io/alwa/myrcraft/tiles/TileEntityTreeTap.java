package io.alwa.myrcraft.tiles;

import io.alwa.myrcraft.Myrcraft;
import io.alwa.myrcraft.MyrcraftConfig;
import io.alwa.myrcraft.blocks.BlockTreeTap;
import io.alwa.myrcraft.client.particle.ParticleTapDrip;
import io.alwa.myrcraft.items.MyrcraftItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

public class TileEntityTreeTap extends TileEntity implements ITickable {
    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
        return oldState.getBlock() != newState.getBlock();
    }

    @Override
    public void update() {
            IBlockState tap = world.getBlockState(pos);
            EnumFacing tapFace = tap.getValue(BlockTreeTap.FACING);

            TileEntity blockLog = world.getTileEntity(pos.offset(tapFace, 1));
            TileEntity blockBucket = world.getTileEntity(pos.down());

            if (blockLog instanceof TileEntityRubberWood) {
                if (blockBucket instanceof TileEntityWoodBucket && world.getTotalWorldTime() % MyrcraftConfig.rubbertree.transfer_ticks == 0) {
                    TileEntityWoodBucket bucket = (TileEntityWoodBucket) blockBucket;
                    TileEntityRubberWood logTank = (TileEntityRubberWood) blockLog;

                    FluidStack stack = logTank.tank.drain(new FluidStack(Myrcraft.LATEX, MyrcraftConfig.rubbertree.transfer_rate), false);

                    if (stack != null) {
                        int filled = bucket.tank.fill(stack, true);
                        if (filled > 0) {
                            logTank.tank.drain(new FluidStack(Myrcraft.LATEX, filled), true);
                            world.setBlockState(pos, world.getBlockState(pos).withProperty(BlockTreeTap.FLOWING, true), 3);
                            if (world.isRemote) {
                                spawnParticle(pos, tapFace);
                            }
                            if(logTank.tank.getFluidAmount() <= 0){
                                world.setBlockState(pos, world.getBlockState(pos).withProperty(BlockTreeTap.FLOWING, false), 3);
                            }
                        } else if(world.getBlockState(pos).getValue(BlockTreeTap.FLOWING)){
                            world.setBlockState(pos, world.getBlockState(pos).withProperty(BlockTreeTap.FLOWING, false), 3);
                        }
                    } else if(world.getBlockState(pos).getValue(BlockTreeTap.FLOWING)){
                        world.setBlockState(pos, world.getBlockState(pos).withProperty(BlockTreeTap.FLOWING, false), 3);
                    }
                } else if(!(blockBucket instanceof TileEntityWoodBucket)){
                    world.setBlockState(pos, world.getBlockState(pos).withProperty(BlockTreeTap.FLOWING, false), 3);
                }
            } else {
                if(!world.isRemote) {
                    world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(MyrcraftItems.TREETAP)));
                    world.setBlockToAir(pos);
                }
            }
    }

    private void spawnParticle(BlockPos pos, EnumFacing tapFace){
        ParticleTapDrip drip = new ParticleTapDrip(world, pos.getX() + 0.5D + tapFace.getXOffset() * 0.3D, pos.getY() - 0.2D, pos.getZ() + 0.5D + tapFace.getZOffset() * 0.3D);
        Minecraft.getMinecraft().effectRenderer.addEffect(drip);
    }
}
