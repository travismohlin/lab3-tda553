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
    private ICarMechanic<Volvo240> volvo240CarMechanic;

    public CarController(GameModel model) {
        this.model = model;
        volvo240CarMechanic = new CarMechanic<Volvo240>(300, 300, 120, 120, 10);
    }

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        GameModel model = new GameModel();
        CarController cc = new CarController(model);

        model.addCar(new Volvo240());

        Saab95 sabb = new Saab95();
        sabb.setY(100);
        model.addCar(sabb);

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

    private void turnAndRestartCar(Car car) {
        car.stopEngine();
        car.turnRight();
        car.turnRight();
        car.startEngine();
        car.move();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.update();
            for (int i = 0; i < model.getCars().size(); i++) {
                Car car = model.getCars().get(i);
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());

                int maxX = frame.drawPanel.getWidth() - 60; // ugly solution to remove control panel height
                int maxY = frame.drawPanel.getHeight() - 60;

                if (x < 0 || x > maxX) {
                    turnAndRestartCar(car);
                }
                if (y < 0 || y > maxY) {
                    turnAndRestartCar(car);
                }
                car.setX(Math.max(0, Math.min(x, maxX)));
                car.setY(Math.max(0, Math.min(y, maxY)));

                if (car instanceof Volvo240 volvo && volvo240CarMechanic.isInside(volvo)) {
                    volvo240CarMechanic.addCar(volvo);
                    model.getCars().remove(car);
                    i--;
                }
            }
            frame.drawPanel.repaint();
        }

    }
}
