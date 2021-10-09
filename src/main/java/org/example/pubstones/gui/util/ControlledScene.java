package org.example.pubstones.gui.util;

import javafx.scene.Parent;
import javafx.scene.Scene;
import org.example.pubstones.gui.controller.BaseController;

public class ControlledScene extends Scene {
    private BaseController baseController;

    public ControlledScene(Parent parent, BaseController baseController) {
        super(parent);
        this.baseController = baseController;
    }

    public BaseController getBaseController() {
        return baseController;
    }
}
