package com.alejjandrodev.ArcaSupliers.ArcaSupliers.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToOne
    private Supplier supplier;

    /*
    @ManyToMany
    @JoinTable(
            name = "product_supplier",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "supplier_id")
    )
    private Set<Supplier> suppliers = new HashSet<>();
    */

}
