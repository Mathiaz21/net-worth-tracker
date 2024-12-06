package SecondaryGraphicComponents;

import javax.swing.*;
import java.awt.*;

public class QuadPanel extends JPanel {

    JPanel[] panelArray;
    GridLayout gridLayout;

    public QuadPanel(JPanel[] jPanelArray) {
        super();
        this.setupPanels(jPanelArray);
    }


    private void setupPanels(JPanel[] jPanelArray) {

        this.gridLayout = new GridLayout(2, 2);
        this.setLayout(this.gridLayout);

        this.panelArray = jPanelArray;
        for (JPanel panel : this.panelArray)
            this.add(panel);
    }
}
