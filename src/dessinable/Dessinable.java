package dessinable;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 * Interface pour les objets dessinables
 * @author Michael Oliveira-Silva
 *
 */
public interface Dessinable {

	/**
	 * Interface reliant les différents objets dessinables
	 * @param g2d Contexte graphique du dessin
	 * @param mat Contexte matrice du dessin
	 */
	public void dessiner(Graphics2D g2d, AffineTransform mat);
	
}
