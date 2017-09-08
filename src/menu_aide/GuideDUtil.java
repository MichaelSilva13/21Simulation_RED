/*
 * Created by JFormDesigner on Wed Feb 08 16:36:58 EST 2017
 */

package menu_aide;

import java.awt.*;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeEvent;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.border.TitledBorder;
import composantsperso.ComposantValeurs;
import dessinable.Images;

/**
 * @author Gabrielle Lim (conception de la fenêtre et des liens)
 * @author Michael Oliveira-Silva (texte et image)
 * Guide d'utilisation de l'application.
 */
public class GuideDUtil extends JFrame {
	private static final long serialVersionUID = 1L;
	/**
     * Constructeur de la fenêtre du Guide d'Utilise
     */
    public GuideDUtil() {
        initComponents();
    }

    private void cmboxTopPropertyChange(PropertyChangeEvent e) {
        // TODO add your code here
    }

    private void cmboxMidPropertyChange(PropertyChangeEvent e) {
        // TODO add your code here
    }

    private void cmboxBotPropertyChange(PropertyChangeEvent e) {
        // TODO add your code here
    }

    private void cmbxBalanceItemStateChanged(ItemEvent e) {
        // TODO add your code here
    }

    private void cmbxEGlobalePropertyChange(PropertyChangeEvent e) {
        // TODO add your code here
    }

    /**
     * Initialise les composants
     */
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        this.lblGuideUtil = new JLabel();
        this.tbGuideUtil = new JTabbedPane();
        this.panel1 = new JPanel();
        this.scrollPane1 = new JScrollPane();
        this.txtPaneOrdrePrio = new JTextPane();
        this.scrollPane2 = new JScrollPane();
        this.textPane1 = new JTextPane();
        this.scrollPane3 = new JScrollPane();
        this.textPane2 = new JTextPane();
        this.compValEV = new ComposantValeurs();
        this.compValBornes = new ComposantValeurs();
        this.compValDemandes = new ComposantValeurs();
        this.panePriorite = new JPanel();
        this.cmboxTop = new JComboBox<>();
        this.cmboxMid = new JComboBox<>();
        this.cmboxBot = new JComboBox<>();
        this.label1 = new JLabel();
        this.label2 = new JLabel();
        this.label3 = new JLabel();
        this.cmbxBalance = new JComboBox<>();
        this.label6 = new JLabel();
        this.cmbxEGlobale = new JComboBox<>();
        this.label5 = new JLabel();
        this.panel3 = new JPanel();
        this.button1 = new JButton();
        this.scrollPane4 = new JScrollPane();
        this.textPane3 = new JTextPane();
        this.scrollPane5 = new JScrollPane();
        this.textPane4 = new JTextPane();
        this.button2 = new JButton();
        this.scrollPane6 = new JScrollPane();
        this.textPane5 = new JTextPane();
        this.button3 = new JButton();
        this.scrollPane7 = new JScrollPane();
        this.textPane6 = new JTextPane();
        this.scrollPane8 = new JScrollPane();
        this.textPane7 = new JTextPane();
        this.button4 = new JButton();
        this.panel2 = new JPanel();
        this.scrollPane9 = new JScrollPane();
        this.textPane8 = new JTextPane();
        this.scrollPane10 = new JScrollPane();
        this.textPane9 = new JTextPane();
        this.scrollPane11 = new JScrollPane();
        this.textPane10 = new JTextPane();
        this.images4 = new Images();
        this.images5 = new Images();
        this.images6 = new Images();

        //======== this ========
        setTitle("Guide d'Utilisation");
        setName("this");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- lblGuideUtil ----
        this.lblGuideUtil.setText("Guide d'Utilisation");
        this.lblGuideUtil.setFont(new Font("AppleGothic", Font.PLAIN, 30));
        this.lblGuideUtil.setName("lblGuideUtil");
        contentPane.add(this.lblGuideUtil);
        this.lblGuideUtil.setBounds(new Rectangle(new Point(350, 10), this.lblGuideUtil.getPreferredSize()));

