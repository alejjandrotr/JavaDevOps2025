package com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.entities;

import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.enums.WarehouseType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Enumerated(EnumType.STRING)
    private WarehouseType warehouseType;
    private String warehouseName;
}
