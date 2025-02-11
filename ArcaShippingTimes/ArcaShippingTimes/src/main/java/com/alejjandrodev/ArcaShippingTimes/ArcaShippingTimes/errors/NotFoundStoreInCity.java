package com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.errors;

import org.springframework.http.HttpStatus;

public class NotFoundStoreInCity extends CommonException {
    private  Long city;

    public NotFoundStoreInCity(Long city) {
        super(HttpStatus.NOT_FOUND, "No hay tienda en la ciudad " + city);
        this.city = city;
    }

    public Long getCity() {
        return city;
    }
}
