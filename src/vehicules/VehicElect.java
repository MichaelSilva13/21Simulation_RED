package vehicules;

import java.awt.geom.Point2D;
import java.io.IOException;

import javax.swing.event.EventListenerList;

import bornes.Borne;
import dessinable.Dessinable;
import ecouteurs.EVListener;
import it.polito.appeal.traci.Vehicle;
import physique.Outils;

/**
 * @author Michael Oliveira-Silva
 * Classe possédant les caractéristiques spécifiques à un véhicule électrique.
 */
public class VehicElect extends Vehicule implements Dessinable, Runnable {

    private double tempCharge = 0;
    private String[] marques = {"BMW i3",
            "Chevrolet Bolt",
            "Ford Focus \u00c9lectrique",
            "Kia Soul EV",
            "Mitsubishi i-MiEV",
            "Nissan Leaf",
            "Tesla Model S",
            "Tesla Model X"};
    private double[] arrayChargesMax = {130000, 383000, 122000, 149000, 100000, 172000, 435000, 413000};
    private double[] arrayChargeMaxKWh = {18800, 60000, 23000, 27000, 16000, 30000, 60000, 85000};
    private double[] tempChargeMax = {6.25,9.5,4,4.5,5,5,12,12};
    private double chargeMax;
    private double charge = 130;
    private double chargeKwH;
    private double chargeMaxKwh;
    private long sleep;
    private double chargeVoulue;
    private double tempsChargement;
    private boolean isCharging = false;
    private boolean demandeEffect = false;
    private double tempsChargeDemander;
    private double consommation;
    private final EventListenerList SAVED_OBJECTS = new EventListenerList();
    private Borne borneTarget;
    private String marque;
    private double changeRouteTime;

    /**
     * Crée un ScvVehicle
     *
     * @param position    la position du vehicule
     * @param sumoVehicle un véhicule sumo
     * @param marque marque du véhicule
     * @throws IOException exceptions
     */
    public VehicElect(Point2D.Double position, Vehicle sumoVehicle, String marque) throws IOException {
        super(position, sumoVehicle);
        setElect(true);
        int index = containsMarque(marque);
        if(index!=-1){
            setChargeMax(arrayChargesMax[index]);
            chargeMaxKwh = arrayChargeMaxKWh[index];
            setCharge(Math.random()*chargeMax);
            chargeKwH = (charge/getChargeMax())/chargeMaxKwh;
            setTempCharge(tempChargeMax[index]);
        }
        this.marque = marque;

    }

    public VehicElect(Point2D.Double position, Vehicle sumoVehicle, int index) throws IOException {
        super(position, sumoVehicle);
        setElect(true);
        if(index!=-1){
            setChargeMax(arrayChargesMax[index]);
            chargeMaxKwh = arrayChargeMaxKWh[index];
            setCharge(Math.random()*chargeMax);
            chargeKwH = (charge/getChargeMax())/chargeMaxKwh;
            setTempCharge(tempChargeMax[index]);
            this.marque = marques[index];
        }

    }

    /**
     * Retourne la charge du véhicule (en km)
     * @return la charge disponible du véhicule en km
     */
    public double getCharge() {
        return charge;
    }

    /**
     * Détermine la charge (en km) du véhicule électrique
     * @param charge la charge courante du véhicule en km
     */
    private void setCharge(double charge) {
        this.charge = charge;
    }

    /**
     * Retourne la capacité maximale (en km) du véhicule
     * @return la charge Maximale en km du véhicule
     */
    public double getChargeMax() {
        return chargeMax;
    }

    /**
     * Détermine la charge (en km) du véhicule électrique
     * @param chargeMax la valeur maximale du véhicule
     */
    private void setChargeMax(double chargeMax) {
        this.chargeMax = chargeMax;
    }

    /**
     * Retourne la consommation d'électricité du véhicule dans le dernier "step"
     * @return la consommmation d'électricité en km
     */
    public double getConsommation() {
        return consommation;
    }

    /**
     * Détermine la consommation d'électricité du véhicule en km
     * @param consommation du véhicule en km
     */
    public void setConsommation(double consommation) {
        this.consommation = consommation;
    }

