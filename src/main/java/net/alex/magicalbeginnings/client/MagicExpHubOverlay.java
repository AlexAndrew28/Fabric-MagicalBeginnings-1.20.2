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

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        int text_x = 0;
        int text_y = 0;

        int bar_x = 0;
        int bar_y = 0;
        int bar_u = 0;
        int bar_v = 0;
        int bar_width = 0;
        int bar_height = 0;
        int magic_exp_bar_width = 0;

        MinecraftClient client = MinecraftClient.getInstance();

        TextRenderer fontRenderer = client.textRenderer;

        if (client != null){
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            text_x = (int) (width*0.5);
            text_y = height-46;

            int magicExpLevel = ((IEntityDataSaver) client.player).getPersistentData().getInt("magicLevel");
            int magicExpLevelCurrent = ((IEntityDataSaver) client.player).getPersistentData().getInt("magicLevelCurrent");
            int magicExpLevelNeeded = ((IEntityDataSaver) client.player).getPersistentData().getInt("magicLevelNeeded");


            //String text = "level: " + magicExpLevel + " exp: " + magicExpLevelCurrent + " need: " + magicExpLevelNeeded;

            //drawContext.drawCenteredTextWithShadow(fontRenderer, text, text_x, text_y, textColor);


            if (magicExpLevel != 0){
                String text = "" + magicExpLevel;

                drawContext.drawCenteredTextWithShadow(fontRenderer, text, text_x, text_y, textColor);

                float magicExpPercentage = 0;

                magicExpPercentage = (float) magicExpLevelCurrent / magicExpLevelNeeded;

                bar_x = (int) (width*0.3);
                bar_y = height-50;
                bar_u = 0; //5;
                bar_v = 0; //182;
                bar_width = (int) (width*0.4);
                magic_exp_bar_width =  (int) (bar_width * magicExpPercentage);
                bar_height = 5;

                Identifier BG_TEXTURE = new Identifier(MagicalBeginnings.MOD_ID, "textures/hud/magic_exp_bar_background.png");

                drawContext.drawTexture(BG_TEXTURE, bar_x, bar_y, bar_u, bar_v, bar_width, bar_height, 182, 5);

                Identifier TEXTURE = new Identifier(MagicalBeginnings.MOD_ID, "textures/hud/magic_exp_bar_foreground.png");

                drawContext.drawTexture(TEXTURE, bar_x, bar_y, bar_u, bar_v, magic_exp_bar_width, bar_height, 182, 5);
            }



        }

    }
}
