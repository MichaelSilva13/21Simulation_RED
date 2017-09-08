/*
 * Created by JFormDesigner on Wed Feb 08 16:29:38 EST 2017
 */

package graphiques;

import java.awt.event.*;
import physique.ModelePhysique;
import physique.Outils;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * @author Gabrielle Lim
 * Classe permettant de dessiner un graphique.
 */
public class Graph extends JPanel {
	private static final long serialVersionUID = 1L;

	/**
     * Constructeur d'un graphique
     */
    public Graph() {
        initComponents();
    }

    /**
     * Méthode qui permet de savoir la position de la souris lorsque celle-ci bouge
     * @param e instance de la classe MouseEvent
     */
    private void thisMouseMoved(MouseEvent e) {
        updatePositionSouris(new Point(e.getX(), e.getY()));
    }

    /**
     * Méthode qui permet de savoir lorsque la souris n'est plus sur le graphique
     * @param e instance de la classe MouseEvent
     */
    private void thisMouseExited(MouseEvent e) {
        updatePositionSouris(null);
    }

    /**
     * Initialise les composants
     */
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license

        //======== this ========
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                thisMouseMoved(e);
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                thisMouseExited(e);
            }
        });
        setLayout(null);

        setPreferredSize(new Dimension(400, 300));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private Path2D.Double axes, grille;
    private final double X_MIN = 0,X_MAX = 10;
    private double x_max = X_MAX;
    private double x_min = X_MIN;
    private final double Y_MIN = -5, Y_MAX = 100;
    private final double DEPLACEMENT_Y=-5;
    private final double ESPACEMENT = 5;

    private FontMetrics fontMetrics;
    private final Font fontGras = new Font("Arial", Font.BOLD, 14);
    private final String[] unitesAxeY = {"Pourcentage (%)", "Temps d'attente (s)"};
    private final String uniteAxeX = "Temps (s)";
    private final String uniteXSouris = "s";
    private final String[] unitesYSouris = {"%", "s"};
    private String uniteYSouris = unitesYSouris[0];
    private String uniteAxeY = unitesAxeY[0];
    private ArrayList<Point2D.Double> points = new ArrayList<>();
    private Path2D.Double ligne;
    private Point positionSouris;
    private String textSouris;
    private ModelePhysique modele;
    private double positionXReel, positionYReel;

    /**
     * Méthode qui dessine le graphique
     * @param g Le composant graphique
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        modele = new ModelePhysique(getWidth(), getHeight(), x_min, Y_MIN, (x_max-x_min), (Y_MAX-Y_MIN));
        AffineTransform mat = modele.getMatMC();
        fontMetrics = g2d.getFontMetrics();

        creerAxes();
        creerGrille();
       // creerLigne();

        g2d.translate(0, getHeight() + (Y_MIN+DEPLACEMENT_Y)*mat.getScaleY()); //pour recentrer au bon endroit

        g2d.scale(1, -1); 	//pour inverser le sens des y

        //on dessine la grille
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.draw(mat.createTransformedShape(grille));

        //on dessine les axes
        g2d.setColor(Color.blue);
        g2d.draw(mat.createTransformedShape(axes));

        //on dessine la ligne
        //g2d.setColor(Color.GREEN);
        //g2d.draw(mat.createTransformedShape(ligne));
        creerLigne(g2d, mat);

        g2d.scale(1, -1); //doit être effectué sinon l'écriture est à l'envers
        g2d.setColor(Color.WHITE);
        int o = 0;
        for (double k = Y_MIN + 5; k <= (Y_MAX+getHeight()); k+=ESPACEMENT) {
            String unite = (int)(Y_MIN + 10 + ESPACEMENT*o) + "";
            g2d.drawString(unite, 0, (float) -(k*modele.getPixelsParUniteY()));
            o++;
        }

        creerUniteGraduation(g2d);
        g2d.setFont(fontGras);
        g2d.drawString(uniteAxeX, (float) getWidth()/2 - fontMetrics.stringWidth(uniteAxeX),(float) (9*modele.getPixelsParUniteY()));

        if(positionSouris != null && modele!=null) {
            double positionX = positionSouris.getX();
            if((positionX + fontMetrics.stringWidth(textSouris)) < getWidth() && hasFocus()) {
                g2d.drawString(textSouris, (float)positionX, (float)(-(positionYReel*modele.getPixelsParUniteY())+fontMetrics.getAscent()));
            } else {
                g2d.drawString(textSouris, (float)positionX-fontMetrics.stringWidth(textSouris), (float)(-(positionYReel*modele.getPixelsParUniteY())+fontMetrics.getAscent()));
            }
        }

        g2d.rotate(Math.toRadians(90));
        g2d.translate(-getWidth()/2, getHeight()/2 + 2*modele.getPixelsParUniteX());
        g2d.drawString(uniteAxeY, 0, -getHeight()/2);

    }

    /**
     * Méthode qui crée les axes d'un graphique afin que seulement le premier quadrant soit affiché
     */
    private void creerAxes() {
        axes = new Path2D.Double ();
        axes.moveTo( x_min, 0 );
        axes.lineTo( x_max,  0 );
        axes.moveTo( 0,  Y_MIN );
        axes.lineTo( 0,  Y_MAX );
        for(double j = Y_MIN; j <= Y_MAX; j+=ESPACEMENT) {
            axes.moveTo(-0.2, j);
            axes.lineTo(0.2, j);
        }
        for(double i = -1; i<= x_max; i+=x_max/10){
            axes.moveTo(i, -0.5);
            axes.lineTo(i, 0.5);
        }
    }

    /**
     * Méthode qui crée la grille des graphiques pour aider à mieux visualiser les valeurs
     */
    private void creerGrille() {
        grille = new Path2D.Double();
        grille.moveTo( -1, 0 );
        grille.lineTo( -1,  getHeight());
        for(double i = Y_MIN; i<=(Y_MAX)+getHeight(); i+=ESPACEMENT){
            grille.moveTo(0, i);
            grille.lineTo(x_max, i);
        }
        for(double i = 0; i<= x_max; i+= x_max/10) {
            grille.moveTo(i, 0);
            grille.lineTo(i, getHeight()+Y_MAX);
        }
    }

    /**
     * Méthode qui permet de dessiner la ligne dans le graphique
     */
    private void creerLigne(Graphics2D g2d, AffineTransform mat){
        ligne = new Path2D.Double();

        if(!points.isEmpty()) {
            ligne.moveTo(points.get(0).getX(), points.get(0).getY());


            for (int i = 1; i < points.size(); i++) {
                ligne.lineTo(points.get(i).getX(), points.get(i).getY());

                if(uniteAxeY.equalsIgnoreCase(unitesAxeY[0])) {
                    if (points.get(i).getY() > 75 && points.get(i).getY() <= 100) {
                        g2d.setColor(new Color(57, 255, 24));
                    } else {
                        if (points.get(i).getY() <= 75 && points.get(i).getY() > 50) {
                            g2d.setColor(new Color(254, 255, 28));
                        } else {
                            if (points.get(i).getY() <= 50 && points.get(i).getY() > 25) {
                                g2d.setColor(new Color(255, 131, 10));
                            } else {
                                if (points.get(i).getY() <= 25 && points.get(i).getY() > 5) {
                                    g2d.setColor(new Color(255, 0, 12));
                                } else {
                                    g2d.setColor(new Color(239, 248, 255));
                                }
                            }
                        }
                    }
                } else {
                    g2d.setColor(Color.GREEN);
                }

                g2d.draw(mat.createTransformedShape(ligne));
            }
        }
    }

    /**
     * Méthode qui permet d'afficher les graduations de l'axe des x
     * @param g2d le composant graphique utilisé
     */
    private void creerUniteGraduation(Graphics2D g2d){
        double j=1;
        for (int i = (int)(x_max/10); i < x_max; i+= x_max/10) {
            double unite = ((x_max/10)*j)/1000; //diviseé par mille pour mettre en secondes
            String uniteStr = Outils.ajusterPrecision(unite, 1) + "";
            g2d.drawString(uniteStr, (float)(i*modele.getPixelsParUniteX()), (float)(0 + ESPACEMENT*modele.getPixelsParUniteY()));
            j++;
        }
    }

    /**
     * Méthode qui permet de modifé l'unité de l'axe des Y
     * @param index l'index de l'unité désiré
     */
    public void setUniteAxeY(int index){
        this.uniteAxeY = unitesAxeY[index];
        this.uniteYSouris = unitesYSouris[index];
    }

    /**
     * Méthode qui permet de rajouter des points au graphique au fur et à mesure que le temps passe
     * @param energie le niveau d'énergie dans le réseau
     * @param temps le temps écoulé dans la simulation
     */
    public void updateValeurGraphique(double energie, double temps) {
        points.add(new Point2D.Double(temps, energie));
        recalculerMaxX();
        repaint();
    }

    /**
     * Méthode permettant de modifier le text qui va être affiché à la position de la souris
     * @param e instance de la classe ActionEvent
     */
    private void updatePositionSouris(Point e){
        positionSouris = e;
        if(positionSouris != null && modele!=null) {
            if(positionSouris.getX()!=0) {
                positionXReel = (positionSouris.getX()/modele.getPixelsParUniteX())/1000;
            }
            positionYReel = (positionSouris.getY()/modele.getPixelsParUniteY()-100)*-1;
            textSouris = "x : " + Outils.ajusterPrecision(positionXReel, 1) + " " + uniteXSouris + "; y : " + Outils.ajusterPrecision(positionYReel, 1) + " " + uniteYSouris;
        }
        repaint();
    }

    /**
     * Méthode qui permet de recalculer la plus grande valeur de x dans le graphique
     */
    private void recalculerMaxX() {
        if(points.get(points.size()-1).getX() > x_max) {
            x_max = points.get(points.size()-1).getX();
        }
    }

}
