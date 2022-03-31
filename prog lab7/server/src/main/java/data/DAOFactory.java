package data;

public abstract class DAOFactory {
    public abstract PostgresProductDAO getProductDAO();
    public abstract PostgresUserDAO getUserDAO();

    public static DAOFactory getDAOFactory() {
        return new PostgresDAOFactory();
    }
}
