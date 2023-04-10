package com.devsuperior.dscatalog.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.factory.Factory;

@DataJpaTest
public class ProductRepositoryTests {
    
    @Autowired
    private ProductRepository repository;
    
    private  long existingId;
    private  long nonExistingId;
    private long countTotalProducts;

    @BeforeEach
    void setUp() throws Exception{
        existingId = 1L;
        nonExistingId = 1000L;
        countTotalProducts = 25L;
    }


    @Test
    public void saveShouldPersistWithAutoIncrementWhenIdIsNull(){
        Product product = Factory.createProduct();
        product.setId(null);

        product = repository.save(product);
        
        assertNotNull(product.getId());
        assertEquals(countTotalProducts + 1, product.getId());
    }

    @Test
    public void deleteShoudDeleteObjectWhenIdExists(){
       
        repository.deleteById(existingId);
        Optional<Product> result = repository.findById(existingId);
        assertFalse(result.isPresent());
    }

    @Test
    public void deleteShouldThrowEmptyDataAccessExceptionWhenIdDoesNotExist(){
        assertThrows(EmptyResultDataAccessException.class, () -> {
           
            repository.deleteById(nonExistingId);
        });
    }

    @Test
    void deveRetornarOptionalSeOIdExistir(){
        Optional<Product> result = repository.findById(existingId);
        assertTrue(result.isPresent());
    }

    @Test
    void deveRetornarOptionalNullSeOIdNaoExistir(){
        Optional<Product> result = repository.findById(nonExistingId);
        assertFalse(result.isPresent());
    }
}