package com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TimeResponseDto {
    List<ShippingTimeDto> shippingTimes = new ArrayList<>();

    public void addShippingTime(ShippingTimeDto shippingTime) {
        this.shippingTimes.add(shippingTime);
    }
}
