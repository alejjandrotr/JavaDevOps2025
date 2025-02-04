package com.alejjandrodev.ArcaSupliers.ArcaSupliers.repositories;

import com.alejjandrodev.ArcaSupliers.ArcaSupliers.entities.Suplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuplierRepository  extends JpaRepository<Suplier, Long> {

    List<Suplier> findAllByIsActive(boolean active);
}
