package io.alwa.myrcraft.proxy;

import io.alwa.myrcraft.blocks.MyrcraftBlocks;
import io.alwa.myrcraft.tiles.TileEntityRubberWood;
import io.alwa.myrcraft.tiles.render.WoodBucketRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy
{
    @Override
    public void registerRenders()
    {
        RenderTypeLookup.setRenderLayer(MyrcraftBlocks.RUBBER_SAPLING.get(), RenderType.getCutout());
        ClientRegistry.bindTileEntityRenderer(MyrcraftBlocks.WOODEN_BUCKET_TILE.get(), WoodBucketRenderer::new);
    }
}
