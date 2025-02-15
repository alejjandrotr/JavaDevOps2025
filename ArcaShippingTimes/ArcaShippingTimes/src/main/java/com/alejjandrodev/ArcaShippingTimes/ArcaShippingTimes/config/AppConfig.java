package com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.config;

import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.DeliveryTimeCalculators.ExpressDeliveryCalculator;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.DeliveryTimeCalculators.IDeliveryTimeCalculator;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.DeliveryTimeCalculators.PickUpDeliveryCalculator;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.DeliveryTimeCalculators.WarehouseDeliveryCalculator;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.repositories.StoreDeliveryTimeRepository;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.services.ClosestWarehouseFinder;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

public class AppConfig {


    @Bean
    public List<IDeliveryTimeCalculator> deliveryTimeCalculators(ExpressDeliveryCalculator expressDeliveryCalculator, PickUpDeliveryCalculator pickUpDeliveryCalculator, WarehouseDeliveryCalculator warehouseDeliveryCalculator) {
        return Arrays.asList(expressDeliveryCalculator,
                            pickUpDeliveryCalculator,
                            warehouseDeliveryCalculator);
    }


}
