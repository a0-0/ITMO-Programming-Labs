package data;

import exception.PersistentException;
import user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgresUserDAO implements UserDAO {

    @Override
    public User getUserWhere(String username) {
        try (Connection connection = PostgresDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ?")){
            preparedStatement.setString(1, username);
            if (preparedStatement.execute()) {
                ResultSet resultSet = preparedStatement.getResultSet();
                if (resultSet.next()) {
                    User user = new User(resultSet.getString(1));
                    user.setPassword(resultSet.getString(2));
                    return user;
                }
            }
            return null;
        } catch (SQLException e) {
            throw new PersistentException(e.getMessage());
        }
    }

    @Override
    public void insertUser(User user) {
        try (Connection connection = PostgresDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users VALUES (?,?)")) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new PersistentException(e.getMessage());
        }
    }

    @Override
    public boolean deleteUser(User user) {
        try (Connection connection = PostgresDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE username = ?")){
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new PersistentException(e.getMessage());
        }
    }
}
