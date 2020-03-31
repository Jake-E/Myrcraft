package io.alwa.myrcraft.client;

import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

public class MyrcraftRenderTypes extends RenderState {
    public MyrcraftRenderTypes(String s, Runnable ra, Runnable rb) {
        super(s, ra, rb);
    }

    public static final RenderType BUCKET_RENDER_TYPE = RenderType.makeType("myrcraft_bucket", DefaultVertexFormats.POSITION_COLOR_TEX_LIGHTMAP, GL11.GL_QUADS, 256, true, false,
            RenderType.State.getBuilder()
            .texture(BLOCK_SHEET)
            .cull(CULL_ENABLED)
            .transparency(TRANSLUCENT_TRANSPARENCY)
            .lightmap(LIGHTMAP_ENABLED)
            .shadeModel(SHADE_ENABLED)
            .build(true));
}
