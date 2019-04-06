package io.alwa.myrcraft.tiles.render;

import io.alwa.myrcraft.tiles.TileEntityWoodBucket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class RendererWoodBucket extends TileEntitySpecialRenderer<TileEntityWoodBucket> {
    @Override
    public void render(TileEntityWoodBucket tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        int amount = tile.tank.getFluidAmount();

        if (amount <= 0)
        {
            return;
        }

        RenderHelper.disableStandardItemLighting();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

        double o0 = 4.00D / 16D;
        double o1 = 12.00D / 16D;

        double y0 = 1.10D / 16D;
        double y11 = 10.8D / 16D;

        double fluidLevel = y0 + ((y11 - y0) * amount / (double) tile.tank.getCapacity());

        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        GlStateManager.glNormal3f(0F, 1F, 0F);
        GlStateManager.translate(0.5F, 0.5F, 0.5F);
        GlStateManager.rotate(180F, 0F, 0F, 1F);
        GlStateManager.rotate(180F, 1F, 0F, 0F);
        GlStateManager.translate(-0.5F, -0.5F, -0.5F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(tile.tank.getFluid().getFluid().getStill(tile.tank.getFluid()).toString());

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);

        double u0 = sprite.getMinU();
        double v0 = sprite.getMinV();
        double u1 = sprite.getMaxU();
        double v1 = sprite.getMaxV();
        double uo = (u1 - u0) / 16D;
        double vo = (v1 - v0) / 16D;

        int color = tile.tank.getFluid().getFluid().getColor(tile.tank.getFluid());
        int a = (color >> 24) & 0xFF;
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = color & 0xFF;

        if(fluidLevel >= 0.18999999999990003 && fluidLevel <= 0.37187500000000007D){
            o0 = 3.00D / 16D;
            o1 = 13.00D / 16D;
        }
        if(fluidLevel >= 0.37187500000000007){
            o0 = 3.00D / 16D;
            o1 = 13.00D / 16D;

            // West
            buffer.pos(2D/16D, fluidLevel, 4D/16D).tex(u0 + uo, v0 + vo).color(r, g, b, a).endVertex();
            buffer.pos(2D/16D, fluidLevel, 12D/16D).tex(u1 - uo, v0 + vo).color(r, g, b, a).endVertex();
            buffer.pos(3D/16D, fluidLevel, 12D/16D).tex(u1 - uo, v1 - vo).color(r, g, b, a).endVertex();
            buffer.pos(3D/16D, fluidLevel, 4D/16D).tex(u0 + uo, v1 - vo).color(r, g, b, a).endVertex();

            // South
            buffer.pos(4D/16D, fluidLevel, 13D/16D).tex(u0 + uo, v0 + vo).color(r, g, b, a).endVertex();
            buffer.pos(4D/16D, fluidLevel, 14D/16D).tex(u1 - uo, v0 + vo).color(r, g, b, a).endVertex();
            buffer.pos(12D/16D, fluidLevel, 14D/16D).tex(u1 - uo, v1 - vo).color(r, g, b, a).endVertex();
            buffer.pos(12D/16D, fluidLevel, 13D/16D).tex(u0 + uo, v1 - vo).color(r, g, b, a).endVertex();

            // East
            buffer.pos(13D/16D, fluidLevel, 4D/16D).tex(u0 + uo, v0 + vo).color(r, g, b, a).endVertex();
            buffer.pos(13D/16D, fluidLevel, 12D/16D).tex(u1 - uo, v0 + vo).color(r, g, b, a).endVertex();
            buffer.pos(14D/16D, fluidLevel, 12D/16D).tex(u1 - uo, v1 - vo).color(r, g, b, a).endVertex();
            buffer.pos(14D/16D, fluidLevel, 4D/16D).tex(u0 + uo, v1 - vo).color(r, g, b, a).endVertex();

            // North
            buffer.pos(4D/16D, fluidLevel, 2D/16D).tex(u0 + uo, v0 + vo).color(r, g, b, a).endVertex();
            buffer.pos(4D/16D, fluidLevel, 3D/16D).tex(u1 - uo, v0 + vo).color(r, g, b, a).endVertex();
            buffer.pos(12D/16D, fluidLevel, 3D/16D).tex(u1 - uo, v1 - vo).color(r, g, b, a).endVertex();
            buffer.pos(12D/16D, fluidLevel, 2D/16D).tex(u0 + uo, v1 - vo).color(r, g, b, a).endVertex();
        }
        //UP
        if (fluidLevel < 1D)
        {
            buffer.pos(o0, fluidLevel, o0).tex(u0 + uo, v0 + vo).color(r, g, b, a).endVertex();
            buffer.pos(o0, fluidLevel, o1).tex(u1 - uo, v0 + vo).color(r, g, b, a).endVertex();
            buffer.pos(o1, fluidLevel, o1).tex(u1 - uo, v1 - vo).color(r, g, b, a).endVertex();
            buffer.pos(o1, fluidLevel, o0).tex(u0 + uo, v1 - vo).color(r, g, b, a).endVertex();
        }

        tessellator.draw();
        GlStateManager.popMatrix();
        RenderHelper.enableStandardItemLighting();
    }
}
