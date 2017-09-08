/*
 * Created by JFormDesigner on Thu Feb 23 20:59:04 EST 2017
 */

package menu_aide;

import java.awt.*;
import javax.swing.*;

/**
 * @author Gabrielle Lim
 * Classe permettant d'afficher une fenêtre disant les étapes de téléchargement préalable pour l'utilisation de l'application
 */
public class Prealables extends JFrame {
	private static final long serialVersionUID = 1L;

	/**
     * Constructeur de la fenêtre Préalables
     */
    public Prealables() {
		setResizable(false);
        initComponents();
    }

    /**
     * Initialise les composants
     */
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        lblInsPre = new JLabel();
        lblTexte = new JLabel();
        lblTexte.setVerticalAlignment(SwingConstants.TOP);
        lblTexte.setHorizontalAlignment(SwingConstants.LEFT);

        //======== this ========
        setTitle("Pr\u00e9alables");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- lblInsPre ----
        lblInsPre.setText("Instructions pour l'Installation de SUMO");
        lblInsPre.setFont(new Font("Dialog", Font.BOLD, 27));
        contentPane.add(lblInsPre);
        lblInsPre.setBounds(new Rectangle(5, 20, 540, 39));
        contentPane.add(lblTexte);
        lblTexte.setBounds(10, 65, 510, 304);

        contentPane.setPreferredSize(new Dimension(555, 380));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents


        lblTexte.setText(text);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JLabel lblInsPre;
    private JLabel lblTexte;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private String text = "<html><p style=\"text-align: left;\">(Simulation of Urban MObility)<br><p>&nbsp;</p><p style=\"text-align: left;\">N&eacute;cessaire pour le fonctionnement de l'application<br>" +
            "<p>&nbsp;</p><p style=\"text-align: left;\">1.T&eacute;l&eacute;charger l'executable sur le site suivant : <a title=\"html Editor\" href=\"https://sourceforge.net/projects/sumo/\">https://sourceforge.net/projects/sumo/</a><br>" +
            "<p>&nbsp;</p><p style=\"text-align: left;\">2.S'assurer de t&eacute;l&eacute;charger la version 0.28.0<br><p>&nbsp;</p><p style=\"text-align: left;\">3.Lancer l'installateur<br><p>&nbsp;</p><p style=\"text-align: left;\">4.Installer SUMO sur un disque local<br>" +
            "<p>&nbsp;</p><p style=\"text-align: left;\">5.Bien v&eacute;rifier que la case à cocher pour ajouter SUMO dans la variable d'environnement PATH est s&eacute;lectionn&eacute;e<br>(n&eacute;cessaire pour localiser l'ex&eacute;cutable)<br>" +
            "<p>&nbsp;</p><p style=\"text-align: left;\">6.Red&eacute;marrer eclipse ou tout autre JDE si l'application &eacute;tait d&eacute;j&agrave; ouverte<br><p>&nbsp;</p><p style=\"text-align: left;\">Vous pouvez maintenant commencer une simulation!!</p></html>";

}