        //======== tbGuideUtil ========
        {
            this.tbGuideUtil.setName("tbGuideUtil");

            //======== panel1 ========
            {
                this.panel1.setName("panel1");
                this.panel1.setLayout(null);

                //======== scrollPane1 ========
                {
                    this.scrollPane1.setName("scrollPane1");

                    //---- txtPaneOrdrePrio ----
                    this.txtPaneOrdrePrio.setText("Dans la fen\u00eatre des param\u00e8tres, ces trois listes d\u00e9roulantes servent \u00e0 d\u00e9finir l'ordre de priorit\u00e9 de l'algorithme de s\u00e9lection de borne. Vous ne pourrez pas d\u00e9marrer l'application si un param\u00e8tre est choisit \u00e0 deux positions diff\u00e9rentes");
                    this.txtPaneOrdrePrio.setEditable(false);
                    this.txtPaneOrdrePrio.setBorder(null);
                    this.txtPaneOrdrePrio.setName("txtPaneOrdrePrio");
                    this.scrollPane1.setViewportView(this.txtPaneOrdrePrio);
                }
                this.panel1.add(this.scrollPane1);
                this.scrollPane1.setBounds(85, 25, 420, 90);

                //======== scrollPane2 ========
                {
                    this.scrollPane2.setName("scrollPane2");

                    //---- textPane1 ----
                    this.textPane1.setText("Les curseurs suivants servent \u00e0 d\u00e9terminer le nombre de v\u00e9hicules \u00e9lectriques dans la simulation, le pourcentage de v\u00e9hicules qui feront des demandes et le pourcentage de bornes occup\u00e9es dans le r\u00e9seau. ");
                    this.textPane1.setEditable(false);
                    this.textPane1.setName("textPane1");
                    this.scrollPane2.setViewportView(this.textPane1);
                }
                this.panel1.add(this.scrollPane2);
                this.scrollPane2.setBounds(200, 460, 415, 80);

                //======== scrollPane3 ========
                {
                    this.scrollPane3.setName("scrollPane3");

                    //---- textPane2 ----
                    this.textPane2.setText("Les derniers param\u00e8tres, d\u00e9termin\u00e9s par des listes d\u00e9roulantes influent sur le r\u00e9seau de bornes. Vous pouvez modifier si le r\u00e9seau aura un niveau d'\u00e9nergie \u00e9lev\u00e9, moyen ou faible. Puis, vous pourrez d\u00e9finir si l'\u00e9nergie du r\u00e9seau sera r\u00e9partie \u00e0 travers les centrales de mani\u00e8re \u00e9gale ou non.");
                    this.textPane2.setEditable(false);
                    this.textPane2.setName("textPane2");
                    this.scrollPane3.setViewportView(this.textPane2);
                }
                this.panel1.add(this.scrollPane3);
                this.scrollPane3.setBounds(15, 175, 410, 95);

                //---- compValEV ----
                this.compValEV.setMax(100.0);
                this.compValEV.setStep(0.9999999999);
                this.compValEV.setValue(25.0);
                this.compValEV.setBorder(new TitledBorder("% de v\u00e9hicules \u00e9lectriques"));
                this.compValEV.setName("compValEV");
                this.panel1.add(this.compValEV);
                this.compValEV.setBounds(625, 335, 270, 70);

                //---- compValBornes ----
                this.compValBornes.setBorder(new TitledBorder("%Bornes de recharge occup\u00e9es"));
                this.compValBornes.setStep(0.9999999);
                this.compValBornes.setMax(100.0);
                this.compValBornes.setValue(30.0);
                this.compValBornes.setName("compValBornes");
                this.panel1.add(this.compValBornes);
                this.compValBornes.setBounds(625, 415, 270, 70);

                //---- compValDemandes ----
                this.compValDemandes.setBorder(new TitledBorder("Fr\u00e9quence de demandes"));
                this.compValDemandes.setMax(100.0);
                this.compValDemandes.setStep(1.0);
                this.compValDemandes.setValue(25.0);
                this.compValDemandes.setName("compValDemandes");
                this.panel1.add(this.compValDemandes);
                this.compValDemandes.setBounds(625, 490, 270, 70);

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
                    this.cmboxTop.setBounds(30, 50, 230, 30);

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
                    this.cmboxMid.setBounds(30, 110, 230, 30);

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
                    this.cmboxBot.setBounds(30, 175, 230, 30);

                    //---- label1 ----
                    this.label1.setText("1.");
                    this.label1.setName("label1");
                    this.panePriorite.add(this.label1);
                    this.label1.setBounds(10, 55, 20, 20);

                    //---- label2 ----
                    this.label2.setText("2.");
                    this.label2.setName("label2");
                    this.panePriorite.add(this.label2);
                    this.label2.setBounds(10, 115, 20, 20);

                    //---- label3 ----
                    this.label3.setText("3.");
                    this.label3.setName("label3");
                    this.panePriorite.add(this.label3);
                    this.label3.setBounds(10, 180, 20, 20);
                }
                this.panel1.add(this.panePriorite);
                this.panePriorite.setBounds(545, 20, 265, 240);

                //---- cmbxBalance ----
                this.cmbxBalance.setModel(new DefaultComboBoxModel<>(new String[] {
                    "\u00c9quilibr\u00e9",
                    "D\u00e9s\u00e9quilibr\u00e9"
                }));
                this.cmbxBalance.setName("cmbxBalance");
                this.cmbxBalance.addItemListener(e -> cmbxBalanceItemStateChanged(e));
                this.panel1.add(this.cmbxBalance);
                this.cmbxBalance.setBounds(195, 360, 260, 26);

                //---- label6 ----
                this.label6.setText("R\u00e9partition de l'\u00e9nergie du r\u00e9seau");
                this.label6.setName("label6");
                this.panel1.add(this.label6);
                this.label6.setBounds(200, 340, 206, 16);

                //---- cmbxEGlobale ----
                this.cmbxEGlobale.setActionCommand("cmbxEnergie");
                this.cmbxEGlobale.setModel(new DefaultComboBoxModel<>(new String[] {
                    "\u00c9lev\u00e9",
                    "Moyen",
                    "Faible"
                }));
                this.cmbxEGlobale.setName("cmbxEGlobale");
                this.cmbxEGlobale.addPropertyChangeListener(e -> cmbxEGlobalePropertyChange(e));
                this.panel1.add(this.cmbxEGlobale);
                this.cmbxEGlobale.setBounds(200, 300, 265, 26);

                //---- label5 ----
                this.label5.setText("\u00c9nergie globale du r\u00e9seau");
                this.label5.setName("label5");
                this.panel1.add(this.label5);
                this.label5.setBounds(200, 280, 159, 16);

                { // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < this.panel1.getComponentCount(); i++) {
                        Rectangle bounds = this.panel1.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = this.panel1.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    this.panel1.setMinimumSize(preferredSize);
                    this.panel1.setPreferredSize(preferredSize);
                }
            }
            this.tbGuideUtil.addTab("Fen\u00eatre Param\u00e8tres", this.panel1);

