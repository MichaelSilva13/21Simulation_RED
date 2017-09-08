/*
 * Created by JFormDesigner on Wed Feb 08 16:26:30 EST 2017
 */

package aapplication;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;

import bornes.ReseauCentrale;
import dessinable.Images;
import ecouteurs.BorneListener;
import ecouteurs.FenetreListener;
import ecouteurs.UpdateListener;
import evenement.Update;
import graphiques.Graph;
import maps.Map;
import menu_aide.APropos;
import menu_aide.ConceptsScientifiques;
import menu_aide.GuideDUtil;
import menu_aide.Prealables;
import physique.Outils;
import vehicules.VehicElect;

/**
 * @author Gabrielle Lim
 * @author Michael Oliveira-Silva
 * Classe contenant la simulation
 */
public class Simulation extends JFrame implements Runnable{
	private static final long serialVersionUID = 1L;

    /**
     * Constructeur de la classe Simulation
     */
	public Simulation() {
        initComponents();
    }

    private String top = "";
    private String mid = "";
    private String bot = "";
    private final double EPSILON = 0.0000001;
    private boolean balance=true;
    private Thread pBar;
    private int nbVehic = 0;
    private int nbVehicElect = 0;
    private final int NBBORNES = 33;
    private int nbBornesOccup = 9;
    private boolean enCours =false;
    private APropos aPropos;
    private Prealables prealables;
    private GuideDUtil guideDUtil;
    private ConceptsScientifiques conceptsScientifiques;
    private FinSim finSim;
    private OptRed optRed;
    private final EventListenerList OBJETS_ENREGISTRES = new EventListenerList();
    private String nivCharge="Équilibré";
    private int[] priorite = new int[] {0,1,2};

