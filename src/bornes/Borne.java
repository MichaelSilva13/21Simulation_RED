package bornes;

import ecouteurs.AttenteListener;
import ecouteurs.BorneListener;
import ecouteurs.EVListener;
import ecouteurs.VehicListener;
import evenement.RechercheBornesPossibles;
import maps.Edge;
import maps.LaneEdgeLien;
import physique.Vecteur;
import vehicules.VehicElect;

import javax.imageio.ImageIO;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;

import static graphiques.CalculDelai.*;

/**
 * @author Gabrielle Lim
 * @author Michael Oliveira-Silva
 * Classe contenant les informations d'une borne
 */
public class Borne implements Runnable{
    private Point2D.Double position;
    private Double positonRapportLane;
    private VehicElect vehicOccuper;
    private Thread borne;
    //Valeur temporaire de charge pour le moment
    private double charge = 100;
    private LinkedList<VehicElect> fileAttente = new LinkedList<>();
    private double chargeAEnlever;
    private boolean occupee, reserver;
    private final EventListenerList SAVED_OBJECTS = new EventListenerList();
    private boolean imageDessine=true;
    private String lane;
    private static Image[] imagesBornes;
    private boolean firstTime = true;
    private maps.Node nodeAssocie;
    private Rectangle2D carre, carreSelectionne;
    private boolean dessin = true;
    private ArrayList<maps.Node> nodePosition = new ArrayList<>();
    private int LARGEUR_BORNE = 3, HAUTEUR_BORNE = 6;
    private double LARGEUR_BORNE_SELECT = 3.2, HAUTEUR_BORNE_SELECT = 6.2;
    private double CHARGE_PAR_DEFAUT = 100;
    private boolean selected = false;
    private double centrale;
    private double duration;
    private Attente att = new Attente();
    private double tempsAttente;

    /**
     * Constructeur permettant de créer un objet Borne
     * @param position la position de la borne
     * @param indexType un chiffre permettant de savoir la valeur en volt de la borne (le type de borne)
     * @param lane le id de la lane (route) sur laquelle se trouve la borne
     * @throws IOException exceptions
     */
    //Gabrielle Lim
    public Borne(Point2D.Double position, int indexType, String lane) throws IOException {
        chargeImages();
        this.positonRapportLane = position.getX();
        this.position = position;
        this.charge = CHARGE_PAR_DEFAUT;
        this.lane = lane;
        this.carre = new Rectangle2D.Double(position.getX(), position.getY(), LARGEUR_BORNE ,HAUTEUR_BORNE);
        this.carreSelectionne = new Rectangle2D.Double(position.getX() - 0.1, position.getY() - 0.1, LARGEUR_BORNE_SELECT, HAUTEUR_BORNE_SELECT);
    }

    /**
     * Méthode qui indique si la borne est réservée par un véhicule
     * @param vehicule véhicule qui a réservé la borne
     */
    //Michael Oliveira-Silva
    public void recoitDemande(VehicElect vehicule){
        this.vehicOccuper = vehicule;
        vehicOccuper.addEVListener(new EVListener() {
            @Override
            public void demandeEffectuer() {
                System.out.println(this.toString()+" réservée");
                reserver=true;
            }
        });
        vehicOccuper.addVehicListener(new VehicListener() {
            @Override
            public void vehicArriver() {
                occupee=true;
                System.out.println(vehicOccuper.getID()+": Est arrivé à " + this.toString());
            }
        });
        reserver = true;
        leverEventReservationEffect();
        att.addArriveListener(new AttenteListener() {
            @Override
            public void arriveListener() {
                if(!att.isArriv())
                    reserver=false;
            }
        });
    }

    /**
     * Ajoute un écouteur d'évènements
     * @param objEcouteurs écouteur
     */
    //Michael Oliveira-Silva
    public void addBorneListener(BorneListener objEcouteurs){
        SAVED_OBJECTS.add(BorneListener.class, objEcouteurs);
    }

