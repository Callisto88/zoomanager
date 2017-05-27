package View;

import Controller.Error.ErrorController;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import javax.print.PrintService;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Style;
import java.awt.*;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Andre on 17.05.2017.
 * Classe permettant de crée un PDF en demandant au client le chemin et le nom pour enregistrer
 */
public class PrintPDF {
    Font fontTitle = new Font(Font.FontFamily.HELVETICA, 30, Font.BOLD, BaseColor.BLACK);
    Font fontTextWhite = new Font(Font.FontFamily.HELVETICA, 15, Font.NORMAL, BaseColor.WHITE);
    Font fontTextBlack = new Font(Font.FontFamily.HELVETICA, 15, Font.NORMAL, BaseColor.BLACK);

    /**
     * Construteur pour crée un PDF et demander au client de sélectionner un chemin
     * @param jtTable table à imprimer
     * @param title titre inclus dans le PDF
     * @param additional String additionnel pouvant être inclus dans le PDF si le champ est rempli
     */
    public PrintPDF(JTable jtTable, String title, String additional){
        this(jtTable, new JLabel(title), additional);
    }

    public PrintPDF(JTable jtTable, JLabel title, String additional){
        this(jtTable, new JTable(), title, additional);
    }

    public PrintPDF(JTable jtOrderContent, JTable jtInfoOrder, JLabel title, String additional){
        Document document = new Document(PageSize.A4);
        // Permet de crée une popup permettant de choisir son chemin de destination
        final JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(new java.io.File("."));
        // Permet de spécifier que l'on désire sélectionner un chemin de destination
        jfc.setDialogTitle("Veuillez sélectionner un dossier de destination");
        // Change le bouton d'ouverture en bouton de création
        jfc.setApproveButtonText("Créer");

        // Permet de désactiver les types de fichiers
        jfc.setAcceptAllFileFilterUsed(false);

        FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("pdf files (*.pdf)", "pdf");
        // add filters
        jfc.addChoosableFileFilter(xmlFilter);
        jfc.setFileFilter(xmlFilter);

        // Permet d'instancier une fenêtre de demande de chemin de destination et de chemin de fichier
        int answers = jfc.showOpenDialog(new JPanel());
        String output = "";
        if (answers == JFileChooser.APPROVE_OPTION) {
            if (!jfc.getSelectedFile().toString().endsWith(".pdf")) {
                output = jfc.getSelectedFile().toString() + ".pdf";
            } else {
                output = jfc.getSelectedFile().toString();
            }
        }

        try {
            PdfWriter.getInstance(document, new FileOutputStream(output));

            document.open();
            Paragraph p = new Paragraph(title.getText(), fontTitle);
            p.setAlignment(1);
            document.add(p);

            if(additional != null){
                Paragraph pAdditional = new Paragraph(additional);
                p.setAlignment(1);
                document.add(pAdditional);
            }
            document.add( Chunk.NEWLINE );

            //Vérifie si la deuxième JTable passée en paramètre est vide ou non
            //Si elle n'est pas vide, elle est mise dans le pdf.
            if(jtInfoOrder.getRowCount() > 0){
                PdfPTable pptTableOrderInfo = new PdfPTable(jtInfoOrder.getColumnCount());
                pptTableOrderInfo.setTotalWidth(200);
                for (int rows = 0; rows < jtInfoOrder.getRowCount(); rows++) {
                    for (int cols = 0; cols < jtInfoOrder.getColumnCount(); cols++) {
                        //Les métadonnées
                        if(cols == 0){
                            PdfPCell ppcCell = new PdfPCell(new Phrase(jtInfoOrder.getValueAt(rows,cols).toString(), fontTextWhite));
                            ppcCell.setBackgroundColor(BaseColor.DARK_GRAY);
                            pptTableOrderInfo.addCell(ppcCell);
                            //Les données (info) sur la commande
                        }else{
                            PdfPCell ppcCellHeaders = new PdfPCell(new Phrase(jtInfoOrder.getValueAt(rows,cols).toString(), fontTextBlack));
                            ppcCellHeaders.setBackgroundColor(BaseColor.WHITE);
                            pptTableOrderInfo.addCell(ppcCellHeaders);
                        }
                    }
                }
                document.add(pptTableOrderInfo);

                document.add( Chunk.NEWLINE );
            }

            PdfPTable pptTableOrderContent = new PdfPTable(jtOrderContent.getColumnCount());

            //Ajout des en-têtes
            for (int i = 0; i < jtOrderContent.getColumnCount(); i++) {
                PdfPCell ppcCellHeaders = new PdfPCell(new Phrase(jtOrderContent.getColumnName(i), fontTextWhite));
                ppcCellHeaders.setBackgroundColor(BaseColor.DARK_GRAY);
                pptTableOrderContent.addCell(ppcCellHeaders);
            }

            //Extrait les données de la JTable et les insère dans pptTableOrderContent
            for (int rows = 0; rows < jtOrderContent.getRowCount(); rows++) {
                for (int cols = 0; cols < jtOrderContent.getColumnCount(); cols++) {
                    pptTableOrderContent.addCell(jtOrderContent.getModel().getValueAt(rows, cols).toString());
                }
            }
            document.add(pptTableOrderContent);

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        document.close();

        // permet de lancer une impression du PDF après avoir sélectionné l'imprimante
        try{
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintService(choosePrinter());
            PDDocument doc = PDDocument.load(new File(output));
            job.setPageable(new PDFPageable(doc));
            job.print();

        } catch (Exception exception) {
            exception.printStackTrace();
            // TODO : à voir si on crée bien cette fenêtre d'erreur...
            new ErrorController(exception.toString());
        }
    }

    /**
     * Méthode permettant de sélectionner une imprimante
     * @return l'imprimante sélectionné
     */
    private PrintService choosePrinter() {
        PrinterJob printJob = PrinterJob.getPrinterJob();
        if(printJob.printDialog()) {
            return printJob.getPrintService();
        }
        else {
            return null;
        }
    }
}
