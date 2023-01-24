package pl.first.firstjava.view;

import javafx.scene.control.Alert;

public class PopOutWindow {
    public void messageBox(String title, String msg, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
