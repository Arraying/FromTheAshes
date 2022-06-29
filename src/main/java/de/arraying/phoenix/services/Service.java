package de.arraying.phoenix.services;

import de.arraying.phoenix.PhoenixMod;

public abstract class Service {

    protected final PhoenixMod phoenix;

    public Service() {
        this.phoenix = PhoenixMod.getInstance();
    }

}
