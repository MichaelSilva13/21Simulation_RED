package ecouteurs;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.EventListener;


/**
 * Listener permettant de savoir quand quelque chose a changé dans le composant d'animation
 * @author Cedryk
 * @author Gabrielle Lim
 */
public interface UpdateListener extends EventListener {

	//Cedryk
	/**
	 * Envoie les informations sur le vehicule selectionne lorsqu'il se deplace
	 * @param position la position du vehicule
	 * @param speed la vitesse du vehicule
	 * @throws IOException exceptions
	 */
	public void carInformationUpdated(Point2D.Double position, double speed) throws IOException;

	//Cedryk
	/**
	 * Envoie les informations sur le vehicule selectionne lorsqu'on clique dessus
	 * @param selected si le vehicule a été sélectionné ou déselectionné
	 * @param carName le nom du véhicule
	 * @param isElectric si le véhicule est électrique
	 * @throws IOException exceptions
	 */
	public void carClicked(boolean selected, String carName, boolean isElectric) throws IOException;

	/**
	 * Envoie les informations sur la borne sélectionnée lorsqu'on clique dessus
	 * @param selected Si la borne est sélectionnée ou non
	 * @throws IOException exception
	 */
	//Gabrielle Lim
	public void borneClicked(boolean selected) throws IOException;

	/**
	 * Méthode permettant de mettre à jour les valeurs du graphique de l'énergie
	 * @param energie l'énergie
	 * @param temps le temps
	 */
	//Gabrielle Lim
	public void updateValeursEnergie(double energie, double temps);

	/**
	 * Méthode permettant de mettre à jour les valeurs du graphique du délai d'attente
	 * @param delaiMoyen le délai Moyen
	 * @param tempsSim le temps
	 */
	//Gabrielle Lim
    public void updateValeursDelai(double delaiMoyen, double tempsSim);

	/**
	 * Méthode permettant de mettre à jour le nombre de bornes occupées
	 * @param valeur le nombre de bornes occupées
	 */
	//Gabrielle Lim
	public void updateValeurBorneOccup(int valeur);
}
