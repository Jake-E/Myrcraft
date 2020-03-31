package io.alwa.myrcraft.world;

import io.alwa.myrcraft.blocks.MyrcraftBlocks;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.SpruceFoliagePlacer;
import net.minecraftforge.common.IPlantable;

import javax.annotation.Nullable;
import java.util.Random;

public class RubberTree extends Tree {

    public static TreeFeatureConfig RUBBER_TREE_CONFIG = null;

    @Nullable
    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random random, boolean b) {
        if (RUBBER_TREE_CONFIG == null) {
            RUBBER_TREE_CONFIG = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(MyrcraftBlocks.RUBBER_WOOD.get().getDefaultState()),
                    new SimpleBlockStateProvider(MyrcraftBlocks.RUBBER_LEAVES.get().getDefaultState()),
                    new SpruceFoliagePlacer(2, 1))).baseHeight(6).heightRandA(3).trunkHeight(1).trunkHeightRandom(1).trunkTopOffsetRandom(2).ignoreVines().setSapling((IPlantable)MyrcraftBlocks.RUBBER_SAPLING.get()).build();
        }
        return Feature.NORMAL_TREE.withConfiguration(RUBBER_TREE_CONFIG);
    }
}
