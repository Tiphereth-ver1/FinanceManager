package financemanager.runnables;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import financemanager.ConfigLoader;
import financemanager.GUI;
import financemanager.TransactionManager;

public class LoadButtonRunnable implements Runnable {
    private final FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files", "csv");
    private JFrame parentFrame;
    private TransactionManager transactions;
    private GUI guiRef;
    private ConfigLoader configLoader;

    public LoadButtonRunnable(JFrame parentFrame, TransactionManager transactions, GUI guiRef, ConfigLoader configLoader) {
        this.parentFrame = parentFrame;
        this.transactions = transactions;
        this.guiRef = guiRef;
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
            guiRef.refreshTransactionTable();
            guiRef.refreshNetBalance();
            }
    }

}