package evenement;

import static graphiques.CalculDelai.setTempsArriveeBorne;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dijkstra.Distances;
import it.polito.appeal.traci.Vehicle;
import maps.Map;
import vehicules.VehicElect;
import vehicules.Vehicule;

/**
 * Met a jour les informations des véhicules et de la simulation.
 * @author Michael Oliveira-Silva
 */
public class Update extends Event implements Runnable{

    private static int stepLength;
    private static double electPercentage = 25;
    private static int currElectVehicles, currVehic;
    private static ArrayList<VehicElect> vehicElects = new ArrayList<VehicElect>();
    private static boolean carImage = true;
    private static int currNbDem = 0;
    private static int freqDemande = 25;
    private static boolean first = true;
    private static Map map;
    private static RechercheBornesPossibles rechercheBornes = new RechercheBornesPossibles();

    /**
     * Définis l'update, sers principalement à initialiser le thread
     * @param time temps de l'update
     */
    public Update(int time) {
        super(time);
        new Thread(this, "update");
    }

    /**
     * Démarre l'évènement de mise à jour et met à jour les positions des
     * voitures
     */
    //Cedryk
    public void run() {
        Collection<Vehicle> sumoVehicles;
        List<Vehicule> vehicles = Map.getVehiculeList();
        try {
            Map.getSumoTraciConnection().nextSimStep();
            sumoVehicles = Map.getSumoTraciConnection().getVehicleRepository().getAll().values();
            updateVehiclesPosition(sumoVehicles, vehicles);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!Map.getVehiculeList().isEmpty()) {
            Map.addEvent(new Update(this.getTime() + stepLength));
        }
    }

