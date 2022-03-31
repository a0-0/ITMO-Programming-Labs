package command;

import connection.ConnectionListener;
import file.ProductWriter;
import server.Application;

import java.io.IOException;

public class ExitCommand implements ServerCommand {
    private final ConnectionListener connectionListener;
    private final Application application;

    public ExitCommand(ConnectionListener connectionListener, Application application) {
        this.connectionListener = connectionListener;
        this.application = application;
    }

    public void execute() {
        try {
            application.setIsRunning(false);
            connectionListener.stop();
            System.exit(0);
        } catch (IOException ignored) {}
    }
}
