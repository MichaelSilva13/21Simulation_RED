/*
 * Created by JFormDesigner on Wed Feb 08 16:21:15 EST 2017
 */

package maps;

import static graphiques.CalculDelai.getDelaiMoyen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import javax.swing.JPanel;
import javax.swing.event.EventListenerList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import bornes.Borne;
import bornes.Centrale;
import bornes.ReseauCentrale;
import dijkstra.Dijkstra;
import dijkstra.GraphDijkstra;
import ecouteurs.BorneListener;
import ecouteurs.UpdateListener;
import evenement.Event;
import evenement.EventList;
import evenement.RechercheBornesPossibles;
import evenement.Update;
import it.polito.appeal.traci.SumoTraciConnection;
import physique.ModelePhysique;
import vehicules.VehicElect;
import vehicules.Vehicule;

/**
 * @author Gabrielle Lim
 * @author Michael Oliveira-Silva
 * Dessine et anime une carte ainsi que les véhicules du réseau
 */
public class Map extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	private boolean firstDrag= true;

    //Gabrielle Lim
    /**
     * Constructeur d'une map
     */
    public Map() {
        initComponents();
    }

    //Michael Oliveira-Silva
    /**
     * Méthode permettant d'éffectuer un agrandissement (zoomer) sur la carte
     * @param e instance de la classe Action Event
     */
    private void thisMouseWheelMoved(MouseWheelEvent e) {
        int nbRot = e.getWheelRotation();
        modelePhysique.translate(1, -getHeight()/3.3);
        zoom(nbRot, e.getPoint());
        modelePhysique.translate(1, getHeight()/3.3);
        repaint();
    }

    //Michael Oliveira-Silva
    /**
     * Méthode permettant d'effectuer une translation sur la carte
     * @param e instance de la classe Action Event
     */
    private void thisMouseDragged(MouseEvent e) {
        if(firstDrag){
            initialPoint = new Point(e.getX(), e.getY());
            firstDrag = false;
        }
        translate(e.getPoint(), initialPoint);
        repaint();
        initialPoint = e.getPoint();
    }

    /**
     * Méthode permettant de savoir lorsqu'un click a été effectué sur la carte
     * @param e instance de la classe Action Event
     * @throws IOException exceptions
     */
    //Michael Oliveira-Silva
    private void thisMousePressed(MouseEvent e) throws IOException {
        modelePhysique.translate(1, -getHeight()/3.3);
        Point2D.Double clickPosition = modelePhysique.componentPositionToReal(e.getPoint());
        modelePhysique.translate(1, getHeight()/3.3);

        cliqueVehicule(clickPosition);
        cliqueBorne(clickPosition);
        selctChanged = true;
        initialPoint = e.getPoint();
    }

    //Michael Oliveira-Silva
    /**
     * Méthode qui sert à savoir lorsqu'un véhicle a été cliqué
     * @param positionClick la position cliqué
     * @throws IOException exceptions
     */
    private void cliqueVehicule(Point2D.Double positionClick) throws IOException{
        for (Vehicule vehicle : vehiculeList) {
            if (vehicle.getRect().contains(positionClick)) {
                vehicle.setSelected(true);
                if (vehicSelect != vehicle){
                    vehicSelect = vehicle;
                    if(!animation){
                        repaint();
                    }

                    for (UpdateListener listener : SAVED_OBJECTS.getListeners(UpdateListener.class)) {
                        if(vehicSelect.isElect()) {
                            listener.carClicked(true, (vehicSelect.toString()), true);
                        }else {
                            listener.carClicked(true, (vehicSelect.toString()), false);
                            vehicle.carInformationUpdated(listener);
                        }
                    }
                } else {
                    vehicSelect = null;
                    if(!animation){
                        repaint();
                    }

                    for (UpdateListener listener : SAVED_OBJECTS.getListeners(UpdateListener.class)) {
                        listener.carClicked(false, "", false);
                    }
                }
            }
        }
    }

    //Gabrielle Lim
    /**
     * Méthode qui permet de savoir quand l'utilisateur a cliqué sur une borne afin de pouvoir afficher ses informations
     * @param positionClick la position du click
     * @throws IOException exceptions
     */
    private void cliqueBorne(Point2D.Double positionClick) throws  IOException {
        for (Borne borne : positionBornes) {
            if(borne.getCarre().contains(positionClick)){
                borne.setSelected(true);
                if(borneSelect!=null) {
                    borneSelect.setSelected(false);
                }
                if(borneSelect != borne) {
                    borneSelect = borne;

                    for (UpdateListener listener : SAVED_OBJECTS.getListeners(UpdateListener.class)) {
                        listener.borneClicked(true);
                    }

                } else {
                    if(borneSelect!=null) {
                        borneSelect.setSelected(false);
                    }
                    borneSelect = null;

                    for (UpdateListener listener : SAVED_OBJECTS.getListeners(UpdateListener.class)) {
                        listener.borneClicked(false);
                    }
                }
                repaint();
            }
        }
    }

    //Gabrielle Lim
    /**
     * Initialise les composants
     */
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license

        //======== this ========
        setName("this");
        addMouseWheelListener(e -> thisMouseWheelMoved(e));
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                thisMouseDragged(e);
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    thisMousePressed(e);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        setLayout(null);
        setPreferredSize(new Dimension(400, 300));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        setBackground(new Color(17, 90,10));
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private boolean firstTime = true;
    private double originalWidth=322;
    private Point2D.Double origin;
    private static java.util.List<Vehicule> vehiculeList;
    private static List<VehicElect> vehicElectList;
    private static SumoTraciConnection sumoTraciConnection;
    private ModelePhysique modelePhysique;
    private String fileNameCnfg = "CentreVilleMtl.sumocfg";
    private Thread thread;
    private boolean animation = false, imageBorne = true;
    private String fileName = "CentreVilleMtl";
    private int vitAnim = 2, freqUpdate = 1;
    private Vehicule vehicSelect;
    private Vehicule hoveredVehic;
    private final double ZOOM_SCALE = 1.2;
    private final int ZOOM_MAX = 10;
    private long longStep, pastTime;
    private int zoom, infoUpdateFrequency = 5, increment;
    private Point initialPoint;
    private final EventListenerList SAVED_OBJECTS = new EventListenerList();
    private static EventList events;
    private int INDEX_PAR_DEFAUT = 1;
    private ArrayList<Edge> liensPositions = new ArrayList<>();
    private ArrayList<maps.Node> positionIntersection = new ArrayList<>();
    private ArrayList<LaneEdgeLien> laneEdgeLiens = new ArrayList<>();
    private ArrayList<Borne> positionBornes = new ArrayList<>();
    private boolean equilibre = true;
    private String nivCharge = "Élevé";
    private int NOMBRE_CENTRALE_DEFAUT = 3;
    private boolean routeDrawn = false, selctChanged = false;
    private Path2D.Double route = new Path2D.Double();
    private Centrale centrale1 = new Centrale(), centrale2 = new Centrale(), centrale3 = new Centrale();
    private ArrayList<Centrale> centrales = new ArrayList<>();
    private ArrayList<maps.Node> nodesBornes = new ArrayList<>();
    private ReseauCentrale reseauCentrale;
    private Borne borneSelect;
    private boolean reset = false;
    private final int HAUTEUR_LIGNE_UNITE = 300;
    private double tempsSim = 0;
    private AffineTransform affMat = new AffineTransform();
    private boolean firstTransfo = true;
    private GraphDijkstra graphDijkstra;
    private Dijkstra dijkstra;
    private RechercheBornesPossibles recherche = new RechercheBornesPossibles();


    /**
     * Méthode permettant de dessiner la carte
     * @param g Le composant graphique
     */
    //Gabrielle
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (firstTime) {
            modelePhysique = new ModelePhysique(getWidth(),getHeight(), originalWidth);
            firstTime = false;
            setChargeCentrale();
            setChargeBornes();
        }

        AffineTransform mat = modelePhysique.getMatMC();
        g2d.translate(1,getHeight()/3.3);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(firstTransfo){
            affMat = mat;
            firstTransfo=false;
        }
        if(reset) {
            mat = affMat;
            modelePhysique.setMatMC(mat);
            reset=false;
            firstTransfo=true;
        }
        drawEdges(g2d, mat);

        if(positionBornes!=null){
            nodesBornes = new ArrayList<>();
            for(int i=0; i<positionBornes.size(); i++){
                Borne b = positionBornes.get(i);
                b.setDessin(imageBorne);
                b.dessiner(g2d, mat, positionIntersection, laneEdgeLiens);
                nodesBornes.add(b.getNodeAssocie());
            }
        }

        if(hoveredVehic!=null){
            hoveredVehic.setHovered(true);
        }
        if(vehiculeList!=null){
            synchronized (vehiculeList) {
                Iterator<Vehicule> vehicleIterator = vehiculeList.iterator();
                while (vehicleIterator.hasNext()) {
                    Vehicule vehicle = vehicleIterator.next();
                    if (!vehicle.equals(vehicSelect)) {
                        vehicle.setSelected(false);
                        vehicle.dessiner(g2d, mat);
                    } else{
                        vehicle.dessiner(g2d, mat);
                    }
                }
            }
        }
        if(vehicSelect!=null&&selctChanged){
            selctChanged = false;
            try {
                setRoute(g2d, mat);
                drawRoute(g2d,mat);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(vehicSelect!=null&&routeDrawn){
            drawRoute(g2d, mat);
        } else if(vehicSelect==null&&routeDrawn){
            routeDrawn=false;
        }

        //Dessine l'échelle d'unité
        g2d.setColor(Color.YELLOW);
        g2d.drawLine((int)(3*modelePhysique.getPixelsParUniteX()),HAUTEUR_LIGNE_UNITE, (int)(13*modelePhysique.getPixelsParUniteX()), HAUTEUR_LIGNE_UNITE);
        g2d.drawLine((int)(3*modelePhysique.getPixelsParUniteX()), HAUTEUR_LIGNE_UNITE - 2, (int)(3*modelePhysique.getPixelsParUniteX()),HAUTEUR_LIGNE_UNITE + 2 );
        g2d.drawLine((int)(13*modelePhysique.getPixelsParUniteX()), HAUTEUR_LIGNE_UNITE - 2, (int)(13*modelePhysique.getPixelsParUniteX()), HAUTEUR_LIGNE_UNITE + 2 );
        g2d.drawString("10 m", (int)(3*modelePhysique.getPixelsParUniteX()), HAUTEUR_LIGNE_UNITE + 12);
    }

    /**
     * Méthode permettant de lire un fichier nod.xml et d'en soustraire les informations importantes sur les nodes pour ensuite les retourner dans un ArrayList
     * @param fichierXML Le nom du fichier xml qu'on souhaite lire
     * @return un ArrayList contenant tous les nodes importants
     */
    //Gabrielle
    private ArrayList<maps.Node> lireFichierXMLNode(String fichierXML) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            FileInputStream fis = new FileInputStream("ressources/sumoxml/" + fichierXML);
            Document doc = builder.parse(fis);

            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("node");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String id = eElement.getAttribute("id");
                    Double x = Double.parseDouble(eElement.getAttribute("x"));
                    Double y = Double.parseDouble(eElement.getAttribute("y"));
                    positionIntersection.add(new maps.Node(id, x,y));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return positionIntersection;
    }

    /**
     * Méthode permettant de lire un fichier edg.xml et d'en soustraire les edges pour ensuite les retourner dans un ArrayList
     * @param fichierXML Le nom du fichier xml qu'on souhaite lire
     * @return un ArrayList contenant tous les composants Edge importants
     */
    //Gabrielle
    private ArrayList<Edge> lireFichierXMLEdge(String fichierXML){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            FileInputStream fis = new FileInputStream("ressources/sumoxml/" + fichierXML);
            Document doc = builder.parse(fis);

            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("edge");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String id = eElement.getAttribute("id");
                    String from = eElement.getAttribute("from");
                    String to = eElement.getAttribute("to");
                    liensPositions.add(new Edge(id, from, to));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liensPositions;
    }

    /**
     * Méthode permettant de lire un fichierXML contenant les informations sur les bornes et de les traduire en infos que l'on peut utiliser
     * @param fichierXML le Nom du fichier XML qu'on désire lire
     * @return un ArrayList contenant toutes les bornes avec les informations sur leurs positions
     */
    //Gabrielle
    private ArrayList<Borne> lireFichierXMLBorne(String fichierXML){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            FileInputStream fis = new FileInputStream("ressources/sumoxml/" + fichierXML);
            Document doc = builder.parse(fis);

            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("chargingStation");
            for(int temp=0; temp<nList.getLength(); temp++){
                Node nBorne = nList.item(temp);
                if (nBorne.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nBorne;
                    double startPos = Double.parseDouble(eElement.getAttribute("startPos"));
                    double endPos = Double.parseDouble(eElement.getAttribute("endPos"));
                    String lane = eElement.getAttribute(("lane"));
                    positionBornes.add(new Borne(new Point2D.Double(startPos,endPos), INDEX_PAR_DEFAUT, lane));
                    positionBornes.get(positionBornes.size()-1).getAtt().setSleep(Math.max(freqUpdate - pastTime, 0));
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return positionBornes;
    }

    /**
     * Méthode permettant de lire les fichiers XML qui contiennent les informations sur les routes et de les traduire pour être utilisé
     * afin de dessiner comme il le faut la carte
     * @param fichierXML le nom du fichier XML qu'on désire lire
     * @return un ArrayList contenant toutes les informations sur les routes
     */
    //Gabrielle
    private ArrayList<LaneEdgeLien> lireFichierXMLLane(String fichierXML){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            FileInputStream fis = new FileInputStream("ressources/sumoxml/" + fichierXML);
            Document doc = builder.parse(fis);

            ArrayList<Edge> edges = new ArrayList<>();
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("edge");
            for(int temp=0; temp<nList.getLength(); temp++){
                Node nBorne = nList.item(temp);
                if (nBorne.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nBorne;
                    String idEdge = eElement.getAttribute("id");
                    String from = eElement.getAttribute("from");
                    String to = eElement.getAttribute(("to"));
                    edges.add(new Edge(idEdge,from,to));
                }
            }

            doc.getDocumentElement().normalize();
            for(int temp=0; temp<nList.getLength(); temp++){
                Node nBorne = nList.item(temp);
                if (nBorne.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nBorne;
                    String idLane = eElement.getAttribute("id");
                    Edge edge = edges.get(temp);
                    laneEdgeLiens.add(new LaneEdgeLien(idLane, edge));
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return laneEdgeLiens;
    }

    /**
     * Méthode permettant de dessiner les routes dans la carte
     * @param g2d objet Graphics2D
     * @param mat matrice de transformation de la carte
     */
    //Gabrielle
    private void drawEdges(Graphics2D g2d, AffineTransform mat) {
        Path2D.Double edges = new Path2D.Double();
        Color intialColor = g2d.getColor();
        g2d.setColor(Color.BLACK);
        Stroke initialStroke = g2d.getStroke();
        //g2d.setStroke(new BasicStroke((float)(WIDTH_STROKE)));
        for(int k=0; k<positionIntersection.size(); k++){
            maps.Node n = positionIntersection.get(k);
            String nodeId = n.getId();
            for (int j=0; j<liensPositions.size(); j++) {
                Edge d = liensPositions.get(j);
                String dFrom = d.getFrom();
                if(dFrom.equalsIgnoreCase(nodeId)) {
                    String dTo = d.getTo();
                    int x = 0;
                    String nodeDirectionId;
                    maps.Node nodeDirection;
                    do{
                        nodeDirection = positionIntersection.get(x);
                        nodeDirectionId = nodeDirection.getId();
                        x++;
                    } while (!nodeDirectionId.equalsIgnoreCase(dTo));
                    edges.moveTo(n.getPosition().getX(), n.getPosition().getY());
                    edges.lineTo(nodeDirection.getPosition().getX(), nodeDirection.getPosition().getY());
                }
            }

        }
        g2d.draw(mat.createTransformedShape(edges));
        g2d.setColor(intialColor);
        g2d.setStroke(initialStroke);
    }

    /**
     * Définis la route qui sera dessiné pour le véhicule choisit
     * @param g2d graphics 2d
     * @param mat matrice de transformation
     * @throws IOException exception
     */
    //Michael Oliveira-Silva
    private void setRoute(Graphics2D g2d, AffineTransform mat) throws IOException {
        Path2D.Double edges = new Path2D.Double();
        dijkstra.execute(vehicSelect.getNode());
        ArrayList<Edge> edgeArrayListist = new ArrayList<>();
        ArrayList<maps.Node> nodes = new ArrayList<>();
        for (it.polito.appeal.traci.Edge edgeS:vehicSelect.getRoute()) {
            edgeArrayListist.add(trouveEdgeParSumo(edgeS.getID()));
        }
        for (Edge e:edgeArrayListist) {
            nodes.add(trouveEdgeNode(e));
        }
        for (int i = 0; i<nodes.size()-1; i++) {
            edges.moveTo(nodes.get(i).getPosition().getX(), nodes.get(i).getPosition().getY());
            edges.lineTo(nodes.get(i+1).getPosition().getX(), nodes.get(i+1).getPosition().getY());
        }
        route = edges;
    }

    /**
     * Dessine la route d'un véhicule choisit
     * @param g2d composant graphics 2d
     * @param mat matrice de transformation
     */
    //Michael Oliveira-Silva
    private void drawRoute(Graphics2D g2d, AffineTransform mat){
        Color intialColor = g2d.getColor();
        g2d.setColor(Color.RED);
        Stroke initialStroke = g2d.getStroke();
        g2d.draw(mat.createTransformedShape(route));
        g2d.setColor(intialColor);
        g2d.setStroke(initialStroke);
        routeDrawn = true;
    }

    /**
     * Permet de redessiné une itération de l'animation du véhicule.
     * @param sByStep boolean qui nous dit si l'animation est pas à pas ou non
     * @return un évènement
     */
    //Cedryk modifié par Michael Oliveira-Silva
    private Event nextStep(boolean sByStep){
        long tempInit = System.currentTimeMillis();

        evenement.Event event = events.remove();
        event.run();

        sumoTraciConnection.getCurrentSimTime();


        repaint();
        if (event.getClass().equals(Update.class)) {
            for (UpdateListener listener : SAVED_OBJECTS.getListeners(UpdateListener.class)) {
                if (vehicSelect != null && (sByStep || increment % infoUpdateFrequency == 0)) {
                    try {
                        vehicSelect.carInformationUpdated(listener);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            increment++;
            pastTime = System.currentTimeMillis() - tempInit;
        } else {
            pastTime = freqUpdate;
        }
        return event;
    }

    /**
     * Permet de démarrer l'animation de la simulation.
     * @param animate boolean qui dit si l'application est animé ou non
     */
    //Michael
    public void start(boolean animate) {
        if (thread == null) {
            try {
                sumoTraciConnection.runServer();
            } catch (InterruptedException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
        if (!animation) {
            thread = new Thread(this, "map");
            if (animate) {
                animation = true;
                thread.start();
            }
        }

    }

    /**
     * Initialise les paramètres de la simulation
     */
    //Cedryk modifié; par Michael Oliveira-Silva & Gabrielle Lim
    public void initialise() {
        sumoTraciConnection= new SumoTraciConnection("ressources/sumoXml/" + fileNameCnfg, 12345);
        vehiculeList = Collections.synchronizedList(new ArrayList<Vehicule>());
        freqUpdate = 16;
        changeAnimationSpeed(vitAnim);
        events = new EventList();
        events.addEvent(new Update(0));
        thread = null;
        vehicSelect= null;
        zoom = 0;
        Update.setStepLength((int) longStep);

        positionBornes = lireFichierXMLBorne(fileName+"Bornes.xml");
        setCentrale();
        setChargeCentrale();
        setChargeBornes();
        liensPositions = lireFichierXMLEdge(fileName+".edg.xml");
        positionIntersection = lireFichierXMLNode(fileName+".nod.xml");
        laneEdgeLiens = lireFichierXMLLane(fileName+".net.xml");
        graphDijkstra = new GraphDijkstra(liensPositions, positionIntersection);
        dijkstra = new Dijkstra(graphDijkstra);
        Update.setMap(this);
        repaint();
    }

    /**
     * Change la vitesse d'animation
     *
     * @param speed facteur multiplicateur de la vitesse d'animation
     */
    // auteur : cedryk
    public void changeAnimationSpeed(int speed) {
        vitAnim = speed;
        longStep = (long) (freqUpdate * vitAnim);
        if (sumoTraciConnection != null) {
            sumoTraciConnection.setStepLength((int) longStep);
            Update.setStepLength((int) longStep);
        }
    }

    /**
     * Méthode qui permet de retourner la largeur initiale du composant
     * @return la largeur initiale
     */
    public double getOriginalWidth() {
        return originalWidth;
    }

    /**
     * Méthode qui permet de retourner l'origine
     * @return l'origine
     */
    public Point2D.Double getOrigin() {
        return origin;
    }

    /**
     * Permet de rouler l'animation
     */
    //Cedryk
    @Override
    public void run() {
        double tempsUp = 0;
        while (!sumoTraciConnection.isClosed() && !events.isEmpty() && animation) {
            for (Vehicule vehicule : vehiculeList) {
                if(vehicule.equals(vehicSelect)&&(!vehicule.isSelected())){
                    vehicSelect=null;
                }
            }
            nextStep(false);
            try {
                Thread.sleep(Math.max(freqUpdate - pastTime, 0));
                tempsSim += Math.max(freqUpdate - pastTime, 0);
                tempsUp +=Math.max(freqUpdate - pastTime, 0);
                if(tempsUp>500) {
                    for (UpdateListener listener : SAVED_OBJECTS.getListeners(UpdateListener.class)) {
                        listener.updateValeursEnergie(reseauCentrale.getNiveauEnergie(), tempsSim);
                        listener.updateValeursDelai(getDelaiMoyen(), tempsSim);
                        listener.updateValeurBorneOccup(getNombreBorneOccup());
                    }
                    tempsUp = 0;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        animation = false;

    }

    /**
     * Change la frequence de mise a jour de la simulation
     *
     * @param updateFrequency le temps entre chaque mise a jour, en ms
     */
    // auteur : cedryk
    public void setUpdateFrequency(int updateFrequency) {
        this.freqUpdate = updateFrequency;
        changeAnimationSpeed(vitAnim);
        Update.setStepLength((int) longStep);
    }

    /**
     * Rajoute un UpdateListener
     * @param listeningObj listener UpdateListener
     */
    //Michael
    public void addUpdateListener(UpdateListener listeningObj) {
        SAVED_OBJECTS.add(UpdateListener.class, listeningObj);
    }

    /**
     * Rajoute un BorneListener
     * @param listeningObj listener BorneListener
     */
    //Michael
    public void addBorneListener(BorneListener listeningObj) {
        SAVED_OBJECTS.add(BorneListener.class, listeningObj);
    }

    /**
     * Ferme les communications avec Traci et stoppe l'animation
     */
    // auteur : cedryk
    public void closeSimulation() {
        try {
            animation = false;
            Thread.sleep(2 * freqUpdate);
            sumoTraciConnection.close();
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Retourne la liste des véhicules de la simulation
     * @return la Liste des véhicules de la siimulation
     */
    //Michael
    public static List<Vehicule> getVehiculeList() {
        return vehiculeList;
    }

    /**
     * Permet de redéfinir la liste des véhicules de la Simulation
     * @param vehiculeList liste des véhicules de la simulation
     */
    //Michael
    public static void setVehiculeList(List<Vehicule> vehiculeList) {
        Map.vehiculeList = vehiculeList;
    }

    /**
     * Retourne la connection entre TRACi, SUMO et R.E.D.
     * @return sumoTraciConnection la connection de TRACi et SUMO
     */
    //Michael
    public static SumoTraciConnection getSumoTraciConnection() {
        return sumoTraciConnection;
    }

    /**
     * Redéfinis la connection de SUMO et TRACi
     * @param sumoTraciConnection la nouvelle connection Sumo et TRACi
     */
    //Michael
    public static void setSumoTraciConnection(SumoTraciConnection sumoTraciConnection) {
        Map.sumoTraciConnection = sumoTraciConnection;
    }

    /**
     * Permet de rajouter un évènement
     * @param eventToAdd qui est l'évènement à rajouté
     */
    //Michael
    public static void addEvent(evenement.Event eventToAdd) {
        events.addEvent(eventToAdd);
    }

    /**
     * Retourne la fréquence de mises à jour de la simulation
     * @return la fréquence de mises à  jour de la simulation
     */
    //Michael Oliveira-Silva
    public int getFreqUpdate() {
        return freqUpdate;
    }

    /**
     * Détermine la fréquence de mises à jour de la simulation
     * @param freqUpdate la fréquence des mises à jour de la simulation
     */
    //Michael Oliveira-Silva
    public void setFreqUpdate(int freqUpdate) {
        this.freqUpdate = freqUpdate;
    }

    /**
     * Retourne le temps qui est déjà passé
     * @return le temps qui est passé dans la simulation
     */
    //Michael Oliveira-Silva
    public long getPastTime() {
        return pastTime;
    }

    /**
     * Méthode qui retourne si c'est équilibré
     * @return si l'énergie est équilibré ou non, true si oui et faux dans le cas contraire
     */
    //Michael Oliveira-Silva
    public boolean isEquilibre() {
        return equilibre;
    }

    //Michael
    /**
     * Méthode permettant de modifié l'équilibre des charges
     * @param equilibre vrai si c'est équilibré, faux dans le cas contraire
     */
    public void setEquilibre(boolean equilibre) {
        this.equilibre = equilibre;
    }

    //Michael Oliveira-Silva
    /**
     * Méthode permettant de retouner le niveau de charge
     * @return le niveau de charge
     */
    public String getNivCharge() {
        return nivCharge;
    }

    //Michael Oliveira-Silva
    /**
     * Méthode permettant de modifié le niveau de charge
     * @param nivCharge le nouveau niveau de charge
     */
    public void setNivCharge(String nivCharge) {
        this.nivCharge = nivCharge;
    }

    //Michael Oliveira-Silva
    /**
     * Méthode permettant d'attribuer une charge au hasard aux bornes
     * @param min le minimum de charge
     * @param max le maximum de charge
     */
    private void setRandomCharge(int min, int max){
        for (Centrale centrale : centrales) {
            int charge = min + (int) (Math.random() * max);
            if (charge > max) charge = max;
            centrale.setQuantiteEnergiePourcentage(charge);
        }
    }

    //Gabrielle Lim
    /**
     * Méthode permettant d'assigner des bornes aux trois centrales du territoire
     */
    private void setCentrale() {
       int nbBorneParCentrale = positionBornes.size()/NOMBRE_CENTRALE_DEFAUT;
       //si le nombre de bornes n'est pas divisible par 3 alors celle de surplus iront à la dernière centrale
       ArrayList<Borne> bornesCentrale1 = new ArrayList<>();
       for (int i = 0; i < nbBorneParCentrale; i++) {
            bornesCentrale1.add(positionBornes.get(i));
            positionBornes.get(i).setCentrale(1);
            //positionBornes.get(i).setCentrale(1, centrale1);
       }

       ArrayList<Borne> bornesCentrale2 = new ArrayList<>();
       for (int j = nbBorneParCentrale; j < 2*nbBorneParCentrale; j++) {
           bornesCentrale2.add(positionBornes.get(j));
           positionBornes.get(j).setCentrale(2);
           //positionBornes.get(j).setCentrale(2, centrale2);
       }

       ArrayList<Borne> bornesCentrale3 = new ArrayList<>();
       for (int k = nbBorneParCentrale*2; k < positionBornes.size(); k++) {
           bornesCentrale3.add(positionBornes.get(k));
           positionBornes.get(k).setCentrale(3);
           //positionBornes.get(k).setCentrale(3, centrale3);
       }

       centrale1.setBornes(bornesCentrale1);
       centrale2.setBornes(bornesCentrale2);
       centrale3.setBornes(bornesCentrale3);
       centrales.add(centrale1);
       centrales.add(centrale2);
       centrales.add(centrale3);
       reseauCentrale = new ReseauCentrale(centrales);
    }

    //Michael Oliveira-Silva
    /**
     * Méthode qui permet de déterminer la charge des bornes
     */
    public void setChargeBornes(){
       for(Centrale centrale : centrales) {
           for (Borne borne : centrale.getBornes()) {
               borne.setCharge(centrale.getQuantiteEnergiePourcentage());
           }
       }
    }

    /**
     * Méthode permettant de déterminer la charge des centrales
     */
    //Gabrielle Lim
    private void setChargeCentrale() {
        if(nivCharge!=null){
            if(equilibre){
                switch (nivCharge){
                    case "Élevé" :
                        for (Centrale centrale: centrales) {
                            centrale.setQuantiteEnergiePourcentage(100);
                        }
                        break;
                    case "Moyen" :
                        for (Centrale centrale: centrales) {
                            centrale.setQuantiteEnergiePourcentage(50);
                        }
                        break;
                    case "Faible" :
                        for (Centrale centrale: centrales) {
                            centrale.setQuantiteEnergiePourcentage(20);
                        }
                        break;
                }
            }else if(!equilibre) {
                switch (nivCharge){
                    case "Élevé" : setRandomCharge(70, 100);
                        break;
                    case "Moyen" : setRandomCharge(25, 70);
                        break;
                    case "Faible" : setRandomCharge(0, 25);
                        break;
                }
            }
        }
        ReseauCentrale.recalculerNiveauEnergie();
        for (BorneListener listener  : SAVED_OBJECTS.getListeners(BorneListener.class)) {
            listener.changementChargeCentrale1(centrale1.getQuantiteEnergiePourcentage());
            listener.changementChargeCentrale2(centrale2.getQuantiteEnergiePourcentage());
            listener.changementChargeCentrale3(centrale3.getQuantiteEnergiePourcentage());
        }
    }

    //Michael Oliveira-Silva
    /**
     * Retourne le modelePhysique de la carte
     * @return modelePhisique le modele de la carte
     */
    public ModelePhysique getModelePhysique() {
        return modelePhysique;
    }

    /**
     * Retourne la valeur du scale utilisé pour le zoom
     * @return la valeur utiliser pour zoomer et dézoomer
     */
    //Michael Oliveira-Silva
    public double getZOOM_SCALE() {
        return ZOOM_SCALE;
    }

    /**
     * Retourne la valeur maximale du zoom
     * @return la valeur maximale du zoom
     */
    //Michael Oliveira-Silva
    public int getZOOM_MAX() {
        return ZOOM_MAX;
    }

    /**
     * Retourne la valeur du zoom
     * @return la valeur du zoom
     */
    //Michael Oliveira-Silva
    public int getZoom() {
        return zoom;
    }

    /**
     * Définis la valeur du zoom
     * @param zoom la valeur du zoom
     */
    //Michael Oliveira-Silva
    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    /**
     * Permet de zoomer a un point de la carte
     * @param nbRot le nombre de rotation de la souris
     * @param zoomCenter le point à zoomer
     */
    //Cedryk
    private void zoom(int nbRot, Point zoomCenter) {
        if (Math.abs(zoom + nbRot) < ZOOM_MAX) {
            zoom+=nbRot;
            modelePhysique.scaleOnPosition(Math.pow(ZOOM_SCALE, nbRot), zoomCenter);
            repaint();
        }
    }

    /**
     * Permet de déplacer la carte a partir d'un point
     * @param initialPoint point central initial
     * @param finalPoint point final qui est donné par la souris
     */
    //Cedryk
    private void translate(Point initialPoint, Point finalPoint) {
        double dx = (finalPoint.getX() - initialPoint.getX());
        double dy = (finalPoint.getY() - initialPoint.getY());
        modelePhysique.translate(dx, dy);

    }

    /**
     * Retourne la liste de véhicules électriques
     * @return la liste des véhicules électriques
     */
    //Michael Oliveira-Silva
    public static List<VehicElect> getVehicElectList() {
        return vehicElectList;
    }

    /**
     * Retourne les informations du véhicule sélectionné
     * @return les informations du véhicule
     * @throws IOException exceptions
     */
    //Michael Oliveira-Silva
    public String selectVehiculeInfo() throws IOException {
        return vehicSelect.getInfo();
    }

    /**
     * Retourne le véhicule sélectionné
     * @return le véhicule sélectionné
     */
    //Michael Oliveira-Silva
    public Vehicule getVehicSelect() {
        return vehicSelect;
    }

    //Gabrielle Lim
    /**
     * Méthode qui retourne la borne Sélectionnée
     * @return la borne sélectionnée
     */
    public Borne getBorneSelect() {
        return borneSelect;
    }

    //Gabrielle Lim
    /**
     * Méthode qui retourne un String avec les informations de la borne
     * @return String des informations de la borne
     * @throws IOException exception
     */
    public String selectBorneInfo() throws IOException {
        return borneSelect.getInfo();
    }

    /**
     * Définis si l'image d'une borne sera dessinée
     * @param imageBorne boolean qui dit si l'image est dessiné ou non
     */
    //Gabrielle Lim
    public void setImageBorne(boolean imageBorne) {
        this.imageBorne = imageBorne;
        repaint();
    }

    /**
     * Méthode qui permet de retouner le réseau de centrale
     * @return le réseau de centrale
     */
    //Gabrielle Lim
    public ReseauCentrale getReseauCentrale() {
        return reseauCentrale;
    }

    /**
     * Méthode qui permet de retourner le temps de la simulation
     * @return le temps de la simulation
     */
    //Gabrielle Lim
    public double getTempsSim() {
        return tempsSim;
    }

    /**
     * Méthode qui permet de retourner l'ArrayList des liens entre les routes et les lanes (voies)
     * @return l'ArrayList des liens entre les routes et les lanes (voies)
     */
    //Gabrielle Lim
    public ArrayList<LaneEdgeLien> getLaneEdgeLiens() {
        return laneEdgeLiens;
    }

    /**
     * Méthode qui retourne l'ArrayList des routes
     * @return l'ArrayList des routes
     */
    //Gabrielle Lim
    public ArrayList<Edge> getLiensPositions() {
        return liensPositions;
    }

    /**
     * Méthode qui retourne l'ArrayList des positions d'intersection des routes
     * @return l'ArrayList des positions d'intersection des routes
     */
    //Gabrielle Lim
    public ArrayList<maps.Node> getPositionIntersection() {
        return positionIntersection;
    }

    /**
     * Méthode qui retoure l'ArrayList des intersections associés aux bornes
     * @return l'ArrayList des intersections associés aux bornes
     */
    //Gabrielle Lim
    public ArrayList<maps.Node> getNodesBornes() {
        return nodesBornes;
    }

    public GraphDijkstra getGraphDijkstra() {
        return graphDijkstra;
    }

    /**
     * Méthode qui permet de modifié le véhicule sélectionné
     * @param vehicSelect le nouveau véhicule sélectionné
     */
    //Michael Oliveira-Silva
    public void setVehicSelect(Vehicule vehicSelect) {
        this.vehicSelect = vehicSelect;
    }

    /**
     * Méthode qui permet de modifié la borne sélectionnée
     * @param borneSelect la nouvelle borne sélectionnée
     */
    //Gabrielle Lim
    public void setBorneSelect(Borne borneSelect) {
        this.borneSelect.setSelected(false);
        this.borneSelect = borneSelect;
        repaint();
    }

    /**
     * Débute la demande pour annuler les tranformations présédentes
     */
    //Michael
    public void resetPos(){
        reset=true;
        repaint();
    }

    /**
     * Retourne la liste des bornes de la simulation
     * @return la liste des bornes
     */
    //Michael
    public ArrayList<Borne> getPositionBornes() {
        return positionBornes;
    }

    /**
     * Définis l'ordre de priorité de la recherche de bornes
     * @param prioriteRecherche la priorité de recherche des bornes
     */
    //Gabrielle
    public void setPrioriteRecherche(int[] prioriteRecherche) {
        recherche.setPriorite(prioriteRecherche);
    }

    /**
     * Méthode permettant de retourner les edges de SUMO
     * @return les edges de SUMO
     * @throws IOException exception
     */
    //Gabrielle
    public ArrayList<it.polito.appeal.traci.Edge> getEdgesSUMO() throws IOException {
        ArrayList<it.polito.appeal.traci.Edge> edgesSUMO = new ArrayList<>();
        for (Edge edge : liensPositions) {
            edgesSUMO.add(sumoTraciConnection.getEdgeRepository().getByID(edge.getId()));
        }
        return edgesSUMO;
    }

    /**
     * Permet de trouver un Edge à partir d'un Node
     * @param n node du Edge
     * @return le Edge du Node
     */
    //Michael Oliveira-Silva
    public Edge trouverNodeEdge(maps.Node n){
        Edge edge = null;
        for (Edge e:liensPositions) {
            if(e.getTo().equals(n.getId())) {
                edge = e;
                continue;
            }
        }
        return edge;
    }

    /**
     * Trouve un Node à partir d'un Edge
     * @param e Edge voulu
     * @return Node
     */
    //Michael Oliveira-Silva
    private maps.Node trouveEdgeNode(Edge e){
        maps.Node node = null;
        for (maps.Node n:positionIntersection) {
            if(e.getTo().equals(n.getId())) {
                node = n;
                continue;
            }
        }
        return node;
    }

    /**
     * Trouve un Edge à partir de l'ID du Edge de Sumo
     * @param ID ID du edge de Sumo
     * @return Le Edge voulu de RED
     */
    //Michael Oliveira-Silva
    private Edge trouveEdgeParSumo(String ID){
        Edge target = null;
        for (Edge e:liensPositions) {
            if(e.getId().equals(ID)) {
                target = e;
                continue;
            }
        }
        return target;
    }

    /**
     * Détermine le nombre de bornes occupées dans la carte
     * @param nombreBorneOccup le nombre de bornes occupées
     */
    //Gabrielle Lim
    public void setNombreBorneOccup(int nombreBorneOccup) {
        int max = positionBornes.size();
        int min = 1;
        ArrayList<Integer> bornesOccupee = new ArrayList<Integer>();
        do {
            Random random = new Random();
            int numeroBorne = random.nextInt(max - min + 1) + min;
            if(!bornesOccupee.contains(numeroBorne)) {
                bornesOccupee.add(numeroBorne);
            }
        } while (bornesOccupee.size() != nombreBorneOccup);

        for (int i = 0; i < nombreBorneOccup; i++) {
            positionBornes.get(bornesOccupee.get(i)-1).setOccupee(true);
        }
    }

    /**
     * Retourne le nombre de bornes occupées
     * @return les bornes occupées
     */
    //Gabrielle Lim
    private int getNombreBorneOccup() {
        int nombreBorneOccup = 0;
        for (int i = 0; i < positionBornes.size(); i++) {
            if (positionBornes.get(i).getOccupee()) {
                nombreBorneOccup++;
            }
        }
        return nombreBorneOccup;
    }
}



