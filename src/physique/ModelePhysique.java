package physique;



import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

/**
 * Un objet ModelePhysique permet de mémoriser un ensemble de valeurs pour passer du monde réel vers un composant de dessin dont les
 * coordonnées sont en pixels. On peut interroger l'objet pour connaitre la matrice de transformation, le nombre de pixels par unité,
 * les dimensions dans le monde réel, etc.
 *
 * @author Caroline Houle
 */
 

public class ModelePhysique {
	private double hautUnitesReelles = -1;
	private double largUnitesReelles;
	private double largPixels;
	private double hautPixels;
	private double xOrigUnitesReelle;
	private double yOrigUnitesReelle;
	private double pixelsParUniteX;
	private double pixelsParUniteY;
	private AffineTransform matMC;
	private AffineTransform componentToReal;


	/**
	 * Permet de créer un objet ModelPhysique, pouvant mémoriser la matrice (et autres valeurs) de transformation pour passer du monde vers le composant. les dimensions du monde
	 * réel sont passées en paramètre (largeur et hauteur). Si le rapport entre les dimensions en pixels n'est pas identique au rapport entre les dimensions réelles, il y aura distortion.
	 *
	 * @param largPixels La largeur du composant, en pixels
	 * @param hautPixels La hauteur du composant, en pixels
	 * @param xOrigUnitesReelle L'origine en x de la portion du monde réel que l'on veut montrer
	 * @param yOrigUnitesReelle L'origine en y de la portion du monde réel que l'on veut montrer
	 * @param largUnitesReelles La largeur considérée dans le monde, en unité réelles
	 * @param hautUnitesReelles La hauteur considérée dans le monde, en unité réelles
	 */
	public ModelePhysique(double largPixels, double hautPixels, double xOrigUnitesReelle, double yOrigUnitesReelle, double largUnitesReelles, double hautUnitesReelles) {
		
		this.largPixels = largPixels;
		this.hautPixels = hautPixels;
		this.xOrigUnitesReelle = xOrigUnitesReelle;
		this.yOrigUnitesReelle = yOrigUnitesReelle;
		this.largUnitesReelles = largUnitesReelles;
		this.hautUnitesReelles = hautUnitesReelles;                            
		this.pixelsParUniteX = largPixels / largUnitesReelles;
		this.pixelsParUniteY = hautPixels / hautUnitesReelles ;
		
		this.pixelsParUniteX = largPixels / largUnitesReelles;
		this.pixelsParUniteY = hautPixels / hautUnitesReelles ;

		//calcul de la matrice monde-vers-composant
		AffineTransform mat = new AffineTransform();  //donne une matrice identite
		mat.scale( pixelsParUniteX, pixelsParUniteY ); 
		mat.translate(xOrigUnitesReelle, yOrigUnitesReelle);
		this.matMC = mat; //on m&eacute;morise la matrice (qui pourra &ecirc;tre retourn&eacute;e via le getter associ&eacute;)
		
	}
	
