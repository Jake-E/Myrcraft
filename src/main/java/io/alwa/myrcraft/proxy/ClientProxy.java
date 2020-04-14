package io.alwa.myrcraft.proxy;

import io.alwa.myrcraft.blocks.MyrcraftBlocks;
import io.alwa.myrcraft.entity.MyrcraftEntities;
import io.alwa.myrcraft.entity.render.AntRenderer;
import io.alwa.myrcraft.tiles.render.WoodBucketRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenders() {
        RenderTypeLookup.setRenderLayer(MyrcraftBlocks.RUBBER_SAPLING.get(), RenderType.getCutout());
        ClientRegistry.bindTileEntityRenderer(MyrcraftBlocks.WOODEN_BUCKET_TILE.get(), WoodBucketRenderer::new);
    
        RenderingRegistry.registerEntityRenderingHandler(MyrcraftEntities.ANT.get(), AntRenderer::new);
    }
}
