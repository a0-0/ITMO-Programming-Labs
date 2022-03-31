package commands;

import client.Application;

public class ExitCommand extends AbstractCommand{
    Application application;


    public ExitCommand(Application application) {
        this.application = application;
    }

    @Override
    public void execute() {
        application.setIsRunning(false);

    }
}
