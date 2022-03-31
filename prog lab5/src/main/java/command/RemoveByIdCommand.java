package command;

import main.ProductCollectionManager;
import exception.InvalidArgumentException;

public class RemoveByIdCommand implements Command{
    ProductCollectionManager productManager;
    String[] args;

    RemoveByIdCommand(ProductCollectionManager productManager) {
        this.productManager = productManager;
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args;
    }

    public void execute() {
        try {
            if (args.length == 1) {
                productManager.removeById(Integer.parseInt(args[0]));
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
