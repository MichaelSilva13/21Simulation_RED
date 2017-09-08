/*
 * Created by JFormDesigner on Wed Feb 08 16:33:54 EST 2017
 */

package aapplication;

import ecouteurs.FenetreListener;

import java.awt.*;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.event.EventListenerList;

/**
 * @author Gabrielle Lim
 * Classe permettant à l'utilisateur de choisir la suite des évènements.
 */
public class FinSim extends JFrame {
	private static final long serialVersionUID = 1L;
    private Resultats resultats;
    private final EventListenerList OBJETS_ENREGISTRES = new EventListenerList();

    /**
     * Constructeur de la classe FinSim
     */
	public FinSim() {
        initComponents();
    }

    /**
     * Méthode qui ramène l'utilisateur à la fenêtre Paramètres lorsque le bouton Recommencer est cliqué
     * @param e instance de la classe ActionEvent
     */
    private void btnRecomActionPerformed(ActionEvent e) {
        this.dispose();
        leverEvenFenetreFermee();
        leverEnvenRecommencer();
    }

    /**
     * Méthode qui ouvre la fenêtre des résultats lorsque le bouton Voir les Résultats est cliqué
     * @param e instance de la classe ActionEvent
     */
    private void btnResultActionPerformed(ActionEvent e) {
        if(!resultats.isVisible()){
            resultats.setVisible(true);
            this.dispose();
            leverEvenFenetreFermee();
        }
    }

    /**
     * Méthode qui ferme l'application lorsque le bouton terminé est cliqué
     * @param e instance de la classe ActionEvent
     */
    private void btnTermActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    /**
     * Méthode qui rajoute l'écouteur à l'objet
     * @param monObjet un fenêtre listener de notre choix
     */
    public void addFenetreListener(FenetreListener monObjet){
        OBJETS_ENREGISTRES.add(FenetreListener.class, monObjet);
    }

    /**
     * Méthode qui lève un évènement lorsque l'écouteur pour savoir lorsque la fenêtre est fermée est appelé
     */
    private void leverEvenFenetreFermee(){
        for(FenetreListener ecout : OBJETS_ENREGISTRES.getListeners(FenetreListener.class)){
            ecout.fenetreClose();
        }
    }

    /**
     * Méthode qui lève un évènement lorsque l'écouteur pour l'option de recommencer est appelé
     */
    private void leverEnvenRecommencer(){
        for(FenetreListener ecout : OBJETS_ENREGISTRES.getListeners(FenetreListener.class)){
            ecout.boutonRecommencer();
        }
    }

    /**
     * Initialise les composants
     */
    private void initComponents() {
        resultats = new Resultats();
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        lblSimTerm = new JLabel();
        btnRecom = new JButton();
        btnTerm = new JButton();
        btnResult = new JButton();

        //======== this ========
        setTitle("Simulation Termin\u00e9e");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- lblSimTerm ----
        lblSimTerm.setText("Simulation Termin\u00e9e");
        lblSimTerm.setFont(new Font("AppleGothic", Font.PLAIN, 30));
        contentPane.add(lblSimTerm);
        lblSimTerm.setBounds(new Rectangle(new Point(150, 15), lblSimTerm.getPreferredSize()));

        //---- btnRecom ----
        btnRecom.setText("Recommencer");
        btnRecom.setFont(new Font("AppleGothic", Font.PLAIN, 18));
        btnRecom.addActionListener(e -> btnRecomActionPerformed(e));
        contentPane.add(btnRecom);
        btnRecom.setBounds(5, 65, 190, btnRecom.getPreferredSize().height);

        //---- btnTerm ----
        btnTerm.setText("Terminer");
        btnTerm.setFont(new Font("AppleGothic", Font.PLAIN, 18));
        btnTerm.addActionListener(e -> btnTermActionPerformed(e));
        contentPane.add(btnTerm);
        btnTerm.setBounds(195, 65, 190, 38);

        //---- btnResult ----
        btnResult.setText("Voir les R\u00e9sultats");
        btnResult.setFont(new Font("AppleGothic", Font.PLAIN, 18));
        btnResult.addActionListener(e -> btnResultActionPerformed(e));
        contentPane.add(btnResult);
        btnResult.setBounds(385, 65, 190, 38);

        contentPane.setPreferredSize(new Dimension(585, 140));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        resultats.addFenetreListener(new FenetreListener() {
            @Override
            public void fenetreClose() {

            }
            @Override
            public void boutonRecommencer() {
                leverEnvenRecommencer();
            }

            @Override
            public void optimiserTop(String priorite, int index) {

            }

            @Override
            public void optimiserMid(String priorite, int index) {

            }

            @Override
            public void optimiserBot(String priorite, int index) {

            }
        });
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JLabel lblSimTerm;
    private JButton btnRecom;
    private JButton btnTerm;
    private JButton btnResult;

    public void setValeurGraphique(double niveauEnergie, double tempsSim) {
        resultats.setValeurGraphique(niveauEnergie, tempsSim);
    }

    public void setValeurGraphiqueDelai(double delaiMoyen, double tempsSim){
        resultats.setValeurGraphiqueDelai(delaiMoyen, tempsSim);
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