    /**
     * Mets à jour les positions des voitures
     *
     * @param sumoVehicles collection de véhicules Sumo
     * @param vehicles     liste de véhicules Scv
     */
    //Cédryk modifiée par Michael
    private void updateVehiclesPosition(Collection<Vehicle> sumoVehicles, List<Vehicule> vehicles) {
        VehicElect electVehicle;
        boolean alreadyPresent;
        for (Vehicle vehicle : sumoVehicles) {
            alreadyPresent = false;
            for (Vehicule vehicule : vehicles) {
                vehicule.setDrawImage(carImage);
                if (vehicle.getID().equals(vehicule.getSumoVehicule().getID())) {
                    alreadyPresent = true;
                    try {
                        vehicule.setPosition((Point2D.Double) vehicle.getPosition());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else if (!sumoVehicles.contains(vehicule.getSumoVehicule())) {
                    vehicule.setDessin(false);
                    vehicule.setSelected(false);
                    //Gabrielle Lim
                    if(vehicule.isElect()) {
                        for (int i = 0; i < vehicElects.size(); i++) {
                            if(vehicElects.get(i).getID() == vehicule.getID()) {
                                if(vehicElects.get(i).isDemandeEffect()) {
                                    double endTime = System.nanoTime();
                                    double duration = (endTime - vehicElects.get(i).getChangeRouteTime());
                                    setTempsArriveeBorne(duration);
                                    vehicElects.get(i).getBorneTarget().setVehicOccuper(vehicElects.get(i));
                                    vehicElects.get(i).getBorneTarget().demarreCharge();
                                    vehicElects.get(i).getBorneTarget().setTempsAtt(duration);
                                    vehicElects.remove(i);
                                }
                            }
                        }
                    }
                    //Fin Gabrielle Lim
                    if (vehicule.equals(map.getVehicSelect()))
                        map.setVehicSelect(null);
                }
            }
            if (!alreadyPresent) {
                try {
                    synchronized (vehicles) {
                        if (nextVehicleIsElect()) {
                            electVehicle = new VehicElect((Point2D.Double) vehicle.getPosition(), vehicle, (int) (Math.random() * 7 + 1));
                            electVehicle.setID(currElectVehicles);
                            if (nextVehicDemande()) {
                                    electVehicle.effectueDemande();
                                    effectueDem(electVehicle);
                                //map.demande(electVehicle);
                                //map.demandeCharge(electVehicle);
                            }
                            electVehicle.setNode(Distances.getVehiculeNode(map.getLiensPositions(), map.getPositionIntersection(), electVehicle));
                            vehicles.add(electVehicle);
                            currElectVehicles++;
                            vehicElects.add(electVehicle);
                        } else {
                            Vehicule vehicule = new Vehicule((Point2D.Double) vehicle.getPosition(), vehicle);
                            try {
                                vehicule.setNode(Distances.getVehiculeNode(map.getLiensPositions(), map.getPositionIntersection(), vehicule));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            vehicles.add(vehicule);
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        currVehic=sumoVehicles.size();
    }

    /**
     * Méthode permettant de déterminer si le prochain véhicule devrait être électrique
     * @return true si le prochain véhicule doit être DSRC et false sinon
     */
    //Michael
    private boolean nextVehicleIsElect() {
        double chance = Math.random() * 100;
        if (chance <= electPercentage) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Détermine au hasard si le prochain véhicule électrique fait une demande
     * @return true si il y a demande, false dans le cas contraire
     */
    //Michael
    private boolean nextVehicDemande() {
        if (Math.random() * 100 <= freqDemande) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Redéfinis la longueur du step de la simulation
     *
     * @param length la longueur du step
     */
    //Cedryk
    public static void setStepLength(int length) {
        stepLength = length;
    }

    /**
     * Redéfinis le pourcentage de véhicules électriques dans la simulation
     *
     * @param electPercentageToChange le pourcentage de véhicules électriques
     */
    //Michael Oliveira-Silva
    public static void setElectPercentage(int electPercentageToChange) {
        electPercentage = electPercentageToChange;
    }

    /**
     * Définis la fréquence à laquelle seront effectuées les demandes
     *
     * @param freqDemande la fréquence de demande
     */
    //Michael Oliveira-Silva
    public static void setFreqDemande(int freqDemande) {
        Update.freqDemande = freqDemande;
    }

    /**
     * Définis si les images des véhicules seront dessinées
     *
     * @param image boolean de si les images seront dessinées
     */
    public static void setCarImage(boolean image) {
        carImage = image;
    }

    /**
     * Détermine la map d'Update
     * @param map la map voulue
     */
    //Michael
    public static void setMap(Map map) {
        Update.map = map;
    }

    /**
     * Effectue la demande de recherche de borne
     * @param electVehicle le véhicule qui fait la demande
     */
    //Michael
    private static void effectueDem(VehicElect electVehicle) {
        if(first) {
            rechercheBornes = new RechercheBornesPossibles();
            rechercheBornes.setEdgesList(map.getLiensPositions());
            rechercheBornes.setNodesBornes(map.getNodesBornes());
            first = false;
        }
        if(rechercheBornes.getBornesArrayList()!=RechercheBornesPossibles.getBornesOccupe()) {
            try {
                rechercheBornes.setNodeCar(
                        Distances.getVehiculeNode(map.getLiensPositions(), map.getPositionIntersection(),
                                                  electVehicle));
            } catch (IOException e) {
                e.printStackTrace();
            }
            rechercheBornes.setVehicElect(electVehicle);
            rechercheBornes.setMap(map);
            rechercheBornes.demarrer();
            currNbDem++;
        }
    }

    /**
     * Retourne le nombre de véhicules électriques du réseau.
     * @return le nombre de véhicules électriques
     */
    //Michael
    public static int getCurrElectVehicles() {
        return currElectVehicles;
    }

    /**
     * Retourne le nombre de véhicules de la simulation
     * @return le nombre de véhicules
     */
    //Michael Oliveira-Silva
    public static int getCurrVehic() {
        return currVehic;
    }

    /**
     * Retourne le nombre de demandes effectuées
     * @return le nombre de demandes effectuées
     */
    public static int getCurrNbDem() {
        return currNbDem;
    }

    /**
     * Méthode permettant de modifié le nombre de demandes effectuées
     * @param currNbDem le nombre de demandes effectuées
     */
    public static void setCurrNbDem(int currNbDem) {
        Update.currNbDem = currNbDem;
    }
}


