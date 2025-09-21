package ru.tac.tac;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import org.fxmisc.richtext.CodeArea;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TabViewController {

    @FXML
    private TabPane tabPane;

    private final Map<Tab, TabContentController> tabContentControllerMap = new HashMap<>();

    private String lastDirectoryPath = System.getProperty("user.home");

    public void addTab(String title, TextArea contentOfFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("tab-content.fxml"));
            Parent content = loader.load();

            TabContentController controller = loader.getController();

            Tab tab = new Tab(title, content);
            tab.setClosable(true);

            controller.getCodeArea().replaceText(contentOfFile.getText());

            tabContentControllerMap.put(tab, controller);

            tabPane.getTabs().add(tab);
            tabPane.getSelectionModel().select(tab);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAsCurrentTab() {
        Tab tab = tabPane.getSelectionModel().getSelectedItem();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(lastDirectoryPath));
        fileChooser.setTitle("Save as");

        CodeArea contentOfFile = tabContentControllerMap.get(tab).getCodeArea();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Текстовые файлы", "*.txt"));
        File file = fileChooser.showSaveDialog(contentOfFile.getScene().getWindow());

        if (file != null) {
            writeFile(file, contentOfFile.getText());
            tab.setText(file.getName());

            lastDirectoryPath = file.getParentFile().getAbsolutePath();
        }

    }

    public void openNewTab() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(lastDirectoryPath));
        fileChooser.setTitle("Open");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.txt"));
        File file = fileChooser.showOpenDialog(tabPane.getScene().getWindow());

        if (file != null) {
            try {
                String text = new String(java.nio.file.Files.readAllBytes(file.toPath()));
                TextArea contentOfFile = new TextArea(text);
                addTab(file.getName(), contentOfFile);

                lastDirectoryPath = file.getParentFile().getAbsolutePath();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void writeFile(File file, String text) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}