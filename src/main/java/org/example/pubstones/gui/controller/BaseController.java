package org.example.pubstones.gui.controller;

import org.example.pubstones.gui.manager.MusicManager;
import org.example.pubstones.gui.manager.SceneManager;

public abstract class BaseController {
    protected SceneManager sceneManager;

    protected MusicManager musicManager;

    public BaseController() {
        this.musicManager = new MusicManager();
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public void init() {
        // can be overwritten if needed
    }
}
