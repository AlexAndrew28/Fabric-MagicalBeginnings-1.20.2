package net.alex.magicalbeginnings.entity.client;

import net.alex.magicalbeginnings.MagicalBeginnings;
import net.alex.magicalbeginnings.entity.custom.FireballEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.Identifier;

public class FireballRenderer extends ProjectileEntityRenderer<FireballEntity> {
    private static final Identifier TEXTURE = new Identifier(MagicalBeginnings.MOD_ID, "textures/entity/fireball.png");

    public FireballRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(FireballEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(FireballEntity persistentProjectileEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(0.7f, 0.7f, 0.7f);
        super.render(persistentProjectileEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
