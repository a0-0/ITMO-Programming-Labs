package commands;

import io.UserIO;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class ClientHelpCommand extends AbstractCommand{
    UserIO userIO;

    public ClientHelpCommand(UserIO userIO) {
        this.userIO = userIO;
    }

    @Override
    public void execute() {
        ResourceBundle rb = ResourceBundle.getBundle("help", Locale.getDefault());
        Enumeration<String> keys = rb.getKeys();
        while (keys.hasMoreElements()) {
            userIO.printLine(rb.getString(keys.nextElement()));
        }
    }
}
