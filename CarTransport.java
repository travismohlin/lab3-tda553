import java.util.ArrayList;

public class CarTransport extends RampTruck {
    private int maxLoad = 6;
    private ArrayList<Car> loadedCars = new ArrayList<>();
    
    public CarTransport() {
        setModelName("Car Transport 💯");
    }
    
    public int getCurrentLoad() {
        return loadedCars.size();
    }

    public int getMaxLoad() {
        return maxLoad;
    }

    /**
     * Lower rampt to allow loading and unload of cars. The ramp can only be lowered if the truck is stationary.
     * @throws IllegalStateException if the truck is not stationary
     */
    public void lowerRamp() {
        if (getCurrentSpeed() != 0) {
            throw new IllegalStateException("Cannot lower ramp while moving.");
        }
        setFlatbedAngle(70);
    }
    
    /**
     * @param car Car to be checked
     * @return true if the car can be loaded, false otherwiser
     * @throws IllegalStateException if the car cannot be loaded due to any of the following reasons:
     * - Ramp is not down
     * - Car is a car transport
     * - Car is larger than 5m
     * - Max load has been reached
     * - Car is not within 1 unit of distance from the truck
     */
    private boolean checkCarValidForLoading(Car car) {
        // We can check for 70 exactly since the ramp can only be fully lowered or raised.
        if (getFlatbedAngle() != 70) {
            throw new IllegalStateException("Cannot load car, ramp is not down.");
        }
        if (car instanceof CarTransport) {
            throw new IllegalStateException("Cannot load a car transport on another car transport.");
        }
        if (car.getLength() > 5) {
            throw new IllegalStateException("Cannot load a car larger than 5m.");
        }
        if (getCurrentLoad() >= getMaxLoad()) {
            throw new IllegalStateException("Cannot load more cars, max load reached.");
        }

        double dx = Math.abs(car.getX() - getX());
        double dy = Math.abs(car.getY() - getY());
        
        if (dx <= 1 && dy <= 1) {
            return true;
        }
        return false;
    }

    @Override
    public void move() {
        super.move();
        for (Car car : loadedCars) {
            car.setX(this.getX());
            car.setY(this.getY());
        }
    }
    
    public void loadCarOnTransport(Car car) {
        if (checkCarValidForLoading(car)) {
            loadedCars.add(car);
            car.setX(this.getX());
            car.setY(this.getY());
        }
    }
    public void unloadCarFromTransport(Car car) {
        if (getFlatbedAngle() != 70) {
            throw new IllegalStateException("Cannot unload car, ramp is not down.");
        }
        if (getCurrentLoad() == 0) {
            throw new IllegalStateException("Cannot unload car, no cars loaded.");
        }
        if(loadedCars.get(getCurrentLoad() - 1) != car) {
            throw new IllegalStateException("Cannot unload car, cars must be unloaded in reverse order of loading.");
        }
        loadedCars.remove(getCurrentLoad() - 1);
    }

    public ArrayList<Car> getLoadedCars() {
        return loadedCars;
    }
}

