package vehicules;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.event.EventListenerList;

import dessinable.Dessinable;
import ecouteurs.UpdateListener;
import ecouteurs.VehicListener;
import it.polito.appeal.traci.Edge;
import it.polito.appeal.traci.Vehicle;
import maps.Node;
import physique.Outils;

/**
 * @author Michael Oliveira-Silva
 * Classe possédant les informations sur un véhicule.
 */
public class Vehicule implements Dessinable {

    private static final double STEP_VALUE = 0.319;
    private Point2D.Double position;
    private int ID;
    private boolean isElect = false;
    private Point2D.Double posAnt;
    private Vehicle sumoVehicule;
    private Rectangle2D.Double rect;
    private Node node;
    private boolean dessin;
    private java.util.List<Edge> route = new ArrayList<>();
    private boolean drawImage = true;
    private boolean selected = false;
    private static Image[] imagesVehic;
    private final static double LONGUEUR = 1.7;
    private double vitesse;
    private boolean hovered = false;
    private final EventListenerList SAVED_OBJECTS = new EventListenerList();
    private double orientation;

    /**
     * Crée un ScvVehicle
     *
     * @param position     la position du vehicule
     * @param sumoVehicule vehicule de SUMO equivalent au vehicule SCV
     * @throws IOException exceptions
     */
    // auteur : philippe alexandre (Modifiée par Michael)
    public Vehicule(Point2D.Double position, Vehicle sumoVehicule) throws IOException {
        ID = (int) Math.random()*300;
        setDessin(true);
        chargeImages();
        this.position = position;
        this.sumoVehicule = sumoVehicule;
        this.rect = new Rectangle2D.Double(position.getX(), position.getY(),
                                           LONGUEUR, LONGUEUR);
        //target = sumoVehicule.getCurrentRoute().get(sumoVehicule.getCurrentRoute().size()-1).getID();
        route = sumoVehicule.getCurrentRoute();
        orientation = 0;
    }


    /**
     * dessine un véhicule générique
     * @param g2d Contexte graphique du dessin
     * @param mat Contexte matrice du dessin
     */
    //Michael Oliveira-Silva
    @Override
    public void dessiner(Graphics2D g2d, AffineTransform mat) {
        drawGenericVehicle(g2d, mat);
    }

    /**
     * modifie la position du vehicule
     *
     * @param position la nouvelle position du vehicule
     */
    // auteur : philippe alexandre (Modifiée par Michael)
    public void setPosition(Point2D.Double position) {
        posAnt = this.position;
        this.position = position;
        calculateOrientation();
    }


    /**
     * retourne la position du véhicule
     * @return la position du véhicule
     */
    //Michael Oliveira-Silva
    public Point2D.Double getPosition() {
        return position;
    }

    /**
     * Retourne le véhicule SUMO qui est associé au ce véhicule
     * @return le véhicule SUMO
     */
    //Michael Oliveira-Silva
    public Vehicle getSumoVehicule() {
        return sumoVehicule;
    }


    /**
     * Calcule l'orientation du véhicule
     */
    // auteur : cedryk
    private void calculateOrientation() {
        if (posAnt != null) {
            double dx = position.getX() - posAnt.getX();
            double dy = -position.getY() + posAnt.getY();
            if (!java.lang.Double.isNaN(dy / dx)) {
                orientation = Math.atan(-dy / dx);
                if ((orientation < 0 && dy > 0) || (orientation > 0 && dy < 0)) {
                    orientation += Math.PI;
                } else if (orientation == 0 && dx < 0) {
                    orientation += Math.PI;
                }
            }
        } else {
            orientation = 0;
        }
    }

    /**
     * retourne l'ID du véhicule
     * @return un string montrant l'ID du véhicule
     */
    //Michael Oliveira-Silva
    public String toString() {
        return sumoVehicule.getID();
    }

    /**
     * methode generique qui permet de dessiner differents vehicule en changeant
     * les valeurs entrees en parametre
     *
     * @param g2d   le Graphics2D qui sera utilise pour dessiner le vehicule
     * @param mat   la matrice de transformation servant a dessiner le vehicule
     */
    // auteur : philippe
    private void drawGenericVehicle(Graphics2D g2d, AffineTransform mat) {
        if (this.dessin) {
            updateCarCircle();
            if (isDrawImage()) {
                drawImages(g2d, mat);
        } else {
            Color colorBefore = g2d.getColor();
                AffineTransform g2dInit = g2d.getTransform();
                calculateOrientation();
                Point2D.Double realPosition = (Double) mat.transform(position, null);
                g2d.rotate(orientation, realPosition.getX(), realPosition.getY());
            if(isElect()){
                if(isSelected()){
                    g2d.setColor(new Color(59, 255, 64));
                } else{
                    if(isHovered()){
                        g2d.setColor(new Color(118, 209, 255));
                    }else{
                        g2d.setColor(new Color(0,90,153));
                    }
                }
            } else {
                if (isSelected()) {
                    g2d.setColor(new Color(59, 255, 64));
                } else {
                    if (isHovered()) {
                        g2d.setColor(new Color(250, 250, 250));
                    } else {
                        g2d.setColor(new Color(150, 150, 150));
                    }
                }
            }
            g2d.fill(mat.createTransformedShape(rect));
            g2d.setColor(colorBefore);
            g2d.setTransform(g2dInit);
        }
    }

    }

