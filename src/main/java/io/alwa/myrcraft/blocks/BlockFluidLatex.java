package io.alwa.myrcraft.blocks;

import io.alwa.myrcraft.Myrcraft;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;

public class BlockFluidLatex extends BlockFluidClassic {
    public BlockFluidLatex() {
        super(Myrcraft.LATEX, Material.WATER);
    }
}