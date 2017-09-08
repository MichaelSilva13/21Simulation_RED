/*
 * Created by JFormDesigner on Wed Feb 08 16:37:26 EST 2017
 */

package menu_aide;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import dessinable.*;

/**
 * @author Gabrielle Lim
 * Inormation sur les concepts scientifiques
 */
public class ConceptsScientifiques extends JFrame {
	private static final long serialVersionUID = 1L;

	/**
     * Constructeur de la fenêtre Concepts Scientifiques
     */
    public ConceptsScientifiques() {
        initComponents();
    }

    /**
     * Initialise les composants
     */
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        this.tbPaConcepts = new JTabbedPane();
        this.panel1 = new JPanel();
        this.scrollPane1 = new JScrollPane();
        this.textPane1 = new JTextPane();
        textPane1.setFocusable(false);
        this.panel2 = new JPanel();
        this.textPane2 = new JTextPane();
        textPane2.setFocusable(false);
        this.panel3 = new JPanel();
        this.images8 = new Images();
        images8.setFocusable(false);
        this.textPane3 = new JTextPane();
        textPane3.setFocusable(false);
        this.panel4 = new JPanel();
        this.images1 = new Images();
        images1.setFocusable(false);
        this.images2 = new Images();
        images2.setFocusable(false);
        this.images3 = new Images();
        images3.setFocusable(false);
        this.images4 = new Images();
        images4.setFocusable(false);
        this.images5 = new Images();
        images5.setFocusable(false);
        this.images6 = new Images();
        images6.setFocusable(false);
        this.images7 = new Images();
        images7.setFocusable(false);
        this.images9 = new Images();
        images9.setFocusable(false);
        this.textPane4 = new JTextPane();
        textPane4.setFocusable(false);
        this.panel5 = new JPanel();
        this.textPane5 = new JTextPane();
        textPane5.setFocusable(false);
        this.lblConceptsSci = new JLabel();

