package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	   // 1️ Find products cheaper than a certain price
    @Query("SELECT p FROM Product p WHERE p.productPrice < :price")
    List<Product> findByPriceLessThan(@Param("price") Double price);

    // 2️  Find products where quantity is below a given value
    @Query("SELECT p FROM Product p WHERE p.productQuantity < :quantity")
    List<Product> findLowStockProducts(@Param("quantity") Integer quantity);

    // 3️ Find products by name (case-insensitive search)
    @Query("SELECT p FROM Product p WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Product> searchByName(@Param("name") String name);
}
