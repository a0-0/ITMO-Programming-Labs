package command;

import exception.InvalidArgumentException;
import io.UserIO;
import main.ProductCollectionManager;
import main.ConsoleProductParser;
import main.ScriptProductParser;
import main.SingleProductReader;

import java.io.BufferedReader;

public class InsertAtCommand implements Command{
    private String[] args;
    private final ProductCollectionManager productManager;
    private final BufferedReader reader;
    private final UserIO userIO;

    public InsertAtCommand(ProductCollectionManager productManager, BufferedReader reader, UserIO userIO) {
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
            if (args.length == 1) {
                SingleProductReader productReader;
                if (reader == null) {
                    productReader = new ConsoleProductParser(productManager, userIO);
                } else {
                    productReader = new ScriptProductParser(reader, productManager);
                }

                productManager.insertAt(Integer.parseInt(args[0]), productReader.read());
            } else {
                throw new InvalidArgumentException("expected 1 argument, got " + args.length);
            }
        } catch (InvalidArgumentException iae) {
            System.err.println(iae.getMessage());
        } catch (NumberFormatException nfe) {
            System.err.println("incorrect argument format");
        }
    }
}
