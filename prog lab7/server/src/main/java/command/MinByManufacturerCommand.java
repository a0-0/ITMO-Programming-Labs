package command;

import collection.ProductCollectionManager;
import commands.AbstractCommand;
import commands.Command;

public class MinByManufacturerCommand extends AbstractCommand implements Command {
    ProductCollectionManager productManager;

    public MinByManufacturerCommand(ProductCollectionManager productManager, boolean req) {
        super(req);
        this.productManager = productManager;
    }

    public void execute() {
        productManager.minByManufacturer();
    }
}
