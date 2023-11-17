/*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
 */
package demo.TPS6P14WebdesignMai2022.model;

/**
 *
 * @author Cedrick
 */
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.RectangleReadOnly;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletContext;

/**
 *
 * @author Rabeasimbola
 */
public class Pdf {

    public void genererPdf() throws Exception {
        Document document = null;
        try {
            document = new Document(new Rectangle(226.772f, 450));
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Cedrick\\Downloads\\Pdf\\ced.pdf"));
            document.open();

            Image image = Image.getInstance("src\\main\\resources\\static\\images\\logo.png");
            float imageWidthPercentage = 20;
            image.scalePercent(imageWidthPercentage);
            float imageXPositionPercentage = 60;
            float imageYPositionPercentage = 80;
            image.setAbsolutePosition(document.getPageSize().getWidth() * (imageXPositionPercentage / 100), document.getPageSize().getHeight() * (imageYPositionPercentage / 100));
            document.add(image);

            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");

            ArrayList<Attente> listeAtt = new Attente().findDernierEntrant();
            for (Attente attente : listeAtt) {
                Paragraph dataParagraph = new Paragraph();
                dataParagraph.add("\n");
                dataParagraph.add("\n");
                dataParagraph.add("\n");
                dataParagraph.add("\n");
                dataParagraph.add("\n");
                dataParagraph.add("\n");

                Font boldFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.GREEN);
                Chunk boldText = new Chunk("Facturation Caisse", boldFont);
                dataParagraph.add(boldText);

                dataParagraph.add("\n");
                dataParagraph.add("\n");
                dataParagraph.add("Ticket N °: " + attente.getNumero() + "\n");
                dataParagraph.add("\n");
                dataParagraph.add("Du: " + dateFormatter.format(attente.getDate_attente()) + "\n");

                document.add(dataParagraph);
            }

            // Fermer le document PDF
            document.close();
            System.out.println("Fichier PDF généré avec succès");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la génération du fichier PDF");
        } finally {
            // Assurez-vous de fermer le document même en cas d'erreur
            if (document != null) {
                document.close();
            }
        }
    }

}
