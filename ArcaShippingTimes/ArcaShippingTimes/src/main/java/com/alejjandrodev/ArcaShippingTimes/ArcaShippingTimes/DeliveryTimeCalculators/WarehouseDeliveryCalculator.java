package com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.DeliveryTimeCalculators;

import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.dto.ShippingTimeDto;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.dto.WarehouseTimeDeliveryDto;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.entities.Store;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.entities.StoreDeliveryTime;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.entities.Warehouse;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.repositories.StoreDeliveryTimeRepository;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.services.ClosestWarehouseFinder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public  class WarehouseDeliveryCalculator implements IDeliveryTimeCalculator {
    private final ClosestWarehouseFinder closestWarehouseFinder;
    private final StoreDeliveryTimeRepository storeDeliveryTimeRepository;

    public WarehouseDeliveryCalculator(ClosestWarehouseFinder closestWarehouseFinder, StoreDeliveryTimeRepository storeDeliveryTimeRepository) {
        this.closestWarehouseFinder = closestWarehouseFinder;
        this.storeDeliveryTimeRepository = storeDeliveryTimeRepository;
    }

    @Override
    public ShippingTimeDto calculateDeliveryTime(String productCode, Long cityId, Store store) {
        WarehouseTimeDeliveryDto warehouseTimeDeliveryDto = closestWarehouseFinder.findClosestWarehouseWithProduct(productCode, store);

        if (warehouseTimeDeliveryDto != null && warehouseTimeDeliveryDto.getTime().toHours() != 99999) {
            ShippingTimeDto shippingTimeDto = new ShippingTimeDto();
            shippingTimeDto.setMethod("Delivery");
            shippingTimeDto.setTime(LocalDateTime.now().plus(warehouseTimeDeliveryDto.getTime()).plusDays(3));
            return shippingTimeDto;
        }
        return null;
    }

    // Extract this method to a separate service
    private Duration getDeliveryTimeFromWarehouseToStore(Warehouse warehouse, Store store) {
        StoreDeliveryTime deliveryTime = storeDeliveryTimeRepository.findByWarehouseOriginIdAndStoreId(warehouse.getId(), store.getId());
        if (deliveryTime == null) {
            return Duration.ofHours(99999);
        }
        return Duration.ofHours(deliveryTime.getDeliveryTimeHours());
    }
}
