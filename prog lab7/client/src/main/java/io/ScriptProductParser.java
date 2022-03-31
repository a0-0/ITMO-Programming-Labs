package io;

import model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Locale;

public class ScriptProductParser implements SingleProductReader {
    BufferedReader reader;
    UserIO userIO;

    public ScriptProductParser(BufferedReader reader, UserIO userIO) {
        this.reader = reader;
        this.userIO = userIO;
    }

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

    public String readString(String fieldName) {
        try {
            String str;
            if (reader.ready()) {
                str = reader.readLine();
            } else {
                throw new IOException();
            }
            if (fieldName.equals("name") && !validateName(str)) {
                userIO.printErrorMessage("invalid name");
            }
            else if (fieldName.equals("partNumber") && !validatePartNumber(str)) {
                userIO.printErrorMessage("invalid part number");
            }
            else if (fieldName.equals("zipCode") && !validateZipCode(str)) {
                userIO.printErrorMessage("invalid zip code");
            }

            return str.replaceAll("[^\\w\\s]", "");
        } catch (IOException ioe) {
            userIO.printErrorMessage("something happened");
        }

        return null;
    }

    public Coordinates readCoordinates() {
        try {
            double x; long y;
            if (reader.ready()) {
                x = Long.parseLong(reader.readLine());
            } else {
                throw new IOException();
            }

            if (reader.ready()) {
                y = Long.parseLong(reader.readLine());
            } else {
                throw new IOException();
            }

            Coordinates coordinates = new Coordinates(x, y);
            if (!validateCoordinates(coordinates)) {
                userIO.printErrorMessage("invalid coordinates");
            }

            return coordinates;
        } catch (IOException ioe) {
            userIO.printErrorMessage("read error");
        } catch (NumberFormatException nfe) {
            userIO.printErrorMessage("invalid coordinates");
        }

        return null;
    }

    public long readLong(String fieldName) {
        try {
            long num;
            if (reader.ready()) {
                num = Long.parseLong(reader.readLine());
            } else {
                throw new IOException();
            }

            if (fieldName.equals("price") && !validatePrice(num)) {
                userIO.printErrorMessage("invalid price");
            }

            return num;
        } catch (IOException ioe) {
            userIO.printErrorMessage("something happened");
        } catch (NumberFormatException nfe) {
            userIO.printErrorMessage("invalid number");
        }

        return 0;
    }

    public int readInt(String fieldName) {
        try {
            int num;
            if (reader.ready()) {
                num = Integer.parseInt(reader.readLine());
            } else {
                throw new IOException();
            }

            if (fieldName.equals("emplCount") && !validateEmployeesCountOfOrganization(num)) {
                userIO.printErrorMessage("invalid employees count");
            }

            return num;
        } catch (IOException ioe) {
            userIO.printErrorMessage("something happened");
        } catch (NumberFormatException nfe) {
            userIO.printErrorMessage("invalid number");
        }

        return 0;
    }

    public UnitOfMeasure readUnitOfMeasure() {
        try {
            if (reader.ready()) {
                String str = reader.readLine().replaceAll("[^\\w\\s]", "").toUpperCase(Locale.ROOT);
                return UnitOfMeasure.valueOf(str);
            }
        } catch (IOException ioe) {
            userIO.printErrorMessage("something happened");
        } catch (IllegalArgumentException e) {
            userIO.printErrorMessage("invalid value");
        }

        return null;
    }

    public OrganizationType readOrganizationType() {
        try {
            if (reader.ready()) {
                String str = reader.readLine().replaceAll("[^\\w\\s]", "").toUpperCase(Locale.ROOT);
                return OrganizationType.valueOf(str);
            } else {
                throw new IOException();
            }
        } catch (IOException ioe) {
            userIO.printErrorMessage("something happened");
        } catch (IllegalArgumentException e) {
            userIO.printErrorMessage("invalid value");
        }

        return null;
    }

    public Address readPostalAddress() {
        try {
            if(reader.ready()) {
                String zipcode = reader.readLine().replaceAll("[^\\w\\s]", "");
                String ans = reader.readLine().replaceAll("[^\\w\\s]", "");

                if (!zipcode.equals("") && !validateZipCode(zipcode)) {
                    userIO.printErrorMessage("invalid zipcode");
                }

                Location location = null;

                if (!ans.equals("")) {
                    location = new Location(readLong("loc.x"), readInt("loc.y"), readInt("loc.z"));
                }

                return (zipcode.equals("") && location == null) ? null : new Address(zipcode, location);
            } else {
                throw new IOException();
            }
        } catch (IOException ioe) {
            userIO.printErrorMessage("something happened");
        }

        return null;
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
