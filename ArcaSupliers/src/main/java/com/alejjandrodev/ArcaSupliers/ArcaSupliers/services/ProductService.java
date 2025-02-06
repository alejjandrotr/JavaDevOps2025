package com.alejjandrodev.ArcaSupliers.ArcaSupliers.services;

import com.alejjandrodev.ArcaSupliers.ArcaSupliers.dtos.ProductDTO;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.entities.Product;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.entities.Supplier;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.repositories.ProductRepository;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Método para agregar un nuevo producto
    public Product addProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPurchasePrice(productDTO.getPurchasePrice());
        product.setSalePrice(productDTO.getSalePrice());
        product.setStock(0);
        return productRepository.save(product);
    }

    // Método para obtener todos los proveedores asociados a un producto específico
    public Set<Supplier> getSuppliersByProductId(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        return productOptional.map(Product::getSuppliers).orElse(Set.of());
    }


}