        //======== this ========
        setTitle("Concepts Scientifiques");
        setName("this");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== tbPaConcepts ========
        {
            this.tbPaConcepts.setName("tbPaConcepts");

            //======== panel1 ========
            {
                this.panel1.setName("panel1");
                this.panel1.setLayout(null);

                //======== scrollPane1 ========
                {
                    this.scrollPane1.setName("scrollPane1");

                    //---- textPane1 ----
                    this.textPane1.setName("textPane1");
                    this.scrollPane1.setViewportView(this.textPane1);
                }
                this.panel1.add(this.scrollPane1);
                this.scrollPane1.setBounds(5, 5, 1130, 550);

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
            this.tbPaConcepts.addTab("V\u00e9hicules \u00c9lectriques", this.panel1);

            //======== panel2 ========
            {
                this.panel2.setName("panel2");
                this.panel2.setLayout(null);

                //---- textPane2 ----
                this.textPane2.setName("textPane2");
                this.panel2.add(this.textPane2);
                this.textPane2.setBounds(5, 5, 1126, 546);

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
            this.tbPaConcepts.addTab("SUMO", this.panel2);

            //======== panel3 ========
            {
                this.panel3.setName("panel3");
                this.panel3.setLayout(null);

                //---- images8 ----
                this.images8.setName("images8");
                this.panel3.add(this.images8);
                this.images8.setBounds(150, 290, 814, 250);

                //---- textPane3 ----
                this.textPane3.setContentType("text/html");
                this.textPane3.setText("<html>\n  <head>\n\n  </head>\n  <body>\n    <h2>\n\t\t\tTraCI</h2>\n\t\t<h4>\n\t\t\t(Traffic Control Interface)</h4>\n\t\t<p>\n\t\t\tNous utilisons TraCI afin de communiquer avec l&#39;application SUMO. Notre application ne peut communiquer directement avec SUMO alors il est n&eacute;cessaire d&#39;avoir un interm&eacute;diaire pour avoir les informations dont nous avons besoin.</p>\n\t\t<p>\n\t\t\tNous avons besoin de TraCI afin de conna&icirc;tre la position des v&eacute;hicules et afin de pouvoir donner une nouvelle route aux v&eacute;hicules lorsque nous leur avons trouv&eacute; une borne adapt&eacute;e &agrave; leurs besoins.</p>\n\t\t<p>\n\t\t\tTraCI nous permet donc de :</p>\n\t\t<ul>\n\t\t\t<li>\n\t\t\t\tSavoir en tout temps les positions des v&eacute;hicules</li>\n\t\t\t<li>\n\t\t\t\tRecevoir les vitesses des v&eacute;hicules</li>\n\t\t\t<li>\n\t\t\t\tEnvoyer un itin&eacute;raire pr&eacute;cis &agrave; un v&eacute;hicule</li>\n\t\t</ul>\n  </body>\n</html>\n");
                this.textPane3.setName("textPane3");
                this.panel3.add(this.textPane3);
                this.textPane3.setBounds(5, 5, 1126, 546);

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
            this.tbPaConcepts.addTab("TraCI", this.panel3);

            //======== panel4 ========
            {
                this.panel4.setName("panel4");
                this.panel4.setLayout(null);

                //---- images1 ----
                this.images1.setName("images1");
                this.panel4.add(this.images1);
                this.images1.setBounds(0, 117, 315, 40);

                //---- images2 ----
                this.images2.setName("images2");
                this.panel4.add(this.images2);
                this.images2.setBounds(50, 196, 20, 12);

                //---- images3 ----
                this.images3.setName("images3");
                this.panel4.add(this.images3);
                this.images3.setBounds(50, 210, 25, 18);

                //---- images4 ----
                this.images4.setName("images4");
                this.panel4.add(this.images4);
                this.images4.setBounds(50, 230, 35, 18);

                //---- images5 ----
                this.images5.setName("images5");
                this.panel4.add(this.images5);
                this.images5.setBounds(50, 248, 20, 20);

                //---- images6 ----
                this.images6.setName("images6");
                this.panel4.add(this.images6);
                this.images6.setBounds(0, 352, 400, 85);

                //---- images7 ----
                this.images7.setName("images7");
                this.panel4.add(this.images7);
                this.images7.setBounds(52, 470, 38, 24);

                //---- images9 ----
                this.images9.setName("images9");
                this.panel4.add(this.images9);
                this.images9.setBounds(325, 117, 95, 40);

                //---- textPane4 ----
                this.textPane4.setEditable(false);
                this.textPane4.setContentType("text/html");
                this.textPane4.setText("<html>\n  <head>\n\n  </head>\n  <body>\n    <h2>\n\t\t\t&Eacute;quations Math&eacute;matiques</h2>\n\t\t<h3>\n\t\t\tCalcul du d&eacute;lai d&#39;attente :</h3>\n\t\t<p>\n\t\t\tAfin de calculer le d&eacute;lai d&#39;attente d&#39;un v&eacute;hicule &eacute;lectrique avant d&#39;&ecirc;tre satisfait nous utilisons la formule math&eacute;matique suivante :</p>\n\t\t<h1>\n\t\t\t<span id=\"docs-internal-guid-a1f4058d-5596-6c20-2359-57a53833385c\"><span style=\"text-decoration: none; font-size: 11pt; font-family: 'Times New Roman'; color: rgb(0, 0, 0); font-variant-ligatures: normal; font-variant-position: normal; font-variant-numeric: normal; font-variant-alternates: normal; font-variant-east-asian: normal; vertical-align: baseline; white-space: pre-wrap;\"><a href=\"http://api.gmath.guru/cgi-bin/gmath?%5Cdpi%7B480%7DD_%7BVE_i%7D%3Da_i%2BC_i%2BFa_i%2BR\" style=\"text-decoration:none;\"><img height=\"16\" src=\"https://lh6.googleusercontent.com/HW8dY4TS1JPQQNHTfDEgWRgBpX4Vmr1M8T3r_q-bMf40MI12NTHfiWZj_F8btOx8byPpxESiQ26F9U6M6pcCR5T_hWpTgjPSh5O3BjziLxO-6TLLxQUEU19if2MBJ0WodqUvr9ge\" style=\"border: none; transform: rotate(0.00rad); -webkit-transform: rotate(0.00rad);\" title=\"cap d sub cap v cap e sub i   equals A sub i  plus cap c sub i  plus cap f A sub i  plus cap r\" width=\"201\" /></a>, </span></span><a href=\"http://api.gmath.guru/cgi-bin/gmath?%5Cdpi%7B480%7D%5Cforall%20i%20%5Cin%20N%0A\" style=\"text-decoration: none;\"><span style=\"font-size: 11pt; font-family: Arial; color: rgb(0, 0, 0); font-variant-ligatures: normal; font-variant-position: normal; font-variant-numeric: normal; font-variant-alternates: normal; font-variant-east-asian: normal; vertical-align: baseline; white-space: pre-wrap;\"><img height=\"12\" src=\"https://lh3.googleusercontent.com/Esg2NSXRfPG6FG1WtTWq50vxqT3SaIf9oqbiIE8cMPnoSme9LTHoEZPudBBjkyOR0UEtV0w_uGyOStrO-5WGEwnZCvR2J3DwOkAbnK8_wIWZf3y4WiOLQtlM-uyAwr9tq0uiF1-_\" style=\"border: none; transform: rotate(0.00rad); -webkit-transform: rotate(0.00rad);\" title=\"for all i small element of cap n\" width=\"51\" /></span></a></h1>\n\t\t<p>\n\t\t\t<span style=\"font-size: 11pt; font-family: 'Times New Roman'; font-variant-ligatures: normal; font-variant-position: normal; font-variant-numeric: normal; font-variant-alternates: normal; font-variant-east-asian: normal; vertical-align: baseline; white-space: pre-wrap;\">o&ugrave;</span><span style=\"font-size: 11pt; font-family: 'Times New Roman'; font-variant-ligatures: normal; font-variant-position: normal; font-variant-numeric: normal; font-variant-alternates: normal; font-variant-east-asian: normal; vertical-align: baseline; white-space: pre-wrap;\"><span class=\"Apple-tab-span\" style=\"white-space:pre;\"> </span></span></p>\n\t\t<ul>\n\t\t\t<li>\n\t\t\t\t<a href=\"http://api.gmath.guru/cgi-bin/gmath?%5Cdpi%7B480%7Da_i%5C%20\" style=\"text-decoration: none;\"><span style=\"font-size: 11pt; font-family: 'Times New Roman'; color: rgb(0, 0, 0); font-variant-ligatures: normal; font-variant-position: normal; font-variant-numeric: normal; font-variant-alternates: normal; font-variant-east-asian: normal; vertical-align: baseline; white-space: pre-wrap;\"><img height=\"8\" src=\"https://lh5.googleusercontent.com/p_dO1uMHt3mi7f5-KBLo44DypBPdNCNn_DpwDD3VTr-FbvIZp3lFTjYtmqL40aA-iJPy14aIqjrY1mkwczY76NhD9l9_RELpiIrvybwd-TjSNJcSFKITvLkwOk-kXBRd_qr0ILd_\" style=\"border: none; transform: rotate(0.00rad); -webkit-transform: rotate(0.00rad);\" width=\"13\" /></span></a><span style=\"font-size: 11pt; font-family: 'Times New Roman'; font-variant-ligatures: normal; font-variant-position: normal; font-variant-numeric: normal; font-variant-alternates: normal; font-variant-east-asian: normal; vertical-align: baseline; white-space: pre-wrap;\"> &nbsp;: le temps d&rsquo;arriv&eacute;e du v&eacute;hicule &eacute;lectrique</span></li>\n\t\t\t<li>\n\t\t\t\t<a href=\"http://api.gmath.guru/cgi-bin/gmath?%5Cdpi%7B480%7DC_i\" style=\"text-decoration: none;\"><span style=\"font-size: 11pt; font-family: 'Times New Roman'; color: rgb(0, 0, 0); font-variant-ligatures: normal; font-variant-position: normal; font-variant-numeric: normal; font-variant-alternates: normal; font-variant-east-asian: normal; vertical-align: baseline; white-space: pre-wrap;\"><img height=\"12\" src=\"https://lh6.googleusercontent.com/TsykazsM6X4TixIgTyW_kGLVdCY8perTNrItrTCkG3494yDSPU19TO5MiDE09oSv0rD43ZgfjOLys3Ow_R0_o07dObkEVSBt5NbgDRQ1FSzkvBo59lv3F0z66WYAqeulSy7irMLg\" style=\"border: none; transform: rotate(0.00rad); -webkit-transform: rotate(0.00rad);\" title=\"cap c sub i \" width=\"16\" /></span></a><span style=\"font-size: 11pt; font-family: 'Times New Roman'; font-variant-ligatures: normal; font-variant-position: normal; font-variant-numeric: normal; font-variant-alternates: normal; font-variant-east-asian: normal; vertical-align: baseline; white-space: pre-wrap;\"> : le temps de chargement du v&eacute;hicule &eacute;lectrique</span></li>\n\t\t\t<li>\n\t\t\t\t<a href=\"http://api.gmath.guru/cgi-bin/gmath?%5Cdpi%7B480%7DFa_i\" style=\"text-decoration: none;\"><span style=\"font-size: 11pt; font-family: 'Times New Roman'; color: rgb(0, 0, 0); font-variant-ligatures: normal; font-variant-position: normal; font-variant-numeric: normal; font-variant-alternates: normal; font-variant-east-asian: normal; vertical-align: baseline; white-space: pre-wrap;\"><img height=\"12\" src=\"https://lh5.googleusercontent.com/c6aaqmrt4T6MDeRR6t-wLKH4E_gL2HQKR1YD4n6xGcc0D-3rc9aU-7RxQfxP1T0ZZVHpyFz1ADGcgs18uvHN-9NBxt-hhA5L6Flf-rwIXaLcdueLYRc32QluK2CMwjHQ3tEiqMDo\" style=\"border: none; transform: rotate(0.00rad); -webkit-transform: rotate(0.00rad);\" title=\"cap f A sub i \" width=\"27\" /></span></a><span style=\"font-size: 11pt; font-family: 'Times New Roman'; font-variant-ligatures: normal; font-variant-position: normal; font-variant-numeric: normal; font-variant-alternates: normal; font-variant-east-asian: normal; vertical-align: baseline; white-space: pre-wrap;\"> : le temps d&rsquo;attente dans la file d&rsquo;attente du v&eacute;hicule &eacute;lectrique</span></li>\n\t\t\t<li>\n\t\t\t\t<a href=\"http://api.gmath.guru/cgi-bin/gmath?%5Cdpi%7B480%7DR\" style=\"text-decoration: none;\"><span style=\"font-size: 11pt; font-family: 'Times New Roman'; color: rgb(0, 0, 0); font-variant-ligatures: normal; font-variant-position: normal; font-variant-numeric: normal; font-variant-alternates: normal; font-variant-east-asian: normal; vertical-align: baseline; white-space: pre-wrap;\"><img height=\"11\" src=\"https://lh5.googleusercontent.com/_UfLYr-qGmi0w1MQt6MHOu1qX9GrumzDXh9xpbsPH0cIGdnm2roJjfcujDUIW4AwtvJBa0m515vxZiMO6saM7AZKnHHEoDjoLNNQkjS-TCIBk4GMiigPplwvTmZjNln3TeNH9ayI\" style=\"border: none; transform: rotate(0.00rad); -webkit-transform: rotate(0.00rad);\" title=\"cap r\" width=\"12\" /></span></a><span style=\"font-size: 11pt; font-family: 'Times New Roman'; font-variant-ligatures: normal; font-variant-position: normal; font-variant-numeric: normal; font-variant-alternates: normal; font-variant-east-asian: normal; vertical-align: baseline; white-space: pre-wrap;\"> : le temps de recherche afin de trouver la borne choisie</span></li>\n\t\t</ul>\n\t\t<h3>\n\t\t\tDeux fonctions objectives :</h3>\n\t\t<p>\n\t\t\tAfin d&#39;avoir le meilleur contexte possible pour nos utilisateurs, nous visons les deux fonctions objectives suivantes :</p>\n\t\t<p>\n\t\t\t<span id=\"docs-internal-guid-a1f4058d-5599-9db4-767b-188f7042123e\"><a href=\"http://api.gmath.guru/cgi-bin/gmath?%5Cdpi%7B480%7DMin%5Cleft(D_%7BVE_%7Bi%2C%5C%20i%5C%20%5Cin%5C%20N%7D%7D%5Cright)\" style=\"text-decoration:none;\"><span style=\"font-size: 11pt; font-family: 'Times New Roman'; color: rgb(0, 0, 0); font-variant-ligatures: normal; font-variant-position: normal; font-variant-numeric: normal; font-variant-alternates: normal; font-variant-east-asian: normal; vertical-align: baseline; white-space: pre-wrap;\"><img height=\"23\" src=\"https://lh4.googleusercontent.com/xG5HomH0HEMxkYlDzfWC5UKB7dJ1DgyjZSky28FV0exMHKJJaP5lkGIj3zF77RTy_6pjGHPfqsXhyi0jWGgBAbU32cAcSYE9seft_4hFt8vZqCkixwEAFAA4migudfp4y_-TW8Dj\" style=\"border: none; transform: rotate(0.00rad); -webkit-transform: rotate(0.00rad);\" title=\"cap m i n left parenthesis cap d sub cap v cap e sub i comma \\  i \\  small element of \\  cap n   right parenthesis\" width=\"139\" /></span></a></span></p>\n\t\t<p>\n\t\t\t<span style=\"text-decoration: none; font-size: 11pt; font-family: 'Times New Roman'; color: rgb(0, 0, 0); font-variant-ligatures: normal; font-variant-position: normal; font-variant-numeric: normal; font-variant-alternates: normal; font-variant-east-asian: normal; vertical-align: baseline; white-space: pre-wrap;\"><a href=\"http://api.gmath.guru/cgi-bin/gmath?%5Cdpi%7B480%7DMin%5Cleft%5Bdiff_%7Bi%5C%20%5Cne%5C%20j%2C%5C%20j%5Cin%20N%5Cleft(Ce_i%5Cright)%2C%5C%20i%2C%5C%20j%5C%20%5Cle%5Cleft%7CM%5Cright%7C%7D%5Cleft%7CE_%7BCe_i%7D-E_%7BCe_j%7D%5Cright%7C%5Cright%5D\" style=\"text-decoration: none;\"><img height=\"24\" src=\"https://lh4.googleusercontent.com/ZgrRTk9bdIiOpWVawSjCLgdQQXldQn7l8AOljSV1qDQW5xC1zaKVj9z1RrE1GUGX6YLL8UFnic1K466hBcWVFQ4xYtRu9-aX8sKZRrH52fFkoTJZESm8hGR06OsjZpiJcn56YkIq\" style=\"border: none; transform: rotate(0.00rad); -webkit-transform: rotate(0.00rad);\" title=\"cap m i n left squared bracket d i f f sub i \\  not equals \\  j comma \\  j small element of cap n left parenthesis cap c e sub i  right parenthesis comma \\  i comma \\  j \\  less than or equals vertical line cap m vertical line  vertical line cap e sub cap c e sub i   minus cap e sub cap c e sub j   vertical line right squared bracket\" width=\"372\" /></a></span></p>\n\t\t<p>\n\t\t\t<span style=\"text-decoration: none; font-size: 11pt; font-family: 'Times New Roman'; color: rgb(0, 0, 0); font-variant-ligatures: normal; font-variant-position: normal; font-variant-numeric: normal; font-variant-alternates: normal; font-variant-east-asian: normal; vertical-align: baseline; white-space: pre-wrap;\"></span><span style=\"font-family: 'Times New Roman'; font-size: 11pt; white-space: pre-wrap;\">o&ugrave;</span></p>\n\t\t<ul>\n\t\t\t<li>\n\t\t\t\t<a href=\"http://api.gmath.guru/cgi-bin/gmath?%5Cdpi%7B480%7DE_%7BCe_i%7D\" style=\"text-decoration: none;\"><span style=\"font-size: 11pt; font-family: 'Times New Roman'; color: rgb(0, 0, 0); font-variant-ligatures: normal; font-variant-position: normal; font-variant-numeric: normal; font-variant-alternates: normal; font-variant-east-asian: normal; vertical-align: baseline; white-space: pre-wrap;\"><img height=\"16\" src=\"https://lh4.googleusercontent.com/7Ky_xMjihyhhfS9fpkYyl8_ExxJidlL558HKxJAoN2mjJMPXMuqTf28R5-DUl_ED1hpD04jmlnyC5nmAgn5rECpQYFWftvsOPkmjJU3uMbi0g3KGlxZFi9XglpLzJ0HuMXMTYuRM\" style=\"border: none; transform: rotate(0.00rad); -webkit-transform: rotate(0.00rad);\" title=\"cap e sub cap c e sub i  \" width=\"33\" /></span></a><span style=\"font-size: 11pt; font-family: 'Times New Roman'; font-variant-ligatures: normal; font-variant-position: normal; font-variant-numeric: normal; font-variant-alternates: normal; font-variant-east-asian: normal; vertical-align: baseline; white-space: pre-wrap;\"> : l&rsquo;&eacute;nergie de la centrale &eacute;lectrique</span></li>\n\t\t\t<li>\n\t\t\t\t<span style=\"font-size: 11pt; font-family: 'Times New Roman'; font-style: italic; font-variant-ligatures: normal; font-variant-position: normal; font-variant-numeric: normal; font-variant-alternates: normal; font-variant-east-asian: normal; vertical-align: baseline; white-space: pre-wrap;\">i, j</span><span style=\"font-size: 11pt; font-family: 'Times New Roman'; font-variant-ligatures: normal; font-variant-position: normal; font-variant-numeric: normal; font-variant-alternates: normal; font-variant-east-asian: normal; vertical-align: baseline; white-space: pre-wrap;\"> : des centrales dans la m&ecirc;me port&eacute;e (dans un m&ecirc;me rayon par rapport au v&eacute;hicule) </span></li>\n\t\t</ul>\n\t\t<p>\n\t\t\t<span style=\"font-size: 11pt; font-family: 'Times New Roman'; font-variant-ligatures: normal; font-variant-position: normal; font-variant-numeric: normal; font-variant-alternates: normal; font-variant-east-asian: normal; vertical-align: baseline; white-space: pre-wrap;\">C&#39;est deux fonctions nous permettent de minimiser le d&eacute;lai d&#39;attente et de diminuer la diff&eacute;rence d&#39;&eacute;nergie entre les centrales &eacute;lectriques. </span></p>\n\t\t<p>\n\t\t\t<font face=\"Times New Roman\"><span style=\"font-size: 12px; white-space: pre-wrap;\">1. </span></font><span style=\"color: rgb(51, 51, 51); font-family: 'Times New Roman'; font-size: 8pt; white-space: pre-wrap; background-color: rgb(255, 255, 255); text-align: justify;\">Jihene Rezgui, Soumaya Cherkaoui, &ldquo;Smart Charge Scheduling for EVs based on Two-Way Communication </span><span style=\"color: rgb(51, 51, 51); font-family: 'Times New Roman'; font-size: 8pt; white-space: pre-wrap; background-color: rgb(255, 255, 255);\">accepted&rdquo;, Publication ICC Paris, 2017</span></p>\n  </body>\n</html>\n");
                this.textPane4.setName("textPane4");
                this.panel4.add(this.textPane4);
                this.textPane4.setBounds(0, -5, 1126, 551);

                { // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < this.panel4.getComponentCount(); i++) {
                        Rectangle bounds = this.panel4.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = this.panel4.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    this.panel4.setMinimumSize(preferredSize);
                    this.panel4.setPreferredSize(preferredSize);
                }
            }
            this.tbPaConcepts.addTab("\u00c9quations Math\u00e9matiques", this.panel4);

            //======== panel5 ========
            {
                this.panel5.setName("panel5");
                this.panel5.setLayout(null);

                //---- textPane5 ----
                this.textPane5.setContentType("text/html");
                this.textPane5.setText("<html>\n  <head>\n\n  </head>\n  <body>\n    <h2>\n\t\t\tAlgorithme de Dijkstra</h2>\n\t\t<p>\n\t\t\t<span style=\"font-family:times new roman,times,serif;font-size: 18pt\">L&#39;algorithme de Dijkstra se base sur la th&eacute;orie des graphes utilis&eacute;e en math&eacute;matiques et en informatiques. Un graphe est un mod&egrave;le abstrait de dessin de r&eacute;seau reliant des objets. Il est constitu&eacute; de &laquo; points &raquo; g&eacute;n&eacute;ralement appel&eacute;s <em>sommets</em>&nbsp; et de &laquo; liens &raquo; entre ces points g&eacute;n&eacute;ralement appel&eacute;s&nbsp;<em>ar&ecirc;tes</em>. Les algorithmes bas&eacute;s sur cette th&eacute;orie permmettent de r&eacute;soudre de nombreux probl&egrave;mes de diff&eacute;rent domaine tel le th&eacute;or&egrave;me des quatres couleurs. </span></p>\n\t\t<p>\n\t\t\t<span style=\"font-family:times new roman,times,serif;font-size: 18pt\">Dans notre cas, celui qui nous aide est l&#39;algorithme de Dijkstra. Celui-ci sert &agrave; r&eacute;soudre le probl&egrave;me du chemin le plus court. Dans notre contexte, il fonctionnera comme suit :</span></p>\n\t\t<p>\n\t\t\t<span style=\"font-family:times new roman,times,serif;font-size: 18pt\">Lorsqu&rsquo;une demande sera effectu&eacute;e celle-ci sera prise en charge par notre m&eacute;canisme de recherche de borne qui peut &ecirc;tre modifi&eacute; gr&acirc;ce &agrave; l&rsquo;ordre de priorit&eacute; des contraintes s&eacute;lectionn&eacute;es pr&eacute;alablement (d&eacute;lai d&rsquo;attente, distance de la borne, r&eacute;partition de la charge), appel&eacute; ED (Enhanced-Dijkstra) .</span></span></p>\n\t\t<p>\n\t\t\t<span style=\"font-family:times new roman,times,serif;font-size: 18pt\"><span id=\"docs-internal-guid-a1f4058d-55b2-9ab9-3eaf-9c4bb2847b97\">Notre m&eacute;canisme, ED, se basera sur l&rsquo;algorithme de Dijkstra que nous modifierons pour s&rsquo;adapter &agrave; nos besoins. Celui-ci nous permettra de trouver les bornes pouvant &ecirc;tre atteinte par le v&eacute;hicule ainsi que le chemin le plus court jusqu&rsquo;&agrave; celle-ci. En effet, celui-ci a &eacute;t&eacute; con&ccedil;us pour trouver le chemin le plus court dans un graphe. Dans notre situation, la carte du r&eacute;seau routier est notre graphe. Les intersections de la route sont les noeuds et les routes sont les liens. ED d&eacute;termine le meilleur chemin en additionnant les poids allou&eacute;s aux routes entre deux intersections. Dans notre contexte, le poids des routes sera d&eacute;termin&eacute; par leurs distances. Par contre, nous nous limiterons pas seulement &agrave; trouver le chemin le plus court. Nous avons modifi&eacute; celui-ci, car il faut prendre en compte une distance maximale atteignable par le v&eacute;hicule d&eacute;pendamment de son niveau de charge ainsi que son point de d&eacute;part dans le graphe. Avec ces informations, ED cherche, dans une liste d&rsquo;intersections, celles qui se trouvent &agrave; une distance &eacute;gale ou inf&eacute;rieure &agrave; la distance donn&eacute;e. Ainsi, ED trouve les bornes auxquels un v&eacute;hicule &eacute;lectrique peut se rendre. La distance donn&eacute;e &agrave; ED sera la distance pouvant encore &ecirc;tre parcourue par le v&eacute;hicule.</span></span></span></p>\t\n  </body>\n</html>\n");
                this.textPane5.setName("textPane5");
                this.panel5.add(this.textPane5);
                this.textPane5.setBounds(5, 5, 1126, 546);

                { // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < this.panel5.getComponentCount(); i++) {
                        Rectangle bounds = this.panel5.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = this.panel5.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    this.panel5.setMinimumSize(preferredSize);
                    this.panel5.setPreferredSize(preferredSize);
                }
            }
            this.tbPaConcepts.addTab("Algorithme de Dijkstra", this.panel5);
        }
        contentPane.add(this.tbPaConcepts);
        this.tbPaConcepts.setBounds(10, 60, 1145, 585);

        //---- lblConceptsSci ----
        this.lblConceptsSci.setText("Concepts Scientifiques");
        this.lblConceptsSci.setFont(new Font("AppleGothic", Font.PLAIN, 30));
        this.lblConceptsSci.setName("lblConceptsSci");
        contentPane.add(this.lblConceptsSci);
        this.lblConceptsSci.setBounds(new Rectangle(new Point(425, 20), this.lblConceptsSci.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(1165, 675));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        textPane1.setContentType("text/html");
        textPane1.setEditable(false);
        textPane1.setText(text);

        textPane2.setContentType("text/html");
        textPane2.setEditable(false);
        textPane2.setText(text2);

        textPane3.setContentType("text/html");
        textPane3.setEditable(false);
        //textPane3.setText(text3);

        textPane4.setContentType("text/html");
        textPane4.setEditable(false);
        //textPane4.setText(text4);

        textPane5.setContentType("text/html");
        textPane5.setEditable(false);
        //textPane5.setText(text5);

        images1.setImageNom("images/formuleWHITE.png");
        images2.setImageNom("images/aiWHITE.png");
        images3.setImageNom("images/ciWHITE.png");
        images4.setImageNom("images/FaiWHITE.png");
        images5.setImageNom("images/RWHITE.png");
        images6.setImageNom("images/FonctionsObjectivesWHITE.png");
        images7.setImageNom("images/EceiWHITE.png");
        images8.setImageNom("images/SUMO_TRACI_BLANC.png");
        images9.setImageNom("images/formulesuiteWHITE.png");
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JTabbedPane tbPaConcepts;
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTextPane textPane1;
    private JPanel panel2;
    private JTextPane textPane2;
    private JPanel panel3;
    private Images images8;
    private JTextPane textPane3;
    private JPanel panel4;
    private Images images1;
    private Images images2;
    private Images images3;
    private Images images4;
    private Images images5;
    private Images images6;
    private Images images7;
    private Images images9;
    private JTextPane textPane4;
    private JPanel panel5;
    private JTextPane textPane5;
    private JLabel lblConceptsSci;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private String text = "\t\t<h2> V&eacute;hicules &Eacute;lectriques </h2>\n" +
            "\t\t<p>\n" +
            "\t\t\tIntroduction\n" +
            "\t\t</p>\n" +
            "\t\t<p>\n" +
            "\t\t\tAfin d&#39;avoir les donn&eacute;es les plus pr&eacute;cises possible, nous \n" +
            "\t\t\tutilisons plusieurs marques et mod&egrave;les de v&eacute;hicules enti&egraverement &eacute;lectriques diff&eacute;rents. Lors de la s&eacute;lection de ceux-ci, \n" +
            "\t\t\tnous avons d&eacute;cid&eacute; de choisir les mod&egrave;les de 2017 disponibles \n" +
            "\t\t\tau Canada. Nous sommes all&eacute;s sur le site : \n" +
            "\t\t\t<a href=\"http://www.plugndrive.ca/electric-cars-available-in-canada\">\n" +
            "\t\t\thttp://www.plugndrive.ca/electric-cars-available-in-canada </a>[1]. \n" +
            "\t\t\tCelui-ci nous &agrave; &eacute;galement permis de trouver des informations importantes \n" +
            "\t\t\tpour la simulation sur chaque v&eacute;hicule. Par exemple, le temps de chargement total \n" +
            "\t\t\td&#39;un v&eacute;hicule ou la distance maximale qui peut &ecirc;tre parcourut gr&acirc;ce &agrave; \n" +
            "\t\t\tune recharge. Voici les v&eacute;hicules choisis :\n" +
            "\t\t</p>\n" +
            "\t\t<p>\n" +
            "\t\t<table border=\"2\" cellpadding=\"1\" cellspacing=\"1\" width=\"600\" \">\n" +
            "\t\t\t<tbody>\n" +
            "\t\t\t\t<tr>\n" +
            "\t\t\t\t\t<td>\n" +
            "\t\t\t\t\t\t<h3>\n" +
            "\t\t\t\t\t\t\tBMW i3</h3>\n" +
            "\t\t\t\t\t\t<p>\n" +
            "\t\t\t\t\t\t\tDistance Maximale Parcourue :&nbsp;130 km</p>\n" +
            "\t\t\t\t\t\t<p>\n" +
            "\t\t\t\t\t\t\tTemps de charge : 6.25 heures</p>\n" +
            "\t\t\t\t\t</td>\n" +
            "\t\t\t\t\t<td>\n" +
            "\t\t\t\t\t\t<img alt=\"BMW i3 image\" src=\"http://cdn.bmwblog.com/wp-content/uploads/2016/05/BMW-i3-Protonic-Blue-1.jpg\" width=\"300\" height=\"210\"/></td>\n" +
            "\t\t\t\t</tr>\n" +
            "\t\t\t\t<tr>\n" +
            "\t\t\t\t\t<td>\n" +
            "\t\t\t\t\t\t<h3>\n" +
            "\t\t\t\t\t\t\tChevrolet Bolt</h3>\n" +
            "\t\t\t\t\t\t<p>\n" +
            "\t\t\t\t\t\t\tDistance Maximale Parcourue :&nbsp;383 km</p>\n" +
            "\t\t\t\t\t\t<p>\n" +
            "\t\t\t\t\t\t\tTemps de charge : 9.5 heures</p>\n" +
            "\t\t\t\t\t</td>\n" +
            "\t\t\t\t\t<td>\n" +
            "\t\t\t\t\t\t<img alt=\"Chevrolet Bolt Image\" src=\"http://www.thedetroitbureau.com/wp-content/uploads/2016/09/2017-Chevrolet-Bolt-EV.jpg\" width=\"300\" height=\"210\" /></td>\n" +
            "\t\t\t\t</tr>\n" +
            "\t\t\t\t<tr>\n" +
            "\t\t\t\t\t<td>\n" +
            "\t\t\t\t\t\t<h3>\n" +
            "\t\t\t\t\t\t\tFord Focus &Eacute;lectrique</h3>\n" +
            "\t\t\t\t\t\t<p>\n" +
            "\t\t\t\t\t\t\tDistance Maximale Parcourue :&nbsp;122 km</p>\n" +
            "\t\t\t\t\t\t<p>\n" +
            "\t\t\t\t\t\t\tTemps de charge : 4 heures</p>\n" +
            "\t\t\t\t\t</td>\n" +
            "\t\t\t\t\t<td>\n" +
            "\t\t\t\t\t\t<img alt=\"Ford Focus Electrique Image\" src=\"http://cnet2.cbsistatic.com/hub/i/r/2014/11/14/034a38a8-528e-4d39-b3b3-d6b6382462e2/thumbnail/770x433/b5011322adfcf86081072079e03a6751/2015-ford-focus-electric-02.jpg\" width=\"300\" height=\"150\" /></td>\n" +
            "\t\t\t\t</tr>\n" +
            "\t\t\t\t<tr>\n" +
            "\t\t\t\t\t<td>\n" +
            "\t\t\t\t\t\t<h3>\n" +
            "\t\t\t\t\t\t\tKia Soul Ev</h3>\n" +
            "\t\t\t\t\t\t<p>\n" +
            "\t\t\t\t\t\t\tDistance Maximale Parcourue :&nbsp;149 km</p>\n" +
            "\t\t\t\t\t\t<p>\n" +
            "\t\t\t\t\t\t\tTemps de charge : 4.5 heures</p>\n" +
            "\t\t\t\t\t</td>\n" +
            "\t\t\t\t\t<td>\n" +
            "\t\t\t\t\t\t<img alt=\"Kia Soul Ev Image\" src=\"http://www.motorward.com/wp-content/images/2014/02/Kia-Soul-EV-1.jpg\" width=\"300\" height=\"210\" /></td>\n" +
            "\t\t\t\t</tr>\n" +
            "\t\t\t\t<tr>\n" +
            "\t\t\t\t\t<td>\n" +
            "\t\t\t\t\t\t<h3>\n" +
            "\t\t\t\t\t\t\tMitsubishi i-MiEV</h3>\n" +
            "\t\t\t\t\t\t<p>\n" +
            "\t\t\t\t\t\t\tDistance Maximale Parcourue :&nbsp;100 km</p>\n" +
            "\t\t\t\t\t\t<p>\n" +
            "\t\t\t\t\t\t\tTemps de charge : 5 heures</p>\n" +
            "\t\t\t\t\t</td>\n" +
            "\t\t\t\t\t<td>\n" +
            "\t\t\t\t\t\t<img alt=\"Mitsubishi i-MiEV Image\" src=\"http://images.dealer.com/ddc/vehicles/2017/Mitsubishi/i-MiEV/Hatchback/color/Diamond%20White%20Pearl-W13-202,199,181-640-en_US.jpg\" width=\"300\" height=\"180\" /></td>\n" +
            "\t\t\t\t</tr>\n" +
            "\t\t\t\t<tr>\n" +
            "\t\t\t\t\t<td>\n" +
            "\t\t\t\t\t\t<h3>\n" +
            "\t\t\t\t\t\t\tNissan LEAF</h3>\n" +
            "\t\t\t\t\t\t<p>\n" +
            "\t\t\t\t\t\t\tDistance Maximale Parcourue :&nbsp;172 km</p>\n" +
            "\t\t\t\t\t\t<p>\n" +
            "\t\t\t\t\t\t\tTemps de charge : 5 heures</p>\n" +
            "\t\t\t\t\t</td>\n" +
            "\t\t\t\t\t<td>\n" +
            "\t\t\t\t\t\t<img alt=\"Nissan Leaf Image\" src=\"http://cdn2.carbuyer.co.uk/sites/carbuyer_d7/files/nissan-leaf-cutout-2.jpg\" width=\"300\" height=\"180\" /></td>\n" +
            "\t\t\t\t</tr>\n" +
            "\t\t\t\t<tr>\n" +
            "\t\t\t\t\t<td>\n" +
            "\t\t\t\t\t\t<h3>\n" +
            "\t\t\t\t\t\t\tTesla Model S</h3>\n" +
            "\t\t\t\t\t\t<p>\n" +
            "\t\t\t\t\t\t\tDistance Maximale Parcourue :&nbsp;435 km</p>\n" +
            "\t\t\t\t\t\t<p>\n" +
            "\t\t\t\t\t\t\tTemps de charge : 12 heures</p>\n" +
            "\t\t\t\t\t</td>\n" +
            "\t\t\t\t\t<td>\n" +
            "\t\t\t\t\t\t<img alt=\"Tesla Model S Image\" src=\"http://3.bp.blogspot.com/-dkV6KmOPdac/TYNlQKQdb8I/AAAAAAAABZU/dGqr8tSgsfw/s1600/Tesla%2BModel%2BSa.jpg\" width=\"300\" height=\"180\" /></td>\n" +
            "\t\t\t\t</tr>\n" +
            "\t\t\t\t<tr>\n" +
            "\t\t\t\t\t<td>\n" +
            "\t\t\t\t\t\t<h3>\n" +
            "\t\t\t\t\t\t\tTesla Model X</h3>\n" +
            "\t\t\t\t\t\t<p>\n" +
            "\t\t\t\t\t\t\tDistance Maximale Parcourue :&nbsp;413 km</p>\n" +
            "\t\t\t\t\t\t<p>\n" +
            "\t\t\t\t\t\t\tTemps de charge : 12 heures</p>\n" +
            "\t\t\t\t\t</td>\n" +
            "\t\t\t\t\t<td>\n" +
            "\t\t\t\t\t\t<img alt=\"Tesla Model X Image\" src=\"http://buyersguide.caranddriver.com/media/assets/submodel/4877.jpg\" width=\"300\" height=\"180\" /></td>\n" +
            "\t\t\t\t</tr>\n" +
            "\t\t\t</tbody>\n" +
            "\t\t</table>\n" +
            "\t\t<p>\n" +
            "\t\t1. Comme consult&eacute; le 24 f&eacute;vrier 2017\n" +
            "\t\t</p>\n" +
            "\t\t\n";

    private String text2 = "<h4>\n" +
            "\t\t\t<img alt=\"logo SUMO\" src=\"http://www.sumo.dlr.de/userdoc/logo.png\" style=\"width: 100px; height: 100px;\" /></h4>\n" +
            "\t\t\t<h2> SUMO </h2>\n" +
            "\t\t<h4>\n" +
            "\t\t\t(Simulation of Urban Mobility)</h4>\n" +
            "\t\t<div>\n" +
            "\t\t\tSUMO est une application open-source d&eacute;velopp&eacute;e par le centre de recherche a&eacute;ronautique et astronautique allemand DLR.&nbsp;</div>\n" +
            "\t\t<div>\n" +
            "\t\t\t&nbsp;</div>\n" +
            "\t\t<div>\n" +
            "\t\t\tL&#39;application permet de simuler la circulation routi&egrave;re ce qui nous permet de nous concentrer seulement aux r&eacute;els objectifs de notre recherche. De cette fa&ccedil;on, SUMO s&#39;occupe de g&eacute;rer la circulation routi&egrave;re lors de nos simulations en s&#39;occupant entre autre :</div>\n" +
            "\t\t<ul>\n" +
            "\t\t\t<li>\n" +
            "\t\t\t\tDu traffic</li>\n" +
            "\t\t\t<li>\n" +
            "\t\t\t\tDes feux de circulation</li>\n" +
            "\t\t\t<li>\n" +
            "\t\t\t\tDe l&#39;acc&eacute;l&eacute;ration et le freinage fait par les v&eacute;hicules</li>\n" +
            "\t\t\t<li>\n" +
            "\t\t\t\tDes routes emprunt&eacute;es par les v&eacute;hicules</li>\n" +
            "\t\t\t<li>\n" +
            "\t\t\t\tDe l&#39;int&eacute;raction entre les v&eacute;hicules</li>\n" +
            "\t\t\t<li>\n" +
            "\t\t\t\tDe l&#39;apparition et la disparition des v&eacute;hicules dans la carte</li>\n" +
            "\t\t</ul>";
}
