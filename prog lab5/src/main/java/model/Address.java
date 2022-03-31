package model;

import java.util.Objects;

public class Address {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(getZipCode(), address.getZipCode()) && Objects.equals(getTown(), address.getTown());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getZipCode(), getTown());
    }

    @Override
    public String toString() {
        return "Address{" +
                "zipCode='" + zipCode + '\'' +
                ", town=" + town +
                '}';
    }
}
