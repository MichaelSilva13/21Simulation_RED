/*
 * Created by JFormDesigner on Wed Feb 08 16:29:50 EST 2017
 */

package graphiques;

import physique.ModelePhysique;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * @author Gabrielle Lim
 * Classe permettant de dessiner un diagramme.
 */
public class Diagramme extends JPanel {
	private static final long serialVersionUID = 1L;

	/**
     * Constructeur d'un Diagramme
     */
    public Diagramme() {
        initComponents();
        setBackground(Color.WHITE);
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

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private Path2D.Double axes, grille;
    private Rectangle2D.Double bandes;
    private ArrayList<Rectangle2D.Double> tabBandes = new ArrayList<Double>();
    private final double Y_MIN = -1, Y_MAX = 100;
    private final double X_MIN = -1, X_MAX = 100;
    private final double DEPLACEMENT_Y=-1;
    private final double DEPLACEMENT_X=3;
    private final double ESPACEMENT_AXES=5;
    private final double ESPACEMENT_BANDES=1;
    private final String uniteAxeY = "Pourcentage (%)";
    private double largeurBandes;
    private final Font fontGras = new Font("Arial", Font.BOLD, 14);
    private double utilisationBorneTot = 0;
    private double utilisationCentrale1 = 0;
    private double utilisationCentrale2 = 0;
    private double utilisationCentrale3 = 0;
    private double[] pourcentageUtilisation = new double[] {0, 0, 0};

    private final double nbBandes = 3;

    /**
     * Méthode qui dessine le graphique
     * @param g Le composant graphique
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        ModelePhysique modele = new ModelePhysique(getWidth(), getHeight(), X_MIN, Y_MIN, (X_MAX - X_MIN), (Y_MAX - Y_MIN));
        AffineTransform mat = modele.getMatMC();
        g2d.getFontMetrics();

        creerGrilleHorizontaleSeulement();
        creerAxes();
        creerBandes(nbBandes, pourcentageUtilisation);

        g2d.translate((X_MIN + DEPLACEMENT_X) * mat.getScaleX(), getHeight() + (Y_MIN + DEPLACEMENT_Y) * mat.getScaleY()); //pour recentrer au bon endroit
        g2d.scale(1, -1);    //pour inverser le sens des y

        //on dessine la grille
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.draw(mat.createTransformedShape(grille));

        //on dessine les axes
        g2d.setColor(Color.blue);
        g2d.draw(mat.createTransformedShape(axes));

        for(int i=0; i<nbBandes; i++) {
            switch (i) {
                case 0 : g2d.setColor(new Color(5, 99, 176));
                break;
                case 1 : g2d.setColor(new Color(255, 255, 255));
                break;
                case 2 : g2d.setColor(new Color(138, 0, 255));
                break;
            }
            g2d.fill(mat.createTransformedShape(tabBandes.get(i)));
        }

        g2d.scale(1, -1); //doit être effectué sinon l'écriture est à l'envers
        g2d.setColor(Color.WHITE);

        int o = 0;
        for (double k = Y_MIN + 1; k <= (Y_MAX+getHeight()); k+=ESPACEMENT_AXES) {
            String unite = (int)(Y_MIN + 1 + ESPACEMENT_AXES*o) + "";
            g2d.drawString(unite, 0, (float) -(k*modele.getPixelsParUniteY()));
            o++;
        }

        g2d.setColor(Color.LIGHT_GRAY);
        g2d.setFont(fontGras);
        for (int j = 0; j < nbBandes; j++) {
            String centrale = "Centrale " + (j+1);
            g2d.drawString(centrale, (float) ((ESPACEMENT_BANDES + ESPACEMENT_BANDES*j + largeurBandes*j + largeurBandes/3.0)* modele.getPixelsParUniteX()), (float) -5);
        }

        g2d.setColor(Color.WHITE);
        g2d.rotate(Math.toRadians(90));
        g2d.translate(-getWidth()/5, getHeight()/2 + 2*modele.getPixelsParUniteX());
        g2d.drawString(uniteAxeY, 0, -getHeight()/2);
    }

    /**
     * Méthode qui crée les axes d'un graphique afin que seulement le premier quadrant soit affiché
     */
    private void creerAxes() {
        axes = new Path2D.Double ();
        axes.moveTo( X_MIN, 0 );
        axes.lineTo( X_MAX,  0 );
        axes.moveTo( 0,  Y_MIN );
        axes.lineTo( 0,  Y_MAX );
        for(double i = X_MIN + 1; i<= X_MAX; i++){
            axes.moveTo(i, -0.5);
            axes.lineTo(i, 0.5);
        }
        for(double k = Y_MIN + 1; k <= Y_MAX; k+=ESPACEMENT_AXES) {
            axes.moveTo(-0.2, k);
            axes.lineTo(0.2, k);
        }
    }

    /**
     * Méthode qui crée la grille pour les diagrammes (seulement en y) pour aider à mieux visualiser les valeurs
     */
    private void creerGrilleHorizontaleSeulement() {
        grille = new Path2D.Double();
        grille.moveTo( -1, 0 );
        grille.lineTo( -1,  getHeight());
        for(double i = Y_MIN + 1; i<=(Y_MAX)+getHeight(); i+=ESPACEMENT_AXES){
            grille.moveTo(-1, i);
            grille.lineTo(X_MAX, i);
        }
    }

    /**
     * Méthode permettant de créer les bandes dans un diagramme à bandes grâce au nombre de bandes et au pourcentage de chaque bande.
     * @param nbBandes Le nombre de bandes à dessiner
     * @param pourcentageChqBande   Un tableau contenant toutes les valeurs (en pourcentage) de chaque bande à dessiner
     */
    private void creerBandes(double nbBandes, double[] pourcentageChqBande) {
        double espacementTotal = nbBandes*ESPACEMENT_BANDES + 1;
        largeurBandes = (Y_MAX - espacementTotal)/nbBandes;

        for(int i=0; i<nbBandes; i++) {
            bandes = new Rectangle2D.Double(ESPACEMENT_BANDES + ESPACEMENT_BANDES*i + largeurBandes*i,0, largeurBandes , pourcentageChqBande[i]);
            tabBandes.add(bandes);
        }
    }

    /**
     * Méthode qui mets à jour les valeurs du diagramme
     * @param numCentrale la centrale qui a une nouvelle utilisation de borne lui appartenant
     */
    public void updateValeursUtilisation(double numCentrale) {
        utilisationBorneTot++;
        switch ((int)numCentrale) {
            case 1 : utilisationCentrale1++;
            break;
            case 2 : utilisationCentrale2++;
            break;
            case 3 : utilisationCentrale3++;
            break;
        }
        pourcentageUtilisation[0] = utilisationCentrale1/utilisationBorneTot * 100;
        pourcentageUtilisation[1] = utilisationCentrale2/utilisationBorneTot * 100;
        pourcentageUtilisation[2] = utilisationCentrale3/utilisationBorneTot * 100;
        repaint();
    }

}
