package io.alwa.myrcraft.tiles.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.alwa.myrcraft.tiles.TileEntityWoodBucket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.world.World;

public class WoodBucketRenderer extends TileEntityRenderer<TileEntityWoodBucket> {

    public WoodBucketRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    private interface LevelRenderer
    {
        void vertex(float x, float z, float u, float v);
    }

    @Override
    public void render(TileEntityWoodBucket bucket, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int combinedLightIn, int combinedOverlayIn) {
        World world = bucket.getWorld();
        int amount = bucket.tank.getFluidAmount();

        if(amount <= 0) return;

        float o0 = 4.00F / 16F;
        float o1 = 12.00F / 16F;

        float y0 = 1.10F / 16F;
        float y11 = 10.8F / 16F;

        float fluidLevel = y0 + ((y11 - y0) * amount / (float) bucket.tank.getCapacity());

        Matrix4f m = matrixStack.getLast().getMatrix();
        Matrix3f n = matrixStack.getLast().getNormal();

        IVertexBuilder buffer = iRenderTypeBuffer.getBuffer(Atlases.getTranslucentBlockType());

        TextureAtlasSprite sprite = Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(bucket.tank.getFluid().getFluid().getAttributes().getStillTexture(bucket.tank.getFluid()));

        float u0 = sprite.getMinU();
        float v0 = sprite.getMinV();
        float u1 = sprite.getMaxU();
        float v1 = sprite.getMaxV();
        float uo = (u1 - u0) / 16F;
        float vo = (v1 - v0) / 16F;

        int color = bucket.tank.getFluid().getFluid().getAttributes().getColor(bucket.tank.getFluid());
        int a = (color >> 24) & 0xFF;
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = color & 0xFF;

        //add(POSITION_3F).add(COLOR_4UB).add(TEX_2F).add(TEX_2S).add(TEX_2SB).add(NORMAL_3B).add(PADDING_1B).build());
        LevelRenderer renderer = (x, z, u, v) -> buffer.pos(m, x, fluidLevel, z).color(r, g, b, a).tex(u, v).overlay(combinedOverlayIn).lightmap(combinedLightIn).normal(n, 0, 1, 0).endVertex();

        if(fluidLevel >= 0.18999999999990003 && fluidLevel <= 0.37187500000000007D){
            o0 = 3.00F / 16F;
            o1 = 13.00F / 16F;
        }
        if(fluidLevel >= 0.37187500000000007){
            o0 = 3.00F / 16F;
            o1 = 13.00F / 16F;

            // West
            renderer.vertex(2F/16F, 4F/16F, u0 + uo, v0 + vo);
            renderer.vertex(2F/16F, 12F/16F, u1 - uo, v0 + vo);
            renderer.vertex(3F/16F, 12F/16F, u1 - uo, v1 - vo);
            renderer.vertex(3F/16F, 4F/16F, u0 + uo, v1 - vo);

            // South
            renderer.vertex(4F/16F, 13F/16F, u0 + uo, v0 + vo);
            renderer.vertex(4F/16F, 14F/16F, u1 - uo, v0 + vo);
            renderer.vertex(12F/16F, 14F/16F, u1 - uo, v1 - vo);
            renderer.vertex(12F/16F, 13F/16F, u0 + uo, v1 - vo);

            // East
            renderer.vertex(13F/16F, 4F/16F, u0 + uo, v0 + vo);
            renderer.vertex(13F/16F, 12F/16F, u1 - uo, v0 + vo);
            renderer.vertex(14F/16F, 12F/16F, u1 - uo, v1 - vo);
            renderer.vertex(14F/16F, 4F/16F, u0 + uo, v1 - vo);

            // North
            renderer.vertex(4F/16F, 2F/16F, u0 + uo, v0 + vo);
            renderer.vertex(4F/16F, 3F/16F, u1 - uo, v0 + vo);
            renderer.vertex(12F/16F, 3F/16F, u1 - uo, v1 - vo);
            renderer.vertex(12F/16F, 2F/16F, u0 + uo, v1 - vo);
        }
        //UP
        if (fluidLevel < 1D)
        {
            renderer.vertex(o0, o0, u0 + uo, v0 + vo);
            renderer.vertex(o0, o1, u1 - uo, v0 + vo);
            renderer.vertex(o1, o1, u1 - uo, v1 - vo);
            renderer.vertex(o1, o0, u0 + uo, v1 - vo);
        }

    }
}