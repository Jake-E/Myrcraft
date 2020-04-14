package io.alwa.myrcraft.entity.render;

import io.alwa.myrcraft.Myrcraft;
import io.alwa.myrcraft.entity.BaseAntEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class AntRenderer extends MobRenderer<BaseAntEntity, AntModel<BaseAntEntity>> {
    
    private static final ResourceLocation TEXTURES = new ResourceLocation(Myrcraft.MOD_ID, "/textures/entity/minispider.png");
    
    public AntRenderer(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new AntModel<>(), 1.0F);
    }
    
    @Override
    public ResourceLocation getEntityTexture(BaseAntEntity entity)
    {
        return TEXTURES;
    }
}
