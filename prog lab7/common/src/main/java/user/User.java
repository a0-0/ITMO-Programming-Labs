package user;

import hash.MD5Generator;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 8347617547303456361L;

    private final String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = MD5Generator.getHash(password);
    }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
