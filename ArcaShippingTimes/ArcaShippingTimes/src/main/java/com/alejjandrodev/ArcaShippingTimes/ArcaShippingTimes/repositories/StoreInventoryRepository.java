package com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.repositories;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreInventoryRepository extends JpaRepository<StoreInventory, Long>, ProductStoreRepository{
    @Query("SELECT si.store FROM StoreInventory si " +
            "WHERE si.product.productCode = :productCode " +
            "AND si.store.city.id = :cityId " +
            "AND si.quantity > 0")
    List<Store> findStoresByProductCodeAndCityIdWithStock(
            @Param("productCode") String productCode,
            @Param("cityId") Long cityId
    );


    @Query("SELECT si.store FROM StoreInventory si " +
            "WHERE si.product.productCode = :productCode " +
            "AND si.store.city.id = :cityId " +
            "AND si.quantity > 0")
    List<Store> findStoresByProductCodeAndCityIdWithStock2(
            @Param("productCode") String productCode,
            @Param("cityId") Long cityId
    );
    @Query("SELECT si.store FROM StoreInventory si " +
            "WHERE si.product.productCode = :productCode " +
            "AND si.store.city.id = :cityId " +
            "AND si.quantity > 0")
    List<Store> findStoresByProductCodeAndCityIdWithStock3(
            @Param("productCode") String productCode,
            @Param("cityId") Long cityId
    );

    @Query("SELECT si.store FROM StoreInventory si " +
            "WHERE si.product.productCode = :productCode " +
            "AND si.store.city.id = :cityId " +
            "AND si.quantity > 0")
    List<Store> findStoresByProductCodeAndCityIdWithStock4(
            @Param("productCode") String productCode,
            @Param("cityId") Long cityId
    );


}
