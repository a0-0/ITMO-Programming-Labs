package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Product implements Comparable<Product>, Serializable {

    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long price; //Значение поля должно быть больше 0
    private String partNumber; //Поле не может быть null
    private int manufactureCost;
    private UnitOfMeasure unitOfMeasure; //Поле не может быть null
    private Organization manufacturer; //Поле не может быть null
    private String username;

    public Product() {}

    protected Product(long id, String name, Coordinates coordinates, LocalDateTime creationDate, long price,
                   String partNumber, int manufactureCost, UnitOfMeasure unitOfMeasure, Organization manufacturer, String username) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate == null ? LocalDateTime.now() : creationDate;
        this.price = price;
        this.partNumber = partNumber;
        this.manufactureCost = manufactureCost;
        this.unitOfMeasure = unitOfMeasure;
        this.manufacturer = manufacturer;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public long getPrice() {
        return price;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public int getManufactureCost() {
        return manufactureCost;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public Organization getManufacturer() {
        return manufacturer;
    }

    public String getUsername() {
        return username;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public void setManufactureCost(int manufactureCost) {
        this.manufactureCost = manufactureCost;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public void setManufacturer(Organization manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return getId() == product.getId() && getPrice() == product.getPrice() && getManufactureCost() == product.getManufactureCost() && Objects.equals(getName(), product.getName()) && Objects.equals(getCoordinates(), product.getCoordinates()) && Objects.equals(getCreationDate(), product.getCreationDate()) && Objects.equals(getPartNumber(), product.getPartNumber()) && getUnitOfMeasure() == product.getUnitOfMeasure() && Objects.equals(getManufacturer(), product.getManufacturer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCoordinates(), getCreationDate(), getPrice(), getPartNumber(), getManufactureCost(), getUnitOfMeasure(), getManufacturer());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", price=" + price +
                ", partNumber='" + partNumber + '\'' +
                ", manufactureCost=" + manufactureCost +
                ", unitOfMeasure=" + unitOfMeasure +
                ", manufacturer=" + manufacturer +
                '}';
    }

    @Override
    public int compareTo(Product o) {
        return (int) (o.getPrice() - this.getPrice());
    }
}
