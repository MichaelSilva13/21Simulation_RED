package maps;

/**
 * @author Gabrielle Lim
 * Classe permettant de créer un composant contenant les informations sur les liens à faire entre les positions (node) afin de créer les routes
 */
public class Edge {
    String id;
    private String from;
    private String to;
    private final int DEFAULT_WEIGHT = 1;
    private int weight = DEFAULT_WEIGHT;

    /**
     * Permet de créer un composant Edge avec les informations d'une route (Edge) où nous avons sont id, le point de départ et le point d'arrivée de celle-ci
     *
     * @param id Code permettant d'identifier les différentes routes (edge) afin de pouvoir les différencier
     * @param from id de la position initiale pour relier les extrémités de la route
     * @param to id de la position finale pour relier les extrémités de la route
     */
    public Edge(String id, String from, String to){
        this.id = id;
        this.from = from;
        this.to = to;
    }

    /**
     * Constructeur permettant de créer un composant Edge mais avec un poids
     * @param id Code permettant d'identifier les différentes routes (edge) afin de pouvoir les différencier
     * @param from id de la position initiale pour relier les extrémités de la route
     * @param to id de la position finale pour relier les extrémités de la route
     * @param weight le poids de la route
     */
    public Edge(String id, String from, String to, int weight){
        this.id = id;
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    /**
     * Méthode qui retourne le id de la position initale de la route
     * @return le id de la position initiale de la route
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * Méthode qui retourne le id de la position finale de la route
     * @return le ide de la position finale de la route
     */
    public String getTo() {
        return this.to;
    }

    /**
     * Méthode qui retourne le id de l'edge (route)
     * @return le id de l'edge
     */
    public String getId() {
        return id;
    }

    /**
     * Méthode permettant de retourner le poids de la route (sert pour Djikstra)
     * @return retourne le poids 
     */
    public int getWeight() {
        return weight;
    }
}
