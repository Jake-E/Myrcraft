package io.alwa.myrcraft;

import io.alwa.myrcraft.blocks.BlockFluidLatex;
import io.alwa.myrcraft.blocks.BlockRubberWood;
import io.alwa.myrcraft.blocks.MyrcraftBlocks;
import io.alwa.myrcraft.items.MyrcraftItems;
import io.alwa.myrcraft.tiles.TileEntityWoodBucket;
import io.alwa.myrcraft.tiles.render.RendererWoodBucket;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = Myrcraft.MODID, value = Side.CLIENT)
public class MyrcraftClientEventHandler {

    private static void addModel(Item item, String variant) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), variant));
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        addModel(MyrcraftItems.RUBBERWOOD, "axis=y");
        addModel(MyrcraftItems.WOODBUCKET, "normal");
        addModel(MyrcraftItems.TREETAP, "facing=north");
        addModel(MyrcraftItems.RUBBERWOODSAPLING, "inventory");
        addModel(MyrcraftItems.RUBBERLEAVES, "normal");
        addModel(MyrcraftItems.RUBBERPLANKS, "normal");

        ModelLoader.setCustomStateMapper(MyrcraftBlocks.RUBBERLEAVES, new StateMap.Builder().ignore(BlockLeaves.CHECK_DECAY, BlockLeaves.DECAYABLE).build());
        ModelLoader.setCustomStateMapper(MyrcraftBlocks.RUBBERWOOD, new StateMap.Builder().ignore(BlockRubberWood.SAP).build());
        ModelLoader.setCustomStateMapper(MyrcraftBlocks.LATEX, new StateMap.Builder().ignore(BlockFluidLatex.LEVEL).build());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWoodBucket.class, new RendererWoodBucket());

    }

}
