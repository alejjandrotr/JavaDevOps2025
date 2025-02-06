package com.alejjandrodev.ArcaSupliers.ArcaSupliers.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   /* @OneToOne
    private Address address;*/

   /* @OneToMany(mappedBy = "store")
    private List<Supplier> suppliers; */

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Supplier supplier;

    @Column()
    private  String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
}
