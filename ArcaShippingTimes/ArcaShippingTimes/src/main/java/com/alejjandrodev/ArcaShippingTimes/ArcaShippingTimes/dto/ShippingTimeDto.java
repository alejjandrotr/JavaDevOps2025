package com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShippingTimeDto {
    private String method;
    private LocalDateTime time;
}
