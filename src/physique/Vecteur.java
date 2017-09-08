package physique;

/**
 * La classe Vecteur permet de réaliser les operations de base sur un vecteur Euclidien en deux dimensions (x,y), où x et y sont les
 * composantes du vecteur.
 * 
 * Cette classe est une version 2d modifiée de la classe SVector3d ecrite par Simon Vezina.
 * Parmi les différences avec cette dernière, on note que les méthodes non static additionne, soustrait, multiplie et normalise affectent le
 * vecteur courant (à la place de retourner un nouveau vecteur)
 * 
 * @author Simon Vézina
 * @author Caroline Houle
 * 
 * @version 2.0 (plusieurs méthodes agissent dorénavant sur le vecteur courant)
 */
public class Vecteur {
	//champs de base
	private static final double EPSILON = 1e-10; //tolerance utilisee dans les comparaisons reeles avec zero
	private double x;	//composante x du vecteur 2d
	private double y;	//composante y du vecteur 2d

	/**
	 * Constructeur representant un vecteur 2d aux composantes nulles
	 */
	public Vecteur(){
		x = 0;
		y = 0;
	}
	
	/**
	 * Constructeur avec composantes x,y
	 * @param x La composante x du vecteur.
	 * @param y La composante y du vecteur.
	 */
	public Vecteur(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	
	/**
	 * Methode qui donne acces a la composante x du vecteur.
	 * @return La composante x
	 */
	public double getX(){ 
	  return x;
	}
	
	/**
	 * Methode qui donne acces a la composante y du vecteur.
	 * @return La composante y
	 */
	public double getY(){ 
	  return y;
	}
	
	/**
	 * Methode qui permet de modifier la composante x du vecteur.
	 * @param x La nouvelle composante x
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * Methode qui permet de modifier la composante y du vecteur.
	 * @param y La nouvelle composante y
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * Methode qui permet de modifier les composantes du vecteur.
	 * @param x La nouvelle composante x
	 * @param y La nouvelle composante y
	 */
	public void setXY(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Genere une chaine de caractere avec les informations  du vecteur
	 */
	@Override
	public String toString(){
		return "[ x = " + x + ", y = " + y + "]";		
	}	
	
	/**
	 * Determine si le vecteur courant est egal ou non a un autre vecteur, a EPSILON pres
	 */
	@Override
	public boolean equals(Object obj) {
	    if(this == obj)
	      return true;
	    
	    if(obj == null)
	      return false;
	    
	    if(!(obj instanceof Vecteur))
	      return false;
	    
	    Vecteur other = (Vecteur) obj;
	    
	    //Comparaison des valeurs x,y et z en utilisant la precision de EPSILON modulee par la valeur a comparer
	    if(Math.abs(x - other.x) > Math.abs(x)*EPSILON)
	      return false;
	    
	    if(Math.abs(y - other.y) > Math.abs(y)*EPSILON)
	      return false;
	    
	    return true;
	  }
	
	/**
	 * Methode qui additionne un vecteur quelconque au vecteur courant.
	 * @param v Le vecteur a ajouter au vecteur courant
	 */
	public void additionne(Vecteur v){	
		x = x + v.x;
		y = y + v.y;
	}
	
	/**
	 * Methode de classe qui retourne l'addition de deux vecteurs quelconques.
	 * @param v1 Le premier vecteur
	 * @param v2 Le deuxieme vecteur
	 * @return La somme des deux vecteurs
	 */
	public static Vecteur additionne(Vecteur v1, Vecteur v2)
	{ 
	  return new Vecteur(v1.x + v2.x, v1.y + v2.y);
	}
	
	/**
	 * Methode qui soustrait un vecteur quelconque du vecteur courant.
	 * @param v Le vecteur a soustraire au vecteur courant.
	 */
	public void soustrait(Vecteur v){
		x = x - v.x;
		y = y - v.y;
	}
	
	/**
	 * Methode de classe qui retourne la soustraction entre deux vecteurs quelconques.
	 * @param v1 Le premier vecteur
	 * @param v2 Le deuxieme vecteur, a soustraire du premier
	 * @return La diffrence entre les deux vecteurs
	 */
	public static Vecteur soustrait(Vecteur v1, Vecteur v2)
	{ 
		 return new Vecteur(v1.x - v2.x, v1.y - v2.y);
	}
	
	
	/**
	 * Methode qui multiplie le vecteur courant par une scalaire.
	 * @param m - Le muliplicateur
	 */
	public void multiplie(double m){
		x = m*x;
		y = m*y;
	}
	
	/**
	 * Methode de classe qui effectue la multiplication d'un vecteur quelconque par une scalaire.
	 * @param v Le vecteur
	 * @param m Le muliplicateur
	 * @return Le resultat de la multiplication par un scalaire m sur le vecteur.
	 */
	public static Vecteur multiplie(Vecteur v, double m)
	{ 
		return new Vecteur(m*v.x, m*v.y);
	}
	
	/**
	 * Methode pour obtenir le module du vecteur courant.
	 * @return Le module du vecteur courant.
	 */
	public double module(){
		return Math.sqrt((x*x) + (y*y));
	}
	
	/**
	 * Methode de classe pour obtenir le module d'un vecteur quelconque.
	 * @param v Le vecteur
	 * @return Le module du vecteur.
	 */
	public static double module(Vecteur v){
		return v.module();
	}
	
	/**
	 * Methode pour normaliser le vecteur courant.
	 * Un vecteur normalise possede la meme orientation, mais possede une longeur unitaire.
	 * 
	 * @throws Exception Si le vecteur ne peut pas etre normalise etant trop petit ou de longueur nulle.
	 */
	public void normalise() throws Exception
	{
		double mod = module();			//obtenir le module du vecteur
		
		//Verification du module. S'il est trop petit, nous ne pouvons pas numeriquement normaliser ce vecteur
		if(mod < EPSILON) { 
		  throw new Exception("Erreur Vecteur: Le vecteur " + this.toString() + " �tant nul ou presque nul ne peut pas etre normalis�.");
		} else {
			x = x/mod;
			y = y/mod;
		}
	}
	
	/**
	 * Methode de classe pour normaliser un vecteur quelconque.
	 * Un vecteur normalise possede la meme orientation, mais possede une longeur unitaire.
	 * 
	 * @param v Le vecteur
	 * @return Le vecteur normalisé.
	 * @throws Exception Si le vecteur ne peut pas etre normalisé étant trop petit ou de longueur nulle.
	 */
	public static Vecteur normalise(Vecteur v) throws Exception
	{
		double mod = module(v);			//obtenir le module du vecteur
		
		//Verification du module. S'il est trop petit, nous ne pouvons pas numeriquement normaliser ce vecteur
		if(mod < EPSILON) { 
		  throw new Exception("Erreur Vecteur: Le vecteur " + v.toString() + " \u00e9tant nul ou presque nul ne peut pas etre normalis\u00e9.");
		} else {
			return( new Vecteur (v.x/mod, v.y/mod));
		}
	}
	
	/**
	 * Methode pour effectuer le produit scalaire du vecteur courant avec un autre vecteur.
	 * @param v L'autre vecteur.
	 * @return Le produit scalaire entre les deux vecteurs.
	 */
	public double prodScalaire(Vecteur v){
		return (x*v.x + y*v.y);
	}
	
	/**
	 * Methode de classe pour effectuer le produit scalaire entre deux vecteurs quelconques.
	 * @param v1 Le premier vecteur
	 * @param v2 Le deuxieme vecteur
	 * @return Le produit scalaire entre les deux vecteurs.
	 */
	public static double prodScalaire(Vecteur v1, Vecteur v2){
		return (v1.prodScalaire(v2));
	}

 
}//fin classe Vecteur
