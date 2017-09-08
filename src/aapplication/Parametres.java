/*
 * Created by JFormDesigner on Wed Feb 08 16:27:37 EST 2017
 */

package aapplication;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

import composantsperso.ComposantValeurs;
import ecouteurs.FenetreListener;
import ecouteurs.ValueListener;
import evenement.Update;
import menu_aide.APropos;
import menu_aide.ConceptsScientifiques;
import menu_aide.GuideDUtil;
import menu_aide.Prealables;
import java.awt.event.ActionListener;

/**
 * @author Gabrielle Lim
 * @author Michael Oliveira-Silva
 * Classe permettant de modifier les paramètres de la simulation.
 */
public class Parametres extends JFrame {
	private static final long serialVersionUID = 1L;
	private String[] priorite= {"R\u00e9partition des ressources", "Satisfaction du client", "Distance de la borne"};
    private String[] nivCharge = {"\u00c9lev\u00e9", "Moyen", "Faible"};
    private Simulation simulation;
    private APropos aPropos;
    private Prealables prealables;
    private ConceptsScientifiques conceptsScientifiques;
    private GuideDUtil guideDUtil;

    /**
     * Constructeur de la classe Parametres
     */
    public Parametres() {
        initComponents();
    }

    /**
     * Change comment la fenêtre sera fermé quand la fenêtre s'ouvre
     * @param e évènements
     */
    //Michael Oliveira-Silva
    private void thisWindowOpened(WindowEvent e) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Détermine la priorité principale de la simulation
     * @param e évènemment
     */
    //Michael Oliveira-Silva
    private void cmboxTopPropertyChange(PropertyChangeEvent e) {
        simulation.setTop(priorite[cmboxTop.getSelectedIndex()], cmboxTop.getSelectedIndex());
    }

    /**
     * Vérifie que les priorités de recherche non pas de doublons
     * @return vrai si elles n'ont pas de doublons, faux dans le cas contraire
     */
    //Michael Oliveira-Silva
    private boolean checkPrio(){
        return (cmboxTop.getSelectedIndex()!=cmboxMid.getSelectedIndex()&&
                cmboxTop.getSelectedIndex()!=cmboxBot.getSelectedIndex()&&
                cmboxMid.getSelectedIndex()!=cmboxBot.getSelectedIndex());
    }

    /**
     * Démarre la simulation
     * @param e évènnement
     */
    //Michael Oliveira-Silva
    private void button2ActionPerformed(ActionEvent e) {
        if (!simulation.isVisible()&&checkPrio()) {
            simulation.setNombreBorneOccup();
            this.setVisible(false);
            simulation.setVisible(true);
        } else{
            JOptionPane.showMessageDialog(null, "Vous ne pouvez pas débuter la simulation si vous avez des doublons"
                                                +"\n dans vos priorités");
        }
    }

    /**
     * Change le deuxième paramètre de priorité de la simulation
     * @param e évènement
     */
    //Michael Oliveira-Silva
    private void cmboxMidPropertyChange(PropertyChangeEvent e) {
        simulation.setMid(priorite[cmboxMid.getSelectedIndex()], cmboxMid.getSelectedIndex());
    }

    /**
     * Change la propriété la moins importante de la simulation
     * @param e évènement
     */
    //Michael Oliveira-Silva
    private void cmboxBotPropertyChange(PropertyChangeEvent e) {
        simulation.setBot(priorite[cmboxBot.getSelectedIndex()], cmboxBot.getSelectedIndex());
    }

    /**
     * Détermine si l'énergie du réseau sera balancée ou non
     * @param e évenement
     */
    //Michael Oliveira-Silva
    private void cmbxBalanceItemStateChanged(ItemEvent e) {
        if(cmbxBalance.getSelectedIndex()==0){
            simulation.setBalance(true);
        }else{
            simulation.setBalance(false);
        }
    }


    /**
     * Méthode permettant d'afficher la fenêtre À Propos lorsque l'on clique sur le menuItem À Propos
     * @param e instance de la classe ActionEvent
     */
    //Gabrielle Lim
    private void aProposActionPerformed(ActionEvent e) {
        if(!aPropos.isVisible()) {
            aPropos.setVisible(true);
        }
    }

