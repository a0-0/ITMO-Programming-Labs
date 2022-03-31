package command;

import connection.ConnectionListener;
import file.ProductWriter;
import server.Application;

import java.io.IOException;

public class ExitCommand implements ServerCommand {
    private final ProductWriter productWriter;
    private final ConnectionListener connectionListener;
    private final Application application;

    public ExitCommand(ProductWriter productWriter, ConnectionListener connectionListener, Application application) {
        this.productWriter = productWriter;
        this.connectionListener = connectionListener;
        this.application = application;
    }

    public void execute() {
        productWriter.write();
        try {
            application.setIsRunning(false);
            connectionListener.stop();
        } catch (IOException ignored) {}
    }
}
