package com.jasco.productManager.service;

import com.jasco.productManager.model.PPI_Product;
import com.jasco.productManager.model.PPI_ProductUrl;
import com.jasco.productManager.repository.PPI_ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class) // doing this we don't have to create an instance of the AutoClosable Class
class PPI_ProductServiceTest {

    @Mock
    private PPI_ProductRepository productRepositoryTest;
    private PPI_ProductService productServiceTest;
    //private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        //mockito is used to not repeat test that are already done, by ensuring that the method can work properly
        //autoCloseable = MockitoAnnotations.openMocks(this);
        productServiceTest = new PPI_ProductService(productRepositoryTest);
    }

    @Test
    void findAllProductsTest() {
        productServiceTest.findAllProducts();
        //verifies that findAll() of the repository inside the service method is called, it fails if we we call another method
        //it means that we are telling to Junit that whatever findAll() returns everything should work because it is already tested
        //therefore we just have to ensure that the correct method is called.
        verify(productRepositoryTest).findAll();
    }

    @Test
    void addProductTest() {
        PPI_ProductUrl product_0 = new PPI_ProductUrl("Test_0", "used for testing addProductTest", 0, "C");

        productServiceTest.addProduct(product_0);

        //to capture the product that was passed in the productServiceTest.addProduct() method
        ArgumentCaptor<PPI_Product> productArgumentCaptor = ArgumentCaptor.forClass(PPI_Product.class);

                                          //to ensure that we have passed the same product because .capture() returns
                                          //the item captured
        verify(productRepositoryTest).save(productArgumentCaptor.capture());

        PPI_Product capturedProduct = productArgumentCaptor.getValue();
        assertNotNull(capturedProduct);
    }

    @Test
    @Disabled
    void deleteProductTest() {
        PPI_Product product_0 = new PPI_Product("Test_0", "used for testing deleteProductTest", 0);

        productServiceTest.deleteProduct(product_0.getId());
        ArgumentCaptor<Long> idArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(productRepositoryTest).deletePPI_ProductById(idArgumentCaptor.capture());
        Long idCaptured = idArgumentCaptor.getValue();
        assertEquals(idCaptured, product_0.getId());
    }
}