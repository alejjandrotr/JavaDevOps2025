package com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.DeliveryTimeCalculators;

import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.dto.ShippingTimeDto;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.entities.Store;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.errors.ShippingNotAvaible;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PickUpProgramadoDeliveryCalculator implements IDeliveryTimeCalculator {
    @Override
    public ShippingTimeDto calculateDeliveryTime(String productCode, Long cityId, Store store) {
       return null;
    }
}
