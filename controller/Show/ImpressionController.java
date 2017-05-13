package Controller.Show;


import Model.*;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;

public class ImpressionController  implements Printable {
    public void imprimer(){
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new ImpressionController());
        boolean doPrint = job.printDialog();
        if(doPrint) {
            try {
		/* Lancement de l'impression */
                job.print();
            }
            catch (PrinterException e1) {
                e1.printStackTrace();
            }
        }
    }
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }
		/* On définit une marge pour l'impression */
        int marge=30;

		/* On récupère les coordonnées des bords de la page */
        int x = (int)pageFormat.getImageableX();
        int y = (int)pageFormat.getImageableY();
        int w = (int)pageFormat.getImageableWidth();
        int h = (int)pageFormat.getImageableHeight();

		/* Dessin d'un cadre gris clair*/
        graphics.setColor(Color.white);
        graphics.fillRect(x+10, y+10, w-20, h-20);

		/* On écrit une ligne de titre en rouge, en gras de taille 18 */
        graphics.setFont(new Font("Arial", Font.BOLD, 18));
        graphics.setColor(Color.blue);
        graphics.drawString("Rapport SUR LES EVENEMENTS PROGRAMMES\n", x + marge, y+marge);

		/* On écrit une ligne en noir de taille 14 */
        graphics.setFont(new Font("Arial", Font.PLAIN, 10));
        graphics.setColor(Color.BLACK);
        ArrayList<String> evtType = (new EventTypeController()).selAll();

        EventController eventCtrl = new EventController();
         AnimalEventController animalEventCtrl = new AnimalEventController();
         PersonneEventController personneEventCtrl = new PersonneEventController();
         IntervenantEventController intervenantEventCtrl = new IntervenantEventController();
         InfrastructureEventController infrastructureEventController = new InfrastructureEventController();
         int o = 0;
        for(String evtT : evtType) {
            graphics.drawString("> Type evenement ---- "+evtT+"\n", x + marge, y + marge + o +20);
            ArrayList<Evenement> ev = eventCtrl.selAllByEventType(evtT);
            for(Evenement e : ev){
                String animaux = "";
                ArrayList<Animal> lstAnimal = animalEventCtrl.selAllByEventId(e.getId());
                int i = 0;
                for (Animal a : lstAnimal){
                    animaux += a.getNom()+", ";
                }
                ArrayList<Personne> lstPersonne = personneEventCtrl.selAllByEventId(e.getId());
                String personnes = "";
                i = 0;
                for (Personne a : lstPersonne){
                    personnes += a.getNom()+", ";
                }
                ArrayList<Intervenant> lstIntervenant = intervenantEventCtrl.selAllByEventId(e.getId());
                String intervenantAll = "";
                i = 0;
                for (Intervenant a : lstIntervenant){
                    intervenantAll += a.getNom()+", ";
                }
                ArrayList<Infrastructure> lstInfra = infrastructureEventController.selAllByEventId(e.getId());
                String infraAll = "";
                i = 0;
                for (Infrastructure a : lstInfra){
                    infraAll += a.getNom()+", ";
                }
                graphics.drawString("> > Programmation du "+e.getDate().toString()+" \n", x + marge, y + marge + o +40);
                graphics.drawString("  - > Animaux ["+animaux+"]\n ", x + marge, y + marge +  o +60);
                graphics.drawString("  - > Personnes ["+personnes+"]\n", x + marge, y + marge +  o +80);
                graphics.drawString("  - > Intervenants ["+intervenantAll+"]\n", x + marge, y + marge +  o +100);
                graphics.drawString("  - > Infrastructures ["+infraAll+"]\n", x + marge, y + marge +  o +120);
                o+=110;
            }

        }

        return PAGE_EXISTS;
    }
}