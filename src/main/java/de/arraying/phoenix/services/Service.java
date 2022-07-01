package de.arraying.phoenix.services;

import de.arraying.phoenix.PhoenixMod;

/**
 * Abstract service to provide an instance of {@link PhoenixMod}.
 * This is to avoid constructors since those are a pain in Java.
 */
public abstract class Service {

    protected final PhoenixMod phoenix;

    /**
     * Sets the correct reference.
     */
    public Service() {
        this.phoenix = PhoenixMod.getInstance();
    }

}
