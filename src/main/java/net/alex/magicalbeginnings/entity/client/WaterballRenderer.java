package net.alex.magicalbeginnings.entity.client;

import net.alex.magicalbeginnings.MagicalBeginnings;
import net.alex.magicalbeginnings.entity.custom.FireballEntity;
import net.alex.magicalbeginnings.entity.custom.WaterballEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class WaterballRenderer extends ProjectileEntityRenderer<WaterballEntity> {
    private static final Identifier TEXTURE = new Identifier(MagicalBeginnings.MOD_ID, "textures/entity/waterball.png");

    public WaterballRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(WaterballEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(WaterballEntity persistentProjectileEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(1f, 1f, 1f);
        super.render(persistentProjectileEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
