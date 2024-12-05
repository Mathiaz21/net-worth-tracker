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

        this.addTransactionBar = new AddTransactionBar(this.globalInfo);
        this.add(addTransactionBar, BorderLayout.SOUTH);

        this.mainTabbedPane = new MainTabbedPane(this.globalInfo);
        this.add(mainTabbedPane, BorderLayout.CENTER);


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
