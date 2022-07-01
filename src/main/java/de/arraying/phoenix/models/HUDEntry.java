package de.arraying.phoenix.models;

import java.util.function.Supplier;

/**
 * Represents a single piece of information.
 */
public class HUDEntry {

    private static final String DELIMITER = ": ";
    private final String title;
    private final Supplier<String> information;

    /**
     * Creates a new entry.
     * @param title The title of the entry without delimiter.
     * @param information A supplier that will supply the entry information.
     */
    public HUDEntry(String title, Supplier<String> information) {
        this.title = title;
        this.information = information;
    }

    /**
     * Formats the entry.
     * @return The title, delimiter and then information.
     */
    public String format() {
        // Don't use String.format for performance.
        return title + DELIMITER + information.get();
    }
}
