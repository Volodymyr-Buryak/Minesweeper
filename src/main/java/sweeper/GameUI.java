package main.java.sweeper;

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

    private final Game game;
    private JPanel panel;
    private JLabel label;

    private static final int ROWS = 9;
    private static final int COLUMNS = 9;
    private static final int IMAGE_SIZE = 50;
    private static final int MINE_COUNT = 10;

    public GameUI() {
        initializeImageBox();
        game = new Game(COLUMNS, ROWS, MINE_COUNT);
        game.start();
        initializeJPanel();
        initializeJFrame();
    }

    private void initializeImageBox(){
        try {
            for (Box box : Box.values()){
                box.setImage(loadImageFromResources(box.name()));
            }
            setIconImage(loadImageFromResources("icon"));
        } catch (FileNotFoundException e){
            showErrorAndExit(e.getMessage());
        }
    }

    private Image loadImageFromResources (String iconName) throws FileNotFoundException {
        URL imgURL = getClass().getResource("/img/" + iconName.toLowerCase().strip() + ".png");
        if (imgURL == null){
            throw new FileNotFoundException("Image not found: " + iconName);
        }
        return new ImageIcon(imgURL).getImage();
    }

    private void showErrorAndExit (String message) {
        JOptionPane.showMessageDialog(this, message, "Error loading resource", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }
    
    private void initializeJPanel(){
        // Panel використовується для розміщення елементів інтерфейсу у вікні JFrame.
        panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coordinate coordinate : Ranges.getAllCoordinates()){
                    g.drawImage((Image) game.getBox(coordinate).getImage(),
                            coordinate.getX() * IMAGE_SIZE,
                            coordinate.getY() * IMAGE_SIZE, this);
                }
            }
        };
        panel.setPreferredSize(new Dimension(
                Ranges.getSize().getX() * IMAGE_SIZE,
                Ranges.getSize().getY() * IMAGE_SIZE));
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
}
