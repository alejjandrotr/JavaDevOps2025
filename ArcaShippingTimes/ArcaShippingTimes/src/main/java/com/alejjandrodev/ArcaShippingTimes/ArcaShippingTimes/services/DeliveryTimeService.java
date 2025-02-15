package com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.services;

import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.DeliveryTimeCalculators.IDeliveryTimeCalculator;
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

    private final StoreRepository storeRepository;

    private final List<IDeliveryTimeCalculator> deliveryTimeCalculators;


    public DeliveryTimeService( StoreRepository storeRepository,  List<IDeliveryTimeCalculator> deliveryTimeCalculators) {
        this.storeRepository = storeRepository;
        this.deliveryTimeCalculators = deliveryTimeCalculators;
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
        for (IDeliveryTimeCalculator calculator : deliveryTimeCalculators) {
            ShippingTimeDto shippingTimeDto = calculator.calculateDeliveryTime(productCode, city, storeOnTheCity);
            if (shippingTimeDto != null) {
                responseDto.addShippingTime(shippingTimeDto);
            }
        }

        return responseDto;


    }


}
