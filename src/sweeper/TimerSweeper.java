package sweeper;

import javax.swing.Timer;
import javax.swing.JLabel;

public final class TimerSweeper {

    private static final int MAX_TIME = 999;
    private static final int ONE_SECOND_IN_MILLISECONDS = 1000;

    private int timeElapsed;
    private boolean isRunning;
    private final Timer timer;
    private final JLabel timeLabel;

    public TimerSweeper(JLabel timerLabel) {
        this.timeLabel = timerLabel;
        timer = new Timer(ONE_SECOND_IN_MILLISECONDS, _ -> tick());
        updateDisplay();
    }

    private void tick() {
        timeElapsed++;
        updateDisplay();
        if (timeElapsed > MAX_TIME) {
            restart();
        }
    }

    public void start() {
        if (!isRunning) {
            isRunning = true;
            timer.start();
        }
    }

    public void stop() {
        if (isRunning) {
            isRunning = false;
            timer.stop();
        }
    }

    public void reset() {
        stop();
        timeElapsed = 0;
        updateDisplay();
    }

    public void restart() {
        reset();
        start();
    }

    private void updateDisplay() {
        if (timeLabel != null) {
            timeLabel.setText(String.format("%03d", timeElapsed));
        }
    }
}
