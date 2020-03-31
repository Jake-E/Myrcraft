package io.alwa.myrcraft.tiles.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.alwa.myrcraft.client.MyrcraftRenderTypes;
import io.alwa.myrcraft.tiles.TileEntityWoodBucket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class WoodBucketRenderer extends TileEntityRenderer<TileEntityWoodBucket> {

    public WoodBucketRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
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

        int la = ((combinedLightIn >> 16 & 0xFFFF) * 3 + 240) / 4;
        int lb = ((combinedOverlayIn & 0xFFFF) * 3 * 3 + 240) / 4;

        Matrix4f m = matrixStack.getLast().getMatrix();

        IVertexBuilder buffer = Minecraft.getInstance().getRenderTypeBuffers().getBufferSource().getBuffer(MyrcraftRenderTypes.BUCKET_RENDER_TYPE);

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

        if(fluidLevel >= 0.18999999999990003 && fluidLevel <= 0.37187500000000007D){
            o0 = 3.00F / 16F;
            o1 = 13.00F / 16F;
        }
        if(fluidLevel >= 0.37187500000000007){
            o0 = 3.00F / 16F;
            o1 = 13.00F / 16F;

            // West
            buffer.pos(m, 2F/16F, fluidLevel, 4F/16F).color(r, g, b, a).tex(u0 + uo, v0 + vo).lightmap(la, lb).endVertex();
            buffer.pos(m, 2F/16F, fluidLevel, 12F/16F).color(r, g, b, a).tex(u1 - uo, v0 + vo).lightmap(la, lb).endVertex();
            buffer.pos(m, 3F/16F, fluidLevel, 12F/16F).color(r, g, b, a).tex(u1 - uo, v1 - vo).lightmap(la, lb).endVertex();
            buffer.pos(m, 3F/16F, fluidLevel, 4F/16F).color(r, g, b, a).tex(u0 + uo, v1 - vo).lightmap(la, lb).endVertex();

            // South
            buffer.pos(m, 4F/16F, fluidLevel, 13F/16F).color(r, g, b, a).tex(u0 + uo, v0 + vo).lightmap(la, lb).endVertex();
            buffer.pos(m, 4F/16F, fluidLevel, 14F/16F).color(r, g, b, a).tex(u1 - uo, v0 + vo).lightmap(la, lb).endVertex();
            buffer.pos(m, 12F/16F, fluidLevel, 14F/16F).color(r, g, b, a).tex(u1 - uo, v1 - vo).lightmap(la, lb).endVertex();
            buffer.pos(m, 12F/16F, fluidLevel, 13F/16F).color(r, g, b, a).tex(u0 + uo, v1 - vo).lightmap(la, lb).endVertex();

            // East
            buffer.pos(m, 13F/16F, fluidLevel, 4F/16F).color(r, g, b, a).tex(u0 + uo, v0 + vo).lightmap(la, lb).endVertex();
            buffer.pos(m, 13F/16F, fluidLevel, 12F/16F).color(r, g, b, a).tex(u1 - uo, v0 + vo).lightmap(la, lb).endVertex();
            buffer.pos(m, 14F/16F, fluidLevel, 12F/16F).color(r, g, b, a).tex(u1 - uo, v1 - vo).lightmap(la, lb).endVertex();
            buffer.pos(m, 14F/16F, fluidLevel, 4F/16F).color(r, g, b, a).tex(u0 + uo, v1 - vo).lightmap(la, lb).endVertex();

            // North
            buffer.pos(m, 4F/16F, fluidLevel, 2F/16F).color(r, g, b, a).tex(u0 + uo, v0 + vo).lightmap(la, lb).endVertex();
            buffer.pos(m, 4F/16F, fluidLevel, 3F/16F).color(r, g, b, a).tex(u1 - uo, v0 + vo).lightmap(la, lb).endVertex();
            buffer.pos(m, 12F/16F, fluidLevel, 3F/16F).color(r, g, b, a).tex(u1 - uo, v1 - vo).lightmap(la, lb).endVertex();
            buffer.pos(m, 12F/16F, fluidLevel, 2F/16F).color(r, g, b, a).tex(u0 + uo, v1 - vo).lightmap(la, lb).endVertex();
        }
        //UP
        if (fluidLevel < 1D)
        {
            buffer.pos(m, o0, fluidLevel, o0).color(r, g, b, a).tex(u0 + uo, v0 + vo).lightmap(la, lb).endVertex();
            buffer.pos(m, o0, fluidLevel, o1).color(r, g, b, a).tex(u1 - uo, v0 + vo).lightmap(la, lb).endVertex();
            buffer.pos(m, o1, fluidLevel, o1).color(r, g, b, a).tex(u1 - uo, v1 - vo).lightmap(la, lb).endVertex();
            buffer.pos(m, o1, fluidLevel, o0).color(r, g, b, a).tex(u0 + uo, v1 - vo).lightmap(la, lb).endVertex();
        }

        Minecraft.getInstance().getRenderTypeBuffers().getBufferSource().finish(MyrcraftRenderTypes.BUCKET_RENDER_TYPE);

    }
}
