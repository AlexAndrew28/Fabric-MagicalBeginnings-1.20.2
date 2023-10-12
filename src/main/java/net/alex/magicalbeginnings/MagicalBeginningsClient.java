package net.alex.magicalbeginnings;

import net.alex.magicalbeginnings.entity.ModEntities;
import net.alex.magicalbeginnings.entity.client.FireballRenderer;
import net.alex.magicalbeginnings.entity.client.ModModelLayers;
import net.alex.magicalbeginnings.entity.client.WaterballRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class MagicalBeginningsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(ModEntities.FIREBALL, FireballRenderer::new);
        EntityRendererRegistry.register(ModEntities.WATERBALL, WaterballRenderer::new);
        //EntityModelLayerRegistry.registerModelLayer(ModModelLayers.FIREBALL, FireballModel::getTexturedModelData);

    }
}
