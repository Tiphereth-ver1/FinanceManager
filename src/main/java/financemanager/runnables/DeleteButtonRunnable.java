package financemanager.runnables;

import javax.swing.table.DefaultTableModel;

import financemanager.TransactionManager;
import financemanager.cardpanels.HomePanel;

public class DeleteButtonRunnable implements Runnable{
    private final DefaultTableModel model;
    private final TransactionManager transactions;
    private HomePanel homePanel;

    public DeleteButtonRunnable(DefaultTableModel model, TransactionManager transactions, HomePanel homePanel) {
        this.model = model;
        this.transactions = transactions;
        this.homePanel = homePanel;
    }

    @Override
    public void run() {
            for (int i = model.getRowCount() - 1; i >= 0; i--) {
                Boolean checked = (Boolean) model.getValueAt(i, 0);
                if (checked != null && checked) {
                    transactions.removeTransaction(i); // also remove from internal list
                    model.removeRow(i);
                }
            }
            homePanel.refreshTransactionTable(); // optional depending on how you sync
            homePanel.refreshNetBalance();    };
    }
    


