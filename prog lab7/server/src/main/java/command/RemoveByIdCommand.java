package command;

import collection.ProductCollectionManager;
import commands.AbstractCommand;
import commands.Command;
import exception.CommandExecutionException;

public class RemoveByIdCommand extends AbstractCommand implements Command {
    private final ProductCollectionManager productManager;
    private String[] args;

    public RemoveByIdCommand(ProductCollectionManager productManager, boolean req) {
        super(req);
        this.productManager = productManager;
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args.clone();
    }

    public void execute() throws CommandExecutionException {
        try {
            if (args.length == 1) {
                productManager.removeById(Integer.parseInt(args[0]));
            } else {
                throw new CommandExecutionException(String.format("expected %d arguments, received %d",
                        1, args.length));
            }
        }  catch (NumberFormatException nfe) {
            throw new CommandExecutionException("invalid data format");
        }

    }
}
