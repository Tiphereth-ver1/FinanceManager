package financemanager.menus;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainMenuBar extends JMenuBar {
    

    public MainMenuBar(JFrame parentFrame, Runnable SaveButtonRunnable, Runnable LoadButtonRunnable) {
        JMenu fileMenu = new JMenu("File");
        JMenu otherMenu = new JMenu("Other");
        // create labeled menu items
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem loadItem = new JMenuItem("Load");
        JMenuItem exitItem = new JMenuItem("Exit");

        saveItem.addActionListener(e -> {SaveButtonRunnable.run();});
        loadItem.addActionListener(e -> {LoadButtonRunnable.run();});
        exitItem.addActionListener(e -> {parentFrame.dispose();});

        // add menu items to menu
        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        otherMenu.add(exitItem);

        // add menu to menu bar
        this.add(fileMenu);
        this.add(otherMenu);
        parentFrame.setJMenuBar(this);
    }
    
}
