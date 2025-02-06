package com.alejjandrodev.ArcaSupliers.ArcaSupliers.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal purchasePrice;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal salePrice;

    @Column(nullable = false)
    private Integer stock;

    @ManyToMany(mappedBy = "products", cascade = CascadeType.PERSIST)
    private Set<Supplier> suppliers = new HashSet<>();

}
