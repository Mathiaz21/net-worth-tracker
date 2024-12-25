package SecondaryGraphicComponents;

import javax.swing.*;
import java.awt.*;

public class QuadPanel extends JPanel {

    JPanel[] panelArray;
    GridBagLayout gridBagLayout;
    GridBagConstraints GBC;

    public QuadPanel(JPanel[] jPanelArray) {
        super();
        this.setupPanels(jPanelArray);
        this.setPanelSizes();
        this.addPanels();
    }


    private void setupPanels(JPanel[] jPanelArray) {

        this.GBC = new GridBagConstraints();
        this.gridBagLayout = new GridBagLayout();
        this.setLayout(this.gridBagLayout);

        this.panelArray = jPanelArray;
        for (JPanel panel : this.panelArray)
            this.add(panel);
    }

    private void addPanels() {
        this.GBC.gridx = 0;
        this.GBC.gridy = 0;
        this.add(this.panelArray[0], this.GBC);

        this.GBC.gridx = 1;
        this.add(this.panelArray[1], this.GBC);

        this.GBC.gridx = 0;
        this.GBC.gridy = 1;
        this.add(this.panelArray[2], this.GBC);

        this.GBC.gridx = 1;
        this.add(this.panelArray[3], this.GBC);
    }

    private void setPanelSizes() {
        Dimension panelSize = new Dimension(800, 500);
        for(JPanel panel : this.panelArray) {
            panel.setPreferredSize(panelSize);
        }
    }
}
