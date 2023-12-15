package net.alex.magicalbeginnings.entity.client;

import net.alex.magicalbeginnings.MagicalBeginnings;
import net.alex.magicalbeginnings.entity.custom.LocationMarkOrbEntity;
import net.alex.magicalbeginnings.entity.custom.WaterballEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class LocationMarkOrbRenderer extends ProjectileEntityRenderer<LocationMarkOrbEntity> {
    private static final Identifier TEXTURE = new Identifier(MagicalBeginnings.MOD_ID, "textures/entity/teleport_orb.png");

    public LocationMarkOrbRenderer(EntityRendererFactory.Context context) {
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
    public Identifier getTexture(LocationMarkOrbEntity entity) {
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
    public void render(LocationMarkOrbEntity persistentProjectileEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(1f, 1f, 1f);
        super.render(persistentProjectileEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
