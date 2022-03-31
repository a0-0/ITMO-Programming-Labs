package model;

public class OrganizationBuilder {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private int employeesCount; //Значение поля должно быть больше 0
    private OrganizationType type; //Поле не может быть null
    private Address postalAddress; //Поле может быть null

    public OrganizationBuilder setId(Integer id) throws Exception {
        if (id != null && id > 0) {
            this.id = id;
            return this;
        }

        throw new Exception("org id");
    }

    public OrganizationBuilder setName(String name) throws Exception {
        if (name != null && !name.equals("")) {
            this.name = name;
            return this;
        }

        throw new Exception("org name");
    }

    public OrganizationBuilder setEmployeesCount(int employeesCount) throws Exception {
        if (employeesCount > 0) {
            this.employeesCount = employeesCount;
            return this;
        }

        throw new Exception("org emplcount");
    }

    public OrganizationBuilder setType(OrganizationType type) throws Exception {
        if (type != null) {
            this.type = type;
            return this;
        }

        throw new Exception("org orgtype");
    }

    public OrganizationBuilder setPostalAddress(Address postalAddress) {
        this.postalAddress = postalAddress;
        return this;
    }

    public Organization build() {
        return new Organization(id, name, employeesCount, type, postalAddress);
    }
}
