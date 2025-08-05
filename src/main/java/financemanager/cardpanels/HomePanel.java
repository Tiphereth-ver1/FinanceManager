package financemanager.cardpanels;
import financemanager.dialogs.AddButtonDialog;
import financemanager.runnables.*;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import financemanager.ConfigLoader;
import financemanager.TransactionManager;
import financemanager.panels.BodyPanel;
import financemanager.panels.HeaderPanel;
import financemanager.panels.ToolPanel;
import financemanager.panels.TransactionTablePanel;

public class HomePanel extends JPanel {
    private JFrame frame;
    private HeaderPanel headerPanel;
    private BodyPanel bodyPanel;
    private ToolPanel toolPanel;
    private TransactionTablePanel transactionTablePanel;
    private DefaultTableModel model;
    private TransactionManager transactions;

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


    public HomePanel(JFrame frame, TransactionManager transactions, ConfigLoader configLoader) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.transactions = transactions;
        this.transactionTablePanel = new TransactionTablePanel(transactions);
        this.model = transactionTablePanel.getModel();

        this.headerPanel = new HeaderPanel();
        this.bodyPanel = new BodyPanel(transactions.netBalance());
        this.toolPanel = new ToolPanel(frame, 
        new AddButtonRunnable(this),
        new DeleteButtonRunnable(model, transactions, this),
        null
        );

        UpdateButtonRunnable updater = new UpdateButtonRunnable(toolPanel.getSortOptionSupplier(), this, transactions);
        toolPanel.setUpdateButtonRunnable(updater);


        add(headerPanel);
        add(bodyPanel);
        add(toolPanel);
        add(transactionTablePanel);


    }


}