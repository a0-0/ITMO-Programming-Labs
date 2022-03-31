package collection;

import model.Product;
import model.UnitOfMeasure;

import java.util.List;

public interface ProductCollectionManager {
    List<Product> getProducts();
    void addProducts(List<Product> products);
    void addProduct(Product product);
    void info();
    void clear();
    void show();
    void removeById(int id);
    void insertAt(int index, Product product);
    void printFieldAscendingManufacturer();
    void minByManufacturer();
    void removeAnyByUnitOfMeasure(UnitOfMeasure unitOfMeasure);
    void sort();
    void removeGreater(Product product);
    void update(int id, Product product);
}
