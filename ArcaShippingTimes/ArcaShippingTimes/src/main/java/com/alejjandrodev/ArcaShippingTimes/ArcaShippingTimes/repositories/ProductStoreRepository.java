package com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.repositories;

import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.entities.Store;

import java.util.List;

public interface ProductStoreRepository {

    List<Store> findStoresByProductCodeAndCityIdWithStock(
            String productCode,
            Long cityId
    );
}
