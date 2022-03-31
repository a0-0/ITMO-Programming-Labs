package data;

import exception.PersistentException;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

public class PostgresProductDAO implements ProductDAO {

    protected PostgresProductDAO() {
        try (Connection connection = PostgresDAOFactory.createConnection();
        Statement statement = connection.createStatement()) {
            statement.execute("CREATE SEQUENCE IF NOT EXISTS public.id\n" +
                    "    INCREMENT 1\n" +
                    "    START 1\n" +
                    "    MINVALUE 1\n" +
                    "    MAXVALUE 9223372036854775807\n" +
                    "    CACHE 1;");
            statement.execute("CREATE TABLE IF NOT EXISTS public.products\n" +
                    "(\n" +
                    "    id integer NOT NULL,\n" +
                    "    name character varying(255) COLLATE pg_catalog.\"default\" NOT NULL,\n" +
                    "    cord_x double precision NOT NULL,\n" +
                    "    cord_y bigint NOT NULL,\n" +
                    "    creation_date timestamp without time zone NOT NULL,\n" +
                    "    price bigint NOT NULL,\n" +
                    "    part_number character varying(255) COLLATE pg_catalog.\"default\" NOT NULL,\n" +
                    "    manufacture_cost integer NOT NULL,\n" +
                    "    unit_of_measure character varying(20) COLLATE pg_catalog.\"default\" NOT NULL,\n" +
                    "    org_name character varying(255) COLLATE pg_catalog.\"default\" NOT NULL,\n" +
                    "    org_employees_count integer NOT NULL,\n" +
                    "    org_organization_type character varying(40) COLLATE pg_catalog.\"default\" NOT NULL,\n" +
                    "    addr_zip_code character varying(40) COLLATE pg_catalog.\"default\",\n" +
                    "    addr_x bigint,\n" +
                    "    addr_y integer,\n" +
                    "    addr_z integer,\n" +
                    "    username character varying(255) COLLATE pg_catalog.\"default\" NOT NULL,\n" +
                    "    CONSTRAINT products_pkey PRIMARY KEY (id)\n" +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS public.users\n" +
                    "(\n" +
                    "    username character varying(255) COLLATE pg_catalog.\"default\" NOT NULL,\n" +
                    "    password character varying(255) COLLATE pg_catalog.\"default\" NOT NULL\n" +
                    ")\n");
        } catch (Exception e) {
            throw new PersistentException(e.getMessage());
        }
    }

    @Override
    public Collection<Product> selectProductsToCollection()  {
        try (Connection connection = PostgresDAOFactory.createConnection();
             Statement statement = connection.createStatement()) {
            Collection<Product> products = new ArrayList<>();
            ResultSet r = statement.executeQuery("SELECT * FROM products");
            while (r.next()) {
                products.add(buildProduct(r));
            }
            return products;
        } catch (Exception e) {
            throw new PersistentException(e.getMessage());
        }
    }

    @Override
    public Product getProduct(int id)  {
        try (Connection connection = PostgresDAOFactory.createConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM products WHERE id=?")) {
            Product product = null;
            statement.setInt(1, id);
            ResultSet r = statement.executeQuery();
            while (r.next()) {
                product = buildProduct(r);
            }
        return product;
        } catch (Exception e) {
            throw new PersistentException(e.getMessage());
        }
    }

    @Override
    public int getNextId()  {
        try (Connection connection = PostgresDAOFactory.createConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT nextval('id')")) {
            ResultSet r = statement.executeQuery();
            r.next();
            return r.getInt(1);
        } catch (SQLException e) {
            throw new PersistentException(e.getMessage());
        }
    }

    private Product buildProduct(ResultSet r) throws Exception {
        ProductBuilder productBuilder = new ProductBuilder();
        OrganizationBuilder organizationBuilder = new OrganizationBuilder();

        organizationBuilder
                .setName(r.getString(10))
                .setEmployeesCount(r.getInt(11))
                .setType(OrganizationType.valueOf(r.getString(12).toUpperCase(Locale.ROOT)));

        if (r.getString(13) == null && r.getLong(14) == 0 && r.getInt(15) == 0 && r.getInt(16) == 0) {
            organizationBuilder.setPostalAddress(null);
        } else if (r.getString(13) != null) {
            organizationBuilder.setPostalAddress(
                    new Address(r.getString(12) == null ? null : r.getString(12), new Location(
                            r.getLong(14), r.getInt(15), r.getInt(16)
                    ))
            );
        }

        productBuilder.setId(r.getInt(1));
        productBuilder.setName(r.getString(2));
        productBuilder.setCoordinates(new Coordinates(r.getDouble(3), r.getLong(4)));
        productBuilder.setCreationDate(r.getTimestamp(5).toLocalDateTime());
        productBuilder.setPrice(r.getLong(6));
        productBuilder.setPartNumber(r.getString(7));
        productBuilder.setManufactureCost(r.getInt(8));
        productBuilder.setUnitOfMeasure(UnitOfMeasure.valueOf(r.getString(9).toUpperCase(Locale.ROOT)));
        productBuilder.setManufacturer(organizationBuilder.build());
        productBuilder.setUsername(r.getString(17));
        return productBuilder.build();
    }

