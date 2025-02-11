package com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.repositories;

import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.entities.StoreDeliveryTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreDeliveryTimeRepository extends JpaRepository<StoreDeliveryTime, Long> {
    StoreDeliveryTime findByWarehouseOriginIdAndStoreId(Long id, Long id1);
}
