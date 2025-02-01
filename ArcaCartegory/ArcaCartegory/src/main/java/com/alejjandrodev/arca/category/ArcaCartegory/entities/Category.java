package com.alejjandrodev.arca.category.ArcaCartegory.entities;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "categorias")
public class Category {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 60)
    private String name;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column()
    private Date activeSince;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "imagen")
    private String imagen;

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

    public Date getActiveSince() {
        return activeSince;
    }

    public void setActiveSince(Date activeSince) {
        this.activeSince = activeSince;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}
