package model;

import java.awt.*;

public class Saab95 extends Car {

    private boolean turboOn;

    public boolean getTurboStatus() {return turboOn;}

    public Saab95(){
        setNrDoors(2);
        setColor(Color.red);
        setEnginePower(125);
        setTurboOff();
        setModelName("model.Positionable.Saab95");
        setLength(3);
        stopEngine();
        CarFactory.register(Saab95.class);
    }

    public void setTurboOn(){
        turboOn = true;
    }

    public void setTurboOff(){ turboOn = false; }

    public double speedFactor(){
        double turbo = 1;
        if(getTurboStatus()) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }

    public void incrementSpeed(double amount){

        setCurrentSpeed(getCurrentSpeed() + speedFactor() * amount);
    }

    public void decrementSpeed(double amount){
        setCurrentSpeed(getCurrentSpeed() - speedFactor() * amount);
    }
}