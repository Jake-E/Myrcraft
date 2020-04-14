package io.alwa.myrcraft.blocks;

import net.minecraft.block.Blocks;
import net.minecraft.block.LogBlock;
import net.minecraft.block.material.MaterialColor;

public class RubberWoodBlock extends LogBlock {
    public RubberWoodBlock() {
        super(MaterialColor.WOOD, Properties.from(Blocks.JUNGLE_LOG).notSolid());
    }
}
