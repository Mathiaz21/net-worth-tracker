package NetWorthTracker;

import FunctionalComponents.GlobalInfo;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    MainTabbedPane mainTabbedPane;
    AddTransactionBar addTransactionBar;
    GlobalInfo globalInfo;

    public MainFrame() {

        super("NetWorth Tracker");
        this.globalInfo = new GlobalInfo();
        this.setupFrameParameters();
        this.mainTabbedPane = new MainTabbedPane();
        this.add(mainTabbedPane, BorderLayout.CENTER);

        this.addTransactionBar = new AddTransactionBar(globalInfo);
        this.add(addTransactionBar, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    private void setupFrameParameters() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(800, 600);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
    }
}
