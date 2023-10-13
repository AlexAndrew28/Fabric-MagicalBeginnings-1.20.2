package net.alex.magicalbeginnings.client;

import com.mojang.blaze3d.systems.RenderSystem;
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
        int x = 0;
        int y = 0;

        MinecraftClient client = MinecraftClient.getInstance();

        TextRenderer fontRenderer = client.textRenderer;

        if (client != null){
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            x = (int) (width*0.8);
            y = (int) (height*0.95);

            //drawContext.fill(x1, y1, x2, y2, 5);
            //drawContext.fill(4, 4, 400, 400, 50);

            int currentMana = ((IEntityDataSaver) client.player).getPersistentData().getInt("currentMana");
            int maxMana = ((IEntityDataSaver) client.player).getPersistentData().getInt("maxMana");

            String text = currentMana + " / " + maxMana;

            drawContext.drawCenteredTextWithShadow(fontRenderer, text, x, y, textColor);

        }



    }
}