    public void insertProduct(Product product)  {
        try (Connection connection = PostgresDAOFactory.createConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO products VALUES (nextval('id'), " +
                     "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)")) {

            statement.setString(1, product.getName());
            statement.setDouble(2, product.getCoordinates().getX());
            statement.setLong(3, product.getCoordinates().getY());
            statement.setTimestamp(4, Timestamp.valueOf(product.getCreationDate()));
            statement.setLong(5, product.getPrice());
            statement.setString(6, product.getPartNumber());
            statement.setInt(7, product.getManufactureCost());
            statement.setString(8, product.getUnitOfMeasure().toString());
            statement.setString(9, product.getManufacturer().getName());
            statement.setInt(10, product.getManufacturer().getEmployeesCount());
            statement.setString(11, product.getManufacturer().getType().toString());
            if (product.getManufacturer().getPostalAddress() == null) {
                statement.setNull(12, Types.VARCHAR);
                statement.setNull(13, Types.BIGINT);
                statement.setNull(14, Types.INTEGER);
                statement.setNull(15, Types.INTEGER);
            } else {
                statement.setString(12, product.getManufacturer().getPostalAddress().getZipCode());
                statement.setLong(13, product.getManufacturer().getPostalAddress().getTown().getX());
                statement.setInt(14, product.getManufacturer().getPostalAddress().getTown().getY());
                statement.setInt(15, product.getManufacturer().getPostalAddress().getTown().getZ());
            }

            statement.setString(16, product.getUsername());
            statement.execute();
        } catch (SQLException e) {
            throw new PersistentException(e.getMessage());
        }
    }

    public boolean updateProduct(Product product) {
        try (Connection connection = PostgresDAOFactory.createConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE products SET " +
                     "name = ?," +
                     "cord_x = ?," +
                     "cord_y = ?," +
                     "creation_date = ?," +
                     "price = ?," +
                     "part_number = ?," +
                     "manufacture_cost = ?," +
                     "unit_of_measure = ?," +
                     "org_name = ?," +
                     "org_employees_count = ?," +
                     "org_organization_type = ?," +
                     "addr_zip_code = ?," +
                     "addr_x = ?," +
                     "addr_y = ?," +
                     "addr_z = ?" +
                     "WHERE id = ?")) {

            statement.setString(1, product.getName());
            statement.setDouble(2, product.getCoordinates().getX());
            statement.setLong(3, product.getCoordinates().getY());
            statement.setTimestamp(4, Timestamp.valueOf(product.getCreationDate()));
            statement.setLong(5, product.getPrice());
            statement.setString(6, product.getPartNumber());
            statement.setInt(7, product.getManufactureCost());
            statement.setString(8, product.getUnitOfMeasure().toString());
            statement.setString(9, product.getManufacturer().getName());
            statement.setInt(10, product.getManufacturer().getEmployeesCount());
            statement.setString(11, product.getManufacturer().getType().toString());
            if (product.getManufacturer().getPostalAddress() == null) {
                statement.setNull(12, Types.VARCHAR);
                statement.setNull(13, Types.BIGINT);
                statement.setNull(14, Types.INTEGER);
                statement.setNull(15, Types.INTEGER);
            } else {
                statement.setString(12, product.getManufacturer().getPostalAddress().getZipCode());
                statement.setLong(13, product.getManufacturer().getPostalAddress().getTown().getX());
                statement.setInt(14, product.getManufacturer().getPostalAddress().getTown().getY());
                statement.setInt(15, product.getManufacturer().getPostalAddress().getTown().getZ());
            }

            statement.setInt(16, (int) product.getId());
            statement.execute();
            return true;
        } catch (SQLException e) {
            throw new PersistentException(e.getMessage());
        }

    }

    public boolean deleteProduct(Product product)  {
        try (Connection connection = PostgresDAOFactory.createConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM products WHERE id=?")) {
            statement.setInt(1, (int) product.getId());
            statement.execute();
            return true;
        } catch (SQLException e) {
            throw new PersistentException(e.getMessage());
        }
    }

    @Override
    public boolean deleteProducts()  {
        try (Connection connection = PostgresDAOFactory.createConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("DELETE * FROM products");
            return true;
        } catch (SQLException e) {
            throw new PersistentException(e.getMessage());
        }
    }
}
