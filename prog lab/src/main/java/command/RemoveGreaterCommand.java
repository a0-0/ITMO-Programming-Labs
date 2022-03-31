package command;

import io.UserIO;
import main.ProductCollectionManager;
import main.ConsoleProductParser;
import main.ScriptProductParser;
import main.SingleProductReader;

import java.io.BufferedReader;

public class RemoveGreaterCommand implements Command{
    private String[] args;
    private final ProductCollectionManager productManager;
    private final BufferedReader reader;
    private final UserIO userIO;

    public RemoveGreaterCommand(ProductCollectionManager productManager, BufferedReader reader, UserIO userIO) {
        this.productManager = productManager;
        this.reader = reader;
        this.userIO = userIO;
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args;
    }

    @Override
    public void execute() {
        try {
            SingleProductReader productReader;
            if (reader == null) {
                productReader = new ConsoleProductParser(productManager, userIO);
            } else {
                productReader = new ScriptProductParser(reader, productManager);
            }

            productManager.removeGreater(productReader.read());
        } catch (NumberFormatException nfe) {
            System.err.println("incorrect argument format");
        }
    }
}
