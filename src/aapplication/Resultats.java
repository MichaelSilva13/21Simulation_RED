/*
 * Created by JFormDesigner on Wed Feb 08 16:32:03 EST 2017
 */

package aapplication;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;
import javax.swing.event.EventListenerList;

import ecouteurs.FenetreListener;
import graphiques.Diagramme;
import graphiques.Graph;
import menu_aide.APropos;
import menu_aide.ConceptsScientifiques;
import menu_aide.GuideDUtil;

/**
 * @author Michael Oliveira-Silva
 * Classe affichant les résultats de la simulation.
 */
public class Resultats extends JFrame {
	private static final long serialVersionUID = 1L;
	private APropos aPropos;
    private ConceptsScientifiques conceptsScientifiques;
    private GuideDUtil guideDUtil;
    private Quitter quitter;
    private final EventListenerList OBJETS_ENREGISTRES = new EventListenerList();

    /**
     * Constructeur de la classe Resultats
     */
    public Resultats() {
        initComponents();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    /**
     * Méthode permettant d'afficher la fenêtre À Propos lorsqu'on clique sur le menuItem À Propos
     * @param e instance de la classe ActionEvent
     */
    //Gabrielle Lim
    private void menuItemAProposActionPerformed(ActionEvent e) {
        if(!aPropos.isVisible()) {
            aPropos.setVisible(true);
        }
    }

    /**
     * Méthode permettant d'afficher la fenêtre des Concepts Scientifiques lorsqu'on clique sur le menuItem Concepts Scientifiques
     * @param e instance de la classe ActionEvent
     */
    //Gabrielle Lim
    private void menuItemConceptsScActionPerformed(ActionEvent e) {
        if(!conceptsScientifiques.isVisible()){
            conceptsScientifiques.setVisible(true);
        }
    }

    /**
     * Méthode permettant d'afficher la fenêtre du Guide d'Utilisation lorsqu'on clique sur le menuItem Guide d'Utilisation
     * @param e instance de la classe ActionEvent
     */
    //Gabrielle Lim
    private void menuItemGuideUtilActionPerformed(ActionEvent e) {
        if(!guideDUtil.isVisible()){
            guideDUtil.setVisible(true);
        }
    }

    /**
     * Méthode qui ferme l'application lorsque le bouton terminé est cliqué
     * @param e instance de la classe ActionEvent
     */
    //Gabrielle Lim
    private void btnQuitterActionPerformed(ActionEvent e) {
        if(!quitter.isVisible()){
            quitter.setVisible(true);
        }
    }

    /**
     * Initialise les les composants
     */
    private void initComponents() {
        this.aPropos = new APropos();
        this.conceptsScientifiques = new ConceptsScientifiques();
        this.guideDUtil = new GuideDUtil();
        this.quitter = new Quitter();
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        menuBar = new JMenuBar();
        menuAide = new JMenu();
        menuItemAPropos = new JMenuItem();
        menuItemGuideUtil = new JMenuItem();
        menuItemConceptsSc = new JMenuItem();
        diagramUtilBor = new Diagramme();
        lblResultats = new JLabel();
        lblUtilBornes = new JLabel();
        graphDelai = new Graph();
        graphEnergie = new Graph();
        lblDelaiAttente = new JLabel();
        lblEnergie = new JLabel();
        btnQuitter = new JButton();

        //======== this ========
        setTitle("R\u00e9sultats");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== menuBar ========
        {

            //======== menuAide ========
            {
                menuAide.setText("Aide");

                //---- menuItemAPropos ----
                menuItemAPropos.setText("\u00c0 Propos");
                menuItemAPropos.addActionListener(e -> menuItemAProposActionPerformed(e));
                menuAide.add(menuItemAPropos);

                //---- menuItemGuideUtil ----
                menuItemGuideUtil.setText("Guide d'Utilisation");
                menuItemGuideUtil.addActionListener(e -> menuItemGuideUtilActionPerformed(e));
                menuAide.add(menuItemGuideUtil);

                //---- menuItemConceptsSc ----
                menuItemConceptsSc.setText("Concepts Scientifiques");
                menuItemConceptsSc.addActionListener(e -> {
			menuItemConceptsScActionPerformed(e);
			menuItemConceptsScActionPerformed(e);
		});
                menuAide.add(menuItemConceptsSc);
            }
            menuBar.add(menuAide);
        }
        setJMenuBar(menuBar);

        //---- diagramUtilBor ----
        diagramUtilBor.setBorder(null);
        diagramUtilBor.setBackground(Color.gray);
        contentPane.add(diagramUtilBor);
        diagramUtilBor.setBounds(20, 70, 990, 285);

        //---- lblResultats ----
        lblResultats.setText("R\u00e9sultats");
        lblResultats.setFont(new Font("AppleGothic", Font.PLAIN, 30));
        contentPane.add(lblResultats);
        lblResultats.setBounds(new Rectangle(new Point(450, 10), lblResultats.getPreferredSize()));

        //---- lblUtilBornes ----
        lblUtilBornes.setText("Utilisation des bornes (en pourcentage)");
        lblUtilBornes.setFont(new Font("AppleGothic", Font.PLAIN, 20));
        contentPane.add(lblUtilBornes);
        lblUtilBornes.setBounds(new Rectangle(new Point(20, 40), lblUtilBornes.getPreferredSize()));
        contentPane.add(graphDelai);
        graphDelai.setBounds(20, 395, 480, 360);
        contentPane.add(graphEnergie);
        graphEnergie.setBounds(530, 395, 480, 360);

        //---- lblDelaiAttente ----
        lblDelaiAttente.setText("D\u00e9lai d'attente ");
        lblDelaiAttente.setFont(new Font("AppleGothic", Font.PLAIN, 20));
        contentPane.add(lblDelaiAttente);
        lblDelaiAttente.setBounds(new Rectangle(new Point(20, 365), lblDelaiAttente.getPreferredSize()));

        //---- lblEnergie ----
        lblEnergie.setText("\u00c9nergie Globale du R\u00e9seau");
        lblEnergie.setFont(new Font("AppleGothic", Font.PLAIN, 20));
        contentPane.add(lblEnergie);
        lblEnergie.setBounds(new Rectangle(new Point(530, 365), lblEnergie.getPreferredSize()));

        //---- btnQuitter ----
        btnQuitter.setText("Quitter");
        btnQuitter.setFont(new Font("AppleGothic", Font.PLAIN, 20));
        btnQuitter.addActionListener(e -> btnQuitterActionPerformed(e));
        contentPane.add(btnQuitter);
        btnQuitter.setBounds(420, 760, 190, btnQuitter.getPreferredSize().height);

        contentPane.setPreferredSize(new Dimension(1035, 855));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        //Gabrielle Lim
        quitter.addFenetreListener(new FenetreListener() {
            @Override
            public void fenetreClose() {
                dispose();
            }
            @Override
            public void boutonRecommencer() {
                leverEnvenRecommencer();
            }

            @Override
            public void optimiserMid(String priorite, int index) {

            }

            @Override
            public void optimiserBot(String priorite, int index) {

            }

            @Override
            public void optimiserTop(String priorite, int index) {

            }
        });
        graphDelai.setUniteAxeY(1);
        graphEnergie.setUniteAxeY(0);
    }

    //Gabrielle Lim
    /**
     * Méthode qui rajoute l'écouteur à l'objet
     * @param monObjet un fenêtre listener de notre choix
     */
    public void addFenetreListener(FenetreListener monObjet){
        OBJETS_ENREGISTRES.add(FenetreListener.class, monObjet);
    }

    //Gabrielle Lim
    /**
     * Méthode qui lève un évènement lorsque l'écouteur pour l'option de recommencer est appelé
     */
    private void leverEnvenRecommencer(){
        for(FenetreListener ecout : OBJETS_ENREGISTRES.getListeners(FenetreListener.class)){
            ecout.boutonRecommencer();
        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JMenuBar menuBar;
    private JMenu menuAide;
    private JMenuItem menuItemAPropos;
    private JMenuItem menuItemGuideUtil;
    private JMenuItem menuItemConceptsSc;
    private static Diagramme diagramUtilBor;
    private JLabel lblResultats;
    private JLabel lblUtilBornes;
    private Graph graphDelai;
    private Graph graphEnergie;
    private JLabel lblDelaiAttente;
    private JLabel lblEnergie;
    private JButton btnQuitter;

    /**
     * Méthode qui instancie les valeurs du graphique de l'énergie
     * @param niveauEnergie le niveau d'énergie du réseau
     * @param tempsSim le temps de la simulation
     */
    //Gabrielle Lim
    public void setValeurGraphique(double niveauEnergie, double tempsSim) {
        graphEnergie.updateValeurGraphique(niveauEnergie, tempsSim);
    }

    /**
     * Méthode qui instancie les valeurs de l'histogramme (diagramme à bandes) de l'utilisation des bornes
     * @param utilisationBorne le numéro (index) de la centrale utilisée
     */
    //Gabrielle Lim
    public static void setUtilisationBorne(double utilisationBorne) {
        diagramUtilBor.updateValeursUtilisation(utilisationBorne);
    }

    /**
     * Méthode qui instancie les valeurs du graphique du délai d'attente
     * @param delaiMoyen le délai d'attente moyen
     * @param tempsSim le temps de la simulation
     */
    //Gabrielle Lim
    public void setValeurGraphiqueDelai(double delaiMoyen, double tempsSim) {
        graphDelai.updateValeurGraphique(delaiMoyen, tempsSim);
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
