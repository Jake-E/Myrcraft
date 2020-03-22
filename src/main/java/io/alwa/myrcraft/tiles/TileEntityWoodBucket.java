package io.alwa.myrcraft.tiles;

import io.alwa.myrcraft.blocks.MyrcraftBlocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEntityWoodBucket extends TileEntity {

    public FluidTank tank = new FluidTank(10000) {};
    
    public TileEntityWoodBucket()
    {
        super(MyrcraftBlocks.WOODEN_BUCKET_TILE.get());
    }
    
    @Override
    public void read(CompoundNBT compound)
    {
        super.read(compound);
        tank.readFromNBT(compound);
    }
    
    @Override
    public CompoundNBT write(CompoundNBT compound)
    {
        compound = super.write(compound);
        compound.merge(tank.writeToNBT(compound));
        return compound;
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket()
    {
        CompoundNBT nbt = new CompoundNBT();
        write(nbt);
        return new SUpdateTileEntityPacket(pos, 0, nbt);
    }

    @Override
    public CompoundNBT getUpdateTag()
    {
        return serializeNBT();
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet)
    {
        super.onDataPacket(net, packet);
        this.read(packet.getNbtCompound());
    }
    
    @Override
    public void handleUpdateTag(CompoundNBT tag)
    {
        deserializeNBT(tag);
    }
    
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, final @Nullable Direction side)
    {
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
        {
            return LazyOptional.of(() -> tank).cast();
        }
        return super.getCapability(cap, side);
    }
}
