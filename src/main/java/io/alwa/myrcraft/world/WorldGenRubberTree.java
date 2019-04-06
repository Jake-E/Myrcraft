package io.alwa.myrcraft.world;

import io.alwa.myrcraft.Myrcraft;
import io.alwa.myrcraft.MyrcraftConfig;
import io.alwa.myrcraft.blocks.BlockRubberWood;
import io.alwa.myrcraft.blocks.MyrcraftBlocks;
import io.alwa.myrcraft.tiles.TileEntityRubberWood;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraftforge.fluids.FluidStack;

import java.util.Random;

public class WorldGenRubberTree extends WorldGenTrees {

    private final int minTreeHeight;
    private final IBlockState stateWood;
    private final IBlockState metaLeaves;

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        int i = rand.nextInt(2) + this.minTreeHeight;
        boolean flag = true;

        if (position.getY() >= 1 && position.getY() + i + 1 <= worldIn.getHeight()) {
            for (int j = position.getY(); j <= position.getY() + 1 + i; ++j) {
                int k = 1;

                if (j == position.getY()) {
                    k = 0;
                }

                if (j >= position.getY() + 1 + i - 2) {
                    k = 2;
                }

                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l) {
                    for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1) {
                        if (j >= 0 && j < worldIn.getHeight()) {
                            if (!this.isReplaceable(worldIn, blockpos$mutableblockpos.setPos(l, j, i1))) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag) {
                return false;
            } else {
                IBlockState state = worldIn.getBlockState(position.down());

                if (state.getBlock().canSustainPlant(state, worldIn, position.down(), net.minecraft.util.EnumFacing.UP, (net.minecraft.block.BlockSapling) Blocks.SAPLING) && position.getY() < worldIn.getHeight() - i - 1) {
                    state.getBlock().onPlantGrow(state, worldIn, position.down(), position);
                    int k2 = 1;
                    int l2 = 0;

                    for (int i3 = position.getY() - 1 + i; i3 <= position.getY() + i + 3; ++i3) {
                        int i4 = i3 - (position.getY() + i);
                        int j1 = 1 - i4 / 2;

                        for (int k1 = position.getX() - j1; k1 <= position.getX() + j1; ++k1) {
                            int l1 = k1 - position.getX();

                            for (int i2 = position.getZ() - j1; i2 <= position.getZ() + j1; ++i2) {
                                int j2 = i2 - position.getZ();

                                if (Math.abs(l1) != j1 || Math.abs(j2) != j1 || rand.nextInt(2) != 0 && i4 != 0) {
                                    BlockPos blockpos = new BlockPos(k1, i3, i2);
                                    state = worldIn.getBlockState(blockpos);

                                    if (state.getBlock().isAir(state, worldIn, blockpos) || state.getBlock().isLeaves(state, worldIn, blockpos) || state.getMaterial() == Material.VINE) {
                                        this.setBlockAndNotifyAdequately(worldIn, blockpos, this.metaLeaves);
                                    }
                                }
                            }
                        }
                    }

                    for (int j3 = 0; j3 < i; ++j3) {
                        BlockPos upN = position.up(j3);
                        state = worldIn.getBlockState(upN);

                        if (state.getBlock().isAir(state, worldIn, upN) || state.getBlock().isLeaves(state, worldIn, upN) || state.getMaterial() == Material.VINE) {
                             this.setBlockAndNotifyAdequately(worldIn, upN, j3 == 1 ? this.stateWood.withProperty(BlockRubberWood.SAP, true) : this.stateWood);
                            if(j3 == 1)
                            {
                                TileEntity tileEntity = worldIn.getTileEntity(upN);

                                if(tileEntity instanceof TileEntityRubberWood)
                                {
                                    ((TileEntityRubberWood)tileEntity).tank.setCapacity(MyrcraftConfig.rubbertree.latex_multiplier * i);
                                    ((TileEntityRubberWood)tileEntity).tank.fillInternal(new FluidStack(Myrcraft.LATEX,MyrcraftConfig.rubbertree.latex_multiplier * i),true);

                                }
                            }
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    public WorldGenRubberTree(boolean notify) {
        super(notify);
        this.minTreeHeight = 5;
        this.stateWood = MyrcraftBlocks.RUBBERWOOD.getDefaultState();
        this.metaLeaves = MyrcraftBlocks.RUBBERLEAVES.getDefaultState();
    }
}
