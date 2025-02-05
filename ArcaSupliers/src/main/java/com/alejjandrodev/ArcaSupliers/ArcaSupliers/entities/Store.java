package com.alejjandrodev.ArcaSupliers.ArcaSupliers.entities;

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

    @Column()
    private  String name;
}
