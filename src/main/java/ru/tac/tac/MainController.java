package ru.tac.tac;

import javafx.fxml.FXML;

public class MainController {

    @FXML
    private MenuBarController menuBarController;

    @FXML
    private TabViewController tabViewController;

    @FXML
    private TerminalController terminalController;

    @FXML
    public void initialize() {

        menuBarController.setTabViewController(tabViewController);
        menuBarController.setTerminalController(terminalController);
    }
}
