package financemanager.panels;
import javax.swing.*;
import java.awt.*;


public class BodyPanel extends JPanel {
    private JLabel netBalanceLabel;
    public BodyPanel(double initialBalance) {
        Font memeFont = new Font("Arial", Font.ITALIC, 13);
        Font balanceFont = new Font("Arial", Font.BOLD, 25);

        setBorder(BorderFactory.createEmptyBorder( 30, 30, 30,30));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        ImageIcon image = new ImageIcon(getClass().getResource("/images/bocchi.png"));
        JLabel bocchi = new JLabel(image, SwingConstants.CENTER);
        bocchi.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel meme = new JLabel("Me looking at my balance", SwingConstants.CENTER);
        meme.setAlignmentX(Component.CENTER_ALIGNMENT);
        meme.setFont(memeFont);

        JLabel preBalanceLabel = new JLabel("Net Balance");
        preBalanceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        netBalanceLabel = new JLabel(String.format("%.2f", initialBalance), SwingConstants.CENTER);
        refreshNetBalance(initialBalance);
        netBalanceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        netBalanceLabel.setFont(balanceFont);

        add(bocchi);
        add(meme);
        add(netBalanceLabel);
    }

    public void refreshNetBalance(double newBalance) {
    if (newBalance < 0) {
        netBalanceLabel.setForeground(Color.RED);} 
    else if (newBalance > 1000) {
        netBalanceLabel.setForeground(new Color(0, 128, 0)); // dark green
    } else {
        netBalanceLabel.setForeground(Color.BLACK);
    }
        netBalanceLabel.setText(String.format("%.2f", newBalance));
    }

    
}
