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
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();
    CarMechanic<Volvo240> volvo240CarMechanic = new CarMechanic<Volvo240>(300, 300, 120, 120, 10);

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240());

        Saab95 sabb = new Saab95();
        sabb.setY(100);
        cc.cars.add(sabb);

        Scania scania = new Scania();
        scania.setY(200);
        cc.cars.add(scania);


        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void stopEngine() {
        for (Car car : cars) {
            car.stopEngine();
        }
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
            for (Car car : cars) {
                car.move();
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

                if (car instanceof Volvo240 && volvo240CarMechanic.isInside(car)) {
                    volvo240CarMechanic.addCar((Volvo240) car);
                    cars.remove(car);
                    break;
                }
            }
            frame.drawPanel.repaint();
        }

    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars) {
            car.gas(gas);
        }
    }
    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Car car : cars) {
            car.brake(brake);
        }
    }
    void turboOn()  {
        for (Car car: cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOn();
            }
        }
    }
    void turboOff()  {
        for (Car car: cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOff();
            }
        }
    }
    void liftBed()  {
        for (Car car: cars) {
            if (car instanceof RampTruck) {
                ((RampTruck) car).setFlatbedAngle(70);
            }
        }
    }
    void lowerBed()  {
        for (Car car: cars) {
            if (car instanceof RampTruck) {
                ((RampTruck) car).setFlatbedAngle(0);
            }
        }
    }
    void startEngine()  {
        for (Car car: cars) {
            if (car != null) {
                ((Car) car).startEngine();
            }
        }
    }
    void turnLeft()  {
        for (Car car: cars) {
            if (car != null) {
                ((Car) car).turnLeft();
            }
        }
    }
    void turnRight()  {
        for (Car car: cars) {
            if (car != null) {
                ((Car) car).turnRight();
            }
        }
    }

}
