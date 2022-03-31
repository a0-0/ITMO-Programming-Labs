package command;

import main.ProductWriter;

public class SaveCommand implements Command{
    ProductWriter fileManager;

    SaveCommand(ProductWriter fileManager) {
        this.fileManager = fileManager;
    }
    public void execute() {
        fileManager.write();
    }
}
