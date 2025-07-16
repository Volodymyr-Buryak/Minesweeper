package main.java;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class GameUI extends JFrame {

    private JPanel panel;
    private JLabel label;

    private static final int ROWS = 0;
    private static final int COLUMNS = 0;
    private static final int IMAGE_SIZE = 0;
    private static final int MINE_COUNT = 0;

    public GameUI() {
        initializeJPanel();
        initializeJFrame();
    }

    private void initializeJPanel(){
        // Panel використовується для розміщення елементів інтерфейсу у вікні JFrame.
        panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(getImage("bomb.png"), 0,0, this);
            }
        };
        panel.setPreferredSize(new Dimension(500, 300));
        add(panel);
    }

    private  void initializeJFrame() {
        setTitle("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private Image getImage (String iconName) {

        if (iconName == null || iconName.isBlank() || !iconName.endsWith(".png")){
            throw new IllegalArgumentException("Icon name cannot be null, blank, or end with .png");
        }

        URL imgURL = getClass().getResource("/img/" + iconName.toLowerCase().strip());

        if (imgURL != null) {
            return new ImageIcon(imgURL).getImage();
        }

        throw new IllegalArgumentException("Image not found: " + iconName);
    }
}
