package io.alwa.myrcraft.fluids;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.state.StateContainer;
import net.minecraftforge.fluids.ForgeFlowingFluid;

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
