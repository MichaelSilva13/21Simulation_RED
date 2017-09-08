package bornes;

import static bornes.ReseauCentrale.recalculerNiveauEnergie;

import java.util.ArrayList;

/**
 * @author Gabrielle Lim
 * Classe permettant de créer une centrale électrique qui possède une quantité limitée d'énergie et a accès à une
 * quantité limité de bornes
 */
public class Centrale{

    private ArrayList<Borne> bornes = new ArrayList<>();
    private final double QUANTITE_ENERGIE_DEFAUT = 22000;
    private double quantiteEnergie = QUANTITE_ENERGIE_DEFAUT;
    private double quantiteEnergiePourcentage =0;
    private double quantiteEnergieTotal = 0;


    /**
     * Constructeur d'une Centrale avec pourcentage d'énergie restant et la liste des bornes lui appartenant
     * @param pourcentageEnergieRestant pourcentage d'énergie restant de la centrale
     * @param bornesLuiAppartenant liste des bornes appartenant à la centrale
     */
    public Centrale(double pourcentageEnergieRestant, ArrayList<Borne> bornesLuiAppartenant) {
        this.quantiteEnergie = QUANTITE_ENERGIE_DEFAUT * bornesLuiAppartenant.size() * pourcentageEnergieRestant;
        this.quantiteEnergieTotal = QUANTITE_ENERGIE_DEFAUT * bornesLuiAppartenant.size();
        this.bornes = bornesLuiAppartenant;
        this.quantiteEnergiePourcentage = pourcentageEnergieRestant;
    }

    /**
     * Constructeur vide de centrale
     */
    public Centrale() {
    }

    /**
     * Méthode permettant d'obtenir la liste des bornes appartenant à la centrale
     * @return un ArrayList des bornes appartenant à la centrale
     */
    public ArrayList<Borne> getBornes() {
        return bornes;
    }

    /**
     * Méthode permettant de modifié la liste des bornes appartenant à la centrale
     * @param bornes un ArrayList des nouvelles bornes qui vont appartenir à la centrale
     */
    public void setBornes(ArrayList<Borne> bornes) {
        this.bornes = bornes;
        this.quantiteEnergieTotal = QUANTITE_ENERGIE_DEFAUT * bornes.size();
    }

    /**
     * Méthode permettant de retourner la quantité d'énergie de la centrale
     * @return la quantité d'énergie de la centrale
     */
    public double getQuantiteEnergie() {
        return quantiteEnergie;
    }

    /**
     * Méthode permettant de modifié la quantité d'énergie de la centrale
     * @param quantiteEnergie la nouvelle quantité d'énergie de la centrale
     */
    public void setQuantiteEnergie(double quantiteEnergie) {
        this.quantiteEnergie = quantiteEnergie;
        this.quantiteEnergiePourcentage = (quantiteEnergie/quantiteEnergieTotal)*100;
    }

    /**
     * Méthode permettant de modifié la quantité d'énergie de la centrale à partir des pourcentages de chaque borne
     * @param pourcentageRestantChqBorne tableau contenant le pourcentage de chaque borne
     */
    public void setQuantiteEnergie(double[] pourcentageRestantChqBorne) {
        double pourcentageMoyen;
        double totalPourcentage = 0;

        for (int i = 0; i < pourcentageRestantChqBorne.length; i++) {
            totalPourcentage += pourcentageRestantChqBorne[i];
        }
        pourcentageMoyen = totalPourcentage/pourcentageRestantChqBorne.length;
        this.quantiteEnergie = QUANTITE_ENERGIE_DEFAUT * bornes.size() * pourcentageMoyen;
        this.quantiteEnergiePourcentage = pourcentageMoyen;
    }

    /**
     * Méthode qui servira à calculer le déchargement de l'énergie au fil du temps
     * @param quantiteChargePerdue la quantité d'énergie perdue (à soustraire)
     */
    //Michael Oliveira-Silva
    public void dechargement(double quantiteChargePerdue) {
        this.quantiteEnergie -= quantiteChargePerdue;
        calculEnergiePourcentage(quantiteEnergie);
        recalculerNiveauEnergie();
        setChargeBorne();
    }

    /**
     * Méthode permettant de calculer l'énergie en pourcentage grâce à la quantité d'énergie en kwH
     * @param quantiteEnergie la quantité d'énergie en kwH
     */
    private void calculEnergiePourcentage(double quantiteEnergie) {
        this.quantiteEnergiePourcentage = (quantiteEnergie/quantiteEnergieTotal)*100;
    }

    /**
     * Méthode permettant de retourner la quantité d'énergie en pourcentage
     * @return la quantité d'énergie en pourcentage
     */
    public double getQuantiteEnergiePourcentage() {
        return quantiteEnergiePourcentage;
    }

    /**
     * Méthode qui permet de modifié la quantité d'énergie grâce à un pourcentage
     * @param quantiteEnergiePourcentage le nouveau pourcentage d'énergie
     */
    public void setQuantiteEnergiePourcentage(double quantiteEnergiePourcentage) {
        this.quantiteEnergiePourcentage = quantiteEnergiePourcentage;
        this.quantiteEnergie = this.quantiteEnergieTotal*(quantiteEnergiePourcentage/100);
    }

    /**
     * Méthode permettant de modifier les charges des bornes de la centrale
     */
    private void setChargeBorne() {
        for (int i = 0; i < bornes.size(); i++) {
            bornes.get(i).setCharge(quantiteEnergiePourcentage);
        }
    }
}