	/**
	 * Permet de créer un objet ModelPhysique, pouvant mémoriser la matrice (et autres valeurs) de transformation pour passer du monde vers le composant. Une des dimensions du monde
	 * réel est passée en paramètre (largeur ou hauteur). L'autre dimension sera  calculée de façon à n'introduire aucune distortion.
	 *
	 * @param largPixels La largeur du composant, en pixels
	 * @param hautPixels La hauteur du composant, en pixels
	 * @param xOrigUnitesReelle L'origine en x de la portion du monde réel que l'on veut montrer
	 * @param yOrigUnitesReelle L'origine en y de la portion du monde réel que l'on veut montrer
	 * @param dimensionEnUnitesReelles La dimensions considérée dans le monde, en unité réelles (il peut s'agir d'une largeur ou d'une hauteur, dépendant du dernier paramètre)
	 * @param typeLargeur Bool&eacute;en qui vaut vrai si la dimension fournie est la largeur du monde, vaut faux si la dimension fournie est la hauteur du monde
	 */
	public ModelePhysique(double largPixels, double hautPixels, double xOrigUnitesReelle, double yOrigUnitesReelle, double dimensionEnUnitesReelles, boolean typeLargeur) {
		
		this.largPixels = largPixels;
		this.hautPixels = hautPixels;
		this.xOrigUnitesReelle = xOrigUnitesReelle;
		this.yOrigUnitesReelle = yOrigUnitesReelle;
	
		if (typeLargeur) {
			//le parametre "dimensionEnUnitesReelles" represente la LARGEUR voulue
			this.largUnitesReelles = dimensionEnUnitesReelles;

			//on calcule la hauteur correspondante pour &eacute;viter toute distortion
			this.hautUnitesReelles = largUnitesReelles * hautPixels/largPixels;
			
		} else {
			//le parametre "dimensionEnUnitesReelles" ne represente PAS la largeur, il repr&eacute;sente plut&ocirc;t la HAUTEUR voulue
			this.hautUnitesReelles = dimensionEnUnitesReelles;

			//on calcule la largeur correspondante pour &eacute;viter toute distortion
			this.largUnitesReelles = hautUnitesReelles * largPixels/hautPixels;
		}
		        
		//pour ce constructeur il n'y a pas de distortion, donc les deux valeurs suivantes seront identiques
		this.pixelsParUniteX = largPixels / largUnitesReelles;
		this.pixelsParUniteY = hautPixels / hautUnitesReelles ;

		//calcul de la matrice monde-vers-composant
		AffineTransform mat = new AffineTransform();  //donne une matrice identite
		mat.scale( pixelsParUniteX, pixelsParUniteY ); 
		mat.translate(xOrigUnitesReelle, yOrigUnitesReelle);
		this.matMC = mat; //on m&eacute;morise la matrice (qui pourra &ecirc;tre retourn&eacute;e via le getter associ&eacute;)
		AffineTransform transform = new AffineTransform();

		transform.scale(pixelsParUniteX, -pixelsParUniteY);
		transform.translate(-xOrigUnitesReelle, -yOrigUnitesReelle);
		try {
			this.componentToReal = transform.createInverse();
		} catch (NoninvertibleTransformException e) {
			System.out.println("Can't inverse matrix");
		}
		
	}//fin constructeur

	
	/**
	 * Création d'un objet ModelePhysique où la dimension passée en paramètre est forcément la largeur du monde
	 *
	 * @param largPixels La largeur du composant, en pixels
	 * @param hautPixels La hauteur du composant, en pixels
	 * @param xOrigUnitesReelle L'origine en x de la portion du monde réel que l'on veut montrer
	 * @param yOrigUnitesReelle L'origine en y de la portion du monde réel que l'on veut montrer
	 * @param dimensionEnUnitesReelles La dimensions considérée dans le monde, en unité réelles (il peut s'agir d'une largeur ou d'une hauteur, dépendant du dernier paramètre)
	 */
	public ModelePhysique(double largPixels, double hautPixels, double xOrigUnitesReelle, double yOrigUnitesReelle, double dimensionEnUnitesReelles) {
		this( largPixels, hautPixels, xOrigUnitesReelle, yOrigUnitesReelle, dimensionEnUnitesReelles, true);
	}
	
	
	/**
	 * Création d'un objet ModelePhysique où l'origine du monde réel est à 0,0
	 *
	 * @param largPixels La largeur du composant, en pixels
	 * @param hautPixels La hauteur du composant, en pixels
	 * @param dimensionEnUnitesReelles La dimensions considérée dans le monde, en unité réelles (il peut s'agir d'une largeur ou d'une hauteur, dépendant du dernier paramètre)
	 * @param typeLargeur Booléen qui vaut vrai si la dimension fournie est la largeur du monde, qui vaut faux si la dimension fournie est la hauteur du monde
	 */
	public ModelePhysique(double largPixels, double hautPixels, double dimensionEnUnitesReelles, boolean typeLargeur) {
		this(largPixels, hautPixels, 0, 0, dimensionEnUnitesReelles, typeLargeur);
	}
	
	/**
	 *  Création d'un objet ModelePhysique où l'origine du monde réel est à 0,0, et où la dimension passée en paramètre est forcément la largeur du monde
	 *
	 * @param largPixels La largeur du composant, en pixels
	 * @param hautPixels La hauteur du composant, en pixels
	 * @param dimensionEnUnitesReelles La dimensions considérée dans le monde, en unité réelles (il peut s'agir d'une largeur ou d'une hauteur, dépendant du dernier paramètre)
	 */
	public ModelePhysique(double largPixels, double hautPixels, double dimensionEnUnitesReelles) {
		this(largPixels, hautPixels, 0, 0, dimensionEnUnitesReelles, true);
	}
	
	/**
	 * Retourne une copie de la matrice monde-vers-composant qui a été calculée dans le constructeur
	 * @return La matrice monde-vers-composant
	 */
	public AffineTransform getMatMC() {
		//on d&eacute;cide de retourner une copie de celle qui est m&eacute;moris&eacute;e, pour &eacute;viter qu'elle soit modifi&eacute;e
		return new AffineTransform (matMC);
	}
	
