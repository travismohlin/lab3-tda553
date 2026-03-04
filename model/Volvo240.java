package model;

import java.awt.*;

public class Volvo240 extends Car {

    private final static double trimFactor = 1.25;

    public double getTrimFactor() {return trimFactor;}

    public Volvo240(){
        setNrDoors(4);
        setColor(Color.black);
        setEnginePower(100);
        setModelName("model.Movable.Volvo240");
        setLength(3);
        stopEngine();
        CarFactory.register(Volvo240.class);
    }

    public double speedFactor(){ return getEnginePower() * 0.01 * getTrimFactor();}

    public void incrementSpeed(double amount){
        setCurrentSpeed(Math.min(getCurrentSpeed() + speedFactor() * amount,getEnginePower()));
    }

    public void decrementSpeed(double amount){
        setCurrentSpeed(Math.max(getCurrentSpeed() - speedFactor() * amount,0));
    }


}