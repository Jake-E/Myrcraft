package io.alwa.myrcraft.tiles;

import io.alwa.myrcraft.Config;
import io.alwa.myrcraft.Myrcraft;
import io.alwa.myrcraft.blocks.TreeTapBlock;
import io.alwa.myrcraft.blocks.MyrcraftBlocks;
import io.alwa.myrcraft.items.MyrcraftItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class TileEntityTreeTap extends TileEntity implements ITickable
{
    public TileEntityTreeTap()
    {
        super(MyrcraftBlocks.TREE_TAP_TILE.get());
    }

    @Override
    public void tick() {
            BlockState tap = world.getBlockState(pos);
            Direction tapFace = tap.get(TreeTapBlock.FACING);

            TileEntity blockLog = world.getTileEntity(pos.offset(tapFace, 1));
            TileEntity blockBucket = world.getTileEntity(pos.down());

            if (blockLog instanceof TileEntityRubberWood) {
                if (blockBucket instanceof TileEntityWoodBucket && world.getWorld().getGameTime() % Config.TRANSFER_TICKS.get() == 0) {
                    TileEntityWoodBucket bucket = (TileEntityWoodBucket) blockBucket;
                    TileEntityRubberWood logTank = (TileEntityRubberWood) blockLog;

                    FluidStack stack = logTank.tank.drain(new FluidStack(Myrcraft.LATEX, Config.TRANSFER_RATE.get()), IFluidHandler.FluidAction.EXECUTE);

                    if (stack != null) {
                        int filled = bucket.tank.fill(stack, IFluidHandler.FluidAction.EXECUTE);
                        if (filled > 0) {
                            logTank.tank.drain(new FluidStack(Myrcraft.LATEX, filled), IFluidHandler.FluidAction.EXECUTE);
                            world.setBlockState(pos, world.getBlockState(pos).with(TreeTapBlock.FLOWING, true), 3);
                            if (world.isRemote) {
                                spawnParticle(pos, tapFace);
                            }
                            if(logTank.tank.getFluidAmount() <= 0){
                                world.setBlockState(pos, world.getBlockState(pos).with(TreeTapBlock.FLOWING, false), 3);
                            }
                        } else if(world.getBlockState(pos).get(TreeTapBlock.FLOWING)){
                            world.setBlockState(pos, world.getBlockState(pos).with(TreeTapBlock.FLOWING, false), 3);
                        }
                    } else if(world.getBlockState(pos).get(TreeTapBlock.FLOWING)){
                        world.setBlockState(pos, world.getBlockState(pos).with(TreeTapBlock.FLOWING, false), 3);
                    }
                } else if(!(blockBucket instanceof TileEntityWoodBucket)){
                    world.setBlockState(pos, world.getBlockState(pos).with(TreeTapBlock.FLOWING, false), 3);
                }
            } else {
                if(!world.isRemote) {
                    world.addEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(MyrcraftItems.TREE_TAP.get())));
                    world.setBlockState(pos, Blocks.AIR.getDefaultState());
                }
            }
    }

    private void spawnParticle(BlockPos pos, Direction tapFace){
//        ParticleTapDrip drip = new ParticleTapDrip(world, pos.getX() + 0.5D + tapFace.getXOffset() * 0.3D, pos.getY() - 0.2D, pos.getZ() + 0.5D + tapFace.getZOffset() * 0.3D);
        //TODO find how this is done now
//        Minecraft.getInstance().effectRenderer.addEffect(drip);
    }
}
