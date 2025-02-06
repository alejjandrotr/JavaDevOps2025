package com.alejjandrodev.ArcaSupliers.ArcaSupliers.repositories;

import com.alejjandrodev.ArcaSupliers.ArcaSupliers.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
