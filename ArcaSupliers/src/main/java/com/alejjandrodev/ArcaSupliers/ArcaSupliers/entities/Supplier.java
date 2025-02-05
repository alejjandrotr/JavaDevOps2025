package com.alejjandrodev.ArcaSupliers.ArcaSupliers.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column()
    private String name;

    @Column()
    private String description;

    @Column()
    private String address;

    @Column()
    private String telefono;

    @Column()
    private String email;

    @Column()
    private String sitioWeb;

    @Column()
    private boolean isActive;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column()
    private Date firstPurchase;


    /*

    @ManyToOne
    private Store store;

    @OneToOne
    private SupplierContact contact;


    @ManyToMany(mappedBy = "suppliers")
    private Set<Product> products = new HashSet<>();

    */

}
