package de.arraying.phoenix;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhoenixMod implements ModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("phoenix");

	@Override
	public void onInitialize() {
		LOGGER.info("Phoenix rising from the ashes");
	}
}
