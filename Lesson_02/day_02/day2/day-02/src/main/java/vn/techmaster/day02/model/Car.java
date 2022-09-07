package vn.techmaster.day02.model;

public class Car {
    private String brand;
    private String model;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public Car() {
    }
}
