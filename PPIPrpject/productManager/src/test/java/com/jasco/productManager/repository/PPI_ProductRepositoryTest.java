package com.jasco.productManager.repository;

import com.jasco.productManager.model.PPI_Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
class PPI_ProductRepositoryTest {

    @Autowired
    PPI_ProductRepository productRepositoryTest;

    @AfterEach
    void deleteAll() {
        productRepositoryTest.deleteAll();
    }

    @Test
    void findPPI_ProductById() {
        PPI_Product product_0 = new PPI_Product("Test_0", "used for testing", 0);
        PPI_Product product_1 = new PPI_Product("Test_0", "used for testing", 0);

        assert(productRepositoryTest.findAll().isEmpty());
        productRepositoryTest.save(product_0);
        assertEquals(product_0.getId(), productRepositoryTest.findPPI_ProductById(product_0.getId()).getId());
        assertNull(productRepositoryTest.findPPI_ProductById(product_1.getId()));
    }

    @Test
    void deletePPI_ProductById() {
        PPI_Product product_0 = new PPI_Product("Test_0", "used for testing", 0);

        List<PPI_Product> productList_0 = productRepositoryTest.findAll();
        assert(productList_0.isEmpty());

        productRepositoryTest.save(product_0);
        assertEquals(product_0.getId(), productRepositoryTest.findPPI_ProductById(product_0.getId()).getId());

        productRepositoryTest.deletePPI_ProductById(product_0.getId());
        assert(productList_0.isEmpty());
    }
}