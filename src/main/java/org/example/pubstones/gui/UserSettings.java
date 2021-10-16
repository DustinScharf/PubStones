package org.example.pubstones.gui;

import java.io.Serializable;

public class UserSettings implements Serializable {
    private final String settingsName;

    private String name;
    private double volume;

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
