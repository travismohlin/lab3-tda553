package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GameModel {
    private final ArrayList<Car> cars = new ArrayList<>();
    private final ArrayList<ModelObserver> observers = new ArrayList<>();
    private CarMechanic<Volvo240> volvoWorkshop = new CarMechanic<>(300, 300, 120, 120, 10, Volvo240.class);

    private int maxX;
    private int maxY;

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void addObserver(ModelObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (ModelObserver observer : observers) {
            observer.modelUpdated();
        }
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void update(int width, int height) {
        this.maxX = width - 100;
        this.maxY = height - 300;

        moveCars();
        handleWallCollisions();
        handleWorkshops();
        notifyObservers();
    }

    private void moveCars() {
        for (Car car : cars) {
            car.move();
        }
    }

    private void handleWallCollisions() {
        for (Car car : cars) {

            int x = (int) Math.round(car.getX());
            int y = (int) Math.round(car.getY());

            if (x < 0 || x > maxX) {
                turnAndRestartCar(car);
            }

            if (y < 0 || y > maxY) {
                turnAndRestartCar(car);
            }

            car.setX(Math.max(0, Math.min(x, maxX)));
            car.setY(Math.max(0, Math.min(y, maxY)));
        }
    }

    private void handleWorkshops() {
        Iterator<Car> iterator = cars.iterator();

        while (iterator.hasNext()) {
            Car car = iterator.next();

            if (volvoWorkshop.canAccept(car) &&
                    volvoWorkshop.isInside(car)) {

                volvoWorkshop.addCar(car);
                iterator.remove();
            }
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
            c.setTurboOn();
        }
    }

    public void setTurboOff() {
        for (Car c : cars) {
            c.setTurboOff();
        }
    }

    public void liftBed()  {
        for (Car car: cars) {
            if (car instanceof FlatBedCapable flatbed) {
                flatbed.setFlatbedAngle(70);
            }
        }
    }

    public void lowerBed()  {
        for (Car car: cars) {
            if (car instanceof FlatBedCapable flatbed) {
                flatbed.setFlatbedAngle(0);
            }
        }
    }

    public void startEngine()  {
        for (Car car: cars) {
            if (car != null) {
                car.startEngine();
            }
        }
    }

    public void turnLeft()  {
        for (Car car: cars) {
            if (car != null) {
                car.turnLeft();
            }
        }
    }

    public void turnRight()  {
        for (Car car: cars) {
            if (car != null) {
               car.turnRight();
            }
        }
    }

    public void stopEngine() {
        for (Car car : cars) {
            car.stopEngine();
        }
    }

    public void turnAndRestartCar(Car car) {
        car.stopEngine();
        car.turnRight();
        car.turnRight();
        car.startEngine();
        car.move();
    }

    public boolean canNotAddCar() {
        return !(cars.size() <= 5);
    }


    public void addSelectedCar(String selectedCar) {
        if (canNotAddCar()) return;
        for (Class<? extends Car> carClass : CarFactory.getCarTypes()) {
            if (carClass.getSimpleName().equals(selectedCar)) {
                Car car = CarFactory.create(carClass);
                addCar(car);
                break;
            }
        }
    }

    public void addRandomCar() {
        if (canNotAddCar()) return;
        Random random = new Random();
        Class<? extends Car> randomCar = CarFactory.getCarTypes().get(random.nextInt(CarFactory.getCarTypes().size()));
        addCar(CarFactory.create(randomCar));
    }

    public void removeLastCar() {
        if (cars.isEmpty()) {
            return;
        }
        cars.removeLast();
    }

}
