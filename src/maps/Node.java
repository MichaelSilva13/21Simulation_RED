package maps;

import java.awt.geom.Point2D;

/**
 * @author Gabrielle Lim
 * Classe permettant de créer un composant contenant les informations sur des points important de la map permettant de la dessiner
 */
public class Node {
    private String id;
    private Point2D.Double position;

    /**
     * Permet de créer un composant Node qui contient les informations sur un point (node) où nous avons le id et les coordonnées du point
     *
     * @param id Code permettant d'identifier les différentes positions (node) afin de pouvoir les différencier
     * @param x Coordonnée en x du point
     * @param y Coordonnée en y du point
     */
    public Node(String id, Double x, Double y) {
        this.id = id;
        this.position = new Point2D.Double(x,y);
    }

    /**
     * Méthode qui retourne le id de la position
     * @return le id de la position
     */
    public String getId(){
        return this.id;
    }

    /**
     * Méthode qui retourne un Point2D.Double contenant les informations sur la position du point
     * @return la position du point
     */
    public Point2D.Double getPosition(){
        return this.position;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /**
     * Déclare si deux objets sont équivalents
     * @param obj l'objet à vérifier
     * @return si les objets sont équivalents
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    /**
     * Retourne le string du node
     * @return l'ID du node
     */
    @Override
    public String toString() {
        return id;
    }


}
