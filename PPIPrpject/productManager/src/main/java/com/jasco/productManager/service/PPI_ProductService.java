package com.jasco.productManager.service;

import com.jasco.productManager.exceptions.ProductNotFoundException;
import com.jasco.productManager.model.PPI_Product;
import com.jasco.productManager.model.PPI_ProductUrl;
import com.jasco.productManager.repository.PPI_ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Service Layer used to not expose the DB to the Controller
 */
@Service
public class PPI_ProductService {

    private final PPI_ProductRepository productRepository;

    @Autowired
    public PPI_ProductService(PPI_ProductRepository productRepository) { this.productRepository = productRepository; }

    /**
     * Method to return all the products in db
     * @return
     */
    public List<PPI_Product> findAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Method to return a Product with ID as filter
     * @param id
     * @return PPI_Product
     * @throws ProductNotFoundException
     */
    private PPI_Product findProductById(Long id) throws ProductNotFoundException{
        PPI_Product product = productRepository.findPPI_ProductById(id);
        if(product == null) {
            throw new ProductNotFoundException("there no product with the id: " + id);
        }
        return product;
    }

    /**
     * Method to add a product to the db
     * @param product
     * @return PPI_Product
     */
    public PPI_Product addProduct(PPI_ProductUrl product) {
        PPI_Product newProduct = new PPI_Product(product.getName(),
                product.getDescription(),
                getImageFromNetByUrl(product.getImage()),
                product.getStorageCount());
        //using the product repository layer to add the product to the db without repeating queries
        return productRepository.save(newProduct);
    }

    /**
     * Method to delete a Product with ID as filter
     * @param id
     */
    public void deleteProduct(Long id) {
        try {
            findProductById(id);
            productRepository.deletePPI_ProductById(id);
        } catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Converts the Image Url into a array of bytes, by doing a connection to the img url
     * @param strUrl
     * @return byte[] btImg
     */
    private byte[] getImageFromNetByUrl(String strUrl) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();
            byte[] btImg = readInputStream(inStream);
            return btImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * read and converts the input stream into a array of bytes
     * @param inStream
     * @return ByteArrayOutputStream outStream
     * @throws IOException
     */
    private byte[] readInputStream(InputStream inStream) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[10240];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();

    }
}
