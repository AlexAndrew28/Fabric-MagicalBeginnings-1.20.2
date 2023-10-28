package net.alex.magicalbeginnings.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.alex.magicalbeginnings.MagicalBeginnings;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class RyftImbuingScreen extends HandledScreen<RyftImbuingScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(MagicalBeginnings.MOD_ID, "textures/gui/ryft_imbuing_station_gui.png");
    private static final Identifier ARROW_TEXTURE = new Identifier(MagicalBeginnings.MOD_ID, "textures/gui/ryft_imbuing_station_gui_progress_arrow.png");

    public RyftImbuingScreen(RyftImbuingScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    /**
     * Calls init and moves the title and player inv
     */
    @Override
    protected void init() {
        super.init();
        titleY = 1000;
        playerInventoryTitleY = 1000;
    }

    /**
     * Draws the gui texture for the ryft imbuing station
     *
     * @param context the draw context
     * @param delta delta (not used)
     * @param mouseX x pos of the mouse (not used)
     * @param mouseY y pos of the mouse (not used)
     */
    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {

        // get the texture for the gui
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);

        // get the x and y for the gui
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;


        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderProgressArrow(context, x, y);
    }

    /**
     * Renders the progress arrow based on crafting percentage
     *
     * @param context the draw context
     * @param x x of the gui
     * @param y y of the gui
     */
    private void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isCrafting()) {
            // draw a scaled arrow based on crafting percentage
            context.drawTexture(ARROW_TEXTURE, x+150, y+18, 2,4, 16, handler.getScaledProgress());
        }
    }

    /**
     * Renders the gui
     *
     * @param context the draw context
     * @param mouseX the x pos of the mouse
     * @param mouseY the y pos of the mouse
     * @param delta ?
     */
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