    /**
     * Lève un écouteur lorsque la borne à été réservé
     */
    //Michael Oliveira-Silva
    private void leverEventReservationEffect(){
        for(BorneListener ecout : SAVED_OBJECTS.getListeners(BorneListener.class)){
            ecout.reservationEffectuer(vehicOccuper);
        }
    }

    /**
     * Lève l'écouteur qui dit si la recharge du véhicule par la borne est terminé
     */
    //Michael Oliveira-Silva
    private void leverEventChargementTerminer(){
        for(BorneListener ecout : SAVED_OBJECTS.getListeners(BorneListener.class)){
            ecout.chargementTerminer();
        }
    }

    /**
     * Méthode qui va diminuer la charge de la borne pendant la recharge d'un véhicule
     */
    //Michael Oliveira-Silva
    @Override
    public void run() {
        double chargeEnleve = 0;
        while(occupee&&chargeEnleve<10000){
            ReseauCentrale.getCentrales().get((int)centrale-1).dechargement(1);
            chargeEnleve++;
            tempsAttente--;
            diminutionTempsAttente();
           // System.out.println(ReseauCentrale.getCentrales().get((int)centrale-1)+" (" + ReseauCentrale.getCentrales().get((int)centrale-1).getQuantiteEnergie() + "Wh)");
            try{
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        leverEventChargementTerminer();
        RechercheBornesPossibles.removeBorne(this);
        reserver=false;
        occupee=false;
        this.chargeAEnlever=0;
        if(fileAttente.size()>0)
            demarreCharge();
    }

    /**
     * Méthode qui sert à dessiner les bornes de la bonne couleur si c'est l'option de ne pas afficher les images est choisie ou de diriger vers la méthode qui dessine
     * les images
     * @param g2d le composant Graphique utilisé
     * @param mat la matrice de transformation utilisé
     */
    //Gabrielle Lim
    private void dessinerBorne(Graphics2D g2d, AffineTransform mat) {
        if(this.dessin) {
            if (isDrawImage()) {
                dessineImages(g2d, mat);
            }
        } else {
            Color colorBefore = g2d.getColor();

            if (!isSelected()) {
                if (charge > 75 && charge <= 100) {
                    g2d.setColor(new Color(57, 255, 24));
                } else {
                    if (charge <= 75 && charge > 50) {
                        g2d.setColor(new Color(254, 255, 28));
                    } else {
                        if (charge <= 50 && charge > 25) {
                            g2d.setColor(new Color(255, 131, 10));
                        } else {
                            if (charge <= 25 && charge > 5) {
                                g2d.setColor(new Color(255, 0, 12));
                            } else {
                                g2d.setColor(new Color(239, 248, 255));
                            }
                        }
                    }
                }
            } else {
                g2d.setColor(new Color(209, 0, 255));
            }

            g2d.fill(mat.createTransformedShape(carreSelectionne));

            switch ((int)centrale) {
                case 1 : g2d.setColor(new Color(5, 99, 176));
                    break;
                case 2 : g2d.setColor(new Color(255, 255, 255));
                    break;
                case 3 : g2d.setColor(new Color(138, 0, 255));
                    break;
            }
            g2d.fill(mat.createTransformedShape(carre));
            g2d.setColor(colorBefore);
        }
    }

    //Gabrielle Lim
    /**
     * Méthode qui sert à dessiner les images des bornes
     * @param g2d le composant graphique sélectionné
     * @param mat la matrice de transformation choisie
     */
    private void dessineImages(Graphics2D g2d, AffineTransform mat) {

        AffineTransform g2dInit = g2d.getTransform();

        Shape carreTransfo = mat.createTransformedShape(carre);
        Rectangle carreCoord = carreTransfo.getBounds();

        Image imageADess;
        if(centrale == 1) {
            if(charge > 75 && charge <= 100) {
                imageADess = imagesBornes[4];
            } else {
                if(charge<= 75 && charge > 50) {
                    imageADess = imagesBornes[3];
                } else {
                    if(charge <=50 && charge > 25) {
                        imageADess = imagesBornes[2];
                    } else {
                        if(charge <= 25 && charge > 5) {
                            imageADess = imagesBornes[1];
                        } else {
                            imageADess = imagesBornes[0];
                        }
                    }
                }
            }
        } else {
            if (centrale == 2) {
                if(charge > 75 && charge <= 100) {
                    imageADess = imagesBornes[9];
                } else {
                    if(charge<= 75 && charge > 50) {
                        imageADess = imagesBornes[8];
                    } else {
                        if(charge <=50 && charge > 25) {
                            imageADess = imagesBornes[7];
                        } else {
                            if(charge <= 25 && charge > 5) {
                                imageADess = imagesBornes[6];
                            } else {
                                imageADess = imagesBornes[5];
                            }
                        }
                    }
                }
            } else {
                if(charge > 75 && charge <= 100) {
                    imageADess = imagesBornes[14];
                } else {
                    if(charge<= 75 && charge > 50) {
                        imageADess = imagesBornes[13];
                    } else {
                        if(charge <=50 && charge > 25) {
                            imageADess = imagesBornes[12];
                        } else {
                            if(charge <= 25 && charge > 5) {
                                imageADess = imagesBornes[11];
                            } else {
                                imageADess = imagesBornes[10];
                            }
                        }
                    }
                }
            }
        }

        if(selected){
           Color couleurInitiale = g2d.getColor();
           g2d.setColor(Color.WHITE);
           g2d.fill(mat.createTransformedShape(carreSelectionne));
           g2d.setColor(couleurInitiale);
        }

        g2d.drawImage(imageADess, (int)carreCoord.getX(), (int)carreCoord.getY(), (int)carreTransfo.getBounds().getWidth(), (int)carreTransfo.getBounds().getHeight(), null);
        g2d.setTransform(g2dInit);
    }

    //Gabrielle Lim
    /**
     * Méthode qui charge les images des bornes
     * @throws IOException exceptions
     */
    private void chargeImages() throws IOException {
        if(isDrawImage()){
            imagesBornes = new Image[15];

            //Images Centrale1
            URL borneVide = getClass().getClassLoader().getResource("images/BorneVide.png");
            URL borne25 = getClass().getClassLoader().getResource("images/borne25.png");
            URL borne50 = getClass().getClassLoader().getResource("images/borne50.png");
            URL borne75 = getClass().getClassLoader().getResource("images/borne75.png");
            URL borne100 = getClass().getClassLoader().getResource("images/borne100.png");

            //Images Centrale2
            URL borneVideC2 = getClass().getClassLoader().getResource("images/BorneVideCentrale2.png");
            URL borne25C2 = getClass().getClassLoader().getResource("images/borne25Centrale2.png");
            URL borne50C2 = getClass().getClassLoader().getResource("images/borne50Centrale2.png");
            URL borne75C2 = getClass().getClassLoader().getResource("images/borne75Centrale2.png");
            URL borne100C2 = getClass().getClassLoader().getResource("images/borne100Centrale2.png");

            //Image Centrale3

            URL borneVideC3 = getClass().getClassLoader().getResource("images/BorneVideCentrale3.png");
            URL borne25C3 = getClass().getClassLoader().getResource("images/borne25Centrale3.png");
            URL borne50C3 = getClass().getClassLoader().getResource("images/borne50Centrale3.png");
            URL borne75C3 = getClass().getClassLoader().getResource("images/borne75Centrale3.png");
            URL borne100C3 = getClass().getClassLoader().getResource("images/borne100Centrale3.png");
            try {
                //Image Centrale1
                Image imgVide = ImageIO.read(borneVide);
                Image img25 = ImageIO.read(borne25);
                Image img50 = ImageIO.read(borne50);
                Image img75 = ImageIO.read(borne75);
                Image img100 = ImageIO.read(borne100);
                imagesBornes[0] = imgVide;
                imagesBornes[1] = img25;
                imagesBornes[2] = img50;
                imagesBornes[3] = img75;
                imagesBornes[4] = img100;

                //Image Centrale2
                Image imgVideC2 = ImageIO.read(borneVideC2);
                Image img25C2 = ImageIO.read(borne25C2);
                Image img50C2 = ImageIO.read(borne50C2);
                Image img75C2 = ImageIO.read(borne75C2);
                Image img100C2 = ImageIO.read(borne100C2);
                imagesBornes[5] = imgVideC2;
                imagesBornes[6] = img25C2;
                imagesBornes[7] = img50C2;
                imagesBornes[8] = img75C2;
                imagesBornes[9] = img100C2;

                //Image Centrale3
                Image imgVideC3 = ImageIO.read(borneVideC3);
                Image img25C3 = ImageIO.read(borne25C3);
                Image img50C3 = ImageIO.read(borne50C3);
                Image img75C3 = ImageIO.read(borne75C3);
                Image img100C3 = ImageIO.read(borne100C3);
                imagesBornes[10] = imgVideC3;
                imagesBornes[11] = img25C3;
                imagesBornes[12] = img50C3;
                imagesBornes[13] = img75C3;
                imagesBornes[14] = img100C3;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //Gabrielle Lim
    /**
     * Retourne si l'image est dessinée
     * @return si l'image est dessinée, true si oui, false dans le cas contraire
     */
    private boolean isDrawImage() {
        return imageDessine;
    }

    //Gabrielle Lim
    /**
     * Méthode qui sert à modifier les coordonnées des bornes (car celles-ci sont données en fonction de la route où elle se trouve)
     * avant que celles-ci soit dessiner grâce à des positions en x et y dans la carte
     * @param g2d le composant graphique utilisé
     * @param mat la matrice de transformation choisie
     * @param positionIntersection le ArrayList de Node contenant toutes les positions d'intersection des routes
     * @param laneEdgeLiens le ArrayList de LaneEdgeLien qui permet de faire les liens entre les routes et les positions d'intersection
     */
    public void dessiner(Graphics2D g2d, AffineTransform mat, ArrayList<maps.Node> positionIntersection, ArrayList<LaneEdgeLien> laneEdgeLiens) {

        if(firstTime) {
            this.nodePosition = positionIntersection;
            String idLane = getLane().substring(0, getLane().length() - 2);
            for(int k=0; k<laneEdgeLiens.size(); k++){
                LaneEdgeLien lien = laneEdgeLiens.get(k);
                String idL = lien.getIdLane();
                if(idL.equalsIgnoreCase(idLane)){
                    Edge edgelien = lien.getEdge();
                    String from = edgelien.getFrom();
                    for(int g=0; g<nodePosition.size(); g++){
                        maps.Node node = nodePosition.get(g);
                        String idNodeFrom = node.getId();
                        if(idNodeFrom.equalsIgnoreCase(from)){
                            Point2D.Double positionNodeFrom = node.getPosition();
                            this.nodeAssocie = node;
                            for (int i = 0; i < nodePosition.size(); i++) {
                                maps.Node nodeto = nodePosition.get(i);
                                String idNodeTo = nodeto.getId();
                                String to = edgelien.getTo();
                                if(idNodeTo.equalsIgnoreCase(to)){
                                    Point2D.Double positionNodeTo = nodeto.getPosition();

                                    double coordYVecteur = positionNodeTo.getY() - positionNodeFrom.getY();
                                    double coordXVecteur = positionNodeTo.getX() - positionNodeFrom.getX();
                                    Vecteur directionLane = new Vecteur(coordXVecteur, coordYVecteur);
                                    double moduleVectDirect = directionLane.module();
                                    double xBorne = position.getX();
                                    double proportion = xBorne/moduleVectDirect;
                                    Vecteur positionInitiale = new Vecteur(positionNodeFrom.getX(), positionNodeFrom.getY());
                                    Vecteur deplacement = Vecteur.multiplie(directionLane, proportion);
                                    Vecteur nouvellePos = Vecteur.additionne(positionInitiale, deplacement);

                                    position = new Point2D.Double(nouvellePos.getX(), nouvellePos.getY());
                                    setCarre(new Rectangle2D.Double(position.getX(), position.getY(),LARGEUR_BORNE,HAUTEUR_BORNE));
                                    setCarreSelectionne(new Rectangle2D.Double(position.getX()-0.1, position.getY()-0.1, LARGEUR_BORNE_SELECT, HAUTEUR_BORNE_SELECT));
                                    firstTime = false;
                                }
                            }
                        }
                    }
                }
            }
        }

        dessinerBorne(g2d, mat);
    }

    //Gabrielle Lim
    /**
     * Méthode qui retourne le id de la lane (route)
     * @return lane le id de la lane (route)
     */
    private String getLane() {
        return lane;
    }

    //Gabrielle Lim
    /**
     * Méthode qui permet de modifié le carre dessiné à l'endroit de la borne
     * @param carre le nouveau carre que l'on souhaite dessiner
     */
    private void setCarre(Rectangle2D carre) {
        this.carre = carre;
    }

    //Gabrielle Lim
    /**
     * Méthode qui permet de savoir le niveau de charge dans la borne
     * @return le niveau de charge dans la borne
     */
    public double getCharge() {
        return charge;
    }

    //Gabrielle Lim
    /**
     * Méthode qui permet de modifier le niveau de charge dans la borne
     * @param charge le nouveau niveau de charge (en pourcentage)
     */
    public void setCharge(double charge) {
        this.charge = charge;
    }

    //Gabrielle Lim
    /**
     * Méthode qui permet de savoir la position de la borne
     * @return la position de la borne
     */
    public Point2D.Double getPosition() {
        return position;
    }

    //Gabrielle Lim
    /**
     * Méthode qui permet de savoir la position de la borne par rapport à la route sur laquelle elle se trouve
     * @return position par rapport à la route
     */
    public Double getPositonRapportLane() {
        return positonRapportLane;
    }

    //Gabrielle Lim
    /**
     * Méthode qui permet de retourner le carre associé à la borne
     * @return le carre associé à la borne
     */
    public Rectangle2D getCarre() {
        return carre;
    }

    //Gabrielle Lim
    /**
     * Méthode permettant de savoir si la borne est sélectionnée ou non
     * @return vrai si la borne est sélectionnée, faux dans le cas contraire
     */
    private boolean isSelected() {
        return selected;
    }

    //Gabrielle Lim
    /**
     * Méthode permettant de modifié si la borne est sélectionné ou non
     * @param selected vrai si la borne est sélectionnée, faux dans le cas contraire
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    //Gabrielle Lim
    /**
     * Méthode permettant d'assigner une centrale à la borne
     * @param centrale numéro de 0 à 2 (identifiant de la centrale correspondante)
     */
    public void setCentrale(double centrale) {
        this.centrale = centrale;
    }

    //Gabrielle Lim
    /**
     * Méthode permettant de savoir à quelle centrale la borne est associée.
     * @return la centrale correspondante (index de la centrale)
     */
    public double getCentrale() {
        return centrale;
    }

    //Gabrielle Lim
    /**
     * Méthode qui retourne une String des informations de la borne
     * @return une String contenant les informations de la borne
     */
    public String getInfo() {
        return "Centrale : " + centrale + "\n \n" +
                "Niveau de Charge : " + charge + " %\n\n" +
                "Occupée : " + occupee + "\n \n" +
                "Réservée : " + reserver;
    }

    //Gabrielle Lim
    /**
     * Méthode permettant de modifié le carré qui indique si la borne est sélectionnée ou non
     * @param carreSelectionne le nouveau carré Sélectionné
     */
    private void setCarreSelectionne(Rectangle2D carreSelectionne) {
        this.carreSelectionne = carreSelectionne;
    }

    //Gabrielle Lim
    /**
     * Méthode qui permet de modifié si les images des bornes vont être dessiné ou non
     * @param dessin vrai si les images sont dessiné, faux dans le cas contraire
     */
    public void setDessin(boolean dessin) {
        this.dessin = dessin;
    }

    /**
     * Retourne le node associé à la borne
     * @return le node associé
     */
    //Michael
    public maps.Node getNodeAssocie() {
        return nodeAssocie;
    }

    /**
     * Retourne si la borne est occupée;
     * @return si la borne est occupée ou non
     */
    //Michael
    public boolean isOccupee() {
        return occupee;
    }

    /**
     * Retourne si la borne est réservée
     * @return si la borne est réservée ou non
     */
    //Michael
    public boolean isReserver() {
        return reserver;
    }

    /**
     * Détermine si la borne est réservée ou non
     * @param reserver si la borne est réservée ou non, true si elle est réservée, false dans le cas contraire
     */
    //Michael
    public void setReserver(boolean reserver, VehicElect evAtt) {
        this.reserver = reserver;
        att.start();
    }

    /**
     * Définis un véhicule qui occupe la borne
     * @param vehicOccuper le véhicule qui occupe la borne
     */
    //Michael
    public void setVehicOccuper(VehicElect vehicOccuper) {
        this.vehicOccuper = vehicOccuper;
        fileAttente.add(vehicOccuper);
        att.setArriv(true);
    }

    /**
     * Démarre la charge du véhicule
     */
    //Michael
    public void demarreCharge(){
        if(fileAttente.size()>0)
            vehicOccuper=fileAttente.remove();
        setChargeAEnlever();
        if(!occupee) {
            borne = new Thread(this, "borne");
            occupee = true;
            double startTime = System.nanoTime();
            borne.start();
            double endTime = System.nanoTime();
            duration = (endTime - startTime);
            setTempsChargement(duration);
            tempsAttente = chargeAEnlever*100;
        } else {
            calculTempsAttente(tempsAttente);
            tempsAttente+=chargeAEnlever*100;
        }
    }

    /**
     * Détermine le niveau d'énergie que la borne doit donner au véhicule
     */
    //Michael
    private void setChargeAEnlever(){
        double chargeRestPercent = (vehicOccuper.getCharge()/vehicOccuper.getChargeMax());
        double chargeKwh = chargeRestPercent*vehicOccuper.getChargeMaxKwh();
        vehicOccuper.setChargeKwH(chargeKwh);
        double chargeRest = vehicOccuper.getChargeMaxKwh()-chargeKwh;
        if(chargeRestPercent<=1&&chargeRestPercent>=0.5){
            chargeAEnlever=chargeRest*chargeRestPercent;
        } else if(chargeRestPercent<0.5&&chargeRestPercent>=0){
            chargeAEnlever=chargeRest*(1-chargeRestPercent);
        }
    }

    /**
     * Définis le temps d'attente du véhicule à la borne
     * @param tempsAtt temps d'attente
     */
    //Michael
    public void setTempsAtt(double tempsAtt) {
        this.att.setTempsMax((long) tempsAtt);
    }

    /**
     * Retourne la classe d'attente
     * @return l'attente du véhicule à la borne
     */
    //Michael
    public Attente getAtt() {
        return att;
    }

    /**
     * Retourne le temps d'attente du véhicule à la borne
     * @return le temps d'Attente
     */
    public double getTempsAttente() {
        return tempsAttente;
    }

    /**
     * Définis si la borne est occupée ou non
     * @param occupee si la borne est occupée ou non
     */
    public void setOccupee(boolean occupee) {
        this.occupee = occupee;
    }

    /**
     * Retourne si la borne est occupée ou non
     * @return le statut de réservation
     */
    public boolean getOccupee() {
        return this.occupee;
    }

    public LinkedList<VehicElect> getFileAttente() {
        return fileAttente;
    }
}