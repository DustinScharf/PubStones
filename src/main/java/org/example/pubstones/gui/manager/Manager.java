package org.example.pubstones.gui.manager;

import javafx.stage.Stage;
import org.example.pubstones.gui.UserSettings;

public class Manager {
    private final SceneManager sceneManager;
    private final MusicManager musicManager;

    private UserSettings userSettings;

    public Manager(Stage stage) {
        this.sceneManager = new SceneManager(this, stage);
        this.musicManager = new MusicManager();
    }

    public SceneManager getSceneManager() {
        return sceneManager;
    }

    public MusicManager getMusicManager() {
        return musicManager;
    }

    public UserSettings getUserSettings() {
        return userSettings;
    }

    public void setUserSettings(UserSettings userSettings) {
        this.userSettings = userSettings;

        this.musicManager.setVolume(this.userSettings.getVolume());
    }
}
