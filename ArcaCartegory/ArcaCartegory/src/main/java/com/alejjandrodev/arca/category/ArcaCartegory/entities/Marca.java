package com.alejjandrodev.arca.category.ArcaCartegory.entities;

import com.alejjandrodev.arca.category.ArcaCartegory.enums.Paises;
import jakarta.persistence.*;

@Entity
public class Marca {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private  String name;

    @Column()
    private String descripcion;

    @Column()
    private String logo;

    @Enumerated()
    private Paises paisOrigen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Paises getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(Paises paisOrigen) {
        this.paisOrigen = paisOrigen;
    }
}
