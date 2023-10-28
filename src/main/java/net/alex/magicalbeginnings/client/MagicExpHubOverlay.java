package net.alex.magicalbeginnings.client;

import net.alex.magicalbeginnings.MagicalBeginnings;
import net.alex.magicalbeginnings.util.IEntityDataSaver;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

public class MagicExpHubOverlay implements HudRenderCallback {

    private int textColor = -156093195;

    /**
     * Called to render the magic exp bar
     *
     * @param drawContext the {@link DrawContext} instance
     * @param tickDelta Progress for linearly interpolating between the previous and current game state
     */
    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {

        int text_x;
        int text_y;
        int bar_x;
        int bar_y;
        int bar_u;
        int bar_v;
        int bar_width;
        int bar_height;
        int magic_exp_bar_width;

        MinecraftClient client = MinecraftClient.getInstance();

        TextRenderer fontRenderer = client.textRenderer;

        if (client != null){
            // only run if client exists

            // get the screen size
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            text_x = (int) (width*0.5);
            text_y = height-46;

            // get the current magic exp level data
            int magicExpLevel = ((IEntityDataSaver) client.player).getPersistentData().getInt("magicLevel");
            int magicExpLevelCurrent = ((IEntityDataSaver) client.player).getPersistentData().getInt("magicLevelCurrent");
            int magicExpLevelNeeded = ((IEntityDataSaver) client.player).getPersistentData().getInt("magicLevelNeeded");

            // don't render if the magic level is 0
            if (magicExpLevel != 0){
                String text = "" + magicExpLevel;

                // draw the magic level as text on the screen
                drawContext.drawCenteredTextWithShadow(fontRenderer, text, text_x, text_y, textColor);

                float magicExpPercentage = 0;

                magicExpPercentage = (float) magicExpLevelCurrent / magicExpLevelNeeded;

                bar_x = (int) (width*0.3);
                bar_y = height-50;
                bar_u = 0;
                bar_v = 0;
                bar_width = (int) (width*0.4);
                magic_exp_bar_width =  (int) (bar_width * magicExpPercentage);
                bar_height = 5;

                // draw the background of the exp bar
                Identifier BG_TEXTURE = new Identifier(MagicalBeginnings.MOD_ID, "textures/hud/magic_exp_bar_background.png");
                drawContext.drawTexture(BG_TEXTURE, bar_x, bar_y, bar_u, bar_v, bar_width, bar_height, 182, 5);

                // draw the foreground of the exp bar (length depends on amount of exp towards next level)
                Identifier TEXTURE = new Identifier(MagicalBeginnings.MOD_ID, "textures/hud/magic_exp_bar_foreground.png");
                drawContext.drawTexture(TEXTURE, bar_x, bar_y, bar_u, bar_v, magic_exp_bar_width, bar_height, 182, 5);
            }

        }

    }
}
