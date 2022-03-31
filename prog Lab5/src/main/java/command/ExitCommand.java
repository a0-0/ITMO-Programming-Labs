package command;

import main.Application;

public class ExitCommand implements Command{

    ExitCommand (){
    }

    public void execute() {
        Application.setIsRunning(false);
    }
}
