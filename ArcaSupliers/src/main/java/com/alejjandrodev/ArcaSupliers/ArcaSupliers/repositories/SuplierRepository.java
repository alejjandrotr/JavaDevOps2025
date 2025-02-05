package com.alejjandrodev.ArcaSupliers.ArcaSupliers.repositories;

import com.alejjandrodev.ArcaSupliers.ArcaSupliers.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuplierRepository  extends JpaRepository<Supplier, Long> {

    List<Supplier> findAllByIsActive(boolean active);
}
