package command;

import commands.AbstractCommand;
import commands.Command;
import response.Creator;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelpCommand extends AbstractCommand implements Command {
    private final Creator creator;

    public HelpCommand(boolean req, Creator creator) {
        super(req);
        this.creator = creator;
    }

    public void execute() {
        ResourceBundle rb = ResourceBundle.getBundle("help", Locale.getDefault());
        Enumeration<String> keys = rb.getKeys();
        while (keys.hasMoreElements()) {
            creator.addToMsg(rb.getString(keys.nextElement()));
        }
    }
}
