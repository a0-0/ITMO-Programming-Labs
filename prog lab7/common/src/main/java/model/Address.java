package model;

import java.io.Serializable;

public class Address implements Serializable {
    private String zipCode; //Длина строки должна быть не меньше 5, Поле может быть null
    private Location town; //Поле может быть null

    public Address() {}

    public Address(String zipCode, Location town) {
        this.zipCode = zipCode;
        this.town = town;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Location getTown() {
        return town;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setTown(Location town) {
        this.town = town;
    }
}
