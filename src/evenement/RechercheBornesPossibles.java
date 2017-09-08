package evenement;

import static aapplication.Resultats.setUtilisationBorne;
import static graphiques.CalculDelai.setTempsRechercheBorne;

import java.io.IOException;
import java.util.ArrayList;

import bornes.Borne;
import dijkstra.Dijkstra;
import dijkstra.GraphDijkstra;
import maps.Edge;
import maps.Map;
import maps.Node;
import vehicules.VehicElect;

/**
 * @author Michael Oliveira-Silva
 * @author Gabrielle Lim
 * Classe permettant de rechercher la borne correspondant au critères
 */
public class RechercheBornesPossibles implements Runnable {

    private ArrayList<Node> nodesBornes = new ArrayList<>();
    private ArrayList<Borne> bornesArrayList = new ArrayList<>();
    private static ArrayList<Borne> bornesOccupe = new ArrayList<>();
    private static int attMax = 0;
    private ArrayList<Node> listNodes = new ArrayList<>();
    private ArrayList<Edge> edgesList = new ArrayList<>();
    private ArrayList<Node> nodesPossible = new ArrayList<>();
    private ArrayList<Integer> pathWeightList = new ArrayList<>();
    private Map map;
    private VehicElect vehicElect;
    private Node nodeCar;
    private boolean found = true;
    private int[] priorite = new int[] {0,1,2};

    /**
     * Définis les Nodes des bornes pour la recherche
     * @param nodesBornes les nodes des bornes
     */
    //Michael
    public void setNodesBornes(ArrayList<Node> nodesBornes) {
        this.nodesBornes = nodesBornes;
    }

    /**
     * Définis les Edges pour la recherche
     * @param edgesList les edges des routes pour la recherche
     */
    //Michael
    public void setEdgesList(ArrayList<Edge> edgesList) {
        this.edgesList = edgesList;
    }

    /**
     * Définis le node de la voiture voulue
     * @param nodeCar le node de la voiture voulue
     */
    //Michael
    public void setNodeCar(Node nodeCar) {
        this.nodeCar = nodeCar;
    }

    /**
     * Définis le véhicule qui fait la demande
     * @param vehicElect le véhicule électrique qui fait la demande
     */
    //Michael
    public void setVehicElect(VehicElect vehicElect) {
        this.vehicElect = vehicElect;
    }

    /**
     * Définis la carte pour la recherche
     * @param map la carte de la ville
     */
    //Michael
    public void setMap(Map map) {
        this.map = map;
        listNodes = map.getPositionIntersection();
        bornesArrayList = map.getPositionBornes();
    }

    /**
     * Démarre la recheche
     */
    //Michael
    public void demarrer(){
        found = false;
        double startTime = System.nanoTime();
        new Thread(this, "recherche");
        run();
        double endTime = System.nanoTime();

        double duration = (endTime - startTime);
        setTempsRechercheBorne(duration);
    }

    /**
     * Méthode permettant de trouver la borne la plus proche
     * @param listNodes la liste des intersections associées aux bornes
     * @param pathWeights le poids des routes des bornes
     * @return l'intersection associé à la borne la plus proche
     */
    //Michael Oliveira-Silva
    private Node trouveMinDist(ArrayList<Node> listNodes, ArrayList<Integer> pathWeights){
        Node target = null;
        int pathWeight = Integer.MAX_VALUE;
        for (int i = 0; i < pathWeights.size(); i++) {
            if(pathWeights.get(i)<pathWeight&&i<listNodes.size()){
                target = listNodes.get(i);
                pathWeight = pathWeights.get(i);
            }
        }
        return target;
    }

    /**
     * Méthode permettant de trouver quelles bornes sont disponibles
     * @param listNodes la liste des nodes associées aux bornes
     * @return la liste des nodes associées aux bornes libres
     */
    //Michael Oliveira-Silva
    private ArrayList<Node> bornesLibres(ArrayList<Node> listNodes){
        ArrayList<Node> nodeList = new ArrayList<>();
        for (int i = 0; i < listNodes.size(); i++) {
            if (i < bornesArrayList.size()) {
                if (!bornesOccupe.contains(bornesArrayList.get(i))) {
                    nodeList.add(listNodes.get(i));
                }
            }else
                continue;
        }
        return nodeList;
    }

