package command;

import main.ProductCollectionManager;

public class ShowCommand implements Command{
    ProductCollectionManager productManager;

    ShowCommand(ProductCollectionManager productManager) {
        this.productManager = productManager;
    }

    public void execute() {
        productManager.show();
    }
}
