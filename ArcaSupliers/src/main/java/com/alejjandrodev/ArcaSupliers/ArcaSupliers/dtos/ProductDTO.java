package com.alejjandrodev.ArcaSupliers.ArcaSupliers.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private String name;
    private String description;
    private BigDecimal purchasePrice;
    private BigDecimal salePrice;
    private Long supplierId;
}