    /**
     * Méthode permettant d'afficher la fenêtre Préalables lorsqu'on clique sur le menuItem Préalables
     * @param e instance de la classe Action Event
     */
    //Gabrielle Lim
    private void menuItemPreActionPerformed(ActionEvent e) {
        if(!prealables.isVisible()){
            prealables.setVisible(true);
        }
    }

    /**
     * Méthode permettant d'afficher la fenêtre des Concepts Scientifiques lorsqu'on clique sur le menuItem Concepts Scientifiques
     * @param e instance de la classe Action Event
     */
    //Gabrielle Lim
    private void mitmSciActionPerformed(ActionEvent e) {
        if(!conceptsScientifiques.isVisible()){
            conceptsScientifiques.setVisible(true);
        }
    }

    /**
     * Méthode permettant d'afficher la fenêtre du Guide d'Utilisation lorsqu'on clique sur le menuItem Guide d'Utilisation
     * @param e instance de la classe Action Event
     */
    //Gabrielle Lim
    private void mitmGuideActionPerformed(ActionEvent e) {
        if(!guideDUtil.isVisible()){
            guideDUtil.setVisible(true);
        }
    }

    /**
     * Méthode qui ferme l'application quand le bouton Quitter est cliqué
     * @param e instance de la classe Action Event
     */
    //Gabrielle Lim
    private void btnQuitterActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    /**
     * Méthode qui servira à optimiser les paramètres de la simulation pour obtenir le meilleur résultats
     * @param e instance de la classe Action Event
     */
    //Gabrielle Lim
    private void btmOptimiserActionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Ce bouton change l'ordre de priorit\u00e9 pour lequel nous pensons que le tout est le plus optimis\u00e9. \nL'ordre de priorit\u00e9 \u00e0 \u00e9t\u00e9 chang\u00e9");
        simulation.setTop(priorite[0], 0);
        simulation.setMid(priorite[1], 1);
        simulation.setBot(priorite[2], 2);
        cmboxTop.setSelectedIndex(0);
        cmboxMid.setSelectedIndex(1);
        cmboxBot.setSelectedIndex(2);
    }

    /**
     * initialise les composants de la fenêtre
     */
    private void initComponents() {
        this.simulation = new Simulation();
        this.aPropos = new APropos();
        this.prealables = new Prealables();
        this.conceptsScientifiques = new ConceptsScientifiques();
        this.guideDUtil = new GuideDUtil();
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        this.menuBar1 = new JMenuBar();
        this.menu1 = new JMenu();
        this.mitmInfo = new JMenuItem();
        this.mitmGuide = new JMenuItem();
        this.mitmSci = new JMenuItem();
        this.menuItemPre = new JMenuItem();
        this.panePriorite = new JPanel();
        this.cmboxTop = new JComboBox<>();
        this.cmboxMid = new JComboBox<>();
        this.cmboxBot = new JComboBox<>();
        this.label1 = new JLabel();
        this.label2 = new JLabel();
        this.label3 = new JLabel();
        this.sldrspnVehicules = new JPanel();
        this.compValEV = new ComposantValeurs();
        this.compValBornes = new ComposantValeurs();
        this.compValDemandes = new ComposantValeurs();
        this.panePriorite3 = new JPanel();
        this.cmbxEGlobale = new JComboBox<>();
        cmbxEGlobale.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		simulation.setNivCharge(nivCharge[cmbxEGlobale.getSelectedIndex()]);
        	}
        });
        this.label5 = new JLabel();
        this.cmbxBalance = new JComboBox<>();
        this.label6 = new JLabel();
        this.txtEnergieGlobal = new JTextArea();
        this.txtEnergieEquilibre = new JTextArea();
        this.lblTitre = new JLabel();
        this.btmOptimiser = new JButton();
        this.btnDemarrer = new JButton();
        this.btnQuitter = new JButton();

        //======== this ========
        setTitle("Simulation d'un R\u00e9seau \u00e9lectrique distibu\u00e9 (R.E.D.)");
        setName("this");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                thisWindowOpened(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== menuBar1 ========
        {
            this.menuBar1.setName("menuBar1");

            //======== menu1 ========
            {
                this.menu1.setText("Aide");
                this.menu1.setName("menu1");

                //---- mitmInfo ----
                this.mitmInfo.setText("\u00c0 propos");
                this.mitmInfo.setName("mitmInfo");
                this.mitmInfo.addActionListener(e -> aProposActionPerformed(e));
                this.menu1.add(this.mitmInfo);

                //---- mitmGuide ----
                this.mitmGuide.setText("Guide d'utilisation");
                this.mitmGuide.setName("mitmGuide");
                this.mitmGuide.addActionListener(e -> mitmGuideActionPerformed(e));
                this.menu1.add(this.mitmGuide);

                //---- mitmSci ----
                this.mitmSci.setText("Concepts Scientifiques");
                this.mitmSci.setName("mitmSci");
                this.mitmSci.addActionListener(e -> mitmSciActionPerformed(e));
                this.menu1.add(this.mitmSci);
                this.menu1.addSeparator();

                //---- menuItemPre ----
                this.menuItemPre.setText("Pr\u00e9alables");
                this.menuItemPre.setName("menuItemPre");
                this.menuItemPre.addActionListener(e -> menuItemPreActionPerformed(e));
                this.menu1.add(this.menuItemPre);
            }
            this.menuBar1.add(this.menu1);
        }
        setJMenuBar(this.menuBar1);

        //======== panePriorite ========
        {
            this.panePriorite.setBorder(new TitledBorder("Ordre de priorit\u00e9"));
            this.panePriorite.setName("panePriorite");
            this.panePriorite.setLayout(null);

            //---- cmboxTop ----
            this.cmboxTop.setModel(new DefaultComboBoxModel<>(new String[] {
                "R\u00e9partition des ressources",
                "Satisfaction des clients",
                "Distance avec la borne"
            }));
            this.cmboxTop.setName("cmboxTop");
            this.cmboxTop.addPropertyChangeListener(e -> cmboxTopPropertyChange(e));
            this.panePriorite.add(this.cmboxTop);
            this.cmboxTop.setBounds(50, 65, 230, 30);

            //---- cmboxMid ----
            this.cmboxMid.setModel(new DefaultComboBoxModel<>(new String[] {
                "R\u00e9partition des ressources",
                "Satisfaction des clients",
                "Distance avec la borne"
            }));
            this.cmboxMid.setSelectedIndex(1);
            this.cmboxMid.setName("cmboxMid");
            this.cmboxMid.addPropertyChangeListener(e -> cmboxMidPropertyChange(e));
            this.panePriorite.add(this.cmboxMid);
            this.cmboxMid.setBounds(50, 125, 230, 30);

            //---- cmboxBot ----
            this.cmboxBot.setModel(new DefaultComboBoxModel<>(new String[] {
                "R\u00e9partition des ressources",
                "Satisfaction des clients",
                "Distance avec la borne"
            }));
            this.cmboxBot.setSelectedIndex(2);
            this.cmboxBot.setName("cmboxBot");
            this.cmboxBot.addPropertyChangeListener(e -> cmboxBotPropertyChange(e));
            this.panePriorite.add(this.cmboxBot);
            this.cmboxBot.setBounds(50, 190, 230, 30);

            //---- label1 ----
            this.label1.setText("1.");
            this.label1.setName("label1");
            this.panePriorite.add(this.label1);
            this.label1.setBounds(30, 70, 20, 20);

            //---- label2 ----
            this.label2.setText("2.");
            this.label2.setName("label2");
            this.panePriorite.add(this.label2);
            this.label2.setBounds(30, 130, 20, 20);

            //---- label3 ----
            this.label3.setText("3.");
            this.label3.setName("label3");
            this.panePriorite.add(this.label3);
            this.label3.setBounds(30, 195, 20, 20);
        }
        contentPane.add(this.panePriorite);
        this.panePriorite.setBounds(10, 60, 290, 270);

        //======== sldrspnVehicules ========
        {
            this.sldrspnVehicules.setBorder(new TitledBorder("Param\u00e8tres de la simulation"));
            this.sldrspnVehicules.setName("sldrspnVehicules");
            this.sldrspnVehicules.setLayout(null);

            //---- compValEV ----
            this.compValEV.setMax(100.0);
            this.compValEV.setStep(0.9999999999);
            this.compValEV.setValue(25.0);
            this.compValEV.setBorder(new TitledBorder("% de v\u00e9hicules \u00e9lectriques"));
            this.compValEV.setName("compValEV");
            this.sldrspnVehicules.add(this.compValEV);
            this.compValEV.setBounds(10, 30, this.compValEV.getPreferredSize().width, 70);

            //---- compValBornes ----
            this.compValBornes.setBorder(new TitledBorder("%Bornes de recharge occup\u00e9es"));
            this.compValBornes.setStep(0.9999999);
            this.compValBornes.setMax(100.0);
            this.compValBornes.setValue(30.0);
            this.compValBornes.setName("compValBornes");
            this.sldrspnVehicules.add(this.compValBornes);
            this.compValBornes.setBounds(10, 110, 270, 70);

            //---- compValDemandes ----
            this.compValDemandes.setBorder(new TitledBorder("Fr\u00e9quence de demandes"));
            this.compValDemandes.setMax(100.0);
            this.compValDemandes.setStep(1.0);
            this.compValDemandes.setValue(25.0);
            this.compValDemandes.setName("compValDemandes");
            this.sldrspnVehicules.add(this.compValDemandes);
            this.compValDemandes.setBounds(15, 185, this.compValDemandes.getPreferredSize().width, 70);
        }
        contentPane.add(this.sldrspnVehicules);
        this.sldrspnVehicules.setBounds(305, 60, 290, 270);

        //======== panePriorite3 ========
        {
            this.panePriorite3.setBorder(new TitledBorder("Param\u00e8tres de la simulation"));
            this.panePriorite3.setName("panePriorite3");
            this.panePriorite3.setLayout(null);

            //---- cmbxEGlobale ----
            this.cmbxEGlobale.setActionCommand("cmbxEnergie");
            this.cmbxEGlobale.setModel(new DefaultComboBoxModel<>(new String[] {
                "\u00c9lev\u00e9",
                "Moyen",
                "Faible"
            }));
            this.cmbxEGlobale.setName("cmbxEGlobale");
            this.panePriorite3.add(this.cmbxEGlobale);
            this.cmbxEGlobale.setBounds(10, 40, 265, this.cmbxEGlobale.getPreferredSize().height);

            //---- label5 ----
            this.label5.setText("\u00c9nergie globale du r\u00e9seau");
            this.label5.setName("label5");
            this.panePriorite3.add(this.label5);
            this.label5.setBounds(new Rectangle(new Point(10, 20), this.label5.getPreferredSize()));

            //---- cmbxBalance ----
            this.cmbxBalance.setModel(new DefaultComboBoxModel<>(new String[] {
                "\u00c9quilibr\u00e9",
                "D\u00e9s\u00e9quilibr\u00e9"
            }));
            this.cmbxBalance.setName("cmbxBalance");
            this.cmbxBalance.addItemListener(e -> cmbxBalanceItemStateChanged(e));
            this.panePriorite3.add(this.cmbxBalance);
            this.cmbxBalance.setBounds(10, 142, 260, this.cmbxBalance.getPreferredSize().height);

            //---- label6 ----
            this.label6.setText("R\u00e9partition de l'\u00e9nergie du r\u00e9seau");
            this.label6.setName("label6");
            this.panePriorite3.add(this.label6);
            this.label6.setBounds(new Rectangle(new Point(15, 122), this.label6.getPreferredSize()));

            //---- txtEnergieGlobal ----
            this.txtEnergieGlobal.setText("Correspond au niveau d'\u00e9nergie dans l'ensemble du r\u00e9seau au d\u00e9but de la simulation");
            this.txtEnergieGlobal.setWrapStyleWord(true);
            this.txtEnergieGlobal.setLineWrap(true);
            this.txtEnergieGlobal.setEditable(false);
            this.txtEnergieGlobal.setName("txtEnergieGlobal");
            this.panePriorite3.add(this.txtEnergieGlobal);
            this.txtEnergieGlobal.setBounds(10, 70, 265, 50);

            //---- txtEnergieEquilibre ----
            this.txtEnergieEquilibre.setText("Correspond \u00e0 la distribution de l'\u00e9nergie dans le r\u00e9seau. Par exemple, si une centrale a 100% de l'\u00e9nergie versus une autre a 50% de son \u00e9nergie de disponible.");
            this.txtEnergieEquilibre.setWrapStyleWord(true);
            this.txtEnergieEquilibre.setLineWrap(true);
            this.txtEnergieEquilibre.setEditable(false);
            this.txtEnergieEquilibre.setName("txtEnergieEquilibre");
            this.panePriorite3.add(this.txtEnergieEquilibre);
            this.txtEnergieEquilibre.setBounds(10, 170, 270, 90);
        }
        contentPane.add(this.panePriorite3);
        this.panePriorite3.setBounds(600, 60, 290, 270);

        //---- lblTitre ----
        this.lblTitre.setText("Veuillez d\u00e9finir les param\u00e8tres de la simulation");
        this.lblTitre.setFont(this.lblTitre.getFont().deriveFont(this.lblTitre.getFont().getSize() + 7f));
        this.lblTitre.setName("lblTitre");
        contentPane.add(this.lblTitre);
        this.lblTitre.setBounds(new Rectangle(new Point(225, 15), this.lblTitre.getPreferredSize()));

        //---- btmOptimiser ----
        this.btmOptimiser.setText("Optimiser");
        this.btmOptimiser.setName("btmOptimiser");
        this.btmOptimiser.addActionListener(e -> btmOptimiserActionPerformed(e));
        contentPane.add(this.btmOptimiser);
        this.btmOptimiser.setBounds(195, 330, 250, 40);

        //---- btnDemarrer ----
        this.btnDemarrer.setText("D\u00e9marrer");
        this.btnDemarrer.setName("btnDemarrer");
        this.btnDemarrer.addActionListener(e -> button2ActionPerformed(e));
        contentPane.add(this.btnDemarrer);
        this.btnDemarrer.setBounds(440, 330, 235, 40);

        //---- btnQuitter ----
        this.btnQuitter.setText("Quitter");
        this.btnQuitter.setName("btnQuitter");
        this.btnQuitter.addActionListener(e -> btnQuitterActionPerformed(e));
        contentPane.add(this.btnQuitter);
        this.btnQuitter.setBounds(320, 370, 250, 40);

        contentPane.setPreferredSize(new Dimension(900, 460));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        this.compValEV.setSpinnerSlider();

        //Michael Oliveira-Silva
        this.compValEV.addValueListener(new ValueListener() {
            public void ValueChange() {
                Update.setElectPercentage((int) compValEV.getValue());
            }
        });
        this.compValBornes.setSpinnerSlider();

        //Michael Oliveira-Silva
        this.compValBornes.addValueListener(new ValueListener() {
            public void ValueChange() {
                simulation.setNbBornesOccup((int) (simulation.getNBBORNES()*(compValBornes.getValue()/100)));
            }
        });
        this.compValDemandes.setSpinnerSlider();

        //Michael Oliveira-Silva
        this.compValDemandes.addValueListener(new ValueListener() {
            @Override
            public void ValueChange() {
                Update.setFreqDemande((int) compValDemandes.getValue());
            }
        });

        //Gabrielle Lim
        simulation.addFenetreListener(new FenetreListener() {
            @Override
            public void fenetreClose() {

            }
            @Override
            public void boutonRecommencer() {
                if(!isVisible()){
                    simulation.setVisible(false);
                    setVisible(true);
                    simulation = new Simulation();
                    Update.setCurrNbDem(0);
                }
            }

            @Override
            public void optimiserBot(String priorite, int index) {

            }

            @Override
            public void optimiserTop(String priorite, int index) {

            }

            @Override
            public void optimiserMid(String priorite, int index) {

            }
        });

        Update.setFreqDemande(25);
        Update.setElectPercentage(25);

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem mitmInfo;
    private JMenuItem mitmGuide;
    private JMenuItem mitmSci;
    private JMenuItem menuItemPre;
    private JPanel panePriorite;
    private JComboBox<String> cmboxTop;
    private JComboBox<String> cmboxMid;
    private JComboBox<String> cmboxBot;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JPanel sldrspnVehicules;
    private ComposantValeurs compValEV;
    private ComposantValeurs compValBornes;
    private ComposantValeurs compValDemandes;
    private JPanel panePriorite3;
    private JComboBox<String> cmbxEGlobale;
    private JLabel label5;
    private JComboBox<String> cmbxBalance;
    private JLabel label6;
    private JTextArea txtEnergieGlobal;
    private JTextArea txtEnergieEquilibre;
    private JLabel lblTitre;
    private JButton btmOptimiser;
    private JButton btnDemarrer;
    private JButton btnQuitter;
    //GEN-END:variables
}