            //======== panel3 ========
            {
                this.panel3.setName("panel3");
                this.panel3.setLayout(null);

                //---- button1 ----
                this.button1.setText("Red\u00e9marrer/Optimiser");
                this.button1.setName("button1");
                this.panel3.add(this.button1);
                this.button1.setBounds(60, 220, 315, 120);

                //======== scrollPane4 ========
                {
                    this.scrollPane4.setName("scrollPane4");

                    //---- textPane3 ----
                    this.textPane3.setText("Voici les boutons qui seront utilis\u00e9s lors de la simulation");
                    this.textPane3.setEditable(false);
                    this.textPane3.setFont(this.textPane3.getFont().deriveFont(this.textPane3.getFont().getSize() + 5f));
                    this.textPane3.setName("textPane3");
                    this.scrollPane4.setViewportView(this.textPane3);
                }
                this.panel3.add(this.scrollPane4);
                this.scrollPane4.setBounds(215, 25, 485, 40);

                //======== scrollPane5 ========
                {
                    this.scrollPane5.setName("scrollPane5");

                    //---- textPane4 ----
                    this.textPane4.setText("Redm\u00e9marrer sert a retourner \u00e0 la fen\u00eatre des param\u00e8tres et de quitter la simulation. la fonction optimiser sert \u00e0 d\u00e9finir des param\u00e8tres offrant des r\u00e9sulatats optimaux.");
                    this.textPane4.setEditable(false);
                    this.textPane4.setFont(this.textPane4.getFont().deriveFont(this.textPane4.getFont().getSize() + 8f));
                    this.textPane4.setName("textPane4");
                    this.scrollPane5.setViewportView(this.textPane4);
                }
                this.panel3.add(this.scrollPane5);
                this.scrollPane5.setBounds(15, 85, 420, 135);

                //---- button2 ----
                this.button2.setText("D\u00e9marrer");
                this.button2.setName("button2");
                this.panel3.add(this.button2);
                this.button2.setBounds(20, 480, 345, 90);

                //======== scrollPane6 ========
                {
                    this.scrollPane6.setName("scrollPane6");

                    //---- textPane5 ----
                    this.textPane5.setText("Ce bouton sert \u00e0 d\u00e9buter la simulation et l'animation de celle-ci");
                    this.textPane5.setEditable(false);
                    this.textPane5.setFont(this.textPane5.getFont().deriveFont(this.textPane5.getFont().getSize() + 9f));
                    this.textPane5.setName("textPane5");
                    this.scrollPane6.setViewportView(this.textPane5);
                }
                this.panel3.add(this.scrollPane6);
                this.scrollPane6.setBounds(15, 355, 330, 115);

                //---- button3 ----
                this.button3.setText("Terminer");
                this.button3.setName("button3");
                this.panel3.add(this.button3);
                this.button3.setBounds(505, 200, 335, 115);

                //======== scrollPane7 ========
                {
                    this.scrollPane7.setName("scrollPane7");

                    //---- textPane6 ----
                    this.textPane6.setText("Termine la simulation et arr\u00eate l'animation de celle-ci");
                    this.textPane6.setEditable(false);
                    this.textPane6.setFont(this.textPane6.getFont().deriveFont(this.textPane6.getFont().getSize() + 13f));
                    this.textPane6.setName("textPane6");
                    this.scrollPane7.setViewportView(this.textPane6);
                }
                this.panel3.add(this.scrollPane7);
                this.scrollPane7.setBounds(515, 90, 310, 100);

                //======== scrollPane8 ========
                {
                    this.scrollPane8.setName("scrollPane8");

                    //---- textPane7 ----
                    this.textPane7.setText("Permet de quitter l'application");
                    this.textPane7.setEditable(false);
                    this.textPane7.setFont(this.textPane7.getFont().deriveFont(this.textPane7.getFont().getSize() + 14f));
                    this.textPane7.setName("textPane7");
                    this.scrollPane8.setViewportView(this.textPane7);
                }
                this.panel3.add(this.scrollPane8);
                this.scrollPane8.setBounds(515, 340, 310, 90);

                //---- button4 ----
                this.button4.setText("Quitter");
                this.button4.setName("button4");
                this.panel3.add(this.button4);
                this.button4.setBounds(495, 435, 355, 110);

                { // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < this.panel3.getComponentCount(); i++) {
                        Rectangle bounds = this.panel3.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = this.panel3.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    this.panel3.setMinimumSize(preferredSize);
                    this.panel3.setPreferredSize(preferredSize);
                }
            }
            this.tbGuideUtil.addTab("Boutons", this.panel3);

