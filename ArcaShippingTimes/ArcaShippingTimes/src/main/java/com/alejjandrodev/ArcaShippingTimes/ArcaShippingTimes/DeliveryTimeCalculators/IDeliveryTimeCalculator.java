package com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.DeliveryTimeCalculators;

import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.dto.ShippingTimeDto;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.entities.Store;

public interface IDeliveryTimeCalculator {
    ShippingTimeDto calculateDeliveryTime(String productCode, Long cityId, Store store);
}

