package com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.DeliveryTimeCalculators;

import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.dto.ShippingTimeDto;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.entities.Store;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.repositories.ProductStoreRepository;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.repositories.StoreInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExpressDeliveryCalculator implements IDeliveryTimeCalculator {

    @Autowired
    ProductStoreRepository storeInventoryRepository;

    @Override
    public ShippingTimeDto calculateDeliveryTime(String productCode, Long cityId, Store store) {
        List<Store> storeList = storeInventoryRepository.findStoresByProductCodeAndCityIdWithStock(productCode, cityId);
        if (!storeList.isEmpty()) {
            ShippingTimeDto shippingTimeDto = new ShippingTimeDto();
            shippingTimeDto.setMethod("Express");
            shippingTimeDto.setTime(LocalDateTime.now().plusDays(1));
            return shippingTimeDto;
        }
        return null;
    }
}
