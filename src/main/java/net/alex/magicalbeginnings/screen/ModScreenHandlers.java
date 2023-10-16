package net.alex.magicalbeginnings.screen;

import net.alex.magicalbeginnings.MagicalBeginnings;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {

    public static final ScreenHandlerType<RyftImbuingScreenHandler> RYFT_IMBUING_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(MagicalBeginnings.MOD_ID, "ryft_imbuing"),
                    new ExtendedScreenHandlerType<>(RyftImbuingScreenHandler::new));

    public static void registerScreenHandlers(){
        MagicalBeginnings.LOGGER.info("Registering Screen Handlers for " + MagicalBeginnings.MOD_ID);
    }
}
