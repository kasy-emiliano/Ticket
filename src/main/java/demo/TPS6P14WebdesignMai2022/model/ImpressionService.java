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

import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.ArrayList;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.Sides;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import java.awt.print.PrinterJob;
import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;

 
public class ImpressionService {

   /*public static void imprimerFichierPDF() {
    try {
        String cheminVersPDF="C:\\Users\\Cedrick\\Downloads\\Pdf\\ced.pdf";
        // Obtenez l'imprimante par défaut
        PrintService defaultPrinter = PrintServiceLookup.lookupDefaultPrintService();
        if (defaultPrinter != null) {
            File file = new File(cheminVersPDF);
            FileInputStream fis = new FileInputStream(file);
            byte[] pdfData = new byte[(int) file.length()];
            fis.read(pdfData);
            DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
            Doc doc = new SimpleDoc(pdfData, flavor, null);
            PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
            attributes.add(new Copies(1)); // Nombre de copies
            attributes.add(Sides.ONE_SIDED);
            DocPrintJob job = defaultPrinter.createPrintJob();
            job.print(doc, attributes);
            System.out.println("Impression terminée avec l'imprimante par défaut : " + defaultPrinter.getName());
        } else {
            System.out.println("Aucune imprimante par défaut n'a été trouvée.");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
*/
    

   
    
  /*public static void imprimerFichierPDF(String cheminVersPDF, String nomImprimante) {
    try {
        // Recherchez l'imprimante par nom
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService service : services) {
            if (service.getName().equals(nomImprimante)) {
                PDDocument document = PDDocument.load(new File(cheminVersPDF));
                PrinterJob job = PrinterJob.getPrinterJob();

                // Spécifiez l'imprimante à utiliser
                job.setPrintService(service);

                job.setPageable(new PDFPageable(document));
                job.print();

                // Affichez un journal pour indiquer que l'impression est terminée
                System.out.println("Impression terminée.");
                
                break; // Quittez la boucle après avoir trouvé l'imprimante.
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}*/
   
  public static void imprimerFichierPDF(String cheminVersPDF) {
    try {
        PDDocument document = PDDocument.load(new File(cheminVersPDF));
        PrinterJob job = PrinterJob.getPrinterJob();

        // Obtenir l'imprimante par défaut
        PrintService defaultPrinter = PrintServiceLookup.lookupDefaultPrintService();

        if (defaultPrinter != null) {
            job.setPrintService(defaultPrinter);
            job.setPageable(new PDFPageable(document));
            job.print();

            // Affichez un journal pour indiquer que l'impression est terminée
            System.out.println("Impression terminée avec l'imprimante par défaut : " + defaultPrinter.getName());
        } else {
            System.out.println("Aucune imprimante par défaut n'a été trouvée.");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
