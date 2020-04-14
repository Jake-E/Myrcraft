package io.alwa.myrcraft.fluids;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.StateContainer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

import javax.annotation.Nullable;

public class LatexFluid extends ForgeFlowingFluid
{
    public LatexFluid(Properties properties)
    {
        super(properties);
        this.setDefaultState(this.getStateContainer().getBaseState().with(LEVEL_1_8, 7));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Fluid, IFluidState> builder)
    {
        super.fillStateContainer(builder);
        builder.add(LEVEL_1_8);
    }

    @Nullable
    @OnlyIn(Dist.CLIENT)
    @Override
    protected IParticleData getDripParticleData() {
        return ParticleTypes.DRIPPING_WATER;
    }

    @Override
    public boolean isSource(IFluidState state)
    {
        return false;
    }
    
    @Override
    public int getLevel(IFluidState state)
    {
        return state.get(LEVEL_1_8);
    }
}
