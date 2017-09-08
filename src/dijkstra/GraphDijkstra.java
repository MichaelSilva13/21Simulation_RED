package dijkstra;

import maps.Edge;
import maps.Node;

import java.util.List;

/**
 * Graph qui sera utilisé pour l'algorithme de Dijkstra
 * @author Michael Oliveira-Silva
 */
public class GraphDijkstra {
    private List<Edge> edgelist;
    private List<Node> nodeList;

    /**
     * Constructeur de la classe
     * @param edgelist Liste de edge (routes)
     * @param nodeList Liste de node (intersections)
     */
    public GraphDijkstra(List<Edge> edgelist,List<Node> nodeList) {
        this.edgelist = edgelist;
        this.nodeList = nodeList;
    }

    /**
     * Retourne la liste des Edges du graph
     * @return la liste des edges
     */
    public List<Edge> getEdgelist() {
        return edgelist;
    }

    /**
     * Retourne la liste des Nodes du graph
     * @return la liste des nodes
     */
    public List<Node> getNodeList() {
        return nodeList;
    }
}
