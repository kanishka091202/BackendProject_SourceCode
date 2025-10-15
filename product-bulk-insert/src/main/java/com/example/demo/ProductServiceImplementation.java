

package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImplementation implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        log.debug("Saving product: {}", product.getProductName());
        Product saved = productRepository.save(product);
        log.info("Product saved successfully with ID: {}", saved.getProductId());
        return saved;
    }

    @Override
    public List<Product> saveAllProducts(List<Product> products) {
        log.debug("Saving multiple products: {}", products.size());
        return productRepository.saveAll(products);
    }

    @Override
    public List<Product> getAllProducts() {
        log.debug("Retrieving all products from repository");
        return productRepository.findAll();
    }

    @Override
    public void deleteProduct(int id) {
        log.warn("Deleting product with ID: {}", id);
        productRepository.deleteById(id);
        log.info("Product with ID {} deleted successfully", id);
    }

    @Override
    public Product getProductById(int id) {
        log.debug("Fetching product by ID: {}", id);
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            log.error("Product not found with ID: {}", id);
        }
        return product.orElse(null);
    }

    @Override
    public List<Product> getProductsCheaperThan(Double price) {
        log.debug("Fetching products cheaper than: {}", price);
        return productRepository.findByPriceLessThan(price);
    }


    @Override
    public List<Product> searchProductsByName(String name) {
        log.debug("Searching products containing name: {}", name);
        return productRepository.searchByName(name);
    }

    @Override
    public Product updateProductPrice(int id, Double newPrice) {
        log.info("Updating price for product ID: {} to new price: {}", id, newPrice);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setProductPrice(newPrice);
        Product updated = productRepository.save(product);
        log.debug("Updated product price successfully: {}", updated);
        return updated;
    }

    @Override
    public Product updateProductQuantity(int id, Integer newQuantity) {
        log.info("Updating quantity for product ID: {} to new quantity: {}", id, newQuantity);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setProductQuantity(newQuantity);
        Product updated = productRepository.save(product);
        log.debug("Updated product quantity successfully: {}", updated);
        return updated;
    }
}
