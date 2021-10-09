package org.example.pubstones.gui.controller;

import org.example.pubstones.gui.manager.Manager;
import org.example.pubstones.gui.manager.MusicManager;
import org.example.pubstones.gui.manager.SceneManager;

public abstract class BaseController {
    protected Manager manager;

    public BaseController() {
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void init() {
        // can be overwritten if needed
    }
}
