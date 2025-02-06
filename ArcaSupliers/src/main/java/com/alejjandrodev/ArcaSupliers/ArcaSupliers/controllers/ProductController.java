package com.alejjandrodev.ArcaSupliers.ArcaSupliers.controllers;

import com.alejjandrodev.ArcaSupliers.ArcaSupliers.dtos.ProductDTO;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.entities.Product;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.entities.Supplier;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Endpoint para agregar un nuevo producto
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO) {
        Product newProduct = productService.addProduct(productDTO);
        return ResponseEntity.ok(newProduct);
    }

    // Endpoint para obtener proveedores por ID de producto
    @GetMapping("/{id}/suppliers")
    public ResponseEntity<Set<Supplier>> getSuppliers(@PathVariable Long id) {
        Set<Supplier> suppliers = productService.getSuppliersByProductId(id);
        return ResponseEntity.ok(suppliers);
    }


}
