package com.example.demo;

import java.util.List;

public interface ProductService {
	 	Product saveProduct(Product product);
	    List<Product> saveAllProducts(List<Product> products);
	    List<Product> getAllProducts();
	    void deleteProduct(int id);
	    Product getProductById(int id);

	    // Custom query-based methods
	    List<Product> getProductsCheaperThan(Double price);
	    List<Product> searchProductsByName(String name);

	    // Update operations
	    Product updateProductPrice(int id, Double newPrice);
	    Product updateProductQuantity(int id, Integer newQuantity);

}
