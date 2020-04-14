package io.alwa.myrcraft.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.alwa.myrcraft.entity.BaseAntEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AntModel <T extends BaseAntEntity> extends EntityModel<T> {
    private ModelRenderer bone = null;

    public AntModel() {
        textureWidth = 16;
        textureHeight = 16;

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone.addBox(-9.0F, -5.0F, 4.0F, 3, 3, 3, 0.0F, false);
        bone.addBox(-9.0F, -6.0F, 3.0F, 1, 1, 1, 0.0F, false);
        bone.addBox(-7.0F, -6.0F, 3.0F, 1, 1, 1, 0.0F, false);
        bone.addBox(-9.0F, -3.0F, 7.0F, 3, 2, 3, 0.0F, false);
        bone.addBox( -6.0F, -1.0F, 6.0F, 1, 1, 1, 0.0F, false);
        bone.addBox(-6.0F, -1.0F, 8.0F, 1, 1, 1, 0.0F, false);
        bone.addBox( -6.0F, -1.0F, 10.0F, 1, 1, 1, 0.0F, false);
        bone.addBox(-10.0F, -1.0F, 8.0F, 1, 1, 1, 0.0F, false);
        bone.addBox(-10.0F, -1.0F, 6.0F, 1, 1, 1, 0.0F, false);
        bone.addBox( -10.0F, -1.0F, 10.0F, 1, 1, 1, 0.0F, false);
        bone.addBox(-10.0F, -5.0F, 10.0F, 5, 3, 4, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T t, float v, float v1, float v2, float v3, float v4) {

    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder iVertexBuilder, int i, int i1, float v, float v1, float v2, float v3) {
        bone.render(matrixStack, iVertexBuilder, i, i1, v, v1, v2, v3);
    }
}