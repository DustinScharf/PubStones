package org.example.pubstones.gui;

import java.io.Serializable;

/**
 * This class holds the UserSettings (mainly for safe reasons)
 */
public class UserSettings implements Serializable {
    /**
     * Unique name for the settings
     */
    private final String settingsName;

    /**
     * Name of the user
     */
    private String name;

    private double volume;

    /**
     * Creates settings with a unique name
     *
     * @param settingsName a unique name for the settings
     */
    public UserSettings(String settingsName) {
        this.settingsName = settingsName;
    }

    public String getSettingsName() {
        return settingsName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }
}
