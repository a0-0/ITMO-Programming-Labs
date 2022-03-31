package command;

import main.ProductCollectionManager;

public class PrintFieldAscendingManufacturerCommand implements Command{
    ProductCollectionManager productManager;

    public PrintFieldAscendingManufacturerCommand(ProductCollectionManager productManager) {
        this.productManager = productManager;
    }

    public void execute() {
        productManager.printFieldAscendingManufacturer();
    }
}
