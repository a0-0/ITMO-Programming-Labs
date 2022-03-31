package main;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import model.ProductWrapper;

import java.io.File;
import java.io.IOException;


public class XmlProductWriter implements ProductWriter {
    private final String fileName;
    private final ProductCollectionManager productManager;
    private final XmlMapper mapper;

    public XmlProductWriter(ProductCollectionManager manager, String fileName) {
        productManager = manager;
        this.fileName = fileName;
        mapper = new XmlMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }


    /**
     * Writes linked list of products to xml file
     */
    @Override
    public void write() {
        try {
            ProductWrapper wrapper = new ProductWrapper();
            wrapper.setProducts(productManager.getProducts());
            mapper.writeValue(new File(fileName), wrapper);
        } catch (IOException e) {
            System.err.println("i/o error");
        }
    }
}
