package main.java;

import main.java.sweeper.GameUI;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameUI::new);
    }
}
