package model;

/**
 * A truck with a flatbed that can be raised and lowered. The truck cannot move while the flatbed is not fully raised.
 * The truck can have its turbo on or off, which affects the speed factor. Turbo factor is 2.5.
 */
public abstract class RampTruck extends Car implements FlatBedCapable {
    private double flatbedAngle;
    private boolean turboOn;

    public boolean getTurboStatus() {return turboOn;}

    public double getFlatbedAngle() {
        return flatbedAngle;
    }

    /**
     * Raise ramp fully.
     */
    public void raiseRamp() { flatbedAngle = 0; }

    /**
     * Set the angle of the flatbed.
     * @param angle Angle between 0 and 70 degrees
     * @throws IllegalStateException if the angle is out of bounds or if the truck is not stationary
     */
    public void setFlatbedAngle(double angle) {
        if (angle > 70 || angle < 0 || getCurrentSpeed() != 0) {
            throw new IllegalStateException("Flatbed angle must be between 0 and 70 degrees, and the truck must be stationary.");
        }
        this.flatbedAngle = angle;
    }

    public void setTurboOn(){
	    turboOn = true;
    }
    
    public void setTurboOff(){ turboOn = false; }

    public double speedFactor(){
        double turbo = 1;
        if(getTurboStatus()) turbo = 2.5;
        return getEnginePower() * 0.01 * turbo;
    }

     public void incrementSpeed(double amount){
        if (getFlatbedAngle() != 0) {
            throw new IllegalStateException("Cannot increment speed while flatbed is not at 0 degrees.");
        } 
        setCurrentSpeed(getCurrentSpeed() + speedFactor() * amount);
    }

    public void decrementSpeed(double amount){
        if (getFlatbedAngle() != 0) {
            throw new IllegalStateException("Cannot decrement speed while flatbed is not at 0 degrees.");
        } 
        setCurrentSpeed(getCurrentSpeed() - speedFactor() * amount);
    }
}
