package model;

import java.util.ArrayList;

public class CarMechanic<T extends Car> implements ICarMechanic<T> {
    private final int maxCars;

    private int x;
    private int y;
    private int width;
    private int height;

    private final Class<T> acceptedType;

    private final ArrayList<T> cars = new ArrayList<>();

    public CarMechanic(int maxCars) {
        this.maxCars = maxCars;
        this.acceptedType = null;
    }

    public CarMechanic(int x, int y, int width, int height, int maxCars, Class<T> acceptedType) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.maxCars = maxCars;
        this.acceptedType = acceptedType;
    }

    public boolean isInside(Positionable positionable) {
        return positionable.getX() >= x && positionable.getX() <= x + width && positionable.getY() >= y && positionable.getY() <= y + height;
    }

    public boolean canAccept(Car car) {
        return acceptedType.isInstance(car) &&
                cars.size() < maxCars;
    }

    @Override
    public void addCar(Car car) {
        if (!canAccept(car)) {
            throw new IllegalArgumentException("Car not accepted");
        }
        cars.add(acceptedType.cast(car));
    }



    @Override
    public void returnCar(T car) {
        if (cars.contains(car)) {
            cars.remove(car);
            return;
        }
        throw new IllegalStateException("model.Car not in this mechanic shop");
    }

}


 