package controller;

import model.*;
import view.CarView;
import view.DrawPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private final Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;

    private final GameModel model;

    public CarController(GameModel model) {
        this.model = model;
    }

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        GameModel model = new GameModel();
        CarController cc = new CarController(model);

        model.addCar(new Volvo240());

        Saab95 saab = new Saab95();
        saab.setY(100);
        model.addCar(saab);

        Scania scania = new Scania();
        scania.setY(200);
        model.addCar(scania);


        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    public ArrayList<Car> getCars() {
        return model.getCars();
    }

    public void stopEngine() {
        model.stopEngine();
    }

    public void startEngine() {
        model.startEngine();
    }

    public void gas(int amount) {
        model.gas(amount);
    }

    public void brake(int amount) {
        model.brake(amount);
    }

    public void turboOn() {
        model.setTurboOn();
    }

    public void turboOff() {
        model.setTurboOff();
    }

    public void liftBed() {
        model.liftBed();
    }

    public void lowerBed() {
        model.lowerBed();
    }

    public void turnLeft() {
        model.turnLeft();
    }

    public void turnRight() {
        model.turnRight();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.update(frame.getWidth(), frame.getHeight());
            frame.repaint();
        }

    }
}
