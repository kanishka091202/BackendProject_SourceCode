package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    // 1️ Add single product
    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        log.info("Saving product: {}", product.getProductName());
        Product saved = productService.saveProduct(product);
        log.debug("Product saved with ID: {}", saved.getProductId());
        return saved;
    }

    // 2️ Bulk insert
    @PostMapping("/bulk")
    public List<Product> saveAllProducts(@RequestBody List<Product> products) {
        log.info("Bulk insert request received. Total products: {}", products.size());
        return productService.saveAllProducts(products);
    }

    // 3️ Get all products
    @GetMapping("/getAllItems")
    public List<Product> getAllProducts() {
        log.info("Fetching all products");
        return productService.getAllProducts();
    }

    // 4️ Get product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        log.info("Fetching product with ID: {}", id);
        return productService.getProductById(id);
    }

    // 5️ Get products cheaper than price
    @GetMapping("/cheaperThan/{price}")
    public List<Product> getProductsCheaperThan(@PathVariable Double price) {
        log.info("Fetching products cheaper than price: {}", price);
        return productService.getProductsCheaperThan(price);
    }


    // 6 Search by name
    @GetMapping("/search/{name}")
    public List<Product> searchProducts(@PathVariable String name) {
        log.info("Searching products by name: {}", name);
        return productService.searchProductsByName(name);
    }

    // 7 Update product price
    @PutMapping("/{id}/price/{newPrice}")
    public Product updateProductPrice(@PathVariable int id, @PathVariable Double newPrice) {
        log.info("Updating product price. ID: {}, New Price: {}", id, newPrice);
        return productService.updateProductPrice(id, newPrice);
    }

    // 8 Update product quantity
    @PutMapping("/{id}/quantity/{newQuantity}")
    public Product updateProductQuantity(@PathVariable int id, @PathVariable Integer newQuantity) {
        log.info("Updating product quantity. ID: {}, New Quantity: {}", id, newQuantity);
        return productService.updateProductQuantity(id, newQuantity);
    }

    //10 Delete product
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        log.warn("Deleting product with ID: {}", id);
        productService.deleteProduct(id);
        return "Product deleted successfully!";
    }
}



