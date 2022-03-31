package main;

import io.UserIO;
import model.*;

import java.io.IOException;
import java.util.Locale;

/**
 * Class for reading product fields from the console
 */
public class ConsoleProductParser implements SingleProductReader {
    UserIO userIO;
    ProductCollectionManager productManager;

    public ConsoleProductParser(ProductCollectionManager productManager, UserIO userIO) {
        this.productManager = productManager;
        this.userIO = userIO;
    }

    /**
     *  Method that reads product from console and returns it.
     *  @return Product class
     */
    public Product read() {
        Product product = null;
        ProductBuilder builder = new ProductBuilder();
        OrganizationBuilder orgBuilder = new OrganizationBuilder();
        try {
            product = builder.setName(readString("name"))
                    .setCoordinates(readCoordinates())
                    .setPrice(readLong("price"))
                    .setPartNumber(readString("partNumber"))
                    .setManufactureCost(readInt("manufacture cost"))
                    .setUnitOfMeasure(readUnitOfMeasure())
                    .setManufacturer(
                            orgBuilder.setName("name")
                            .setEmployeesCount(readInt("emplCount"))
                            .setType(readOrganizationType())
                            .setPostalAddress(readPostalAddress())
                            .build())
                    .build();
        } catch (Exception ignored) {}

        return product;
    }

    /**
     * Reads string from console
     * @param fieldName
     * @return parsed string
     */
    public String readString(String fieldName) {
        while (true) {
            try {
                userIO.printLine("enter " + fieldName + ": ");
                String str = userIO.readLine();
                if (fieldName.equals("name") && !validateName(str)) {
                    System.err.println("invalid name");
                    continue;
                }
                else if (fieldName.equals("partNumber") && !validatePartNumber(str)) {
                    System.err.println("invalid part number");
                    continue;
                }
                else if (fieldName.equals("zipCode") && !validateZipCode(str)) {
                    System.err.println("invalid zip code");
                    continue;
                }

                return str.replaceAll("[^\\w\\s]", "");
            } catch (IOException ioe) {
                System.err.println("something happened");
            }
        }
    }

    /**
     * Parses coordinates from console
     * @return Coordinates class
     */
    public Coordinates readCoordinates() {
        while (true) {
            try {
                userIO.printLine("enter coordinates.x: ");
                double x = Long.parseLong(userIO.readLine());

                userIO.printLine("enter coordinates.y: ");
                long y = Long.parseLong(userIO.readLine());

                Coordinates coordinates = new Coordinates(x, y);
                if (!validateCoordinates(coordinates)) {
                    System.err.println("invalid coordinates");
                    continue;
                }

                return coordinates;
            } catch (IOException ioe) {
                System.err.println("read error");
            } catch (NumberFormatException nfe) {
                System.err.println("invalid coordinates");
            }
        }
    }

    /**
     * Parses long from console
     * @param fieldName
     * @return parsed long
     */
    public long readLong(String fieldName) {
        while (true) {
            try {
                userIO.printLine("enter " + fieldName + ": ");
                long num = Long.parseLong(userIO.readLine());

                if (fieldName.equals("price") && !validatePrice(num)) {
                    System.err.println("invalid price");
                    continue;
                }

                return num;
            } catch (IOException ioe) {
                System.err.println("something happened");
            } catch (NumberFormatException nfe) {
                System.err.println("invalid number");
            }
        }
    }

    /**
     * Parses int from console
     * @param fieldName
     * @return parsed int
     */
    public int readInt(String fieldName) {
        while (true) {
            try {
                userIO.printLine("enter " + fieldName + ": ");
                int num = Integer.parseInt(userIO.readLine());

                if (fieldName.equals("emplCount") && !validateEmployeesCountOfOrganization(num)) {
                    System.err.println("invalid employees count");
                    continue;
                }

                return num;
            } catch (IOException ioe) {
                System.err.println("something happened");
            } catch (NumberFormatException nfe) {
                System.err.println("invalid number");
            }
        }
    }

    /**
     * Parses unit of measure from console
     * @return parsed unit of measure
     */
    public UnitOfMeasure readUnitOfMeasure() {
        while (true) {
            try {
                userIO.printLine("enter unit of measure (KILOGRAMS, SQUARE_METERS, PCS, GRAMS, MILLIGRAMS): ");
                String str = userIO.readLine().replaceAll("[^\\w\\s]", "").toUpperCase(Locale.ROOT);
                return UnitOfMeasure.valueOf(str);
            } catch (IOException ioe) {
                System.err.println("something happened");
            } catch (IllegalArgumentException e) {
                System.err.println("invalid value");
            }
        }
    }

    /**
     * Parses OrganizationType from console
     * @return parsed OrganizationType
     */
    public OrganizationType readOrganizationType() {
        while (true) {
            try {
                userIO.printLine("enter organization type (PUBLIC, TRUST, OPEN_JOINT_STOCK_COMPANY): ");
                String str = userIO.readLine().replaceAll("[^\\w\\s]", "").toUpperCase(Locale.ROOT);
                return OrganizationType.valueOf(str);
            } catch (IOException ioe) {
                System.err.println("something happened");
            } catch (IllegalArgumentException e) {
                System.err.println("invalid value");
            }
        }
    }

    /**
     * Parses Address from console
     * @return parsed Address
     */
    public Address readPostalAddress() {
        while (true) {
            try {
                userIO.printLine("enter address zipcode (empty line for null): ");
                String zipcode = userIO.readLine().replaceAll("[^\\w\\s]", "");
                if (!zipcode.equals("") && !validateZipCode(zipcode)) {
                    System.err.println("invalid zipcode");
                    continue;
                }

                Location location = null;
                userIO.printLine("do you need location? (empty line for no): ");
                String ans = userIO.readLine().replaceAll("[^\\w\\s]", "");
                if (!ans.equals("")) {
                    location = new Location(readLong("loc.x"), readInt("loc.y"), readInt("loc.z"));
                }

                return (zipcode.equals("") && location == null) ? null : new Address(zipcode, location);
            } catch (IOException ioe) {
                System.err.println("something happened");
            }
        }
    }

    private boolean validateName(String name) {
        return name != null && !name.equals("");
    }

    private boolean validateCoordinates(Coordinates coordinates) {
        return coordinates.getY() != null && coordinates.getY() <= 311;
    }

    private boolean validatePrice(long price) {
        return price > 0;
    }

    private boolean validatePartNumber(String str) {
        return str != null;
    }

    private boolean validateEmployeesCountOfOrganization(int count) {
        return count > 0;
    }

    private boolean validateZipCode(String str) {
        return str.length() >= 5;
    }

}
