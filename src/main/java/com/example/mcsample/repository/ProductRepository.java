package com.example.mcsample.repository;

import com.example.mcsample.entity.Category;
import com.example.mcsample.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository

public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findByNameContainingIgnoreCase(String name); //Поиск по имени (нечувствителен к регистру)

    List<Product> findByCategory(Category category); //Поиск по категории

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name% AND p.price <= :maxPrice")
    List<Product> findByNameAndPriceLessThanEqual(@Param("name") String name, @Param("maxPrice") BigDecimal maxPrice);
}