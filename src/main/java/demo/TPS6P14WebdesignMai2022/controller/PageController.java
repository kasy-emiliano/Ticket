/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.TPS6P14WebdesignMai2022.controller;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.thymeleaf.context.Context;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;

import demo.TPS6P14WebdesignMai2022.generic.GenericDAO;
import demo.TPS6P14WebdesignMai2022.model.*;
import demo.TPS6P14WebdesignMai2022.model.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.lang.model.element.Element;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.TemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.OutputStream;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;

/**
 *
 * @author Cedrick
 */
@Controller

public class PageController {

    @Autowired
    ServletContext servletContext;
    @Autowired
    private TemplateEngine templateEngine;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/inserAtten")
    public String insertattente() {
        return "insertAttente";
    }

    @RequestMapping("/insertAttente")
    public void insertAttente(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {

        //********INSERTION************//
        Attente emp = new Attente();
        Date date_attente = Date.valueOf(LocalDate.now());
        emp.insererFileAttente(date_attente);

        //********GENERER FICHIER PDF************//
       /* response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=ced.pdf");

        Pdf pdf = new Pdf();
        pdf.genererPdf();

        //********IMPRIMER FICHIER************
        ImpressionService imp = new ImpressionService();
        String cheminVersPDF = "C:\\Users\\Cedrick\\Downloads\\Pdf\\ced.pdf";
        //String nomImprimante = "Microsoft Print to PDF"; // Remplacez par le nom de votre imprimante
       imp.imprimerFichierPDF(cheminVersPDF);

        // Format de date souhaité (par  exemple, "yyyy-MM-dd")
        /* SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String dateFormatted = sdf.format(date_attente);*/
        // Maintenant, vous pouvez utiliser dateFormatted pour l'insertion dans la base de données*
    }

    /*try {
            Pdf pdf = new Pdf();
            Attente pf = new Attente();
            String alefa = pf.pdf();
            String i = alefa;
            pdf.genererPdf(servletContext.getRealPath(""), "ticket", i);
        } catch (Exception e) {
            throw e;
        }*/
    @RequestMapping("/pdf")
    public void testpppppp(HttpServletResponse response) throws Exception {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=ced.pdf");

        Pdf pdf = new Pdf();
        pdf.genererPdf();
    }

    @RequestMapping("/imprime")
    public String imprime() {
        ImpressionService imp = new ImpressionService();

        String cheminVersPDF = "C:\\Users\\Cedrick\\Downloads\\Pdf\\ced.pdf";
        String nomImprimante = "Microsoft Print to PDF"; // Remplacez par le nom de votre imprimante
        //imp.printPdf();

        return "index";
    }

    @RequestMapping("/ticketJson")
    @ResponseBody
    public ArrayList<Attente> getListeAttente() throws Exception {
        ArrayList<Attente> listeAtt = new Attente().findDernierEntrant();
        return listeAtt;
    }

   
    /*@RequestMapping("/insertAttente")
    public String insertAttente(Model model, HttpServletRequest request) throws IOException, Exception {
        Attente emp = new Attente();
            // String dateA = request.getParameter("dateAttente");
            Date date_attente = Date.valueOf(LocalDate.now());
            emp.insererFileAttente(date_attente);

        
           /*  ArrayList<Attente> listeAtt = new Attente().findDernierEntrant();
        model.addAttribute("listeAtt", listeAtt);
            
        return "redirect:/ticket";
    }*/
 /* try {
                Pdf pdf = new Pdf();
                Attente pf = new Attente();
                String alefa = pf.pdf();
                String i = alefa;
                pdf.genererPdf(servletContext.getRealPath(""), "ticket", i);
            } catch (Exception e) {
            throw e;
        }*/

 /*@RequestMapping("/ticket")
    public String listeticket(Model model, HttpSession session,
            HttpServletResponse response) throws IOException, Exception {
        PrintWriter out = response.getWriter();
        ArrayList<Attente> listeAtt = new Attente().findDernierEntrant();
        model.addAttribute("listeAtt", listeAtt);
        return "ticket";
    }*/
}
