package command;

import collection.ProductCollectionManager;
import commands.AbstractCommand;
import commands.Command;

public class ClearCommand extends AbstractCommand implements Command {
    private final ProductCollectionManager productManager;

    public ClearCommand(ProductCollectionManager productManager, boolean req) {
        super(req);
        this.productManager = productManager;
    }

    public void execute() {
        productManager.clear();
    }
}
