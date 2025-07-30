package financemanager.runnables;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import financemanager.TransactionManager;

public class SaveButtonRunnable implements Runnable{
    private final FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files", "csv");
    private JFrame parentFrame;
    private TransactionManager transactions;

    public SaveButtonRunnable(JFrame parentFrame, TransactionManager transactions){
        this.parentFrame = parentFrame;
        this.transactions = transactions;
    }

    public void run() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showSaveDialog(parentFrame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            transactions.saveToCSV(file.getAbsolutePath());
            }

    }

    }
