package com.alejjandrodev.arca.category.ArcaCartegory.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateCategoryDto {

    @NotNull()
    @NotBlank()
    String nombre;

    @NotBlank(message = "La descripcion no debe estar en blanco")
    String descripcion;

    @NotBlank()
    String imagen;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public CreateCategoryDto(String nombre, String descripcion, String imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }
}
