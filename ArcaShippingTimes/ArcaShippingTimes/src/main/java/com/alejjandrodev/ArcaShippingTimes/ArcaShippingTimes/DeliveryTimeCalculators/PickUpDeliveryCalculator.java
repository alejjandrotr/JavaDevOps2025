package com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.DeliveryTimeCalculators;

import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.dto.ShippingTimeDto;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.entities.Store;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PickUpDeliveryCalculator implements IDeliveryTimeCalculator {
    @Override
    public ShippingTimeDto calculateDeliveryTime(String productCode, Long cityId, Store store) {
        ShippingTimeDto shippingTimeDto = new ShippingTimeDto();
        shippingTimeDto.setMethod("PickUp");
        shippingTimeDto.setTime(LocalDateTime.now().plusHours(1));
        return shippingTimeDto;
    }
}
