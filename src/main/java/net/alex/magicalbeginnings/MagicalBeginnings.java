package net.alex.magicalbeginnings;

import net.alex.magicalbeginnings.block.ModBlocks;
import net.alex.magicalbeginnings.event.PlayerTickHandler;
import net.alex.magicalbeginnings.item.ModItemGroups;
import net.alex.magicalbeginnings.item.ModItems;
import net.alex.magicalbeginnings.networking.ModMessages;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MagicalBeginnings implements ModInitializer {
	public static final String MOD_ID = "magicalbeginnings";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModMessages.registerC2SPackets();

		ServerTickEvents.START_SERVER_TICK.register(new PlayerTickHandler());

	}

}