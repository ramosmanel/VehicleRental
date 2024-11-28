package entities;

import entities.enums.Category;

public class Vehicle {
    private int id;
    private String plate;
    private String brand;
    private String model;
    private String color;
    private int manufacturingYear;
    private char category;

    public Vehicle(int id, String plate, String brand, String model, String color, int manufacturingYear, char category) {
        this.id = id;
        this.plate = plate;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.manufacturingYear = manufacturingYear;
        this.category = category;
    }

    public int getId() {
        return id;
    }
}
