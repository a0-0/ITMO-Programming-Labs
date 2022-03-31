package collection;

import data.ProductDAO;
import model.Product;
import model.ProductFormatter;
import model.UnitOfMeasure;
import response.Creator;
import server.UserAuthModule;

import java.time.LocalDate;
import java.util.*;

public class ProductLinkedListManager implements ProductCollectionManager {

    private final List<Product> products;

    private final Creator creator;

    private long productId = 1;
    private int orgId = 1;

    private final LocalDate dateOfInit = LocalDate.now();

    private final ProductDAO productDAO;
    private final UserAuthModule authModule;

    public ProductLinkedListManager(Creator creator, ProductDAO productDAO, UserAuthModule authModule) {
        products = Collections.synchronizedList(new LinkedList<>());
        this.creator = creator;
        this.productDAO = productDAO;
        this.authModule = authModule;
    }


    public void addProduct(Product product) {
        product.setId(productId);
        product.getManufacturer().setId(orgId);
        productId++;
        orgId++;
        productDAO.insertProduct(product);
        products.add(product);
    }

    public void changeProduct(int index, Product product) {
        productDAO.updateProduct(product);
        products.set(index, product);
    }

    public void insertProduct(int index, Product product) {
        product.setId(productId);
        product.getManufacturer().setId(orgId);
        productId++;
        orgId++;
        productDAO.insertProduct(product);
        products.add(index, product);
    }

    public void addProducts() {
        this.products.addAll(productDAO.selectProductsToCollection());
    }

    public List<Product> getProducts() {
        return products;
    }

    public void info() {
        creator.addToMsg(String.format("%s, Date of init:%s, Number of objects: %d", products.getClass().getName(),
                dateOfInit, products.size()));
    }

    public void clear() {
        products.clear();
    }

    public void show() {
        ProductFormatter formatter = new ProductFormatter();
        for(Product product: products) {
            creator.addToMsg(formatter.formatProduct(product));
        }
    }


    public void removeById(int id) {
        int count = 0;
        Iterator<Product> iterator = products.iterator();
        while(iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getId() == id) {
                productDAO.deleteProduct(product);
                iterator.remove();
                count++;
            }
        }
        creator.addToMsg(String.format("removed %d object(-s)", count));
    }

    public void removeGreater(Product product) {
        products.stream().filter(p -> p.compareTo(product) < 0).forEach(productDAO::deleteProduct);
        products.removeIf(p -> p.compareTo(product) < 0);
    }

    public void sort() {
        Collections.sort(products);
    }

    public void removeAnyByUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        Optional<Product> product = products.stream().filter(p -> p.getUnitOfMeasure() == unitOfMeasure).findFirst();
        product.ifPresent(productDAO::deleteProduct);
        product.ifPresent(products::remove);
    }

    public void minByManufacturer() {
        Optional<Product> product = products.stream().min(Comparator.comparing(Product::getManufacturer));
        if (product.isPresent()) {
            ProductFormatter formatter = new ProductFormatter();
            creator.addToMsg(formatter.formatProduct(product.get()));
        }
    }

    public void printFieldAscendingManufacturer() {
        ProductFormatter formatter = new ProductFormatter();
        products.stream().sorted().forEach(p -> creator.addToMsg(formatter.formatOrganization(p)));
    }

    public void insertAt(int index, Product product) {
        try {
            productDAO.insertProduct(product);
            insertProduct(index, product);
            creator.addToMsg("inserted 1 object");
        } catch (ArrayIndexOutOfBoundsException e) {
            creator.addToMsg("invalid index");
        }
    }

    public void update(int id, Product product) {
        int index = 0;
        for (Product productOld : products) {
            if (productOld.getId() == id) {
                break;
            }
            index++;
        }

        product.setId(id);
        if (products.size() != 0 && index != products.size()) {
            productDAO.updateProduct(product);
            changeProduct(index, product);
        }
    }
}
