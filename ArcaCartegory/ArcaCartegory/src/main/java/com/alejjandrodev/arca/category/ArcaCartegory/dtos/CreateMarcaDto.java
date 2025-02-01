package com.alejjandrodev.arca.category.ArcaCartegory.dtos;

import com.alejjandrodev.arca.category.ArcaCartegory.enums.Paises;
import jakarta.validation.constraints.NotBlank;

public class CreateMarcaDto {

    @NotBlank()
    private  String name;

    @NotBlank()
    private String descripcion;

    @NotBlank()
    private String logo;

    @NotBlank()
    private Paises paisOrigen;

    public Paises getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(Paises paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
