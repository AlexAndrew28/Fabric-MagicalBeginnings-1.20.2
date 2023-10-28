package net.alex.magicalbeginnings.mixin;

import net.alex.magicalbeginnings.MagicalBeginnings;
import net.alex.magicalbeginnings.item.ModItems;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    /**
     * Overrides the standard item renderer to allow for fancy 3d models for the ruby wand
     *
     * @param value a baked model
     * @param stack the item
     * @param renderMode the mode to render in
     * @param leftHanded what hand is the item being used in
     * @param matrices graphic matrix
     * @param vertexConsumers vertex matrix
     * @param light integer representing the light
     * @param overlay integer representing the overlay
     * @return a baked model
     */
    @ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
    public BakedModel useRubyWandModel(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (stack.isOf(ModItems.RUBY_WAND) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(MagicalBeginnings.MOD_ID, "ruby_wand_3d", "inventory"));
        }
        return value;
    }

    /**
     * Overrides the standard item renderer to allow for fancy 3d models for the sapphire wand
     *
     * @param value a baked model
     * @param stack the item
     * @param renderMode the mode to render in
     * @param leftHanded what hand is the item being used in
     * @param matrices graphic matrix
     * @param vertexConsumers vertex matrix
     * @param light integer representing the light
     * @param overlay integer representing the overlay
     * @return a baked model
     */
    public BakedModel useSapphireWandModel(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (stack.isOf(ModItems.SAPPHIRE_WAND) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(MagicalBeginnings.MOD_ID, "sapphire_wand_3d", "inventory"));
        }
        return value;
    }
}