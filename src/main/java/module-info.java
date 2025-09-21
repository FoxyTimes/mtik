module ru.tac.tac {
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.fxml;
    requires org.fxmisc.richtext;
    requires org.apache.pdfbox;
    requires java.desktop;
    requires org.apache.pdfbox.io;

    opens ru.tac.tac to javafx.fxml;
    exports ru.tac.tac;
}