package io.alwa.myrcraft.blocks;

import io.alwa.myrcraft.world.RubberTree;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.IPlantable;

public class RubberSaplingBlock extends SaplingBlock implements IPlantable {

    public RubberSaplingBlock() {
        super(new RubberTree(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().sound(SoundType.PLANT).hardnessAndResistance(0f));
    }
}
