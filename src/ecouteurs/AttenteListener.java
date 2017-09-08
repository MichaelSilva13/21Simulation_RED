package ecouteurs;

import java.util.EventListener;

/**
 * @author Michael Oliveira-Silva
 * Listener permettant de gérer l'attente aux bornes
 */
public interface AttenteListener extends EventListener{

    /**
     * Écouteur de l'arrivé d'un véhicule
     */
    public void arriveListener();
}
