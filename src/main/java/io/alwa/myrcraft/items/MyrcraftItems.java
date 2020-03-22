package io.alwa.myrcraft.items;

import io.alwa.myrcraft.Myrcraft;
import io.alwa.myrcraft.blocks.MyrcraftBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MyrcraftItems
{
    public static final Item.Properties ITEM_GROUP = new Item.Properties().group(Myrcraft.itemGroup);
    
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Myrcraft.MOD_ID);
    
    public static final RegistryObject<Item> RUBBER_WOOD_ITEM = ITEMS.register("rubberwood", () -> new BlockItem(MyrcraftBlocks.RUBBER_WOOD.get(), ITEM_GROUP));
    public static final RegistryObject<Item> RUBBER_PLANKS = ITEMS.register("rubberplanks", () -> new BlockItem(MyrcraftBlocks.RUBBER_PLANKS.get(), ITEM_GROUP));
    public static final RegistryObject<Item> RUBBER_SAPLING = ITEMS.register("rubberwoodsapling", () -> new BlockItem(MyrcraftBlocks.RUBBER_SAPLING.get(), ITEM_GROUP));
    public static final RegistryObject<Item> WOODEN_BUCKET_ITEM = ITEMS.register("woodbucket", () -> new BlockItem(MyrcraftBlocks.WOODEN_BUCKET.get(), ITEM_GROUP));
    public static final RegistryObject<Item> TREE_TAP = ITEMS.register("treetap", () -> new BlockItem(MyrcraftBlocks.TREE_TAP.get(), ITEM_GROUP));
    
}
