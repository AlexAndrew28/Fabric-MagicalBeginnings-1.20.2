package net.alex.magicalbeginnings.mixin;

import net.alex.magicalbeginnings.MagicalBeginnings;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

@Mixin(ModelLoader.class)
public abstract class ModelLoaderMixin {
    @Shadow
    protected abstract void addModel(ModelIdentifier modelId);

    /**
     * Overrides the model loader for the ruby wand
     *
     * @param blockColors the colours
     * @param profiler a profiler
     * @param jsonUnbakedModels unbaked model
     * @param blockStates the state
     * @param ci callback information
     */
    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/model/ModelLoader;addModel(Lnet/minecraft/client/util/ModelIdentifier;)V", ordinal = 3, shift = At.Shift.AFTER))
    public void addRubyWand(BlockColors blockColors, Profiler profiler, Map<Identifier, JsonUnbakedModel> jsonUnbakedModels, Map<Identifier, List<ModelLoader.SourceTrackedData>> blockStates, CallbackInfo ci) {
        this.addModel(new ModelIdentifier(MagicalBeginnings.MOD_ID, "ruby_wand_3d", "inventory"));
    }

    /**
     * Overrides the model loader for the sapphire wand
     *
     * @param blockColors the colours
     * @param profiler a profiler
     * @param jsonUnbakedModels unbaked model
     * @param blockStates the state
     * @param ci callback information
     */
    public void addSapphireWand(BlockColors blockColors, Profiler profiler, Map<Identifier, JsonUnbakedModel> jsonUnbakedModels, Map<Identifier, List<ModelLoader.SourceTrackedData>> blockStates, CallbackInfo ci) {
        this.addModel(new ModelIdentifier(MagicalBeginnings.MOD_ID, "sapphire_wand_3d", "inventory"));
    }

    /**
     * Overrides the model loader for the sky amulet
     *
     * @param blockColors the colours
     * @param profiler a profiler
     * @param jsonUnbakedModels unbaked model
     * @param blockStates the state
     * @param ci callback information
     */
    public void addSkyAmulet(BlockColors blockColors, Profiler profiler, Map<Identifier, JsonUnbakedModel> jsonUnbakedModels, Map<Identifier, List<ModelLoader.SourceTrackedData>> blockStates, CallbackInfo ci) {
        this.addModel(new ModelIdentifier(MagicalBeginnings.MOD_ID, "sky_amulet_3d", "inventory"));
    }
}