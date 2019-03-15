package model;

public enum BusModel{
    MAN("MAN NG363F", 55),
    VOLVO("Volvo B7RLE", 62),
    MERCEDES("Mercedes-Benz Citaro", 88);

    String model;
    int capacity;

    BusModel(String model, int capacity) {
        this.model = model;
        this.capacity = capacity;
    }
}
