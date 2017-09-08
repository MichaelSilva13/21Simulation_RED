package dijkstra;

import maps.*;

import java.util.*;
import java.util.Map;

/**
 * ALgorithme du chemin le plus court de Dijkstra dans un graphe
 * @author Michael Oliveira-Silva
 */
public class Dijkstra {
    private List<Edge> edgesList;
    private List<Node> nodesList;
    private Set<Node> settleNodes;
    private Set<Node> unSettledNodes;
    private Map<Node, Node> predecessors;
    private Map<Node, Integer> distance;
    private Distances dist = new Distances();

    /**
     * Associe un GraphDijkstra à l'algorithme. Le graphe sera utilisé dans l'algorithme
     * @param graph graphique qui sera utilisé
     */
    public Dijkstra(GraphDijkstra graph) {
        edgesList = graph.getEdgelist();
        nodesList = graph.getNodeList();

    }

    /**
     * Trouve les distances minimales d'un node avec d'autres nodes dans le graph
     * @param node le node source
     */
    private void findMinimalDistances(Node node) {
        List<Node> adjacentNodes = getNeighbors(node);
        for (Node target : adjacentNodes) {
            if (getDistPlusCourte(target) > getDistPlusCourte(node)
                                              + getDistance(node, target)) {
                distance.put(target, getDistPlusCourte(node) + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    /**
     * Retourne la distance entre un node et un autre
     * @param node le node de source
     * @param target le node cible
     * @return la distance entre les deux
     */
    private int getDistance(Node node, Node target) {
        for (Edge edge : edgesList) {
            if (edge.getFrom().equals(node.toString())
                && edge.getTo().equals(target.getId())) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    /**
     * Retourne les nodes voisins a un node source
     * @param node le node source
     * @return la liste des nodes voisins du node source
     */
    private List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<Node>();
        for (Edge edge : edgesList) {
            Node edgeTo = null;
            for (Node n: nodesList) {
                if(n!=null){
                    if(n.getId().equals(edge.getTo())){
                        edgeTo = n;
                    }
                }
            }
            if (edge.getFrom().equals(node.getId())
                && !isSettled(edgeTo)) {
                for (Node n: nodesList) {
                    if(n!=null) {
                        if (n.getId().equals(edge.getTo())) {
                            neighbors.add(n);
                        }
                    }
                }
            }
        }
        return neighbors;
    }

    /**
     * Retourne le node d'une liste qui a le poid le plus court
     * @param nodes la liste de nodes à vérifier
     * @return le node ayant le poid le plus petit
     */
    private Node getMinimum(Set<Node> nodes) {
        Node minimum = null;
        for (Node node : nodes) {
            if (minimum == null) {
                minimum = node;
            } else {
                if (getDistPlusCourte(node) < getDistPlusCourte(minimum)) {
                    minimum = node;
                }
            }
        }
        return minimum;
    }

    /**
     * Retourne si le node a déjà été traité
     * @param node le node voulu
     * @return si oui ou non le node a été traité
     */
    private boolean isSettled(Node node) {
        return settleNodes.contains(node);
    }

    private int getDistPlusCourte(Node destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /**
     * Retourne le chemin pour se rendre a un node
     * @param target le node cible
     * @return la liste qui définis le chemin vers la cible
     */
    private LinkedList<Node> getPath(Node target) {
        LinkedList<Node> path = new LinkedList<Node>();
        Node step = target;
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        Collections.reverse(path);
        return path;
    }

    public ArrayList<Node> getNodesForDistance(int weight, ArrayList<Node> nodes, maps.Map map){
        ArrayList<Node> nodeApprouve = new ArrayList<>();
        for (Node node :nodes) {
            boolean first = true;
            LinkedList<Node> path = getPath(node);
            int pathWeight=0;
            Node nodeAnt = null;
            if(path!=null){
                for (Node n:path) {
                    if(first){
                        nodeAnt=n;
                        first = false;
                    } else{
                        pathWeight+=(getDistance(nodeAnt, n, map)*10);
                        nodeAnt=n;
                    }
                }
                if(pathWeight<=weight) nodeApprouve.add(node);
            }
        }
        return nodeApprouve;
    }

    /**
     * Retourne la distance entre deux points dans une carte
     * @param node début
     * @param target cible
     * @param map carte
     * @return la distance
     */
    private double getDistance(Node node, Node target, maps.Map map) {
        for (Edge edge : edgesList) {
            if (edge.getFrom().equals(node.toString())
                && edge.getTo().equals(target.getId())) {
                ArrayList<LaneEdgeLien> laneArrayList = map.getLaneEdgeLiens();
                for (LaneEdgeLien lane : laneArrayList) {
                    if (lane.getEdge().getId().equals(edge.getId())) {
                        return dist.getDistanceLane(map.getLaneEdgeLiens(), nodesList, lane.getIdLane());
                    }
                }
                return 1;
            }
        }
        throw new RuntimeException("Should not happen");
    }

    /**
     * Retourne la distance d'un Path
     * @param target le node cible
     * @param map la carte
     * @return la distance du path
     */
    public int getPathDist(Node target, maps.Map map){
        LinkedList<Node> path = getPath(target);
        boolean first = true;
        int pathWeight=0;
        Node nodeAnt = null;
        if(path!=null) {
            for (Node n : path) {
                if (first) {
                    nodeAnt = n;
                    first = false;
                } else {
                    pathWeight += (getDistance(nodeAnt, n, map) * 10);
                    nodeAnt = n;
                }
            }
        }
        return pathWeight;
    }

    /**
     * Définis auxquel ont peut se rendre tout en ayant le poid le plus petit
     * @param source le node de source du chemin que l'on voudra trouver
     */
    public void execute(Node source) {
        settleNodes = new HashSet<>();
        unSettledNodes = new HashSet<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Node node = getMinimum(unSettledNodes);
            settleNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

}
