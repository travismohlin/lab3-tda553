package model;

import java.awt.*;

public class Scania extends RampTruck {
    public Scania() {
        setNrDoors(2);
        setColor(Color.black);
        setEnginePower(425);
        setTurboOff();
        setModelName("Scania");
        setLength(6);
        stopEngine();
    }
}
