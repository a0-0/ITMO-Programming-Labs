package file;

import collection.ProductCollectionManager;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import model.Product;
import model.ProductWrapper;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class XmlProductReader implements ProductReader {
    private final ProductCollectionManager productManager;
    private final XmlMapper mapper;
    private final String fileName;

    public XmlProductReader(ProductCollectionManager manager, String fileName) {
        productManager = manager;
        mapper = new XmlMapper();
        this.fileName = fileName;
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    /**
     * Reads linked list for xml file
     * @return list with read products
     */
    @Override
    public List<Product> read() {
        try {
            File file = new File(fileName);
            file.createNewFile();
            ProductWrapper wrapper;
            wrapper = mapper.readValue(file, ProductWrapper.class);
            return wrapper.getProducts();
        }
        catch (IOException e) {
            System.err.println("invalid xml or i/o error, continuing with empty collection");
            List<Product> products = new LinkedList<>();
            return products;
        }
    }
}