    /**
     * Retourne l'index de la marque du véhicule dans le tableau des marques
     * @param marque une chaine qui montre la marque du véhicule
     * @return l'index de la marque dans le tableau
     */
    private int containsMarque(String marque){
        for (int i = 0; i < this.marques.length; i++) {
            if(marques[i].equalsIgnoreCase(marque)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Retourne le temps de charge en heures du véhicule électrique de 0 a 100%
     * @return le temps de charge du véhicule
     */
    public double getTempCharge() {
        return tempCharge;
    }

    /**
     * Détermine le temps de charge de 0 a 100% du véhicule
     * @param tempCharge temps de charfe de 0 a 100%
     */
    private void setTempCharge(double tempCharge) {
        this.tempCharge = tempCharge;
    }

    /**
     * Redéfinis la position d'un véhicule et réduit la charge du véhicule électrique
     * @param position la nouvelle position du vehicule
     */
    @Override
    public void setPosition(Point2D.Double position){
        super.setPosition(position);
        if(getPosition().getX()==getPosition().getX()){
            charge= charge-(Math.abs(getPosition().getY()-getPosAnt().getY())*10);
        }else if(getPosition().getY()==getPosition().getY()){
            charge= charge-(Math.abs((getPosition().getX()-getPosAnt().getX()))*10);
        }else{
            double deplaceX = Math.abs((getPosition().getX()-getPosAnt().getX()))*10;
            double deplaceY = Math.abs((getPosition().getY()-getPosAnt().getY()))*10;
            double dist = Math.sqrt((deplaceX*deplaceX)+(deplaceY*deplaceY));
            charge = charge-dist;
        }
    }

    /**
     * Redéfinis la charge demandée du véhicule électrique
     * @param sleep temps du sleep du thread
     * @param chargeVoulu la charge que l'on va donné au véhicule
     */
    public void chargeVehicule(long sleep, double chargeVoulu){
        this.sleep = sleep;
        this.chargeVoulue = ((chargeMax - (chargeMax*chargeVoulu))/chargeMax);
        setTempsChargeDemander(this.chargeVoulue*tempsChargement);
    }

    /**
     * Méthode de recharge du véhicule
     */
    @Override
    public void run() {
        int temps = 0;
        while (isCharging) {
            temps+=sleep;
            if(temps>=tempsChargement) {
                isCharging = false;
            }
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        isCharging= false;
    }

    /**
     * Retourne une chaîne qui décris les informations du véhicule électrique
     * @return la chaîne d'informations du véhicule
     * @throws IOException exceptions
     */
    @Override
    public String getInfo() throws IOException {
        double deplaceX = Math.abs((getPosition().getX()-getPosAnt().getX()));
        double deplaceY = Math.abs((getPosition().getY()-getPosAnt().getY()));
        double dist = Math.sqrt((deplaceX*deplaceX)+(deplaceY*deplaceY))*10;
        double vitesse = dist/getStepValue();
        String info = "Véhicule ID : " + getID() + "\n" + "\n" +
                      "Électrique : oui" + ", Marque : " + this.marque + ", Charge Max :  " + this.chargeMax + "m" +"\n" + "\n" +
                      "En rechargement : " + isCharging + "\n \n" +
                      "Position X : " + Outils.ajusterPrecision(getPosition().getX(), 2) + ", Position Y : " + Outils.ajusterPrecision(getPosition().getY(), 2) + "\n" + "\n" +
                      "Vitesse : " + Outils.ajusterPrecision(vitesse, 2) + "m/s" + ", Distance restante : " + Outils.ajusterPrecision(charge, 2) + "m";
        return info;
    }

    /**
     * Méthode qui effectue la demande du véhicule.
     */
    public void effectueDemande(){
        demandeEffect=true;
        levDemandeEffect();
    }

    /**
     * Rajoute un écouteur d'évènement EVListener
     * @param evListener un EVListener de notre choix
     */
    public void addEVListener(EVListener evListener){
        SAVED_OBJECTS.add(EVListener.class, evListener);
    }

    /**
     * Lève l'écouteur de l'évènement demandeEffect
     */
    private void levDemandeEffect(){
        for (EVListener listener : SAVED_OBJECTS.getListeners(EVListener.class)) {
            listener.demandeEffectuer();
        }
    }

    /**
     * Change la route du véhicule
     * @param edge un edge de sumo
     * @throws IOException exceptions
     */
    public void changeRoute(it.polito.appeal.traci.Edge edge) throws IOException {
        getSumoVehicule().changeTarget(edge);
        setTarget(edge.getID());
        setRoute(getSumoVehicule().getCurrentRoute());
        changeRouteTime = System.nanoTime();
    }

    /**
     * Retourne le temps où la route à été changer
     * @return le temps de changement
     */
    public double getChangeRouteTime() {
        return changeRouteTime;
    }

    /**
     * Retourne si une demande à été effectué
     * @return si la demande a été effectué
     */
    public boolean isDemandeEffect() {
        return demandeEffect;
    }

    /**
     * Retourne la borne cible du véhicule
     * @return la borne du véhicule
     */
    public Borne getBorneTarget() {
        return borneTarget;
    }

    /**
     * Définis la borne cible du véhicule
     * @param borneTarget la borne cible
     */
    public void setBorneTarget(Borne borneTarget) {
        this.borneTarget = borneTarget;
    }

    /**
     * Permet de définir la charge en kWh du véhicule
     * @param chargeKwH la charge en kWh
     */
    public void setChargeKwH(double chargeKwH) {
        this.chargeKwH = chargeKwH;
    }

    /**
     * Retourne la charge en kWh du véhicule
     * @return la charge en kWh
     */
    public double getChargeMaxKwh() {
        return chargeMaxKwh;
    }

    /**
     * retourne la charge en kWh du véhicule
     * @return la charge
     */
	public double getChargeKwH() {
		return chargeKwH;
	}

	/**
	 * retourne le temps de charge demander
	 * @return le temps de charge
	 */
	public double getTempsChargeDemander() {
		return tempsChargeDemander;
	}

	/**
	 * défini le temps de charge demander
	 * @param tempsChargeDemander le temps de charge demander
	 */
	public void setTempsChargeDemander(double tempsChargeDemander) {
		this.tempsChargeDemander = tempsChargeDemander;
	}

}
    
    
