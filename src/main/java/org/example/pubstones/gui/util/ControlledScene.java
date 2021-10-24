package org.example.pubstones.gui.util;

import javafx.scene.Parent;
import javafx.scene.Scene;
import org.example.pubstones.gui.controller.BaseController;

/**
 * This calls connects the basic JavaFX scene with our base ("parent") controller
 */
public class ControlledScene extends Scene {
    /**
     * Simply the base ("parent") controller connected to the scene for access
     */
    private BaseController baseController;

    public ControlledScene(Parent parent, BaseController baseController) {
        super(parent); // this creates a "normal" java fx scene
        this.baseController = baseController;
    }

    public BaseController getBaseController() {
        return baseController;
    }
}