    /**
     * methode qui dessine les vehicules avec l'image appropriée
     *  @param g2d le Graphics2D qui sera utilise pour dessiner le vehicule
     * @param mat Affine Transform
     *
     */
    // auteur : cedryk modifiée par Michael Oliveira-Silva
    private void drawImages(Graphics2D g2d, AffineTransform mat) {
        Point2D.Double realDestCorner1 = new Point2D.Double();
        Point2D.Double bottomLeftCorner = new Point2D.Double(rect.getX(), rect.getY());
        realDestCorner1 = (Double) mat.transform(bottomLeftCorner, realDestCorner1);

        Point2D.Double realDestCorner2 = new Point2D.Double();
        Point2D.Double topRightCorner = new Point2D.Double(position.getX() + LONGUEUR, rect.getY() + LONGUEUR);
        realDestCorner2 = (Double) mat.transform(topRightCorner, realDestCorner2);

        Point2D.Double realPosition = (Double) mat.transform(position, null);

        AffineTransform g2dInit = g2d.getTransform();
        calculateOrientation();
        g2d.rotate(orientation, realPosition.getX(), realPosition.getY());

        Image imageToDraw = null;
        if (!isElect()) {
            if(isHovered()){
                imageToDraw = imagesVehic[1];
            }else{
                if(isSelected()){
                    imageToDraw = imagesVehic[2];
                }else{
                    imageToDraw = imagesVehic[0];
                }
            }
        } else {
            if(isHovered()){
                imageToDraw = imagesVehic[4];
            }else {
                if (isSelected()) {
                    imageToDraw = imagesVehic[5];
                } else {
                    imageToDraw = imagesVehic[3];
                }
            }
        }
        g2d.drawImage(imageToDraw, (int) (realDestCorner1.getX()),
                      (int) (realDestCorner1.getY()),
                      (int) Math.abs(realDestCorner1.getX()-realDestCorner2.getX()),
                      (int) Math.abs(realDestCorner1.getY()-realDestCorner2.getY()), null);
        g2d.setTransform(g2dInit);

    }

    /**
     * met à jour l'information du véhicule
     * @param listener écouteur de mise à jour
     * @throws IOException exceptions
     */
    //Cedryk
    public void carInformationUpdated(UpdateListener listener) throws IOException {
        listener.carInformationUpdated(position, vitesse);
    }

    /**
     * détermine si le véhicule doit être dessiné ou non
     * @param dessin vrai s'il doit être dessiné, faux dans le cas contraire
     */
    //Michael Oliveira-Silva
    public void setDessin(boolean dessin) {
        this.dessin = dessin;
    }

