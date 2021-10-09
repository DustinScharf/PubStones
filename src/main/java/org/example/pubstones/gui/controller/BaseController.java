package org.example.pubstones.gui.controller;

import org.example.pubstones.gui.MusicManager;
import org.example.pubstones.gui.SceneManager;

public class BaseController {
    protected SceneManager sceneManager;

    protected MusicManager musicManager;

    public BaseController() {
        this.musicManager = new MusicManager();
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