	/**
	 * Retourne la hauteur du monde, en unités réelles
	 * @return La hauteur du monde, en unités réelles
	 */
	public double getHautUnitesReelles() {
		return hautUnitesReelles;
	}

	/**
	 * Retourne la largeur du monde, en unités réelles
	 * @return La largeur du monde, en unités réelles
	 */
	public double getLargUnitesReelles() {
		return largUnitesReelles;
	}

	/**
	 * Retourne le nombre de pixels contenus dans une unité du monde réel, en x
	 * @return Le nombre de pixels contenus dans une unité du monde réel, en x
	 */
	public double getPixelsParUniteX() {
		return pixelsParUniteX;
	}

	/**
	 * Retourne le nombre de pixels contenus dans une unité du monde réel, en y
	 * @return Le nombre de pixels contenus dans une unité du monde réel, en y
	 */
	public double getPixelsParUniteY() {
		return pixelsParUniteY;
	}

	/**
	 * Retourne la largeur en pixels du composant auquel s'appliquera la transformation 
	 * @return La largeur en pixels 
	 */
	public double getLargPixels() {
		return largPixels;
	}

	/**
	 * Retourne la hauteur en pixels du composant auquel s'appliquera la transformation 
	 * @return La hauteur en pixels 
	 */
	public double getHautPixels() {
		return hautPixels;
	}

	/**
	 * Retourne l'origine, en x, de la portion du monde réel considérée
	 * @return L'origine en x, de la portion du monde réel considérée
	 */
	public double getxOrigUnitesReelle() {
		return xOrigUnitesReelle;
	}

	/**
	 * Retourne l'origine, en y, de la portion du monde réel considérée
	 * @return L'origine en y, de la portion du monde réel considérée
	 */
	public double getyOrigUnitesReelle() {
		return yOrigUnitesReelle;
	}

	/**
	 * Transforme un point en pixels en un point en unités réelles
	 * @param position le point en pixels
	 * @return le point en unités réelles
	 */
	public Point2D.Double componentPositionToReal(Point position) {
		Point2D.Double realPoint = new Point2D.Double();
		return (Point2D.Double) componentToReal.transform(new Point2D.Double(position.getX(), position.getY()), realPoint);
	}

	/**
	 * Permet de scale un dessin selont un certain point précis.
	 * @param scale valeur par laquelle on doit agrandir le dessin
	 * @param point Point central de l'agrandissement
	 */
	public void scaleOnPosition(double scale, Point point) {

		largUnitesReelles *= scale;
		hautUnitesReelles = largUnitesReelles * hautPixels / largPixels;
		pixelsParUniteX = largPixels / largUnitesReelles;
		pixelsParUniteY = hautPixels / hautUnitesReelles;

		Point2D.Double realPoint = this.componentPositionToReal(point);

		AffineTransform transform = new AffineTransform();

		transform.translate(point.getX(), point.getY() - hautUnitesReelles/10);
		transform.scale(pixelsParUniteX, pixelsParUniteY);
		transform.translate(realPoint.getX()+xOrigUnitesReelle,realPoint.getY()+yOrigUnitesReelle);

		matMC = transform;
		try {
			componentToReal = matMC.createInverse();
		} catch (NoninvertibleTransformException e) {
			System.out.println("Can't inverse matrix");
		}
		Point2D.Double realPointAfter = this.componentPositionToReal(point);
		matMC.translate(-(realPoint.getX()-realPointAfter.getX()), -(realPoint.getY()-realPointAfter.getY()));
		try {
			componentToReal = matMC.createInverse();
		} catch (NoninvertibleTransformException e) {
			System.out.println("Can't inverse matrix");
		}
	}

	/**
	 * Effectue une translation des matrices de transformation
	 * @param tx translation en X en pixels
	 * @param ty translation en Y en pixels
	 */
	//Cedryk
	public void translate(double tx, double ty){

		matMC.translate(-tx/pixelsParUniteX, -ty/pixelsParUniteY);
		try {
			componentToReal = matMC.createInverse();
		} catch (NoninvertibleTransformException e) {
			System.out.println("Can't inverse matrix");
		}
	}

	/**
	 * définis la matrice de transformation
	 * @param matMC matrice de transformation
	 */
	//Michael
	public void setMatMC(AffineTransform matMC) {
		this.matMC = matMC;
	}
}//fin classe
