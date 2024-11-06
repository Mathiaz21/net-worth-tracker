package NetWorthTracker;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    MainTabbedPane mainTabbedPane;
    AddTransactionBar addTransactionBar;

    public MainFrame() {

        super("NetWorth Tracker");
        this.setupFrameParameters();
        this.mainTabbedPane = new MainTabbedPane();
        this.add(mainTabbedPane, BorderLayout.CENTER);

        this.addTransactionBar = new AddTransactionBar();
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
