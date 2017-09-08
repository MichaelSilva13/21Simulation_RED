package dijkstra;

import bornes.Borne;
import maps.Edge;
import maps.LaneEdgeLien;
import maps.Node;
import vehicules.Vehicule;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.List;

/**
 * @author Gabrielle Lim
 * Classe permettant de calculer la distance qui sépare deux point
 */
public class Distances {

    private List<Edge> edgeList;
    private List<Node> nodesList;
    private List<LaneEdgeLien> laneEdgeLiens;
    private String idLane;
    private double distance;

    /**
     * Méthode permettant de calculer la distance entre une borne et l'intersection la plus proche
     * @param borneSelect la borne que l'on souhaite utiliser pour le calcul
     * @return la distance entre l'intersection et la borne
     */
    public double getDistanceBorneNode(Borne borneSelect){
        distance = borneSelect.getPositonRapportLane();
        return distance;
    }

    /**
     * Méthode permettant de calculer la longueur (distance) totale d'une route
     * @param listLaneEdgeLien une liste contenant toutes les informations sur toutes les routes de la carte
     * @param listNode une liste contenant toutes les informations sur toutes les intersections de la carte
     * @param idLaneACalculer le id de la route pour laquelle nous souhaitons calculer la distance
     * @return la distance (longueur) totale de la route
     */
    public double getDistanceLane(List<LaneEdgeLien> listLaneEdgeLien, List<Node> listNode, String idLaneACalculer){

        this.nodesList = listNode;
        this.laneEdgeLiens = listLaneEdgeLien;

        this.idLane = idLaneACalculer;

        for(int k=0; k<laneEdgeLiens.size(); k++) {
            LaneEdgeLien lien = laneEdgeLiens.get(k);
            String idL = lien.getIdLane();
            if (idL.equalsIgnoreCase(idLane)) {
                Edge edgelien = lien.getEdge();
                String from = edgelien.getFrom();
                for (int g = 0; g < nodesList.size()-1; g++) {
                    maps.Node node = nodesList.get(g);
                    String idNodeFrom = node.getId();
                    if (idNodeFrom.equalsIgnoreCase(from)) {
                        Point2D.Double positionNodeFrom = node.getPosition();
                        for (int i = 0; i < nodesList.size()-1; i++) {
                            maps.Node nodeto = nodesList.get(i);
                            String idNodeTo = nodeto.getId();
                            String to = edgelien.getTo();
                            if (idNodeTo.equalsIgnoreCase(to)) {
                                Point2D.Double positionNodeTo = nodeto.getPosition();

                                double deltaX = positionNodeTo.getX() - positionNodeFrom.getX();
                                double deltaY = positionNodeTo.getY() - positionNodeFrom.getY();

                                distance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
                            }
                        }
                    }
                }
            }
        }

        return distance;
    }

    /**
     * Méthode permettant de calculer la distance entre un véhicule et la prochaine intersection dans son chemin
     * @param listedge une liste de toutes les routes de la carte
     * @param listNode une liste de toutes les intersections de la carte
     * @param vehicule le véhicule que l'on souhaite utilisé pour les calculs
     * @return la distance entre le véhicule et la prochaine intersection dans son chemin
     * @throws IOException exceptions
     */
    public double getDistanceVehiculeNode(List<Edge> listedge, List<Node> listNode, Vehicule vehicule) throws IOException {

        this.edgeList = listedge;
        this.nodesList = listNode;

       it.polito.appeal.traci.Edge edgeVehicule = vehicule.getSumoVehicule().getCurrentEdge();
       String edgeId = edgeVehicule.getID();

        for (int i = 0; i < edgeList.size(); i++) {
            Edge edge = edgeList.get(i);
            String edgeIdComparer = edge.getId();
            if(edgeIdComparer.equalsIgnoreCase(edgeId)) {
                String to = edge.getTo();
                for (int j = 0; j < nodesList.size(); j++) {
                    maps.Node node = nodesList.get(j);
                    String idNodeTo = node.getId();
                    if(idNodeTo.equalsIgnoreCase(to)) {
                        Point2D.Double positionNodeTo = node.getPosition();

                        double deltaX = positionNodeTo.getX() - vehicule.getPosition().getX();
                        double deltaY = positionNodeTo.getY() - vehicule.getPosition().getY();

                        distance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
                    }
                }
            }
        }

       return distance;
    }

    /**
     * retourne le Node associé a un véhicule
     * @param listedge les Edges
     * @param listNode Les nodes
     * @param vehicule le véhicule
     * @return le Node du véhicule
     * @throws IOException exceptions
     */
    public static Node getVehiculeNode(List<Edge> listedge, List<Node> listNode, Vehicule vehicule) throws IOException {

        Node nodeVehic = null;
        it.polito.appeal.traci.Edge edgeVehicule = vehicule.getSumoVehicule().getCurrentEdge();
        String edgeId = edgeVehicule.getID();

        for (int i = 0; i < listedge.size(); i++) {
            Edge edge = listedge.get(i);
            String edgeIdComparer = edge.getId();
            if(edgeIdComparer.equalsIgnoreCase(edgeId)) {
                String to = edge.getTo();
                for (int j = 0; j < listNode.size(); j++) {
                    maps.Node node = listNode.get(j);
                    String idNodeTo = node.getId();
                    if(idNodeTo.equalsIgnoreCase(to)) {
                        nodeVehic=node;
                    }
                }
            }
        }

        return nodeVehic;
    }


}