            //======== panel2 ========
            {
                this.panel2.setName("panel2");
                this.panel2.setLayout(null);

                //======== scrollPane9 ========
                {
                    this.scrollPane9.setName("scrollPane9");

                    //---- textPane8 ----
                    this.textPane8.setText("Lors de la simulation, vous pourrez int\u00e9ragir avec celle-ci. Il est possible de zoomer gr\u00e2ce \u00e0 la roulette. Il est d'autant plus possible de cliqu\u00e9 sur un v\u00e9hicule ou une Borne. Les informations de l'objet cliqu\u00e9 seront affich\u00e9s dans une zone de texte sous l'animation. Vous pouvez aussi d\u00e9placer la carte dans le composant de l'animation en appuyant enfonc\u00e9 le clique de la souris et en d\u00e9pla\u00e7ant celle-ci.");
                    this.textPane8.setEditable(false);
                    this.textPane8.setName("textPane8");
                    this.scrollPane9.setViewportView(this.textPane8);
                }
                this.panel2.add(this.scrollPane9);
                this.scrollPane9.setBounds(20, 25, 420, 140);

                //======== scrollPane10 ========
                {
                    this.scrollPane10.setName("scrollPane10");

                    //---- textPane9 ----
                    this.textPane9.setText("Le graphique d\u00e9montre au choix la moyenne du d\u00e9lai d'attente jusqu'\u00e0 la fin du chargement lors de la simulation. Ou bien, vous pouvez voir l'\u00e9negie totale du r\u00e9seau des centrales.");
                    this.textPane9.setEditable(false);
                    this.textPane9.setName("textPane9");
                    this.scrollPane10.setViewportView(this.textPane9);
                }
                this.panel2.add(this.scrollPane10);
                this.scrollPane10.setBounds(10, 265, 300, 95);

                //======== scrollPane11 ========
                {
                    this.scrollPane11.setName("scrollPane11");

                    //---- textPane10 ----
                    this.textPane10.setText("\u00c0 droite de la simulation vous pourrez voir des informations sur la simulation dont l'\u00e9nergie des centrales de la simulation. Plus bas, se trouve plusieurs options. Vous pouvez d\u00e9cider de retir\u00e9 les images des v\u00e9hicules ou des bornes, ceux-ci seront remplac\u00e9 par des rectangle de leurs couleurs respectives. Vous pouvez aussi r\u00e9initialiser les d\u00e9placement fait \u00e0 la simulation en appuyant sur le bouton en dessous. ");
                    this.textPane10.setEditable(false);
                    this.textPane10.setName("textPane10");
                    this.scrollPane11.setViewportView(this.textPane10);
                }
                this.panel2.add(this.scrollPane11);
                this.scrollPane11.setBounds(10, 485, 685, 75);

                //---- images4 ----
                this.images4.setName("images4");
                this.panel2.add(this.images4);
                this.images4.setBounds(470, 25, 435, 200);

                //---- images5 ----
                this.images5.setName("images5");
                this.panel2.add(this.images5);
                this.images5.setBounds(340, 260, 350, 215);

                //---- images6 ----
                this.images6.setName("images6");
                this.panel2.add(this.images6);
                this.images6.setBounds(715, 265, 195, this.images6.getPreferredSize().height);

                { // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < this.panel2.getComponentCount(); i++) {
                        Rectangle bounds = this.panel2.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = this.panel2.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    this.panel2.setMinimumSize(preferredSize);
                    this.panel2.setPreferredSize(preferredSize);
                }
            }
            this.tbGuideUtil.addTab("Fen\u00eatre de simulation", this.panel2);
        }
        contentPane.add(this.tbGuideUtil);
        this.tbGuideUtil.setBounds(15, 55, 925, 605);

        contentPane.setPreferredSize(new Dimension(950, 695));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        images4.setImageNom("images/Map.png");
        images5.setImageNom("images/Graph.png");
        images6.setImageNom("images/SimOptions.png");
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JLabel lblGuideUtil;
    private JTabbedPane tbGuideUtil;
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTextPane txtPaneOrdrePrio;
    private JScrollPane scrollPane2;
    private JTextPane textPane1;
    private JScrollPane scrollPane3;
    private JTextPane textPane2;
    private ComposantValeurs compValEV;
    private ComposantValeurs compValBornes;
    private ComposantValeurs compValDemandes;
    private JPanel panePriorite;
    private JComboBox<String> cmboxTop;
    private JComboBox<String> cmboxMid;
    private JComboBox<String> cmboxBot;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JComboBox<String> cmbxBalance;
    private JLabel label6;
    private JComboBox<String> cmbxEGlobale;
    private JLabel label5;
    private JPanel panel3;
    private JButton button1;
    private JScrollPane scrollPane4;
    private JTextPane textPane3;
    private JScrollPane scrollPane5;
    private JTextPane textPane4;
    private JButton button2;
    private JScrollPane scrollPane6;
    private JTextPane textPane5;
    private JButton button3;
    private JScrollPane scrollPane7;
    private JTextPane textPane6;
    private JScrollPane scrollPane8;
    private JTextPane textPane7;
    private JButton button4;
    private JPanel panel2;
    private JScrollPane scrollPane9;
    private JTextPane textPane8;
    private JScrollPane scrollPane10;
    private JTextPane textPane9;
    private JScrollPane scrollPane11;
    private JTextPane textPane10;
    private Images images4;
    private Images images5;
    private Images images6;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
