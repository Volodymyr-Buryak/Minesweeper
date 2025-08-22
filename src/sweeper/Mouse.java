package sweeper;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public final class Mouse extends MouseAdapter {
    private final Game game;
    private final JPanel panel;
    private final int imageSize;
    private final JLabel flagsCount;
    private final JButton restartButton;
    private final TimerSweeper timerSweeper;

    public Mouse(Game game, JPanel panel, JLabel flagsCount, JButton restartButton, int imageSize, TimerSweeper timerSweeper){
        this.game = game;
        this.panel = panel;
        this.flagsCount = flagsCount;
        this.imageSize = imageSize;
        this.timerSweeper = timerSweeper;
        this.restartButton = restartButton;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int positionX = e.getX() / imageSize, positionY = e.getY() / imageSize;
        Coordinate coordinate = new Coordinate(positionX, positionY);
        switch (e.getButton()){
            case MouseEvent.BUTTON1 -> game.pressLeftButton(coordinate);
            case MouseEvent.BUTTON3 -> game.pressRightButton(coordinate);
        }
        flagsCount.setText(Integer.toString(game.getRemainingFlags()));
        game.updateButtonIconOnGame(restartButton);
        game.stopTimer(timerSweeper);
        panel.repaint();
    }
}
