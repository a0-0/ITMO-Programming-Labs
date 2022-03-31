package command;

import main.ProductCollectionManager;

public class MinByManufacturerCommand implements Command {
    ProductCollectionManager productManager;

    public MinByManufacturerCommand(ProductCollectionManager productManager) {
        this.productManager = productManager;
    }

    public void execute() {
        productManager.minByManufacturer();
    }
}
