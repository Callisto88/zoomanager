package View;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Style;
import java.awt.*;
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
        Document document = new Document(PageSize.A4.rotate());
        // Permet de crée une popup permettant de choisir son chemin de destination
        final JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(new java.io.File("."));
        // Permet de spécifier que l'on désire sélectionner un chemin de destination
        jfc.setDialogTitle("Veuillez sélectionner un dossier de destination");
        // Change le bouton d'ouverture en bouton de création
        jfc.setApproveButtonText("créer");

        // Permet de désactiver les types de fichiers
        jfc.setAcceptAllFileFilterUsed(false);

        FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("pdf files (*.pdf)", "pdf");
        // add filters
        jfc.addChoosableFileFilter(xmlFilter);
        jfc.setFileFilter(xmlFilter);

        // Permet d'instancier une fenêtre de demande de chemin de destination et de chemin de fichier
        int answers = jfc.showOpenDialog(new JPanel());
        String output = "";
        if(answers == JFileChooser.APPROVE_OPTION){
            output = jfc.getSelectedFile().toString() + ".pdf";
        }

        try {
            PdfWriter.getInstance(document, new FileOutputStream(output));

            document.open();
            Paragraph p = new Paragraph(title);
            p.setAlignment(1);
            document.add(p);
            if(additional != null){
                Paragraph pAdditional = new Paragraph(additional);
                p.setAlignment(1);
                document.add(pAdditional);
            }
            document.add( Chunk.NEWLINE );

            PdfPTable pdfTable = new PdfPTable(jtTable.getColumnCount());
            // Permet d'ajouter les nom des colonnes
            for (int i = 0; i < jtTable.getColumnCount(); i++) {
                pdfTable.addCell(jtTable.getColumnName(i));
            }
            // Permet d'extraire les données de la JTable, et de les insérer dans la PDFTable
            for (int rows = 0; rows < jtTable.getRowCount() - 1; rows++) {
                for (int cols = 0; cols < jtTable.getColumnCount(); cols++) {
                    pdfTable.addCell(jtTable.getModel().getValueAt(rows, cols).toString());
                }
            }
            document.add(pdfTable);


        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        document.close();
    }

    public PrintPDF(JTable jtTable, JLabel title, String additional){
        Document document = new Document(PageSize.A4);
        // Permet de crée une popup permettant de choisir son chemin de destination
        final JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(new java.io.File("."));
        jfc.setDialogTitle("Veuillez sélectionner un dossier de destination");
        jfc.setApproveButtonText("créer");

        // Permet de désactiver les types de fichiers
        jfc.setAcceptAllFileFilterUsed(false);
        int answers = jfc.showOpenDialog(new JPanel());
        String output = "";
        if(answers == JFileChooser.APPROVE_OPTION){
            output = jfc.getSelectedFile().toString() + ".pdf";
        }

        try {
            PdfWriter pwWriter = PdfWriter.getInstance(document, new FileOutputStream(output));
            //pwWriter.setPageEvent(jtTable);

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


            PdfPTable pdfTable = new PdfPTable(jtTable.getColumnCount() - 2);

            //adding table headers
            for (int i = 0; i < jtTable.getColumnCount(); i++) {
                if(i != 3 && i != 5){
                    PdfPCell ppcCellHeaders = new PdfPCell(new Phrase(jtTable.getColumnName(i), fontTextWhite));
                    ppcCellHeaders.setBackgroundColor(BaseColor.BLACK);
                    pdfTable.addCell(ppcCellHeaders);
                }

            }
            //extracting data from the JTable and inserting it to PdfPTable
            for (int rows = 0; rows < jtTable.getRowCount() - 1; rows++) {
                for (int cols = 0; cols < jtTable.getColumnCount(); cols++) {
                    if(cols != 3 && cols != 5){
                        pdfTable.addCell(jtTable.getModel().getValueAt(rows, cols).toString());
                    }
                }
            }
            document.add(pdfTable);


        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        document.close();
    }

    public PrintPDF(JTable jtOrderContent, JTable jtInfoOrder, JLabel title, String additional){
        Document document = new Document(PageSize.A4);
        // Permet de crée une popup permettant de choisir son chemin de destination
        final JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(new java.io.File("."));
        jfc.setDialogTitle("Veuillez sélectionner un dossier de destination");
        jfc.setApproveButtonText("créer");

        // Permet de désactiver les types de fichiers
        jfc.setAcceptAllFileFilterUsed(false);
        int answers = jfc.showOpenDialog(new JPanel());
        String output = "";
        if(answers == JFileChooser.APPROVE_OPTION){
            output = jfc.getSelectedFile().toString() + ".pdf";
        }

        try {
            PdfWriter pwWriter = PdfWriter.getInstance(document, new FileOutputStream(output));
            //pwWriter.setPageEvent(jtTable);

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

            PdfPTable pptTableOrderInfo = new PdfPTable(jtInfoOrder.getColumnCount());
            pptTableOrderInfo.setTotalWidth(200);
            for (int rows = 0; rows < jtInfoOrder.getRowCount(); rows++) {
                for (int cols = 0; cols < jtInfoOrder.getColumnCount(); cols++) {
                    if(cols == 0){
                        PdfPCell ppcCell = new PdfPCell(new Phrase(jtInfoOrder.getValueAt(rows,cols).toString(), fontTextWhite));
                        ppcCell.setBackgroundColor(BaseColor.BLACK);
                        pptTableOrderInfo.addCell(ppcCell);
                    }else{
                        PdfPCell ppcCellHeaders = new PdfPCell(new Phrase(jtInfoOrder.getValueAt(rows,cols).toString(), fontTextBlack));
                        ppcCellHeaders.setBackgroundColor(BaseColor.WHITE);
                        pptTableOrderInfo.addCell(ppcCellHeaders);
                    }
                }
            }
            document.add(pptTableOrderInfo);

            document.add( Chunk.NEWLINE );

            PdfPTable pptTableOrderContent = new PdfPTable(jtOrderContent.getColumnCount());

            //adding table headers
            for (int i = 0; i < jtOrderContent.getColumnCount(); i++) {
                PdfPCell ppcCellHeaders = new PdfPCell(new Phrase(jtOrderContent.getColumnName(i), fontTextWhite));
                ppcCellHeaders.setBackgroundColor(BaseColor.BLACK);
                pptTableOrderContent.addCell(ppcCellHeaders);
            }
            //extracting data from the JTable and inserting it to PdfPTable
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
    }

    /*
    class MyPDFTable extends PdfPageEventHelper {
        private String header;
        private PdfTemplate total;
        private JTable jt;

        public MyPDFTable(JTable jt){
            this.jt = jt;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public void onOpenDocument(PdfWriter writer, Document document) {
            total = writer.getDirectContent().createTemplate(30, 16);
        }



        public void onEndPage(PdfWriter writer, Document document) {

            PdfPTable pdfTable = new PdfPTable(jt.getColumnCount() - 2);

            //adding table headers
            for (int i = 0; i < jt.getColumnCount(); i++) {
                if(i != 3 && i != 5){
                    PdfPCell ppcCellHeaders = new PdfPCell(new Phrase(jt.getColumnName(i), fontText));
                    ppcCellHeaders.setBackgroundColor(BaseColor.BLACK);
                    pdfTable.addCell(ppcCellHeaders);
                }

            }
            //extracting data from the JTable and inserting it to PdfPTable
            for (int rows = 0; rows < jt.getRowCount() - 1; rows++) {
                for (int cols = 0; cols < jt.getColumnCount(); cols++) {
                    if(cols != 3 && cols != 5){
                        pdfTable.addCell(jt.getModel().getValueAt(rows, cols).toString());
                    }
                }
            }
            super.document.add(pdfTable);
            try {
                table.setWidths(new int[]{24, 24, 2});
                table.setTotalWidth(527);
                table.setLockedWidth(true);
                table.getDefaultCell().setFixedHeight(20);
                table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                table.addCell(header);
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(String.format("Page %d of", writer.getPageNumber()));
                PdfPCell cell = new PdfPCell(Image.getInstance(total));
                cell.setBorder(Rectangle.BOTTOM);
                table.addCell(cell);
                table.writeSelectedRows(0, -1, 34, 803, writer.getDirectContent());
            } catch (DocumentException de) {
                throw new ExceptionConverter(de);
            }
        }
    }

*/
}
