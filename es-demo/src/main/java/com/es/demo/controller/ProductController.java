package com.es.demo.controller;

import com.es.demo.indices.ProductIndex;
import com.es.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.query.UpdateResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    public ProductIndex create(@RequestBody ProductIndex productIndex) {
        return productService.createProduct(productIndex);
    }

    @GetMapping("/get-all")
    public List<ProductIndex> getAll() {
        return productService.findAll();
    }

    @GetMapping("/get-by-id")
    public ProductIndex getById(@RequestParam String id) {
        return productService.getById(id);
    }

    @DeleteMapping("/delete-all")
    public void deleteAll() {
        productService.deleteAll();
    }

    @DeleteMapping("/delete-by-id")
    public void deleteById(@RequestParam String id) {
        productService.deleteById(id);
    }

    @PatchMapping("/update")
    public UpdateResponse update(@RequestParam String id, @RequestBody ProductIndex productIndex) {
        return productService.updateProduct(id, productIndex);
    }
}
