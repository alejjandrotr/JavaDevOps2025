package com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.services;

import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.dto.ShippingTimeDto;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.dto.TimeResponseDto;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.dto.WarehouseTimeDeliveryDto;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.entities.*;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.enums.WarehouseType;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.errors.NotFoundStoreInCity;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.repositories.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.time.Duration;

@Service
public class DeliveryTimeService {

    private final WarehouseRepository warehouseRepository;
    private final StoreRepository storeRepository;
    private final WarehouseDeliveryTimeRepository warehouseDeliveryTimeRepository;
    private final StoreDeliveryTimeRepository storeDeliveryTimeRepository;
    private final WarehouseInventoryRepository warehouseInventoryRepository;
    private final StoreInventoryRepository storeInventoryRepository;


    public DeliveryTimeService(WarehouseRepository warehouseRepository, StoreRepository storeRepository, WarehouseDeliveryTimeRepository warehouseDeliveryTimeRepository, StoreDeliveryTimeRepository storeDeliveryTimeRepository, WarehouseInventoryRepository warehouseInventoryRepository, StoreInventoryRepository storeInventoryRepository) {
        this.warehouseRepository = warehouseRepository;
        this.storeRepository = storeRepository;
        this.warehouseDeliveryTimeRepository = warehouseDeliveryTimeRepository;
        this.storeDeliveryTimeRepository = storeDeliveryTimeRepository;
        this.warehouseInventoryRepository = warehouseInventoryRepository;
        this.storeInventoryRepository = storeInventoryRepository;
    }



    public TimeResponseDto calculateDeliveryTimes(String productCode, Long city) {
        // Verificando que exista el product
        TimeResponseDto responseDto = new TimeResponseDto();

        //Hay tienda en esa ciudad
        List<Store> storesOnTheCity = storeRepository.findAllByCityId(city);
        if (storesOnTheCity.isEmpty()){
            throw new NotFoundStoreInCity(city);
        }
        Store storeOnTheCity = storesOnTheCity.get(0);
        //Express

        List<Warehouse> warehouseOnTheCityWithTheProduct = warehouseInventoryRepository.findWareHouseByProductCodeAndCityIdWithStock(productCode, city);
        List<Store> storeOnTheCityWithTheProduct = storeInventoryRepository.findStoresByProductCodeAndCityIdWithStock(productCode, city);

        if (warehouseOnTheCityWithTheProduct.size() > 0 ||
                storeOnTheCityWithTheProduct.size() > 0){
            ShippingTimeDto shippingTimeDto = new ShippingTimeDto();
            shippingTimeDto.setMethod("Express");
            shippingTimeDto.setTime(LocalDateTime.now().plusDays(1));
            responseDto.addShippingTime(shippingTimeDto);
        }

        //PickUp
        if (storeOnTheCityWithTheProduct.size() > 0){
            ShippingTimeDto shippingTimeDto = new ShippingTimeDto();
            shippingTimeDto.setMethod("PickUp");
            shippingTimeDto.setTime(LocalDateTime.now().plusHours(1));
            responseDto.addShippingTime(shippingTimeDto);
        }

        WarehouseTimeDeliveryDto warehouseTimeDeliveryDto = findClosestWarehouseWithProduct(productCode, storeOnTheCity);

        if (warehouseTimeDeliveryDto != null && warehouseTimeDeliveryDto.getTime().toHours() != 99999){
            if (storeOnTheCityWithTheProduct.size() == 0) {
                ShippingTimeDto shippingTimeDtoPikup = new ShippingTimeDto();
                shippingTimeDtoPikup.setMethod("PickUp");
                shippingTimeDtoPikup.setTime(LocalDateTime.now().plus(warehouseTimeDeliveryDto.getTime()).plusHours(1));
                responseDto.addShippingTime(shippingTimeDtoPikup);
            }
            ShippingTimeDto shippingTimeDtoDelivery = new ShippingTimeDto();
            shippingTimeDtoDelivery.setMethod("Delivery");
            shippingTimeDtoDelivery.setTime(LocalDateTime.now().plus(warehouseTimeDeliveryDto.getTime()).plusDays(3));
            responseDto.addShippingTime(shippingTimeDtoDelivery);

        }


        return responseDto;
    }

    private WarehouseTimeDeliveryDto findClosestWarehouseWithProduct(String productCode, Store store) {
        // Find all warehouses that have the product
        List<Warehouse> warehousesSecondaryWithProduct = warehouseInventoryRepository.findWarehousesByProductCodeWithStock(productCode)
                .stream()
                .filter(wi ->
                      wi.getWarehouseType() == WarehouseType.SECONDARY)
                .toList();


        if (warehousesSecondaryWithProduct.isEmpty()) {
            return null;
        }

        Warehouse closestWarehouse = null;
        Duration minDeliveryTime = null;

        for (Warehouse warehouse : warehousesSecondaryWithProduct) {
            Duration deliveryTime = getDeliveryTimeFromWarehouseToStore(warehouse, store);
            if (closestWarehouse == null || deliveryTime.compareTo(minDeliveryTime) < 0) {
                closestWarehouse = warehouse;
                minDeliveryTime = deliveryTime;
            }
        }
        WarehouseTimeDeliveryDto warehouseTimeDeliveryDto = new WarehouseTimeDeliveryDto(closestWarehouse, minDeliveryTime);
        return warehouseTimeDeliveryDto;
    }

    private Duration getDeliveryTimeFromWarehouseToStore(Warehouse warehouse, Store store) {
        StoreDeliveryTime deliveryTime = storeDeliveryTimeRepository.findByWarehouseOriginIdAndStoreId(warehouse.getId(), store.getId());
        if (deliveryTime == null) {
            return Duration.ofHours(99999);
        }
        return Duration.ofHours(deliveryTime.getDeliveryTimeHours());
    }
}
