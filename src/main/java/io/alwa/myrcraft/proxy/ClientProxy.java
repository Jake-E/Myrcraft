package io.alwa.myrcraft.proxy;

import io.alwa.myrcraft.blocks.MyrcraftBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class ClientProxy extends CommonProxy
{
    @Override
    public void registerRenders()
    {
        RenderTypeLookup.setRenderLayer(MyrcraftBlocks.RUBBER_SAPLING.get(), RenderType.getCutout());
    }
}
