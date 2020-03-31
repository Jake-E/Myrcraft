package io.alwa.myrcraft.blocks;

import io.alwa.myrcraft.tiles.TileEntityRubberWood;
import net.minecraft.block.BlockState;
import net.minecraft.tags.BlockTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.data.ForgeBlockTagsProvider;

import javax.annotation.Nullable;

public class LatexLogBlock extends RubberWoodBlock {

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityRubberWood();
    }
}
