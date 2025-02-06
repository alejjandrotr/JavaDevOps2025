package com.alejjandrodev.ArcaSupliers.ArcaSupliers.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoreDto {

    @NotBlank()
    private String name;
}
