package main.java.sweeper;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import java.net.URL;
import java.io.FileNotFoundException;

import java.util.Date;
import java.text.SimpleDateFormat;

public class GameUI extends JFrame {

    private final Game game;
    private JPanel panel;
    private JLabel label;

    private static final int ROWS = 9;
    private static final int COLUMNS = 9;
    private static final int IMAGE_SIZE = 50;
    private static final int BOMB_COUNT = 10;

    public GameUI() {
        initializeImageBox();
        game = new Game(COLUMNS, ROWS, BOMB_COUNT);
        game.start();
        initializeJPanel();
        initializeJLabel();
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
                System.out.println("paintComponent вызван: " +  new SimpleDateFormat("HH:mm:ss").format(new Date()));
                super.paintComponent(g);
                for (Coordinate coordinate : Ranges.getAllCoordinates()){
                    g.drawImage((Image) game.getBox(coordinate).getImage(),
                            coordinate.getX() * IMAGE_SIZE,
                            coordinate.getY() * IMAGE_SIZE, this);
                }
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int positionX = e.getX() / IMAGE_SIZE, positionY = e.getY() / IMAGE_SIZE;
                Coordinate coordinate = new Coordinate(positionX, positionY);
                switch (e.getButton()){
                    case MouseEvent.BUTTON1 -> game.pressLeftButton(coordinate);
                    case MouseEvent.BUTTON3 -> game.pressRightButton(coordinate);
                    case MouseEvent.BUTTON2 -> game.start();
                    default -> System.out.println("Unknown mouse button pressed: " + e.getButton());
                }
                panel.repaint();
            }
        });

        panel.setPreferredSize(new Dimension(
                Ranges.getSize().getX() * IMAGE_SIZE,
                Ranges.getSize().getY() * IMAGE_SIZE));

        add(panel);
    }

    private void initializeJLabel () {
        label = new JLabel("Welcome!");
        label.setFont(new Font("Tahoma", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);
    }

    private void initializeJFrame() {
        setTitle("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
