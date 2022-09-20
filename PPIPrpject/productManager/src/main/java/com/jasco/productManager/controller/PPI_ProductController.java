package com.jasco.productManager.controller;

import com.jasco.productManager.model.PPI_Product;
import com.jasco.productManager.model.PPI_ProductUrl;
import com.jasco.productManager.service.PPI_ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/product")
public class PPI_ProductController {

    private final PPI_ProductService productService;

    @Autowired
    public PPI_ProductController(PPI_ProductService productService) {
        this.productService = productService;
    }

    /**
     * Controller layer: return all the products in the database
     * @return
     */
    @GetMapping//on default path
    public ResponseEntity<List<PPI_Product>> getAllProducts() {
        List<PPI_Product> products = productService.findAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Controller layer: deletes a product with ID as filter
     * @param id
     */
    @Transactional
    @DeleteMapping ("/delete/{id}")                        //to specify what type i am expecting to avoid data injections
    public ResponseEntity<?> deleteProducts(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Controller layer: adding a new product in the database
     * @param product
     * @return PPI_Product
     */
    @PostMapping("/add")                        //i am expecting a json
    public ResponseEntity<PPI_Product> addProducts(@RequestBody PPI_ProductUrl product) {
        PPI_Product newProduct = productService.addProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

}
