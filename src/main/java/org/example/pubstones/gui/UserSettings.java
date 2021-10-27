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

    /**
     * Volume for the music
     */
    private double volume;

    /**
     * Creates settings with a unique name
     *
     * @param settingsName a unique name for the settings
     */
    public UserSettings(String settingsName) {
        this.settingsName = settingsName;
    }

    /**
     * Gives the name of this settings
     *
     * @return name of this settings
     */
    public String getSettingsName() {
        return settingsName;
    }

    /**
     * Gives the name of the user
     *
     * @return name of the user
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gives the music volume
     *
     * @return music volume
     */
    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }
}
