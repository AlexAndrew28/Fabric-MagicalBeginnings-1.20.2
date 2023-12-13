package net.alex.magicalbeginnings.entity.client;

import net.alex.magicalbeginnings.MagicalBeginnings;
import net.alex.magicalbeginnings.entity.custom.DigProjectileEntity;
import net.alex.magicalbeginnings.entity.custom.WaterballEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class DigProjectileRenderer extends ProjectileEntityRenderer<DigProjectileEntity> {
    private static final Identifier TEXTURE = new Identifier(MagicalBeginnings.MOD_ID, "textures/entity/earthball.png");

    public DigProjectileRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    /**
     * Gets the texture of the entity
     *
     * @param entity entity to get texture of
     *
     * @return the texture
     */
    @Override
    public Identifier getTexture(DigProjectileEntity entity) {
        return TEXTURE;
    }

    /**
     * Method to render the entity
     *
     * @param persistentProjectileEntity the entity
     * @param f f
     * @param g g
     * @param matrixStack scale the entity's texture
     * @param vertexConsumerProvider a vertex provider
     * @param i i
     */
    @Override
    public void render(DigProjectileEntity persistentProjectileEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(1f, 1f, 1f);
        super.render(persistentProjectileEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
