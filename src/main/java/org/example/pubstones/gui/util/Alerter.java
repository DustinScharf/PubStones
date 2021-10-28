package org.example.pubstones.gui.util;

import javafx.scene.control.Alert;

/**
 * This class can create alerts
 */
public class Alerter {
    /**
     * Default title for all alerts matching the app name
     */
    public static final String TITLE = "PubStones";

    /**
     * Builds an info alert
     *
     * @param infoTitle   title of the info
     * @param infoMessage detailed info message
     * @return Alert with the build info
     */
    public static Alert buildInfoAlert(String infoTitle, String infoMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(TITLE);
        alert.setHeaderText(infoTitle);
        alert.setContentText(infoMessage);

        return alert;
    }
}
