package View.Show;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


/**
 *
 * Cette classe contient le template du JPanel qui permet fournir un slider show des images partout ou il est appelé
 *
 * @author doriane kaffo
 * @version 1.0
 *
 * @date    14/03/2017.(Création)
 * @date    39/05/2017 (Finalisation v1.0)
 *
 */
public class SlideJPanel extends javax.swing.JPanel {
    private Image img;
    private  int progress;
    public SlideJPanel(){
        progress = 0;
        Thread t = new Thread(
                new Runnable() {
                    private final int WAIT = 4000;
                    @Override
                    public void run() {
                        int attente = 6;
                        double start = System.nanoTime();
                        while(true){
                            update();
                            try {
                                Thread.sleep(WAIT);
                            } catch (InterruptedException ex) {
                                System.out.println("Erreur dans la boucle du slide");
                            }
                        }
                    }

                }
        );
        t.start();
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        if(img!=null)
            g2.drawImage(img, 5, 5,getWidth()-10,getHeight()-10, null);
    }
    public void update(){
        progress = (++progress%4);
        String c = "";
        switch (progress){
            case 0:
                c = "un";
                break;
            case 1:
                c = "deux";
                break;
            case 2:
                c = "trois";
                break;
            default:
                c = "quatre";
                break;
        }
        try {
            img = ImageIO.read(new File(c+".jpg"));
        } catch (IOException e) {
            System.out.println("l imagane est innexistante "+c+".jpg");
        }
        this.repaint();
    }
}
