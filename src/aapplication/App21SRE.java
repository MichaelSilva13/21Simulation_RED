/*
 * Created by JFormDesigner on Wed Feb 08 16:02:49 EST 2017
 */

package aapplication;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

import com.bulenkov.darcula.DarculaLaf;
import dessinable.Images;

/**
 * @author Michael Oliveira-Silva
 * Il s'agit de la fenêtre d'accueil de l'application.
 */
public class App21SRE extends JFrame {
	private static final long serialVersionUID = -5923561137067290460L;
	private Parametres parametres;

    /**
     * Constructeur de la fenêtre App21SRE qui est la fenêtre d'accueil
     */
    private App21SRE() {
        setUndecorated(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        initComponents();
    }

    /**
     * Méthode permettant de démarrer l'application à la fenêtre d'accueil
     * @param args un tableau de string
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            try {
                //Hello darkness my old friend
                UIManager.setLookAndFeel(new DarculaLaf());
            } catch (UnsupportedLookAndFeelException | ExceptionInInitializerError e) {
                e.printStackTrace();
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (InstantiationException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedLookAndFeelException e1) {
                    e1.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "Unsupported Look and Feel. Falling back to default.", "Unsupported Look and Feel", JOptionPane.ERROR_MESSAGE);
            }finally {
                try{
                    App21SRE frame = new App21SRE();
                    frame.setVisible(true);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * Méthode qui ferme l'application lorsque le bouton Quitter est cliqué
     * @param e instance de la classe ActionEvent
     */
    private void btnQuitterActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    /**
     * Méthode qui démarre le processus de l'application en ouvrant la fenêtre des Paramètres de la simulation
     * @param e instance de la classe ActionEvent
     */
    private void btnDemarrerActionPerformed(ActionEvent e) {
        if (!parametres.isVisible()) {
            this.dispose();
            parametres.setVisible(true);
        }
    }

    /**
     * Initialise les composants
     */
    private void initComponents() {
        this.parametres = new Parametres();
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        imgLogo = new Images();
        btnDemarrer = new JButton();
        btnQuitter = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(imgLogo);
        imgLogo.setBounds(35, 10, 560, 415);

        //---- btnDemarrer ----
        btnDemarrer.setText("D\u00e9marrer");
        btnDemarrer.addActionListener(e -> btnDemarrerActionPerformed(e));
        contentPane.add(btnDemarrer);
        btnDemarrer.setBounds(190, 425, 120, 40);

        //---- btnQuitter ----
        btnQuitter.setText("Quitter");
        btnQuitter.addActionListener(e -> btnQuitterActionPerformed(e));
        contentPane.add(btnQuitter);
        btnQuitter.setBounds(310, 425, 115, 40);

        contentPane.setPreferredSize(new Dimension(630, 505));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private Images imgLogo;
    private JButton btnDemarrer;
    private JButton btnQuitter;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

}
