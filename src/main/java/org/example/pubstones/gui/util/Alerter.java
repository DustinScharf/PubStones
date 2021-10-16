package org.example.pubstones.gui.util;

import javafx.scene.control.Alert;

public class Alerter {
    public static final String TITLE = "PubStones";

    public static Alert buildInfoAlert(String infoTitle, String infoMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(TITLE);
        alert.setHeaderText(infoTitle);
        alert.setContentText(infoMessage);

        return alert;
    }
}
