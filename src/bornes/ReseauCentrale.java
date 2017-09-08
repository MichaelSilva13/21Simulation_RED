package bornes;

import java.util.ArrayList;

/**
 * Classe qui contient le réseau de Centrale ainsi que ses informations
 * @author Gabrielle Lim
 */
public class ReseauCentrale {

    private static ArrayList<Centrale> centrales;
    private static double niveauEnergie;

    /**
     * Constructeur permettant de créer un réseau de centrale
     * @param centrales une liste des centrales du réseau
     */
    public ReseauCentrale(ArrayList<Centrale> centrales) {
        ReseauCentrale.centrales = centrales;
        double pourcentageTotal = 0;
        for (int i = 0; i < centrales.size(); i++) {
            pourcentageTotal += centrales.get(i).getQuantiteEnergiePourcentage();
        }
        ReseauCentrale.niveauEnergie = pourcentageTotal/centrales.size();
    }

    /**
     * Méthode permettant de retourner le niveau d'énergie du réseau
     * @return le niveau d'énergie du réseau
     */
    public double getNiveauEnergie() {
        return niveauEnergie;
    }

    /**
     * Méthode permettant de recalculer le niveau d'énergie du réseau
     */
    public static void recalculerNiveauEnergie() {
        double pourcentageTotal = 0;
        for (int i = 0; i < centrales.size(); i++) {
            pourcentageTotal += centrales.get(i).getQuantiteEnergiePourcentage();
        }
        niveauEnergie = pourcentageTotal/centrales.size();
    }

    /**
     * Méthode permettant de retourner les centrales du réseau
     * @return les centrales du réseau
     */
    public static ArrayList<Centrale> getCentrales() {
        return centrales;
    }


}
