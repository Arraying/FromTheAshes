package de.arraying.phoenix;

import de.arraying.phoenix.services.HUDService;
import de.arraying.phoenix.services.KeyboardService;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhoenixMod implements ModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("phoenix");
    private static PhoenixMod instance;
    private HUDService hudService;
    private KeyboardService keyboardService;

	@Override
	public void onInitialize() {
        instance = this;
		LOGGER.info("Phoenix rising from the ashes");
        this.hudService = new HUDService();
        this.keyboardService = new KeyboardService();
	}

    public static PhoenixMod getInstance() {
        return instance;
    }

    public HUDService getHudService() {
        return hudService;
    }

    public KeyboardService getKeyboardService() {
        return keyboardService;
    }

}
