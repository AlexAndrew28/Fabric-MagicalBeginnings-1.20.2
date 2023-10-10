package net.alex.magicalbeginnings.entity.client;

import net.alex.magicalbeginnings.MagicalBeginnings;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer FIREBALL =
            new EntityModelLayer(new Identifier(MagicalBeginnings.MOD_ID, "fireball"), "main");
}
