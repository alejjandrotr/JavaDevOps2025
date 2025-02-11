package com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponseDto {
    private Object data;
    private String message;
    private Integer statusCode;
 }
