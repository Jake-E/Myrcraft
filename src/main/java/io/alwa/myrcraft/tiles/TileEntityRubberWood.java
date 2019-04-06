package io.alwa.myrcraft.tiles;

import io.alwa.myrcraft.Myrcraft;
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

public class TileEntityRubberWood extends TileEntity {

    public final FluidTank tank = new FluidTank(1000){
        @Override
        protected void onContentsChanged() {
            TileEntityRubberWood.this.markDirty();
        }

        @Override
        public boolean canDrainFluidType(@Nullable FluidStack fluid) {
            return fluid != null && fluid.getFluid() == Myrcraft.LATEX;
        }
    };

    public TileEntityRubberWood() {
        tank.setTileEntity(this);
        tank.setCanDrain(true);
        tank.setCanFill(false);
    }


    public void read(NBTTagCompound nbt){
        tank.readFromNBT(nbt.getCompoundTag("tank"));
        tank.setCapacity(nbt.getInteger("capacity"));
    }

    public void write(NBTTagCompound nbt){
        nbt.setTag("tank", tank.writeToNBT(new NBTTagCompound()));
        nbt.setInteger("capacity", tank.getCapacity());
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
