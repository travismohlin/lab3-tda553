import javax.swing.event.TableModelListener;
import java.util.ArrayList;

public class GameModel {
    private final ArrayList<Car> cars = new ArrayList<>();

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void update() {
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            car.move();
        }
    }

    // Calls the gas method for each car once
    public void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars) {
            car.gas(gas);
        }
    }

    public void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Car car : cars) {
            car.brake(brake);
        }
    }

    public void setTurboOn() {
        for (Car c : cars) {
            if (c instanceof TurboCapable t) {
                t.setTurboOn();
            }
        }
    }

    public void setTurboOff() {
        for (Car c : cars) {
            if (c instanceof TurboCapable t) {
                t.setTurboOff();
            }
        }
    }

    public void liftBed()  {
        for (Car car: cars) {
            if (car instanceof RampTruck) {
                ((RampTruck) car).setFlatbedAngle(70);
            }
        }
    }

    public void lowerBed()  {
        for (Car car: cars) {
            if (car instanceof RampTruck) {
                ((RampTruck) car).setFlatbedAngle(0);
            }
        }
    }

    public void startEngine()  {
        for (Car car: cars) {
            if (car != null) {
                ((Car) car).startEngine();
            }
        }
    }

    public void turnLeft()  {
        for (Car car: cars) {
            if (car != null) {
                ((Car) car).turnLeft();
            }
        }
    }

    public void turnRight()  {
        for (Car car: cars) {
            if (car != null) {
                ((Car) car).turnRight();
            }
        }
    }

    public void stopEngine() {
        for (Car car : cars) {
            car.stopEngine();
        }
    }
}
