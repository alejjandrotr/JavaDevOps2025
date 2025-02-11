package com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.repositories;

import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
