package com.alejjandrodev.ArcaSupliers.ArcaSupliers.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class CreateSuplierDto {

    @NotBlank()
    private String name;

    @NotBlank()
    private String description;

    @NotBlank()
    private String address;

    @NotBlank()
    private String telefono;

    @Email()
    private String email;

    @URL()
    private String sitioWeb;

    @NotNull()
    private Boolean isActive;

   @NotNull()
    private SupplierContactDto contact;
}



