package org.adaschool.api.controller.product;

import jakarta.servlet.http.HttpServletRequest;
import org.adaschool.api.repository.product.Product;
import org.adaschool.api.service.product.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/products/")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(@Autowired ProductsService productsService) {
        this.productsService = productsService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product newProduct) {
        //TODO implement this method
        URI createdProductUri = URI.create("");
        Product product = productsService.save(newProduct);
        return ResponseEntity.created(createdProductUri).body(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> product = productsService.all();
        return ResponseEntity.ok(product);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") String id) {
        Optional<Product> product = productsService.findById(id);
        return ResponseEntity.ok(product.get());
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product updateProduct, @PathVariable("id") String id) {
        //TODO implement this method
        Optional<Product> product = productsService.findById(id);
        if (product.isPresent()) {
            product.get().setName(updateProduct.getName());
            product.get().setDescription(updateProduct.getDescription());
            product.get().setCategory(updateProduct.getCategory());
            product.get().setTags(updateProduct.getTags());
            product.get().setPrice(updateProduct.getPrice());
            product.get().setImageUrl(updateProduct.getImageUrl());
            final Product updatedProduct = productsService.update(product.get(),id);
            return ResponseEntity.ok(updatedProduct);
        }
        return ResponseEntity.ok(product.get());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String id) {
        //TODO implement this method
        productsService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
