package de.arraying.phoenix.models;

import java.util.function.Supplier;

public class HUDEntry {

    private static final String DELIMITER = ": ";
    private final String title;
    private final Supplier<String> information;

    public HUDEntry(String title, Supplier<String> information) {
        this.title = title;
        this.information = information;
    }

    public String format() {
        // Don't use String.format for performance.
        return title + DELIMITER + information.get();
    }
}
