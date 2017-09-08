/*
 * Created by JFormDesigner on Wed Feb 08 16:41:42 EST 2017
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
 * Classe offrant l'option de redémarrer ou d'optimiser.
 */
public class OptRed extends JFrame {
	private static final long serialVersionUID = 6127884465445770768L;
    private final EventListenerList OBJETS_ENREGISTRES = new EventListenerList();
    private String[] priorite= {"R\u00e9partition des ressources", "Satisfaction du client", "Distance de la borne"};

    /**
     * Constructeur de la classe OptRed
     */
	public OptRed() {
        initComponents();
    }

    /**
     * Méthode qui redémarre une nouvelle simulation à la fenêtre paramètres lorsque le bouton redémarrer est cliqué
     * @param e instance de la classe ActionEvent
     */
    private void btnRedemarrerActionPerformed(ActionEvent e) {
        this.dispose();
        leverEvenFenetreFermee();
        leverEnvenRecommencer();
    }

    /**
     * Redéfinis l'ordre des priorités de sélection de borne
     * @param e instance de la classe ActionEvent
     */
    private void btnOptimiserActionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Ce bouton change l'ordre de priorit\u00e9 pour lequel nous pensons que le tout est le plus optimis\u00e9. \nL'ordre de priorit\u00e9 \u00e0 \u00e9t\u00e9 chang\u00e9");
        leverEnvenOptTop(0);
        leverEnvenOptMid(1);
        leverEnvenOptBot(2);
    }

    /**
     * Initialise les composants
     */
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        this.lblOptRed = new JLabel();
        this.lblNouvSim = new JLabel();
        this.btnOptimiser = new JButton();
        this.btnRedemarrer = new JButton();

        //======== this ========
        setTitle("Optimiser ou Red\u00e9marrer");
        setName("this");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- lblOptRed ----
        this.lblOptRed.setText("Souhaitez-vous optimiser la simulation ou red\u00e9marrer ");
        this.lblOptRed.setHorizontalAlignment(SwingConstants.CENTER);
        this.lblOptRed.setFont(new Font("AppleGothic", Font.PLAIN, 20));
        this.lblOptRed.setName("lblOptRed");
        contentPane.add(this.lblOptRed);
        this.lblOptRed.setBounds(30, 10, 540, 45);

        //---- lblNouvSim ----
        this.lblNouvSim.setText("une nouvelle simulation ?");
        this.lblNouvSim.setFont(new Font("AppleGothic", Font.PLAIN, 20));
        this.lblNouvSim.setName("lblNouvSim");
        contentPane.add(this.lblNouvSim);
        this.lblNouvSim.setBounds(new Rectangle(new Point(185, 50), this.lblNouvSim.getPreferredSize()));

        //---- btnOptimiser ----
        this.btnOptimiser.setText("Optimiser");
        this.btnOptimiser.setFont(new Font("AppleGothic", Font.PLAIN, 20));
        this.btnOptimiser.setName("btnOptimiser");
        this.btnOptimiser.addActionListener(e -> btnOptimiserActionPerformed(e));
        contentPane.add(this.btnOptimiser);
        this.btnOptimiser.setBounds(90, 85, 185, this.btnOptimiser.getPreferredSize().height);

        //---- btnRedemarrer ----
        this.btnRedemarrer.setText("Red\u00e9marrer");
        this.btnRedemarrer.setFont(new Font("AppleGothic", Font.PLAIN, 20));
        this.btnRedemarrer.setName("btnRedemarrer");
        this.btnRedemarrer.addActionListener(e -> {
			btnRedemarrerActionPerformed(e);
			btnRedemarrerActionPerformed(e);
		});
        contentPane.add(this.btnRedemarrer);
        this.btnRedemarrer.setBounds(295, 85, 185, 41);

        contentPane.setPreferredSize(new Dimension(585, 165));
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
     * Méthode qui làve unévènement lorsque l'écouteur pour savoir lorsque la fenêtre est fermée est appelé
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
     * Méthode qui lève un évènement lorsque le premier critère de recherche de borne est changé
     * @param index l'index du nouveau critère
     */
    private void leverEnvenOptTop(int index) {
        for(FenetreListener ecout : OBJETS_ENREGISTRES.getListeners(FenetreListener.class)){
            ecout.optimiserTop(priorite[index], index);
        }
    }

    /**
     * Méthode qui lève un évènement lorsque le deuxième critère de recherche de borne est changé
     * @param index l'index du nouveau critère
     */
    private void leverEnvenOptMid(int index) {
        for(FenetreListener ecout : OBJETS_ENREGISTRES.getListeners(FenetreListener.class)){
            ecout.optimiserMid(priorite[index], index);
        }
    }

    /**
     * Méthode qui lève un évènement lorsque le troisième critère de recherche de borne est changé
     * @param index l'index du nouveau critère
     */
    private void leverEnvenOptBot(int index) {
        for(FenetreListener ecout : OBJETS_ENREGISTRES.getListeners(FenetreListener.class)){
            ecout.optimiserBot(priorite[index], index);
        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JLabel lblOptRed;
    private JLabel lblNouvSim;
    private JButton btnOptimiser;
    private JButton btnRedemarrer;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
