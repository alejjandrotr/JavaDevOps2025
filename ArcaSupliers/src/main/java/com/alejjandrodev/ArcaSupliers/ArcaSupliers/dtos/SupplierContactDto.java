package com.alejjandrodev.ArcaSupliers.ArcaSupliers.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierContactDto {

    @NotBlank()
    private String name;

    @NotBlank()
    private String email;

    @NotBlank()
    private String phone;
}
