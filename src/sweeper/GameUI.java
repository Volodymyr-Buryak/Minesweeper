package sweeper;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import sweeper.util.game.Ranges;
import sweeper.util.game.ImageHandler;

public final class GameUI extends JFrame {

    private final Game game;
    private JLabel flagsCount;
    private JButton restartButton;
    private TimerSweeper timerSweeper;
    private JPanel topJPanel;
    private JPanel bottomJPanel;

    private static final int ROWS = 9;
    private static final int COLUMNS = 9;
    private static final int IMAGE_SIZE = 50;
    private static final int BOMB_COUNT = 8;

    public GameUI() {
        ImageHandler.initializeImageEnum();
        setIconImage(ImageHandler.getGameImageIcon("icon").getImage());
        game = new Game(COLUMNS, ROWS, BOMB_COUNT);
        game.start();
        initializeTopJPanel();
        initializeBottomJPanel();
        initializeJFrame();
    }

    public static void showErrorAndExit (String message) {
        JOptionPane.showMessageDialog(null, message, "Error loading resource", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }

    private void initializeTopJPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        topJPanel = new JPanel(new GridBagLayout());
        topJPanel.setBackground(new Color(158, 158, 158));
        topJPanel.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));

        initializeTimerLabel(gbc);
        initializeRestartButton(gbc);
        initializeFlagsCountLabel(gbc);

        add(topJPanel, BorderLayout.NORTH);
    }

    private void initializeTimerLabel(GridBagConstraints gbc) {
        JLabel timer = new JLabel(ImageHandler.getGameImageIcon("timer"), SwingUtilities.CENTER);
        timer.setFont(new Font("Tahoma", Font.BOLD, 20));
        timerSweeper = new TimerSweeper(timer);
        timerSweeper.start();

        gbc.gridx = 2;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.EAST;
        topJPanel.add(timer, gbc);
    }

    private void initializeRestartButton(GridBagConstraints gbc) {
        restartButton = new JButton();
        restartButton.setBorderPainted(false);
        restartButton.setFocusPainted(false);
        restartButton.setContentAreaFilled(false);
        game.updateButtonIconOnGame(restartButton);

        restartButton.addActionListener(_ -> restartGame());

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 25, 0, 0);

        topJPanel.add(restartButton, gbc);
    }

    private void restartGame() {
        game.start();
        game.updateButtonIconOnGame(restartButton);
        flagsCount.setText(Integer.toString(game.getRemainingFlags()));
        game.restartTimer(timerSweeper);
        bottomJPanel.repaint();
    }

    private void initializeFlagsCountLabel(GridBagConstraints gbc) {
        flagsCount = new JLabel(ImageHandler.getGameImageIcon("flag"), SwingUtilities.CENTER);
        flagsCount.setFont(new Font("Tahoma", Font.BOLD, 20));
        flagsCount.setHorizontalTextPosition(JLabel.RIGHT);
        flagsCount.setVerticalTextPosition(JLabel.CENTER);
        flagsCount.setText(Integer.toString(game.getRemainingFlags()));

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 0, 0);
        topJPanel.add(flagsCount, gbc);
    }

    private void initializeBottomJPanel(){
        bottomJPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coordinate coordinate : Ranges.getAllCoordinates()){
                    g.drawImage((Image) game.getBox(coordinate).getImage(),
                            coordinate.x() * IMAGE_SIZE,
                            coordinate.y() * IMAGE_SIZE, this);
                }
            }
        };
        bottomJPanel.addMouseListener(new Mouse(game, bottomJPanel, flagsCount, restartButton, IMAGE_SIZE, timerSweeper));
        bottomJPanel.setPreferredSize(new Dimension(
                Ranges.getSize().x() * IMAGE_SIZE,
                Ranges.getSize().y() * IMAGE_SIZE));
        add(bottomJPanel, BorderLayout.CENTER);
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
