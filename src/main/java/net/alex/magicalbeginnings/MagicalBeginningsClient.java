package net.alex.magicalbeginnings;

import net.alex.magicalbeginnings.client.MagicExpHubOverlay;
import net.alex.magicalbeginnings.client.ManaHubOverlay;
import net.alex.magicalbeginnings.entity.ModEntities;
import net.alex.magicalbeginnings.entity.client.*;
import net.alex.magicalbeginnings.networking.ModMessages;
import net.alex.magicalbeginnings.screen.ModScreenHandlers;
import net.alex.magicalbeginnings.screen.RyftImbuingScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class MagicalBeginningsClient implements ClientModInitializer {

    /**
     * Runs all the initialises that are required on the client side
     */
    @Override
    public void onInitializeClient() {

        // register entities
        EntityRendererRegistry.register(ModEntities.FIREBALL, FireballRenderer::new);
        EntityRendererRegistry.register(ModEntities.WATERBALL, WaterballRenderer::new);
        EntityRendererRegistry.register(ModEntities.DIG_PROJECTILE, DigProjectileRenderer::new);
        EntityRendererRegistry.register(ModEntities.LOCATION_MARK_ORB, LocationMarkOrbRenderer::new);
        EntityRendererRegistry.register(ModEntities.TELEPORT_ORB, TeleportOrbRenderer::new);

        ModMessages.registerS2CPackets();

        // register hud changes
        HudRenderCallback.EVENT.register(new ManaHubOverlay());
        HudRenderCallback.EVENT.register(new MagicExpHubOverlay());

        // register block screens
        HandledScreens.register(ModScreenHandlers.RYFT_IMBUING_SCREEN_HANDLER, RyftImbuingScreen::new);
    }
}
