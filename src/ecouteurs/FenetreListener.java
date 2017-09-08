package ecouteurs;

import java.util.EventListener;

/**
 * @author Gabrielle Lim
 * Interface pour les écouteurs personnalisées permettant de savoir lorsqu'une fenêtre est fermée ou doit être ouverte suite à
 * l'action d'un bouton servant à recommencer une nouvelle simulation
 */
public interface FenetreListener extends EventListener{

    /**
     * Se déclenche lorsque la fenêtre est fermée
     */
    public void fenetreClose();

    /**
     * Se déclenche lorsque l'option de recommencer une nouvelle simulation est choisie
     */
    public void boutonRecommencer();

    /**
     * Se déclenche lorsque la priorité numéro 1 de l'algorithme est modifié
     * @param priorite le String de la priorité
     * @param index l'index de la priorité
     */
    public void optimiserTop(String priorite, int index);

    /**
     * Se déclenche lorsque la priorité numéro 2 de l'algorithme est modifié
     * @param priorite le String de la priorité
     * @param index l'index de la priorité
     */
    public void optimiserMid(String priorite, int index);

    /**
     * Se déclenche lorsque la priorité numéro 3 de l'algorithme est modifié
     * @param priorite le String de la priorité
     * @param index l'index de la priorité
     */
    public void optimiserBot(String priorite, int index);

}
