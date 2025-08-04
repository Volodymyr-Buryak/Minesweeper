import sweeper.GameUI;
import javax.swing.SwingUtilities;

public final class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameUI::new);
    }
}
