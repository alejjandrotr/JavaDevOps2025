package com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.dto;

import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.entities.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WarehouseTimeDeliveryDto {
    private Warehouse warehouse;
    private Duration time;
}
