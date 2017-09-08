package ecouteurs;

import java.util.EventListener;

/**
 * Interface des écouteurs des véhicules électriques
 * @author Michael Oliveira-Silva
 */
public interface EVListener extends EventListener{

    /**
     * Le véhicule effectue une demande
     */
    public void demandeEffectuer();


}
