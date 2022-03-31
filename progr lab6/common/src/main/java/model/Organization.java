package model;

import java.io.Serializable;

public class Organization implements Comparable<Organization>, Serializable {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private int employeesCount; //Значение поля должно быть больше 0
    private OrganizationType type; //Поле не может быть null
    private Address postalAddress; //Поле может быть null

    public Organization() {}

    protected Organization(Integer id, String name, int employeesCount, OrganizationType type, Address postalAddress) {
        this.id = id;
        this.name = name;
        this.employeesCount = employeesCount;
        this.type = type;
        this.postalAddress = postalAddress;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getEmployeesCount() {
        return employeesCount;
    }

    public OrganizationType getType() {
        return type;
    }

    public Address getPostalAddress() {
        return postalAddress;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployeesCount(int employeesCount) {
        this.employeesCount = employeesCount;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    public void setPostalAddress(Address postalAddress) {
        this.postalAddress = postalAddress;
    }

    @Override
    public int compareTo(Organization o) {
        return o.getEmployeesCount() - this.getEmployeesCount();
    }
}
