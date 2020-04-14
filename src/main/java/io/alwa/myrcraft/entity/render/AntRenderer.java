package io.alwa.myrcraft.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.alwa.myrcraft.Myrcraft;
import io.alwa.myrcraft.entity.BaseAntEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class AntRenderer extends MobRenderer<BaseAntEntity, AntModel<BaseAntEntity>> {
    
    private static final ResourceLocation TEXTURES = new ResourceLocation(Myrcraft.MOD_ID, "/textures/entity/ant.png");
    
    public AntRenderer(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new AntModel<>(), 0.1F);
    }
    
    @Override
    public ResourceLocation getEntityTexture(BaseAntEntity entity)
    {
        return TEXTURES;
    }
    
    @Override
    protected void preRenderCallback(BaseAntEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime)
    {
        //Use this to change the scale
        matrixStackIn.scale(0.4F, 0.4F, 0.4F);
        matrixStackIn.translate(0.0D, 0.0010000000474974513D, 0.0D);
    }
}
