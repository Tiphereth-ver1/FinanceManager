package financemanager;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TransactionManager {
    private List<Transaction> transactions;
    
    public TransactionManager() {
        this.transactions = new ArrayList<Transaction>();
    }

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public void removeTransaction(Transaction t) {
        transactions.remove(t);
    }
    public void removeTransaction(int i) {
        transactions.remove(i);
    }



    public Object[][] getMatrix() {
        Object[][] data = new Object[transactions.size()][5];
        for (int i = 0; i < transactions.size(); i++) {
            data[i] = transactions.get(i).getTransactionAsArray();
        }
        return data;
    }

    public void sortTransactions(SortMode sorter) {
        switch (sorter) {
            case DATE_ASC:
                transactions.sort(Comparator.comparing(Transaction::getDate));
                break;
            case DATE_DESC:
                transactions.sort(Comparator.comparing(Transaction::getDate).reversed());
                break;
            case VALUE_ASC:
                transactions.sort(Comparator.comparing(Transaction::getValue));
                break;
            case VALUE_DESC:
                transactions.sort(Comparator.comparing(Transaction::getValue).reversed());
        }
    }

    public void editTransaction() {
        //will add at some later date
    }

    public float netBalance(){
        float total = 0;
        for (Transaction t: transactions) {
            total += t.getBalanceEffect();
        }
        return total; 
    }

    public void printAllTransactions() {
    for (Transaction t : transactions) {
        t.printTransactionDetails();
    }
    System.out.printf("Current total balance: %f\n", netBalance());
}

public void clearAllTransactions() {
    for (Transaction t: transactions) {
        transactions.remove(t);
    }
}

public void saveToCSV(String filename) {
    try (PrintWriter pwriter = new PrintWriter(new FileWriter(filename))) {
        pwriter.println("Date,Type,Value,Description");
        for (Transaction t: transactions) {
            pwriter.printf("%s,%s,%f,%s\n",
            t.getDateString(),
            t.getTransactionType().toString(),
            t.getValue(),
            t.getDescription().replace(","," "));
        }
    }
    catch (IOException e) {
        e.printStackTrace();
    }
}

public void readFromCSV(String filename) {
    transactions.clear();
    try (InputStream input = getClass().getResourceAsStream("/" + filename);
        BufferedReader breader = new BufferedReader(new InputStreamReader(input))) {
        String line;
        boolean skipHeader = true;
        while ((line = breader.readLine()) != null) {
            if (skipHeader) {
                skipHeader = false;
                continue;
            }
            String[] composite = line.split(",", -1);
            String date = composite[0];
            String type = composite[1];
            Float value = Float.parseFloat(composite[2]);
            String description = composite[3];
            transactions.add(new Transaction(date, type, value, description));
        }
    }
    catch (IOException | IllegalArgumentException e) {
        e.printStackTrace();
    }
    catch (Exception e) {
        e.printStackTrace();
    }
}

public void readFromCSVPath(String filePath) {
    File file = new File(filePath);
    if (!file.exists()) {
        System.err.println("CSV file not found at: " + filePath);
        return;
    }

    transactions.clear();
    try (BufferedReader breader = new BufferedReader(new FileReader(file))) {
    String line;
    boolean skipHeader = true;
    while ((line = breader.readLine()) != null) {
        if (skipHeader) {
            skipHeader = false;
            continue;
        }
        String[] composite = line.split(",", -1);
        String date = composite[0];
        String type = composite[1];
        Float value = Float.parseFloat(composite[2]);
        String description = composite[3];
        transactions.add(new Transaction(date, type, value, description));
        }
    }
        catch (IOException | IllegalArgumentException e) {
        e.printStackTrace();
    }
    catch (Exception e) {
        e.printStackTrace();
    }
}


public void readFromCSVFile(String filename) {
    transactions.clear();
    try (BufferedReader breader = new BufferedReader(new FileReader(filename))) {
        String line;
        boolean skipHeader = true;
        while ((line = breader.readLine()) != null) {
            if (skipHeader) {
                skipHeader = false;
                continue;
            }
            String[] composite = line.split(",", -1);
            String date = composite[0];
            String type = composite[1];
            Float value = Float.parseFloat(composite[2]);
            String description = composite[3];
            transactions.add(new Transaction(date, type, value, description));
        }
    }
    catch (IOException | IllegalArgumentException e) {
        e.printStackTrace();
    }
    catch (Exception e) {
        e.printStackTrace();
    }
}


}