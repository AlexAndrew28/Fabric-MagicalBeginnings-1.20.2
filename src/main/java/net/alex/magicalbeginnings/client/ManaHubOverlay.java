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

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {

        MinecraftClient client = MinecraftClient.getInstance();

        TextRenderer fontRenderer = client.textRenderer;

        if (client != null){
            // only happen if client exists (this stops crashing when rendering to a client that does not exist)

            // get screen size
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            int textX = (int) (width*0.85);
            int textY = (int) (height*0.85);

            // get current saved mana data
            int currentMana = ((IEntityDataSaver) client.player).getPersistentData().getInt("currentMana");
            int maxMana = ((IEntityDataSaver) client.player).getPersistentData().getInt("maxMana");

            // only display if you have a max mana of above 0
            if (maxMana != 0){

                // number stats
                String text = currentMana + " / " + maxMana;

                int textColor = -156093195;
                drawContext.drawCenteredTextWithShadow(fontRenderer, text, textX, textY, textColor);

                float manaPercentage = (float) currentMana / maxMana;

                int barX = (int) (width*0.75);
                int barY = (int) (height*0.93);
                int barWidth = (int) (width*0.2);
                int manaBarWidth =  (int) (barWidth * manaPercentage);
                int border_width = 1;
                int barHeight = 10;

                // border of the mana bar
                Identifier BORDER_TEXTURE = new Identifier(MagicalBeginnings.MOD_ID, "textures/hud/border_mana_bar.png");
                drawContext.drawTexture(BORDER_TEXTURE, barX- border_width, barY- border_width, 0, 0, barWidth+ border_width *2, barHeight + border_width *2);

                // background of the mana bar
                Identifier BG_TEXTURE = new Identifier(MagicalBeginnings.MOD_ID, "textures/hud/bg_mana_bar.png");
                drawContext.drawTexture(BG_TEXTURE, barX, barY, 0, 0, barWidth, barHeight);

                // foreground of the mana bar
                Identifier TEXTURE = new Identifier(MagicalBeginnings.MOD_ID, "textures/hud/mana_bar.png");
                drawContext.drawTexture(TEXTURE, barX, barY, 0, 0, manaBarWidth, barHeight);
            }

        }

    }
}
