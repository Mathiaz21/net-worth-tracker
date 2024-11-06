package GraphicComponents;

import javax.swing.*;
import java.awt.*;

public class QuadPanel extends JPanel {

    JPanel[] panelArray;

    GridBagLayout gridBagLayout;
    GridBagConstraints gridBagConstraints;

    public QuadPanel(JPanel[] JPanelArray) {
        super();
        this.setLayout(this.gridBagLayout);
        this.displayPanels(JPanelArray);
    }


    private void displayPanels(JPanel[] JPanelArray) {

        this.setupGridBag();
        this.placePanels(JPanelArray);

    }

    private void setupGridBag() {

        this.gridBagLayout = new GridBagLayout();
        this.setLayout(this.gridBagLayout);

        this.gridBagConstraints = new GridBagConstraints();
        this.gridBagConstraints.gridwidth = 2;
        this.gridBagConstraints.gridheight = 2;
    }

    private void placePanels(JPanel[] JPanelArray) {

        this.panelArray = JPanelArray;
        int[] xList = {0,1,0,1};
        int[] yList = {0,0,1,1};
        for (int i = 0; i < this.panelArray.length; i++) {

            JPanel panel = this.panelArray[i];
            this.gridBagConstraints.gridx = xList[i];
            this.gridBagConstraints.gridy = yList[i];
            this.add(panel, this.gridBagConstraints);
        }
    }
}
