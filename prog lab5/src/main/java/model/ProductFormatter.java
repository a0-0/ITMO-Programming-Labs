package model;

public class ProductFormatter {
    public String formatProduct(Product product) {
        String address;
        if (product.getManufacturer().getPostalAddress() == null) {
            address = "null";
        } else {
            if (product.getManufacturer().getPostalAddress().getTown() == null) {
                address = String.format("{zipCode: %s, town: null}", product.getManufacturer().getPostalAddress().getZipCode());
            } else {
                address = String.format("{zipCode: %s, town: {x: %d y: %d z: %d}}",
                        product.getManufacturer().getPostalAddress().getZipCode(),
                        product.getManufacturer().getPostalAddress().getTown().getX(),
                        product.getManufacturer().getPostalAddress().getTown().getY(),
                        product.getManufacturer().getPostalAddress().getTown().getZ());
            }
        }
        return String.format("Product object:ID:%d, Name:\"%s\", Coordinates: {x:%.2f, y:%d}, Creation date: %s" +
                        "price: %d, part number: %s, manufacture cost: %d, unitOfMeasure: %s" +
                        "manufacturer: {id: %d, name: %s, emplCount: %d, orgType: %s," +
                        "postalAddress:" + address + " ", product.getId(), product.getName(), product.getCoordinates().getX(),
                product.getCoordinates().getY(), product.getCreationDate(), product.getPrice(), product.getPartNumber(),
                product.getManufactureCost(), product.getUnitOfMeasure(), product.getManufacturer().getId(),
                product.getManufacturer().getName(), product.getManufacturer().getEmployeesCount(),
                product.getManufacturer().getType());
    }

    public String formatOrganization(Product product) {
        String address;
        if (product.getManufacturer().getPostalAddress() == null) {
            address = "null";
        } else {
            if (product.getManufacturer().getPostalAddress().getTown() == null) {
                address = String.format("{zipCode: %s, town: null}", product.getManufacturer().getPostalAddress().getZipCode());
            } else {
                address = String.format("{zipCode: %s, town: {x: %d y: %d z: %d}}",
                        product.getManufacturer().getPostalAddress().getZipCode(),
                        product.getManufacturer().getPostalAddress().getTown().getX(),
                        product.getManufacturer().getPostalAddress().getTown().getY(),
                        product.getManufacturer().getPostalAddress().getTown().getZ());
            }
        }

        return String.format("manufacturer: {id: %d, name: %s, emplCount: %d, orgType: %s," +
                "postalAddress:" + address + " ", product.getManufacturer().getId(),
                product.getManufacturer().getName(), product.getManufacturer().getEmployeesCount(),
                product.getManufacturer().getType());
    }
}
