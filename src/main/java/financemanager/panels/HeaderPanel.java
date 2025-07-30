package financemanager.panels;
import javax.swing.*;
import java.awt.*;


public class HeaderPanel extends JPanel {
    public HeaderPanel() {
            setBackground(new Color(30, 30, 30)); // Dark theme background
            setPreferredSize(new Dimension(800, 100));
            setLayout(new BorderLayout()); // or BoxLayout for vertical stack

            // Title
            JLabel titleLabel = new JLabel("Finance Manager");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setHorizontalAlignment(JLabel.CENTER);

            // Optional subtitle
            JLabel subtitle = new JLabel("Hello broke bitch", JLabel.CENTER);
            subtitle.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
            subtitle.setForeground(Color.PINK);

            JPanel titleContainer = new JPanel();
            titleContainer.setLayout(new BoxLayout(titleContainer, BoxLayout.Y_AXIS));
            titleContainer.setBackground(new Color(30, 30, 30)); // match header bg
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            titleContainer.add(titleLabel);
            titleContainer.add(subtitle);

            add(titleContainer, BorderLayout.CENTER);
    }
    
}
