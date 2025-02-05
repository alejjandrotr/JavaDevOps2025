package com.alejjandrodev.ArcaSupliers.ArcaSupliers.dtos;

import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class UpdateSuplierDto {

    private String name;

    private String description;

    private String address;

    private String telefono;

    @Email
    private String email;

    @URL
    private String sitioWeb;

    private Boolean isActive;

}
