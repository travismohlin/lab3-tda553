import java.util.ArrayList;

public class CarMechanic<T extends Car> implements ICarMechanic<T> {
    private final int maxCars;

    private int x;
    private int y;
    private int width;
    private int height;

    private final ArrayList<Car> cars = new ArrayList<Car>();

    public CarMechanic(int maxCars) {
        this.maxCars = maxCars;
    }

    public CarMechanic(int x, int y, int width, int height, int maxCars) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.maxCars = maxCars;
    }

    public boolean isInside(Car car) {
        return car.getX() >= x && car.getX() <= x + width && car.getY() >= y && car.getY() <= y + height;
    }

    @Override
    public void addCar(Car car){
        if (maxCars > cars.size()){
            cars.add(car);
        }
        else {
            throw new IllegalStateException("Max amount of cars reached.");
        }
    }

    @Override
    public void returnCar(Car car) {
        if (cars.contains(car)) {
            cars.remove(car);
            return;
        }
        throw new IllegalStateException("Car not in this mechanic shop");
    }

}


 