    /**
     * permet de charger les images des véhicules.
     */
    //Michael Oliveira-Silva
    private void chargeImages() throws IOException {
        if(isDrawImage()){
            imagesVehic = new Image[6];
            URL vehicURL = getClass().getClassLoader().getResource("images/Vehic.png");
            URL EVURL = getClass().getClassLoader().getResource("images/EV.png");
            URL EVHoveredURL = getClass().getClassLoader().getResource("images/EVHovered.png");
            URL selectedVehicURL = getClass().getClassLoader().getResource("images/SelectedVehic.png");
            URL vehicHoveredURL = getClass().getClassLoader().getResource("images/VehicHovered.png");
            URL selectedEVURL = getClass().getClassLoader().getResource("images/SelectedEV.png");
            try {
                Image imgVehic = ImageIO.read(vehicURL);
                Image imgSelectedV = ImageIO.read(selectedVehicURL);
                Image imgVHovered = ImageIO.read(vehicHoveredURL);
                Image imgEV = ImageIO.read(EVURL);
                Image imgSelectedEV = ImageIO.read(selectedEVURL);
                Image imgEVHovered = ImageIO.read(EVHoveredURL);
                imagesVehic[0] = imgVehic;
                imagesVehic[1] = imgSelectedV;
                imagesVehic[2] = imgVHovered;
                imagesVehic[3] = imgEV;
                imagesVehic[4] = imgSelectedEV;
                imagesVehic[5] = imgEVHovered;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * retourne si le véhicule est électrique ou non
     * @return la valeur booléenne si le véhicule est électrique ou non.
     */
    //Michael Oliveira-Silva
    public boolean isElect() {
        return isElect;
    }

    /**
     * détermine si le véhicule est électrique ou non
     * @param elect boolean qui décide si il s'agit un véhicule électrique ou non.
     */
    //Michael Oliveira-Silva
    public void setElect(boolean elect) {
        isElect = elect;
    }

    /**
     * retourne si l'image est dessiné
     * @return drawImage une valeur booléenne qui dit si l'image est dessiner
     */
    //Michael Oliveira-Silva
    private boolean isDrawImage() {
        return drawImage;
    }

    /**
     * détermine si oui ou non les images sont dessiné.
     * @param drawImage qui dit si l'image doit etre dessiné ou non
     */
    //Michael Oliveira-Silva
    public void setDrawImage(boolean drawImage) {
        this.drawImage = drawImage;
    }

    /**
     * retourne une valeur booléenne qui indique si oui ou non la souris survole le véhicule
     * @return un boolean Hovered
     */
    //Michael Oliveira-Silva
    private boolean isHovered() {
        return hovered;
    }

    /**
     * permet de défini si le véhicule est survolé; d'une souris
     * @param hovered oui si le véhicule est survolé, faux dans le cas contraire
     */
    //Michael Oliveira-Silva
    public void setHovered(boolean hovered) {
        this.hovered = hovered;
    }

    /**
     * indique si le véhicule est selectionné par l'utilisateur
     * @return si le véhicule est sélectionné
     */
    //Michael Oliveira-Silva
    public boolean isSelected() {
        return selected;
    }

    /**
     * détermine si le véhicule est sélectionné ou non
     * @param selected booléenne qui détermine si le véhicule est sélectionnée
     */
    //Michael Oliveira-Silva
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * met a jour la valeur du cercle dessine pour representer le véhicule
     */
    // auteur : philippe
    private void updateCarCircle() {
        rect = new Rectangle2D.Double(position.getX() - LONGUEUR / 1.5, position.getY() - LONGUEUR / 2, 1.5*LONGUEUR,
                                      LONGUEUR);
    }

    /**
     * retourne la position au dernier "step" de la simulation
     * @return l'acienne position du véhicule
     */
    public Double getPosAnt() {
        return posAnt;
    }

    /**
     * retourne le rectangle qui représente le véhicule
     * @return le rectangle qui représente le véhicule
     */
    public Rectangle2D.Double getRect() {
        return rect;
    }

    /**
     * retourne une chaîne qui décris les informations du véhicule électrique
     * @return la chaîne d'informations du véhicule
     * @throws IOException exception
     */
    public String getInfo() throws IOException {
        double deplaceX = Math.abs((getPosition().getX()-getPosAnt().getX()));
        double deplaceY = Math.abs((getPosition().getY()-getPosAnt().getY()));
        double dist = Math.sqrt((deplaceX*deplaceX)+(deplaceY*deplaceY))*10;
        this.vitesse = dist / STEP_VALUE;
        String info = "Véhicule ID : " + ID + "\n" + "\n" +
                      "Électrique : Non\n" + "\n" +
                      "Position X : " + Outils.ajusterPrecision(getPosition().getX(), 2) + ", Position Y : " + Outils.ajusterPrecision(getPosition().getY(), 2) + "\n" + "\n" +
                      "Vitesse : " + Outils.ajusterPrecision(vitesse, 2) + "m/s (" + Outils.ajusterPrecision(((vitesse*3600)/1000), 2) + "km/h)" ;
        return info;
    }

    /**
     * retourne l'ID du véhicule
     * @return la chaîne de l'ID
     */
    public int getID() {
        return ID;
    }

    /**
     * retourne la valeur du step
     * @return le step
     */
    public static double getStepValue() {
        return STEP_VALUE;
    }

    /**
     * définis le ID du véhicule
     * @param ID du véhicule
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * définis le Node associé
     * @param n node du véhicule
     */
    public void setNode(Node n){
        node = n;
    }

    /**
     * retourne le Node du véhicule
     * @return le Node
     */
    public Node getNode() {
        return node;
    }

    /**
     * retourne le ID Cible du véhicule
     * @param target la cible du véhicule
     */
    public void setTarget(String target) {
    }

    /**
     * retourne la route du véhicule
     * @return les edges qui seront parcourus par le véhicule
     */
    public List<Edge> getRoute() {
        return route;
    }

    /**
     * définis la route du véhicule
     * @param route les edges qui seront parcouru par le véhicule
     */
    public void setRoute(List<Edge> route) {
        this.route = route;
    }

    /**
     * ajoute un vehicListener
     * @param vehicListener écouteur de Véhicules
     */
    public void addVehicListener(VehicListener vehicListener){
        SAVED_OBJECTS.add(VehicListener.class, vehicListener);
    }

}
