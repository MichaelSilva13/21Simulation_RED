/*
 * Created by JFormDesigner on Wed Feb 08 16:43:12 EST 2017
 */

package aapplication;

import java.awt.event.*;
import ecouteurs.FenetreListener;

import java.awt.*;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.event.EventListenerList;

/**
 * @author Gabrielle Lim
 * Classe demandant si l'utilisateur souhaite quitter.
 */
public class Quitter extends JFrame {
	private static final long serialVersionUID = 1L;
    private final EventListenerList OBJETS_ENREGISTRES = new EventListenerList();

    /**
     * Constructeur de la classe Quitter
     */
	public Quitter() {
        initComponents();
    }

    /**
     * Méthode qui quitte l'application lorsque le bouton Quitter est cliqué
     * @param e instance de la classe ActionEvent
     */
    private void btnQuitterActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    /**
     * Méthode qui ramène l'utilisateur à la fenêtre Paramètres lorsque le bouton Recommencer est cliqué
     * @param e instance de la classe ActionEvent
     */
    private void btnRedemarrerActionPerformed(ActionEvent e) {
        this.dispose();
        leverEvenFenetreQuitFermee();
        leverEvenRecommencer();

    }

    /**
     * Initialise les composants
     */
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        lblQuitNouv = new JLabel();
        btnRedemarrer = new JButton();
        btnQuitter = new JButton();

        //======== this ========
        setTitle("Quitter ?");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- lblQuitNouv ----
        lblQuitNouv.setText("Voulez-vous quitter ou red\u00e9marrer une nouvelle simulation ?");
        lblQuitNouv.setFont(new Font("AppleGothic", Font.PLAIN, 20));
        contentPane.add(lblQuitNouv);
        lblQuitNouv.setBounds(new Rectangle(new Point(10, 30), lblQuitNouv.getPreferredSize()));

        //---- btnRedemarrer ----
        btnRedemarrer.setText("Red\u00e9marrer");
        btnRedemarrer.setFont(new Font("AppleGothic", Font.PLAIN, 20));
        btnRedemarrer.addActionListener(e -> btnRedemarrerActionPerformed(e));
        contentPane.add(btnRedemarrer);
        btnRedemarrer.setBounds(95, 80, 180, btnRedemarrer.getPreferredSize().height);

        //---- btnQuitter ----
        btnQuitter.setText("Quitter");
        btnQuitter.setFont(new Font("AppleGothic", Font.PLAIN, 20));
        btnQuitter.addActionListener(e -> btnQuitterActionPerformed(e));
        contentPane.add(btnQuitter);
        btnQuitter.setBounds(285, 80, 180, 41);

        contentPane.setPreferredSize(new Dimension(570, 165));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    /**
     * Méthode qui rajoute l'écouteur à l'objet
     * @param monObjet un fenêtre listener de notre choix
     */
    public void addFenetreListener(FenetreListener monObjet){
        OBJETS_ENREGISTRES.add(FenetreListener.class, monObjet);
    }

    /**
     * Méthode qui lève un évènement lorsque l'écouteur, pour savoir lorsque la fenêtre est fermée, est appelé
     */
    private void leverEvenFenetreQuitFermee(){
        for(FenetreListener ecout : OBJETS_ENREGISTRES.getListeners(FenetreListener.class)){
            ecout.fenetreClose();
        }
    }

    /**
     * Méthode qui lève un évènement lorsque l'écouteur pour l'option de recommencer est appelé
     */
    private void leverEvenRecommencer(){
        for(FenetreListener ecout : OBJETS_ENREGISTRES.getListeners(FenetreListener.class)){
            ecout.boutonRecommencer();
        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JLabel lblQuitNouv;
    private JButton btnRedemarrer;
    private JButton btnQuitter;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
