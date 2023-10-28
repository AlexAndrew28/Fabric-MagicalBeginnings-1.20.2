package net.alex.magicalbeginnings.event;

import net.alex.magicalbeginnings.util.IEntityDataSaver;
import net.alex.magicalbeginnings.util.MagicExpData;
import net.alex.magicalbeginnings.util.ManaData;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.profiling.jfr.event.ServerTickTimeEvent;

public class PlayerTickHandler implements ServerTickEvents.StartTick {
    /**
     * Called on the start of te server ticks to update the mana level of the player
     *
     * @param server the server
     */
    @Override
    public void onStartTick(MinecraftServer server) {
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            IEntityDataSaver dataPlayer = ((IEntityDataSaver) player);

            // regen mana
            ManaData.regenMana(dataPlayer);
        }
    }
}
