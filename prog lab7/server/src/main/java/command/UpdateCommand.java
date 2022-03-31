package command;

import collection.ProductCollectionManager;
import commands.AbstractCommand;
import commands.ProductCommand;
import exception.CommandExecutionException;
import model.Product;

public class UpdateCommand extends AbstractCommand implements ProductCommand {
    private final ProductCollectionManager productManager;
    private String[] args;

    public UpdateCommand(ProductCollectionManager productManager, boolean req) {
        super(req);
        this.productManager = productManager;
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args.clone();
    }

    public void execute() {
        throw new UnsupportedOperationException();
    }

    public void execute(Product product) throws CommandExecutionException {
        try {
            if (args.length == 1) {
                productManager.update(Integer.parseInt(args[0]), product);
            } else {
                throw new CommandExecutionException(String.format("expected %d arguments, received %d",
                        1, args.length));
            }
        } catch (NumberFormatException nfe) {
            throw new CommandExecutionException("invalid data format");
        }
    }
}
