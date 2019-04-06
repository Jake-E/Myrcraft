package io.alwa.myrcraft;

import io.alwa.myrcraft.blocks.*;
import io.alwa.myrcraft.tiles.TileEntityRubberWood;
import io.alwa.myrcraft.tiles.TileEntityTreeTap;
import io.alwa.myrcraft.tiles.TileEntityWoodBucket;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Myrcraft.MODID)
public class MyrcraftEventHandler
{
    private static Block withName(Block block, String name)
    {
        block.setCreativeTab(Myrcraft.TAB);
        block.setRegistryName(name);
        block.setTranslationKey(Myrcraft.MODID + "." + name);
        return block;
    }

    private static Item withName(Item item, String name)
    {
        item.setCreativeTab(Myrcraft.TAB);
        item.setRegistryName(name);
        item.setTranslationKey(Myrcraft.MODID + "." + name);
        return item;
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        IForgeRegistry<Block> r = event.getRegistry();
        r.register(withName(new BlockRubberWood(), "rubberwood"));
        r.register(withName(new BlockWoodBucket(), "woodbucket"));
        r.register(withName(new BlockTreeTap(), "treetap"));
        r.register(withName(new BlockRubberSapling(), "rubberwoodsapling"));
        r.register(withName(new BlockRubberLeaves(), "rubberleaves"));
        r.register(withName(new BlockFluidLatex(), "latex"));
        r.register(withName(new BlockRubberPlanks(), "rubberplanks"));
        GameRegistry.registerTileEntity(TileEntityWoodBucket.class, new ResourceLocation(Myrcraft.MODID, "woodbucket"));
        GameRegistry.registerTileEntity(TileEntityRubberWood.class, new ResourceLocation(Myrcraft.MODID, "rubberwood"));
        GameRegistry.registerTileEntity(TileEntityTreeTap.class, new ResourceLocation(Myrcraft.MODID, "treetap"));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        IForgeRegistry<Item> r = event.getRegistry();
        r.register(new ItemBlock(MyrcraftBlocks.RUBBERWOOD).setRegistryName("rubberwood"));
        r.register(new ItemBlock(MyrcraftBlocks.WOODBUCKET).setRegistryName("woodbucket"));
        r.register(new ItemBlock(MyrcraftBlocks.TREETAP).setRegistryName("treetap"));
        r.register(new ItemBlock(MyrcraftBlocks.RUBBERWOODSAPLING).setRegistryName("rubberwoodsapling"));
        r.register(new ItemBlock(MyrcraftBlocks.RUBBERLEAVES).setRegistryName("rubberleaves"));
        r.register(new ItemBlock(MyrcraftBlocks.RUBBERPLANKS).setRegistryName("rubberplanks"));
    }
}
