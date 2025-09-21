package ru.tac.tac;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.Random;

public class MenuBarController {

    private TabViewController tabViewController;
    private TerminalController terminalController;

    public void setTabViewController(TabViewController tabViewController) {
        this.tabViewController = tabViewController;
    }

    public void setTerminalController(TerminalController terminalController) {
        this.terminalController = terminalController;
    }

    @FXML
    private void onNew() {
        if (tabViewController != null) {
            tabViewController.addTab("Новая вкладка", new TextArea());
        }
    }

    @FXML
    private void onOpen() {
        tabViewController.openNewTab();
    }

    @FXML
    private void onSaveAs() {
        tabViewController.saveAsCurrentTab();
    }

    @FXML
    private void onExit() {
        System.exit(0);
    }

    @FXML
    private void onRussian() {
        App.changeAppLanguage(new Locale("ru"));
    }

    @FXML
    private void onEnglish() {
        App.changeAppLanguage(new Locale("en"));
    }

    @FXML
    private void onAboutTitlePage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/tac/tac/PdfModal.fxml"));
            Scene scene = new Scene(loader.load());


            PdfModalController controller = loader.getController();
            controller.loadPdfPage("documentation.pdf", 1);

            Stage stage = new Stage();
            stage.setTitle("Title Page");

            stage.setWidth(800);
            stage.setHeight(600);

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onRun() {
        new Thread(() -> {
            terminalController.displayNextMessage("Анализ...\n", Color.WHITE);

            Random random = new Random();
            for (int i = 0; i < 100; i += random.nextInt(10, 20)) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                terminalController.displayNextMessage(i + "%\n", Color.WHITE);
            }

            terminalController.displayNextMessage("Готово!\n", Color.GREEN);
        }).start();
    }


}
