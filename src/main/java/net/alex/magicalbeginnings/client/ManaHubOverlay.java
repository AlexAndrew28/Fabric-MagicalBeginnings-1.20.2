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
        int text_x = 0;
        int text_y = 0;

        int bar_x = 0;
        int bar_y = 0;
        int bar_u = 0;
        int bar_v = 0;
        int bar_width = 0;
        int bar_height = 0;
        int mana_bar_width = 0;

        int border_width = 1;

        MinecraftClient client = MinecraftClient.getInstance();

        TextRenderer fontRenderer = client.textRenderer;

        if (client != null){
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            text_x = (int) (width*0.85);
            text_y = (int) (height*0.85);

            int currentMana = ((IEntityDataSaver) client.player).getPersistentData().getInt("currentMana");
            int maxMana = ((IEntityDataSaver) client.player).getPersistentData().getInt("maxMana");

            if (maxMana != 0){
                String text = currentMana + " / " + maxMana;

                drawContext.drawCenteredTextWithShadow(fontRenderer, text, text_x, text_y, textColor);

                float manaPercentage = 0;

                manaPercentage = (float) currentMana / maxMana;

                bar_x = (int) (width*0.75);
                bar_y = (int) (height*0.93);
                bar_u = 10;
                bar_v = 10;
                bar_width = (int) (width*0.2);
                mana_bar_width =  (int) (bar_width * manaPercentage);
                bar_height = 10;

                Identifier BORDER_TEXTURE = new Identifier(MagicalBeginnings.MOD_ID, "textures/hud/border_mana_bar.png");

                drawContext.drawTexture(BORDER_TEXTURE, bar_x-border_width, bar_y-border_width, bar_u, bar_v, bar_width+border_width*2, bar_height+border_width*2);

                Identifier BG_TEXTURE = new Identifier(MagicalBeginnings.MOD_ID, "textures/hud/bg_mana_bar.png");

                drawContext.drawTexture(BG_TEXTURE, bar_x, bar_y, bar_u, bar_v, bar_width, bar_height);

                Identifier TEXTURE = new Identifier(MagicalBeginnings.MOD_ID, "textures/hud/mana_bar.png");

                drawContext.drawTexture(TEXTURE, bar_x, bar_y, bar_u, bar_v, mana_bar_width, bar_height);
            }

        }

    }
}
