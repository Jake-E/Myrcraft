package io.alwa.myrcraft.tiles;

import io.alwa.myrcraft.Myrcraft;
import io.alwa.myrcraft.MyrcraftConfig;
import io.alwa.myrcraft.blocks.BlockTreeTap;
import io.alwa.myrcraft.blocks.MyrcraftBlocks;
import io.alwa.myrcraft.client.particle.ParticleTapDrip;
import io.alwa.myrcraft.items.MyrcraftItems;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleDrip;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Random;

public class TileEntityTreeTap extends TileEntity implements ITickable {
    @Override
    public void update() {
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
                            if (world.isRemote) {
                                ParticleTapDrip drip = new ParticleTapDrip(world, pos.getX()+0.2D, pos.getY()-0.1D, pos.getZ()+0.5D);
                                Minecraft.getMinecraft().effectRenderer.addEffect(drip);
                            }
                        }
                    }
                }
            } else {
                if(!world.isRemote) {
                    world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(MyrcraftItems.TREETAP)));
                    world.setBlockToAir(pos);
                }
            }
    }
}
