package ecouteurs;

import vehicules.VehicElect;

import java.util.EventListener;

/**
 * Listener permettant de gérer les demandes de recharge aux bornes
 * @author Michael Oliveira-Silva
 */
public interface BorneListener extends EventListener{

    /**
     * écouteur d'une demande de recharge d'un véhicule électrique à cette borne
     * @param vehicElect véhicule qui a effectuer la demande
     */
    public void reservationEffectuer(VehicElect vehicElect);

    /**
     * écouteur de la fin de la recharge du véhicule
     */
    public void chargementTerminer();

    /**
     * écouteur du changement de charge de la centrale1
     * @param charge la charge de la centrale
     */
    //Gabrielle Lim
    public void changementChargeCentrale1(double charge);

    /**
     * écouteur du changement de charge de la centrale2
     * @param charge la charge de la centrale
     */
    //Gabrielle Lim
    public void changementChargeCentrale2(double charge);

    /**
     * écouteur du changement de charge de la centrale3
     * @param charge la charge de la centrale
     */
    //Gabrielle Lim
    public void changementChargeCentrale3(double charge);
}
