package view;

import javax.swing.*;
import java.awt.*;

// start och stop knapparna
public class ButtonPanel extends JPanel {
    private final JButton startButton = new JButton("Start all cars");
    private final JButton stopButton = new JButton("Stop all cars");

    public ButtonPanel(int frameWidth) {
        setLayout(new GridLayout(1, 2, 8, 0));
        setPreferredSize(new Dimension((frameWidth / 5) * 2, 200));

        startButton.setBackground(Color.GREEN);
        startButton.setForeground(Color.BLACK);

        stopButton.setBackground(Color.RED);
        stopButton.setForeground(Color.BLACK);

        add(startButton);
        add(stopButton);
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getStopButton() {
        return stopButton;
    }
}