    /**
     * Permet à l'utilisateur de fermer la fenêtre avec le bouton "x"
     * @param e instance de la classe windowEvent
     */
    //Michael Oliveira-Silva
    private void thisWindowOpened(WindowEvent e) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        toFront();
    }

    /**
     * Permet à l'utilisateur de quitter l'application
     * @param e instance de la classe ActionEvent
     */
    //Michael Oliveira-Silva
    private void btnQuitterActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    /**
     * Méthode permettant d'afficher la fenêtre à Propos lorsque l'on clique sur le menuItem À Propos
     * @param e instance de la classe ActionEvent
     */
    //Gabrielle Lim
    private void aProposActionPerformed(ActionEvent e) {
        if(!aPropos.isVisible()){
            aPropos.setVisible(true);
        }
    }

    /**
     * Méthode permettant d'afficher la fenêtre Préalables lorsqu'on clique sur le menuItem Préalables
     * @param e instance de la classe Action Event
     */
    //Gabrielle Lim
    private void menuItemPreActionPerformed(ActionEvent e) {
        if(!prealables.isVisible()) {
            prealables.setVisible(true);
        }
    }

    /**
     * Méthode permettant d'afficher la fenêtre des Concepts Scientifiques lorsqu'on clique sur le menuItem Concepts Scientifiques
     * @param e instance de la classe Action Event
     */
    //Gabrielle Lim
    private void menuItemConceptsSciActionPerformed(ActionEvent e) {
        if(!conceptsScientifiques.isVisible()){
            conceptsScientifiques.setVisible(true);
        }
    }

    /**
     * Méthode permettant d'afficher la fenêtre du Guide d'Utilisation lorsqu'on clique sur le menuItem Guide d'Utilisation
     * @param e instance de la classe Action Event
     */
    //Gabrielle Lim
    private void menuItemGuideUtilActionPerformed(ActionEvent e) {
        if(!guideDUtil.isVisible()) {
            guideDUtil.setVisible(true);
        }
    }

    /**
     * Méthode permettant d'afficher la fenêtre FinSim lorsqu'on clique sur le bouton Arrêter et de proposer les nouvelles possibilités d'actions
     * @param e instance de la classe Action Event
     */
    //Gabrielle Lim
    private void btnArreterActionPerformed(ActionEvent e) {
        if(!finSim.isVisible()) {
            finSim.setVisible(true);
            map1.closeSimulation();
            map1 = new Map();
            enCours = false;
        }
    }

    private void stop() {
        if(!finSim.isVisible()) {
            finSim.setVisible(true);
            map1.closeSimulation();
            map1 = new Map();
            enCours = false;
        }
    }

    /**
     * Méthode qui rajoute l'écouteur à l'objet
     * @param monObjet un fenêtre listener de notre choix
     */
    //Gabrielle Lim
    public void addFenetreListener(FenetreListener monObjet){
        OBJETS_ENREGISTRES.add(FenetreListener.class, monObjet);
    }

    /**
     * Méthode qui lève un évènement lorsque l'écouteur pour l'option de recommencer est appelé
     */
    //Gabrielle Lim
    private void leverEnvenRecommencer(){
        for(FenetreListener ecout : OBJETS_ENREGISTRES.getListeners(FenetreListener.class)){
            ecout.boutonRecommencer();
        }
    }

    /**
     * Méthode qui ouvre la fenêtre Optimiser ou Redémarrer lorsque le bouton du mê;me non est cliqué
     * @param e instance de la classe ActionEvent
     */
    //Gabrielle Lim
    private void btnOptRedActionPerformed(ActionEvent e) {
        if(!optRed.isVisible()){
            optRed.setVisible(true);
            map1.closeSimulation();
            map1 = new Map();
            enCours = false;
        }
    }

    /**
     * Méthode qui permet de démarrer la simulation
     * @param e instance de la classe ActionEvent
     */
    //Michael Oliveira-Silva
    private void btnDemarrerActionPerformed(ActionEvent e) {
        map1.start(true);
        System.out.println("Start");
        this.enCours = true;
        pBar = new Thread(this);
        this.pBar.start();
    }

    /**
     * Méthode qui permet d'ouvrir la fenêtre du Guide d'Utilisation lorsque le menu Item Guide d'Utilisation est sélectionné
     * @param e instance de la classe Action Event
     */
    //Gabrielle Lim
    private void menuItem2ActionPerformed(ActionEvent e) {
        menuItemGuideUtilActionPerformed(e);
    }

    /**
     * Méthode qui permet de modifier la vitesse d'animation de la simulation
     * @param e instance de la classe Action Event
     */
    //Michael Oliveira-Silva
    private void sldrVitStateChanged(ChangeEvent e) {
        map1.changeAnimationSpeed(sldrVit.getValue());
    }

    /**
     * Méthode qui permet de modifier si l'on veut afficher les images des véhicules lors de la simulation
     * @param e instance de la classe Action Event
     */
    //Michael Oliveira-Silva
    private void chckbxIVehicActionPerformed(ActionEvent e) {
        if(chckbxIVehic.isSelected()){
            Update.setCarImage(true);
        }else{
            Update.setCarImage(false);
        }
    }

    /**
     * Méthode qui permet de modifier si l'on veut afficher les images des bornes lors de la simulation
     * @param e instance de la classe Action Event
     */
    //Michael Oliveira-Silva
    private void chckbxIBornesActionPerformed(ActionEvent e) {
        if(chckbxIBornes.isSelected()){
            map1.setImageBorne(true);
        }else{
            map1.setImageBorne(false);
        }
    }

    /**
     * Méthode qui recentre la carte
     * @param e instance de la classe ActionEvent
     */
    //Michael Oliveira-Silva
    private void btnResetPosActionPerformed(ActionEvent e) {
        map1.resetPos();
    }

    /**
     * Initialise les composants
     */
    public void initComponents() {
        this.aPropos = new APropos();
        this.prealables = new Prealables();
        this.conceptsScientifiques = new ConceptsScientifiques();
        this.guideDUtil = new GuideDUtil();
        this.finSim = new FinSim();
        this.optRed = new OptRed();
        Update.setElectPercentage(nbVehicElect);
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItemAPropos = new JMenuItem();
        menuItemGuideUtil = new JMenuItem();
        menuItemConceptsSci = new JMenuItem();
        separator1 = new JSeparator();
        menuItemPre = new JMenuItem();
        panel1 = new JPanel();
        lblPri1 = new JLabel();
        lblPri2 = new JLabel();
        lblPri3 = new JLabel();
        lblTop = new JLabel();
        lblMid = new JLabel();
        lblBot = new JLabel();
        lblTitreNBVehic = new JLabel();
        lblTitreNbVehicElect = new JLabel();
        lblTitreNbBornes = new JLabel();
        lblTitreNbBusy = new JLabel();
        lblNbVehic = new JLabel();
        lblNbVehicElect = new JLabel();
        lblBornes = new JLabel();
        lblBusy = new JLabel();
        panel2 = new JPanel();
        txtaInfo = new JTextArea();
        lblTitre = new JLabel();
        btnQuitter = new JButton();
        tabbedPane2 = new JTabbedPane();
        graphDelai = new Graph();
        graphEnergie = new Graph();
        pbarSim = new JProgressBar();
        btnArreter = new JButton();
        btnOptRed = new JButton();
        btnDemarrer = new JButton();
        map1 = new Map();
        paneInfo = new JPanel();
        lblCentraleA = new JLabel();
        lblInfo1 = new JLabel();
        panel3 = new JPanel();
        lblCentrale2 = new JLabel();
        lblInfo2 = new JLabel();
        panel4 = new JPanel();
        panel5 = new JPanel();
        lblCentraleC = new JLabel();
        lblInfo3 = new JLabel();
        imgVehic = new Images();
        imgEV = new Images();
        label3 = new JLabel();
        label4 = new JLabel();
        paneParam = new JPanel();
        sldrVit = new JSlider();
        chckbxIVehic = new JCheckBox();
        chckbxIBornes = new JCheckBox();
        btnResetPos = new JButton();
        txtInfoImages = new JTextArea();

        //======== this ========
        setAutoRequestFocus(false);
        setTitle("Simulation de r\u00e9seau \u00e9lectrique distribu\u00e9 (R.E.D)");
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

            //======== menu1 ========
            {
                menu1.setText("Aide");

                //---- menuItemAPropos ----
                menuItemAPropos.setText("\u00c0 propos");
                menuItemAPropos.addActionListener(e -> aProposActionPerformed(e));
                menu1.add(menuItemAPropos);

                //---- menuItemGuideUtil ----
                menuItemGuideUtil.setText("Guide d'utilisation");
                menuItemGuideUtil.addActionListener(e -> menuItem2ActionPerformed(e));
                menu1.add(menuItemGuideUtil);

                //---- menuItemConceptsSci ----
                menuItemConceptsSci.setText("Concepts Scientifiques");
                menuItemConceptsSci.addActionListener(e -> menuItemConceptsSciActionPerformed(e));
                menu1.add(menuItemConceptsSci);
                menu1.add(separator1);

                //---- menuItemPre ----
                menuItemPre.setText("Pr\u00e9alables");
                menuItemPre.addActionListener(e -> {
			menuItemPreActionPerformed(e);
			menuItemPreActionPerformed(e);
		});
                menu1.add(menuItemPre);
            }
            menuBar1.add(menu1);
        }
        setJMenuBar(menuBar1);

        //======== panel1 ========
        {
            panel1.setBorder(new TitledBorder("Param\u00e8tres de Simulation"));
            panel1.setLayout(null);

            //---- lblPri1 ----
            lblPri1.setText("1.");
            panel1.add(lblPri1);
            lblPri1.setBounds(new Rectangle(new Point(50, 30), lblPri1.getPreferredSize()));

            //---- lblPri2 ----
            lblPri2.setText("2.");
            panel1.add(lblPri2);
            lblPri2.setBounds(new Rectangle(new Point(50, 55), lblPri2.getPreferredSize()));

            //---- lblPri3 ----
            lblPri3.setText("3.");
            panel1.add(lblPri3);
            lblPri3.setBounds(50, 80, 15, lblPri3.getPreferredSize().height);

            //---- lblTop ----
            lblTop.setText("Top");
            panel1.add(lblTop);
            lblTop.setBounds(70, 30, 210, lblTop.getPreferredSize().height);

            //---- lblMid ----
            lblMid.setText("Milieu");
            panel1.add(lblMid);
            lblMid.setBounds(70, 55, 210, lblMid.getPreferredSize().height);

            //---- lblBot ----
            lblBot.setText("Bas");
            panel1.add(lblBot);
            lblBot.setBounds(70, 80, 210, 15);

            //---- lblTitreNBVehic ----
            lblTitreNBVehic.setText("Nombre de V\u00e9hicules :");
            panel1.add(lblTitreNBVehic);
            lblTitreNBVehic.setBounds(new Rectangle(new Point(280, 30), lblTitreNBVehic.getPreferredSize()));

            //---- lblTitreNbVehicElect ----
            lblTitreNbVehicElect.setText("- Nombre de demandes effecut\u00e9es :");
            panel1.add(lblTitreNbVehicElect);
            lblTitreNbVehicElect.setBounds(new Rectangle(new Point(300, 50), lblTitreNbVehicElect.getPreferredSize()));

            //---- lblTitreNbBornes ----
            lblTitreNbBornes.setText("Nombre de bornes :");
            panel1.add(lblTitreNbBornes);
            lblTitreNbBornes.setBounds(new Rectangle(new Point(280, 70), lblTitreNbBornes.getPreferredSize()));

            //---- lblTitreNbBusy ----
            lblTitreNbBusy.setText("- Bornes occup\u00e9es :");
            panel1.add(lblTitreNbBusy);
            lblTitreNbBusy.setBounds(new Rectangle(new Point(300, 90), lblTitreNbBusy.getPreferredSize()));

            //---- lblNbVehic ----
            lblNbVehic.setText("0");
            panel1.add(lblNbVehic);
            lblNbVehic.setBounds(440, 30, 55, lblNbVehic.getPreferredSize().height);

            //---- lblNbVehicElect ----
            lblNbVehicElect.setText("0");
            panel1.add(lblNbVehicElect);
            lblNbVehicElect.setBounds(530, 50, 50, lblNbVehicElect.getPreferredSize().height);

            //---- lblBornes ----
            lblBornes.setText("10");
            panel1.add(lblBornes);
            lblBornes.setBounds(425, 70, 60, lblBornes.getPreferredSize().height);

            //---- lblBusy ----
            lblBusy.setText("2");
            panel1.add(lblBusy);
            lblBusy.setBounds(435, 90, 45, lblBusy.getPreferredSize().height);
        }
        contentPane.add(panel1);
        panel1.setBounds(10, 575, 595, 140);

        //======== panel2 ========
        {
            panel2.setBorder(new TitledBorder("Information du v\u00e9hicule/ borne"));
            panel2.setLayout(null);

            //---- txtaInfo ----
            txtaInfo.setBorder(new BevelBorder(BevelBorder.LOWERED));
            txtaInfo.setLineWrap(true);
            txtaInfo.setEditable(false);
            txtaInfo.setWrapStyleWord(true);
            txtaInfo.setRows(18);
            panel2.add(txtaInfo);
            txtaInfo.setBounds(5, 15, 815, 190);
        }
        contentPane.add(panel2);
        panel2.setBounds(615, 570, 825, 215);

        //---- lblTitre ----
        lblTitre.setText("Simulation d'un r\u00e9seau de bornes et de v\u00e9hicules \u00e9lectriques");
        lblTitre.setFont(lblTitre.getFont().deriveFont(lblTitre.getFont().getSize() + 8f));
        contentPane.add(lblTitre);
        lblTitre.setBounds(new Rectangle(new Point(420, 5), lblTitre.getPreferredSize()));

        //---- btnQuitter ----
        btnQuitter.setText("Quitter");
        btnQuitter.addActionListener(e -> btnQuitterActionPerformed(e));
        contentPane.add(btnQuitter);
        btnQuitter.setBounds(310, 750, 290, 30);

        //======== tabbedPane2 ========
        {
            tabbedPane2.addTab("D\u00e9lai d'attente", graphDelai);
            tabbedPane2.addTab("\u00c9nergie globale du r\u00e9seau", graphEnergie);
        }
        contentPane.add(tabbedPane2);
        tabbedPane2.setBounds(5, 35, 595, 505);

        //---- pbarSim ----
        pbarSim.setForeground(new Color(68, 186, 28));
        pbarSim.setMaximum(300000);
        contentPane.add(pbarSim);
        pbarSim.setBounds(10, 550, 1425, 15);

        //---- btnArreter ----
        btnArreter.setText("Arr\u00eater");
        btnArreter.addActionListener(e -> btnArreterActionPerformed(e));
        contentPane.add(btnArreter);
        btnArreter.setBounds(15, 750, 290, 30);

        //---- btnOptRed ----
        btnOptRed.setText("Optimiser/Red\u00e9marrer");
        btnOptRed.addActionListener(e -> {
			btnOptRedActionPerformed(e);
			btnOptRedActionPerformed(e);
		});
        contentPane.add(btnOptRed);
        btnOptRed.setBounds(15, 715, 290, 30);

        //---- btnDemarrer ----
        btnDemarrer.setText("D\u00e9marrer");
        btnDemarrer.addActionListener(e -> btnDemarrerActionPerformed(e));
        contentPane.add(btnDemarrer);
        btnDemarrer.setBounds(310, 715, 290, 30);

        //---- map1 ----
        contentPane.add(map1);
        map1.setBounds(615, 55, 820, 490);

        //======== paneInfo ========
        {
            paneInfo.setBorder(new TitledBorder("Info Simulation"));
            paneInfo.setLayout(null);

            //---- lblCentraleA ----
            lblCentraleA.setText("Centrale 1 :");
            paneInfo.add(lblCentraleA);
            lblCentraleA.setBounds(new Rectangle(new Point(8, 40), lblCentraleA.getPreferredSize()));

            //---- lblInfo1 ----
            lblInfo1.setText("100%");
            paneInfo.add(lblInfo1);
            lblInfo1.setBounds(83, 40, 40, lblInfo1.getPreferredSize().height);

            //======== panel3 ========
            {
                panel3.setBackground(new Color(5, 99, 176));
                panel3.setBorder(LineBorder.createBlackLineBorder());
                panel3.setLayout(null);
            }
            paneInfo.add(panel3);
            panel3.setBounds(125, 35, 30, 30);

            //---- lblCentrale2 ----
            lblCentrale2.setText("Centrale 2 : ");
            paneInfo.add(lblCentrale2);
            lblCentrale2.setBounds(new Rectangle(new Point(8, 95), lblCentrale2.getPreferredSize()));

            //---- lblInfo2 ----
            lblInfo2.setText("100%");
            paneInfo.add(lblInfo2);
            lblInfo2.setBounds(new Rectangle(new Point(83, 95), lblInfo2.getPreferredSize()));

            //======== panel4 ========
            {
                panel4.setBackground(Color.white);
                panel4.setBorder(LineBorder.createBlackLineBorder());
                panel4.setLayout(null);
            }
            paneInfo.add(panel4);
            panel4.setBounds(125, 90, 30, 30);

            //======== panel5 ========
            {
                panel5.setBackground(new Color(138, 0, 255));
                panel5.setBorder(LineBorder.createBlackLineBorder());
                panel5.setLayout(null);
            }
            paneInfo.add(panel5);
            panel5.setBounds(125, 145, 30, 30);

            //---- lblCentraleC ----
            lblCentraleC.setText("Centrale 3 :");
            paneInfo.add(lblCentraleC);
            lblCentraleC.setBounds(new Rectangle(new Point(8, 155), lblCentraleC.getPreferredSize()));

            //---- lblInfo3 ----
            lblInfo3.setText("100%");
            paneInfo.add(lblInfo3);
            lblInfo3.setBounds(new Rectangle(new Point(83, 155), lblInfo3.getPreferredSize()));

            //---- imgVehic ----
            imgVehic.setBorder(null);
            paneInfo.add(imgVehic);
            imgVehic.setBounds(10, 215, 140, 75);
            paneInfo.add(imgEV);
            imgEV.setBounds(10, 330, 140, 75);

            //---- label3 ----
            label3.setText("V\u00e9hicule normal");
            paneInfo.add(label3);
            label3.setBounds(new Rectangle(new Point(15, 190), label3.getPreferredSize()));

            //---- label4 ----
            label4.setText("V\u00e9hicule \u00c9lectrique");
            paneInfo.add(label4);
            label4.setBounds(new Rectangle(new Point(15, 300), label4.getPreferredSize()));
        }
        contentPane.add(paneInfo);
        paneInfo.setBounds(1440, 55, 160, 430);

        //======== paneParam ========
        {
            paneParam.setBorder(new TitledBorder("Param\u00e8tres"));
            paneParam.setLayout(null);

            //---- sldrVit ----
            sldrVit.setBorder(new TitledBorder("Vitesse animation"));
            sldrVit.setValue(2);
            sldrVit.setMaximum(10);
            sldrVit.addChangeListener(e -> sldrVitStateChanged(e));
            paneParam.add(sldrVit);
            sldrVit.setBounds(5, 20, 150, 70);

            //---- chckbxIVehic ----
            chckbxIVehic.setText("Images v\u00e9hicules");
            chckbxIVehic.setSelected(true);
            chckbxIVehic.addActionListener(e -> chckbxIVehicActionPerformed(e));
            paneParam.add(chckbxIVehic);
            chckbxIVehic.setBounds(10, 100, 145, chckbxIVehic.getPreferredSize().height);

            //---- chckbxIBornes ----
            chckbxIBornes.setText("Images bornes");
            chckbxIBornes.setSelected(true);
            chckbxIBornes.addActionListener(e -> chckbxIBornesActionPerformed(e));
            paneParam.add(chckbxIBornes);
            chckbxIBornes.setBounds(10, 125, 125, chckbxIBornes.getPreferredSize().height);

            //---- btnResetPos ----
            btnResetPos.setText("Recentrer la carte");
            btnResetPos.addActionListener(e -> btnResetPosActionPerformed(e));
            paneParam.add(btnResetPos);
            btnResetPos.setBounds(5, 225, 152, 55);

            //---- txtInfoImages ----
            txtInfoImages.setLineWrap(true);
            txtInfoImages.setEditable(false);
            txtInfoImages.setText("Enlever les images peut limiter le ralentissement de l'animation");
            txtInfoImages.setWrapStyleWord(true);
            txtInfoImages.setFont(txtInfoImages.getFont().deriveFont(txtInfoImages.getFont().getSize() - 1f));
            paneParam.add(txtInfoImages);
            txtInfoImages.setBounds(10, 150, 145, 70);
        }
        contentPane.add(paneParam);
        paneParam.setBounds(1440, 495, 160, 290);

        contentPane.setPreferredSize(new Dimension(1600, 855));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        lblBornes.setText(NBBORNES+"");
        lblBusy.setText(nbBornesOccup+"");

        //Gabrielle Lim
        finSim.addFenetreListener(new FenetreListener() {
            public void fenetreClose() {
                dispose();
            }
            public void boutonRecommencer() {
                leverEnvenRecommencer();
            }

            @Override
            public void optimiserBot(String priorite, int index) {
            }

            @Override
            public void optimiserMid(String priorite, int index) {
            }

            @Override
            public void optimiserTop(String priorite, int index) {
            }
        });

        //Gabrielle Lim
        optRed.addFenetreListener(new FenetreListener() {
            @Override
            public void fenetreClose() {
                dispose();
            }
            @Override
            public void boutonRecommencer() {
                leverEnvenRecommencer();
            }
            @Override
            public void optimiserBot(String priorite, int index) {
                setBot(priorite, index);
            }

            @Override
            public void optimiserMid(String priorite, int index) {
                setMid(priorite,index);
            }

            @Override
            public void optimiserTop(String priorite, int index) {
                setTop(priorite, index);
            }
        });
        map1.initialise();
        map1.setNivCharge(nivCharge);
        map1.setEquilibre(balance);
        map1.setChargeBornes();
        imgVehic.setImageNom("images/Vehic.png");
        imgEV.setImageNom("images/EV.png");

        if(!this.isVisible()){
            graphDelai.setUniteAxeY(1);
            graphEnergie.setUniteAxeY(0);
        }

        map1.addUpdateListener(new UpdateListener() {
            //Michael Oliveira-Silva
            @Override
            public void carInformationUpdated(Point2D.Double position, double speed) throws IOException {
                if(!(map1.getVehicSelect()==null)) {
                    txtaInfo.setText(map1.selectVehiculeInfo());
                }else {
                    txtaInfo.setText("");
                }
            }

            //Michael Oliveira-Silva
            @Override
            public void carClicked(boolean selected, String carName, boolean isElectric) throws IOException {
                if(!(map1.getVehicSelect()==null)) {
                    if (map1.getBorneSelect() != null)
                        map1.setBorneSelect(null);
                    txtaInfo.setText(map1.selectVehiculeInfo());
                }else
                    txtaInfo.setText("");
            }

            //Gabrielle Lim
            @Override
            public void borneClicked(boolean selected) throws IOException {
                if(!(map1.getBorneSelect() == null)) {
                    if(map1.getVehicSelect()!=null)
                        map1.setVehicSelect(null);
                    txtaInfo.setText(map1.selectBorneInfo());
                } else {
                    txtaInfo.setText("");
                }
            }

            //Gabrielle Lim
            @Override
            public void updateValeursEnergie(double energie, double temps) {
                graphEnergie.updateValeurGraphique(energie, temps);
                finSim.setValeurGraphique(energie, temps);
				lblInfo1.setText((int)Outils.ajusterPrecision(ReseauCentrale.getCentrales().get(0).getQuantiteEnergiePourcentage(), 0) + "%");
				lblInfo2.setText((int)Outils.ajusterPrecision(ReseauCentrale.getCentrales().get(1).getQuantiteEnergiePourcentage(), 0) + "%");
				lblInfo3.setText((int)Outils.ajusterPrecision(ReseauCentrale.getCentrales().get(2).getQuantiteEnergiePourcentage(), 0) + "%");
				if(energie<=EPSILON){
					stop();
				}
            }

            //Gabrielle Lim
            @Override
            public void updateValeursDelai(double delaiMoyen, double tempsSim) {
                graphDelai.updateValeurGraphique(delaiMoyen, tempsSim);
                finSim.setValeurGraphiqueDelai(delaiMoyen, tempsSim);
            }

            //Gabrielle Lim
            @Override
            public void updateValeurBorneOccup(int valeur) {
                lblBusy.setText(valeur + "");
            }
        });

        //Gabrielle Lim
        map1.addBorneListener(new BorneListener() {
            @Override
            public void reservationEffectuer(VehicElect vehicElect) {

            }

            @Override
            public void chargementTerminer() {

            }

            @Override
            public void changementChargeCentrale1(double charge) {
                lblInfo1.setText((int)Outils.ajusterPrecision(charge, 0) + "%");
            }

            @Override
            public void changementChargeCentrale2(double charge) {
                lblInfo2.setText((int)Outils.ajusterPrecision(charge, 0) + "%");
            }

            @Override
            public void changementChargeCentrale3(double charge) {
                lblInfo3.setText((int)Outils.ajusterPrecision(charge, 0) + "%");
                graphEnergie.updateValeurGraphique(map1.getReseauCentrale().getNiveauEnergie(), map1.getTempsSim());
                finSim.setValeurGraphique(map1.getReseauCentrale().getNiveauEnergie(), map1.getTempsSim());
            }
        });
    }

    /**
     * Méthode qui retourne le Top (la priorité numéro 1)
     * @return le Top (la priorité numéro 1)
     */
    //Michael Oliveira-Silva
    public String getTop() {
        return top;

    }

    /**
     * Méthode qui permet de modifier le Top (la priorité numéro 1)
     * @param top le nouveau Top (la nouvelle priorité numéro 1)
     * @param index l'index de la priorité
     */
    //Michael Oliveira-Silva
    public void setTop(String top, int index) {
        this.top = top;
        lblTop.setText(top);
        priorite[0] = index;
        map1.setPrioriteRecherche(priorite);
    }

    /**
     * Méthode qui retourne le Mid (la priorité numéro 2)
     * @return le Mid (la priorité numéro 2)
     */
    //Michael Oliveira-Silva
    public String getMid() {
        return mid;
    }

    /**
     * Méthode qui permet de modifier le Mid (la priorité numéro 2)
     * @param mid le nouveau Mid (la nouvelle priorité numéro 2)
     * @param index l'index de la priorité
     */
    //Michael Oliveira-Silva
    public void setMid(String mid, int index) {
        this.mid = mid;
        lblMid.setText(mid);
        priorite[1] = index;
        map1.setPrioriteRecherche(priorite);
    }

    /**
     * Méthode qui retourne le Bot (la priorité numéro 3)
     * @return le Bot (la priorité numéro 3)
     */
    //Michael Oliveira-Silva
    public String getBot() {
        return bot;
    }

    /**
     * Méthode qui permet de modifier le Bot (la priorité numéro 3)
     * @param bot le nouveau Bot (la nouvelle priorité numéro 3)
     * @param index l'index de la priorité
     */
    //Michael Oliveira-Silva
    public void setBot(String bot, int index) {
        this.bot = bot;
        lblBot.setText(bot);
        priorite[2] = index;
        map1.setPrioriteRecherche(priorite);
    }

    /**
     * Méthode qui retourne le nombre de véhicules
     * @return le nombre de véhicules
     */
    //Michael Oliveira-Silva
    public int getNbVehic() {
        return nbVehic;
    }

    /**
     * Méthode qui permet de modifier le nombre de véhicules
     * @param nbVehic le nouveau nombre de véhicules
     */
    //Michael Oliveira-Silva
    public void setNbVehic(int nbVehic) {
        this.nbVehic = nbVehic;
        lblNbVehic.setText(Update.getCurrNbDem()+"");
    }

    /**
     * Méthode qui retourne le nombre de véhicules électriques
     * @return le nombre de véhicules électriques
     */
    //Michael Oliveira-Silva
    public int getNbVehicElect() {
        return nbVehicElect;
    }

    /**
     * Méthode qui permet de modifier le nombre de véhicules électriques
     * @param nbVehicElect le nouveau nombre de véhicules électriques
     */
    //Michael Oliveira-Silva
    public void setNbVehicElect(int nbVehicElect) {
        this.nbVehicElect = nbVehicElect;
        lblNbVehicElect.setText(nbVehicElect+"");
    }

    /**
     * Méthode qui retourne le nombre de bornes occupées
     * @return le nombre de bornes occupées
     */
    //Michael Oliveira-Silva
    public int getNbBornesOccup() {
        return nbBornesOccup;
    }

    /**
     * Méthode qui permet de modifier le nombre de bornes occupées
     * @param nbBornesOccup le nouveau nombre de bornes occupées
     */
    //Michael Oliveira-Silva
    public void setNbBornesOccup(int nbBornesOccup) {
        this.nbBornesOccup = nbBornesOccup;
        lblBusy.setText(nbBornesOccup+"");
    }

    /**
     * Méthode qui retourne le nombre de bornes
     * @return le nombre de bornes
     */
    //Michael Oliveira-Silva
    public int getNBBORNES() {
        return NBBORNES;
    }

    /**
     * Méthode qui retourne si le réseau est balancé
     * @return si oui ou non le réseau est balancé
     */
    //Michael Oliveira-Silva
    public boolean isBalance() {
        return balance;
    }

    /**
     * Méthode qui permet de modifier si le réseau est balancé ou non
     * @param balance valeur true ou false permettant de modifier le statut du réseau
     */
    //Michael Oliveira-Silva
    public void setBalance(boolean balance) {
        this.balance = balance;
        map1.setEquilibre(balance);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItemAPropos;
    private JMenuItem menuItemGuideUtil;
    private JMenuItem menuItemConceptsSci;
    private JSeparator separator1;
    private JMenuItem menuItemPre;
    private JPanel panel1;
    private JLabel lblPri1;
    private JLabel lblPri2;
    private JLabel lblPri3;
    private JLabel lblTop;
    private JLabel lblMid;
    private JLabel lblBot;
    private JLabel lblTitreNBVehic;
    private JLabel lblTitreNbVehicElect;
    private JLabel lblTitreNbBornes;
    private JLabel lblTitreNbBusy;
    private JLabel lblNbVehic;
    private JLabel lblNbVehicElect;
    private JLabel lblBornes;
    private JLabel lblBusy;
    private JPanel panel2;
    private JTextArea txtaInfo;
    private JLabel lblTitre;
    private JButton btnQuitter;
    private JTabbedPane tabbedPane2;
    private Graph graphDelai;
    private Graph graphEnergie;
    private JProgressBar pbarSim;
    private JButton btnArreter;
    private JButton btnOptRed;
    private JButton btnDemarrer;
    private Map map1;
    private JPanel paneInfo;
    private JLabel lblCentraleA;
    private JLabel lblInfo1;
    private JPanel panel3;
    private JLabel lblCentrale2;
    private JLabel lblInfo2;
    private JPanel panel4;
    private JPanel panel5;
    private JLabel lblCentraleC;
    private JLabel lblInfo3;
    private Images imgVehic;
    private Images imgEV;
    private JLabel label3;
    private JLabel label4;
    private JPanel paneParam;
    private JSlider sldrVit;
    private JCheckBox chckbxIVehic;
    private JCheckBox chckbxIBornes;
    private JButton btnResetPos;
    private JTextArea txtInfoImages;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    /**
     * Méthode qui permet de démarrer la barre de progression et de la modifié au fur et à mesure
     */
    //Michael Oliveira-Silva
    @Override
    public void run(){
        while(enCours&&pbarSim.getValue()!=pbarSim.getMaximum()) {
            pbarSim.setValue(pbarSim.getValue() + 1);
            lblNbVehic.setText(Update.getCurrVehic()+"");
            lblNbVehicElect.setText(Update.getCurrNbDem()+"");
            try {
                Thread.sleep(1);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stop();
    }

    /**
     * Définis le niveau de charge du réseau
     * @param nivCharge le niveau de charge voulu
     */
    //Michael Oliveira-Silva
    public void setNivCharge(String nivCharge) {
        this.nivCharge = nivCharge;
        map1.setNivCharge(nivCharge);
    }

    /**
     * Définis le nombre de bornes qui sont occupées dans la map
     */
    //Gabrielle Lim
    public void setNombreBorneOccup() {
        map1.setNombreBorneOccup(nbBornesOccup);
    }
}
