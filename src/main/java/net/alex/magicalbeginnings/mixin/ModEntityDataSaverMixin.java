package net.alex.magicalbeginnings.mixin;

import net.alex.magicalbeginnings.util.IEntityDataSaver;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class ModEntityDataSaverMixin implements IEntityDataSaver {
    private NbtCompound persistentData;

    /**
     * Checks if there exists persistent data and if not creates a new nbt
     *
     * @return the persistent data
     */
    public NbtCompound getPersistentData(){
        if(this.persistentData == null){
            this.persistentData = new NbtCompound();
        }
        return persistentData;
    }

    /**
     * Writes the persistent data into the nbt with a specific key
     *
     * @param nbt the nbt where info is saved
     * @param info callback info
     */
    @Inject(method = "writeNbt", at = @At("HEAD"))
    protected void injectWriteMethod(NbtCompound nbt, CallbackInfoReturnable info){
        if(persistentData != null){
            nbt.put("magicalbeginnings.alex_data", persistentData);
        }
    }

    /**
     * Reads the info of the nbt at the specific key
     *
     * @param nbt the nbt to read from
     * @param info callback info
     */
    @Inject(method = "readNbt", at = @At("HEAD"))
    protected void injectReadMethod(NbtCompound nbt, CallbackInfo info) {
        if (nbt.contains("magicalbeginnings.alex_data", 10)) {
            persistentData = nbt.getCompound("magicalbeginnings.alex_data");
        }
    }
}
