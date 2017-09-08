package ecouteurs;

import java.util.EventListener;

/**
 * @author Michael Oliveira-Silva
 * Interface pour l'écouteur personnalisé des véhicules
 */
public interface VehicListener extends EventListener{

    /**
     * Évenement pour l'arrivé d'un véhicule
     */
    public void vehicArriver();

}
