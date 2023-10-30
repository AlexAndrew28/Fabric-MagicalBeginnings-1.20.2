package net.alex.magicalbeginnings.client;

import net.alex.magicalbeginnings.MagicalBeginnings;
import net.alex.magicalbeginnings.util.IEntityDataSaver;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

public class MagicExpHubOverlay implements HudRenderCallback {

    /**
     * Called to render the magic exp bar
     *
     * @param drawContext the {@link DrawContext} instance
     * @param tickDelta Progress for linearly interpolating between the previous and current game state
     */
    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {

        MinecraftClient client = MinecraftClient.getInstance();

        TextRenderer fontRenderer = client.textRenderer;

        if (client != null){
            // only run if client exists

            // get the screen size
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            int textX = (int) (width*0.5);
            int textY = height-46;

            // get the current magic exp level data
            int magicExpLevel = ((IEntityDataSaver) client.player).getPersistentData().getInt("magicLevel");
            int magicExpLevelCurrent = ((IEntityDataSaver) client.player).getPersistentData().getInt("magicLevelCurrent");
            int magicExpLevelNeeded = ((IEntityDataSaver) client.player).getPersistentData().getInt("magicLevelNeeded");

            // don't render if the magic level is 0
            if (magicExpLevel != 0){
                String text = "" + magicExpLevel;

                // draw the magic level as text on the screen
                int textColor = -156093195;
                drawContext.drawCenteredTextWithShadow(fontRenderer, text, textX, textY, textColor);

                float magicExpPercentage = (float) magicExpLevelCurrent / magicExpLevelNeeded;

                int barX = (int) (width*0.3);
                int barY = height-50;
                int barWidth = (int) (width*0.4);
                int magicExpBarWidth =  (int) (barWidth * magicExpPercentage);
                int barHeight = 5;

                // draw the background of the exp bar
                Identifier BG_TEXTURE = new Identifier(MagicalBeginnings.MOD_ID, "textures/hud/magic_exp_bar_background.png");
                drawContext.drawTexture(BG_TEXTURE, barX, barY, 0, 0, barWidth, barHeight, 182, 5);

                // draw the foreground of the exp bar (length depends on amount of exp towards next level)
                Identifier TEXTURE = new Identifier(MagicalBeginnings.MOD_ID, "textures/hud/magic_exp_bar_foreground.png");
                drawContext.drawTexture(TEXTURE, barX, barY, 0, 0, magicExpBarWidth, barHeight, 182, 5);
            }

        }

    }
}
