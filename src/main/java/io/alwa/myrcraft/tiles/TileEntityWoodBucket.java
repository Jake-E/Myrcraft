package io.alwa.myrcraft.tiles;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

import javax.annotation.Nullable;

public class TileEntityWoodBucket extends TileEntity {

    public FluidTank tank = new FluidTank(10000) {
        @Override
        public boolean canFillFluidType(FluidStack fluid) {
            return (fluid.getFluid().getTemperature() <= 300);
        }

        @Override
        protected void onContentsChanged() {
            markDirty();
            IBlockState state = world.getBlockState(pos);
            world.notifyBlockUpdate(pos, state, state, 11);
            world.markBlockRangeForRenderUpdate(pos, pos);
        }
    };


    public void read(NBTTagCompound nbt){
        tank.readFromNBT(nbt.getCompoundTag("tank"));
    }

    public void write(NBTTagCompound nbt){
        nbt.setTag("tank", tank.writeToNBT(new NBTTagCompound()));
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        read(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        write(compound);
        return super.writeToNBT(compound);
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {

        NBTTagCompound nbt = new NBTTagCompound();
        write(nbt);

        return new SPacketUpdateTileEntity(pos, 0, nbt);
    }

    @Override
    public NBTTagCompound getUpdateTag() {

        return serializeNBT();
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        read(pkt.getNbtCompound());
    }

    @Override
    public void handleUpdateTag(NBTTagCompound tag) {
        deserializeNBT(tag);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY ? (T) tank : super.getCapability(capability, facing);
    }
}
