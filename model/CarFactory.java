package model;

import java.util.ArrayList;
import java.util.List;

public class CarFactory {
    private static final ArrayList<Class<? extends Car>> carList = new ArrayList<>();

    public static void register(Class<? extends Car> carClass) {
        carList.add(carClass);
    }

    public static ArrayList<Class<? extends Car>> getCarTypes() {
        return carList;
    }

    public static Car create(Class<? extends Car> carClass) {
        try {
            return carClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
 }
