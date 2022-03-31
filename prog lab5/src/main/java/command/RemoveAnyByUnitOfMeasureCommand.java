package command;

import exception.InvalidArgumentException;
import main.ProductCollectionManager;
import model.UnitOfMeasure;

import java.util.Locale;

public class RemoveAnyByUnitOfMeasureCommand implements Command{
    ProductCollectionManager productManager;
    String[] args;

    public RemoveAnyByUnitOfMeasureCommand(ProductCollectionManager productManager) {
        this.productManager = productManager;
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args;
    }

    public void execute() {
        try {
            if (args.length == 1) {
                productManager.removeAnyByUnitOfMeasure(UnitOfMeasure.valueOf(args[0].toUpperCase(Locale.ROOT)));
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
