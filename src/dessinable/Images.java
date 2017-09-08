/*
 * Created by JFormDesigner on Fri Feb 10 14:14:20 EST 2017
 */

package dessinable;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Classe permettant de dessiner des images
 * @author Michael Oliveira-Silva
 * modifié par Gabrielle Lim
 */
public class Images extends JPanel {
	private static final long serialVersionUID = 1L;
	private String emplacementLogo = "images/R.E.D.png";
    private String imageNom;

    /**
     * Constructueur d'un objet image qui prend toujours en valeur par défaut l'image de notre logo
     */
    //Michael Oliveira-Silva modifiée par Gabrielle Lim
    public Images() {
        initComponents();
        imageNom = emplacementLogo;
    }

    /**
     * Initialise les composants
     */
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license

        //======== this ========
        setLayout(null);

        setPreferredSize(new Dimension(400, 300));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

    }

    /**
     * Méthode permettant de dessiner l'image
     * @param g le composant graphique
     */
    //Michael Oliveira-Silva modifié par Gabrielle Lim
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        Image img=null;
        URL urlImage= getClass().getClassLoader().getResource(imageNom);
        if (urlImage == null) {
            JOptionPane.showMessageDialog(null , "Fichier" + imageNom + "introuvable");
        }
        try {
            img = ImageIO.read(urlImage);
        }
        catch (IOException e) {
            System.out.println("Erreur pendant la lecture du fichier d'image");
        }
        g2d.drawImage(img, 0,0,getWidth(), getHeight(), null);

    }

    //Gabrielle Lim
    /**
     * Méthode permettant de changer le nom du fichier lu par défaut pour un nouveau
     * @param imageNom Nom du nouveau fichier image
     */
    public void setImageNom(String imageNom){
        this.imageNom = imageNom;
        repaint();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
