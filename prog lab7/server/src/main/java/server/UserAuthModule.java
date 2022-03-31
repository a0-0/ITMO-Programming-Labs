package server;

import data.UserDAO;
import exception.PersistentException;
import user.User;

public class UserAuthModule {
    private final UserDAO userDAO;
    private User user;
    private String reason;

    public UserAuthModule(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean authUser(User user) {
        User authUser = userDAO.getUserWhere(user.getUsername());
        if (authUser == null) {
            reason = "Incorrect username or password";
            this.user = null;
            return false;
        } else if(authUser.getPassword().equals(user.getPassword())) {
            this.user = user;
            reason = "";
        }
        return true;
    }

    public boolean registerUser(User user) {
        try {
            userDAO.insertUser(user);
            reason = "";
            return true;
        } catch (PersistentException e) {
            reason = "User exists";
            return false;
        }
    }

    public User getUser() {
        return user;
    }

    public String getReason() {
        return reason;
    }
}
