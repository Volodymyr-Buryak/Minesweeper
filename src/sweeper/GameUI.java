package sweeper;

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

import java.net.URL;
import java.io.FileNotFoundException;

import java.util.Date;
import java.text.SimpleDateFormat;

import sweeper.EnumGame.Box;
import sweeper.util.game.Ranges;

public final class GameUI extends JFrame {
    private JPanel panel;
    private JLabel label;
    private final Game game;

    private static final int ROWS = 9;
    private static final int COLUMNS = 9;
    private static final int IMAGE_SIZE = 50;
    private static final int BOMB_COUNT = 4;

    public GameUI() {
        initializeImageBox();
        game = new Game(COLUMNS, ROWS, BOMB_COUNT);
        game.start();
        initializeJLabel();
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
                System.out.println("paintComponent вызван: " +  new SimpleDateFormat("HH:mm:ss").format(new Date()));
                super.paintComponent(g);
                for (Coordinate coordinate : Ranges.getAllCoordinates()){
                    g.drawImage((Image) game.getBox(coordinate).getImage(),
                            coordinate.getX() * IMAGE_SIZE,
                            coordinate.getY() * IMAGE_SIZE, this);
                }
            }
        };

        panel.addMouseListener(new Mouse(game, panel, label, IMAGE_SIZE));
        panel.setPreferredSize(new Dimension(
                Ranges.getSize().getX() * IMAGE_SIZE,
                Ranges.getSize().getY() * IMAGE_SIZE));

        add(panel);
    }

    private void initializeJLabel () {
        label = new JLabel(game.getMassage());
        label.setFont(new Font("Tahoma", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);
        System.out.println("JLabel initialized: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
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
