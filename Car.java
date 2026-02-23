import java.awt.*;
import java.util.Objects;
import java.math.*;

public abstract class Car implements Movable {
    private int nrDoors; // Number of doors on the car
    private double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private String modelName; // The car model name
    private double lenght;

    public int getNrDoors(){
        return nrDoors;
    }

    public void setNrDoors(int nrDoors) {this.nrDoors = nrDoors;};

    public double getEnginePower(){ return enginePower; }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public void setCurrentSpeed(double currentSpeed) throws IllegalArgumentException {
        if (currentSpeed > getEnginePower() || currentSpeed < 0) {
            throw new IllegalArgumentException("Value must be above 0 or lower to engine power.");
        }
        this.currentSpeed = currentSpeed;
    }

    public Color getColor(){ return color; }

    public void setColor(Color clr){ color = clr; }

    public void startEngine(){
        currentSpeed = 0.1;
    }

    public void setEnginePower(double enginePower) {this.enginePower = enginePower;}

    public void stopEngine(){
        currentSpeed = 0;
    }

    public double getLength() { return lenght; }
    public void setLength(double length) { this.lenght = length; }

    public void setModelName(String modelName) {this.modelName = modelName;}

    public String getModelName() {return this.modelName;}

    public abstract double speedFactor();

    public abstract void incrementSpeed(double amount);

    public abstract void decrementSpeed(double amount);

    private double x = 0;
    private double y = 0;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double[] getPosition() {
        return new double[]{x, y};
    }

    protected void setX(double x) {
        this.x = x;
    }

    protected void setY(double y) {
        this.y = y;
    }

    public enum Direction {
            NORTH,
            SOUTH,
            EAST,
            WEST
    }

    private Direction direction = Direction.NORTH;

    public Direction getDirection() { return direction; }

    protected void setDirection(Direction direction) {this.direction = direction;}

    public void move() {
        if(getDirection() == Direction.NORTH){
            y += getCurrentSpeed();
        }
        if(getDirection() == Direction.SOUTH){
            y -= getCurrentSpeed();
        }
        if(getDirection() == Direction.EAST){
            x += getCurrentSpeed();
        }
        if(getDirection() == Direction.WEST){
            x -= getCurrentSpeed();
        }
    }

    public void turnLeft() {
        switch (getDirection()) {
            case Direction.NORTH -> setDirection(Direction.WEST);
            case Direction.SOUTH -> setDirection(Direction.EAST);
            case Direction.EAST -> setDirection(Direction.NORTH);
            case Direction.WEST -> setDirection(Direction.SOUTH);
        }
    }

    public void turnRight() {
        switch (getDirection()) {
            case Direction.NORTH -> setDirection(Direction.EAST);
            case Direction.SOUTH -> setDirection(Direction.WEST);
            case Direction.EAST -> setDirection(Direction.SOUTH);
            case Direction.WEST -> setDirection(Direction.NORTH);
        }
    }

    // Only accepts values in range (1, 0)🤓. Can not decrease speed
    public void gas(double amount) throws IllegalArgumentException{
        if (amount < 0.0 || amount > 1.0) {
            throw new IllegalArgumentException("Value must be between 0 and 1");
        }
        incrementSpeed(amount);
    }

    // Only accepts values in range (0,1)🤓. Can not increase speed
    public void brake(double amount) throws IllegalArgumentException {
        if (amount < 0.0 || amount > 1.0) {
            throw new IllegalArgumentException("Value must be between 0 and 1");
        }
        decrementSpeed(amount);
    }
}
