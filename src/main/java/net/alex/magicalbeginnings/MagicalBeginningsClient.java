package net.alex.magicalbeginnings;

import net.alex.magicalbeginnings.client.MagicExpHubOverlay;
import net.alex.magicalbeginnings.client.ManaHubOverlay;
import net.alex.magicalbeginnings.entity.ModEntities;
import net.alex.magicalbeginnings.entity.client.DigProjectileRenderer;
import net.alex.magicalbeginnings.entity.client.FireballRenderer;
import net.alex.magicalbeginnings.entity.client.ModModelLayers;
import net.alex.magicalbeginnings.entity.client.WaterballRenderer;
import net.alex.magicalbeginnings.networking.ModMessages;
import net.alex.magicalbeginnings.screen.ModScreenHandlers;
import net.alex.magicalbeginnings.screen.RyftImbuingScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class MagicalBeginningsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(ModEntities.FIREBALL, FireballRenderer::new);
        EntityRendererRegistry.register(ModEntities.WATERBALL, WaterballRenderer::new);
        EntityRendererRegistry.register(ModEntities.DIG_PROJECTILE, DigProjectileRenderer::new);

        ModMessages.registerS2CPackets();

        HudRenderCallback.EVENT.register(new ManaHubOverlay());
        HudRenderCallback.EVENT.register(new MagicExpHubOverlay());

        HandledScreens.register(ModScreenHandlers.RYFT_IMBUING_SCREEN_HANDLER, RyftImbuingScreen::new);
    }
}
