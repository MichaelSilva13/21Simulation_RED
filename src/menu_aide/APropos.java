/*
 * Created by JFormDesigner on Wed Feb 08 16:36:45 EST 2017
 */

package menu_aide;

import java.awt.*;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.JFrame;
import dessinable.*;

/**
 * @author Gabrielle Lim
 * Information sur l'application
 */
public class APropos extends JFrame {
	private static final long serialVersionUID = 1L;

	/**
     * Constructeur de la fenêtre À Propos
     */
    public APropos() {
        initComponents();
    }

    /**
     * Initialise les composants
     */
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        lblTitreAPropos = new JLabel();
        lblTexte = new JLabel();
        logoRED = new Images();
        logoLRIMA = new Images();

        //======== this ========
        setTitle("\u00c0 Propos");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- lblTitreAPropos ----
        lblTitreAPropos.setText("\u00c0 Propos");
        lblTitreAPropos.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitreAPropos.setFont(new Font("AppleGothic", Font.BOLD, 30));
        contentPane.add(lblTitreAPropos);
        lblTitreAPropos.setBounds(160, 10, 200, 35);

        //---- lblTexte ----
        lblTexte.setHorizontalAlignment(SwingConstants.CENTER);
        lblTexte.setVerticalAlignment(SwingConstants.TOP);
        contentPane.add(lblTexte);
        lblTexte.setBounds(15, 55, 495, 305);
        contentPane.add(logoRED);
        logoRED.setBounds(10, 345, 245, 120);
        contentPane.add(logoLRIMA);
        logoLRIMA.setBounds(290, 365, 215, 90);

        contentPane.setPreferredSize(new Dimension(525, 495));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        lblTexte.setText(text);
        logoLRIMA.setImageNom("images/LRIMA.png");
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JLabel lblTitreAPropos;
    private JLabel lblTexte;
    private Images logoRED;
    private Images logoLRIMA;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private String text = "<html> <h2 style=\"text-align: center;\"><b>R.E.D.</b></h2><h3 style=\"text-align: center;\">R&eacute;seau &Eacute;lectrique Distribu&eacute;</h3><p style=\"text-align: center;\">D&eacute;veloppeurs :<br>Gabrielle Lim & Michael Oliveira-Silva<br><p>&nbsp;</p>" +
            "<p style=\"text-align: center;\">Coll&egrave;ge de Maisonneuve, 2017<br><p>&nbsp;</p><p style=\"text-align: center;\">Int&eacute;gration des apprentissages en sciences informatiques et math&eacute;matiques<br>420-SCD-MA<br><p>&nbsp;</p><p style=\"text-align: center;\">Sous la supervision de Caroline Houle et Jihene Rezgui<br>" +
            "Dans le cadre du laboratoire de recherche LRIMa<br>(<b>L</b>aboratoire de <b>R</b>echerche <b>I</b>nformatique <b>M</b>aisonneuve)</p></html>";

}
