package view;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

// innehaller alla andra knappar och spinnern
public class ControlPanel extends JPanel {
    private final JPanel gasPanel = new JPanel();
    private final JSpinner gasSpinner;
    private int gasAmount = 0;

    private final JLabel gasLabel = new JLabel("Amount of gas");

    private final JButton gasButton = new JButton("Gas");
    private final JButton brakeButton = new JButton("Brake");
    private final JButton turboOnButton = new JButton("Saab Turbo on");
    private final JButton turboOffButton = new JButton("Saab Turbo off");
    private final JButton liftBedButton = new JButton("Scania Lift Bed");
    private final JButton lowerBedButton = new JButton("Lower Lift Bed");
    private final JButton turnRightButton = new JButton("Turn right");
    private final JButton turnLeftButton = new JButton("Turn left");

    public ControlPanel(int frameWidth) {
        setLayout(new BorderLayout());

        SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 100, 1);
        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner) e.getSource()).getValue();
            }
        });

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(2, 4));
        buttonsPanel.add(gasButton, 0);
        buttonsPanel.add(turboOnButton, 1);
        buttonsPanel.add(liftBedButton, 2);
        buttonsPanel.add(brakeButton, 3);
        buttonsPanel.add(turboOffButton, 4);
        buttonsPanel.add(lowerBedButton, 5);
        buttonsPanel.add(turnLeftButton, 6);
        buttonsPanel.add(turnRightButton, 7);

        add(gasPanel, BorderLayout.PAGE_START);
        add(buttonsPanel, BorderLayout.CENTER);

        setPreferredSize(new Dimension((frameWidth / 2) + 4, 230));
        setBackground(Color.GRAY);
        buttonsPanel.setBackground(Color.GRAY);
        gasPanel.setBackground(Color.lightGray);
    }

    public int getGasAmount() {
        return gasAmount;
    }

    public JButton getGasButton() {
        return gasButton;
    }

    public JButton getBrakeButton() {
        return brakeButton;
    }

    public JButton getTurboOnButton() {
        return turboOnButton;
    }

    public JButton getTurboOffButton() {
        return turboOffButton;
    }

    public JButton getLiftBedButton() {
        return liftBedButton;
    }

    public JButton getLowerBedButton() {
        return lowerBedButton;
    }

    public JButton getTurnRightButton() {
        return turnRightButton;
    }

    public JButton getTurnLeftButton() {
        return turnLeftButton;
    }
}
