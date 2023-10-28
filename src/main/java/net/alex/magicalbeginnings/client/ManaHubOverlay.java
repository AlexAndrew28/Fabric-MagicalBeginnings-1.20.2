package net.alex.magicalbeginnings.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.alex.magicalbeginnings.MagicalBeginnings;
import net.alex.magicalbeginnings.util.IEntityDataSaver;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;

public class ManaHubOverlay implements HudRenderCallback {

    private int textColor = -156093195;

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
        int mana_bar_width;
        float manaPercentage;

        int border_width = 1;

        MinecraftClient client = MinecraftClient.getInstance();

        TextRenderer fontRenderer = client.textRenderer;

        if (client != null){
            // only happen if client exists (this stops crashing when rendering to a client that does not exist)

            // get screen size
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            text_x = (int) (width*0.85);
            text_y = (int) (height*0.85);

            // get current saved mana data
            int currentMana = ((IEntityDataSaver) client.player).getPersistentData().getInt("currentMana");
            int maxMana = ((IEntityDataSaver) client.player).getPersistentData().getInt("maxMana");

            // only display if you have a max mana of above 0
            if (maxMana != 0){

                // number stats
                String text = currentMana + " / " + maxMana;

                drawContext.drawCenteredTextWithShadow(fontRenderer, text, text_x, text_y, textColor);

                manaPercentage = (float) currentMana / maxMana;

                bar_x = (int) (width*0.75);
                bar_y = (int) (height*0.93);
                bar_u = 10;
                bar_v = 10;
                bar_width = (int) (width*0.2);
                mana_bar_width =  (int) (bar_width * manaPercentage);
                bar_height = 10;

                // border of the mana bar
                Identifier BORDER_TEXTURE = new Identifier(MagicalBeginnings.MOD_ID, "textures/hud/border_mana_bar.png");
                drawContext.drawTexture(BORDER_TEXTURE, bar_x-border_width, bar_y-border_width, bar_u, bar_v, bar_width+border_width*2, bar_height+border_width*2);

                // background of the mana bar
                Identifier BG_TEXTURE = new Identifier(MagicalBeginnings.MOD_ID, "textures/hud/bg_mana_bar.png");
                drawContext.drawTexture(BG_TEXTURE, bar_x, bar_y, bar_u, bar_v, bar_width, bar_height);

                // foreground of the mana bar
                Identifier TEXTURE = new Identifier(MagicalBeginnings.MOD_ID, "textures/hud/mana_bar.png");
                drawContext.drawTexture(TEXTURE, bar_x, bar_y, bar_u, bar_v, mana_bar_width, bar_height);
            }

        }

    }
}
