package com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.errors;

import org.springframework.http.HttpStatus;

public class ShippingNotAvaible extends CommonException {

    public ShippingNotAvaible(){
        super(HttpStatus.BAD_REQUEST, "Mensajito de error");
    }
}
