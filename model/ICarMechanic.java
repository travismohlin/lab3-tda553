package model;

public interface ICarMechanic<T extends Car> {
    void addCar(Car car);
    void returnCar(T car);
}
