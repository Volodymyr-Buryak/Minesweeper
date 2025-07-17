package main.java;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;

import java.io.FileNotFoundException;
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
                try {
                    g.drawImage(getImage("sау.png"), 0,0, this);
                } catch (FileNotFoundException e) {
                    System.out.println("Error loading image: " + e.getMessage());
                    showErrorAndExit(e.getMessage());
                }
            }
        };
        panel.setPreferredSize(new Dimension(500, 300));
        add(panel);
    }

    private void showErrorAndExit (String message) {
        JOptionPane.showMessageDialog(this, message, "Error loading resource", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }

    private  void initializeJFrame() {
        setTitle("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private Image getImage (String iconName) throws FileNotFoundException {
        if (iconName == null || iconName.isBlank() || !iconName.endsWith(".png")){
            throw new IllegalArgumentException("Icon name cannot be null, blank, or end with .png");
        }

        URL imgURL = getClass().getResource("/img/" + iconName.toLowerCase().strip());
        if (imgURL == null){
            throw new FileNotFoundException("Image not found: " + iconName);
        }

        return new ImageIcon(imgURL).getImage();
    }
}
