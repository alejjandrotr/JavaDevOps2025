package com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.controllers;

import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.dto.TimeResponseDto;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.errors.CommonException;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.errors.ErrorResponseDto;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.errors.NotFoundStoreInCity;
import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.services.DeliveryTimeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.Duration;

@RestController
public class DeliveryTimeController {

    private final DeliveryTimeService deliveryTimeService;

    public DeliveryTimeController(DeliveryTimeService deliveryTimeService) {
        this.deliveryTimeService = deliveryTimeService;
    }

    @GetMapping("/deliveryTime")
    public ResponseEntity<TimeResponseDto> getDeliveryTime(
            @RequestParam String productCode,
            @RequestParam Long city) {
            TimeResponseDto deliveryTime = deliveryTimeService.calculateDeliveryTimes(productCode, city);
            return ResponseEntity.ok(deliveryTime);
    }

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(CommonException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toErrorResponseDto());
    }
}

