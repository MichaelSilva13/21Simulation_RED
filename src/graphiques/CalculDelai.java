package graphiques;

import java.util.ArrayList;

/**
 * Classe permettant de calculer le délai d'attente
 * @author Gabrielle Lim
 */
public class CalculDelai {

    private static ArrayList<Double> tempsRechercheBorne = new ArrayList<>();
    private static ArrayList<Double> tempsArriveeBorne = new ArrayList<>();
    private static ArrayList<Double> tempsChargement =  new ArrayList<>();
    private static double nombreDemande = 0;
    private static double delaiTot;
    private static double delaiMoyen;

    /**
     * Méthode permettant de déterminer le temps de recherche de borne
     * @param temps le temps de recherche de borne
     */
    public static void setTempsRechercheBorne(double temps){
        tempsRechercheBorne.add(temps);
        nombreDemande++;
        delaiTot += temps;
        calculDelaiMoyen();
    }

    /**
     * Méthode permettant de retourner le délai moyen d'attente
     * @return le délai moyen d'attente
     */
    public static double getDelaiMoyen() {
        return delaiMoyen;
    }

    /**
     * Méthode permettant de déterminer le temps d'arrivée jusqu'à la borne (calcul du temps de parcours)
     * @param temps le temps pour se rendre jusqu'à la borne
     */
    public static void setTempsArriveeBorne(double temps) {
        tempsArriveeBorne.add(temps);
        delaiTot += temps;
        calculDelaiMoyen();
    }

    /**
     * Méthode permettant de calculer le délai moyen
     */
    private static void calculDelaiMoyen(){
        delaiMoyen = (delaiTot/nombreDemande)/(Math.pow(10, 9));
    }

    /**
     * Méthode permettant de déterminer le temps de chargement d'un véhicule
     * @param temps le temps de chargement
     */
    public static void setTempsChargement(double temps) {
        tempsChargement.add(temps);
        delaiTot += temps;
        calculDelaiMoyen();
    }

    /**
     * Méthode permettant de calculer le temps d'attente
     * @param temps le temps d'attente
     */
    public static void calculTempsAttente(double temps) {
        delaiTot += temps;
        calculDelaiMoyen();
    }

    /**
     * Méthode qui diminue le temps d'attente quand celui-ci diminue
     */
    public static void diminutionTempsAttente(){
        delaiTot--;
        calculDelaiMoyen();
    }

    /**
     * Méthode qui permet de modifier le délai total
     * @param delaiTot le nouveau délai total
     */
    public static void setDelaiTot(double delaiTot) {
        CalculDelai.delaiTot = delaiTot;
    }

    /**
     * Méthode qui permet de retourner le délai d'attente total
     * @return le délai d'attente total
     */
    public static double getDelaiTot() {
        return delaiTot;
    }
}
