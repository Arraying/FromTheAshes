package de.arraying.phoenix;

import de.arraying.phoenix.services.HUDService;
import de.arraying.phoenix.services.KeyboardService;
import de.arraying.phoenix.services.ZoomService;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The mod itself.
 */
public class PhoenixMod implements ModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("phoenix");
    private static PhoenixMod instance;
    private HUDService hudService;
    private KeyboardService keyboardService;
    private ZoomService zoomService;

    /**
     * When the mod gets initialized.
     */
	@Override
	public void onInitialize() {
        instance = this;
		LOGGER.info("Phoenix rising from the ashes");
        this.hudService = new HUDService();
        this.keyboardService = new KeyboardService();
        this.zoomService = new ZoomService();
	}

    /**
     * Singleton getter.
     * @return The mod instance.
     */
    public static PhoenixMod getInstance() {
        return instance;
    }

    /**
     * Service getter.
     * @return The service.
     */
    public HUDService getHudService() {
        return hudService;
    }

    /**
     * Service getter.
     * @return The service.
     */
    public KeyboardService getKeyboardService() {
        return keyboardService;
    }

    /**
     * Service getter.
     * @return The service.
     */
    public ZoomService getZoomService() {
        return zoomService;
    }

}
