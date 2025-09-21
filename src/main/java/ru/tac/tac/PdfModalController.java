package ru.tac.tac;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.io.RandomAccessReadBufferedFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class PdfModalController {

    @FXML
    private ImageView pdfImageView;

    public void loadPdfPage(String filePath, int pageNumber) {
        File file = new File(filePath);

        try (PDDocument document = Loader.loadPDF(new RandomAccessReadBufferedFile(file))) {

            //System.out.println(document.getNumberOfPages());

            PDFRenderer renderer = new PDFRenderer(document);

            BufferedImage bufferedImage = renderer.renderImageWithDPI(pageNumber - 1, 150);

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", os);
            ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
            Image fxImage = new Image(is);

            // Отображаем в ImageView
            pdfImageView.setImage(fxImage);
            pdfImageView.setFitWidth(400);
            pdfImageView.setFitHeight(800);
            pdfImageView.setPreserveRatio(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
