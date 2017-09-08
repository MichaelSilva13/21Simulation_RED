package maps;

/**
 * Classe permettant de créer un composant contenant tous les liens entre le id d'une lane et le edge de la lane en question
 * @author Gabrielle Lim
 */
public class LaneEdgeLien {

    Edge edge;
    String idLane;

    /**
     * Constructeur du composant LaneEdgeLien
     * @param idLane le id de lane qui permet de la différencié des autres lanes
     * @param edge un objet edge
     */
    public LaneEdgeLien(String idLane, Edge edge){
        this.edge = edge;
        this.idLane = idLane;
    }

    /**
     * Méthode qui retourne le id de la lane
     * @return le id de la lane
     */
    public String getIdLane() {
        return idLane;
    }

    /**
     * Méthode qui retourne le edge associé à la lane
     * @return le edge associé à la lane
     */
    public Edge getEdge(){
        return edge;
    }

}
