package ru.tac.tac;

import javafx.fxml.FXML;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;



public class TabContentController {

    @FXML
    private CodeArea codeArea;

    @FXML
    public void initialize() {
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
    }

    public CodeArea getCodeArea() {
        return codeArea;
    }

}