    /**
     * Algorithme de recherche prenant en compte l'ordre de priorité choisi
     * @param ev le véhicule électrique ayant fait une demande
     * @param priorite l'ordre de priorité choisi par l'utilisateur
     * @return l'intersection associés à la borne trouvée
     */
    //Gabrielle Lim
    private Node rechercheNode(VehicElect ev, int[] priorite){
        ArrayList<Node> bornesPossible;
        attMax = borneMaxAttente();
        Node bornechoisie = null;
          switch (priorite[0]) {
              case 0 : bornesPossible = trouverBornesEnergie();
              if(priorite[1] == 1) {
                  if(bornesPossible == null||bornesPossible.size()<=2) {
                      bornechoisie = trouverBornesDelai(nodesPossible);
                      if(bornechoisie == null) {
                          bornechoisie = trouveMinDist(nodesPossible, pathWeightList);
                      }
                  } else {
                      bornechoisie = trouverBornesDelai(bornesPossible);
                      if(bornechoisie == null) {
                          bornechoisie = trouveMinDist(nodesPossible, pathWeightList);
                      }
                  }
              } else {
                  bornechoisie = trouveMinDist(nodesPossible, pathWeightList);
              }
              break;
              case 1 : bornechoisie = trouverBornesDelai(nodesPossible);
              if(bornechoisie == null) {
                  if(priorite[1] == 0) {
                      bornesPossible = trouverBornesEnergie();
                      bornechoisie = trouveMinDist(bornesPossible, pathWeightList);
                  } else {
                      bornechoisie = trouveMinDist(nodesPossible, pathWeightList);
                  }
              }
              break;
              case 2 : bornechoisie = trouveMinDist(nodesPossible, pathWeightList);
              break;
          }

        return bornechoisie;
    }

