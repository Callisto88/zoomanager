package View;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.io.FileOutputStream;

/**
 * Created by Andre on 17.05.2017.
 */
public class PrintPDF {

    public PrintPDF(JTable jtTable, String title, String additional){
        Document document = new Document(PageSize.A4.rotate());
        // Permet de crée une popup permettant de choisir son chemin de destination
        final JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(new java.io.File("."));
        jfc.setDialogTitle("Veuillez sélectionner un dossier de destination");
        jfc.setApproveButtonText("crée");

        // Permet de désactiver les types de fichiers
        jfc.setAcceptAllFileFilterUsed(false);
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
            //adding table headers
            for (int i = 0; i < jtTable.getColumnCount(); i++) {
                pdfTable.addCell(jtTable.getColumnName(i));
            }
            //extracting data from the JTable and inserting it to PdfPTable
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
}
