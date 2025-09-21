package ru.tac.tac;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class TerminalController {

    @FXML
    private TextFlow terminalArea;

    public void displayNextMessage(String message, Color color) {
        javafx.application.Platform.runLater(() -> {
            Text text = new Text(message);
            text.setFill(color);
            terminalArea.getChildren().add(text);
        });
    }


}
