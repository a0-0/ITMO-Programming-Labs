package command;

import collection.ProductCollectionManager;
import commands.AbstractCommand;
import commands.Command;

public class PrintFieldAscendingManufacturerCommand extends AbstractCommand implements Command {
    ProductCollectionManager productManager;

    public PrintFieldAscendingManufacturerCommand(ProductCollectionManager productManager, boolean req) {
        super(req);
        this.productManager = productManager;
    }

    public void execute() {
        productManager.printFieldAscendingManufacturer();
    }
}
