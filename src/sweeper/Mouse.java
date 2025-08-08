package sweeper;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public final class Mouse extends MouseAdapter {
    private final Game game;
    private final JPanel panel;
    private final JLabel label;
    private final int imageSize;

    public Mouse(Game game, JPanel panel, JLabel label, int imageSize){
        this.game = game;
        this.panel = panel;
        this.label = label;
        this.imageSize = imageSize;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int positionX = e.getX() / imageSize, positionY = e.getY() / imageSize;
        Coordinate coordinate = new Coordinate(positionX, positionY);
        switch (e.getButton()){
            case MouseEvent.BUTTON1 -> game.pressLeftButton(coordinate);
            case MouseEvent.BUTTON3 -> game.pressRightButton(coordinate);
            case MouseEvent.BUTTON2 -> game.start();
            default -> System.out.println("Unknown mouse button pressed: " + e.getButton());
        }
        label.setText(game.getMassage());
        panel.repaint();
    }
}
