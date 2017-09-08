package ecouteurs;

import java.util.EventListener;

/**
 * Interface pour l'écouteur personnalisé des entrées
 * @author Michael
 */
public interface ValueListener extends EventListener{
	
	/**
	 * Se déclenche lorsque la valeur d'une des entrées est modifiée
	 */
	public void ValueChange();

}
