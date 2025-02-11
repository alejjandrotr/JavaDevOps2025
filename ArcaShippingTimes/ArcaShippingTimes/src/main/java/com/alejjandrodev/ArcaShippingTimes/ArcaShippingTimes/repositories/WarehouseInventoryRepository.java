package com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.repositories;

import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.entities.Store;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.entities.Warehouse;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.entities.WarehouseInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WarehouseInventoryRepository extends JpaRepository<WarehouseInventory, Long> {
    @Query("SELECT wi.warehouse FROM WarehouseInventory wi WHERE wi.product.productCode = :productCode AND wi.quantity > 0")
    List<Warehouse> findWarehousesByProductCodeWithStock(@Param("productCode") String productCode);

    @Query("SELECT wi.warehouse FROM WarehouseInventory wi " +
            "WHERE wi.product.productCode = :productCode " +
            "AND wi.warehouse.city.id = :cityId " +
            "AND wi.quantity > 0")
    List<Warehouse> findWareHouseByProductCodeAndCityIdWithStock(
            @Param("productCode") String productCode,
            @Param("cityId") Long cityId
    );
}
