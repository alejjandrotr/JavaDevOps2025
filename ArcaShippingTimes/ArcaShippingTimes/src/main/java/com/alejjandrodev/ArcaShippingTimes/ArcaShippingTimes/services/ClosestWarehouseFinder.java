package com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.services;

import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.dto.WarehouseTimeDeliveryDto;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.entities.Store;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.entities.StoreDeliveryTime;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.entities.Warehouse;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.enums.WarehouseType;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.repositories.StoreDeliveryTimeRepository;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.repositories.WarehouseInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class ClosestWarehouseFinder {

    private final WarehouseInventoryRepository warehouseInventoryRepository;

    @Autowired
    StoreDeliveryTimeRepository storeDeliveryTimeRepository;

    public ClosestWarehouseFinder(WarehouseInventoryRepository warehouseInventoryRepository) {
        this.warehouseInventoryRepository = warehouseInventoryRepository;

    }

    public WarehouseTimeDeliveryDto findClosestWarehouseWithProduct(String productCode, Store store) {
        List<Warehouse> warehousesSecondaryWithProduct = warehouseInventoryRepository
                .findWarehousesByProductCodeWithStock(productCode)
                .stream()
                .filter(wi -> wi.getWarehouseType() == WarehouseType.SECONDARY)
                .toList();

        if (warehousesSecondaryWithProduct.isEmpty()) {
            return null;
        }

        Warehouse closestWarehouse = null;
        Duration minDeliveryTime = null;

        for (Warehouse warehouse : warehousesSecondaryWithProduct) {
            Duration deliveryTime = this.getDeliveryTimeFromWarehouseToStore(warehouse, store);
            if (closestWarehouse == null || deliveryTime.compareTo(minDeliveryTime) < 0) {
                closestWarehouse = warehouse;
                minDeliveryTime = deliveryTime;
            }
        }
        return new WarehouseTimeDeliveryDto(closestWarehouse, minDeliveryTime);
    }

    public Duration getDeliveryTimeFromWarehouseToStore(Warehouse warehouse, Store store) {
        StoreDeliveryTime deliveryTime = storeDeliveryTimeRepository.findByWarehouseOriginIdAndStoreId(warehouse.getId(), store.getId());
        if (deliveryTime == null) {
            return Duration.ofHours(99999);
        }
        return Duration.ofHours(deliveryTime.getDeliveryTimeHours());
    }
}

