package io.alwa.myrcraft.blocks;

import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

import javax.annotation.Nonnull;
import java.util.List;

public class RubberLeavesBlock extends LeavesBlock {
    public RubberLeavesBlock() {
        super(Properties.from(Blocks.JUNGLE_LEAVES));
    }

    @Override
    public boolean isShearable(@Nonnull ItemStack item, IWorldReader world, BlockPos pos) {
        return true;
    }

}
