public interface ICarMechanic<T extends Car> {
    void addCar(T car);

    void returnCar(T car);

    boolean isInside(T car);
}
