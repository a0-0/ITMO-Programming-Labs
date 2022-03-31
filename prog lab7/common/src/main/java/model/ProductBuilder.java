package model;

import java.time.LocalDateTime;

public class ProductBuilder {
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

    public ProductBuilder setId(long id) throws Exception {
        if (id != 0) {
            this.id = id;
            return this;
        }

        throw new Exception("id");
    }

    public ProductBuilder setName(String name) throws Exception {
        if (name != null && !name.equals("")) {
            this.name = name;
            return this;
        }

        throw new Exception("name");
    }

    public ProductBuilder setCoordinates(Coordinates coordinates) throws Exception {
        if (coordinates.getY() != null || coordinates.getY() <= 311) {
            this.coordinates = coordinates;
            return this;
        }

        throw new Exception("coordinates");
    }

    public ProductBuilder setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public ProductBuilder setPrice(long price) throws Exception {
        if (price > 0) {
            this.price = price;
            return this;
        }

        throw new Exception("price");
    }

    public ProductBuilder setPartNumber(String partNumber) throws Exception {
        if (partNumber != null) {
            this.partNumber = partNumber;
            return this;
        }

        throw new Exception("partNumber");
    }

    public ProductBuilder setManufactureCost(int manufactureCost) {
        this.manufactureCost = manufactureCost;
        return this;
    }

    public ProductBuilder setUnitOfMeasure(UnitOfMeasure unitOfMeasure) throws Exception {
        if (unitOfMeasure != null) {
            this.unitOfMeasure = unitOfMeasure;
            return this;
        }

        throw new Exception("unitofmeasure");
    }

    public ProductBuilder setManufacturer(Organization manufacturer) throws Exception{
        if (manufacturer != null) {
            this.manufacturer = manufacturer;
            return this;
        }

        throw new Exception("manufacturer");
    }

    public ProductBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public Product build() {
        return new Product(id, name, coordinates, creationDate, price, partNumber, manufactureCost, unitOfMeasure, manufacturer, username);
    }
}