    /**
     * Méthode qui trouve la borne
     */
    //Michael Oliveira-Silva
    public void run() {
        int index = 0;
        boolean trouve = found;
        VehicElect ev = vehicElect;
        Dijkstra dijkstra = new Dijkstra(new GraphDijkstra(edgesList, listNodes));
        //ArrayList<Integer> pathWeightList = new ArrayList<>();
        dijkstra.execute(nodeCar);
        while(!trouve){
            int pathWeight = dijkstra.getPathDist(nodesBornes.get(index), map);
            if(pathWeight<ev.getCharge()&&nodesBornes.get(index)!=null){
                nodesPossible.add(nodesBornes.get(index));
                pathWeightList.add(pathWeight);
            }
            if(index == nodesBornes.size()-1){
                trouve = true;
            }
            index++;
        }try {
            Thread.sleep(0);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }


        //Node nodeTrouve = trouveMinDist(nodesPossible, pathWeightList);
        Node nodeTrouve = rechercheNode(ev, priorite);
        reserverBorneChoisie(nodeTrouve);
        utilisationBorneCentraleDiagramme(nodeTrouve);
        if(nodeTrouve != null) {
            Edge edge = map.trouverNodeEdge(nodeTrouve);
            ArrayList<it.polito.appeal.traci.Edge> edgesSumo = null;
            it.polito.appeal.traci.Edge eWanted = null;
            try {
                edgesSumo = map.getEdgesSUMO();
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (it.polito.appeal.traci.Edge eSumo:edgesSumo) {
                if(edge.getId().equals(eSumo.getID()))
                    eWanted = eSumo;
            }
            try {
                ev.changeRoute(eWanted);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Méthode permettant de trouver la meilleure borne avec le délai
     * @param bornesPossible les bornes possibles
     * @return l'intersection associée à la borne choisie
     */
    //Gabrielle Lim
    private Node trouverBornesDelai(ArrayList<Node> bornesPossible) {
        ArrayList<Node> bornesLibres = bornesLibres(bornesPossible);
        if (bornesLibres.size() != 0) {
            return trouveMinDist(bornesLibres, pathWeightList);
        } else {
            ArrayList<Node> bornesAttenteMinimum = new ArrayList<>();
            double tempsAttenteMinimum = 0;
            for (Node borne : bornesPossible) {
                for (int i = 0; i < bornesArrayList.size(); i++) {
                    Borne borneTest = bornesArrayList.get(i);
                    if (borneTest.getFileAttente().size()<attMax) {
                        if (borneTest.getNodeAssocie().getId().equalsIgnoreCase(borne.getId())) {
                            if (tempsAttenteMinimum == 0) {
                                tempsAttenteMinimum = borneTest.getTempsAttente();
                                bornesAttenteMinimum.add(borne);
                            } else {
                                if (tempsAttenteMinimum > borneTest.getTempsAttente()) {
                                    tempsAttenteMinimum = borneTest.getTempsAttente();
                                    bornesAttenteMinimum.clear();
                                    bornesAttenteMinimum.add(borne);
                                } else {
                                    if (tempsAttenteMinimum == borneTest.getTempsAttente()) {
                                        bornesAttenteMinimum.add(borne);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            return trouveMinDist(bornesAttenteMinimum, pathWeightList);
        }
    }

    /**
     * Méthode permettant de trouver les bornes avec le plus d'énergie
     * @return la liste de borne avec le plus d'énergie dans le réseau
     */
    //Gabrielle Lim
    private ArrayList<Node> trouverBornesEnergie() {
        ArrayList<Node> bornes = new ArrayList<>();
        Borne borneMinimum = bornesArrayList.get(0);
        Node borneMini = bornesArrayList.get(0).getNodeAssocie();
        bornes.add(borneMini);
        for (int i = 1; i < bornesArrayList.size()-2; i++) {
            if(bornesArrayList.get(i).getCharge() > borneMinimum.getCharge()&&!bornesOccupe.contains(bornesArrayList.get(i))) {
                borneMini = bornesArrayList.get(i).getNodeAssocie();
                bornes.clear();
                bornes.add(borneMini);
            } else {
                if(bornesArrayList.get(i).getCharge() == borneMinimum.getCharge()&&!bornesOccupe.contains(bornesArrayList.get(i))) {
                    bornes.add(bornesArrayList.get(i).getNodeAssocie());
                }
            }
        }

        return bornes;
    }

    /**
     * Permet de modifier l'ordre de priorité
     * @param priorite le nouvel ordre de priorité
     */
    //Gabrielle Lim
    public void setPriorite(int[] priorite) {
        this.priorite = priorite;
    }

    /**
     * Méthode permettant de réserver la borne choisie
     * @param nodeTrouver l'intersection associée à la borne choisie
     */
    //Gabrielle Lim
    private void reserverBorneChoisie(Node nodeTrouver) {
        for (int i = 0; i < bornesArrayList.size(); i++) {
            Borne borne = bornesArrayList.get(i);
            if(borne.getNodeAssocie()!= null && nodeTrouver != null) {
                if (borne.getNodeAssocie().getId().equalsIgnoreCase(nodeTrouver.getId())) {
                    borne.setReserver(true, vehicElect);
                    borne.setVehicOccuper(vehicElect);
                    bornesOccupe.add(borne);
                    vehicElect.setBorneTarget(borne);
                }
            }
        }
    }

    /**
     * Méthode permettant de mettre à jour les valeurs dans le diagramme d'utilisation des bornes
     * @param nodeTrouver l'intersection associée à la borne choisie
     */
    //Gabrielle Lim
    private void utilisationBorneCentraleDiagramme(Node nodeTrouver){
        for (int i = 0; i < bornesArrayList.size(); i++) {
            Borne borne = bornesArrayList.get(i);
            if(nodeTrouver != null) {
                if(borne.getNodeAssocie().getId().equalsIgnoreCase(nodeTrouver.getId())){
                    double numCentrale = borne.getCentrale();
                    setUtilisationBorne(numCentrale);
                    break;
                }
            }
        }
    }

    /**
     * Retire une borne qui n'est plus occupée
     * @param b borne libre à nouveau
     */
    //Michael Oliveira-Silva
    public static void removeBorne(Borne b){
        bornesOccupe.remove(b);
    }

    /**
     * Retourne l'Array qui contient les bornes
     * @return la liste de bornes
     */
    //Gabrielle Lim
    public ArrayList<Borne> getBornesArrayList() {
        return bornesArrayList;
    }

    /**
     * Retourne la liste de bornes occupées
     * @return les bornes qui sont occupées
     */
    //Michael Oliveira-Silva
    public static ArrayList<Borne> getBornesOccupe() {
        return bornesOccupe;
    }

    private int bornePettiteAttente(){
        int att = Integer.MAX_VALUE;
        Node n = null;
        for (int i = 0; i < bornesArrayList.size(); i++) {
            if(bornesArrayList.get(i).getFileAttente().size()<att) {
                att = bornesArrayList.get(i).getFileAttente().size();
                n = nodesBornes.get(i);
            }
        }
        return att;
    }

    private int borneMaxAttente(){
        int att = 0;
        Node n = null;
        for (int i = 0; i < bornesArrayList.size(); i++) {
            if(bornesArrayList.get(i).getFileAttente().size()>att) {
                att = bornesArrayList.get(i).getFileAttente().size();
                n = nodesBornes.get(i);
            }
        }
        return att;
    }
}
