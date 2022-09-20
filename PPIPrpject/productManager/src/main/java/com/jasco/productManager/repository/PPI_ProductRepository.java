package com.jasco.productManager.repository;

import com.jasco.productManager.model.PPI_Product;
import org.springframework.data.jpa.repository.JpaRepository;

//passing the metadata and the primary key type to the JpaRepository args
public interface PPI_ProductRepository extends JpaRepository<PPI_Product, Long> {
    PPI_Product findPPI_ProductById(Long id);
    void deletePPI_ProductById(Long id);
}
