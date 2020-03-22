package io.alwa.myrcraft.blocks;

import io.alwa.myrcraft.Myrcraft;
import io.alwa.myrcraft.tiles.TileEntityRubberWood;
import io.alwa.myrcraft.tiles.TileEntityTreeTap;
import io.alwa.myrcraft.tiles.TileEntityWoodBucket;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

public class MyrcraftBlocks {
    
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Myrcraft.MOD_ID);
    public static final DeferredRegister<TileEntityType<?>> TILES_ENTITIES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, Myrcraft.MOD_ID);
    
    public static final RegistryObject<Block> RUBBER_WOOD = BLOCKS.register("rubberwood", RubberWoodBlock::new);
    public static final RegistryObject<Block> RUBBER_PLANKS = BLOCKS.register("rubberplanks", RubberPlanksBlock::new);
    public static final RegistryObject<Block> RUBBER_SAPLING = BLOCKS.register("rubberwoodsapling", RubberSaplingBlock::new);
    public static final RegistryObject<Block> WOODEN_BUCKET = BLOCKS.register("woodbucket", WoodBucketBlock::new);
    public static final RegistryObject<Block> TREE_TAP = BLOCKS.register("treetap", TreeTapBlock::new);
    
    
    public static final RegistryObject<TileEntityType<TileEntityWoodBucket>> WOODEN_BUCKET_TILE =
            TILES_ENTITIES.register("woodbucket", () -> TileEntityType.Builder.create(TileEntityWoodBucket::new, MyrcraftBlocks.WOODEN_BUCKET.get()).build(null));
    
    public static final RegistryObject<TileEntityType<TileEntityTreeTap>> TREE_TAP_TILE =
            TILES_ENTITIES.register("treetap", () -> TileEntityType.Builder.create(TileEntityTreeTap::new, MyrcraftBlocks.TREE_TAP.get()).build(null));
    
    public static final RegistryObject<TileEntityType<TileEntityRubberWood>> RUBBER_WOOD_TILE =
            TILES_ENTITIES.register("rubberwood", () -> TileEntityType.Builder.create(TileEntityRubberWood::new, MyrcraftBlocks.RUBBER_WOOD.get()).build(null));
    
}
