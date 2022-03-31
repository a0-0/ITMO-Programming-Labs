package command;

import file.ProductWriter;

public class SaveCommand implements ServerCommand{
    private final ProductWriter productWriter;

    public SaveCommand(ProductWriter productWriter) {
        this.productWriter = productWriter;
    }
    public void execute() {
        productWriter.write();
    }
}
