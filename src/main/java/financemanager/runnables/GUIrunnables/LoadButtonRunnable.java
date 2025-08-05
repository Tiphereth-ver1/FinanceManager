package financemanager.runnables.GUIrunnables;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import financemanager.ConfigLoader;
import financemanager.TransactionManager;
import financemanager.cardpanels.HomePanel;

public class LoadButtonRunnable implements Runnable {
    private final FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files", "csv");
    private JFrame parentFrame;
    private TransactionManager transactions;
    private HomePanel homePanel;
    private ConfigLoader configLoader;

    public LoadButtonRunnable(JFrame parentFrame, TransactionManager transactions, HomePanel homePanel, ConfigLoader configLoader) {
        this.parentFrame = parentFrame;
        this.transactions = transactions;
        this.homePanel = homePanel;
        this.configLoader = configLoader;
    }

    @Override
    public void run() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(parentFrame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            configLoader.set("default.csv",file.getPath());
            configLoader.save(); 
            transactions.readFromCSVFile(file.getAbsolutePath());
            homePanel.refreshTransactionTable();
            homePanel.refreshNetBalance();
            }
    }

}