package data;

import user.User;

public interface UserDAO {
    User getUserWhere(String username);
    void insertUser(User user);
    boolean deleteUser(User user);
}
