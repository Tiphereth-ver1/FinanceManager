package financemanager;
import com.formdev.flatlaf.FlatDarkLaf;

import financemanager.dialogs.AddButtonDialog;
import financemanager.panels.*;
import financemanager.runnables.*;
import financemanager.menus.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GUI {
    private TransactionManager transactions;
    private ConfigLoader configLoader;
    private DefaultTableModel model;
    private JFrame frame;

    private HeaderPanel headerPanel;
    private BodyPanel bodyPanel;
    private ToolPanel toolPanel;
    private TransactionTablePanel transactionTablePanel;
    private JMenuBar menuBar;

    public void refreshNetBalance() {
        bodyPanel.refreshNetBalance(transactions.netBalance());
    }

    public void showAddTransactionDialog() {
    AddButtonDialog dialog = new AddButtonDialog(
        frame,
        transactions,
        () -> {
            refreshTransactionTable();
            refreshNetBalance();
        }
    );
    dialog.setVisible(true);
}

    public void refreshTransactionTable() {
    model.setRowCount(0); // Clear current rows

    Object[][] data = transactions.getMatrix(); // Pull current data
    for (Object[] row : data) {
        model.addRow(row); // Add each row to the table
    }
}


    public GUI(TransactionManager transactions, ConfigLoader configLoader) {
        this.transactions = transactions;
        this.configLoader = configLoader;

        JFrame frame = new JFrame("Finance Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 650);
        frame.setTitle("Finance Manager");
        frame.setIconImage(new ImageIcon(getClass().getResource("/icon.jpg")).getImage());

        
        this.bodyPanel = new BodyPanel(transactions.netBalance());

        JPanel mainPanel = new JPanel();
        // mainPanel.setBackground(new Color(255, 191, 243)); // Light gray
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.transactionTablePanel = new TransactionTablePanel(this.transactions);
        this.model = transactionTablePanel.getModel();

        this.headerPanel = new HeaderPanel();
        this.bodyPanel = new BodyPanel(this.transactions.netBalance());
        this.toolPanel = new ToolPanel(frame, 
        new AddButtonRunnable(this),
        new DeleteButtonRunnable(model, this.transactions, this),
        null,
        new SaveButtonRunnable(frame, this.transactions),
        new LoadButtonRunnable(frame, this.transactions, this, this.configLoader)
        );

        UpdateButtonRunnable updater = new UpdateButtonRunnable(toolPanel.getSortOptionSupplier(), this, this.transactions);
        toolPanel.setUpdateButtonRunnable(updater);

        this.menuBar = new MainMenuBar(frame, new SaveButtonRunnable(frame, this.transactions),
        new LoadButtonRunnable(frame, this.transactions, this, this.configLoader));

        mainPanel.add(headerPanel);
        mainPanel.add(bodyPanel);
        mainPanel.add(toolPanel);
        mainPanel.add(transactionTablePanel);
        frame.add(mainPanel);
        frame.setJMenuBar(this.menuBar);

        frame.setVisible(true);

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
            // UIManager.put("TableHeader.background", new Color(50, 50, 50));
            // UIManager.put("TableHeader.foreground", Color.WHITE);
            // UIManager.put("TableHeader.font", new Font("Arial", Font.BOLD, 14));
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
