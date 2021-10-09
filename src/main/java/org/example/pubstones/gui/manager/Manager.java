package org.example.pubstones.gui.manager;

import javafx.stage.Stage;

public class Manager {
    private SceneManager sceneManager;
    private MusicManager musicManager;

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
}
