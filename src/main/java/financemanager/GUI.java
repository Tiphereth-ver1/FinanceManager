package financemanager;
import com.formdev.flatlaf.FlatDarkLaf;

import financemanager.cardpanels.*;
import financemanager.menus.*;
import financemanager.runnables.GUIrunnables.*;

import javax.swing.*;
import java.awt.*;

public class GUI {
    private TransactionManager transactions;
    private ConfigLoader configLoader;
    private JMenuBar menuBar;
    private HomePanel homePanel;
    private GraphPanel graphPanel;

    public GUI(TransactionManager transactions, ConfigLoader configLoader) {
        this.transactions = transactions;
        this.configLoader = configLoader;

        JFrame frame = new JFrame("Finance Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 650);
        frame.setTitle("Finance Manager");
        frame.setIconImage(new ImageIcon(getClass().getResource("/images/icon.jpg")).getImage());

        this.homePanel = new HomePanel(frame, transactions, configLoader);
        this.graphPanel = new GraphPanel();

        JPanel mainPanel = new JPanel(new CardLayout());
        mainPanel.add(homePanel, "Home");
        mainPanel.add(graphPanel, "Graph");
        

        frame.add(mainPanel);
        this.menuBar = new MainMenuBar(frame, new SaveButtonRunnable(frame, this.transactions),
        new LoadButtonRunnable(frame, this.transactions, homePanel, this.configLoader));

        frame.setJMenuBar(this.menuBar);
        frame.setVisible(true);

        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "Home");


    }
    public static void main(String[] args) {
        try {
            // Set native OS Look and Feel
            UIManager.setLookAndFeel(new FlatDarkLaf());
            UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 14));
            UIManager.put("Button.foreground", Color.BLACK);
            UIManager.put("Button.focus", new Color(140, 114, 138));
            UIManager.put("Button.background", new Color(255, 214, 252));
            UIManager.put("Panel.background", new Color(40, 40, 40));
            UIManager.put("Button.select", new Color(140, 114, 138));
            UIManager.put("TextField.background", new Color(40, 40, 40));
            UIManager.put("TextField.foreground", Color.WHITE);
            UIManager.put("Table.background", new Color(30, 30, 30));
            UIManager.put("Table.foreground", Color.WHITE);
            UIManager.put("Table.selectionBackground", new Color(70, 70, 120));
            UIManager.put("Table.gridColor", Color.WHITE);
            UIManager.put("TableHeader.background", new Color(50, 50, 50));
            UIManager.put("TableHeader.foreground", Color.WHITE);
            UIManager.put("TableHeader.font", new Font("Arial", Font.BOLD, 14));
            UIManager.put("ScrollBar.thumb", new Color(255, 166, 252));

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Then run your GUI
        javax.swing.SwingUtilities.invokeLater(() -> {
            TransactionManager transactions = new TransactionManager();
            ConfigLoader configLoader = new ConfigLoader("config/config.properties");
            transactions.readFromCSVPath(configLoader.get("default.csv"));
            new GUI(transactions, configLoader);
        });
    }
}
