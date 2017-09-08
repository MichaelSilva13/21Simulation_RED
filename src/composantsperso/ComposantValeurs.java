package composantsperso;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

import ecouteurs.ValueListener;

/**
 * Composant qui regroupe un spinner et un slider. Cela permet d'alléger l'interface tout en ajoutant un niveau de précision
 * @author Michael Oliveira-Silva
 *
 */

public class ComposantValeurs extends JPanel{

    private static final long serialVersionUID = 1L;
    private JSlider sldrValue;
    private JSpinner spnValue;
    private double value =0, max, min, step, divise=1;
    private final EventListenerList OBJETS_ENREGISTRES = new EventListenerList();
    private JLabel lblMin;
    private JLabel lblMax;



    /**
     * Constructeur qui créer le composantValeurs avec le spinner et le slider
     */
    public ComposantValeurs() {
    	
        setLayout(null);
        setPreferredSize(new Dimension(270, 67));
        spnValue = new JSpinner();
        spnValue.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                value = Double.parseDouble(spnValue.getValue()+"");
                int slideValue = (int) Math.round((value*divise));
                sldrValue.setValue(slideValue);
                leverEvenValueChange();
            }
        });
        spnValue.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        spnValue.setBounds(195, 17, 69, 26);
        add(spnValue);

        sldrValue = new JSlider();
        sldrValue.setMinimum(0);
        sldrValue.setMaximum(100);
        sldrValue.setValue(0);
        sldrValue.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                value = sldrValue.getValue()/divise;
                spnValue.setValue(sldrValue.getValue()/divise);
                leverEvenValueChange();
            }
        });
        sldrValue.setBounds(6, 17, 190, 33);
        add(sldrValue);

        lblMin = new JLabel(min+"");
        lblMin.setBounds(6, 51, 61, 16);
        add(lblMin);

        lblMax = new JLabel(max+"");
        lblMax.setHorizontalAlignment(SwingConstants.RIGHT);
        lblMax.setBounds(135, 49, 61, 16);
        add(lblMax);

    }

    /**
     * Permet de définir les valeurs du spinner et du slider
     */
    public void setSpinnerSlider(){
        if(sldrValue.getMaximum()<value){
            sldrValue.setMaximum((int)(max*divise));
            sldrValue.setValue((int)(value*divise));
        }else{
            sldrValue.setValue((int)(value*divise));
            sldrValue.setMaximum((int)(max*divise));
        }
        sldrValue.setMinimum((int)(min*divise));
        SpinnerNumberModel model = new SpinnerNumberModel(value, min, max, step);
        spnValue.setModel(model);

    }

    /**
     * Rajoute l'écouteur a l'objet
     * @param monObjet un ValueListener de notre choix
     */
    public void addValueListener(ValueListener monObjet){
        OBJETS_ENREGISTRES.add(ValueListener.class, monObjet);
    }

    /**
     * Relève le moment ou l'écouteur est appelé
     */
    private void leverEvenValueChange(){
        for(ValueListener ecout : OBJETS_ENREGISTRES.getListeners(ValueListener.class)){
            ecout.ValueChange();
        }
    }

    /**
     * Retourne la valeur du composant
     * @return value la valeur du composant
     */
    public double getValue() {
        return value;
    }

    /**
     * Définis la valeur du composant
     * @param value la valeur que l'on veut attribuer au composant
     */
    public void setValue(double value) {
        this.value = value;
        spnValue.setValue(this.value);
        sldrValue.setValue((int) (this.value*divise));
    }

    /**
     * Retourne la valeur maximale possible du composant
     * @return max la valeur maximale
     */
    public double getMax() {
        return max;
    }

    /**
     * Permet de changer la valeur maximale du composant
     * @param max le nouveau maximum que l'on veut définir
     */
    public void setMax(double max) {
        this.max = max;
        lblMax.setText(max+"");
    }

    /**
     * Retourne la valeur minimale possible du composant
     * @return min la valeur minimale
     */
    public double getMin() {
        return min;
    }

    /**
     * Permet de changer la valeur minimale du composant
     * @param min le nouveau minimale que l'on veut définir
     */
    public void setMin(double min) {
        this.min = min;
        lblMin.setText(min+"");
    }

    /**
     * Permet de retourner le pas du spinner
     * @return step le pas du spinner
     */
    public double getStep() {
        return step;
    }

    /**
     * Permet de changer le pas du spinner
     * @param step le nouveau pas que l'on veut mettre
     */
    public void setStep(double step) {
        this.step = step;
    }

    /**
     * Retourne la valeur utilisée pour attribuer des valeurs entières et équivalentes au slider a partir du spinner ou vice-versa
     * @return divise la valeur qui permet de mettre les valeurs décimales en entiers
     */
    public double getDivise() {
        return divise;
    }

    /**
     * Permet de changer le divise du composant
     * @param divise la valeur qui permet de mettre les valeurs décimales en entiers
     */
    public void setDivise(double divise) {
        this.divise=divise;
    }

    /**
     * Permet d'activer ou désactiver le spinner et le slider
     * @param actif boolean changeant l'état du slider
     */
    public void setActif(boolean actif){
        spnValue.setEnabled(actif);
        sldrValue.setEnabled(actif);
    }
}
