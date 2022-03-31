package command;

import io.UserIO;
import main.ProductCollectionManager;
import main.ConsoleProductParser;
import main.ScriptProductParser;
import main.SingleProductReader;

import java.io.BufferedReader;

public class AddCommand implements Command{
    ProductCollectionManager productManager;
    UserIO userIO;
    BufferedReader reader;

    AddCommand(ProductCollectionManager productManager, BufferedReader reader, UserIO userIO) {
        this.productManager = productManager;
        this.reader = reader;
        this.userIO = userIO;
    }

    public void execute() {
        try {
            SingleProductReader productReader;
            if (reader == null) {
                productReader = new ConsoleProductParser(productManager, userIO);
            } else {
                productReader = new ScriptProductParser(reader, productManager);
            }

            productManager.addProduct(productReader.read());
        } catch (NumberFormatException nfe) {
            System.err.println("invalid argument");
        }
    }
}
