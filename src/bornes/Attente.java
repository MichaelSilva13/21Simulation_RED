package bornes;

import ecouteurs.AttenteListener;
import javax.swing.event.EventListenerList;

/**
 * Classe de l'attente d'un véhicule à une borne
 * @author Michael Oliveira-Silva
 */
public class Attente implements Runnable{
    private long tempsAtt = 0;
    private final long TEMP_SUPP = 2000;
    private long tempsMax = 1000, sleep = 0;
    private boolean arriv = false, dem = false;
    private final EventListenerList SAVED_OBJECTS = new EventListenerList();
    private Thread att = new Thread(this);

    /**
     * Méthode qui permet de modifier le temps maximal
     * @param tempsMax le nouveau temps maximal
     */
    public void setTempsMax(long tempsMax) {
        this.tempsMax = tempsMax;
    }

    /**
     * Méthode qui permet de modifier si le véhicule est arrivé ou non
     * @param arriv vrai si le véhicule est arrivé, faux dans le cas contraire
     */
    public void setArriv(boolean arriv) {
        this.arriv = arriv;
    }

    /**
     * Méthode qui retourne si le véhicule est arrivé
     * @return vrai si le véhicule est arrivé, faux dans le cas contraire
     */
    public boolean isArriv() {
        return arriv;
    }

    /**
     * Démarre l'attente d'un véhicule
     */
    public void start(){
        if(!dem){
            att.start();
            dem=true;
        }
    }

    /**
     * Thread de l'attente d'un véhicule
     */
    @Override
    public void run() {
        while(tempsAtt<(tempsMax+TEMP_SUPP)&&(!arriv)){
            tempsAtt++;
            try {
                Thread.sleep(sleep);
                System.out.println(arriv);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        leverEventArrive();
        arriv=false;

    }

    /**
     * Ajoute un écouteur d'attente
     * @param listener écouteur d'attente
     */
    public void addArriveListener(AttenteListener listener){
        SAVED_OBJECTS.add(AttenteListener.class, listener);
    }

    /**
     * Lève un évenement si un véhicule arrive à ka borne
     */
    private void leverEventArrive(){
        for(AttenteListener ecout : SAVED_OBJECTS.getListeners(AttenteListener.class)){
            ecout.arriveListener();
        }
    }

    /**
     * Définis le sleep
     * @param sleep le sleep
     */
    public void setSleep(long sleep) {
        this.sleep = sleep;
    }
}
