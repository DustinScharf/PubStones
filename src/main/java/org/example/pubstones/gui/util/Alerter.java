package org.example.pubstones.gui.util;

import javafx.scene.control.Alert;

public class Alerter {
    public static final String TITLE = "PubStones";

    public static Alert buildInfoAlert(String errorTitle, String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(TITLE);
        alert.setHeaderText(errorTitle);
        alert.setContentText(errorMessage);

        return alert;
    }
}
